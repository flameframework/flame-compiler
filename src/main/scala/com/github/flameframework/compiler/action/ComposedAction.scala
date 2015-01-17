package com.github.flameframework.compiler.action

import com.github.flameframework.compiler.domain._

import scala.collection.JavaConversions._

/**
 * Created by michel on 13-12-14.
 */
class ComposedAction(name: Descriptor[Action],
                      inputVariables: java.util.List[Variable],
                      outputVariable: Variable,
                      actionCalls: java.util.List[ActionCall]) extends Action(name, inputVariables, outputVariable.getType) {

  val getActionCalls = actionCalls
  val getOutputVariable = outputVariable

}

object ComposedAction {

  def apply(name: String, inputVariables: Seq[Variable] = Nil, outputVariable: Option[Variable] = None, actionCalls: Seq[ActionCall]) =
    new ComposedAction(Descriptor(classOf[Action], name), inputVariables, outputVariable.orNull, actionCalls)

}