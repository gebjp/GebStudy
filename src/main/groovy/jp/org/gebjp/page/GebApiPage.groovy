package jp.org.gebjp.page

import geb.Page

class GebApiPage extends Page {

  static url = "http://www.gebish.org/manual/current/api/"

  static at = { title.contains("Groovy API")}

  static content = {
  }
}