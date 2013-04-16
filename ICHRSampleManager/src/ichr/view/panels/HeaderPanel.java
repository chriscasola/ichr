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

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Chris Casola
 * @version Apr 16, 2013
 *
 */
@SuppressWarnings("serial")
public class HeaderPanel extends JPanel {

	public HeaderPanel() {
		final JLabel lblHeader = new JLabel("ICHR Sample Management System");
		lblHeader.setFont(lblHeader.getFont().deriveFont(18f));
		add(lblHeader);
		setBackground(new Color(111,173,43));
		setBorder(null);
	}
}
