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

import ichr.database.DataStore;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Panel containing the login form
 * 
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	
	protected final JTextField txtUserName;
	
	protected final JPasswordField txtPassword;
	
	protected final JButton btnLogin;
	
	public static final int LABEL_SPACING = 15; //px

	public LoginPanel() {
		// Construct fields
		txtUserName = new JTextField(DataStore.USER_NAME_LEN);
		txtPassword = new JPasswordField(DataStore.PASSWORD_LEN);
		txtUserName.setMaximumSize(txtUserName.getPreferredSize());
		txtPassword.setMaximumSize(txtPassword.getPreferredSize());
		btnLogin = new JButton("Login");
		
		// Create inner panels
		final JPanel labelPanel = new JPanel();
		final JPanel fieldPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.PAGE_AXIS));
		
		// Set layout of login panel
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		// Add labels
		labelPanel.add(new JLabel("Username:"));
		labelPanel.add(Box.createVerticalStrut(LABEL_SPACING));
		labelPanel.add(new JLabel("Password:"));
		fieldPanel.add(Box.createVerticalGlue());
		add(Box.createHorizontalGlue());
		add(labelPanel);

		// Leave space between labels and fields
		add(Box.createHorizontalStrut(10));
		
		// Add fields
		fieldPanel.add(txtUserName);
		fieldPanel.add(txtPassword);
		fieldPanel.add(Box.createVerticalStrut(20));
		fieldPanel.add(btnLogin);
		fieldPanel.add(Box.createVerticalGlue());
		add(fieldPanel);
		add(Box.createHorizontalGlue());
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
