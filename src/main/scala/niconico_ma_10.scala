//# encoding: utf-8
// src/main/scala/NiconicoMa10.scala

package ankokujs.brigen

import scala.scalajs.js

import org.scalajs.dom.XMLHttpRequest

import js.annotation.JSExport


@JSExport
object NiconicoMA10 {

  type Dictionary   = js.Dictionary[AnyRef]
  type JSArray      = js.Array[Dictionary]
  type ContentParam = Dictionary
  type TagParam     = Dictionary
  type OnSuccess    = Callback[JSArray]
  type OnInvalid    = Callback[Dictionary]
  type OnFailure    = Callback[Throwable]

  def dictionary = js.Dictionary[AnyRef]()
  def array = js.Array[Dictionary]()

  @JSExport
  def contents(params: ContentParam, success: OnSuccess, invalid: OnInvalid = null, failure: OnFailure = null) =
    ajax("http://api.search.nicovideo.jp/api/", params, success, invalid, failure)

  @JSExport
  def tags(params: TagParam, success: OnSuccess, invalid: OnInvalid = null, failure: OnFailure = null) =
    ajax("http://api.search.nicovideo.jp/api/tag/", params, success, invalid, failure)

  def ajax(url: String, params: ContentParam, success: OnSuccess, invalid: OnInvalid, failure: OnFailure) = {
    Ajax.post(url,
      js.JSON.stringify(params),
      (obj: XMLHttpRequest) => {
        val ary = obj.responseText.split("\n").collect {
          case line if !line.trim.isEmpty =>
            js.Dynamic.global.JSON.parse(line)
              .asInstanceOf[Dictionary]
        }.foldLeft(array)((z, n) => z :+ n)
        Option(ary).filterNot(_.isEmpty).map(_(0)).collect {
          case dic if !dic("errid").isInstanceOf[scala.runtime.BoxedUnit] =>
            dic
        } match {
          case None => success(ary)
          case Some(dic) => Option(invalid).map(_(dic))
        }
      }: Unit, failure)
  }
}

