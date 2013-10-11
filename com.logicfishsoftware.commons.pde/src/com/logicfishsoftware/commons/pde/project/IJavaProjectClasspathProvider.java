package com.logicfishsoftware.commons.pde.project;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;

import com.logicfishsoftware.commons.jre.ClasspathProvider;

@SuppressWarnings("restriction")
public class IJavaProjectClasspathProvider implements ClasspathProvider<IJavaProject> {
	private static URL[] resolveProject(IJavaProject javaProject)
	{
		if (javaProject == null) return null;
		Set<IJavaProject> visited = new HashSet<IJavaProject>();
		List<URL> urls = new ArrayList<URL>(20);
		collectClasspathURLs(javaProject, urls, visited, true);
		URL[] result = new URL[urls.size()];
		urls.toArray(result);
		return result;
	}

	private static void collectClasspathURLs(IJavaProject javaProject,
			List<URL> urls, Set<IJavaProject> visited, boolean isFirstProject) {
		if (visited.contains(javaProject)) return;
		visited.add(javaProject);
		IPath outPath;
		try {
			outPath = getJavaProjectOutputAbsoluteLocation(javaProject);
		} catch (IllegalStateException | JavaModelException e) {
			e.printStackTrace(System.out);
			return;
		}
		outPath = outPath.addTrailingSeparator();
		URL out = createFileURL(outPath);
		urls.add(out);
		IClasspathEntry[] entries = null;
		try {
			entries = javaProject.getResolvedClasspath(true);
		} catch (JavaModelException e) {
			e.printStackTrace(System.out);
			return;
		}
		IClasspathEntry entry, resEntry;
		for (int i = 0; i < entries.length; i++) {
			entry = entries[i];
			switch (entry.getEntryKind()) {
			case IClasspathEntry.CPE_LIBRARY :
			case IClasspathEntry.CPE_CONTAINER :
			case IClasspathEntry.CPE_VARIABLE :
				collectClasspathEntryURL(entry, urls);
				break;
			case IClasspathEntry.CPE_PROJECT : {
				if (isFirstProject || entry.isExported())
					collectClasspathURLs(getJavaProject(entry), urls, visited, false);
				break;
			}
			}
		}
	}

	private static URL createFileURL(IPath path) {
		URL url = null;
		try {
			if(new File(path.toOSString()).isDirectory()) {
				url = new URL("file://" + path.addTrailingSeparator().toOSString());				
			} else {
				url = new URL("file://" + path.toOSString());				
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}


	private static void collectClasspathEntryURL(IClasspathEntry entry,
			List<URL> urls) {
		URL url = createFileURL(entry.getPath());
		if (url != null)
			urls.add(url);
	}

	private static IJavaProject getJavaProject(IClasspathEntry entry) {
		IProject proj =
				ResourcesPlugin.getWorkspace().getRoot().getProject(entry.getPath().segment(0));
		if (proj != null)
			return getJavaProject(proj);
		return null;
	}

	private static IJavaProject getJavaProject(IProject proj) {
		if(JavaProject.hasJavaNature(proj)) {
			return JavaCore.create(proj);
		}
		return null;
	}

	private static IPath getJavaProjectOutputAbsoluteLocation(IJavaProject project) throws IllegalStateException, JavaModelException {
//		return project.getProject().getLocation();
		return Platform.getLocation().append(project.getOutputLocation());

	}

	@Override
	public ClassLoader getClassLoaderFor(ClassLoader parent,IJavaProject t) {
		return new URLClassLoader(resolveProject(t),parent);
	}
}
