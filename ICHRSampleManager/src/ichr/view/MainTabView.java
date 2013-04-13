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

import ichr.database.DataStore;
import ichr.view.listeners.TabButtonListener;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * @author Chris Casola
 * @version Apr 13, 2013
 *
 */
@SuppressWarnings("serial")
public class MainTabView extends JFrame {
	
	protected final TabButtonPanel tabButtonPanel;
	protected final MainTabPanel mainTabPanel;
	
	protected static final int WINDOW_WIDTH = 800;
	protected static final int WINDOW_HEIGHT = 600;

	public MainTabView(String applicationName) {
		super(applicationName);
		
		// Construct the content panel
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(0,1));
		
		// Add header section
		content.add(new JLabel("ICHR"));
		
		// Add button panel
		tabButtonPanel = new TabButtonPanel();
		content.add(tabButtonPanel);
		
		// Add the main tab panel
		mainTabPanel = new MainTabPanel();
		content.add(mainTabPanel);
		
		// Setup listeners for the tab panel
		setupTabPanelListeners();
		
		// Add the content panel to the frame
		setContentPane(content);

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
	
	public void setupTabPanelListeners() {
		for (JToggleButton b : tabButtonPanel.getButtons()) {
			b.addActionListener(new TabButtonListener(mainTabPanel, tabButtonPanel));
		}
	}
}
