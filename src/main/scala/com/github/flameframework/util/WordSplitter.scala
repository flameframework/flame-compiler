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

package com.github.flameframework.util

/**
 * Created by michel on 16-01-15.
 */
object WordSplitter {

  private val splitRegEx = Seq(
    "[^A-Za-z0-9]",             // Abc*def becomes Abc def
    "(?<=[0-9])(?=[^0-9])",     // 123abc becomes 123 abc
    "(?<=[a-z])(?=[^a-z])",     // abc123 becomes abc 123
    "(?<=[A-Z])(?=[A-Z][a-z])", // ABCDef becomes ABC Def
    "(?<=[A-Z])(?=[0-9])"       // ABC456 becomes ABC 456
  ).mkString("|")

  def split(sentence : String) = {
    sentence.split(splitRegEx).map(_.toLowerCase).filter(!_.isEmpty).toSeq
  }

}
