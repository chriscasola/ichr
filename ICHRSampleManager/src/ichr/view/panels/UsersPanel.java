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

import static ichr.view.MainView.*;

import static javax.swing.SpringLayout.*;

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
	protected JScrollPane userListScroll;
	protected JButton btnEdit;
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
		
		// layout the edit button
		layout.putConstraint(NORTH, btnEdit, 0, NORTH, userListScroll);
		layout.putConstraint(WEST, btnEdit, VERTICAL_SPACING, EAST, userListScroll);
		layout.putConstraint(EAST, btnEdit, 0, EAST, btnDelete);
		
		// layout the delete button
		layout.putConstraint(NORTH, btnDelete, VERTICAL_SPACING, SOUTH, btnEdit);
		layout.putConstraint(WEST, btnDelete, VERTICAL_SPACING, EAST, userListScroll);
		
		add(btnNewUser);
		add(userListScroll);
		add(btnEdit);
		add(btnDelete);
	}
	
	private void constructComponents() {
		btnNewUser = new JButton("New User");
		
		// dummy data
		Object[] users = {"Chris", "Dongni", "Silvia", "Steve"};
		userList = new JList(users);
		userListScroll = new JScrollPane(userList);
		
		btnEdit = new JButton("Edit User");
		
		btnDelete = new JButton("Delete User");
	}
}
