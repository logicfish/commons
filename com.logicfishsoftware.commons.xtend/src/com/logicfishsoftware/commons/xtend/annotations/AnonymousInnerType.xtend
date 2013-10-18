package com.logicfishsoftware.commons.xtend.annotations

import com.logicfishsoftware.commons.xtend.xannotation.AbstractMemberProcessor
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import java.util.Collections
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.MemberDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableMemberDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration
import org.eclipse.xtend.lib.macro.declaration.Type

import static extension com.logicfishsoftware.commons.xtend.CommonsCollections.*
import static extension com.logicfishsoftware.commons.xtend.xannotation.Notes.*

/**
 * Create an anonymous instance of type 'value', using the optional type parameters. The optional mixin string
 * is used as the constructor parameter list.
 */
@Active(typeof(AnonymousInnerTypeProcessor))
@Target(#[ElementType.FIELD,ElementType.METHOD])
@Retention(RetentionPolicy.SOURCE)
annotation AnonymousInnerType {
	Class<?> value=typeof(Object)
	String[] typeParamS=#[]
	Class<?>[] typeParam=#[]
	String[] ctorParam=#[]
	String mixin=""
}

class AnonymousInnerTypeProcessor extends AbstractMemberProcessor {
	
	override doTransform(MutableMemberDeclaration annotatedMember, TransformationContext context) {
		annotatedMember.doTransformInternal(context)
	}
	
	protected def dispatch void doTransformInternal(MutableMemberDeclaration annotatedField, extension TransformationContext context) {
	}
	protected def dispatch void doTransformInternal(MutableFieldDeclaration annotatedField, extension TransformationContext context) {

		annotatedField.initializer = [annotatedField.buildAnonymousInner(annotatedField.type.type,context)]
	}

	protected def dispatch void doTransformInternal(MutableMethodDeclaration annotatedMethod, extension TransformationContext context) {
		annotatedMethod.abstract = false
		annotatedMethod.body = [ (annotatedMethod.body?.toString ?:"") + " return " + annotatedMethod.buildAnonymousInner(annotatedMethod.returnType.type,context) + ";"]				
	}
	
	def buildAnonymousInner(MemberDeclaration member, Type type,extension TransformationContext context) {
		val annoType = typeof(AnonymousInnerType).findTypeGlobally
		val qName = typeof(AnonymousInnerType).notesAsClasses(member,context)?.head?.qualifiedName ?: type.qualifiedName
		val param = typeof(AnonymousInnerType).notesAsClasses("typeParam",member,context)?.map[qualifiedName]
		val paramS = <String[]>notes(typeof(String).findTypeGlobally,"typeParamS",member)
		
		val ctorParam = annoType.<String>notes("ctorParam",member)?:Collections.emptyList
		val mixin = annoType.<String>note("mixin",member)
//		val typeParam = 
//			if(param!=null&&!param.empty) { "<" + param.toCSVString() + ">"}
//			else if(paramS == null || paramS.empty) { "" } else { "<" + paramS.toCSVString() + ">"}
//		return "new " + qName + typeParam + "(" + ctorParam.toCSVString() + "){" + mixin + "}"
		'''
			new «qName»«IF param!=null && !param.empty»<«param.toCSVString»>«ELSEIF paramS!=null && !paramS.empty»<«paramS.toCSVString»>«ENDIF»
				(«ctorParam?.toCSVString»){«mixin?:""»}
		'''
	}
	
}