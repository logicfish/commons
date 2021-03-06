package com.logicfishsoftware.commons.xtend

import java.util.Map
import org.eclipse.xtext.xbase.lib.Pair

import static extension java.util.Collections.*
import java.util.List

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
	def static <T> Iterable<T> removeEach(List<T> list,(T)=>void fnc) {
		list.forEach[if(list.remove(it))fnc.apply(it)]
		return list
	}

	def static <T> Iterable<T> asCollection(Object v) throws ClassCastException {
		if(v.class.array) {
			return v as T[]
		}
		if(typeof(Iterable).isAssignableFrom(v.class)) {
			return v as Iterable<T>
		} 
		return (v as T).singletonList 			
	}
	def static <T> T asNotCollection(Object v) throws ClassCastException {
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
	def static <T> String toCSVString(T[] t) {
		t.toString(",")
	}
	def static <T> Iterable<T> append(T[] c,T t) {
		c+#[t]
	}
	def static <T> Iterable<T> append(T[] c,T ... t) {
		c+t
	}
	
	def static <T> Iterable<T> prepend(T[] c,T t) {
		#[t]+c
	}
	
	def static <T> Iterable<T> prepend(T[] c,T ... t) {
		t+c
	}
	
	def static <T,U> Map<T,U> filterKeys(Map<T,U> m,(T)=>boolean f) {
		m.filter[p1, p2|f.apply(p1)]
	}
	def static <T,U> Map<T,U> filterValues(Map<T,U> m,(U)=>boolean f) {
		m.filter[p1, p2|f.apply(p2)]
	}
	def static <T,U> Pair<? extends T,? extends U> put(Map<T,U> m,Pair<? extends T,? extends U> p) {
		m.put(p.key,p.value)
		return p
	}
	def static <T,U> Pair<? extends T,? extends U> removePair(Map<T,U> m,Pair<? extends T,? extends U> p) {
		if(m.get(p.key)==p.value) {
			m.remove(p.key)
			return p
		}
		return null
	}
	
	
}