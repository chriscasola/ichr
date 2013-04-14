/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * @author Chris Casola
 * @version Apr 13, 2013
 *
 */
@SuppressWarnings("serial")
public class TabButtonPanel extends JPanel {
	
	private final List<JToggleButton> buttons;
	
	private final ButtonGroup buttonGroup;
	
	public TabButtonPanel() {
		buttons = new ArrayList<JToggleButton>();
		buttonGroup = new ButtonGroup();
		setLayout(new GridLayout(1,0));
		constructButtons();
		addButtonsToPanel();
	}
	
	private void addButtonsToPanel() {
		for (JToggleButton b : buttons) {
			b.setFocusable(false);
			buttonGroup.add(b);
			add(b);
		}
	}
	
	private void constructButtons() {
		buttons.add(new JToggleButton("Check Out", true));
		buttons.add(new JToggleButton("Check In"));
		buttons.add(new JToggleButton("Search"));
		buttons.add(new JToggleButton("Receive Samples"));
		buttons.add(new JToggleButton("Reports"));
		buttons.add(new JToggleButton("Users"));
		buttons.add(new JToggleButton("Help"));
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}
	
	public List<JToggleButton> getButtons() {
		return buttons;
	}
}
