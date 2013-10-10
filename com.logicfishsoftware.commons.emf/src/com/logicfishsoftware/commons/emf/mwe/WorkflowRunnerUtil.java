package com.logicfishsoftware.commons.emf.mwe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.mwe2.language.Mwe2StandaloneSetup;
import org.eclipse.emf.mwe2.launch.runtime.Mwe2Launcher;

import com.google.inject.Injector;

final public class WorkflowRunnerUtil {
	public static class WorkflowRunnerUtilException extends Exception {
		public WorkflowRunnerUtilException(Throwable cause) {
			super(cause);
		}
	}
	
	static {
		Injector injector = new Mwe2StandaloneSetup().createInjectorAndDoEMFRegistration();
	}

	public static <T,K,V> void run(T workflow,Map<K,V> param) throws WorkflowRunnerUtilException {
		List<String> arg = new ArrayList<String>();
		arg.add("" + workflow);
		if(param!=null) {
			for (K parameter : param.keySet()) {
				arg.add("-p");
				arg.add(parameter + "=" + param.get(parameter));
			}
		}
		try {
			new Mwe2Launcher().run(arg.toArray(new String[arg.size()]));
		} catch (Throwable e) {
			throw new WorkflowRunnerUtilException(e);
		}
	}
}
