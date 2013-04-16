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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
public class CheckOutController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

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
