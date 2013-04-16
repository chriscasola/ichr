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
import ichr.view.ManageSampleTypesDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Chris Casola
 * @version Apr 16, 2013
 *
 */
public class AddSampleTypeController implements ActionListener {
	
	private final ManageSampleTypesDialog view;
	
	public AddSampleTypeController(ManageSampleTypesDialog view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		PreparedStatement s;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"INSERT INTO sample_types (sample_type_desc) VALUES " +
					"(?)"
			);
			s.setString(1, view.getNewTypeField().getText());
			int result = s.executeUpdate();
			DataStore.getDB().getConnection().commit();
			if (result <= 0) {
				System.err.println("Unable to add sample type!");
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
		
		view.getSampleTypesModel().updateModel(new GetSampleTypesController().getTypes());
		view.getNewTypeField().setText("");
		view.getNewTypeField().requestFocusInWindow();
	}
}
