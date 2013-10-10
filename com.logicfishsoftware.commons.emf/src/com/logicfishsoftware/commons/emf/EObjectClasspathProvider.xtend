package com.logicfishsoftware.commons.emf

import com.logicfishsoftware.commons.pde.project.ProjectClasspathResolver
import java.net.URLClassLoader
import org.eclipse.emf.ecore.EObject
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.xtext.resource.XtextResourceSet

abstract class EObjectClasspathProvider {
	def static ClassLoader getClassLoader(EObject object,ClassLoader parent) {
		val resourceSet = object.eResource.resourceSet
		switch(resourceSet) {
			XtextResourceSet: {
				val ctx = resourceSet.classpathURIContext
				return switch(ctx) {
					ClassLoader: ctx
					Class<?>: ctx.classLoader 
					IJavaProject: new URLClassLoader(ProjectClasspathResolver::resolveProject(ctx),parent)
					default: parent  // class.classLoader  
				}
			}			
		} 
	}
	
}
