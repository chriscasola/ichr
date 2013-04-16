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

import static ichr.view.MainView.*;

import static javax.swing.SpringLayout.*;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

/**
 * @author Chris Casola
 * @version Apr 13, 2013
 *
 */
@SuppressWarnings("serial")
public class ReportsPanel extends JPanel {

	private final SpringLayout layout;
	
	protected JList reportList;
	protected JScrollPane reportListScroll;
	protected JButton btnExport;
	
	public ReportsPanel() {
		layout = new SpringLayout();
		setLayout(layout);
		
		constructComponents();
		addComponents();
	}
	
	private void addComponents() {
		// layout report list
		layout.putConstraint(NORTH, reportListScroll, VERTICAL_SPACING, NORTH, this);
		layout.putConstraint(WEST, reportListScroll, LABEL_WIDTH, WEST, this);
		layout.putConstraint(EAST, reportListScroll, 300, WEST, reportListScroll);
		layout.putConstraint(SOUTH, reportListScroll, 300, NORTH, reportList);
		
		// layout export button
		layout.putConstraint(NORTH, btnExport, SECTION_SPACING, SOUTH, reportListScroll);
		layout.putConstraint(HORIZONTAL_CENTER, btnExport, 0, HORIZONTAL_CENTER, reportListScroll);
		
		add(reportListScroll);
		add(btnExport);
	}
	
	private void constructComponents() {
		
		// dummy data
		Object[] reports = {"Grant Proposal Report", "Empty/Missing Samples Report"};
		reportList = new JList(reports);
		reportListScroll = new JScrollPane(reportList);
		
		btnExport = new JButton("Export Selected Report");
	}
}
