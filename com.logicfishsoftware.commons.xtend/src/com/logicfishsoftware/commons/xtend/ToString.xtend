package com.logicfishsoftware.commons.xtend

class ToString {
	def static <T> String toString(T[] t,String delim) {
		if(t==null || t.empty)return ""
		if(t.length==1)return t.head?.toString
		return t.head?.toString + delim + t.tail?.toString(delim)
	}
}