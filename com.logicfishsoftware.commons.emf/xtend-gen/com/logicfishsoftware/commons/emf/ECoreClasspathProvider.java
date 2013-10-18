package com.logicfishsoftware.commons.emf;

import com.google.inject.Inject;
import com.logicfishsoftware.commons.emf.ResourceSetClasspathProvider;
import com.logicfishsoftware.commons.jre.ClasspathProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class ECoreClasspathProvider implements ClasspathProvider<EObject> {
  @Inject
  @Extension
  private ResourceSetClasspathProvider _resourceSetClasspathProvider;
  
  public ClassLoader getClassLoaderFor(final ClassLoader parent, final EObject t) {
    Resource _eResource = t.eResource();
    ResourceSet _resourceSet = _eResource.getResourceSet();
    ClassLoader _classLoaderFor = this._resourceSetClasspathProvider.getClassLoaderFor(parent, _resourceSet);
    return _classLoaderFor;
  }
}
