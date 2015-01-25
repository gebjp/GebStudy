package jp.org.gebjp.manual.chapter4

import geb.spock.GebSpec
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
class Per04_InteractingWithContentTest extends GebSpec {

	@Shared GebDebugUtil debug = new GebDebugUtil()

	/**
	 * ■解説
	 * 複数のNavigatorを組み合わせることができる。
	 * PageObjectのcontentを利用することで汎用化することもできる
	 *
	 * ■HTML
	 * --
	 *
	 * ■Geb
	 * ・汎用化前
	 * $($("div.a"), $("div.d")) // ['div.a','div.d']
	 *
	 * ・汎用化後
	 * -PageObject
	 * static content = {
	 *      divElement { divClass -> $('div', 'class': divClass) }
	 * }
	 *
	 * -Geb
	 * $(divElement('a'), divElement('d'))
	 *
	 */
	def "4.4 Composition"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		//debug.printContents($($("div").filter(".index3").previous(),$("div").filter(".index3").next()))
		$($("div").filter(".index3").previous(),$("div").filter(".index3").next())*.@class ==
				["line number3 index2 alt2", "line number5 index4 alt2"]
		$($("div").filter(".index3").previous(),$("div").filter(".index3").next())*.@class ==
				["line number3 index2 alt2", "line number5 index4 alt2"]

		//debug.printContents(divElement(".index2"))
		//debug.printContents(divElement(".index4"))
		$(divElement(".index2"), divElement(".index4"))*.@class ==
				["line number3 index2 alt2", "line number5 index4 alt2"]
	}
}