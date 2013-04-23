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
import ichr.view.login.LoginView;
import ichr.view.main.MainView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

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
	
	private static MainView mainView;
	
	private static long timeLeft = 0;
	
	private static Timer sessionTimer;

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
			e.printStackTrace();
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
				mainView = new MainView(applicationName);
				mainView.pack();
				mainView.setVisible(true);
				mainView.getMainTabPanel().getCheckOutPanel().getSampleNumField().requestFocusInWindow();
				mainView.setMinimumSize(mainView.getSize());
				
				mainView.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						setSessionTime(300000l);
					}
				});
			}
		});
		
		
		if (sessionTimer != null) {
			sessionTimer.cancel();
		}
		setSessionTime(300000l);
		sessionTimer = new Timer();
		sessionTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				decSessionTime(1000l);
				if (getSessionTime() <= 0) {
					showLogin();
					this.cancel();
				}
			}
		}, 1, 1000);
	}
	
	public static void logout() {
		sessionTimer.cancel();
		showLogin();
	}
	
	public static void showLogin() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainView.setVisible(false);
				mainView.dispose();
				final LoginView loginView = new LoginView(applicationName);
				loginView.setVisible(true);
			}
		});
	}
	
	public synchronized static void decSessionTime(long n) {
		timeLeft -= n;
	}
	
	public synchronized static void incSessionTime(long n) {
		timeLeft += n;
	}
	
	public synchronized static void setSessionTime(long n) {
		timeLeft = n;
	}
	
	public synchronized static long getSessionTime() {
		return timeLeft;
	}
	
	public static void setUsername(String username) {
		ICHRSampleManager.username = username;
	}
	
	public static String getUsername() {
		return ICHRSampleManager.username;
	}
}
