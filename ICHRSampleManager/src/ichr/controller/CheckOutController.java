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
import ichr.view.main.CheckOutPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
public class CheckOutController implements ActionListener {

	public final CheckOutPanel view;
	
	public final JDialog parentWindow;
	
	public CheckOutController(JDialog parentWindow, CheckOutPanel view) {
		this.parentWindow = parentWindow;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			checkOutSample(view.getSampleNumField().getText(), ICHRSampleManager.getUsername());
			parentWindow.setVisible(false);
			parentWindow.dispose();
			
		} catch (ICHRException e1) {
			JOptionPane.showMessageDialog(view, "Unable to checkout that sample!", "Checkout Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void checkOutSample(String sample, String username) throws ICHRException {
		PreparedStatement s;
		// Create a checkout entry in the database
		s = DataStore.getDB().getPreparedStatement("INSERT INTO sample_uses (sample_barcode, username) VALUES (?, ?)");
		try {
			s.setString(1, sample);
			s.setString(2, username);
			s.executeUpdate();
			DataStore.getDB().getConnection().commit();
		}
		catch (SQLException e) {
			throw new ICHRException("Cannot checkout sample: " + sample, e);
		}
	}
}
