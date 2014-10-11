// i was referring to
//   http://blog.pab-tech.net/2014/03/07/scala-js-future.html

//# encoding: utf-8
// src/main/scala/Ajax.scala

package ankokujs.brigen

import scala.util.{ Success, Failure }
import scala.concurrent.{ Promise, Future }

import scala.scalajs.js

import org.scalajs.dom.{ XMLHttpRequest, Event }

import js.annotation.JSExport

// concurrency
//   immediate
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
//   setTimeout 0
//import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue


@JSExport
object Ajax {

  type OnSuccess = Callback[XMLHttpRequest]
  type OnFailure = Callback[Throwable]

  @JSExport
  def get(url: String, success: OnSuccess, failure: OnFailure = null) =
    apply("GET", url, None, Option(success), Option(failure))

  @JSExport
  def post(url: String, data: String, success: OnSuccess, failure: OnFailure = null) =
    apply("POST", url, Option(data), Option(success), Option(failure))

  def apply(method: String, url: String, data: Option[String], success: Option[OnSuccess], failure: Option[OnFailure]) = {
    val req = new XMLHttpRequest
    val promise = Promise[XMLHttpRequest]

    //req.withCredentials = true

    req.onreadystatechange = (e: Event) => {
      if (req.readyState.toInt == 4) {
        if (200 <= req.status && req.status < 300) {
          promise.success(req)
        } else {
          promise.failure(AjaxException(req))
        }
      }
    }

    req.open(method, url, true)

    data match {
      case Some(d) => req.send(d)
      case None => req.send()
    }

    promise.future onComplete {
      case Success(e) => success.map(_(e))
      case Failure(e) => failure.map(_(e))
    }
  }
}

