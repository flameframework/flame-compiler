package com.github.flameframework.compiler.parser

import com.github.flameframework.compiler.action.NativeAction
import com.github.flameframework.compiler.domain.{ListType, StringType, Variable, DomainClass}
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
    assert(result == DomainClass("mail", Seq(Variable("from", StringType), Variable("to", StringType), Variable("body", StringType))))
  }
}
