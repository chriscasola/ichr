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

import static javax.swing.SpringLayout.*;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	protected JLabel lblSupplier;
	protected JComboBox cmbSupplier;
	protected JLabel lblSampleType;
	protected JComboBox cmbSampleType;
	protected JButton btnAddSampleType;
	protected JLabel lblNumSamples;
	protected JTextField txtNumSamples;
	protected JLabel lblNumEmpty;
	protected JTextField txtNumEmpty;
	protected JLabel lblFreezerAssignment;
	protected JLabel lblFreezerDesc;
	protected JLabel lblFreezerCell;
	protected JButton btnAdd;

	public static final int LABEL_WIDTH = 200;
	public static final int SECTION_SPACING = 35;
	public static final int VERTICAL_SPACING = 10;
	
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
		
		// layout supplier
		layout.putConstraint(NORTH, lblSupplier, VERTICAL_SPACING, SOUTH, txtBoxNum);
		layout.putConstraint(EAST, lblSupplier, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, cmbSupplier, 0, VERTICAL_CENTER, lblSupplier);
		layout.putConstraint(WEST, cmbSupplier, 10, EAST, lblSupplier);
		
		// layout sample type
		layout.putConstraint(NORTH, lblSampleType, VERTICAL_SPACING, SOUTH, cmbSupplier);
		layout.putConstraint(EAST, lblSampleType, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, cmbSampleType, 0, VERTICAL_CENTER, lblSampleType);
		layout.putConstraint(WEST, cmbSampleType, 10, EAST, lblSampleType);
		layout.putConstraint(VERTICAL_CENTER, btnAddSampleType, 0, VERTICAL_CENTER, cmbSampleType);
		layout.putConstraint(WEST, btnAddSampleType, 30, EAST, cmbSampleType);
		
		// layout number of samples
		layout.putConstraint(NORTH, lblNumSamples, VERTICAL_SPACING, SOUTH, cmbSampleType);
		layout.putConstraint(EAST, lblNumSamples, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtNumSamples, 0, VERTICAL_CENTER, lblNumSamples);
		layout.putConstraint(WEST, txtNumSamples, 10, EAST, lblNumSamples);
		
		// layout number of missing
		layout.putConstraint(NORTH, lblNumEmpty, VERTICAL_SPACING, SOUTH, txtNumSamples);
		layout.putConstraint(EAST, lblNumEmpty, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtNumEmpty, 0, VERTICAL_CENTER, lblNumEmpty);
		layout.putConstraint(WEST, txtNumEmpty, 10, EAST, lblNumEmpty);
		
		// layout freezer assignment
		layout.putConstraint(NORTH, lblFreezerAssignment, SECTION_SPACING, SOUTH, txtNumEmpty);
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
		add(lblSupplier);
		add(cmbSupplier);
		add(lblSampleType);
		add(cmbSampleType);
		add(btnAddSampleType);
		add(lblNumSamples);
		add(txtNumSamples);
		add(lblNumEmpty);
		add(txtNumEmpty);
		add(lblFreezerAssignment);
		add(lblFreezerDesc);
		add(lblFreezerCell);
		add(btnAdd);
	}
	
	private void constructComponents() {
		lblBoxNum = new JLabel("Box Number: ");
		txtBoxNum = new JTextField(20);
		
		// dummy data
		Object[] suppliers = {"Acme", "BioCo"};
		lblSupplier = new JLabel("Supplier: ");
		cmbSupplier = new JComboBox(suppliers);
		
		// dummy data
		Object[] types = {"serum", "virus"};
		lblSampleType = new JLabel("Sample Type: ");
		cmbSampleType = new JComboBox(types);
		
		btnAddSampleType = new JButton("+");
		final Dimension currSize = btnAddSampleType.getPreferredSize();
		btnAddSampleType.setPreferredSize(new Dimension((int) (currSize.width * .33), (int) (currSize.height * .75)));
		
		lblNumSamples = new JLabel("Sample Count: ");
		txtNumSamples = new JTextField(2);
		
		lblNumEmpty = new JLabel("Empty Sample Count: ");
		txtNumEmpty = new JTextField(2);
		
		lblFreezerAssignment = new JLabel("Freezer Assignment: ");
		lblFreezerDesc = new JLabel("Freezer desc here");
		lblFreezerCell = new JLabel("Freezer location here");
		
		btnAdd = new JButton("Add");
	}
}
