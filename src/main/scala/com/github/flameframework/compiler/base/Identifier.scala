package com.github.flameframework.compiler.base

import com.github.flameframework.util.WordSplitter._

import scala.language.implicitConversions

trait Identifier {

  def words : Seq[String]

  override def toString = words.mkString("-")

}

object Identifier {

  private case class IdentifierImpl(words: Seq[String]) extends Identifier

  implicit def apply(fullText: String): Identifier = IdentifierImpl(split(fullText))

}
