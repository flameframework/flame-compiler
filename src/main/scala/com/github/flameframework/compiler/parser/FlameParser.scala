package com.github.flameframework.compiler.parser

import com.github.flameframework.compiler.action._
import com.github.flameframework.compiler.base.Identifier
import com.github.flameframework.compiler.domain._
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.SafeConstructor

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.reflect._
import scala.reflect.runtime.universe.{Type => UniverseType}

/**
 * Parser that parses YAML into flame interaction model classes.
 */
class FlameParser() {

  private type Document = mutable.Map[String, Any]

  private val yamlParser = new Yaml(new SafeConstructor)

  /** Helper class to convert java map instances to scala map instances. */
  private implicit class MapDecorator(javaMap: Any) {
    def toScalaMap[K, V]: mutable.Map[K, V] = javaMap.asInstanceOf[java.util.Map[K, V]]
  }

  /**
   * Parses and adds the flame interaction model classes in the yaml.
   * @param yaml the yaml to parse
   */
  def parse(yaml: String) = {
    def documents = yamlParser.loadAll(yaml).map(_.toScalaMap[String, Any])

    val registry = documents.map(parseDocument)

    InteractionModel(
      filterByType[AppInfo](registry).headOption.orNull,
      filterByType[DomainClass](registry),
      filterByType[NativeAction](registry),
      filterByType[ComposedAction](registry))
  }

  private def parseDocument(document: mutable.Map[String, Any]) = {
    document.head._1 match {
      case "app" =>
        parseAppInfo(document)
      case "domain class" =>
        parseDomainClass(document)
      case "action" =>
        parseNativeAction(document)
      case "composed action" =>
        parseComposedAction(document)
      case other =>
        throw new FlameParserException(s"Unknown type '${other}'")
    }
  }

  private def parseAppInfo(document: Document) : AppInfo = {
    AppInfo(
      document("app").asInstanceOf[String],
      ActionCall(document("start action").asInstanceOf[String])
    )
  }

  private def parseDomainClass(document: Document): DomainClass = {
    DomainClass(
      document("domain class").asInstanceOf[String],
      document.get("properties").map(_.toScalaMap[String, String].toSeq.map { case (propName, propType) => Variable(Identifier(propName), parseType(propType)) } ).getOrElse(Nil)
    )
  }

  private def parseNativeAction(document: Document) : NativeAction = {
    NativeAction(
      document("action").asInstanceOf[String],
      _outputType = document.get("output type").map(typeName => parseType(typeName.asInstanceOf[String]))
    )
  }

  private def parseComposedAction(document: Document) : ComposedAction = {
    ComposedAction(
      document("composed action").asInstanceOf[String],
      _actionCalls = document("action calls").asInstanceOf[java.lang.Iterable[String]].map(parseActionCall).toSeq
    )
  }

  private def parseType(typeName: String) : Type = {
    if (typeName.endsWith("*")) {
      ListType(parseSingleType(typeName.dropRight(1)))
    } else {
      parseSingleType(typeName)
    }
  }

  private def parseSingleType(typeName: String) : Type = {
    typeName match {
      case "text" => StringType
      case "integer" => IntegerType
      case typeName => DomainClassType(typeName)
    }
  }

  private def parseActionCall(text: String) : ActionCall = {
    val indexOfAssign = text.indexOf('=')
    val indexOfOpen = if (text.indexOf('(') > -1) text.indexOf('(') else text.length
    val indexOfClose = text.indexOf(')')
    val outputVariable = if (indexOfAssign > -1 ) Some(text.substring(0, indexOfAssign)) else None
    val actionName = text.substring(indexOfAssign + 1, indexOfOpen)
    val inputValues = if (indexOfClose > - 1)
      text.substring(indexOfOpen + 1, indexOfClose).split(",")
    else
      Array[String]()
    ActionCall(
      actionName,
      inputValues.map(Variable(_, VoidType)), // TODO: I do not want to specify a type here!
      outputVariable.map(Variable(_, VoidType)) // TODO: I do not want to specify a type here!
    )
  }

  private def filterByType[A : ClassTag](registry: Iterable[_]) = {
    registry.filter(classTag[A].runtimeClass.isInstance).toSeq.asInstanceOf[Seq[A]]
  }

}
