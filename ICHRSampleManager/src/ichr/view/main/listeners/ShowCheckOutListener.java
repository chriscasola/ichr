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

import ichr.view.main.CheckOutPanel;
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
public class ShowCheckOutListener implements ActionListener {
	
	private CheckOutPanel checkOutPanel;
	
	private ModalDialog dialogWindow;
	
	private final JFrame parentWindow;
	
	public ShowCheckOutListener(JFrame parentWindow) {
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
				checkOutPanel = new CheckOutPanel();
				dialogWindow = new ModalDialog(parentWindow, checkOutPanel);
				dialogWindow.getRootPane().setDefaultButton(checkOutPanel.getCheckInButton());
				checkOutPanel.getCancelButton().addActionListener(new ActionListener() {
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
