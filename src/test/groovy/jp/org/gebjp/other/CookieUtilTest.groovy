package jp.org.gebjp.other

import geb.spock.GebSpec
import jp.org.gebjp.util.GebCookieUtil
import spock.lang.Shared

class CookieUtilTest extends GebSpec {

    @Shared GebCookieUtil cookieUtil = new GebCookieUtil(driver)

    def "output cookie"() {
        when:
        go "http://www.yahoo.co.jp"

        then:
        waitFor{ title == "Yahoo! JAPAN"}
        cookieUtil.printCookies()
        assert cookieUtil.getCookieVal("JV") == null

        when:
        $("form").p = "sample"
        $("input",value:"検索").click()

        then:
        waitFor{ title == "「sample」の検索結果 - Yahoo!検索"}
        cookieUtil.printCookies()
        assert cookieUtil.getCookieVal("JV") != null
    }
}