/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view;

import static ichr.view.MainTabView.*;
import ichr.controller.AddSampleTypeController;
import ichr.controller.GetSampleTypesController;
import ichr.controller.RemoveSampleTypeController;
import ichr.model.SampleTypesModel;

import static javax.swing.SpringLayout.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

/**
 * @author Chris Casola
 * @version Apr 16, 2013
 *
 */
@SuppressWarnings("serial")
public class ManageSampleTypesDialog extends JPanel {
	
	private final SpringLayout layout;
	
	protected JLabel lblAddSample;
	protected JList lstSampleTypes;
	protected SampleTypesModel lstSampleTypesModel;
	protected JScrollPane lstSampleTypesScroll;
	protected JTextField txtNewSampleType;
	protected JButton btnAddSampleType;
	protected JButton btnRemoveSampleType;
	
	public ManageSampleTypesDialog() {
		layout = new SpringLayout();
		setLayout(layout);
		
		constructComponents();
		addComponents();
	}
	
	private void addComponents() {
		// layout add types label
		layout.putConstraint(NORTH, lblAddSample, VERTICAL_SPACING, NORTH, this);
		layout.putConstraint(WEST, lblAddSample, VERTICAL_SPACING, WEST, this);
		
		// layout the sample types list
		layout.putConstraint(NORTH, lstSampleTypesScroll, VERTICAL_SPACING, SOUTH, lblAddSample);
		layout.putConstraint(WEST, lstSampleTypesScroll, VERTICAL_SPACING, WEST, this);
		layout.putConstraint(EAST, lstSampleTypesScroll, 250, WEST, lstSampleTypesScroll);
		layout.putConstraint(SOUTH, lstSampleTypesScroll, 275, NORTH, lstSampleTypesScroll);
		
		// layout the text field
		layout.putConstraint(NORTH, txtNewSampleType, VERTICAL_SPACING, SOUTH, lstSampleTypesScroll);
		layout.putConstraint(WEST, txtNewSampleType, VERTICAL_SPACING, WEST, this);
		layout.putConstraint(EAST, txtNewSampleType, VERTICAL_SPACING * -1, WEST, btnAddSampleType);
		
		// layout the add button
		layout.putConstraint(VERTICAL_CENTER, btnAddSampleType, 0, VERTICAL_CENTER, txtNewSampleType);
		layout.putConstraint(EAST, btnAddSampleType, 0, EAST, lstSampleTypesScroll);
		
		// layout the remove button
		layout.putConstraint(NORTH, btnRemoveSampleType, 0, NORTH, lstSampleTypesScroll);
		layout.putConstraint(WEST, btnRemoveSampleType, VERTICAL_SPACING, EAST, lstSampleTypesScroll);
		
		layout.putConstraint(EAST, this, VERTICAL_SPACING, EAST, btnRemoveSampleType);
		layout.putConstraint(SOUTH, this, VERTICAL_SPACING, SOUTH, btnAddSampleType);
		
		add(lblAddSample);
		add(lstSampleTypesScroll);
		add(txtNewSampleType);
		add(btnAddSampleType);
		add(btnRemoveSampleType);
	}
	
	private void constructComponents() {
		lblAddSample = new JLabel("Sample Types:");
		
		lstSampleTypesModel = new SampleTypesModel();
		lstSampleTypesModel.updateModel(new GetSampleTypesController().getTypes());
		lstSampleTypes = new JList(lstSampleTypesModel);
		lstSampleTypes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstSampleTypesScroll = new JScrollPane(lstSampleTypes);
		
		txtNewSampleType = new JTextField(20);
		
		btnAddSampleType = new JButton("Add");
		btnAddSampleType.addActionListener(new AddSampleTypeController(this));
		
		btnRemoveSampleType = new JButton("Remove");
		btnRemoveSampleType.addActionListener(new RemoveSampleTypeController(this));
	}
	
	public JTextField getNewTypeField() {
		return txtNewSampleType;
	}
	
	public SampleTypesModel getSampleTypesModel() {
		return lstSampleTypesModel;
	}
	
	public JList getSampleTypesList() {
		return lstSampleTypes;
	}
}
