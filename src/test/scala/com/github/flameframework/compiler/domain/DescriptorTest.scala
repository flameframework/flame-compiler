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

package com.github.flameframework.compiler.domain

import org.scalatest.FlatSpec

/**
 * Created by michel on 12-01-15.
 */
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
