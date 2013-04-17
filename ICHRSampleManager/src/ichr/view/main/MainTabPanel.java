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

import ichr.controller.ChangePasswordController;
import ichr.controller.DeleteUserController;
import ichr.controller.ReceiveBoxController;
import ichr.controller.RetrieveSampleController;
import ichr.controller.SearchSamplesController;
import ichr.view.panels.CheckInPanel;
import ichr.view.panels.CheckOutPanel;
import ichr.view.panels.HelpPanel;
import ichr.view.panels.ReceiveSamplesPanel;
import ichr.view.panels.ReportsPanel;
import ichr.view.panels.SearchPanel;
import ichr.view.panels.UsersPanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
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
	private final CheckOutPanel checkOutView;
	private final UsersPanel usersPanel;
	
	public MainTabPanel() {
		
		// Setup the layout
		layout = new CardLayout();
		setLayout(layout);
		
		// Add check out panel
		checkOutView = new CheckOutPanel();
		add(checkOutView, "Check Out");
		final RetrieveSampleController retrieveSampleController = new RetrieveSampleController();
		checkOutView.getSampleNumField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (retrieveSampleController.isValidId(checkOutView.getSampleNumField().getText())) {
					checkOutView.fillInFields(retrieveSampleController);
				}
				else {
					checkOutView.clearFields();
				}
			}
		});
		checkOutView.getBtnCheckOut().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (retrieveSampleController.checkOutSample(checkOutView.getCommentField().getText())) {
					checkOutView.clearFields();
					checkOutView.getSampleNumField().setText("");
					checkOutView.getCommentField().setText("");
					checkOutView.showMessage("Sample Checked Out");
				}
				else {
					JOptionPane.showMessageDialog(checkOutView, "The sample is already checked out!", "Sample Already Checked Out", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		// add check in panel
		add(new CheckInPanel(), "Check In");
		
		// add search panel
		searchView = new SearchPanel();
		add(searchView, "Search");
		SearchSamplesController searchController = new SearchSamplesController(searchView);
		searchView.getSearchButton().addActionListener(searchController);
		searchController.getData();
		
		// add receive samples panel
		receiveSamplesView = new ReceiveSamplesPanel();
		add(receiveSamplesView, "Receive Samples");
		receiveSamplesView.getAddButton().addActionListener(new ReceiveBoxController(receiveSamplesView));
		add(new ReportsPanel(), "Reports");
		
		// add users panel
		usersPanel = new UsersPanel();
		add(usersPanel, "Users");
		usersPanel.getChangePwdButton().addActionListener(new ChangePasswordController(usersPanel));
		usersPanel.getDeleteButton().addActionListener(new DeleteUserController(usersPanel));
		
		add(new HelpPanel(), "Help");
	}
	
	public CardLayout getLayout() {
		return layout;
	}
}
