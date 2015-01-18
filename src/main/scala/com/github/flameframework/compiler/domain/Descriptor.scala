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