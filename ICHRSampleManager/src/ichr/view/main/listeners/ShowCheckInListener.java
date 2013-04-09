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

import ichr.controller.CheckInController;
import ichr.view.main.CheckInPanel;
import ichr.view.main.ModalDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
public class ShowCheckInListener implements ActionListener {

	private CheckInPanel checkInPanel;
	
	private ModalDialog dialogWindow;
	
	private final JFrame parentWindow;
	
	public ShowCheckInListener(JFrame parentWindow) {
		this.parentWindow = parentWindow;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				checkInPanel = new CheckInPanel();
				dialogWindow = new ModalDialog(parentWindow, checkInPanel);
				dialogWindow.getRootPane().setDefaultButton(checkInPanel.getCheckInButton());
				checkInPanel.getCheckInButton().addActionListener(new CheckInController(dialogWindow, checkInPanel));
				checkInPanel.getCancelButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialogWindow.setVisible(false);
						dialogWindow.dispose();
					}
				});
				dialogWindow.setVisible(true);
			}
		});
	}

}
