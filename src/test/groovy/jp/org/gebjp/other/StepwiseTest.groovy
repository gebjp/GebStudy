package jp.org.gebjp.other

import geb.spock.GebSpec
import spock.lang.Stepwise

/**
 * @Stepwiseがある時には、クッキーが維持されることを確かめる
 * テストケースです。
 *
 * @author Yuki.Fujisawa
 *
 */
@Stepwise
class StepwiseTest extends GebSpec {

  def "Login google"() {
    setup:
    def googleLoginUrl = "https://accounts.google.com"

    //GebConfig.groovyの設定値を読み込む
    def googleUserId = browser.config.rawConfig.googleUserId
    def googlePassword = browser.config.rawConfig.googlePassword

    when:
    go googleLoginUrl

    then:
    waitFor{ title == "ログイン - Google アカウント" }

    when:
    $("form").Email = googleUserId
    $("form").Passwd = googlePassword
    $("input" , name:"signIn").click()

    then:
    waitFor{ title == "アカウント設定"}
  }

  def "we don't need to login google. Because stepwise is setting"() {
    setup:
    def googleLoginUrl = "https://accounts.google.com"

    when:
    go googleLoginUrl

    then:
    waitFor{ title == "アカウント設定"}

  }
}