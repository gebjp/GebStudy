package jp.org.gebjp.util;


class GebDebugUtil {

	def printContents(contents){
		def i = 0;
		if(contents.tag() == "a"){
			printLink(contents)
		}else{
			contents.each {
				println "index: " + i + ", tag():\"" + it.tag() + "\", @class():\"" + it.@class + "\", text():\""+ it.text() + "\""
				i++
			}
		}
	}

	def printLink(contents){
		def i = 0;
		contents.each {
			println "index: " + i + ", tag():\"" + it.tag()+ "\", @href:\""+ it.@href+ "\", text():\""+ it.text() + "\"" + ", isDisplayed(): " + it.isDisplayed()
			i++
		}
	}
}
