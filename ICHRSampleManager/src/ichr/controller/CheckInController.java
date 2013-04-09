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
import ichr.view.main.CheckInPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
public class CheckInController implements ActionListener {
	
	public final CheckInPanel view;
	
	public final JDialog parentWindow;
	
	public CheckInController(JDialog parentWindow, CheckInPanel view) {
		this.view = view;
		this.parentWindow = parentWindow;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			checkInSample(view.getSampleNumField().getText(), ICHRSampleManager.getUsername());
			parentWindow.setVisible(false);
			parentWindow.dispose();
		} catch (ICHRException e) {
			JOptionPane.showMessageDialog(parentWindow, "Cannot check in this sample!", "Sample Check In Error!", JOptionPane.ERROR_MESSAGE);
			if (e.getCause() != null) {
				System.err.println(e.getCause().getMessage());
			}
		}
	}

	protected void checkInSample(String sample, String username) throws ICHRException {
		PreparedStatement s;
		// Create a checkout entry in the database
		s = DataStore.getDB().getPreparedStatement(
				"UPDATE sample_uses SET time_in=? " +
				"WHERE sample_barcode=? AND username=? AND time_in='0000-00-00 00:00:00'"
		);
		try {
			s.setDate(1, new Date(System.currentTimeMillis()));
			s.setString(2, sample);
			s.setString(3, username);
			final int updates = s.executeUpdate();
			DataStore.getDB().getConnection().commit();
			if (updates < 1) {
				throw new ICHRException("Sample cannot be checked in!");
			}
		}
		catch (SQLException e) {
			throw new ICHRException("Cannot checkin sample: " + sample, e);
		}
	}
}
