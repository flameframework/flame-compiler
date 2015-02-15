package com.github.flameframework.compiler.action

import com.github.flameframework.compiler.domain.{VoidType, Descriptor, Ref, Type, Variable}

import scala.collection.JavaConversions._

/**
 * Created by michel on 14-02-15.
 */
abstract class Action(_description: String,
                              _inputVariables: Seq[Variable] = Nil,
                              _outputType: Option[Ref[Type]] = None) {

  // getter methods returning plain Java objects for free marker
  val getName : Descriptor[Action] = Descriptor(classOf[Action], _description)
  val getInputVariables : java.util.List[Variable] = _inputVariables
  val getOutputType : Type = _outputType.getOrElse(new Ref(Some(VoidType))).get

}
