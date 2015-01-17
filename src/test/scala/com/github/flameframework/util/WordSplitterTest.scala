package com.github.flameframework.util

import com.github.flameframework.util.WordSplitter._
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

/**
 * Created by michel on 16-01-15.
 */
@RunWith(classOf[JUnitRunner])
class WordSplitterTest extends FlatSpec {

  "Words separated by spaces" should "be split into separate words" in {
    assert(split("three separate words") == Seq("three", "separate", "words"))
  }

  "Words separated by dashes" should "be split into separate words" in {
    assert(split("words-separated-by-dashes") == Seq("words", "separated", "by", "dashes"))
  }

  "Words separated by underlines" should "be split into separate words" in {
    assert(split("words_separated_by_underlines") == Seq("words", "separated", "by", "underlines"))
  }

  "A camel cased sentence" should "be split into separate lowercased words" in {
    assert(split("fourCamelCasedWords") == Seq("four", "camel", "cased", "words"))
  }

  "A Title Cased Sentence" should "be converted to lowercase words" in {
    assert(split("A Title Cased Sentence") == Seq("a", "title", "cased", "sentence"))
  }

  "A camel cased sentence with abbreviations" should "not split the abbreviations" in {
    assert(split("AnXMLAndHTMLParser") == Seq("an", "xml", "and", "html", "parser"))
  }

  "A sentence with numbers" should "not split the numbers" in {
    assert(split("12plus34equals46") == Seq("12", "plus", "34", "equals", "46"))
  }


}
