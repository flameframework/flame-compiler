package com.github.flameframework.compiler.action

/**
 * General application info.
 */
class AppInfo(startAction: Action) {

  def getStartAction = startAction

}

object AppInfo {

  def apply(startAction: Action) = new AppInfo(startAction)

}