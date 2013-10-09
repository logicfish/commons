package com.logicfishsoftware.commons.xtend

class NewInstance {
	def <T> T newInstanceOf(T t) {
		t.class.newInstance as T
	}
	def <T> T newInstanceOf(T[] t) {
		t.class.newInstance as T
	}
	
}

