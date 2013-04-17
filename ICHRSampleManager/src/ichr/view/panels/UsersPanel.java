/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view.panels;

import static ichr.view.main.MainView.*;

import static javax.swing.SpringLayout.*;

import ichr.controller.GetUserNamesController;
import ichr.controller.IsAdminController;
import ichr.model.UserNamesModel;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

/**
 * @author Chris Casola
 * @version Apr 13, 2013
 *
 */
@SuppressWarnings("serial")
public class UsersPanel extends JPanel {

	private final SpringLayout layout;
	
	protected JButton btnNewUser;
	protected JList userList;
	protected UserNamesModel userListModel;
	protected JScrollPane userListScroll;
	protected JButton btnChangePassword;
	protected JButton btnDelete;
	
	public UsersPanel() {
		layout = new SpringLayout();
		setLayout(layout);
		
		constructComponents();
		addComponents();
	}
	
	private void addComponents() {
		// layout the new user button
		layout.putConstraint(NORTH, btnNewUser, VERTICAL_SPACING, NORTH, this);
		layout.putConstraint(WEST, btnNewUser, LABEL_WIDTH, WEST, this);
		
		// layout the user list
		layout.putConstraint(NORTH, userListScroll, SECTION_SPACING, SOUTH, btnNewUser);
		layout.putConstraint(WEST, userListScroll, LABEL_WIDTH, WEST, this);
		layout.putConstraint(EAST, userListScroll, 300, WEST, userListScroll);
		layout.putConstraint(SOUTH, userListScroll, 300, NORTH, userListScroll);
		
		// layout the change password button
		layout.putConstraint(NORTH, btnChangePassword, 0, NORTH, userListScroll);
		layout.putConstraint(WEST, btnChangePassword, VERTICAL_SPACING, EAST, userListScroll);
		
		// layout the delete button
		layout.putConstraint(NORTH, btnDelete, VERTICAL_SPACING, SOUTH, btnChangePassword);
		layout.putConstraint(WEST, btnDelete, VERTICAL_SPACING, EAST, userListScroll);
		layout.putConstraint(EAST, btnDelete, 0, EAST, btnChangePassword);
		
		add(btnNewUser);
		add(userListScroll);
		add(btnChangePassword);
		add(btnDelete);
	}
	
	private void constructComponents() {
		btnNewUser = new JButton("New User");
		
		userListModel = new UserNamesModel();
		userListModel.updateModel(GetUserNamesController.getUserNames());
		userList = new JList(userListModel);
		userListScroll = new JScrollPane(userList);
		
		btnChangePassword = new JButton("Change Password");
		btnDelete = new JButton("Delete User");
		
		if (!IsAdminController.isAdmin()) {
			btnDelete.setEnabled(false);
			btnNewUser.setEnabled(false);
		}
	}
	
	public UserNamesModel getUsersListModel() {
		return userListModel;
	}
	
	public JButton getChangePwdButton() {
		return btnChangePassword;
	}
	
	public JButton getDeleteButton() {
		return btnDelete;
	}
	
	public JList getUsersList() {
		return userList;
	}
}
