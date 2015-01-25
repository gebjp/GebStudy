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
class Per02_InteractingWithContentTest extends GebReportingSpec {

	@Shared GebDebugUtil debug = new GebDebugUtil()

	/**
	 * ■解説
	 * “find”, ”$”は子要素を検索するための関数である。“filter”, ”not”は、
	 * 要素を減らすための関数である
	 *
	 * ■HTML
	 * <div class="a">
	 *     <p class="b">geb</p>
	 * </div>
	 * <div class="b">
	 *     <input type="text"/>
	 * </div>
	 *
	 * ■Geb
	 * $("div").find(".b").text() == "geb"
	 * $("div").$(".b").text() == "geb"
	 * $("div").filter(".b").text() == ""
	 * $(".b").not("p").text() == ""
	 * $("div").has("p").text() == "geb"
	 * $("div").has("input", type: "text").text() == ""
	 */
	def "4.2 Finding & Filtering"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		//debug.printContents($("div").find(".keyword"))
		$("div").find(".keyword" , 0).text() == "import"
		$("div").$(".keyword" , 0).text() == "import"

		//debug.printContents($("div").filter(".index0").find(".groovy"))
		$("div").filter(".index0").find(".groovy").text() == "import"
		$("div").filter(".index0").find(".groovy").not(".keyword").text() == "geb.Browser"


		//hasは、指定したコンテンツを子にもつ一番上の親コンテンツを取得する。
		//例えば、$("div").has("code" , text:"geb.Browser")だと、一番親の"<div id="content-wrap">"が取得される
		//debug.printContents($("div" , class:"line").has("code" , text:"geb.Browser"))
		$("div" , class:"line").has("code" , text:"geb.Browser").text() == "import geb.Browser"
		$("div").has("code" , text:"geb.Browser").@id == "content-wrap"
	}
}