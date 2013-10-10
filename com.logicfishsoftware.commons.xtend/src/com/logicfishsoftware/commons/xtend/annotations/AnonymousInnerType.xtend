package com.logicfishsoftware.commons.xtend.annotations

import org.eclipse.xtend.lib.macro.AbstractFieldProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration
import org.eclipse.xtend.lib.macro.declaration.Type

import static extension com.logicfishsoftware.commons.xtend.ToString.*
import static extension com.logicfishsoftware.commons.xtend.xannotation.Notes.*
import java.lang.annotation.Target
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Create an anonymous instance of type 'value', using the optional type parameters. The optional mixin string
 * is used as the constructor parameter list.
 */
@Active(typeof(AnonymousInnerTypeProcessor))
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
annotation AnonymousInnerType {
	Class<?> value=typeof(Object)
	Class<?>[] parameters=#[]
	String[] mixin=#[]
}

class AnonymousInnerTypeProcessor extends AbstractFieldProcessor {
	
	override doTransform(MutableFieldDeclaration annotatedField, extension TransformationContext context) {
		val Type cls = typeof(AnonymousInnerType).notesAsClasses(annotatedField,context)?.head
		val param = typeof(AnonymousInnerType).notesAsClasses("parameters",annotatedField,context)
		val mixin = typeof(AnonymousInnerType).findTypeGlobally.<String>notes("mixin",annotatedField)?.map[it]
		
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