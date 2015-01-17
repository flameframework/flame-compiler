package com.github.flameframework.compiler.domain

import scala.collection.JavaConversions._

/**
 * Abstract representation for a domain object.
 */
class DomainClass(description: String, properties: java.util.List[Variable]) extends Type {

  private val descriptor = Descriptor(classOf[DomainClass], description)

  def getName = descriptor
  def getProperties = properties

}

object DomainClass {

  def apply(description: String, properties : Seq[Variable] = Nil) = new DomainClass(description, properties)

}