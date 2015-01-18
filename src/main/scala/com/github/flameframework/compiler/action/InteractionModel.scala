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

import com.github.flameframework.compiler.domain.DomainClass

import scala.collection.JavaConversions._

/**
 * Created by michel on 16-11-14.
 */
class InteractionModel (domainClasses : java.util.List[DomainClass] ,
                        nativeActions: java.util.List[Action],
                        composedActions : java.util.List[ComposedAction]) {

  def getDomainClasses = domainClasses
  def getNativeActions = nativeActions
  def getComposedActions = composedActions

}

object InteractionModel {

  def apply(domainClasses: Seq[DomainClass], nativeActions : Seq[Action], composedActions: Seq[ComposedAction]) =
    new InteractionModel(domainClasses, nativeActions, composedActions)

}