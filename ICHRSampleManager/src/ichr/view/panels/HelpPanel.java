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

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

/**
 * @author Chris Casola
 * @version Apr 13, 2013
 *
 */
@SuppressWarnings("serial")
public class HelpPanel extends JPanel {

	private final SpringLayout layout;
	
	protected JLabel lblContents;
	protected JList helpList;
	protected JScrollPane helpListScroll;
	protected JTextArea txtDetail;
	protected JScrollPane txtDetailScroll;
	
	public HelpPanel() {
		layout = new SpringLayout();
		setLayout(layout);
		
		constructComponents();
		addComponents();
	}

	private void addComponents() {
		// layout the contents label
		layout.putConstraint(NORTH, lblContents, VERTICAL_SPACING, NORTH, this);
		layout.putConstraint(WEST, lblContents, VERTICAL_SPACING, WEST, this);
		
		// layout the help list
		layout.putConstraint(NORTH, helpListScroll, VERTICAL_SPACING, SOUTH, lblContents);
		layout.putConstraint(WEST, helpListScroll, VERTICAL_SPACING, WEST, this);
		layout.putConstraint(EAST, helpListScroll, 200, WEST, helpListScroll);
		layout.putConstraint(SOUTH, helpListScroll, 400, NORTH, helpListScroll);
		
		// layout the detail pane
		layout.putConstraint(NORTH, txtDetailScroll, 0, NORTH, helpListScroll);
		layout.putConstraint(WEST, txtDetailScroll, SECTION_SPACING, EAST, helpListScroll);
		layout.putConstraint(EAST, txtDetailScroll, VERTICAL_SPACING * -1, EAST, this);
		layout.putConstraint(SOUTH, txtDetailScroll, 0, SOUTH, helpListScroll);
		
		add(lblContents);
		add(helpListScroll);
		add(txtDetailScroll);
	}
	
	private void constructComponents() {
		lblContents = new JLabel("Contents");
		
		// dummy data
		Object[] help = {"Check In", "Check Out", "Receive Samples", "Reports", "Search", "Users"};
		helpList = new JList(help);
		helpListScroll = new JScrollPane(helpList);
		
		// dummy data
		String helpDetail = "Help contents will go here...";
		txtDetail = new JTextArea(helpDetail, 200, 200);
		txtDetailScroll = new JScrollPane(txtDetail);
	}
}
