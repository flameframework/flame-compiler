package com.github.flameframework.compiler.parser

import org.scalatest.FlatSpec

import scala.collection.mutable
import com.github.flameframework.compiler.domain.Ref
import com.github.flameframework.util.WordSplitter._

/**
 * Created by michel on 15-02-15.
 */
class RegistryTest extends FlatSpec {

  trait Fixture {
    val emptyItem: Ref[String] = new Ref(None)
    val existingItem: Ref[String] = new Ref(Some("existing item"))
    val registry = new Registry[String](mutable.Map(
      split("existing item") -> existingItem,
      split("empty item") -> emptyItem))
  }

  "apply" should "return an existing item" in new Fixture() {
    assert(registry("existing item") == new Ref(Some("existing item")))
  }
  it should "add a nonexisting item" in new Fixture() {
    assert(registry("non existing item") == new Ref(None))
  }

  "register" should "add a non existing item" in new Fixture() {
    registry.register("new item", "new item")
    assert(registry("new item") == new Ref(Some("new item")))
  }
  it should "set an empty item" in new Fixture() {
    registry.register("empty item", "new value")
    assert(emptyItem == new Ref(Some("new value")))
  }
  it should "fail on overriding an item" in new Fixture() {
    intercept[FlameParserException] {
      registry.register("existing item", "override")
    }
    assert(existingItem == new Ref(Some("existing item")))
  }


}