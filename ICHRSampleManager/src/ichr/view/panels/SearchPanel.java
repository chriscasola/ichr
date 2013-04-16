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

import ichr.model.SamplesTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * @author Chris Casola
 * @version Apr 13, 2013
 *
 */
@SuppressWarnings("serial")
public class SearchPanel extends JPanel {
	
	private final SpringLayout layout;
	
	protected JLabel lblQuery;
	protected JTextField txtQuery;
	protected JLabel lblSearchBy;
	protected ButtonGroup searchByGroup;
	protected JRadioButton btnSampleNum;
	protected JRadioButton btnBoxNum;
	protected JRadioButton btnCensusNum;
	protected JRadioButton btnSampleType;
	protected JRadioButton btnSupplierName;
	protected JButton btnSearch;
	protected JButton btnReset;
	protected JTable sampleTable;
	protected SamplesTableModel sampleModel;
	protected JScrollPane sampleTableScroll;

	public SearchPanel() {
		layout =  new SpringLayout();
		setLayout(layout);
		
		constructComponents();
		addComponents();
	}
	
	private void addComponents() {
		// layout query field
		layout.putConstraint(NORTH, lblQuery, 10, NORTH, this);
		layout.putConstraint(EAST, lblQuery, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtQuery, 0, VERTICAL_CENTER, lblQuery);
		layout.putConstraint(WEST, txtQuery, 10, EAST, lblQuery);
		
		// layout search by
		layout.putConstraint(NORTH, lblSearchBy, VERTICAL_SPACING * 2, SOUTH, lblQuery);
		layout.putConstraint(EAST, lblSearchBy, LABEL_WIDTH, WEST, this);
		JPanel btnGroupPanel = new JPanel();
		btnGroupPanel.setLayout(new GridLayout(0,2));
		btnGroupPanel.add(btnSampleNum);
		btnGroupPanel.add(btnSampleType);
		btnGroupPanel.add(btnBoxNum);
		btnGroupPanel.add(btnSupplierName);
		btnGroupPanel.add(btnCensusNum);
		btnGroupPanel.validate();
		layout.putConstraint(NORTH, btnGroupPanel, 0, NORTH, lblSearchBy);
		layout.putConstraint(WEST, btnGroupPanel, 10, EAST, lblSearchBy);
		
		// layout search button
		layout.putConstraint(NORTH, btnSearch, 5, SOUTH, txtQuery);
		layout.putConstraint(WEST, btnSearch, 25, EAST, txtQuery);
		
		// layout reset button
		layout.putConstraint(NORTH, btnReset, VERTICAL_SPACING, SOUTH, btnSearch);
		layout.putConstraint(WEST, btnReset, 0, WEST, btnSearch);
		layout.putConstraint(EAST, btnReset, 0, EAST, btnSearch);
		
		// layout the search results
		layout.putConstraint(NORTH, sampleTableScroll, SECTION_SPACING, SOUTH, btnGroupPanel);
		layout.putConstraint(EAST, sampleTableScroll, -15, EAST, this);
		layout.putConstraint(WEST, sampleTableScroll, 15, WEST, this);
		layout.putConstraint(SOUTH, sampleTableScroll, -5, SOUTH, this);
		
		add(lblQuery);
		add(txtQuery);
		add(lblSearchBy);
		add(btnGroupPanel);
		add(btnSearch);
		add(btnReset);
		add(sampleTableScroll);
	}
	
	private void constructComponents() {
		lblQuery = new JLabel("Query: ");
		txtQuery = new JTextField(20);
		
		lblSearchBy = new JLabel("Search By:");
		searchByGroup = new ButtonGroup();
		btnSampleNum = new JRadioButton("Sample Number");
		btnBoxNum = new JRadioButton("Box Number");
		btnCensusNum = new JRadioButton("Census Number");
		btnSampleType = new JRadioButton("Sample Type");
		btnSupplierName = new JRadioButton("Supplier Name");
		searchByGroup.add(btnSampleNum);
		searchByGroup.add(btnBoxNum);
		searchByGroup.add(btnCensusNum);
		searchByGroup.add(btnSampleType);
		searchByGroup.add(btnSupplierName);
		searchByGroup.setSelected(btnSampleNum.getModel(), true);
		
		btnSearch = new JButton("Search");
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtQuery.setText("");
				btnSearch.doClick();
			}
		});
		
		sampleModel = new SamplesTableModel();
		sampleTable = new JTable(sampleModel);
		sampleTable.setAutoCreateRowSorter(true);
		sampleTableScroll = new JScrollPane(sampleTable);
	}

	public SamplesTableModel getSamplesTableModel() {
		return sampleModel;
	}
	
	public JTable getSamplesTable() {
		return sampleTable;
	}
	
	public JButton getSearchButton() {
		return btnSearch;
	}
	
	public JTextField getQueryField() {
		return txtQuery;
	}
	
	public JRadioButton getSelectedButton() {
		Enumeration<AbstractButton> buttons = searchByGroup.getElements();
		AbstractButton retVal;
		while (buttons.hasMoreElements()) {
			retVal = buttons.nextElement();
			if (retVal.isSelected()) {
				return (JRadioButton) retVal;
			}
		}
		return null;
	}
}
