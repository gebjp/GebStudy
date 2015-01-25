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
class Per07_InteractingWithContentTest extends GebReportingSpec {

	@Shared GebDebugUtil debug = new GebDebugUtil()

	/**
	 * ■解説
	 * ・PageのSizeとLocationを取得できる
	 * ・LocationはPageの左上からのx , yプロパティでpixel指定する
	 *
	 * ■HTML
	 * $("div").height == 20
	 * $("div").width == 40
	 * $("div").x == 60
	 * $("div").y == 80
	 *
	 * ■Geb
	 * $("div")*.height == [20, 30]
	 * $("div")*.width == [40, 50]
	 * $("div")*.x == [60, 70]
	 * $("div")*.y == [80, 90]
	 */
	def "4.7 Size and Location"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }
		debug.printLocationAndSize($("div"))
	}
}