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

import static javax.swing.SpringLayout.*;

import static ichr.view.main.MainView.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;

/**
 * @author Chris Casola
 * @version Apr 17, 2013
 *
 */
@SuppressWarnings("serial")
public class ChangePasswordDialog extends JPanel {

	private final SpringLayout layout;
	
	private JLabel lblOldPassword;
	private JPasswordField txtOldPassword;
	private JLabel lblNewPassword;
	private JPasswordField txtNewPassword;
	private JButton btnCancel;
	private JButton btnChange;
	
	private static int LABEL_WIDTH = 120;
	
	public ChangePasswordDialog() {
		layout = new SpringLayout();
		setLayout(layout);
		
		constructComponents();
		addComponents();
	}
	
	private void addComponents() {
		// layout old password
		layout.putConstraint(NORTH, lblOldPassword, SECTION_SPACING, NORTH, this);
		layout.putConstraint(EAST, lblOldPassword, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtOldPassword, 0, VERTICAL_CENTER, lblOldPassword);
		layout.putConstraint(WEST, txtOldPassword, VERTICAL_SPACING, EAST, lblOldPassword);
		
		// layout new password
		layout.putConstraint(NORTH, lblNewPassword, SECTION_SPACING, NORTH, txtOldPassword);
		layout.putConstraint(EAST, lblNewPassword, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtNewPassword, 0, VERTICAL_CENTER, lblNewPassword);
		layout.putConstraint(WEST, txtNewPassword, VERTICAL_SPACING, EAST, lblNewPassword);
		
		// layout buttons
		layout.putConstraint(NORTH, btnCancel, SECTION_SPACING, SOUTH, txtNewPassword);
		layout.putConstraint(NORTH, btnChange, SECTION_SPACING, SOUTH, txtNewPassword);
		layout.putConstraint(EAST, btnCancel, VERTICAL_SPACING, HORIZONTAL_CENTER, this);
		layout.putConstraint(WEST, btnChange, VERTICAL_SPACING, HORIZONTAL_CENTER, this);
		
		layout.putConstraint(EAST, this, VERTICAL_SPACING, EAST, txtNewPassword);
		layout.putConstraint(SOUTH, this, VERTICAL_SPACING, SOUTH, btnChange);
		
		add(lblOldPassword);
		add(txtOldPassword);
		add(lblNewPassword);
		add(txtNewPassword);
		add(btnCancel);
		add(btnChange);
	}
	
	private void constructComponents() {
		lblOldPassword = new JLabel("Old Password: ");
		txtOldPassword = new JPasswordField(15);
		
		lblNewPassword = new JLabel("New Password: ");
		txtNewPassword = new JPasswordField(15);
		
		btnCancel = new JButton("Cancel");
		btnChange = new JButton("Change");
	}
	
	public JButton getCancelButton() {
		return btnCancel;
	}
	
	public JButton getChangeButton() {
		return btnChange;
	}
	
	public JPasswordField getOldPasswordField() {
		return txtOldPassword;
	}
	
	public JPasswordField getNewPasswordField() {
		return txtNewPassword;
	}
	
	public void clearFields() {
		txtOldPassword.setText("");
		txtNewPassword.setText("");
	}
}
