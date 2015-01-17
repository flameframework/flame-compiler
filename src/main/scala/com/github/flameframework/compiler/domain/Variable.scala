package com.github.flameframework.compiler.domain

/**
 * Created by michel on 30-11-14.
 */
class Variable(name: Descriptor[Variable], variableType: Type) extends Value {

  def getName = name
  def getType = variableType

}

object Variable {

  def apply(description: String, propertyType: Type) = new Variable(Descriptor(classOf[Variable], description), propertyType)

}
