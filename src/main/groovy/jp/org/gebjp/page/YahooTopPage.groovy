package jp.org.gebjp.page

import geb.Page

class YahooTopPage extends Page {


  static url = "http://www.yahoo.co.jp"

  static at = { title == "Yahoo! JAPAN" }

  static content = {
    contentType1{ $("head meta",0) }
    contentType2{ $("head meta",0,"http-equiv":"content-type") }
    contentType3{ $("http-equiv":"content-type") }
    searchBtn{ $("input",value:"検索") }
  }

  public search(String key) {
  }
}