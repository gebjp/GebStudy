package jp.org.gebjp.other

import geb.spock.GebSpec
import jp.org.gebjp.page.GoogleHomePage
import jp.org.gebjp.page.GoogleResultsPage
import jp.org.gebjp.page.WikipediaPage

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver

import spock.lang.Unroll

/**
 * ■公式マニュアル
 * ・2 The Browser
 * http://www.gebish.org/manual/current/browser.html#the_browser
 *
 * ■概要
 * browser.driverを変更することでクロスブラウザが実現できるというサンプルです。
 *
 * ■詳細
 * 下記、ブログを参照してください
 * http://yfj2.hateblo.jp/entry/2014/12/25/023054
 *
 * @author YukiFujisawa
 *
 */

class WebDriverTest extends GebSpec {
	@Unroll
	def "first result for wikipedia search should be wikipedia"() {

		setup:
		switch(driverName){
			case "firefox" :
				driver = new FirefoxDriver()
				break
			case "chrome":
				driver = new ChromeDriver()
				break
			case "ie":
				driver = new InternetExplorerDriver()
				break
		}

		when:
		to GoogleHomePage

		then:
		at GoogleHomePage

		when:
		search("Wikipedia")

		then:
		waitFor { at GoogleResultsPage }

		when:
		firstResultLinkClick()

		then:
		waitFor { at WikipediaPage }

		where:
		driverName << ["firefox", "chrome"]
	}

	def cleanup(){
		driver.quit()
	}
}