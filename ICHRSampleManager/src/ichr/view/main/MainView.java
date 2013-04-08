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

import ichr.database.DataStore;
import ichr.view.main.listeners.ShowCheckInListener;
import ichr.view.main.listeners.ShowCheckOutListener;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * The main application window
 * 
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
@SuppressWarnings("serial")
public class MainView extends JFrame {
	
	private final MainPanel mainPanel;
	
	protected static final int WINDOW_WIDTH = 800;
	protected static final int WINDOW_HEIGHT = 600;

	public MainView(String applicationName) {
		super(applicationName);
		
		// Construct the main panel
		mainPanel = new MainPanel();
		setContentPane(mainPanel);
		
		// Add action listeners
		mainPanel.getCheckOutButton().addActionListener(new ShowCheckOutListener(this));
		mainPanel.getCheckInButton().addActionListener(new ShowCheckInListener(this));
		
		// Clean up when the close button is clicked.
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				// Close the database connection
				DataStore.getDB().closeConnection();

				// dispose of this window
				dispose();
				System.exit(0);
			}
		});

		// Set the window size and position
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (dim.width - WINDOW_WIDTH) / 2;
		int yPos = (int)((dim.height - WINDOW_HEIGHT) / 2 * .75);
		setBounds(xPos, yPos, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
}
