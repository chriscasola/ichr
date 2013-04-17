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
import ichr.controller.GetFullNameController;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

/**
 * @author Chris Casola
 * @version Apr 17, 2013
 *
 */
@SuppressWarnings("serial")
public class StatusPanel extends JPanel {

	private final SpringLayout layout;
	
	protected JLabel lblCurrentTime;
	protected JLabel lblCurrentUser;
	protected JLabel lblExpiration;
	
	public StatusPanel() {
		layout = new SpringLayout();
		setLayout(layout);
		setBackground(new Color(111,173,43));
		
		constructComponents();
		addComponents();
	}
	
	private void addComponents() {
		layout.putConstraint(VERTICAL_CENTER, lblCurrentUser, 0, VERTICAL_CENTER, this);
		layout.putConstraint(VERTICAL_CENTER, lblCurrentTime, 0, VERTICAL_CENTER, this);
		layout.putConstraint(VERTICAL_CENTER, lblExpiration, 0, VERTICAL_CENTER, this);
		
		layout.putConstraint(WEST, lblCurrentUser, 10, WEST, this);
		layout.putConstraint(HORIZONTAL_CENTER, lblCurrentTime, 0, HORIZONTAL_CENTER, this);
		layout.putConstraint(EAST, lblExpiration, -10, EAST, this);
		
		add(lblCurrentUser);
		add(lblCurrentTime);
		add(lblExpiration);
	}
	
	private void constructComponents() {
		lblCurrentTime = new JLabel();
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a  MM-dd-yyyy");
						lblCurrentTime.setText(formatter.format(new Date()));
					}
				});
			}
		}, 1, 1000);
		
		GetFullNameController.getName();
		lblCurrentUser = new JLabel();
		lblCurrentUser.setText(GetFullNameController.getLast() + ", " + GetFullNameController.getFirst());
		
		lblExpiration = new JLabel();
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						long timeRemaining = ICHRSampleManager.getSessionTime();
						long minutes = timeRemaining / 1000 / 60;
						long seconds = timeRemaining / 1000 % 60;
						lblExpiration.setText("Session Ending: " + String.valueOf(minutes) + ":" + String.valueOf((seconds >= 10) ? seconds : "0" + seconds));
					}
				});
			}
		}, 1, 1000);
	}
}
