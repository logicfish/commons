package com.logicfishsoftware.commons.pde.jvm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMInstallType;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Menu;

public class JvmLaunch {
	private IVMInstall jre;

	boolean toolsJar;
	String[] bootstrapClasspath;
	String[] userClasspath;
	String mainClass;
	String[] vmargs;
	String[] args;
	String configName;
	String workingDir;

	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public boolean isToolsJar() {
		return toolsJar;
	}
	public void setToolsJar(boolean toolsJar) {
		this.toolsJar = toolsJar;
	}
	public String[] getBootstrapClasspath() {
		return bootstrapClasspath;
	}
	public void setBootstrapClasspath(String[] bootstrapClasspath) {
		this.bootstrapClasspath = bootstrapClasspath;
	}
	public String[] getUserClasspath() {
		return userClasspath;
	}
	public void setUserClasspath(String[] userClasspath) {
		this.userClasspath = userClasspath;
	}
	public String getMainClass() {
		return mainClass;
	}
	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}
	public String[] getArgs() {
		return args;
	}
	public void setArgs(String[] args) {
		this.args = args;
	}
	public IContainer getContainer() {
		return container;
	}
	public void setContainer(IContainer container) {
		this.container = container;
	}
	
	public String getWorkingDir() {
		return workingDir;
	}
	public void setWorkingDir(String workingDir) {
		this.workingDir = workingDir;
	}

	public String[] getVmargs() {
		return vmargs;
	}
	public void setVmargs(String[] vmargs) {
		this.vmargs = vmargs;
	}

	private IContainer container;

	public JvmLaunch(IVMInstall ivmInstall) {
		this.jre = ivmInstall;
	}
	public JvmLaunch() {
		this(JavaRuntime.getDefaultVMInstall());
	}
	public void launch() throws CoreException, IOException {
		launch(workingDir,configName,container,jre,bootstrapClasspath,userClasspath,mainClass,vmargs,args);
	}
	public static void launch(
			String dir, 
			String configName, 
			IContainer container, 
			IVMInstall jre, 
			String[] bootstrapClasspath, 
			String[] userClasspath, 
			String mainClass, 
			String[] vmargs,
			String[] args) 
			throws CoreException, IOException {
		File workingDir = new File(dir);

		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType type =
				manager.getLaunchConfigurationType(IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION);
		ILaunchConfiguration[] configurations =
				manager.getLaunchConfigurations(type);
		for (int i = 0; i < configurations.length; i++) {
			ILaunchConfiguration configuration = configurations[i];
			if (configuration.getName().equals(configName)) {
				configuration.delete();
				break;
			}
		}
		ILaunchConfigurationWorkingCopy workingCopy =
				type.newInstance(container, configName);

//		workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_INSTALL_NAME, jre.getName());
//		workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_INSTALL_TYPE, jre.getVMInstallType().getId());	

		File jdkHome = jre.getInstallLocation();
		IPath toolsPath = new Path(jdkHome.getAbsolutePath())
		.append("lib")
		.append("tools.jar");
		IRuntimeClasspathEntry toolsEntry =
				JavaRuntime.newArchiveRuntimeClasspathEntry(toolsPath);
		toolsEntry.setClasspathProperty(IRuntimeClasspathEntry.USER_CLASSES);
		//		IPath bootstrapPath = new Path("TOMCAT_HOME")
		//		.append("bin")
		//		.append("bootstrap.jar");
		//		IRuntimeClasspathEntry bootstrapEntry = 
		//				JavaRuntime.newVariableRuntimeClasspathEntry(bootstrapPath);
		//		bootstrapEntry.setClasspathProperty(IRuntimeClasspathEntry.USER_CLASSES);

		List<String> classpath = new ArrayList<String>();
		classpath.add(toolsEntry.getMemento());

		for(String bootstrapJar : bootstrapClasspath) {
			IPath bootstrapPath = new Path(bootstrapJar);			
			IRuntimeClasspathEntry bootstrapEntry = 
					JavaRuntime.newVariableRuntimeClasspathEntry(bootstrapPath);
			bootstrapEntry.setClasspathProperty(IRuntimeClasspathEntry.USER_CLASSES);
			classpath.add(bootstrapEntry.getMemento());
		}

		List<String> userJars = new ArrayList<String>();
		for(String path : userClasspath) {
			URI uri = URI.createURI(path);
			URL url = FileLocator.toFileURL(new URL(uri.toString()));
			String jarPath = url.getPath();
			IPath compilerPath = new Path(jarPath);
			IRuntimeClasspathEntry compilerEntry =
					JavaRuntime.newArchiveRuntimeClasspathEntry(compilerPath);
			compilerEntry.setClasspathProperty(IRuntimeClasspathEntry.USER_CLASSES);
			userJars.add(compilerEntry.getMemento());
		}

		IPath systemLibsPath = new Path(JavaRuntime.JRE_CONTAINER);
		IRuntimeClasspathEntry systemLibsEntry =
				JavaRuntime.newRuntimeContainerClasspathEntry(systemLibsPath,
						IRuntimeClasspathEntry.STANDARD_CLASSES);

		for (String jar : userJars) {
			classpath.add(jar);
		}
		classpath.add(systemLibsEntry.getMemento());
		workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH, classpath);
		workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_DEFAULT_CLASSPATH, false);

		workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,
				StringUtils.join(vmargs," "));

		workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME,
				mainClass);

		workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS,
				StringUtils.join(args," "));

		workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_WORKING_DIRECTORY,
				workingDir.getAbsolutePath());

		ILaunchConfiguration configuration = workingCopy.doSave();
		DebugUITools.launch(configuration, ILaunchManager.RUN_MODE);
	}

	public static void menu(Menu menu) {
		IVMInstallType[] types = JavaRuntime.getVMInstallTypes();
		for (int i = 0; i < types.length; i++) {
			IVMInstallType type = types[i];
			IVMInstall[] jres = type.getVMInstalls();
			for (int j = 0; j < jres.length; j++) {
				IAction action = new LaunchAction(jres[j]);
				ActionContributionItem item = new ActionContributionItem(action);
				item.fill(menu, -1);
			}
		}

	}

}
