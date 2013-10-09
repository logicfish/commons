package com.logicfishsoftware.commons.xtend.annotations

import org.eclipse.xtend.lib.macro.AbstractFieldProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration
import org.eclipse.xtend.lib.macro.declaration.Type

import static extension com.logicfishsoftware.commons.xtend.ToString.*
import static extension com.logicfishsoftware.commons.xtend.xannotation.Notes.*

/**
 * Create and anonymous instance of type 'value', using the optional type parameters. The optional mixin string
 * is used as the constructor parameter list.
 */
@Active(typeof(AnonymousInstanceProcessor))
annotation AnonymousInstance {
	Class<?> value=typeof(Object)
	Class<?>[] parameters=#[]
	String[] mixin=#[]
}

class AnonymousInstanceProcessor extends AbstractFieldProcessor {
	
	override doTransform(MutableFieldDeclaration annotatedField, extension TransformationContext context) {
		val Type cls = typeof(AnonymousInstance).notesAsClasses(annotatedField,context)?.head
		val param = typeof(AnonymousInstance).notesAsClasses("parameters",annotatedField,context)
		val mixin = typeof(AnonymousInstance).findTypeGlobally.<String>notes("mixin",annotatedField)?.map[it]
		
		annotatedField.initializer = [
			val qName = if(object.type==cls||cls==null) {
				annotatedField.type.type.qualifiedName
			} else {
				cls.qualifiedName
			}
			val typeParam = if(param == null || param.empty) { "" } else { "<" + param.map[it.qualifiedName].toString(",") + ">"}
			"new " + qName + typeParam + "(" + mixin.toString(",") + "){}"
		]
	}
	
}