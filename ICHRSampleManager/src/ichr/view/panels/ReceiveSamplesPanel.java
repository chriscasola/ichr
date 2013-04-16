/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view.panels;

import static ichr.view.main.MainView.*;

import static javax.swing.SpringLayout.*;

import ichr.controller.GetSampleTypesController;
import ichr.controller.GetSupplierController;
import ichr.model.SampleTypesModel;
import ichr.view.dialogs.ManageSampleTypesDialog;
import ichr.view.dialogs.ModalDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * @author Chris Casola
 * @version Apr 13, 2013
 *
 */
@SuppressWarnings("serial")
public class ReceiveSamplesPanel extends JPanel {
	
	private final SpringLayout layout;
	
	protected JLabel lblBoxNum;
	protected JTextField txtBoxNum;
	protected JLabel lblCensusNum;
	protected JTextField txtCensusNum;
	protected JLabel lblSupplier;
	protected JComboBox cmbSupplier;
	protected JLabel lblSampleType;
	protected JComboBox cmbSampleType;
	protected SampleTypesModel cmbSampleTypeModel;
	protected JButton btnManageSampleTypes;
	protected JLabel lblNumSamples;
	protected JTextField txtNumSamples;
	protected JLabel lblSampleVolume;
	protected JTextField txtSampleVolume;
	protected JLabel lblThawed;
	protected ButtonGroup thawedGroup;
	protected JRadioButton btnThawedYes;
	protected JRadioButton btnThawedNo;
	protected JLabel lblPlannedUse;
	protected JTextArea txtPlannedUse;
	protected JScrollPane txtPlannedUseScroll;
	protected JLabel lblFreezerAssignment;
	protected JLabel lblFreezerDesc;
	protected JLabel lblFreezerCell;
	protected JButton btnAdd;

	public ReceiveSamplesPanel() {
		layout = new SpringLayout();
		setLayout(layout);
		
		constructComponents();
		addComponents();
	}
	
	private void addComponents() {
		// layout box num
		layout.putConstraint(NORTH, lblBoxNum, 10, NORTH, this);
		layout.putConstraint(EAST, lblBoxNum, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtBoxNum, 0, VERTICAL_CENTER, lblBoxNum);
		layout.putConstraint(WEST, txtBoxNum, 10, EAST, lblBoxNum);
		
		// layout census num
		layout.putConstraint(NORTH, lblCensusNum, SECTION_SPACING, SOUTH, lblBoxNum);
		layout.putConstraint(EAST, lblCensusNum, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtCensusNum, 0, VERTICAL_CENTER, lblCensusNum);
		layout.putConstraint(WEST, txtCensusNum, 10, EAST, lblCensusNum);
		
		// layout supplier
		layout.putConstraint(NORTH, lblSupplier, VERTICAL_SPACING, SOUTH, txtCensusNum);
		layout.putConstraint(EAST, lblSupplier, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, cmbSupplier, 0, VERTICAL_CENTER, lblSupplier);
		layout.putConstraint(WEST, cmbSupplier, 10, EAST, lblSupplier);
		
		// layout sample type
		layout.putConstraint(NORTH, lblSampleType, VERTICAL_SPACING, SOUTH, cmbSupplier);
		layout.putConstraint(EAST, lblSampleType, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, cmbSampleType, 0, VERTICAL_CENTER, lblSampleType);
		layout.putConstraint(WEST, cmbSampleType, 10, EAST, lblSampleType);
		layout.putConstraint(VERTICAL_CENTER, btnManageSampleTypes, 0, VERTICAL_CENTER, cmbSampleType);
		layout.putConstraint(WEST, btnManageSampleTypes, 30, EAST, cmbSampleType);
		
		// layout number of samples
		layout.putConstraint(NORTH, lblNumSamples, VERTICAL_SPACING, SOUTH, cmbSampleType);
		layout.putConstraint(EAST, lblNumSamples, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtNumSamples, 0, VERTICAL_CENTER, lblNumSamples);
		layout.putConstraint(WEST, txtNumSamples, 10, EAST, lblNumSamples);
		
		// layout sample volume
		layout.putConstraint(NORTH, lblSampleVolume, VERTICAL_SPACING, SOUTH, txtNumSamples);
		layout.putConstraint(EAST, lblSampleVolume, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtSampleVolume, 0, VERTICAL_CENTER, lblSampleVolume);
		layout.putConstraint(WEST, txtSampleVolume, 10, EAST, lblSampleVolume);
		
		// layout thawed
		layout.putConstraint(NORTH, lblThawed, VERTICAL_SPACING, SOUTH, txtSampleVolume);
		layout.putConstraint(EAST, lblThawed, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, btnThawedNo, 0, VERTICAL_CENTER, lblThawed);
		layout.putConstraint(VERTICAL_CENTER, btnThawedYes, 0, VERTICAL_CENTER, lblThawed);
		layout.putConstraint(WEST, btnThawedNo, 10, EAST, lblThawed);
		layout.putConstraint(WEST, btnThawedYes, 10, EAST, btnThawedNo);
		
		// layout planned use
		layout.putConstraint(NORTH, lblPlannedUse, VERTICAL_SPACING, SOUTH, btnThawedNo);
		layout.putConstraint(EAST, lblPlannedUse, LABEL_WIDTH, WEST, this);
		layout.putConstraint(NORTH, txtPlannedUseScroll, 0, NORTH, lblPlannedUse);
		layout.putConstraint(WEST, txtPlannedUseScroll, 10, EAST, lblPlannedUse);
		
		// layout freezer assignment
		layout.putConstraint(NORTH, lblFreezerAssignment, SECTION_SPACING, SOUTH, txtPlannedUseScroll);
		layout.putConstraint(EAST, lblFreezerAssignment, LABEL_WIDTH, WEST, this);
		layout.putConstraint(NORTH, lblFreezerDesc, VERTICAL_SPACING, SOUTH, lblFreezerAssignment);
		layout.putConstraint(WEST, lblFreezerDesc, LABEL_WIDTH - 100, WEST, this);
		layout.putConstraint(NORTH, lblFreezerCell, VERTICAL_SPACING, SOUTH, lblFreezerDesc);
		layout.putConstraint(WEST, lblFreezerCell, LABEL_WIDTH - 100, WEST, this);
		
		// layout add button
		layout.putConstraint(NORTH, btnAdd, SECTION_SPACING, SOUTH, lblFreezerCell);
		layout.putConstraint(WEST, btnAdd, LABEL_WIDTH, WEST, this);
		
		add(lblBoxNum);
		add(txtBoxNum);
		add(lblCensusNum);
		add(txtCensusNum);
		add(lblSupplier);
		add(cmbSupplier);
		add(lblSampleType);
		add(cmbSampleType);
		add(btnManageSampleTypes);
		add(lblNumSamples);
		add(txtNumSamples);
		add(lblSampleVolume);
		add(txtSampleVolume);
		add(lblThawed);
		add(btnThawedNo);
		add(btnThawedYes);
		add(lblPlannedUse);
		add(txtPlannedUseScroll);
		add(lblFreezerAssignment);
		add(lblFreezerDesc);
		add(lblFreezerCell);
		add(btnAdd);
	}
	
