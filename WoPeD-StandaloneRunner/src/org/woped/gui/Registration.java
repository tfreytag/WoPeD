package org.woped.gui;

import java.io.IOException;

import org.woped.translations.Messages;

public class Registration {

	public Registration() {

	}

	public String[] getOS(){
		String[] osDetails = new String[3];
		osDetails[0] = System.getProperty("os.name");
		osDetails[1] = System.getProperty("java.version");
		osDetails[2] = Messages.getString("Application.Version");
		return osDetails;
	}
	
}