package com.logicfishsoftware.commons.xtend

import static extension java.util.Collections.*

class CommonsCollections {
	def static <T> Iterable<T> withoutLast(T[] t) {
		return t.take(t.length-1)
	}
	def static <T> Iterable<T> reverseViewIf(T[] t,boolean condition) {
		if (condition) return t.reverseView
		else return t
	}
	def static <T> Iterable<T> reverseViewIf(T[] t,()=>boolean condition) {
		if (condition.apply()) return t.reverseView
		else return t
	}
	def static <T> removeIf(T[] c,T t,boolean condition) {
		if (condition) return c.remove(t)
	}
	def static <T> removeIf(T[] c,T t,()=>boolean condition) {
		if (condition.apply()) return c.remove(t)
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
	def static <T> String toString(T[] t,String delim) {
		if(t==null || t.empty)return ""
		if(t.length==1)return t.head?.toString
		return t.head?.toString + delim + t.tail?.toString(delim)
	}
	
}