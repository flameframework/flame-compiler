/*
 * Copyright 2015 Michel Vollebregt
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.flameframework.compiler

import com.github.flameframework.compiler.action.{Action, ActionCall, ComposedAction, InteractionModel}
import com.github.flameframework.compiler.domain._
import com.github.flameframework.treemarker.TreeMarker

/**
 * Created by michel on 21-11-14.
 */
object Generator {

  def generate(platform: String, target: String, interactionModel: InteractionModel): Unit = {
    val objectWrapper = new FlameObjectWrapper(TreeMarker.version)
    TreeMarker.generate(s"flame/$platform/", target, interactionModel, Some(objectWrapper))
  }

  def main(args: Array[String]) = {

    val from = Variable("from", StringType)
    val to = Variable("to", StringType)
    val body = Variable("body", StringType)
    val Mail = DomainClass("mail", Seq(from, to, body))
    val mail = Variable("mail", Mail)

    val mails = Variable("mails", ListType(Mail))
    val Inbox = DomainClass("Inbox", Seq(mails))
    val inbox = Variable("inbox", Inbox)

    val obj = Variable("object", ListType(Mail))

    val refresh = Action("refresh inbox", inputVariables = Seq(inbox), outputType = Some(IntegerType))
    val view = Action("view", inputVariables = Seq(obj), outputType = Some(IntegerType))

    val open = ComposedAction("open inbox", inputVariables = Seq(inbox),
      outputVariable = Some(Variable("new item count", IntegerType)),
      actionCalls = Seq(
      ActionCall(refresh, Seq(inbox)),
      ActionCall(view, Seq(PropertyValue(inbox, mails)), Some(Variable("new item count", IntegerType)))
    ))

    generate("ios", "../flame-ios/flame-ios", InteractionModel(
      Seq(Inbox, Mail),
      Seq(refresh, view),
      Seq(open)
    ))
  }
}
