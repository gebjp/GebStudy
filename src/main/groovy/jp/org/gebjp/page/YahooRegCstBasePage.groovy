package jp.org.gebjp.page

import geb.Page
/**
 * yahooの新規会員登録ページ
 * 
 * @author Yuki.Fujisawa
 *
 */
class YahooRegCstBasePage extends Page {

	static url = "https://account.edit.yahoo.co.jp/registration"

	static at = { title.startsWith("Yahoo! JAPAN ID登録")}

	static content = {
	}
}
