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
import ichr.view.main.BrowseSamplesPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
public class BrowseSamplesController implements ActionListener {
	
	private final BrowseSamplesPanel view;
	
	public BrowseSamplesController(BrowseSamplesPanel view) {
		this.view = view;
		refreshSamples();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		refreshSamples();
	}

	public void refreshSamples() {
		PreparedStatement s;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"SELECT samples.sample_barcode, sample_name, username, time_out, time_in " +
					"FROM samples " +
					"LEFT JOIN sample_uses " +
					"ON samples.sample_barcode = sample_uses.sample_barcode"
			);
			final ResultSet rs = s.executeQuery();
			view.getSamplesTableModel().updateTable(rs);
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("Error occurred retrieving samples!");
		}
		catch (ICHRException e) {
			System.err.println("Error occurred retrieving samples!");
		}
	}
}
