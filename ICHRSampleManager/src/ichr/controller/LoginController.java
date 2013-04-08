/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ichr.ICHRException;
import ichr.ICHRSampleManager;
import ichr.database.DataStore;
import ichr.view.login.LoginView;

/**
 * Controller to handle logging in
 * 
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
public class LoginController implements ActionListener {
	
	private final LoginView view;

	public LoginController(LoginView view) {
		this.view = view;
	}

	/**
	 * Checks the login credentials the user entered
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Check the credentials
		try {
			checkCredentials();
		}
		catch (ICHRException e) {
			JOptionPane.showMessageDialog(view, "Invalid login information!", "Invalid Login", JOptionPane.ERROR_MESSAGE);
		}
		
		// Show the main application window
		ICHRSampleManager.showMainWindow();
		
		// Close the login window
		view.dispose();
	}
	
	protected void checkCredentials() throws ICHRException {
		// Get user input
		String username = view.getLoginPanel().getUserNameField().getText();
		char[] charPassword = view.getLoginPanel().getPasswordField().getPassword();
		String password = String.copyValueOf(charPassword);
		
		// Check the password
		checkPassword(username, password);
		
		// Clear password from memory
		for (int i = 0; i < charPassword.length; i++) {
			charPassword[i] = 0;
		}
	}
	
	protected void checkPassword(String username, String password) throws ICHRException {
		PreparedStatement s = null;
		try {
			// Query the database for the user's password
			s = DataStore.getDB().getPreparedStatement("SELECT password FROM users WHERE username=?");
			s.setString(1, username);
			final ResultSet rs = s.executeQuery();
			
			// Check that the user exists and their password matches
			if (!rs.next() || !rs.getString(1).equals(password)) {
				throw new ICHRException("Login Failed: Incorrect password");
			}
		}
		catch (SQLException e) { // Catch database access errors
			throw new ICHRException("Error occurred trying to login", e);
		}
		finally { // Close the statement
			DataStore.getDB().closeStatement(s);
		}
	}
}
