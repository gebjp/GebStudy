package jp.org.gebjp.page

import geb.Page

class GebTopPage extends Page {

  static url = "http://www.gebish.org/"

  static at = { $("div" , id:"main").$("h1").text() == "What is it?"}

  static content = {
    divElement { divClass -> $("div").filter(divClass) }
    apiLink{ version -> $("a" , href:contains(version)).filter(href:contains("api"))}
    apiMenu{ $("span" , text:"API")}
  }
}