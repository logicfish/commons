package com.logicfishsoftware.commons.xtend.xannotation

import com.google.inject.Inject
import java.util.List
import org.eclipse.xtend.lib.macro.CodeGenerationContext
import org.eclipse.xtend.lib.macro.CodeGenerationParticipant
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext
import org.eclipse.xtend.lib.macro.RegisterGlobalsParticipant
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.TransformationParticipant
import org.eclipse.xtend.lib.macro.declaration.MemberDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableMemberDeclaration
import org.eclipse.xtend.lib.macro.services.TypeReferenceProvider

abstract class AbstractMemberProcessor 
implements RegisterGlobalsParticipant<MemberDeclaration>, TransformationParticipant<MutableMemberDeclaration>, CodeGenerationParticipant<MemberDeclaration> 
{
	@Inject extension TypeReferenceProvider
	
	override doRegisterGlobals(List<? extends MemberDeclaration> annotatedSourceElements, RegisterGlobalsContext context) {
		annotatedSourceElements.forEach[it.doRegisterGlobals(context)]
	}
	def dispatch void doRegisterGlobals(MemberDeclaration annotatedMember, extension RegisterGlobalsContext context) {}
//	def dispatch void registerGlobals(FieldDeclaration annotatedField, extension RegisterGlobalsContext context) {}
//	def dispatch void registerGlobals(MethodDeclaration annotatedMethod, extension RegisterGlobalsContext context) {}
//	def dispatch void registerGlobals(TypeDeclaration annotatedType, extension RegisterGlobalsContext context) {}

	override doTransform(List<? extends MutableMemberDeclaration> annotatedTargetElements, extension TransformationContext context) {
		annotatedTargetElements.forEach[it.doTransform(context)]
	}
	def dispatch void doTransform(MemberDeclaration annotatedMember, extension TransformationContext context) {}
//	def dispatch void transform(MutableFieldDeclaration annotatedField, extension TransformationContext context) {}
//	def dispatch void transform(MutableMethodDeclaration annotatedMethod, extension TransformationContext context) {}
//	def dispatch void transform(MutableTypeDeclaration annotatedType, extension TransformationContext context) {}
	
	override doGenerateCode(List<? extends MemberDeclaration> annotatedSourceElements, extension CodeGenerationContext context) {
		annotatedSourceElements.forEach[it.doGenerateCode(context)]
	}
	def dispatch void doGenerateCode(MemberDeclaration annotatedMember, extension CodeGenerationContext context) {}
//	def dispatch void generateCode(FieldDeclaration annotatedField, extension CodeGenerationContext context) {}
//	def dispatch void generateCode(MethodDeclaration annotatedMethod, extension CodeGenerationContext context) {}
//	def dispatch void generateCode(TypeDeclaration annotatedType, extension CodeGenerationContext context) {}
	
}