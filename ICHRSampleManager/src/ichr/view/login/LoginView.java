/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view.login;

import ichr.controller.LoginController;
import ichr.database.DataStore;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * The login window
 * 
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
@SuppressWarnings("serial")
public class LoginView extends JFrame {
	
	protected final LoginPanel loginPanel;
	
	public LoginView(String applicationName) {
		super("Login - " + applicationName);
		
		// Create the login panel
		loginPanel = new LoginPanel();
		setContentPane(loginPanel);
		
		// Add controller to login button
		loginPanel.getLoginButton().addActionListener(new LoginController(this));
		
		// Make the login button the default button for the enter key
		this.getRootPane().setDefaultButton(loginPanel.getLoginButton());
		
		// Clean up when the close button is clicked.
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				// Close the database connection
				DataStore.getDB().closeConnection();

				// dispose of this window
				dispose();
				System.exit(0);
			}
		});
		pack();
		
		// Set the window size and position
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) getPreferredSize().getWidth();
		int height = (int) getPreferredSize().getHeight();
		int xPos = (dim.width - width) / 2;
		int yPos = (int)((dim.height - height) / 2 * .75);
		setBounds(xPos, yPos, width, height);
	}
	
	/**
	 * @return the login panel
	 */
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}
}
