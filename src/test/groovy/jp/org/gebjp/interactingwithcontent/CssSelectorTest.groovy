package jp.org.gebjp.interactingwithcontent

import geb.spock.GebSpec
import jp.org.gebjp.page.YahooSearchResultPage
import jp.org.gebjp.page.YahooTopPage

/**
 * ■公式マニュアル
 * ・4.1 The $ Function
 * http://www.gebish.org/manual/current/navigator.html#interacting_with_content
 *
 * ■概要
 * 4.1.1 CSS Selectorsを解説したサンプルです
 *
 * @author YukiFujisawa
 *
 */
class CssSelectorTest extends GebSpec {

    def "get contents test"() {
        when:
        to YahooTopPage

        then:
        waitFor{ at YahooTopPage }

        when:
        $("form").p = "sample"
        searchBtn.click()

        then:
        waitFor{ at YahooSearchResultPage }
    }
}