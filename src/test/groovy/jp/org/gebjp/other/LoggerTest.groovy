package jp.org.gebjp.other

import geb.spock.GebSpec
import groovy.util.logging.Log
import spock.lang.Shared
@Log
class LoggerTest extends GebSpec {

	@Shared File logger = new File("target/" + LoggerTest.class.simpleName + ".log")

	def "output log file"() {
		setup:
		logger.delete()

		when:
		go "http://www.yahoo.co.jp"

		then:
		waitFor{ title == "Yahoo! JAPAN"}
		logger.append("firstPage: " + title + "\n")
		$("input").each{
			println "name: " + it.getAttribute("name") +
					", type: " + it.getAttribute("type") +
					", value: " + it.getAttribute("value")
		}

		when:
		$("form").p = "sample"
		$("input",value:"検索").click()
		logger.append("search: " + "sample" + "\n")

		then:
		waitFor{ title == "「sample」の検索結果 - Yahoo!検索"}
		logger.append("secondPage: " + title + "\n")
	}
}