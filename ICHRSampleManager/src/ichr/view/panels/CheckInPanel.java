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

import static ichr.view.MainTabView.*;

import static javax.swing.SpringLayout.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
public class CheckInPanel extends JPanel {
	
	private final SpringLayout layout;
	
	protected JLabel lblSampleNum;
	protected JTextField txtSampleNum;
	protected JLabel lblEmpty;
	protected ButtonGroup emptyGroup;
	protected JRadioButton btnEmptyNo;
	protected JRadioButton btnEmptyYes;
	protected JLabel lblCurrTime;
	protected JLabel currTime;
	protected JLabel lblThawCount;
	protected JLabel thawCount;
	protected JLabel lblComments;
	protected JTextArea txtComments;
	protected JScrollPane txtCommentsScroll;
	protected JLabel lblFreezerDesc;
	protected JLabel freezerDesc;
	protected JLabel lblFreezerCell;
	protected JLabel freezerCell;
	protected JLabel lblBoxId;
	protected JLabel boxId;
	protected JButton btnCheckIn;
	protected JButton btnCancel;
	
	public CheckInPanel() {
		layout = new SpringLayout();
		setLayout(layout);
		
		constructComponents();
		addComponents();
	}
	
	private void addComponents() {
		// layout lblSampleNum
		layout.putConstraint(NORTH, lblSampleNum, 10, NORTH, this);
		layout.putConstraint(EAST, lblSampleNum, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, txtSampleNum, 0, VERTICAL_CENTER, lblSampleNum);
		layout.putConstraint(WEST, txtSampleNum, 10, EAST, lblSampleNum);
		
		// layout empty radio buttons
		layout.putConstraint(NORTH, lblEmpty, SECTION_SPACING, SOUTH, lblSampleNum);
		layout.putConstraint(EAST, lblEmpty, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, btnEmptyNo, 0, VERTICAL_CENTER, lblEmpty);
		layout.putConstraint(VERTICAL_CENTER, btnEmptyYes, 0, VERTICAL_CENTER, lblEmpty);
		layout.putConstraint(WEST, btnEmptyNo, 10, EAST, lblEmpty);
		layout.putConstraint(WEST, btnEmptyYes, 10, EAST, btnEmptyNo);
		
		// layout date/time
		layout.putConstraint(NORTH, lblCurrTime, VERTICAL_SPACING, SOUTH, lblEmpty);
		layout.putConstraint(EAST, lblCurrTime, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, currTime, 0, VERTICAL_CENTER, lblCurrTime);
		layout.putConstraint(WEST, currTime, 10, EAST, lblCurrTime);
		
		// layout thaw count
		layout.putConstraint(NORTH, lblThawCount, VERTICAL_SPACING, SOUTH, lblCurrTime);
		layout.putConstraint(EAST, lblThawCount, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, thawCount, 0, VERTICAL_CENTER, lblThawCount);
		layout.putConstraint(WEST, thawCount, 10, EAST, lblThawCount);
		
		// layout comments
		layout.putConstraint(NORTH, lblComments, VERTICAL_SPACING, SOUTH, lblThawCount);
		layout.putConstraint(EAST, lblComments, LABEL_WIDTH, WEST, this);
		layout.putConstraint(NORTH, txtCommentsScroll, 0, NORTH, lblComments);
		layout.putConstraint(WEST, txtCommentsScroll, 10, EAST, lblComments);

		// layout lblFreezerDesc
		layout.putConstraint(NORTH, lblFreezerDesc, SECTION_SPACING - 5, SOUTH, txtCommentsScroll);
		layout.putConstraint(EAST, lblFreezerDesc, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, freezerDesc, 0, VERTICAL_CENTER, lblFreezerDesc);
		layout.putConstraint(WEST, freezerDesc, 10, EAST, lblFreezerDesc);

		// layout lblFreezerDesc
		layout.putConstraint(NORTH, lblFreezerCell, VERTICAL_SPACING, SOUTH, lblFreezerDesc);
		layout.putConstraint(EAST, lblFreezerCell, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, freezerCell, 0, VERTICAL_CENTER, lblFreezerCell);
		layout.putConstraint(WEST, freezerCell, 10, EAST, lblFreezerCell);

		// layout lblFreezerDesc
		layout.putConstraint(NORTH, lblBoxId, VERTICAL_SPACING, SOUTH, lblFreezerCell);
		layout.putConstraint(EAST, lblBoxId, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, boxId, 0, VERTICAL_CENTER, lblBoxId);
		layout.putConstraint(WEST, boxId, 10, EAST, lblBoxId);

		// layout btnCancel
		layout.putConstraint(NORTH, btnCancel, SECTION_SPACING, SOUTH, lblBoxId);
		layout.putConstraint(WEST, btnCancel, 0, WEST, lblBoxId);

		// layout btnCheckOut
		layout.putConstraint(NORTH, btnCheckIn, SECTION_SPACING, SOUTH, lblBoxId);
		layout.putConstraint(WEST, btnCheckIn, 10, EAST, btnCancel);
		
		add(lblSampleNum);
		add(txtSampleNum);
		add(lblEmpty);
		add(btnEmptyNo);
		add(btnEmptyYes);
		add(lblCurrTime);
		add(currTime);
		add(lblThawCount);
		add(thawCount);
		add(lblComments);
		add(txtCommentsScroll);
		add(lblFreezerDesc);
		add(freezerDesc);
		add(lblFreezerCell);
		add(freezerCell);
		add(lblBoxId);
		add(boxId);
		add(btnCancel);
		add(btnCheckIn);
	}
	
	private void constructComponents() {
		lblSampleNum = new JLabel("Sample Number: ");
		txtSampleNum = new JTextField(20);
		
		lblEmpty = new JLabel("Sample Empty? ");
		emptyGroup = new ButtonGroup();
		btnEmptyNo = new JRadioButton("No");
		btnEmptyYes = new JRadioButton("Yes");
		emptyGroup.add(btnEmptyNo);
		emptyGroup.add(btnEmptyYes);
		emptyGroup.setSelected(btnEmptyNo.getModel(), true);
		
		lblCurrTime = new JLabel("Date/Time: ");
		currTime = new JLabel("Current time here");
		
		lblThawCount = new JLabel("Thaw Count: ");
		thawCount = new JLabel("New thaw count here");
		
		lblComments = new JLabel("Comment: ");
		txtComments = new JTextArea(3, 20);
		txtComments.setLineWrap(true);
		txtComments.setWrapStyleWord(true);
		txtCommentsScroll = new JScrollPane(txtComments);
		
		lblFreezerDesc = new JLabel("Freezer: ");
		freezerDesc = new JLabel("Freezer desc here");
		
		lblFreezerCell = new JLabel("Position: ");
		freezerCell = new JLabel("Freezer cell here");
		
		lblBoxId = new JLabel("Box ID: ");
		boxId = new JLabel("Box id here");
		
		btnCheckIn = new JButton("Check In");
		btnCancel = new JButton("Cancel");
	}
}
