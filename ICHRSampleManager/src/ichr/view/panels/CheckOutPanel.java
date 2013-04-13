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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * @author Chris Casola
 * @version Apr 13, 2013
 *
 */
@SuppressWarnings("serial")
public class CheckOutPanel extends JPanel {

	private final SpringLayout layout;
	
	protected JLabel lblSampleNum;
	protected JTextField txtSampleNum;
	protected JLabel lblCurrTime;
	protected JLabel currTime;
	protected JLabel lblThawCount;
	protected JLabel thawCount;
	protected JLabel lblPurpose;
	protected JTextArea txtPurpose;
	protected JScrollPane txtPurposeScroll;
	protected JLabel lblFreezerDesc;
	protected JLabel freezerDesc;
	protected JLabel lblFreezerCell;
	protected JLabel freezerCell;
	protected JLabel lblBoxId;
	protected JLabel boxId;
	protected JButton btnCheckOut;
	protected JButton btnCancel;
	
	public static final int LABEL_WIDTH = 200;
	public static final int SECTION_SPACING = 35;
	public static final int VERTICAL_SPACING = 10;

	public CheckOutPanel() {
		layout = new SpringLayout();
		setLayout(layout);
		
		constructComponents();
		addComponents();
	}
	
	private void addComponents() {
		
		// layout lblSampleNum
		layout.putConstraint(SpringLayout.NORTH, lblSampleNum, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, lblSampleNum, LABEL_WIDTH, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, txtSampleNum, 0, SpringLayout.VERTICAL_CENTER, lblSampleNum);
		layout.putConstraint(SpringLayout.WEST, txtSampleNum, 10, SpringLayout.EAST, lblSampleNum);
		
		// layout lblCurrTime
		layout.putConstraint(SpringLayout.NORTH, lblCurrTime, SECTION_SPACING, SpringLayout.SOUTH, lblSampleNum);
		layout.putConstraint(SpringLayout.EAST, lblCurrTime, LABEL_WIDTH, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, currTime, 0, SpringLayout.VERTICAL_CENTER, lblCurrTime);
		layout.putConstraint(SpringLayout.WEST, currTime, 10, SpringLayout.EAST, lblCurrTime);
		
		// layout lblThawCount
		layout.putConstraint(SpringLayout.NORTH, lblThawCount, VERTICAL_SPACING, SpringLayout.SOUTH, lblCurrTime);
		layout.putConstraint(SpringLayout.EAST, lblThawCount, LABEL_WIDTH, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, thawCount, 0, SpringLayout.VERTICAL_CENTER, lblThawCount);
		layout.putConstraint(SpringLayout.WEST, thawCount, 10, SpringLayout.EAST, lblThawCount);
		
		// layout lblPurpose
		layout.putConstraint(SpringLayout.NORTH, lblPurpose, VERTICAL_SPACING, SpringLayout.SOUTH, lblThawCount);
		layout.putConstraint(SpringLayout.EAST, lblPurpose, LABEL_WIDTH, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, txtPurposeScroll, 0, SpringLayout.NORTH, lblPurpose);
		layout.putConstraint(SpringLayout.WEST, txtPurposeScroll, 10, SpringLayout.EAST, lblPurpose);
		
		// layout lblFreezerDesc
		layout.putConstraint(SpringLayout.NORTH, lblFreezerDesc, SECTION_SPACING - 5, SpringLayout.SOUTH, txtPurposeScroll);
		layout.putConstraint(SpringLayout.EAST, lblFreezerDesc, LABEL_WIDTH, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, freezerDesc, 0, SpringLayout.VERTICAL_CENTER, lblFreezerDesc);
		layout.putConstraint(SpringLayout.WEST, freezerDesc, 10, SpringLayout.EAST, lblFreezerDesc);

		// layout lblFreezerDesc
		layout.putConstraint(SpringLayout.NORTH, lblFreezerCell, VERTICAL_SPACING, SpringLayout.SOUTH, lblFreezerDesc);
		layout.putConstraint(SpringLayout.EAST, lblFreezerCell, LABEL_WIDTH, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, freezerCell, 0, SpringLayout.VERTICAL_CENTER, lblFreezerCell);
		layout.putConstraint(SpringLayout.WEST, freezerCell, 10, SpringLayout.EAST, lblFreezerCell);
		
		// layout lblFreezerDesc
		layout.putConstraint(SpringLayout.NORTH, lblBoxId, VERTICAL_SPACING, SpringLayout.SOUTH, lblFreezerCell);
		layout.putConstraint(SpringLayout.EAST, lblBoxId, LABEL_WIDTH, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, boxId, 0, SpringLayout.VERTICAL_CENTER, lblBoxId);
		layout.putConstraint(SpringLayout.WEST, boxId, 10, SpringLayout.EAST, lblBoxId);
		
		// layout btnCancel
		layout.putConstraint(SpringLayout.NORTH, btnCancel, SECTION_SPACING, SpringLayout.SOUTH, lblBoxId);
		layout.putConstraint(SpringLayout.WEST, btnCancel, 0, SpringLayout.WEST, lblBoxId);
		
		// layout btnCheckOut
		layout.putConstraint(SpringLayout.NORTH, btnCheckOut, SECTION_SPACING, SpringLayout.SOUTH, lblBoxId);
		layout.putConstraint(SpringLayout.WEST, btnCheckOut, 10, SpringLayout.EAST, btnCancel);
		
		add(lblSampleNum);
		add(txtSampleNum);
		add(lblCurrTime);
		add(currTime);
		add(lblThawCount);
		add(thawCount);
		add(lblPurpose);
		add(txtPurposeScroll);
		add(lblFreezerDesc);
		add(freezerDesc);
		add(lblFreezerCell);
		add(freezerCell);
		add(lblBoxId);
		add(boxId);
		add(btnCancel);
		add(btnCheckOut);
	}
	
	private void constructComponents() {
		lblSampleNum = new JLabel("Sample Number: ");
		txtSampleNum = new JTextField(20);
		
		lblCurrTime = new JLabel("Date/Time: ");
		currTime = new JLabel("Current time here");
		
		lblThawCount = new JLabel("Thaw Count: ");
		thawCount = new JLabel("Thaw count here");
		
		lblPurpose = new JLabel("Purpose: ");
		txtPurpose = new JTextArea(3, 20);
		txtPurpose.setLineWrap(true);
		txtPurpose.setWrapStyleWord(true);
		txtPurposeScroll = new JScrollPane(txtPurpose);
		
		lblFreezerDesc = new JLabel("Freezer: ");
		freezerDesc = new JLabel("Freezer desc here");
		
		lblFreezerCell = new JLabel("Position: ");
		freezerCell = new JLabel("Freezer cell here");
		
		lblBoxId = new JLabel("Box ID: ");
		boxId = new JLabel("Box id here");
		
		btnCheckOut = new JButton("Check Out");
		btnCancel = new JButton("Cancel");
	}
}
