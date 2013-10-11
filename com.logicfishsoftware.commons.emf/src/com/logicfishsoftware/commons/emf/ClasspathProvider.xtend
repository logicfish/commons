package com.logicfishsoftware.commons.emf

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.xtext.resource.XtextResourceSet
import com.logicfishsoftware.commons.jre.ClasspathProvider
import com.logicfishsoftware.commons.pde.project.IJavaProjectClasspathProvider

class ECoreClasspathProvider  implements ClasspathProvider<EObject> {
	
	@Inject
	IJavaProjectClasspathProvider projectClasspathProvider
	
	override ClassLoader getClassLoaderFor(ClassLoader parent,EObject object) {
		val resourceSet = object.eResource.resourceSet
		switch(resourceSet) {
			XtextResourceSet: {
				val ctx = resourceSet.classpathURIContext
				return switch(ctx) {
					ClassLoader: ctx
					Class<?>: ctx.classLoader 
					IJavaProject: return projectClasspathProvider.getClassLoaderFor(parent,ctx) 
					default: parent
				}
			}			
		} 
	}
	
}

class ResourceSetClasspathProvider  implements ClasspathProvider<EObject> {
	
	@Inject
	ECoreClasspathProvider ecoreClasspathProvider

	override getClassLoaderFor(ClassLoader parent, EObject eObject) {
		ecoreClasspathProvider.getClassLoaderFor(parent,eObject)
	}
	
}