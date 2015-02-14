package com.github.flameframework.compiler.action

/**
 * General application info.
 */
case class AppInfo(_startAction: Action) {

  // getter methods returning plain Java objects for free marker
  def getStartAction = _startAction

}
