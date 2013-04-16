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

import static javax.swing.SpringLayout.*;

import ichr.database.DataStore;
import ichr.view.listeners.TabButtonListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout;

/**
 * @author Chris Casola
 * @version Apr 13, 2013
 *
 */
@SuppressWarnings("serial")
public class MainView extends JFrame {
	
	protected final TabButtonPanel tabButtonPanel;
	protected final MainTabPanel mainTabPanel;
	protected final JPanel headerPanel;
	
	public static final int LABEL_WIDTH = 200;
	public static final int SECTION_SPACING = 35;
	public static final int VERTICAL_SPACING = 10;
	
	protected static final int WINDOW_WIDTH = 800;
	protected static final int WINDOW_HEIGHT = 600;

	public MainView(String applicationName) {
		super(applicationName);
		
		// Construct the content panel
		JPanel content = new JPanel();
		SpringLayout layout = new SpringLayout();
		content.setLayout(layout);

		headerPanel = new HeaderPanel();
		
		tabButtonPanel = new TabButtonPanel();
		tabButtonPanel.setBackground(new Color(179,243,109));
		tabButtonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		tabButtonPanel.validate();
		
		mainTabPanel = new MainTabPanel();
		
		// Setup constraints
		layout.putConstraint(NORTH, headerPanel, 0, NORTH, content);
		layout.putConstraint(WEST, headerPanel, 0, WEST, content);
		layout.putConstraint(EAST, headerPanel, 0, EAST, content);
		layout.putConstraint(SOUTH, headerPanel, 50, NORTH, headerPanel);
		
		layout.putConstraint(NORTH, tabButtonPanel, 0, SOUTH, headerPanel);
		layout.putConstraint(WEST, tabButtonPanel, 0, WEST, content);
		layout.putConstraint(EAST, content, 0, EAST, tabButtonPanel);
		layout.putConstraint(SOUTH, tabButtonPanel, 40, NORTH, tabButtonPanel);
		
		layout.putConstraint(NORTH, mainTabPanel, 5, SOUTH, tabButtonPanel);
		layout.putConstraint(WEST, mainTabPanel, 5, WEST, content);
		layout.putConstraint(EAST, mainTabPanel, -5, EAST, content);
		
		layout.putConstraint(SOUTH, content, 5, SOUTH, mainTabPanel);
		
		// Add components
		content.add(headerPanel);
		content.add(tabButtonPanel);
		content.add(mainTabPanel);
		
		// Setup listeners for the tab panel
		setupTabPanelListeners();
		
		// Add the content panel to the frame
		content.setPreferredSize(new Dimension(content.getPreferredSize().width, WINDOW_HEIGHT));
		content.validate();
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
		int width = content.getPreferredSize().width;
		int xPos = (dim.width - width) / 2;
		int yPos = (int)((dim.height - WINDOW_HEIGHT) / 2 * .75);
		setBounds(xPos, yPos, width, WINDOW_HEIGHT);
	}
	
	public void setupTabPanelListeners() {
		for (JToggleButton b : tabButtonPanel.getButtons()) {
			b.addActionListener(new TabButtonListener(mainTabPanel, tabButtonPanel));
		}
	}
}
