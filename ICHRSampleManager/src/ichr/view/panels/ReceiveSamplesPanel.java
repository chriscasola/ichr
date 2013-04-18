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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	protected JButton btnAdd;
	protected JLabel lblStatus;

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
		
		// layout add button
		layout.putConstraint(NORTH, btnAdd, SECTION_SPACING, SOUTH, txtPlannedUseScroll);
		layout.putConstraint(WEST, btnAdd, LABEL_WIDTH, WEST, this);
		
		// layout status label
		layout.putConstraint(VERTICAL_CENTER, lblStatus, 0, VERTICAL_CENTER, btnAdd);
		layout.putConstraint(WEST, lblStatus, VERTICAL_SPACING, EAST, btnAdd);
		
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
		add(btnAdd);
		add(lblStatus);
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
				final ManageSampleTypesDialog dialog = new ManageSampleTypesDialog();
				final ModalDialog dialogWrapper = new ModalDialog(parent, dialog);
				dialog.getNewTypeField().requestFocusInWindow();
				dialogWrapper.pack();
				dialogWrapper.setMinimumSize(dialogWrapper.getPreferredSize());
				dialogWrapper.setResizable(false);
				dialogWrapper.setVisible(true);
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
		
		btnAdd = new JButton("Add");
		
		lblStatus = new JLabel("");
		lblStatus.setForeground(Color.BLUE);
	}
	
	public void showMessage(String message) {
		lblStatus.setText(message);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				lblStatus.setText("");
			}
		}, 5000);
	}
	
	public void clearForm() {
		txtBoxNum.setText("");
		txtCensusNum.setText("");
		cmbSupplier.setSelectedIndex(0);
		cmbSampleType.setSelectedIndex(0);
		txtNumSamples.setText("");
		txtSampleVolume.setText("");
		thawedGroup.setSelected(btnThawedNo.getModel(), true);
		txtPlannedUse.setText("");
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
		return btnThawedYes.isSelected();
	}
	
	public void showFreezerAssignment(String freezerName, int row, int col) {
		JOptionPane.showMessageDialog(this, 
				"Place the box in " + freezerName + " at row " + row + " and column " + col + ".", 
				"Freezer Assignment", 
				JOptionPane.INFORMATION_MESSAGE);
	}
}
