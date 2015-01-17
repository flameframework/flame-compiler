package com.github.flameframework.compiler.domain

import com.github.flameframework.util.WordSplitter
import WordSplitter._

/**
 * Created by michel on 12-01-15.
 */
class Descriptor[T](val clazz: Class[T], words: Seq[String]) {

  implicit class StringExtensions(s: String) {
    def toUpperCamelCase = s.substring(0, 1).toUpperCase + s.substring(1)
  }

  def getToLowerCamelCase = words.head + words.tail.map(_.toUpperCamelCase).mkString

  def getToUpperCamelCase = words.map(_.toUpperCamelCase).mkString

  def getToUpperCase = words.map(_.toUpperCase).mkString("_")

  def getToLowerCase = words.mkString("-")

}

object Descriptor {

  def apply[T](clazz: Class[T], description: String) = new Descriptor(clazz, split(description))

}