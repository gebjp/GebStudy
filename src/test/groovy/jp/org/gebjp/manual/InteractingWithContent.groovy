package jp.org.gebjp.manual

import geb.spock.GebSpec
import jp.org.gebjp.page.GebApiPage
import jp.org.gebjp.page.GebTopPage
import jp.org.gebjp.util.GebDebugUtil

import org.openqa.selenium.ElementNotVisibleException
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
class InteractingWithContent extends GebSpec {

	@Shared GebDebugUtil debug = new GebDebugUtil()

	/**
	 * ■解説
	 * コンテンツを単一取得ができるし、配列で複数取得することもできる
	 * 
	 * ■HTML
	 * <p>a</p>
	 * <p>b</p>
	 * <p>c</p>
	 * 
	 * ■Geb
	 * $("p", 0).text() == "a"
	 * $("p", 2).text() == "c"
	 * $("p", 0..1)*.text() = ["a", "b"]
	 * $("p", 1..2)*.text() = ["b", "c"]
	 */
	def "4.1.2 The ＄ Function - Indexes and Ranges"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		//debug.printContents($("p"))

		//debug.printContents($("p", 0))
		$("p", 0).text().startsWith("Geb is a browser automation solution.")

		//debug.printContents($("p", 2))
		$("p", 2).text().startsWith("It can be used for scripting,")
		$("p", 0..1)*.text() == ["Geb is a browser automation solution.", "It brings together the power of WebDriver, the elegance of jQuery content selection, the robustness of Page Object modelling and the expressiveness of the Groovy language."]
	}

	/**
	 * ■解説
	 * HTMLのAttributeとTextにマッチさせることができる
	 *
	 * ■HTML
	 * <p attr1="a" attr2="b">p1</p>
	 * <p attr1="a" attr2="c">p2</p>
	 *
	 * ■Geb
	 * $("p", attr1: "a").size() == 2
	 * $("p", attr2: "c").size() == 1
	 * $("p", attr1: "a", attr2: "b").size() == 1
	 * $("p", text: "p1").size() == 1
	 * $("p", text: "p1", attr1: "a").size() == 1
	 *
	 */
	def "4.1.3 The ＄ Function - Attribute and Text Matching"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		//debug.printContents($("a"))
		$("a").size() == 45

		//debug.printContents($("a", href: contains("www.gebish.org")))
		$("a", href: contains("www.gebish.org")).size() == 31

		//debug.printContents($("a", href: contains("www.gebish.org") ,text:""))
		$("a", href: contains("www.gebish.org") ,text:"").size() == 22
	}

	/**
	 * ■解説
	 * パターンマッチも可能
	 * 
	 * startsWith 		| 指定した文字列で開始する値とマッチする
	 * contains			| 指定した文字列を含む値とマッチする
	 * endsWith			| 指定した文字列で終了する値とマッチする
	 * containsWord		| 指定した単語（前後に半角スペースを含む文字列）を含む値とマッチする
	 * notStartsWith	| 指定した文字列で開始しない値とマッチする
	 * notContains		| 指定した文字列を含まない値とマッチする
	 * notEndsWith		| 指定した文字列で終了しない値とマッチする
	 * notContainsWord	| 指定した単語（前後に半角スペースを含む文字列）を含まない値とマッチする
	 *
	 * ■HTML
	 * <p attr1="a" attr2="b">p1</p>
	 * <p attr1="a" attr2="c">p2</p>
	 *
	 * ■Geb
	 * $("p", text: ~/p./).size() == 2
	 * $("p", text: startsWith("p")).size() == 2
	 * $("p", text: endsWith("2")).size() == 1
	 */
	def "4.1.3.1 The ＄ Function - Using Patterns"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		//debug.printContents($("a"))
		$("a").size() == 45

		//debug.printContents($("a", href: startsWith("http://www.gebish.org")))
		$("a", href: startsWith("http://www.gebish.org")).size() == 31
		$("a", href: notStartsWith("http://www.gebish.org")).size() == 14

		//debug.printContents($("a", href: contains("manual")))
		$("a", href: contains("manual")).size() == 24
		$("a", href: notContains("manual")).size() == 21

		//debug.printContents($("a", href: endsWith("/api/")))
		$("a", href: endsWith("/api/")).size() == 11
		$("a", href: notEndsWith("/api/")).size() == 34

		//debug.printContents($("a", text: containsWord("Page")))
		//debug.printContents($("a", text: notContainsWord("Page")))
		//"PageObjects"はcontainsWordでは合致しない。
		$("a", text: containsWord("Page")).size() == 2
		$("a", text: notContainsWord("Page")).size() == 43

	}

	/**
	 * ■解説
	 * Navigatorオブジェクトは、Iterableインタフェースを実装しているのでmax(),min()のような
	 * Groovyの文法を使うこともできる
	 * 
	 * ■HTML
	 * <p>1</p>
	 * <p>2</p>
	 * 
	 * ■Geb
	 * $("p").max { it.text() }.text() == "2"
	 * $("p")*.text().max() == "2"
	 */
	def "4.1.4 The ＄ Function - Navigators are Iterable"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		//debug.printContents($("a" ,href:contains("manual")).max { it.@href })
		$("a" ,href:contains("manual")).max { it.@href }.@href.contains("snapshot")

		//debug.printContents($("a" ,href:contains("manual")).min { it.@href })
		$("a" ,href:contains("manual")).min { it.@href }.@href.contains("0.6.2")
	}

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
		$("div").filter(".index3").prevAll()*.@class == ["line number1 index0 alt2", "line number2 index1 alt1", "line number3 index2 alt2"]
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
		$("div").filter(".index3").closest(class:"container").size() == 1
		$("div").filter(".index3").closest(".container").size() == 1
		$("div").filter(".index3").closest("div" , class:"container").size() == 1
		$("div").filter(".index3").nextUntil(".index6")*.@class ==["line number5 index4 alt2", "line number6 index5 alt1"]

	}

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

	/**
	 * ■解説
	 * ・Navigator objectsは、click()を実装している
	 * ・click()は、最初にマッチした要素のみ実行される
	 * ・click(Class)を実行するとクリック後、Classがセットされる
	 * 
	 * ■HTML
	 * -
	 * 
	 * ■Geb
	 * $("input.loginButton").click(LoginPage)
	 */
	def "4.5 Clicking"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		when:
		Actions action = new Actions(driver);
		action.moveToElement(apiMenu.firstElement()).perform();
		waitFor{apiLink("current").isDisplayed() == true}
		apiLink("current").click(GebApiPage)

		then:
		waitFor{ at GebApiPage }
	}

	/**
	 * ■解説
	 * ・Navigator objectsには、displayedプロパティが定義されている。
	 *  displayed==falseの場合は、マッチしても操作することができない
	 * ・“Navigator.isDisplayed() == false”の場合にNavigator.click()
	 *  を実行すると例外が発生する
	 * ・ElementNotVisibleException
	 * 
	 * ■HTML
	 * -
	 * 
	 * ■Geb
	 * -
	 */
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

	/**
	 * ■解説
	 * ・Navigator objectsのtag(), text(), @attribute and classes()を使って値を取得できる
	 * ・classes()は、class属性を java.util.List形式で返す
	 * 
	 * ■HTML
	 * <p title="a" class="a para">a</p>
	 * <p title="b" class="b para">b</p>
	 * <p title="c" class="c para">c</p>
	 * 
	 * ■Geb
	 * $("p").text() == "a"
	 * $("p").tag() == "p"
	 * $("p").@title == "a"
	 * $("p").classes() == ["a", "para"]
	 */
	def "4.8 Accessing tag name, attributes, text and classes"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }
		//debug.printContents($("div"))

		and:
		$("div").filter(".index0").classes() == ["alt2", "index0" , "line", "number1"]

	}

	/**
	 * ■解説
	 * cssメソッドでCSSプロパティを取得することができる
	 * 
	 * ■HTML
	 * <div style="float: left">text</div>
	 * 
	 * ■Geb
	 * $("div").css("float") == "left"
	 * 
	 */
	def "4.9 Css properties"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		println $("body").css("font")
	}

}