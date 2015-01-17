package jp.org.gebjp.page

import geb.Page
import jp.org.gebjp.module.GoogleSearchModule

class GoogleHomePage extends Page {

    static url = "http://google.com/ncr"

    static at = { title == "Google" }

    static content = {
        search{ module GoogleSearchModule, buttonName: "btnG" }
    }

    public void search(String keyword){
        try {
            search.field.value(keyword)
            search.button.click()
        } catch (Exception e) {
        }
    }
}