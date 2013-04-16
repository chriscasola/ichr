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
import ichr.view.panels.ReceiveSamplesPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Chris Casola
 * @version Apr 16, 2013
 *
 */
public class ReceiveBoxController implements ActionListener {
	
	private final ReceiveSamplesPanel view;
	
	public ReceiveBoxController(ReceiveSamplesPanel view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		addBox(view.getBoxNum().getText(), (String) view.getSupplierBox().getSelectedItem(), (String) view.getSampleType().getSelectedItem());
		addSamples(view.getCensusNum().getText(), 
				view.getPlannedUse().getText(), 
				view.getSampleVolume().getText(), 
				view.getBoxNum().getText(), 
				view.isThawed() ? 1 : 0);
	}

	protected void addSamples(String censusNum, String plannedUse, String sampleVolume,
			String boxNum, int thawCount) {
		PreparedStatement s;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"INSERT INTO samples (sample_id, census_num, planned_use, volume, box_id, thaw_count) VALUES " +
					"(?, ?, ?, ?, ?, ?)"
			);
			
			int numSamples = Integer.parseInt(view.getSampleCount().getText());
			for (int i = 1; i <= numSamples; i++) {
				s.setString(1, boxNum + "_" + String.valueOf(i));
				s.setString(2, censusNum);
				s.setString(3, plannedUse);
				s.setString(4,  sampleVolume);
				s.setString(5, boxNum);
				s.setInt(6, thawCount);
				int result = s.executeUpdate();
				DataStore.getDB().getConnection().commit();
				if (result <= 0) {
					System.err.println("Unable to add box!");
				}
			}
			DataStore.getDB().closeStatement(s);
		}
		catch (SQLException e) {
			System.err.println("Error occurred creating a box!");
			e.printStackTrace();
		}
		catch (ICHRException e) {
			System.err.println("Error occurred creating a box!");
			e.printStackTrace();
		}
	}

	protected void addBox(String boxID, String supplierName, String sampleType) {
		PreparedStatement s;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"INSERT INTO boxes (box_id, supplier_id, sample_type) " +
					"SELECT ? AS box_id, supplier_id, ? AS sample_type " +
					"FROM suppliers " +
					"WHERE supplier_name = ?"
			);
			s.setString(1, boxID);
			s.setString(2, sampleType);
			s.setString(3, supplierName);
			int result = s.executeUpdate();
			DataStore.getDB().getConnection().commit();
			if (result <= 0) {
				System.err.println("Unable to add box!");
			}
			DataStore.getDB().closeStatement(s);
		}
		catch (SQLException e) {
			System.err.println("Error occurred creating a box!");
			e.printStackTrace();
		}
		catch (ICHRException e) {
			System.err.println("Error occurred creating a box!");
			e.printStackTrace();
		}
	}
}
