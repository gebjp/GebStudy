package jp.org.gebjp.manual.chapter4

import geb.spock.GebReportingSpec
import jp.org.gebjp.page.YahooRegCstBasePage
import jp.org.gebjp.page.YahooTopPage
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
class Per11_InteractingWithContentTest extends GebReportingSpec {

  @Shared GebDebugUtil debug = new GebDebugUtil()

  /**
   *
   * ■解説
   * Input, select,  textarea など入力値は、valueメソッドでセットすることができる
   * checkbox は、booleanをセットすることもできる
   * multiple select は配列をセットすることもできる
   *
   */
  def "4.11 Accessing input values"() {
    when:
    to YahooTopPage

    then:
    waitFor{ at YahooTopPage }

    when:
    $("a" , href:contains("rdsig.yahoo.co.jp") , text:"新規取得").click()

    then:
    waitFor{ at YahooRegCstBasePage }

    when:
    // https://account.edit.yahoo.co.jp/registrationを参照してください
    $("input" , name:"mail").value("test@example.com")
    $("input" , name:"yid").value("testtesttest")
    $("input" , name:"pw1").value("testtesttest")
    $("input" , name:"pw2").value("testtesttest")
    $("input" , name:"post_code").value("1120012")
    $("input" , name:"gender").value("男性")
    $("input" , name:"birthday").value("19800501")
    $("input" , name:"deliver").value(false)
    $("input" , name:"cword").value("1111111")
    $("input" , name:"has_tcard").value("chk_no_tcard")
    $("button" , name:"commit").click()

    then:
    waitFor{$("ul").$("li").text().startsWith("正しく入力してください")}
  }
}