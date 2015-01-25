package jp.org.gebjp.manual.chapter4

import geb.spock.GebReportingSpec
import jp.org.gebjp.page.GebTopPage
import jp.org.gebjp.util.GebDebugUtil
import spock.lang.Shared

/**
 * ■公式マニュアル
 * ・4 Interacting with content
 * http://www.gebish.org/manual/current/navigator.html#interacting_with_content
 *
 * ■概要
 * 4 Interacting with contentの内容に対応したサンプルです
 *
 * @author YukiFujisawa
 *
 */
class Per01_InteractingWithContentTest extends GebReportingSpec {

  @Shared GebDebugUtil debug = new GebDebugUtil()

  /**
   * ■解説
   * コンテンツを単一取得ができるし、配列で複数取得することもできる
   *
   * ■HTML
   * <p>a</p>
   * <p>b</p>
   * <p>c</p>
   *
   * ■Geb
   * $("p", 0).text() == "a"
   * $("p", 2).text() == "c"
   * $("p", 0..1)*.text() = ["a", "b"]
   * $("p", 1..2)*.text() = ["b", "c"]
   */
  def "4.1.2 Indexes and Ranges"() {
    when:
    to GebTopPage

    then:
    waitFor{ at GebTopPage }

    and:
    debug.printContents($("p"))
    //debug.printContents($("p", 0))
    $("p", 0).text().startsWith("Geb is a browser automation solution.")

    //debug.printContents($("p", 2))
    $("p", 2).text().startsWith("It can be used for scripting,")
    $("p", 0..1)*.text() == [
      "Geb is a browser automation solution.",
      "It brings together the power of WebDriver, the elegance of jQuery content selection, the robustness of Page Object modelling and the expressiveness of the Groovy language."
    ]
  }

  /**
   * ■解説
   * HTMLのAttributeとTextにマッチさせることができる
   *
   * ■HTML
   * <p attr1="a" attr2="b">p1</p>
   * <p attr1="a" attr2="c">p2</p>
   *
   * ■Geb
   * $("p", attr1: "a").size() == 2
   * $("p", attr2: "c").size() == 1
   * $("p", attr1: "a", attr2: "b").size() == 1
   * $("p", text: "p1").size() == 1
   * $("p", text: "p1", attr1: "a").size() == 1
   *
   */
  def "4.1.3 Attribute and Text Matching"() {
    when:
    to GebTopPage

    then:
    waitFor{ at GebTopPage }

    and:
    //debug.printContents($("a"))
    $("a").size() == 45

    //debug.printContents($("a", href: contains("www.gebish.org")))
    $("a", href: contains("www.gebish.org")).size() == 31

    //debug.printContents($("a", href: contains("www.gebish.org") ,text:""))
    $("a", href: contains("www.gebish.org") ,text:"").size() == 22
  }

  /**
   * ■解説
   * パターンマッチも可能
   *
   * startsWith 		| 指定した文字列で開始する値とマッチする
   * contains			| 指定した文字列を含む値とマッチする
   * endsWith			| 指定した文字列で終了する値とマッチする
   * containsWord		| 指定した単語（前後に半角スペースを含む文字列）を含む値とマッチする
   * notStartsWith	| 指定した文字列で開始しない値とマッチする
   * notContains		| 指定した文字列を含まない値とマッチする
   * notEndsWith		| 指定した文字列で終了しない値とマッチする
   * notContainsWord	| 指定した単語（前後に半角スペースを含む文字列）を含まない値とマッチする
   *
   * ■HTML
   * <p attr1="a" attr2="b">p1</p>
   * <p attr1="a" attr2="c">p2</p>
   *
   * ■Geb
   * $("p", text: ~/p./).size() == 2
   * $("p", text: startsWith("p")).size() == 2
   * $("p", text: endsWith("2")).size() == 1
   */
  def "4.1.3.1 Using Patterns"() {
    when:
    to GebTopPage

    then:
    waitFor{ at GebTopPage }

    and:
    //debug.printContents($("a"))
    $("a").size() == 45

    //debug.printContents($("a", href: startsWith("http://www.gebish.org")))
    $("a", href: startsWith("http://www.gebish.org")).size() == 31
    $("a", href: notStartsWith("http://www.gebish.org")).size() == 14

    //debug.printContents($("a", href: contains("manual")))
    $("a", href: contains("manual")).size() == 24
    $("a", href: notContains("manual")).size() == 21

    //debug.printContents($("a", href: endsWith("/api/")))
    $("a", href: endsWith("/api/")).size() == 11
    $("a", href: notEndsWith("/api/")).size() == 34

    //debug.printContents($("a", text: containsWord("Page")))
    //debug.printContents($("a", text: notContainsWord("Page")))
    //"PageObjects"はcontainsWordでは合致しない。
    $("a", text: containsWord("Page")).size() == 2
    $("a", text: notContainsWord("Page")).size() == 43

  }

  /**
   * ■解説
   * Navigatorオブジェクトは、Iterableインタフェースを実装しているのでmax(),min()のような
   * Groovyの文法を使うこともできる
   *
   * ■HTML
   * <p>1</p>
   * <p>2</p>
   *
   * ■Geb
   * $("p").max { it.text() }.text() == "2"
   * $("p")*.text().max() == "2"
   */
  def "4.1.4 Navigators are Iterable"() {
    when:
    to GebTopPage

    then:
    waitFor{ at GebTopPage }

    and:
    //debug.printContents($("a" ,href:contains("manual")).max { it.@href })
    $("a" ,href:contains("manual")).max { it.@href }.@href.contains("snapshot")

    //debug.printContents($("a" ,href:contains("manual")).min { it.@href })
    $("a" ,href:contains("manual")).min { it.@href }.@href.contains("0.6.2")
  }
}