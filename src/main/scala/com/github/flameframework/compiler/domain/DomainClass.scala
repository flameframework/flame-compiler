package com.github.flameframework.compiler.domain

import scala.collection.JavaConversions._

/**
 * Abstract representation for a domain object.
 */
class DomainClass(name : Descriptor[DomainClass], properties: java.util.List[Variable]) extends Type {

  def getName = name
  def getProperties = properties

}

object DomainClass {

  def apply(description: String, properties : Seq[Variable] = Nil) = new DomainClass(Descriptor(classOf[DomainClass], description), properties)

}