package com.logicfishsoftware.commons.xtend.annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import org.eclipse.xtend.lib.macro.AbstractMethodProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration
import org.eclipse.xtend.lib.macro.TransformationContext

@Active(typeof(SynchronizeProcessor))
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
annotation Synchronize {
}

class SynchronizeProcessor extends AbstractMethodProcessor {
	
	override doTransform(MutableMethodDeclaration annotatedMethod, extension TransformationContext context) {
		annotatedMethod.synchronized = true
	}
	
}