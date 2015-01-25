package jp.org.gebjp.manual.chapter4

import geb.spock.GebReportingSpec
import jp.org.gebjp.page.YahooRegCstBasePage
import jp.org.gebjp.page.YahooTopPage
import jp.org.gebjp.util.GebDebugUtil

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

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
class Per13_InteractingWithContentTest extends GebReportingSpec {

  @Shared GebDebugUtil debug = new GebDebugUtil()

  /**
   * ■解説
   * WebDriver driverのActionクラスを使うことで複雑な動作も実行することができる
   *
   * ■HTML
   * -
   *
   * ■Geb
   * def actions = new Actions(driver)
   *
   * WebElement someItem = $('li.clicky').firstElement()
   * def shiftDoubleClickAction = actions.keyDown(Keys.SHIFT).doubleClick(someItem).keyUp(Keys.SHIFT).build()
   *
   * shiftDoubleClickAction.perform()
   */
  def "4.13.2 Using Actions"() {
    when:
    to YahooTopPage

    then:
    waitFor{ at YahooTopPage }

    when:
    WebElement regCstLink = $("a" , href:contains("rdsig.yahoo.co.jp") , text:"新規取得").firstElement()
    def actions = new Actions(driver)
    def newTabAction = actions.keyDown(Keys.CONTROL).click(regCstLink).keyUp(Keys.CONTROL).build()

    //Ctrl + click()は、リンク先を新規タブで起動する操作
    //その操作をperform()で実行している
    newTabAction.perform()
    newTabAction.perform()
    regCstLink.click()

    then:
    waitFor{ at YahooRegCstBasePage }
  }

  /**
   * ■解説
   * Interact Closuresを使うと、Actionよりも簡単に実装できる
   *
   * ■HTML
   * -
   *
   * ■Geb
   * interact {
   *    keyDown(Keys.SHIFT)
   *    doubleClick($('li.clicky'))
   *    keyUp(Keys.SHIFT)
   * }
   * @return
   */
  def "4.13.3 Using Interact Closures"() {
    when:
    to YahooTopPage

    then:
    waitFor{ at YahooTopPage }

    when:
    WebElement regCstLink = $("a" , href:contains("rdsig.yahoo.co.jp") , text:"新規取得").firstElement()
    //Ctrl + click()は、リンク先を新規タブで起動する操作
    //その操作をinteractで実行している
    interact {
      keyDown(Keys.CONTROL)
      click(regCstLink)
      keyUp(Keys.CONTROL)
    }
    interact {
      keyDown(Keys.CONTROL)
      click(regCstLink)
      keyUp(Keys.CONTROL)
    }
    regCstLink.click()

    then:
    waitFor{ at YahooRegCstBasePage }
  }
}