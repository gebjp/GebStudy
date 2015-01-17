package jp.org.gebjp.module

import geb.Module
import jp.org.gebjp.page.GoogleResultsPage

class GoogleSearchModule extends Module {

    def buttonName

    static content = {
        field { $("input", name: "q") }
        button(to: GoogleResultsPage) {
            $("button", name: buttonName)
        }
    }
}