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
		// Get user input
		String username = view.getLoginPanel().getUserNameField().getText();
		char[] charPassword = view.getLoginPanel().getPasswordField().getPassword();
		String password = String.copyValueOf(charPassword);
		
		// Check the credentials
		try {
			checkPassword(username, password);
			view.dispose();
		}
		catch (ICHRException e) {
			JOptionPane.showMessageDialog(view, "Invalid login information!", "Invalid Login", JOptionPane.ERROR_MESSAGE);
		}
		
		// Clear password in memory
		for (int i = 0; i < charPassword.length; i++) {
			charPassword[i] = 0;
		}
	}
	
	protected void checkPassword(String username, String password) throws ICHRException {
		PreparedStatement s = null;
		try {
			s = DataStore.getDB().getConnection().prepareStatement("SELECT password FROM users WHERE username=?");
			s.setString(1, username);
			final ResultSet rs = s.executeQuery();
			if (!rs.next() || !rs.getString(1).equals(password)) {
				throw new ICHRException("Login Failed: Incorrect password");
			}
		} catch (SQLException e) {
			throw new ICHRException("Error occurred trying to login", e);
		}
		finally {
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
