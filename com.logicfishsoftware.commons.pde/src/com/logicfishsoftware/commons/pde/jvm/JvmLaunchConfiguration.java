package com.logicfishsoftware.commons.pde.jvm;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;

/**
 *    <extension
 *         point="org.eclipse.debug.core.launchConfigurationTypes">
 *     <launchConfigurationType
 *           delegate="com.logicfishsoftware.commons.pde.jvm.JvmLaunchConfiguration"
 *           id="jvmLaunchConfiguration"
 *           modes="run"
 *           name=".....">
 *     </launchConfigurationType>
 *  </extension>
 * @author logicfish
 *
 */
public class JvmLaunchConfiguration implements
		ILaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub

	}

}
