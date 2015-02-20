package com.github.flameframework.compiler.action

import com.github.flameframework.compiler.base.{Descriptor, Identifier}
import com.github.flameframework.compiler.domain.{VoidType, Type, Variable}

import scala.collection.JavaConversions._

/**
 * Created by michel on 14-02-15.
 */
abstract class Action(_description: Identifier,
                              _inputVariables: Seq[Variable] = Nil,
                              _outputType: Option[Type] = None) {

  // getter methods returning plain Java objects for free marker
  val getName : Descriptor[Action] = new Descriptor[Action](_description)
  val getInputVariables : java.util.List[Variable] = _inputVariables
  def getOutputType : Type = _outputType.getOrElse(VoidType)

}
