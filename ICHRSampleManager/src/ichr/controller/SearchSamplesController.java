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
import ichr.view.panels.SearchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JRadioButton;

/**
 * @author Chris Casola
 * @version Apr 16, 2013
 *
 */
public class SearchSamplesController implements ActionListener {
	
	private final SearchPanel view;
	
	public SearchSamplesController(SearchPanel view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		final String fieldName = getFieldName(view.getSelectedButton());
		getData(fieldName, view.getQueryField().getText());
	}
	
	protected void getData(String fieldName, String condition) {
		PreparedStatement s;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"SELECT sample_id AS 'Sample ID', census_num AS 'Census Number', sample_type AS 'Sample Type', planned_use AS 'Planned Use', volume AS 'Volume', boxes.box_id AS 'Box ID', thaw_count AS 'Thaw Count', supplier_name AS 'Supplier' " +
					"FROM samples, boxes, suppliers " +
					"WHERE samples.box_id = boxes.box_id " +
					"AND boxes.supplier_id = suppliers.supplier_id " +
					"AND " + fieldName + " LIKE ?"
			);
			s.setString(1, "%" + condition + "%");
			final ResultSet rs = s.executeQuery();
			view.getSamplesTableModel().updateTable(rs);
			rs.close();
			DataStore.getDB().closeStatement(s);
		}
		catch (SQLException e) {
			System.err.println("Error occurred retrieving samples!");
			e.printStackTrace();
		}
		catch (ICHRException e) {
			System.err.println("Error occurred retrieving samples!");
			e.printStackTrace();
		}
	}

	public void getData() {
		PreparedStatement s;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"SELECT sample_id AS 'Sample ID', census_num AS 'Census Number', sample_type AS 'Sample Type', planned_use AS 'Planned Use', volume AS 'Volume', boxes.box_id AS 'Box ID', thaw_count AS 'Thaw Count', supplier_name AS 'Supplier' " +
					"FROM samples, boxes, suppliers " +
					"WHERE samples.box_id = boxes.box_id " +
					"AND boxes.supplier_id = suppliers.supplier_id"
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
	
	private String getFieldName(JRadioButton button) {
		String text = button.getText();
		String fieldName = "samples.sample_id";
		if (text.equals("Sample Number")) {
			fieldName = "samples.sample_id";
		}
		else if (text.equals("Box Number")) {
			fieldName = "boxes.box_id";
		}
		else if (text.equals("Census Number")) {
			fieldName = "samples.census_num";
		}
		else if (text.equals("Sample Type")) {
			fieldName = "boxes.sample_type";
		}
		else if (text.equals("Supplier Name")) {
			fieldName = "suppliers.supplier_name";
		}
		
		return fieldName;
	}
}
