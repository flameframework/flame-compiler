package com.github.flameframework.compiler.parser

import com.github.flameframework.compiler.domain._
import org.yaml.snakeyaml.Yaml

import scala.collection.JavaConversions._
import scala.collection.mutable

/**
 * Parser that parses YAML into flame interaction model classes.
 */
class FlameParser() {

  private val yamlParser = new Yaml

  /** Helper class to convert java map instances to scala map instances. */
  private implicit class JavaMapExtensions(javaMap: Any) {
    def toScalaMap[K, V] : mutable.Map[K, V] = javaMap.asInstanceOf[java.util.Map[K, V]]
  }

  /**
   * Parses yaml into a flame interaction model classes.
   * @param yaml the yaml to parse
   * @return flame interaction model classes
   */
  def parse(yaml: String) = {
    val document = yamlParser.load(yaml).toScalaMap[String, Any]

    DomainClass(
      _description = document("domain class").asInstanceOf[String],
      _properties = document("properties").toScalaMap[String, String]
          .map{case (propName: String, propType : String) => Variable(propName, typeFor(propType))}.toSeq
    )
  }

  def typeFor(typeName: String): Type = typeName match {
    case "text" => StringType
    case "integer" => IntegerType
  }



}
