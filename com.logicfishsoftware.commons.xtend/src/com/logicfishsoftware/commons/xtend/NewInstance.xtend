package com.logicfishsoftware.commons.xtend

import java.lang.reflect.Constructor

import static extension com.logicfishsoftware.commons.xtend.CommonsCollections.*

class NewInstance {
	static def <T> T newInstanceOf(T t) {
		t.class.newInstance as T
	}
	static def <T> T newInstanceOf(T[] t) {
		t.class.newInstance as T
	}
	
	static def Boolean isAssignableFrom(Class<?>[] cls,Class<?>[] to) {
		return cls.length==to.length && cls.isAssignableFromHead(to)
	}
	static def Boolean isAssignableFromHead(Class<?>[] cls,Class<?>[] to) {
		if(cls.head.isAssignableFrom(to.head)) return isAssignableFromHead(cls.tail(),to.tail())
	}
	static def Boolean isAssignableFromTail(Class<?>[] cls,Class<?>[] to) {
		if(cls.last.isAssignableFrom(to.last)) return isAssignableFromHead(cls.withoutLast(),to.withoutLast())
	}
	
	static def <T> T newInstance(Class<T> t,Object ... u) {
		val Constructor<T> c = t.class.constructors.filter[parameterTypes.isAssignableFrom(u.map[class])]?.head as Constructor<T>
		return c?.newInstance(u)		
	}
	static def <T> T newInstanceOf(T t,Object ... u) {
		return (t.class as Class<T>).newInstance(u)
	}
	
}

