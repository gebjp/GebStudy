package jp.org.gebjp.manual

import geb.spock.GebSpec
import jp.org.gebjp.page.GebApiPage
import jp.org.gebjp.page.GebTopPage
import jp.org.gebjp.util.GebDebugUtil

import org.openqa.selenium.ElementNotVisibleException

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

	//	def "4.1.2 The ＄ Function - Indexes and Ranges"() {
	//		when:
	//		to GebTopPage
	//
	//		then:
	//		waitFor{ at GebTopPage }
	//
	//		and:
	//		//debug.printContents($("p"))
	//
	//		//debug.printContents($("p", 0))
	//		$("p", 0).text().startsWith("Geb is a browser automation solution.")
	//
	//		//debug.printContents($("p", 2))
	//		$("p", 2).text().startsWith("It can be used for scripting,")
	//		$("p", 0..1)*.text() == ["Geb is a browser automation solution.", "It brings together the power of WebDriver, the elegance of jQuery content selection, the robustness of Page Object modelling and the expressiveness of the Groovy language."]
	//	}
	//
	//	def "4.1.3 The ＄ Function - Attribute and Text Matching"() {
	//		when:
	//		to GebTopPage
	//
	//		then:
	//		waitFor{ at GebTopPage }
	//
	//		and:
	//		//debug.printContents($("a"))
	//		$("a").size() == 45
	//
	//		//debug.printContents($("a", href: contains("www.gebish.org")))
	//		$("a", href: contains("www.gebish.org")).size() == 31
	//
	//		//debug.printContents($("a", href: contains("www.gebish.org") ,text:""))
	//		$("a", href: contains("www.gebish.org") ,text:"").size() == 22
	//	}
	//
	//	def "4.1.3.1 The ＄ Function - Using Patterns"() {
	//		when:
	//		to GebTopPage
	//
	//		then:
	//		waitFor{ at GebTopPage }
	//
	//		and:
	//		//debug.printContents($("a"))
	//		$("a").size() == 45
	//
	//		//debug.printContents($("a", href: startsWith("http://www.gebish.org")))
	//		$("a", href: startsWith("http://www.gebish.org")).size() == 31
	//		$("a", href: notStartsWith("http://www.gebish.org")).size() == 14
	//
	//		//debug.printContents($("a", href: contains("manual")))
	//		$("a", href: contains("manual")).size() == 24
	//		$("a", href: notContains("manual")).size() == 21
	//
	//		//debug.printContents($("a", href: endsWith("/api/")))
	//		$("a", href: endsWith("/api/")).size() == 11
	//		$("a", href: notEndsWith("/api/")).size() == 34
	//
	//		//debug.printContents($("a", text: containsWord("Page")))
	//		//debug.printContents($("a", text: notContainsWord("Page")))
	//		//"PageObjects"はcontainsWordでは合致しない。
	//		$("a", text: containsWord("Page")).size() == 2
	//		$("a", text: notContainsWord("Page")).size() == 43
	//
	//	}
	//
	//	def "4.1.4 The ＄ Function - Navigators are Iterable"() {
	//		when:
	//		to GebTopPage
	//
	//		then:
	//		waitFor{ at GebTopPage }
	//
	//		and:
	//		//debug.printContents($("a" ,href:contains("manual")).max { it.@href })
	//		$("a" ,href:contains("manual")).max { it.@href }.@href.contains("snapshot")
	//
	//		//debug.printContents($("a" ,href:contains("manual")).min { it.@href })
	//		$("a" ,href:contains("manual")).min { it.@href }.@href.contains("0.6.2")
	//	}
	//
	//	def "4.2 Finding & Filtering"() {
	//		when:
	//		to GebTopPage
	//
	//		then:
	//		waitFor{ at GebTopPage }
	//
	//		and:
	//		//debug.printContents($("div").find(".keyword"))
	//		$("div").find(".keyword" , 0).text() == "import"
	//		$("div").$(".keyword" , 0).text() == "import"
	//
	//		//debug.printContents($("div").filter(".index0").find(".groovy"))
	//		$("div").filter(".index0").find(".groovy").text() == "import"
	//		$("div").filter(".index0").find(".groovy").not(".keyword").text() == "geb.Browser"
	//
	//
	//		//hasは、指定したコンテンツを子にもつ一番上の親コンテンツを取得する。
	//		//例えば、$("div").has("code" , text:"geb.Browser")だと、一番親の"<div id="content-wrap">"が取得される
	//		//debug.printContents($("div" , class:"line").has("code" , text:"geb.Browser"))
	//		$("div" , class:"line").has("code" , text:"geb.Browser").text() == "import geb.Browser"
	//		$("div").has("code" , text:"geb.Browser").@id == "content-wrap"
	//	}
	//	def "4.3 Traversing"() {
	//		when:
	//		to GebTopPage
	//
	//		then:
	//		waitFor{ at GebTopPage }
	//
	//		and:
	//		//debug.printContents($("div" , class:contains("index")))
	//		$("div").filter(".index3").previous().@class == "line number3 index2 alt2"
	//		$("div").filter(".index3").prevAll()*.@class == ["line number1 index0 alt2", "line number2 index1 alt1", "line number3 index2 alt2"]
	//		$("div").filter(".index3").next().@class == "line number5 index4 alt2"
	//		$("div").filter(".index12").nextAll()*.@class == ["line number14 index13 alt1", "line number15 index14 alt2"]
	//		$("div").filter(".index3").parent().@class == "container"
	//		$("div").filter(".index3").siblings()*.@class ==[
	//			"line number1 index0 alt2",
	//			"line number2 index1 alt1",
	//			"line number3 index2 alt2",
	//			"line number5 index4 alt2",
	//			"line number6 index5 alt1",
	//			"line number7 index6 alt2",
	//			"line number8 index7 alt1",
	//			"line number9 index8 alt2",
	//			"line number10 index9 alt1",
	//			"line number11 index10 alt2",
	//			"line number12 index11 alt1",
	//			"line number13 index12 alt2",
	//			"line number14 index13 alt1",
	//			"line number15 index14 alt2"
	//		]
	//		$("div").filter(".index3").children()*.@class == ["groovy spaces" , "groovy plain" , "groovy string"]
	//		$("div").filter(".index3").closest(class:"container").size() == 1
	//		$("div").filter(".index3").closest(".container").size() == 1
	//		$("div").filter(".index3").closest("div" , class:"container").size() == 1
	//		$("div").filter(".index3").nextUntil(".index6")*.@class ==["line number5 index4 alt2", "line number6 index5 alt1"]
	//
	//	}
	//
	//	def "4.4 Composition"() {
	//		when:
	//		to GebTopPage
	//
	//		then:
	//		waitFor{ at GebTopPage }
	//
	//		and:
	//		//debug.printContents($($("div").filter(".index3").previous(),$("div").filter(".index3").next()))
	//		$($("div").filter(".index3").previous(),$("div").filter(".index3").next())*.@class ==
	//				["line number3 index2 alt2", "line number5 index4 alt2"]
	//		$($("div").filter(".index3").previous(),$("div").filter(".index3").next())*.@class ==
	//				["line number3 index2 alt2", "line number5 index4 alt2"]
	//
	//		//debug.printContents(divElement(".index2"))
	//		//debug.printContents(divElement(".index4"))
	//		$(divElement(".index2"), divElement(".index4"))*.@class ==
	//				["line number3 index2 alt2", "line number5 index4 alt2"]
	//	}
	//
	//	def "4.5 Clicking"() {
	//		when:
	//		to GebTopPage
	//
	//		then:
	//		waitFor{ at GebTopPage }
	//
	//		when:
	//		Actions action = new Actions(driver);
	//		action.moveToElement(apiMenu.firstElement()).perform();
	//		waitFor{apiLink("current").isDisplayed() == true}
	//		apiLink("current").click(GebApiPage)
	//
	//		then:
	//		waitFor{ at GebApiPage }
	//	}

	def "4.6 Determining Visibility"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		when:
		//debug.printContents(apiLink("current"))
		apiLink("current").click(GebApiPage)

		then:
		thrown(ElementNotVisibleException)
	}

	def "4.7 Size and Location"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }
		debug.printLocationAndSize($("div"))
	}

	def "4.8 Accessing tag name, attributes, text and classes"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }
		//debug.printContents($("div"))

		and:
		$("div").filter(".index0").classes() == ["alt2", "index0" , "line", "number1"]

	}

	def "4.9 Css properties"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		println $("body").css("font")

	}



}