/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view.main.listeners;

import ichr.controller.BrowseSamplesController;
import ichr.view.main.BrowseSamplesPanel;
import ichr.view.main.ModalDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
public class ShowBrowseSamplesListener implements ActionListener {
	
	private final JFrame parentWindow;
	
	private BrowseSamplesPanel browsePanel;
	
	private JDialog dialogWindow;
	
	public ShowBrowseSamplesListener(JFrame parentWindow) {
		this.parentWindow = parentWindow;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				browsePanel = new BrowseSamplesPanel();
				dialogWindow = new ModalDialog(parentWindow, browsePanel);
				browsePanel.getRefreshButton().addActionListener(new BrowseSamplesController(browsePanel));
				/*checkInPanel.getCancelButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialogWindow.setVisible(false);
						dialogWindow.dispose();
					}
				});*/
				dialogWindow.setVisible(true);
			}
		});
	}

}
