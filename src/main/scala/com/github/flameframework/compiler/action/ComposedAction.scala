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

package com.github.flameframework.compiler.action

import com.github.flameframework.compiler.base.Identifier
import com.github.flameframework.compiler.domain._

import scala.collection.JavaConversions._

/**
 * Created by michel on 13-12-14.
 */
case class ComposedAction(_description: Identifier ,
                      _inputVariables: Seq[Variable] = Nil,
                      _outputType: Option[Type] = None,
                      _actionCalls: Seq[ActionCall]) extends Action(_description, _inputVariables, _outputType) {

  // getter methods returning plain Java objects for free marker
  val getActionCalls : java.util.List[ActionCall] = _actionCalls

}
