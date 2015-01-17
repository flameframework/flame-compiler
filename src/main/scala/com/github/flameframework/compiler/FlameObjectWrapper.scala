package com.github.flameframework.compiler

import com.github.flameframework.compiler.action._
import com.github.flameframework.compiler.domain._
import freemarker.ext.beans.BeanModel
import freemarker.template._

/**
 * Created by michel on 30-11-14.
 */
// TODO : this is just a proof of concept for a custom object wrapper
class FlameObjectWrapper(version: Version) extends DefaultObjectWrapper(version) {

  override def wrap(obj: scala.Any): TemplateModel =
    obj match {
      case (typeObj: Type) => wrap(typeObj, asString(typeObj))
      case (value: Value) => wrap(value, asString(value))
      case (descriptor: Descriptor[_]) => wrap(descriptor, asString(descriptor))
      case _ => super.wrap(obj)
    }

  private def asString(typeObj : Type) : String = typeObj match {
    // TODO: the type names should be configurable somehow
    case (IntegerType) => "NSInteger"
    case (StringType) => "NSString"
    case (domainClass: DomainClass) => s"${asString(domainClass.getName)}"
    case (listType: ListType) => "NSMutableArray"
  }

  private def asString(value: Value) : String = value match {
    case (variable: Variable) => asString(variable.getName)
    // TODO: the syntax for property reference should be configurable somehow
    case (property : PropertyValue) => s"${asString(property.getObject)}.${asString(property.getProperty.getName)}"
  }

  // TODO: the default casing for various descriptors should be configurable somehow
  private def asString(descriptor: Descriptor[_]) : String =
    if (descriptor.clazz == classOf[DomainClass]) {
      descriptor.getToUpperCamelCase
    } else if (descriptor.clazz == classOf[Variable]) {
      descriptor.getToLowerCamelCase
    } else if (descriptor.clazz == classOf[Action]) {
      descriptor.getToUpperCamelCase
    } else throw new Exception

  private def wrap(obj: Object, valueAsString: String) = new BeanModel(obj, this) with TemplateScalarModel {

    override def getAsString: String = valueAsString

  }
}
