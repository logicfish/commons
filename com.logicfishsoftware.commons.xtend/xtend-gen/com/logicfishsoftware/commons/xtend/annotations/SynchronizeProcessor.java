package com.logicfishsoftware.commons.xtend.annotations;

import org.eclipse.xtend.lib.macro.AbstractMethodProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class SynchronizeProcessor extends AbstractMethodProcessor {
  public void doTransform(final MutableMethodDeclaration annotatedMethod, @Extension final TransformationContext context) {
    annotatedMethod.setSynchronized(true);
  }
}
