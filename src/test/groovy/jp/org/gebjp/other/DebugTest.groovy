package jp.org.gebjp.other

import geb.spock.GebSpec

class DebugTest extends GebSpec {

  def "output debug log"() {
    when:
    go "http://www.yahoo.co.jp"

    then:
    waitFor{ title == "Yahoo! JAPAN"}
    $("input").each{
      println "name: " + it.getAttribute("name") +
          ", type: " + it.getAttribute("type") +
          ", value: " + it.getAttribute("value")
    }
  }
}