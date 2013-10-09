package com.logicfishsoftware.commons.xtend

import java.util.List
import org.eclipse.emf.ecore.EObject

class CommonsECore {
	def static List<EObject> eAllContainers(EObject o) {
		if(o.eContainer==null) return #[]
		val r = #[ o.eContainer ]
		r += o.eContainer.eAllContainers
		return r
	}
}