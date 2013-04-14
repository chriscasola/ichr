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

import ichr.view.panels.CheckInPanel;
import ichr.view.panels.CheckOutPanel;
import ichr.view.panels.HelpPanel;
import ichr.view.panels.ReceiveSamplesPanel;
import ichr.view.panels.ReportsPanel;
import ichr.view.panels.SearchPanel;
import ichr.view.panels.UsersPanel;

import java.awt.CardLayout;

import javax.swing.JPanel;

/**
 * @author Chris Casola
 * @version Apr 13, 2013
 *
 */
@SuppressWarnings("serial")
public class MainTabPanel extends JPanel {

	private final CardLayout layout;
	
	public MainTabPanel() {
		
		// Setup the layout
		layout = new CardLayout();
		setLayout(layout);
		
		// Add panels
		add(new CheckOutPanel(), "Check Out");
		add(new CheckInPanel(), "Check In");
		add(new SearchPanel(), "Search");
		add(new ReceiveSamplesPanel(), "Receive Samples");
		add(new ReportsPanel(), "Reports");
		add(new UsersPanel(), "Users");
		add(new HelpPanel(), "Help");
	}
	
	public CardLayout getLayout() {
		return layout;
	}
}
