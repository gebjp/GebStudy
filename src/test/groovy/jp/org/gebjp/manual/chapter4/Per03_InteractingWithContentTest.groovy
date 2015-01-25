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
class Per03_InteractingWithContentTest extends GebReportingSpec {

  @Shared GebDebugUtil debug = new GebDebugUtil()

  /**
   * ■解説
   * 検索したコンテンツ前後のコンテンツとマッチさせることができる
   *
   * ■HTML
   * <div class="a">
   *   <div class="b">
   *     <p class="c"></p>
   *     <p class="d"></p>
   *     <p class="e"></p>
   *   </div>
   *   <div class="f"></div>
   * </div>
   *
   * ■Geb
   * $("p.d").previous() // 'p.c'
   * $("p.e").prevAll() // 'p.c' & 'p.d'
   * $("p.d").next() // 'p.e'
   * $("p.c").nextAll() // 'p.d' & 'p.e'
   * $("p.d").parent() // 'div.b'
   * $("p.c").siblings() // 'p.d' & 'p.e'
   * $("div.a").children() // 'div.b' & 'div.f'
   */
  def "4.3 Traversing"() {
    when:
    to GebTopPage

    then:
    waitFor{ at GebTopPage }

    and:
    //debug.printContents($("div" , class:contains("index")))
    $("div").filter(".index3").previous().@class == "line number3 index2 alt2"
    $("div").filter(".index3").prevAll()*.@class == [
      "line number1 index0 alt2",
      "line number2 index1 alt1",
      "line number3 index2 alt2"
    ]
    $("div").filter(".index3").next().@class == "line number5 index4 alt2"
    $("div").filter(".index12").nextAll()*.@class == ["line number14 index13 alt1", "line number15 index14 alt2"]
    $("div").filter(".index3").parent().@class == "container"
    $("div").filter(".index3").siblings()*.@class ==[
      "line number1 index0 alt2",
      "line number2 index1 alt1",
      "line number3 index2 alt2",
      "line number5 index4 alt2",
      "line number6 index5 alt1",
      "line number7 index6 alt2",
      "line number8 index7 alt1",
      "line number9 index8 alt2",
      "line number10 index9 alt1",
      "line number11 index10 alt2",
      "line number12 index11 alt1",
      "line number13 index12 alt2",
      "line number14 index13 alt1",
      "line number15 index14 alt2"
    ]
    $("div").filter(".index3").children()*.@class == ["groovy spaces" , "groovy plain" , "groovy string"]
    
    //closestは、指定したcontentから一番近くの条件に一致したcontentを取得するメソッド
    $("div").filter(".index3").closest(class:"container").size() == 1
    $("div").filter(".index3").closest(".container").size() == 1
    $("div").filter(".index3").closest("div" , class:"container").size() == 1
    
    //nextUntilは、指定したcontentから条件に合致するまでのcontentまで取得するメソッド
    $("div").filter(".index3").nextUntil(".index6")*.@class ==["line number5 index4 alt2", "line number6 index5 alt1"]

  }
}