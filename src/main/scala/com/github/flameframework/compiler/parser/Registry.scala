package com.github.flameframework.compiler.parser

import com.github.flameframework.compiler.domain.Ref
import com.github.flameframework.util.WordSplitter._

import scala.collection.mutable

/**
 * A registry for objects of a certain type.
 * Objects can be found in the registry by description.
 * If an object is searched that was not yet registered, an empty ref is added in the registry.
 * If an object is registered for which an empty ref was already added, the object in the empty ref will be set to the
 * registered object.
 */
class Registry[A](val objects: mutable.Map[Seq[String], Ref[A]] = mutable.Map[Seq[String], Ref[A]]()) {

  /**
   * Finds the object with the given description in the registry. If no object with the description was added yet, a
   * new empty ref will be added.
   */
  def apply(description: String) = objects.getOrElseUpdate(split(description), new Ref[A])

  /**
   * Registered the object in the registry. If an empty ref was already registered for the description, the object in
   * the empty ref will be set to the registered object. If another object was already registered for the same
   * description, an exception will be thrown.
   */
  def register(description: String, value: A) = {
    val ref = this(description)
    if (ref.obj == None) {
      ref.obj = Some(value)
    } else {
      throw new FlameParserException(s"A value for $description was provided more than once")
    }
  }

  /**
   * Returns a Seq of all registered objects of the given type.
   */
  def filterType[B](implicit manifest: Manifest[B]) = {
    objects.values.map(_.get).filter(manifest.runtimeClass.isInstance).map(_.asInstanceOf[B]).toSeq
  }

}

object Registry {

  private def mapItem[T](kv : (String, T)) : (Seq[String], Ref[T]) = kv match { case (k, v) => split(k) -> new Ref(Some(v)) }

  def apply[T](definedObjects : (String, T)*) : Registry[T] = new Registry[T](mutable.Map[Seq[String], Ref[T]](definedObjects.map(mapItem) : _*))

}