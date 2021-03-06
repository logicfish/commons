package com.logicfishsoftware.commons.emf

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.parsetree.reconstr.Serializer

class ToString {
	@Inject static extension IQualifiedNameProvider

//	@Inject
// 	private static Serializer SERIALIZER

 	private static Serializer SERIALIZER = null  
 	
 	@Inject
 	private static RuntimeInjectorProvider injectorProvider
 	
	def private static Serializer getSerializer() {  
  		if (SERIALIZER == null) { // lazy creation  
	   		SERIALIZER = injectorProvider?.injector  
        		?.getInstance(typeof(Serializer))
  		}  
	  	return SERIALIZER;  
 	}  
	def public static <T> String valueOf(T t) { 
		t.getStringValue
	}	
	def static dispatch String getStringValue(Object o) {
		"" + o
	}
	def static dispatch String getStringValue(EObject o) {
		getSerializer?.serialize(o)?:"null"
	}
	def public static <T> String toString(EObject o) { 
		o.fullyQualifiedName.toString
	}	
}