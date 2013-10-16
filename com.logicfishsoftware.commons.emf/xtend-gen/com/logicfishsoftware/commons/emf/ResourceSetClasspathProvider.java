package com.logicfishsoftware.commons.emf;

import com.google.inject.Inject;
import com.logicfishsoftware.commons.emf.XTextResourceSetClasspathProvider;
import com.logicfishsoftware.commons.jre.ClasspathProvider;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class ResourceSetClasspathProvider implements ClasspathProvider<ResourceSet> {
  @Inject
  @Extension
  private XTextResourceSetClasspathProvider _xTextResourceSetClasspathProvider;
  
  public ClassLoader getClassLoaderFor(final ClassLoader parent, final ResourceSet resourceSet) {
    ClassLoader _switchResult = null;
    boolean _matched = false;
    if (!_matched) {
      if (resourceSet instanceof XtextResourceSet) {
        final XtextResourceSet _xtextResourceSet = (XtextResourceSet)resourceSet;
        _matched=true;
        ClassLoader _classLoaderFor = this._xTextResourceSetClasspathProvider.getClassLoaderFor(parent, _xtextResourceSet);
        _switchResult = _classLoaderFor;
      }
    }
    if (!_matched) {
      Class<? extends ResourceSetClasspathProvider> _class = this.getClass();
      ClassLoader _classLoader = _class.getClassLoader();
      _switchResult = _classLoader;
    }
    return _switchResult;
  }
}
