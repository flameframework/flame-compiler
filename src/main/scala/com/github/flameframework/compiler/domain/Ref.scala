package com.github.flameframework.compiler.domain

/**
 * A reference to another object, that can be empty or set.
 * By replacing direct references to other objects with Ref-objects, we allow circular references.
 */
class Ref[A](var obj: Option[A] = None) {

  def get = obj.get

  override def equals(other: Any) = other.asInstanceOf[Ref[A]].obj == obj

  override def hashCode = obj.hashCode()

  override def toString = obj.toString


}
