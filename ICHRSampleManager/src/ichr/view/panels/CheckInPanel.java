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
	
	
	public static final int LABEL_WIDTH = 200;
	public static final int SECTION_SPACING = 35;
	public static final int VERTICAL_SPACING = 10;

	public CheckInPanel() {
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
		
		// layout empty radio buttons
		layout.putConstraint(SpringLayout.NORTH, lblEmpty, SECTION_SPACING, SpringLayout.SOUTH, lblSampleNum);
		layout.putConstraint(SpringLayout.EAST, lblEmpty, LABEL_WIDTH, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, btnEmptyNo, 0, SpringLayout.VERTICAL_CENTER, lblEmpty);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, btnEmptyYes, 0, SpringLayout.VERTICAL_CENTER, lblEmpty);
		layout.putConstraint(SpringLayout.WEST, btnEmptyNo, 10, SpringLayout.EAST, lblEmpty);
		layout.putConstraint(SpringLayout.WEST, btnEmptyYes, 10, SpringLayout.EAST, btnEmptyNo);
		
		// layout date/time
		layout.putConstraint(SpringLayout.NORTH, lblCurrTime, VERTICAL_SPACING, SpringLayout.SOUTH, lblEmpty);
		layout.putConstraint(SpringLayout.EAST, lblCurrTime, LABEL_WIDTH, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, currTime, 0, SpringLayout.VERTICAL_CENTER, lblCurrTime);
		layout.putConstraint(SpringLayout.WEST, currTime, 10, SpringLayout.EAST, lblCurrTime);
		
		// layout thaw count
		layout.putConstraint(SpringLayout.NORTH, lblThawCount, VERTICAL_SPACING, SpringLayout.SOUTH, lblCurrTime);
		layout.putConstraint(SpringLayout.EAST, lblThawCount, LABEL_WIDTH, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, thawCount, 0, SpringLayout.VERTICAL_CENTER, lblThawCount);
		layout.putConstraint(SpringLayout.WEST, thawCount, 10, SpringLayout.EAST, lblThawCount);
		
		// layout comments
		layout.putConstraint(SpringLayout.NORTH, lblComments, VERTICAL_SPACING, SpringLayout.SOUTH, lblThawCount);
		layout.putConstraint(SpringLayout.EAST, lblComments, LABEL_WIDTH, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, txtCommentsScroll, 0, SpringLayout.NORTH, lblComments);
		layout.putConstraint(SpringLayout.WEST, txtCommentsScroll, 10, SpringLayout.EAST, lblComments);

		// layout lblFreezerDesc
		layout.putConstraint(SpringLayout.NORTH, lblFreezerDesc, SECTION_SPACING - 5, SpringLayout.SOUTH, txtCommentsScroll);
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
		layout.putConstraint(SpringLayout.NORTH, btnCheckIn, SECTION_SPACING, SpringLayout.SOUTH, lblBoxId);
		layout.putConstraint(SpringLayout.WEST, btnCheckIn, 10, SpringLayout.EAST, btnCancel);
		
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
