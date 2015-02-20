package com.github.flameframework.compiler.parser

import com.github.flameframework.compiler.action.{ActionCall, AppInfo, InteractionModel, NativeAction}
import com.github.flameframework.compiler.domain.{DomainClassType, DomainClass, StringType, Variable}
import org.scalatest.FlatSpec

/**
* Created by michel on 08-02-15.
*/
class FlameParserTest extends FlatSpec {

  trait Fixture {
    val parser = new FlameParser()
  }

  "Yaml for a domain class" should "be parsed into a domain class" in new Fixture() {
    val result = parser.parse("""
      domain class:     mail

      properties:
        from  :      text
        to    :      text
        body  :      text
      """)
    assert(result == InteractionModel(
      null,
      Seq(DomainClass("mail", Seq(Variable("from", StringType), Variable("to", StringType), Variable("body", StringType)))),
      Nil,
      Nil
    ))
  }

  "Yaml for a native action" should "be parsed into a native action class" in new Fixture() {
    val result = parser.parse("""
        app:              example mail app
        start action:     fetch all mails
---
        domain class: mail
---
        action:           fetch all mails
        output type:      mail
      """)
    val mail = DomainClass("mail")
    val fetchAllMails = NativeAction("fetch all mails", _outputType = Some(DomainClassType("mail")))
    val appInfo = AppInfo("example mail app", ActionCall("fetchAllMails"))
    assert(result == InteractionModel(appInfo, Seq(mail), Seq(fetchAllMails), Nil))
  }

  "A circular reference" should "be parsed normally" in new Fixture() {
    val result = parser.parse(
      """
         domain class: parent
         properties:
           child : Child
---
         domain class: child
         properties:
           parent: parent
      """)
  }

//  "Yaml with a duplicate definition" should "cause an exception to be thrown" in new Fixture() {
//    intercept[FlameParserException] {
//      parser.parse(
//        """
//          app: app name
//          start action: some action
//---
//          app: duplicate definition
//          start action: some action
//        """)
//    }
//  }
}
