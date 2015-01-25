package jp.org.gebjp.other

import geb.spock.GebSpec

/**
 * @Stepwiseが無いときには、クッキーが維持されないこと確かめる
 * テストケースです。
 * 
 * @author Yuki.Fujisawa
 *
 */
class NonStepwiseTest extends GebSpec {

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

  /**
   * クッキーが消去されるので、ログイン状態が維持できない。
   * ログイントップに遷移する。
   * しかし、ブラウザキャッシュが残っているのか、
   * $("form").Email = googleUserIdがエラーになった。
   * @return
   */
  def "we need to login google. Because stepwise is not setting"() {
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
    $("form").Passwd = googlePassword
    $("input" , name:"signIn").click()

    then:
    waitFor{ title == "アカウント設定"}
  }
}