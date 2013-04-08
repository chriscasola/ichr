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

import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
@SuppressWarnings("serial")
public class CheckInPanel extends JPanel {

	private final JTextField txtSampleNum;
	
	private final JButton btnCheckIn;
	
	private final JButton btnCancel;
	
	public CheckInPanel() {
		txtSampleNum = new JTextField(15);
		btnCheckIn = new JButton("Check In");
		btnCancel = new JButton("Cancel");
		
		setLayout(new FlowLayout());
		
		add(new JLabel("Sample Number: "));
		add(txtSampleNum);
		add(btnCheckIn);
		add(btnCancel);
	}
	
	public JButton getCheckInButton() {
		return btnCheckIn;
	}

	public AbstractButton getCancelButton() {
		return btnCancel;
	}
}
