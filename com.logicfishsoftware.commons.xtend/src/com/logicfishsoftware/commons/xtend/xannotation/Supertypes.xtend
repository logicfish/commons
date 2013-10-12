package com.logicfishsoftware.commons.xtend.xannotation

import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.InterfaceDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableInterfaceDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableTypeDeclaration

import static extension com.logicfishsoftware.commons.xtend.CommonsCollections.*


abstract class Supertypes {
	def static addImplentedInterface(MutableClassDeclaration type, InterfaceDeclaration objectFunc, extension TransformationContext context) {
		type.implementedInterfaces = type.implementedInterfaces.append(objectFunc.newTypeReference())
	}
	def static addExtendedInterface(MutableInterfaceDeclaration type, InterfaceDeclaration objectFunc, extension TransformationContext context) {
		type.extendedInterfaces = type.extendedInterfaces.append(objectFunc.newTypeReference())
	}
	def static addSupertype(MutableTypeDeclaration it,InterfaceDeclaration superType,extension TransformationContext context) {
		switch(it) {
			MutableClassDeclaration : {
				it.addImplentedInterface(superType,context)
			}
			MutableInterfaceDeclaration : {
				it.addExtendedInterface(superType,context)				
			}
		}
	}
}