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
import ichr.view.dialogs.ManageSampleTypesDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * @author Chris Casola
 * @version Apr 16, 2013
 *
 */
public class RemoveSampleTypeController implements ActionListener {
	
	private final ManageSampleTypesDialog view;
	
	public RemoveSampleTypeController(ManageSampleTypesDialog view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		PreparedStatement s;
		String sampleType = (String) view.getSampleTypesList().getSelectedValue();
		
		if (sampleType != null) {
			try {
				s = DataStore.getDB().getPreparedStatement(
						"DELETE FROM sample_types WHERE sample_type_desc = ?");
				s.setString(1, sampleType);
				int result = s.executeUpdate();
				DataStore.getDB().getConnection().commit();
				if (result <= 0) {
					System.err.println("Unable to remove sample type!");
				}
				DataStore.getDB().closeStatement(s);
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(view, "You cannot remove a sample type that is currently assigned to a sample.", "Cannot Remove Sample Type", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (ICHRException e) {
				System.err.println("Error removing sample type!");
				e.printStackTrace();
			}
			
			view.getSampleTypesModel().updateModel(new GetSampleTypesController().getTypes());
		}
	}

}
