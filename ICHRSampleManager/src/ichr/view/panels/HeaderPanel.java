/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view.panels;

import static javax.swing.SpringLayout.*;

import ichr.ICHRSampleManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * @author Chris Casola
 * @version Apr 16, 2013
 *
 */
@SuppressWarnings("serial")
public class HeaderPanel extends JPanel {
	
	private final SpringLayout layout;
	
	private final JButton btnLogout;

	public HeaderPanel(boolean showLogout) {
		layout = new SpringLayout();
		setLayout(layout);
		
		final JLabel lblHeader = new JLabel("ICHR Sample Management System");
		lblHeader.setFont(lblHeader.getFont().deriveFont(18f));
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ICHRSampleManager.logout();
			}
		});
		
		// layout components
		layout.putConstraint(VERTICAL_CENTER, lblHeader, 0, VERTICAL_CENTER, this);
		layout.putConstraint(HORIZONTAL_CENTER, lblHeader, 0, HORIZONTAL_CENTER, this);
		
		layout.putConstraint(VERTICAL_CENTER, btnLogout, 0, VERTICAL_CENTER, this);
		layout.putConstraint(EAST, btnLogout, -10, EAST, this);
		
		add(lblHeader);
		
		if (showLogout) {
			add(btnLogout);
		}
		
		setBackground(new Color(111,173,43));
		setBorder(null);
	}
}
