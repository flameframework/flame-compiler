package com.github.flameframework.compiler.parser

import com.github.flameframework.compiler.action._
import com.github.flameframework.compiler.domain._
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.SafeConstructor

import scala.collection.JavaConversions._
import scala.collection.mutable

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

  // A cache of objects that were already parsed
  private var appInfo = None: Option[AppInfo]
  private val typeRegistry = Registry[Type]("text" -> StringType, "integer" -> IntegerType)
  private val actionRegistry = Registry[Action]("list" -> ListAction, "view" -> ViewAction)

  /**
   * Parses and adds the flame interaction model classes in the yaml.
   * @param yaml the yaml to parse
   */
  def parse(yaml: String) = {
    def documents = yamlParser.loadAll(yaml).map(_.toScalaMap[String, Any])

    for (document <- documents) {
      document.head._1 match {
        case "app" =>
          if (appInfo == None) {
            appInfo = Some(parseAppInfo(document))
          } else throw new FlameParserException("App info was provided more than once")
        case "domain class" =>
          val domainClass = parseDomainClass(document)
          typeRegistry.register (document.head._2.asInstanceOf[String], domainClass)
        case "action" =>
          val action = parseNativeAction(document)
          actionRegistry.register (document.head._2.asInstanceOf[String], action)
        case other =>
          throw new FlameParserException("Unknown type $other")
      }
    }
  }

  /** Returns the full model that was parsed. */
  def parsed = InteractionModel(appInfo.orNull, typeRegistry.filterType[DomainClass], actionRegistry.filterType[NativeAction], actionRegistry.filterType[ComposedAction])

  private def parseAppInfo(document: Document) : AppInfo = {
    AppInfo(
      document("app").asInstanceOf[String],
      actionRegistry(document("start action").asInstanceOf[String])
    )
  }

  private def parseDomainClass(document: Document): DomainClass = {
    DomainClass(
      document("domain class").asInstanceOf[String],
      document.get("properties").map(_.toScalaMap[String, String].toSeq.map { case (propName, propType) => Variable(propName, typeRegistry(propType)) } ).getOrElse(Nil)
    )
  }

  private def parseNativeAction(document: Document) : NativeAction = {
    NativeAction(
      document("action").asInstanceOf[String],
      _outputType = document.get("output type").map(typeName => typeRegistry(typeName.asInstanceOf[String]))
    )
  }
}
