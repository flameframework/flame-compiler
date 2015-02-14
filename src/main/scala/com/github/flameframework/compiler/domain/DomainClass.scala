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
case class DomainClass(_description: String, _properties: Seq[Variable] = Nil) extends Type {

  // getter methods returning plain Java objects for free marker
  val getName : Descriptor[DomainClass] = Descriptor(classOf[DomainClass], _description)
  val getProperties : java.util.List[Variable] = _properties

}
