package com.github.flameframework.compiler.action

import com.github.flameframework.compiler.base.Identifier

/**
 * General application info.
 */
case class AppInfo(_description: Identifier, _startAction: ActionCall) {

  // getter methods returning plain Java objects for free marker
  def getStartAction = _startAction

}
