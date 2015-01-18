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

import scala.collection.JavaConversions._

/**
 * Abstract representation for a domain object.
 */
class DomainClass(description: String, properties: java.util.List[Variable]) extends Type {

  private val descriptor = Descriptor(classOf[DomainClass], description)

  def getName = descriptor
  def getProperties = properties

}

object DomainClass {

  def apply(description: String, properties : Seq[Variable] = Nil) = new DomainClass(description, properties)

}