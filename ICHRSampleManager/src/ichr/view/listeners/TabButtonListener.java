/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view.listeners;

import ichr.view.main.MainTabPanel;
import ichr.view.panels.TabButtonPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

/**
 * @author Chris Casola
 * @version Apr 13, 2013
 *
 */
public class TabButtonListener implements ActionListener {
	
	protected final MainTabPanel tabPanel;
	
	protected final TabButtonPanel buttonPanel;
	
	public TabButtonListener(MainTabPanel tabPanel, TabButtonPanel buttonPanel) {
		this.tabPanel = tabPanel;
		this.buttonPanel = buttonPanel;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		final JToggleButton source = (JToggleButton) e.getSource();
		final String name = source.getText();

		tabPanel.getLayout().show(tabPanel, name);
	}

}
