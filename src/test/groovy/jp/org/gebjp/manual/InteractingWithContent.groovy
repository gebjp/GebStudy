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
class IndexesRangesTest extends GebSpec {

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
}