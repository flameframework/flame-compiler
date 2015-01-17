package com.github.flameframework.compiler.action

import com.github.flameframework.compiler.domain.Descriptor
import scala.collection.JavaConversions._

object ViewAction extends Action(Descriptor(classOf[Action], "view"))
object ListAction extends Action(Descriptor(classOf[Action], "list"))
