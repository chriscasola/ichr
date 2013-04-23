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
public class DeleteUserController implements ActionListener {

	private final UsersPanel view;
	
	public DeleteUserController(UsersPanel view) {
		this.view = view;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = (String) view.getUsersList().getSelectedValue();
		if (username != null) {
			int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this user?", "Delete User", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				PreparedStatement s = null;
				try {
					s = DataStore.getDB().getPreparedStatement(
							"DELETE FROM users " +
							"WHERE username = ?"
					);
					s.setString(1, username);
					s.executeUpdate();
					DataStore.getDB().getConnection().commit();
					view.getUsersListModel().updateModel(GetUserNamesController.getUserNames());
				}
				catch (ICHRException e1) {
					System.err.println("Error occured deleting user!");
					e1.printStackTrace();
				} catch (SQLException e1) {
					System.err.println("Error occured deleting user!");
					e1.printStackTrace();
				}
				finally {
					DataStore.getDB().closeStatement(s);
				}
			}
		}
	}

}
