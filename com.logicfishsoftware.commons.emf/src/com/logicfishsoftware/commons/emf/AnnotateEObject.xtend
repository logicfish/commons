package com.logicfishsoftware.commons.emf

import org.eclipse.emf.common.util.BasicEMap
import org.eclipse.emf.ecore.EAnnotation
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.EcorePackage

class AnnotateEObject {
	static def addEAnnotationDetail(EAnnotation annotation, String key,String value) {
		val map = EcoreFactory::eINSTANCE.create(EcorePackage::eINSTANCE.getEStringToStringMapEntry()) as BasicEMap.Entry
		map.key = key
		map.value = value
		annotation.details.add(map)
	}    
}