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
class Per09_InteractingWithContentTest extends GebReportingSpec {

  @Shared GebDebugUtil debug = new GebDebugUtil()

  /**
   * ■解説
   * cssメソッドでCSSプロパティを取得することができる
   *
   * ■HTML
   * <div style="float: left">text</div>
   *
   * ■Geb
   * $("div").css("float") == "left"
   *
   */
  def "4.9 Css properties"() {
    when:
    to GebTopPage

    then:
    waitFor{ at GebTopPage }

    and:
    //debug.printContents($("span" , class:"orange"))
    $("span" , class:"orange").css("font-style") == "italic"
  }
}