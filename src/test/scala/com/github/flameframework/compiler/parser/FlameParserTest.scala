package com.github.flameframework.compiler.parser

import com.github.flameframework.compiler.action.{AppInfo, InteractionModel, NativeAction}
import com.github.flameframework.compiler.domain.{ListType, StringType, Variable, DomainClass, Ref}
import org.scalatest.FlatSpec

/**
* Created by michel on 08-02-15.
*/
class FlameParserTest extends FlatSpec {

  trait Fixture {
    val parser = new FlameParser()
  }

  "Yaml for a domain class" should "be parsed into a domain class" in new Fixture() {
    parser.parse("""
      domain class:     mail

      properties:
        from  :      text
        to    :      text
        body  :      text
      """)
    assert(parser.parsed == InteractionModel(
      null,
      Seq(DomainClass("mail", Seq(Variable("from", new Ref(Some(StringType))), Variable("to", new Ref(Some(StringType))), Variable("body", new Ref(Some(StringType)))))),
      Nil,
      Nil
    ))
  }

  "Yaml for a native action" should "be parsed into a native action class" in new Fixture() {
    parser.parse("""
        app:              example mail app
        start action:     fetch all mails
---
        domain class: mail
---
        action:           fetch all mails
        output type:      mail
      """)
    val mail = DomainClass("mail")
    val fetchAllMails = NativeAction("fetch all mails", _outputType = Some(new Ref(Some(mail))))
    val appInfo = AppInfo("example mail app", new Ref(Some(fetchAllMails)))
    assert(parser.parsed == InteractionModel(appInfo, Seq(mail), Seq(fetchAllMails), Nil))
  }

}
