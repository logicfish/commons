package com.logicfishsoftware.commons.pde.log;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

public class PlatformLog {
	public static void warn(String id,Exception e) {
		try {
			Platform.getLog(Platform.getBundle(id)).log(new Status(IStatus.WARNING,id,e.getMessage(),e));
		} catch (Throwable throwable) {
		}
	}
	public static void warn(String id,String msg) {
		try {
			Platform.getLog(Platform.getBundle(id)).log(new Status(IStatus.WARNING,id,msg));
		} catch (Throwable throwable) {
		}
	}

	public static void error(String id,Exception e) {
		try {
			Platform.getLog(Platform.getBundle(id)).log(new Status(IStatus.ERROR,id,e.getMessage(),e));
		} catch (Throwable throwable) {
		}
	}

	public static void log(String id,String msg) {
		try {
			Platform.getLog(Platform.getBundle(id)).log(new Status(IStatus.INFO,id,msg));
		} catch (Throwable throwable) {
		}
	}
	
}
