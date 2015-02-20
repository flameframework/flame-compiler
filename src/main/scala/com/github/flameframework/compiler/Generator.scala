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

import com.github.flameframework.compiler.action._
import com.github.flameframework.compiler.domain._
import com.github.flameframework.compiler.parser.FlameParser
import com.github.flameframework.treemarker.TreeMarker

/**
 * Created by michel on 21-11-14.
 */
object Generator {

  def generate(platform: String, target: String, interactionModel: InteractionModel): Unit = {
    val objectWrapper = new FlameObjectWrapper(interactionModel, TreeMarker.version)
    TreeMarker.generate(s"flame/$platform/", target, interactionModel, Some(objectWrapper))
  }

  def main(args: Array[String]) = {
    val text = io.Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("example.yaml")).mkString
    generate("ios", ".", new FlameParser().parse(text))

  }

  def main2(args: Array[String]) = {

    val from = Variable("from", StringType)
    val to = Variable("to", StringType)
    val body = Variable("body", StringType)

    val Mail = DomainClass("mail", Seq(from, to, body))

    val mails = Variable("mails", new ListType(DomainClassType("mail")))

    val fetchAllMails = NativeAction("fetch all mails", _outputType = Some(ListType(DomainClassType("Mail"))))

    val open = ComposedAction("open inbox", _actionCalls = Seq(
      ActionCall("fetchAllMails", _outputVariable = Some(mails)),
      ActionCall("List", Seq(mails))
    ))

    val appInfo = AppInfo("example ios app", _startAction = ActionCall("openInbox"))

    generate("ios", ".", InteractionModel(
      appInfo,
      Seq(Mail),
      Seq(fetchAllMails),
      Seq(open)
    ))
  }
}
