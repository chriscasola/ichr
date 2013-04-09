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

import ichr.model.SamplesTableModel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
@SuppressWarnings("serial")
public class BrowseSamplesPanel extends JPanel {
	
	private final JTable samplesTable;
	
	private final SamplesTableModel samplesTableModel;
	
	private final JButton btnRefresh;

	public BrowseSamplesPanel() {
		
		btnRefresh = new JButton("Refresh");

		samplesTableModel = new SamplesTableModel();
		samplesTable = new JTable(samplesTableModel);
		final JScrollPane samplesScrollPane = new JScrollPane(samplesTable);
		samplesTable.setFillsViewportHeight(true);
		
		add(samplesScrollPane);
	}
	
	public SamplesTableModel getSamplesTableModel() {
		return samplesTableModel;
	}
	
	public JButton getRefreshButton() {
		return btnRefresh;
	}
}
