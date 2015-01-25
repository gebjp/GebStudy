package jp.org.gebjp.manual.chapter4

import geb.spock.GebReportingSpec
import jp.org.gebjp.page.YahooRegCstBasePage
import jp.org.gebjp.page.YahooTopPage
import jp.org.gebjp.util.GebDebugUtil

import org.openqa.selenium.Keys

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
class Per12_InteractingWithContentTest extends GebReportingSpec {

  @Shared GebDebugUtil debug = new GebDebugUtil()

  /**
   * ■解説
   * Input, selectなどform要素への入力に対して簡単に入力できるようにしている
   *
   * ■HTML
   * <form>
   *    <input type="text" name="geb" value="testing" />
   * </form>
   *
   * ■Geb
   * $("form").geb == "testing"
   * $("form").geb = "goodness"
   * $("form").geb == "goodness"
   *
   */
  def "4.12 Form Control Shortcuts"() {
    when:
    to YahooTopPage

    then:
    waitFor{ at YahooTopPage }

    when:
    $("a" , href:contains("rdsig.yahoo.co.jp") , text:"新規取得").click()

    then:
    waitFor{ at YahooRegCstBasePage }

    when:
    //4.12.1.5 text inputs and textareas
    $("form").mail = "test@example.com"
    $("form").mail << Keys.BACK_SPACE
    $("form").yid = "testtesttest"
    $("form").pw1 = "testtesttest"
    $("form").pw2 = "testtesttest"
    $("form").post_code = "1120012"
    $("form").birthday = "19800501"
    $("form").cword = "1111111"

    //4.12.1.3 checkbox
    $("form").deliver = false

    //4.12.1.4 radio
    $("form").gender = "男性"
    $("form").has_tcard = "chk_no_tcard"

    $("button" , name:"commit").click()

    then:
    waitFor{$("ul").$("li").text().startsWith("正しく入力してください")}
  }

}