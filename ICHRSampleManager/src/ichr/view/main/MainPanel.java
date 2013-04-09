/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view.main;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	
	private final JButton btnCheckOut, btnCheckIn, btnBrowseSamples;

	public MainPanel() {
		
		// Construct the buttons
		btnCheckOut = new JButton("Check Out a Sample");
		btnCheckIn = new JButton("Check In a Sample");
		btnBrowseSamples = new JButton("Browse Samples");
		
		// Set Layout Manager
		setLayout(new GridLayout(0,3));
		
		// Add buttons
		add(btnCheckOut);
		add(btnCheckIn);
		add(btnBrowseSamples);
	}
	
	public JButton getCheckInButton() {
		return btnCheckIn;
	}
	
	public JButton getCheckOutButton() {
		return btnCheckOut;
	}
	
	public JButton getBrowseSamplesButton() {
		return btnBrowseSamples;
	}
}
