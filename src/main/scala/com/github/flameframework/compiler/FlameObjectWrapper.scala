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

package com.github.flameframework.compiler

import com.github.flameframework.compiler.action._
import com.github.flameframework.compiler.base.Descriptor
import com.github.flameframework.compiler.domain._
import freemarker.ext.beans.BeanModel
import freemarker.template._

import scala.reflect.runtime.universe.{Type => _, _}

/**
 * Created by michel on 30-11-14.
 */
class FlameObjectWrapper(interactionModel: InteractionModel, version: Version) extends DefaultObjectWrapper(version) {

  override def wrap(obj: scala.Any): TemplateModel =
    obj match {
      case (actionCall: ActionCall) => wrap(actionCall, "action" -> super.wrap(interactionModel.get(actionCall._description)))
      case (typeObj: Type) => wrap(typeObj, asString(typeObj))
      case (value: Value) => wrap(value, asString(value))
      case (descriptor: Descriptor[_]) => wrap(descriptor, asString(descriptor))
      case _ => super.wrap(obj)
    }

  private def asString(typeObj : Type) : String = typeObj match {
    // TODO: the type names should be configurable somehow
    case (IntegerType) => "NSInteger*"
    case (StringType) => "NSString*"
    case (ObjectType) => "NSObject*"
    case (VoidType) => "void"
    case (domainClass: DomainClassType) => s"${asString(domainClass.getName)}*"
    case (listType: ListType) => "NSMutableArray*"
  }

  private def asString(value: Value) : String = value match {
    case (variable: Variable) => asString(variable.getName)
    // TODO: the syntax for property reference should be configurable somehow
    case (property : PropertyValue) => s"${asString(property.getObject)}.${asString(property.getProperty.getName)}"
  }

  // TODO: the default casing for various descriptors should be configurable somehow
  private def asString(descriptor: Descriptor[_]) : String =
    if (descriptor.clazz == classOf[DomainClass] || descriptor.clazz == classOf[DomainClassType]) {
      descriptor.getToUpperCamelCase
    } else if (descriptor.clazz == classOf[Variable]) {
      descriptor.getToLowerCamelCase
    } else if (descriptor.clazz == classOf[Action] || descriptor.clazz == classOf[ActionCall]) {
      descriptor.getToUpperCamelCase
    } else throw new Exception(s"Descriptor for class ${descriptor.clazz} not defined")

  private def wrap(obj: Object, valueAsString: String) = new BeanModel(obj, this) with TemplateScalarModel {

    override def getAsString: String = valueAsString

  }

  private def wrap(obj: Object, extensions : (String, TemplateModel)*) = new BeanModel(obj, this) {

    private val extensionMap = extensions.toMap

    override def get(key: String) : TemplateModel = extensionMap.get(key).getOrElse(super.get(key))

  }



}
