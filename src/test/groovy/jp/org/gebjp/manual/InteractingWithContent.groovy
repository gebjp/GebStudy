package jp.org.gebjp.manual

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
class InteractingWithContent extends GebSpec {

	@Shared GebDebugUtil debug = new GebDebugUtil()

	def "The ＄ Function - Indexes and Ranges"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		//debug.printContents($("p"))

		//debug.printContents($("p", 0).text().startsWith("Geb is a browser automation solution."))
		$("p", 0).text().startsWith("Geb is a browser automation solution.")

		//debug.printContents($("p", 2).text().startsWith("It can be used for scripting,"))
		$("p", 2).text().startsWith("It can be used for scripting,")
		$("p", 0..1)*.text() == [
			"Geb is a browser automation solution.",
			"It brings together the power of WebDriver, the elegance of jQuery content selection, the robustness of Page Object modelling and the expressiveness of the Groovy language."
		]
	}

	def "The ＄ Function - Attribute and Text Matching"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		//debug.printLink($("a"))
		$("a").size() == 45

		//debug.printLink($("a", href: contains("www.gebish.org")))
		$("a", href: contains("www.gebish.org")).size() == 31

		//debug.printLink($("a", href: contains("www.gebish.org") ,text:""))
		$("a", href: contains("www.gebish.org") ,text:"").size() == 22
	}

	def "The ＄ Function - Using Patterns"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		//debug.printLink($("a"))
		$("a").size() == 45

		//debug.printLink($("a", href: startsWith("http://www.gebish.org")))
		$("a", href: startsWith("http://www.gebish.org")).size() == 31
		$("a", href: notStartsWith("http://www.gebish.org")).size() == 14

		//debug.printLink($("a", href: contains("manual")))
		$("a", href: contains("manual")).size() == 24
		$("a", href: notContains("manual")).size() == 21

		//debug.printLink($("a", href: endsWith("/api/")))
		$("a", href: endsWith("/api/")).size() == 11
		$("a", href: notEndsWith("/api/")).size() == 34

		//debug.printLink($("a", text: containsWord("Page")))
		//debug.printLink($("a", text: notContainsWord("Page")))
		//"PageObjects"はcontainsWordでは合致しない。
		$("a", text: containsWord("Page")).size() == 2
		$("a", text: notContainsWord("Page")).size() == 43

	}

	def "The ＄ Function - Navigators are Iterable"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		//debug.printLink($("a" ,href:contains("manual")).max { it.@href })
		$("a" ,href:contains("manual")).max { it.@href }.@href.contains("snapshot")

		//debug.printLink($("a" ,href:contains("manual")).min { it.@href })
		$("a" ,href:contains("manual")).min { it.@href }.@href.contains("0.6.2")
	}

	def "Finding & Filtering"() {
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