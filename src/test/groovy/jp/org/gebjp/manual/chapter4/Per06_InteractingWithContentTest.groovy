package jp.org.gebjp.manual.chapter4

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
class Per06_InteractingWithContentTest extends GebSpec {

	@Shared GebDebugUtil debug = new GebDebugUtil()

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
}