/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr;

import ichr.database.DataStore;
import ichr.view.MainView;
import ichr.view.login.LoginView;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

/**
 * The main runner class for the Sample Manager application
 * 
 * @author Chris Casola
 * @version Apr 7, 2013
 *
 */
public class ICHRSampleManager {

	public static final String applicationName = "ICHR Sample Manager";
	
	private static String username = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Open database connection and create tables if necessary
		
		try {
			DataStore.getDB().openConnection();
			
			if (args.length > 0 && args[0].equals("-init")) {
				DataStore.getDB().createTables();
			}
		}
		catch (SQLException e) { // exit if unable to open database connection
			System.err.println("Could not open database connection!");
			System.exit(1);
		}

		// Start the GUI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final LoginView loginView = new LoginView(applicationName);
				loginView.setVisible(true);
			}
		});
	}

	public static void showMainWindow() {
		// Start the GUI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final MainView mainView = new MainView(applicationName);
				mainView.pack();
				mainView.setVisible(true);
				mainView.setMinimumSize(mainView.getSize());
			}
		});
	}
	
	public static void setUsername(String username) {
		ICHRSampleManager.username = username;
	}
	
	public static String getUsername() {
		return ICHRSampleManager.username;
	}
}
