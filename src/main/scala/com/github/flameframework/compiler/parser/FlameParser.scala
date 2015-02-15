package com.github.flameframework.compiler.parser

import com.github.flameframework.compiler.action._
import com.github.flameframework.compiler.domain._
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.SafeConstructor

import scala.collection.JavaConversions._
import scala.collection.mutable

import com.github.flameframework.util.WordSplitter._

/**
 * Parser that parses YAML into flame interaction model classes.
 */
class FlameParser() {

  private val yamlParser = new Yaml(new SafeConstructor)

  /** Helper class to convert java map instances to scala map instances. */
  private implicit class Document(javaMap: Any) {
    def toScalaMap[K, V]: mutable.Map[K, V] = javaMap.asInstanceOf[java.util.Map[K, V]]
  }

  // A cache of objects that were already parsed
  private var appInfo = None: Option[AppInfo]
  private val typeRegistry = new Registry[Type](mutable.Map(split("text") -> new Ref(Some(StringType)), split("integer") -> new Ref(Some(IntegerType))))
  private val actionRegistry = new Registry[Action](mutable.Map(split("list") -> new Ref(Some(ListAction)), split("view") -> new Ref(Some(ViewAction))))

  /**
   * Parses and adds the flame interaction model classes in the yaml.
   * @param yaml the yaml to parse
   */
  def parse(yaml: String) = {

    def documents = yamlParser.loadAll(yaml).map(_.toScalaMap[String, Any])

    for (document <- documents) {
      document.head._1 match {
        case "app" =>
          appInfo = Some(parseAppInfo(document)) // TODO what if the app info already exists?
        case "domain class" =>
          val domainClass = parseDomainClass(document)
          typeRegistry.register (document.head._2.asInstanceOf[String], domainClass)
        case "action" =>
          val action = parseNativeAction(document)
          actionRegistry.register (document.head._2.asInstanceOf[String], action)
          // TODO handle case if nothing matches: show decent error message
      }
    }
  }

  /** Returns the full model that was parsed. */
  def parsed = InteractionModel(appInfo.orNull, typeRegistry.filterType[DomainClass], actionRegistry.filterType[NativeAction], actionRegistry.filterType[ComposedAction])

  private def parseAppInfo(document: mutable.Map[String, Any]) : AppInfo = {
    AppInfo(
      document("app").asInstanceOf[String],
      actionRegistry(document("start action").asInstanceOf[String])
    )
  }

  private def parseDomainClass(map: mutable.Map[String, Any]): DomainClass = {
    DomainClass(
      map("domain class").asInstanceOf[String],
      map.getOrElse("properties", new java.util.HashMap[String, String]).toScalaMap[String, String].toSeq.map { case (propName, propType) => Variable(propName, typeRegistry(propType)) }
    )
  }

  private def parseNativeAction(document: mutable.Map[String, Any]) : NativeAction = {
    NativeAction(
      document("action").asInstanceOf[String],
      _outputType = document.get("output type").map(typeName => typeRegistry(typeName.asInstanceOf[String]))
    )
  }
}
