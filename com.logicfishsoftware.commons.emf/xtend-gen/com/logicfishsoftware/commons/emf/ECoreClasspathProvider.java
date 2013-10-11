package com.logicfishsoftware.commons.emf;

import com.google.inject.Inject;
import com.logicfishsoftware.commons.jre.ClasspathProvider;
import com.logicfishsoftware.commons.pde.project.IJavaProjectClasspathProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtext.resource.XtextResourceSet;

@SuppressWarnings("all")
public class ECoreClasspathProvider implements ClasspathProvider<EObject> {
  @Inject
  private IJavaProjectClasspathProvider projectClasspathProvider;
  
  public ClassLoader getClassLoaderFor(final ClassLoader parent, final EObject object) {
    ClassLoader _xblockexpression = null;
    {
      Resource _eResource = object.eResource();
      final ResourceSet resourceSet = _eResource.getResourceSet();
      ClassLoader _switchResult = null;
      boolean _matched = false;
      if (!_matched) {
        if (resourceSet instanceof XtextResourceSet) {
          final XtextResourceSet _xtextResourceSet = (XtextResourceSet)resourceSet;
          _matched=true;
          final Object ctx = _xtextResourceSet.getClasspathURIContext();
          ClassLoader _switchResult_1 = null;
          boolean _matched_1 = false;
          if (!_matched_1) {
            if (ctx instanceof ClassLoader) {
              final ClassLoader _classLoader = (ClassLoader)ctx;
              _matched_1=true;
              _switchResult_1 = _classLoader;
            }
          }
          if (!_matched_1) {
            if (ctx instanceof Class) {
              final Class<?> _class = (Class<?>)ctx;
              _matched_1=true;
              ClassLoader _classLoader = _class.getClassLoader();
              _switchResult_1 = _classLoader;
            }
          }
          if (!_matched_1) {
            if (ctx instanceof IJavaProject) {
              final IJavaProject _iJavaProject = (IJavaProject)ctx;
              _matched_1=true;
              return this.projectClasspathProvider.getClassLoaderFor(parent, _iJavaProject);
            }
          }
          if (!_matched_1) {
            _switchResult_1 = parent;
          }
          return _switchResult_1;
        }
      }
      _xblockexpression = (_switchResult);
    }
    return _xblockexpression;
  }
}
