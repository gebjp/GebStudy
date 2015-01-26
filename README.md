# GebStudy
日本Geb研究会が公開するGeb学習用ソースです。Gebの勉強に活用してください

#実行環境 (maven)
<dl>
  <dt>Eclipse Kepler: 4.3.2</dt>
  <dd>https://eclipse.org/kepler/</dd>
  <dt>Groovy-Eclipse plugin: 2.9.1</dt>
  <dd>http://dist.springsource.org/release/GRECLIPSE/e4.3/</dd>
  <dt>geb-spock: 0.10.0</dt>
  <dd>http://mvnrepository.com/artifact/org.gebish/geb-spock/0.10.0</dd>
  <dt>groovy: 2.3.7</dt>
  <dd>http://mvnrepository.com/artifact/org.codehaus.groovy/groovy-all/2.3.7</dd>
</dl>

#実行環境 (gradle)

| Tool | version |
|:-----|--------:|
| gradle | 2.2 |
| groovy | 2.3.7 |
| geb-spock | 0.10.0 |

## 各IDE用設定

gradle未インストールの方は、`gradlew`コマンドを使ってください。

eclipse用プロジェクトを生成する時のコマンド

```
./gradlew eclipse
```

IntelliJ IDEA用プロジェクトを生成する時のコマンド

```
./gradlew idea
```



#ソース構成
* このGeb学習用ソースは、Geb公式サイトのマニュアルを補足するものになっています。是非、下記、URL公式マニュアルと一緒にお使いください
 * http://www.gebish.org/manual/current/
* マニュアル目次の章ごとにディレクトリが作成されています
* 例えば、"4.Interacting with content"	は、"GebStudy/src/test/groovy/jp/org/gebjp/manual/chapter4"にテストコードがあります

1. （未）Introduction
1. （未）The Browser
1. （未）The WebDriver Implementation
1. Interacting with content
  * GebStudy/src/test/groovy/jp/org/gebjp/manual/chapter4
  * Per01_InteractingWithContentTest.groovy
  * Per02_InteractingWithContentTest.groovy
  * Per03_InteractingWithContentTest.groovy
  * Per04_InteractingWithContentTest.groovy
  * Per05_InteractingWithContentTest.groovy
  * Per06_InteractingWithContentTest.groovy
  * Per07_InteractingWithContentTest.groovy
  * Per08_InteractingWithContentTest.groovy
  * Per09_InteractingWithContentTest.groovy
  * Per10_InteractingWithContentTest.groovy
  * Per11_InteractingWithContentTest.groovy
  * Per12_InteractingWithContentTest.groovy
  * Per13_InteractingWithContentTest.groovy
1. （未）Pages
1. （未）Modules
1. （未）Configuration
1. （未）Implicit Assertions
1. （未）Javascript, AJAX and Dynamic Pages
1. （未）Direct Downloading
1. （未）Scripts and Binding
1. （未）Reporting
