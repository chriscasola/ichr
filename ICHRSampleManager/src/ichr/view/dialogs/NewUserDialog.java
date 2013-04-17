/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view.dialogs;

import static ichr.view.main.MainView.*;

import static javax.swing.SpringLayout.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * @author Chris Casola
 * @version Apr 17, 2013
 *
 */
@SuppressWarnings("serial")
public class NewUserDialog extends JPanel {

	private final SpringLayout layout;
	
	private JLabel lblTitle;
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JLabel lblFirstName;
	private JTextField txtFirstName;
	private JLabel lblLastName;
	private JTextField txtLastName;
	private JButton btnAddUser;
	private JButton btnCancel;
	
	private static final int LABEL_WIDTH = 100;
	
	public NewUserDialog() {
		layout = new SpringLayout();
		setLayout(layout);
		
		constructComponents();
		addComponents();
	}
	
	private void addComponents() {
		// layout title
		layout.putConstraint(NORTH, lblTitle, VERTICAL_SPACING, NORTH, this);
		layout.putConstraint(WEST, lblTitle, VERTICAL_SPACING, WEST, this);
		
		// layout username
		layout.putConstraint(NORTH, lblUsername, SECTION_SPACING, SOUTH, lblTitle);
		layout.putConstraint(EAST, lblUsername, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtUsername, 0, VERTICAL_CENTER, lblUsername);
		layout.putConstraint(WEST, txtUsername, VERTICAL_SPACING, EAST, lblUsername);
		
		// layout password
		layout.putConstraint(NORTH, lblPassword, VERTICAL_SPACING, SOUTH, txtUsername);
		layout.putConstraint(EAST, lblPassword, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtPassword, 0, VERTICAL_CENTER, lblPassword);
		layout.putConstraint(WEST, txtPassword, VERTICAL_SPACING, EAST, lblPassword);
		
		// layout first name
		layout.putConstraint(NORTH, lblFirstName, VERTICAL_SPACING, SOUTH, txtPassword);
		layout.putConstraint(EAST, lblFirstName, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtFirstName, 0, VERTICAL_CENTER, lblFirstName);
		layout.putConstraint(WEST, txtFirstName, VERTICAL_SPACING, EAST, lblFirstName);
		
		// layout last name
		layout.putConstraint(NORTH, lblLastName, VERTICAL_SPACING, SOUTH, txtFirstName);
		layout.putConstraint(EAST, lblLastName, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtLastName, 0, VERTICAL_CENTER, lblLastName);
		layout.putConstraint(WEST, txtLastName, VERTICAL_SPACING, EAST, lblLastName);
		
		// layout buttons
		layout.putConstraint(NORTH, btnCancel, SECTION_SPACING, SOUTH, txtLastName);
		layout.putConstraint(EAST, btnCancel, -10, HORIZONTAL_CENTER, this);
		layout.putConstraint(NORTH, btnAddUser, 0, NORTH, btnCancel);
		layout.putConstraint(WEST, btnAddUser, 10, HORIZONTAL_CENTER, this);
		
		layout.putConstraint(EAST, this, VERTICAL_SPACING, EAST, txtFirstName);
		layout.putConstraint(SOUTH, this, VERTICAL_SPACING, SOUTH, btnAddUser);
		
		add(lblTitle);
		add(lblUsername);
		add(txtUsername);
		add(lblPassword);
		add(txtPassword);
		add(lblFirstName);
		add(txtFirstName);
		add(lblLastName);
		add(txtLastName);
		add(btnCancel);
		add(btnAddUser);
	}
	
	private void constructComponents() {
		lblTitle = new JLabel("Create a User");
		
		lblUsername = new JLabel("Username: ");
		txtUsername = new JTextField(10);
		
		lblPassword = new JLabel("Password: ");
		txtPassword = new JPasswordField(10);
		
		lblFirstName = new JLabel("First Name: ");
		txtFirstName = new JTextField(15);
		
		lblLastName = new JLabel("Last Name: ");
		txtLastName = new JTextField(15);
		
		btnAddUser = new JButton("Add User");
		btnCancel = new JButton("Cancel");
	}
	
	public JTextField getUserNameField() {
		return txtUsername;
	}
	
	public JPasswordField getPasswordField() {
		return txtPassword;
	}
	
	public JTextField getFirstNameField() {
		return txtFirstName;
	}
	
	public JTextField getLastNameField() {
		return txtLastName;
	}
	
	public JButton getAddUserButton() {
		return btnAddUser;
	}
}
