package com.logicfishsoftware.commons.pde.log;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.internal.Workbench;

@SuppressWarnings("restriction")
public class WorkbenchDialogs {
	public static void errorDialog(Throwable e,String msg) {
		MessageDialog.openError(Workbench.getInstance().getActiveWorkbenchWindow().getShell(), msg, e.getMessage());		
	}
	public static void infoDialog(String title,String msg) {
		MessageDialog.openInformation(Workbench.getInstance().getActiveWorkbenchWindow().getShell(), title, msg);		
	}
	public static boolean confirmDialog(String title,String msg) {
		return MessageDialog.openConfirm(Workbench.getInstance().getActiveWorkbenchWindow().getShell(), title, msg);		
	}
	public static boolean questionDialog(String title,String msg) {
		return MessageDialog.openQuestion(Workbench.getInstance().getActiveWorkbenchWindow().getShell(), title, msg);		
	}

}
