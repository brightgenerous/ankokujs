//# encoding: utf-8
// src/main/scala/package.scala

package ankokujs

import scala.scalajs.js

import org.scalajs.dom.XMLHttpRequest

package object brigen {

  case class AjaxException(xhr: XMLHttpRequest) extends Exception

  type Callback[T] = js.Function1[T, Unit]

  implicit def convertCallback[T](func: T => Unit) =
    { (obj: T) => func(obj) } : Callback[T]
}

