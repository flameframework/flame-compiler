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
case class InteractionModel (_appInfo: AppInfo,
                        _domainClasses : Seq[DomainClass] ,
                        _nativeActions: Seq[NativeAction],
                        _composedActions : Seq[ComposedAction]) {

  // getter methods returning plain Java objects for free marker
  def getAppInfo : AppInfo = _appInfo
  def getDomainClasses : java.util.List[DomainClass] = _domainClasses
  def getNativeActions : java.util.List[NativeAction] = _nativeActions
  def getComposedActions : java.util.List[ComposedAction] = _composedActions

}
