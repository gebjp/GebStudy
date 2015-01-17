package jp.org.gebjp.interactingwithcontent

import geb.spock.GebSpec
import jp.org.gebjp.page.YahooTopPage

/**
 * ■公式マニュアル
 * ・4.1 The $ Function
 * http://www.gebish.org/manual/current/navigator.html#interacting_with_content
 *
 * ■概要
 * $(«css selector», «index or range», «attribute / text matchers»)を解説したサンプルです
 *
 * @author YukiFujisawa
 *
 */
class DollarFunctionTest extends GebSpec {

    def "get contents test"() {
        when:
        to YahooTopPage

        then:
        waitFor{ at YahooTopPage }

        and:
        //$("head meta",0)
        contentType1.@content == "text/html; charset=utf-8"

        and:
        // $("head meta",0,"http-equiv":"content-type")
        contentType2.@content == "text/html; charset=utf-8"

        and:
        //$("http-equiv":"content-type")
        contentType3.@content == "text/html; charset=utf-8"
    }
}