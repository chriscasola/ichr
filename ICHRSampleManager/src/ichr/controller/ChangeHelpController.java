/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.controller;

import ichr.view.panels.HelpPanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Chris Casola
 * @version Apr 18, 2013
 *
 */
public class ChangeHelpController implements ListSelectionListener {
	
	private final HelpPanel view;

	public ChangeHelpController(HelpPanel view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent le) {
		final JList source = (JList) le.getSource();
		String item = (String) source.getSelectedValue();
		String filename = "";
		
		if (item.equals("Check Out")) {
			filename = "check_out.txt";
		}
		else if (item.equals("Check In")) {
			filename = "check_in.txt";
		}
		else if (item.equals("Search")) {
			filename = "search.txt";
		}
		else if (item.equals("Receive Samples")) {
			filename = "receive_samples.txt";
		}
		else if (item.equals("Reports")) {
			filename = "reports.txt";
		}
		else if (item.equals("Users")) {
			filename = "users.txt";
		}
		else {
			return; // no help file available
		}
		
		try {
			final InputStream s = this.getClass().getClassLoader().getResourceAsStream(filename);
			final BufferedReader br = new BufferedReader(new InputStreamReader(s));
			view.getHelpDetailArea().read(br, null);
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
