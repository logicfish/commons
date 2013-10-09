package com.logicfishsoftware.commons.pde.jvm;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jface.action.Action;

import com.logicfishsoftware.commons.pde.log.WorkbenchDialogs;

public class LaunchAction extends Action {

	private JvmLaunch launch;

	public LaunchAction(IVMInstall ivmInstall) {
		this.launch = new JvmLaunch(ivmInstall);
	}
	public LaunchAction() {
		this.launch = new JvmLaunch();
	}

	@Override
	public void run() {
		try {
			launch.launch();
		} catch (CoreException | IOException e) {
			WorkbenchDialogs.errorDialog(e,"Launch Error");
		}
	}


}
