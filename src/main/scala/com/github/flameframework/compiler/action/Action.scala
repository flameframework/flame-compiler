package com.github.flameframework.compiler.action

import com.github.flameframework.compiler.domain.{Descriptor, Variable, Type}

import scala.collection.JavaConversions._

/**
 * Created by michel on 13-12-14.
 */
class Action(description: String,
                      inputVariables: java.util.List[Variable] = Nil,
                      outputType: Type = null) {

  private val descriptor = Descriptor(classOf[Action], description)

  def getName = descriptor
  def getInputVariables = inputVariables
  def getOutputType = outputType

}

object Action {

  def apply(description: String, inputVariables: Seq[Variable] = Nil, outputType: Option[Type] = None) =
    new Action(description, inputVariables, outputType.orNull)

}