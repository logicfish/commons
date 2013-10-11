package com.logicfishsoftware.commons.emf;

import com.google.inject.Inject;
import com.logicfishsoftware.commons.emf.ECoreClasspathProvider;
import com.logicfishsoftware.commons.jre.ClasspathProvider;
import org.eclipse.emf.ecore.EObject;

@SuppressWarnings("all")
public class ResourceSetClasspathProvider implements ClasspathProvider<EObject> {
  @Inject
  private ECoreClasspathProvider ecoreClasspathProvider;
  
  public ClassLoader getClassLoaderFor(final ClassLoader parent, final EObject eObject) {
    ClassLoader _classLoaderFor = this.ecoreClasspathProvider.getClassLoaderFor(parent, eObject);
    return _classLoaderFor;
  }
}
