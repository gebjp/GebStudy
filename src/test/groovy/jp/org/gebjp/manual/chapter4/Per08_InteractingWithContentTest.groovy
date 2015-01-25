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
class Per08_InteractingWithContentTest extends GebReportingSpec {

  @Shared GebDebugUtil debug = new GebDebugUtil()

  /**
   * ■解説
   * ・Navigator objectsのtag(), text(), @attribute and classes()を使って値を取得できる
   * ・classes()は、class属性を java.util.List形式で返す
   *
   * ■HTML
   * <p title="a" class="a para">a</p>
   * <p title="b" class="b para">b</p>
   * <p title="c" class="c para">c</p>
   *
   * ■Geb
   * $("p").text() == "a"
   * $("p").tag() == "p"
   * $("p").@title == "a"
   * $("p").classes() == ["a", "para"]
   */
  def "4.8 Accessing tag name, attributes, text and classes"() {
    when:
    to GebTopPage

    then:
    waitFor{ at GebTopPage }
    //debug.printContents($("div"))

    and:
    $("div").filter(".index0").classes() == ["alt2", "index0" , "line", "number1"]

  }
}