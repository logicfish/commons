package com.logicfishsoftware.commons.xtend

import static extension java.util.Collections.*

class CommonsCollections {
	def static <T> Iterable<T> withoutLast(T[] t) {
		return t.take(t.length-1)
	}
	def static <T> Iterable<T> reverseViewIf(T[] t,boolean reverse) {
		if (reverse) return t.reverseView
		else return t
	}
	def static <T> Iterable<T> reverseViewIf(T[] t,()=>boolean reverse) {
		if (reverse.apply()) return t.reverseView
		else return t
	}
	def static <T> Iterable<T> asCollection(Object v) {
		if(v.class.array) {
			return v as T[]
		}
		if(typeof(Iterable).isAssignableFrom(v.class)) {
			return v as Iterable<T>
		} 
		return (v as T).singletonList 			
	}
	def static <T> T asNotCollection(Object v) {
		if(v.class.array) {
			return (v as T[]).head
		}
		if(typeof(Iterable).isAssignableFrom(v.class)) {
			return (v as Iterable<T>).head
		} 
		
		return v as T 			
	}
}