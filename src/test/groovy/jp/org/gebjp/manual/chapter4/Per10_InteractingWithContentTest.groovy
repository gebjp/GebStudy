package jp.org.gebjp.manual.chapter4

import geb.spock.GebSpec
import jp.org.gebjp.page.GebTopPage
import jp.org.gebjp.util.GebDebugUtil

import org.openqa.selenium.Keys

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
class Per10_InteractingWithContentTest extends GebSpec {

	@Shared GebDebugUtil debug = new GebDebugUtil()

	/**
	 * ■解説
	 * ・ leftShiftを使うことでどのコンテンツにもキーストロークを送信することができる
	 *
	 * ■HTML
	 * -
	 *
	 * ■Geb
	 * $("div") << "abc"
	 */
	def "4.10 Sending keystrokes"() {
		when:
		to GebTopPage

		then:
		waitFor{ at GebTopPage }

		and:
		$("body") << Keys.chord(Keys.CONTROL, "a")
		$("body") << Keys.chord(Keys.ENTER)
	}
}