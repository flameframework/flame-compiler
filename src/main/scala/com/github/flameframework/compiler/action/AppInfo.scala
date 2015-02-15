package com.github.flameframework.compiler.action

import com.github.flameframework.compiler.domain.Ref

/**
 * General application info.
 */
case class AppInfo(_description: String, _startAction: Ref[Action]) {

  // getter methods returning plain Java objects for free marker
  // TODO: this should be an action call instead of an action
  def getStartAction = _startAction.get

}
