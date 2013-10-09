package com.logicfishsoftware.commons.xtend.generics

import java.lang.reflect.Type
import java.lang.reflect.ParameterizedType
import java.util.ArrayList
import java.lang.reflect.TypeVariable
import java.util.List

class GenericSupertype {
	/**
	 * Retrieve the parameters used to declare the class 'type' as a super-type of class 'cls'.
	 * The inheritance tree of 'cls' is iterated until the class 'type' is found.  Then, the generic parameters
	 * are retrieved.  If the parameter is of type {@link Class<?>} then it is added to the result array.  If the parameter
	 * is of type {@link TypeVariable} then the first bounds parameter is added to the result.<br/>
	 * This function is good for both classes and interfaces.<br/>
	 * <br/>
	 * Returns and array of classes representing either the direct type parameters, or the bounds thereof in the case of a variable. 
	 */
	def static Class<?>[] parametersOfSuper(Class<?> type,Class<?> cls) {
		if(type.interface) {
			val r = new ArrayList<Class<?>>()			
			cls.genericInterfaces.forEach[
				if(it!=typeof(Object) && it!=null) {
					if( !(it instanceof ParameterizedType) || (it as ParameterizedType).rawType != type) {
						r.addAll(type.parametersOfSuper(it as Class<?>))
					} else {
						r.addAll((it as ParameterizedType).actualTypeArguments.convertParameters())			
					}				
				}
			]
			return r
		} else {
			val Type supertype = cls.genericSuperclass
			if(supertype==typeof(Object) || supertype==null) return #[]
			if(!(supertype instanceof ParameterizedType)||(supertype as ParameterizedType).rawType != type) {
				return type.parametersOfSuper(supertype as Class<?>) 
			}
			return (supertype as ParameterizedType).actualTypeArguments.convertParameters()				
		}
	}
	def private static Class<?>[] convertParameters(Type[] types) {
		val List<Class<?>> r = new ArrayList<Class<?>>()
		types.forEach[
			switch(it) {
				Class<?>: r += it
				TypeVariable<?>: r.addAll(it.bounds.convertParameters())
			}			
		]
		return r
	}
}
