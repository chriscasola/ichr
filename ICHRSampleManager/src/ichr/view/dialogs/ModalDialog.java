/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.view.dialogs;

import java.awt.Component;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
@SuppressWarnings("serial")
public class ModalDialog extends JDialog {

	public ModalDialog(Component parent, JPanel content) {
		setAlwaysOnTop(true);
		setLocationRelativeTo(parent);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setContentPane(content);
		pack();
		final int xPos = (int) (getLocation().getX() - (getWidth() / 2));
		final int yPos = (int) (getLocation().getY() - (getHeight() / 2));
		setLocation(new Point(xPos, yPos));
	}
}