	private void constructComponents() {
		lblBoxNum = new JLabel("Box Number: ");
		txtBoxNum = new JTextField(20);
		
		lblCensusNum = new JLabel("Census Number: ");
		txtCensusNum = new JTextField(20);
		
		lblSupplier = new JLabel("Supplier: ");
		cmbSupplier = new JComboBox(new GetSupplierController().getSupplierNames());
		
		lblSampleType = new JLabel("Sample Type: ");
		cmbSampleTypeModel = new SampleTypesModel();
		cmbSampleTypeModel.updateModel(new GetSampleTypesController().getTypes());
		cmbSampleType = new JComboBox(cmbSampleTypeModel);
		
		btnManageSampleTypes = new JButton("Manage Sample Types");
		final JPanel parent = this;
		btnManageSampleTypes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final ModalDialog dialog = new ModalDialog(parent, new ManageSampleTypesDialog());
				((ManageSampleTypesDialog) dialog.getContentPane()).getNewTypeField().requestFocusInWindow();
				dialog.setVisible(true);
				cmbSampleTypeModel.updateModel(new GetSampleTypesController().getTypes());
			}
		});
		
		lblNumSamples = new JLabel("Sample Count: ");
		txtNumSamples = new JTextField(2);
		
		lblSampleVolume = new JLabel("Sample Volume: ");
		txtSampleVolume = new JTextField(10);
		
		lblThawed = new JLabel("Thawed during shipment? ");
		thawedGroup = new ButtonGroup();
		btnThawedNo = new JRadioButton("No");
		btnThawedYes = new JRadioButton("Yes");
		thawedGroup.add(btnThawedNo);
		thawedGroup.add(btnThawedYes);
		thawedGroup.setSelected(btnThawedNo.getModel(), true);
		
		lblPlannedUse = new JLabel("Planned Use: ");
		txtPlannedUse = new JTextArea(3, 20);
		txtPlannedUse.setLineWrap(true);
		txtPlannedUse.setWrapStyleWord(true);
		txtPlannedUseScroll = new JScrollPane(txtPlannedUse);
		
		lblFreezerAssignment = new JLabel("Freezer Assignment: ");
		lblFreezerDesc = new JLabel("Freezer desc here");
		lblFreezerCell = new JLabel("Freezer location here");
		
		btnAdd = new JButton("Add");
	}
	
	public JTextField getBoxNum() {
		return txtBoxNum;
	}
	
	public JComboBox getSupplierBox() {
		return cmbSupplier;
	}
	
	public JComboBox getSampleType() {
		return cmbSampleType;
	}
	
	public JButton getAddButton() {
		return btnAdd;
	}
	
	public JTextField getSampleVolume() {
		return txtSampleVolume;
	}
	
	public JTextField getSampleCount() {
		return txtNumSamples;
	}
	
	public JTextField getCensusNum() {
		return txtCensusNum;
	}
	
	public JTextArea getPlannedUse() {
		return txtPlannedUse;
	}
	
	public boolean isThawed() {
		if (btnThawedNo.isSelected()) {
			return false;
		}
		else {
			return true;
		}
	}
}
