package com.github.flameframework.compiler.domain

/**
 * Created by michel on 30-11-14.
 */
class Variable(description: String, variableType: Type) extends Value {

  private val descriptor = Descriptor(classOf[Variable], description)

  def getName = descriptor
  def getType = variableType

}

object Variable {

  def apply(description: String, propertyType: Type) = new Variable(description, propertyType)

}
