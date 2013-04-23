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

import ichr.ICHRException;
import ichr.database.DataStore;
import ichr.view.dialogs.ModalDialog;
import ichr.view.dialogs.NewUserDialog;
import ichr.view.panels.UsersPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * @author Chris Casola
 * @version Apr 17, 2013
 *
 */
public class AddUserController implements ActionListener {
	
	private final UsersPanel view;
	
	public AddUserController(UsersPanel view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		final NewUserDialog dialog = new NewUserDialog();
		final ModalDialog dialogWrapper = new ModalDialog(view, dialog);
		dialog.getAddUserButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = dialog.getUserNameField().getText();
				char[] charPassword = dialog.getPasswordField().getPassword();
				String password = String.valueOf(charPassword);
				String firstName = dialog.getFirstNameField().getText();
				String lastName = dialog.getLastNameField().getText();
				String[] fields = {username, password, firstName, lastName};
				if (checkFields(fields)) {
					if (doAddUser(username, password, firstName, lastName)) {
						dialogWrapper.setVisible(false);
						dialogWrapper.dispose();
					}
					else {
						JOptionPane.showMessageDialog(dialogWrapper, "This user already exists!", "Unable to Add User", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		dialog.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogWrapper.setVisible(false);
				dialogWrapper.dispose();
			}
		});
		
		dialog.getUserNameField().requestFocusInWindow();
		dialogWrapper.pack();
		dialogWrapper.setMinimumSize(dialogWrapper.getPreferredSize());
		dialogWrapper.getRootPane().setDefaultButton(dialog.getAddUserButton());
		dialogWrapper.setResizable(false);
		dialogWrapper.setVisible(true);
		view.getUsersListModel().updateModel(GetUserNamesController.getUserNames());
	}
	
	private boolean doAddUser(String username, String password, String firstName, String lastName) {
		boolean isSuccessful = false;
		PreparedStatement s = null;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"INSERT INTO contacts (first, last) VALUES (?, ?)"
			);
			s.setString(1, firstName);
			s.setString(2, lastName);
			int result = s.executeUpdate();
			s.close();
			if (result > 0) {
				s = DataStore.getDB().getPreparedStatement(
						"INSERT INTO users (username, password, role, contact_id) VALUES " +
						"(?, ?, ?, (SELECT contact_id FROM contacts WHERE first=? AND last=?))"
				);
				s.setString(1, username);
				s.setString(2, password);
				s.setString(3, "tech");
				s.setString(4, firstName);
				s.setString(5, lastName);
				s.executeUpdate();
				DataStore.getDB().getConnection().commit();
				isSuccessful = true;
			}
		}
		catch (ICHRException e) {
			System.err.println("Error occurred adding new user.");
			try {
				DataStore.getDB().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.err.println("Error occurred adding new user.");
			try {
				DataStore.getDB().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			DataStore.getDB().closeStatement(s);
		}
		
		return isSuccessful;
	}

	private boolean checkFields(String[] fields) {
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].length() <= 0) {
				return false;
			}
		}
		return true;
	}
}
