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
import ichr.ICHRSampleManager;
import ichr.database.DataStore;
import ichr.view.dialogs.ChangePasswordDialog;
import ichr.view.dialogs.ModalDialog;
import ichr.view.panels.UsersPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * @author Chris Casola
 * @version Apr 17, 2013
 *
 */
public class ChangePasswordController implements ActionListener {

	private final UsersPanel view;
	
	public ChangePasswordController(UsersPanel view) {
		this.view = view;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		String selectedUser = (String) view.getUsersList().getSelectedValue();
		if (selectedUser != null && (selectedUser.equals(ICHRSampleManager.getUsername()) ||
				IsAdminController.isAdmin()))
		{
			final ChangePasswordDialog dialog = new ChangePasswordDialog();
			final ModalDialog dialogWindow = new ModalDialog(view, dialog);
			dialogWindow.pack();
			dialogWindow.setMinimumSize(dialogWindow.getPreferredSize());
			dialogWindow.setResizable(false);
			dialog.getCancelButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dialogWindow.setVisible(false);
					dialogWindow.dispose();
				}
			});
			dialog.getChangeButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					changePassword(dialog, dialogWindow);
				}
			});
			dialogWindow.setVisible(true);
		}
		else {
			System.out.println("Don't allow password change");
		}
	}

	private void changePassword(ChangePasswordDialog dialog, ModalDialog dialogWindow) {
		char[] charOldPassword = dialog.getOldPasswordField().getPassword();
		String oldPassword = String.copyValueOf(charOldPassword);
		char[] charNewPassword = dialog.getNewPasswordField().getPassword();
		String newPassword = String.copyValueOf(charNewPassword);
		String username = (String) view.getUsersList().getSelectedValue();
		
		if (oldPasswordMatch(username, oldPassword)) {
			doChangePassword(username, newPassword);
			dialogWindow.setVisible(false);
			dialogWindow.dispose();
		}
		else {
			dialog.clearFields();
			JOptionPane.showMessageDialog(dialogWindow, "The old password you entered is incorrect!", "Password Incorrect!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void doChangePassword(String username, String newPassword) {
		PreparedStatement s = null;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"UPDATE users " +
					"SET password = ? " +
					"WHERE username = ?"
			);
			s.setString(1, newPassword);
			s.setString(2, username);
			s.executeUpdate();
			DataStore.getDB().getConnection().commit();
		}
		catch (ICHRException e) {
			System.err.println("Error occured changing password!");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.err.println("Error occured changing password!");
			e.printStackTrace();
		}
		finally {
			DataStore.getDB().closeStatement(s);
		}
	}
	
	private boolean oldPasswordMatch(String username, String oldPassword) {
		boolean isMatch = false;
		
		PreparedStatement s = null;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"SELECT username " +
					"FROM users " +
					"WHERE username = ? " +
					"AND password = ?"
			);
			s.setString(1, username);
			s.setString(2, oldPassword);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				isMatch = true;
			}
			rs.close();
		}
		catch (ICHRException e) {
			System.err.println("Error occured matching old password.");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.err.println("Error occured matching old password.");
			e.printStackTrace();
		}
		finally {
			DataStore.getDB().closeStatement(s);
		}
		
		return isMatch;
	}
}
