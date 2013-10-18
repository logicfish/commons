package com.logicfishsoftware.commons.xtend;

import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

public class Synchronization {
	public static <T,U> U synchronize(T o,Function1<T,? extends U> func) {
		synchronized (o) {
			return func.apply(o);
		}
	}
	public static <T> void synchronize(T o,Procedure1<T> func) {
		synchronized (o) {
			func.apply(o);
		}
	}
}
