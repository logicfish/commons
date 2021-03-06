package com.logicfishsoftware.commons.xtend.xannotation

import java.util.List
import org.eclipse.xtend.lib.macro.declaration.AnnotationTarget
import org.eclipse.xtend.lib.macro.declaration.TypeDeclaration
import org.eclipse.xtend.lib.macro.services.TypeLookup
import org.eclipse.xtext.common.types.JvmDeclaredType

import static extension com.logicfishsoftware.commons.xtend.CommonsCollections.*
import org.eclipse.xtend.lib.macro.declaration.Type
import org.eclipse.xtext.common.types.JvmType

/**
 * TODO this class is dirty, switch to using qualifiedNames all over.
 */
class Notes {
	def static Iterable<TypeDeclaration> notesAsClasses(Class<?> cls,AnnotationTarget declaration,extension TypeLookup context) {
		Notes::notesAsClasses(cls,"value",declaration,context)
	}
	def static Iterable<TypeDeclaration> notesAsClasses(Class<?> cls,String property,AnnotationTarget declaration,extension TypeLookup context) {
		val note = declaration.findAnnotation(cls.findTypeGlobally)?.getValue(property?:"value")		
		switch(note) {
			List<JvmDeclaredType>:
				return note.map[qualifiedName.findTypeGlobally as TypeDeclaration] 
			JvmDeclaredType:
				return #[note.qualifiedName.findTypeGlobally as TypeDeclaration]
		}
	}
	def static Type noteAsClass(Class<?> cls,String property,AnnotationTarget declaration,extension TypeLookup context) {
		val note = declaration.findAnnotation(cls.findTypeGlobally)?.getValue(property?:"value")		
		switch(note) {
			List<JvmDeclaredType>:
				return note.head.qualifiedName.findTypeGlobally 
			JvmDeclaredType:
				return note.qualifiedName.findTypeGlobally
		}
	}
	def static <T> Iterable<T> notes(Type cls,String property,AnnotationTarget declaration) {
		val v = declaration.findAnnotation(cls)?.getValue(property?:"value")
		return v?.asCollection
	}
	def static <T> T note(Type cls,String property,AnnotationTarget declaration) {
		val v = declaration.findAnnotation(cls)?.getValue(property?:"value")
		return v?.asNotCollection
	}
}