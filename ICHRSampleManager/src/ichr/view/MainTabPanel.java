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

import ichr.controller.ReceiveBoxController;
import ichr.controller.SearchSamplesController;
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
	
	private final SearchPanel searchView;
	private final ReceiveSamplesPanel receiveSamplesView;
	
	public MainTabPanel() {
		
		// Setup the layout
		layout = new CardLayout();
		setLayout(layout);
		
		// Add panels
		add(new CheckOutPanel(), "Check Out");
		add(new CheckInPanel(), "Check In");
		
		searchView = new SearchPanel();
		add(searchView, "Search");
		SearchSamplesController searchController = new SearchSamplesController(searchView);
		searchView.getSearchButton().addActionListener(searchController);
		searchController.getData();
		
		receiveSamplesView = new ReceiveSamplesPanel();
		add(receiveSamplesView, "Receive Samples");
		receiveSamplesView.getAddButton().addActionListener(new ReceiveBoxController(receiveSamplesView));
		
		add(new ReportsPanel(), "Reports");
		add(new UsersPanel(), "Users");
		add(new HelpPanel(), "Help");
	}
	
	public CardLayout getLayout() {
		return layout;
	}
}
