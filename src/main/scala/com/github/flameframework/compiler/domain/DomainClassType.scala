package com.github.flameframework.compiler.domain

import com.github.flameframework.compiler.base.{Descriptor, Identifier}

case class DomainClassType(identifier: Identifier) extends Type {

  val getName = new Descriptor[DomainClassType](identifier)

}
