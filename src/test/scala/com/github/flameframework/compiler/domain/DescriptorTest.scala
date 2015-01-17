package com.github.flameframework.compiler.domain

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

/**
 * Created by michel on 12-01-15.
 */
@RunWith(classOf[JUnitRunner])
class DescriptorTest extends FlatSpec {

  trait Fixture {
    val descriptor = new Descriptor[DomainClass](classOf[DomainClass], Seq("three", "single", "words"))
  }

  "toLowerCamelCase" should "return lowerCamelCase" in new Fixture {
    assert(descriptor.getToLowerCamelCase == "threeSingleWords")
  }

  "toUpperCamelCase" should "return UpperCamelCase" in new Fixture {
    assert(descriptor.getToUpperCamelCase == "ThreeSingleWords")
  }

  "toUpperCase" should "return UPPER_CASE" in new Fixture {
    assert(descriptor.getToUpperCase == "THREE_SINGLE_WORDS")
  }

  "toLowerCase" should "return lower-case" in new Fixture {
    assert(descriptor.getToLowerCase == "three-single-words")
  }

}
