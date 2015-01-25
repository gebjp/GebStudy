package jp.org.gebjp.page

import geb.Page
import jp.org.gebjp.module.GoogleSearchModule

class GoogleResultsPage extends Page {
  static url = "https://www.google.com/?gws_rd=ssl"
  static at = { title.endsWith "Google Search" }
  static content = {
    search{ module GoogleSearchModule, buttonName: "btnG" }
    resultLinks(wait:true){ $("li" , class:"g")}
    firstResultLink(wait:true){ $("li" , class:"g" , 0).$("a")}
  }
  public void firstResultLinkClick(){
    firstResultLink.click()
  }

  public void search(String keyword){
    try {
      search.field.value(keyword)
      search.button.click()
    } catch (Exception e) {
    }
  }
}
