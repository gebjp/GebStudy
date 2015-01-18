package jp.org.gebjp.util;


class GebDebugUtil {

	def printContents(contents){
		def i = 0;
		contents.each {
			println "index: " + i + ", tag(): " + it.tag()+ ", text(): "+ it.text()
			i++
		}
	}

	def printLink(contents){
		def i = 0;
		contents.each {
			println "index: " + i + ", tag(): " + it.tag()+ ", @href: "+ it.@href+ ", text(): "+ it.text()
			i++
		}
	}
}
