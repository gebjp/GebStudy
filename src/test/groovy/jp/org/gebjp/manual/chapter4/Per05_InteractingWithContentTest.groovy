package jp.org.gebjp.manual.chapter4

import geb.spock.GebSpec
import jp.org.gebjp.page.GebApiPage
import jp.org.gebjp.page.GebTopPage
import jp.org.gebjp.util.GebDebugUtil

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
class Per05_InteractingWithContentTest extends GebSpec {

	@Shared GebDebugUtil debug = new GebDebugUtil()

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
}