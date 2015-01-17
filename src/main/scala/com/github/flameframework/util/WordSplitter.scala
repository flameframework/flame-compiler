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
