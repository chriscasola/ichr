/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view.login;

import static ichr.view.main.MainView.*;

import static javax.swing.SpringLayout.*; 

import ichr.database.DataStore;
import ichr.view.panels.HeaderPanel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * Panel containing the login form
 * 
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	
	protected JPanel headerPanel;
	protected JLabel lblUserName;
	protected JTextField txtUserName;
	protected JLabel lblPassword;
	protected JPasswordField txtPassword;
	protected JButton btnLogin;
	
	private static final int LABEL_WIDTH = 150;
	
	private final SpringLayout layout;
	
	public LoginPanel() {
		layout = new SpringLayout();
		setLayout(layout);
		
		constructComponents();
		addComponents();
	}
	
	private void addComponents() {
		// layout header panel
		layout.putConstraint(NORTH, headerPanel, 0, NORTH, this);
		layout.putConstraint(WEST, headerPanel, 0, WEST, this);
		layout.putConstraint(EAST, headerPanel, 0, EAST, this);
		layout.putConstraint(SOUTH, headerPanel, 50, NORTH, headerPanel);
		
		// layout username
		layout.putConstraint(NORTH, lblUserName, SECTION_SPACING, SOUTH, headerPanel);
		layout.putConstraint(EAST, lblUserName, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtUserName, 0, VERTICAL_CENTER, lblUserName);
		layout.putConstraint(WEST, txtUserName, 10, EAST, lblUserName);
		
		// layout password
		layout.putConstraint(NORTH, lblPassword, VERTICAL_SPACING, SOUTH, txtUserName);
		layout.putConstraint(EAST, lblPassword, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtPassword, 0, VERTICAL_CENTER, lblPassword);
		layout.putConstraint(WEST, txtPassword, 10, EAST, lblPassword);
		
		// layout login button
		layout.putConstraint(NORTH, btnLogin, SECTION_SPACING, SOUTH, txtPassword);
		layout.putConstraint(WEST, btnLogin, 0, WEST, txtPassword);
		
		layout.putConstraint(EAST, this, SECTION_SPACING, EAST, txtPassword);
		layout.putConstraint(SOUTH, this, SECTION_SPACING, SOUTH, btnLogin);
				
		add(headerPanel);
		add(lblUserName);
		add(txtUserName);
		add(lblPassword);
		add(txtPassword);
		add(btnLogin);
	}
	
	private void constructComponents() {
		headerPanel = new HeaderPanel();
		
		lblUserName = new JLabel("Username: ");
		txtUserName = new JTextField(DataStore.USER_NAME_LEN);
		
		lblPassword = new JLabel("Password: ");
		txtPassword = new JPasswordField(DataStore.PASSWORD_LEN);
		
		btnLogin = new JButton("Login");
	}
	
	/**
	 * @return the username field
	 */
	public JTextField getUserNameField() {
		return txtUserName;
	}
	
	/**
	 * @return the password field
	 */
	public JPasswordField getPasswordField() {
		return txtPassword;
	}
	
	/**
	 * @return the login button
	 */
	public JButton getLoginButton() {
		return btnLogin;
	}
}
