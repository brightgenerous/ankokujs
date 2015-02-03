# README #

暗黒 jsフレームワーク武術会 ぶらいじぇんチームのリポジトリです。


* JSフレームワーク - [Knockout](http://knockoutjs.com/)
* AltJS - [Scala.js](http://www.scala-js.org/)
* 外部API - [niconico Mashup Awards 10 向け提供API](http://search.nicovideo.jp/docs/api/contest.html)


----

* デモ - [Ninonico Search.](http://ec2-54-65-40-165.ap-northeast-1.compute.amazonaws.com/)

----


### 最初にやっておくこと ###

#### sbt をインストール ####

    > brew install scala
    > brew install sbt


#### npm, bower をインストール ####

    > brew install node.js
    > npm install bower -g


#### clone する ####

    > git clone https://github.com/brightgenerous/ankokujs.git ankokujs

> 以下、このディレクトリを ${WORK_DIR}/ankokujs とする


#### コンパイルなど する ####

    > cd ${WORK_DIR}/ankokujs

    > npm install
    > grunt init


----


### 実行する ###

#### ブラウザで開く ####

    > open ${WORK_DIR}/ankokujs/index.html
