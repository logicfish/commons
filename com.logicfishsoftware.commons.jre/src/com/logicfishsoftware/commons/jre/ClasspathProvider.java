package com.logicfishsoftware.commons.jre;

public interface ClasspathProvider<T> {
	ClassLoader getClassLoaderFor(ClassLoader parent, T t);
}
