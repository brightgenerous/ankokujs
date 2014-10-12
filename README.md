# README #

暗黒 jsフレームワーク武術会 ぶらいじぇんチームのリポジトリです。


* JSフレームワーク - [Knockout](http://knockoutjs.com/)
* AltJS - [Scala.js](http://www.scala-js.org/)


----


### 最初にやっておくこと ###

#### sbt をインストールしておく ####

    > brew install scala
    > brew install sbt


#### npm, bower をインストールしておく ####

    > brew install node.js
    > npm install bower -g


#### clone する ####

    > git clone https://github.com/brightgenerous/ankokujs.git ankokujs

> 以下、このディレクトリを ${WORK_DIR}/ankokujs とする


#### コンパイルなど する ####

    > cd ${WORK_DIR}/ankokujs

    > sbt fastOptJS

    > bower install knockout
    > bower install bootstrap
    > bower install masonry


----


### 実行する ###

#### ブラウザで開く ####

    > open ${WORK_DIR}/index.html
