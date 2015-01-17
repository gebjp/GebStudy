package jp.org.gebjp.page

import geb.Page

class YahooSearchResultPage extends Page {

    static url = ""

    static at = { title.contains("検索結果") }

    static content = {
    }
}