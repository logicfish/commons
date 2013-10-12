package com.logicfishsoftware.commons.emf

import com.google.inject.Inject
import com.logicfishsoftware.commons.jre.ClasspathProvider
import com.logicfishsoftware.commons.pde.project.IJavaProjectClasspathProvider
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.xtext.resource.XtextResourceSet

class ECoreClasspathProvider implements ClasspathProvider<EObject> {
	
	@Inject
	extension ResourceSetClasspathProvider 

	override getClassLoaderFor(ClassLoader parent, EObject t) {
		parent.getClassLoaderFor(t.eResource.resourceSet)
	}
	
	
}

class ResourceSetClasspathProvider implements ClasspathProvider<ResourceSet> {
	
	@Inject
	extension XTextResourceSetClasspathProvider	

	override ClassLoader getClassLoaderFor(ClassLoader parent,ResourceSet resourceSet) {
		switch(resourceSet) {
			XtextResourceSet: parent.getClassLoaderFor(resourceSet)
			default: class.classLoader		
		}
	}	
}

class XTextResourceSetClasspathProvider implements ClasspathProvider<XtextResourceSet> {
	
	@Inject
	extension IJavaProjectClasspathProvider	

	override ClassLoader getClassLoaderFor(ClassLoader parent,XtextResourceSet resourceSet) {
			val ctx = resourceSet.classpathURIContext
			return switch(ctx) {
				ClassLoader: ctx
				Class<?>: ctx.classLoader 
				IJavaProject: parent.getClassLoaderFor(ctx) 
				default: parent
			}
	}
}