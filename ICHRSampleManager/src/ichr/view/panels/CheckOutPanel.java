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

import static ichr.view.main.MainView.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import ichr.controller.RetrieveSampleController;

import static javax.swing.SpringLayout.*;

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
	protected JLabel lblStatus;
	protected JLabel lblCurrTime;
	protected JLabel currTime;
	protected JLabel lblThawCount;
	protected JLabel thawCount;
	protected JLabel lblComment;
	protected JTextArea txtComment;
	protected JScrollPane txtCommentScroll;
	protected JLabel lblFreezerDesc;
	protected JLabel freezerDesc;
	protected JLabel lblFreezerCell;
	protected JLabel freezerCell;
	protected JLabel lblBoxId;
	protected JLabel boxId;
	protected JButton btnCheckOut;
	protected JButton btnCancel;
	protected JLabel lblResult;
	
	public CheckOutPanel() {
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
		
		// layout status label
		layout.putConstraint(VERTICAL_CENTER, lblStatus, 0, VERTICAL_CENTER, txtSampleNum);
		layout.putConstraint(WEST, lblStatus, VERTICAL_SPACING, EAST, txtSampleNum);
		
		// layout lblCurrTime
		layout.putConstraint(NORTH, lblCurrTime, SECTION_SPACING, SOUTH, lblSampleNum);
		layout.putConstraint(EAST, lblCurrTime, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, currTime, 0, VERTICAL_CENTER, lblCurrTime);
		layout.putConstraint(WEST, currTime, 10, EAST, lblCurrTime);
		
		// layout lblThawCount
		layout.putConstraint(NORTH, lblThawCount, VERTICAL_SPACING, SOUTH, lblCurrTime);
		layout.putConstraint(EAST, lblThawCount, LABEL_WIDTH, WEST, this);
		layout.putConstraint(VERTICAL_CENTER, thawCount, 0, VERTICAL_CENTER, lblThawCount);
		layout.putConstraint(WEST, thawCount, 10, EAST, lblThawCount);
		
		// layout lblComment
		layout.putConstraint(NORTH, lblComment, VERTICAL_SPACING, SOUTH, lblThawCount);
		layout.putConstraint(EAST, lblComment, LABEL_WIDTH, WEST, this);
		layout.putConstraint(NORTH, txtCommentScroll, 0, NORTH, lblComment);
		layout.putConstraint(WEST, txtCommentScroll, 10, EAST, lblComment);
		
		// layout lblFreezerDesc
		layout.putConstraint(NORTH, lblFreezerDesc, SECTION_SPACING - 5, SOUTH, txtCommentScroll);
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
		layout.putConstraint(NORTH, btnCheckOut, SECTION_SPACING, SOUTH, lblBoxId);
		layout.putConstraint(WEST, btnCheckOut, 10, EAST, btnCancel);
		
		// layout lblResult
		layout.putConstraint(VERTICAL_CENTER, lblResult, 0, VERTICAL_CENTER, btnCheckOut);
		layout.putConstraint(WEST, lblResult, VERTICAL_SPACING, EAST, btnCheckOut);
		
		add(lblSampleNum);
		add(txtSampleNum);
		add(lblStatus);
		add(lblCurrTime);
		add(currTime);
		add(lblThawCount);
		add(thawCount);
		add(lblComment);
		add(txtCommentScroll);
		add(lblFreezerDesc);
		add(freezerDesc);
		add(lblFreezerCell);
		add(freezerCell);
		add(lblBoxId);
		add(boxId);
		add(btnCancel);
		add(btnCheckOut);
		add(lblResult);
	}
	
	private void constructComponents() {
		lblSampleNum = new JLabel("Sample Number: ");
		txtSampleNum = new JTextField(20);
		
		lblStatus = new JLabel("");
		lblStatus.setForeground(Color.blue);
		
		lblCurrTime = new JLabel("Date/Time: ");
		currTime = new JLabel("");
		
		lblThawCount = new JLabel("Thaw Count: ");
		thawCount = new JLabel("");
		
		lblComment = new JLabel("Purpose: ");
		txtComment = new JTextArea(3, 20);
		txtComment.setLineWrap(true);
		txtComment.setWrapStyleWord(true);
		txtCommentScroll = new JScrollPane(txtComment);
		
		lblFreezerDesc = new JLabel("Freezer: ");
		freezerDesc = new JLabel("");
		
		lblFreezerCell = new JLabel("Position: ");
		freezerCell = new JLabel("");
		
		lblBoxId = new JLabel("Box ID: ");
		boxId = new JLabel("");
		
		btnCheckOut = new JButton("Check Out");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				clearFields();
				txtSampleNum.setText("");
				txtComment.setText("");
			}
		});
		
		lblResult = new JLabel("");
		lblResult.setForeground(Color.blue);
	}

	public void fillInFields(RetrieveSampleController controller) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm MM-dd-yyyy");
		currTime.setText(formatter.format(new Date()));
		thawCount.setText(String.valueOf(controller.getThawCount()));
		freezerDesc.setText(controller.getFreezerName());
		freezerCell.setText("Row: " + controller.getFreezerRow() + "   Column: " + controller.getFreezerCol());
		boxId.setText(controller.getBoxId());
		lblStatus.setText("Valid Sample");
	}
	
	public void clearFields() {
		currTime.setText("");
		thawCount.setText("");
		freezerDesc.setText("");
		freezerCell.setText("");
		boxId.setText("");
		lblStatus.setText("");
	}
	
	public void showMessage(String message) {
		lblResult.setText(message);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				lblResult.setText("");
			}
		}, 5000);
	}
	
	public JButton getBtnCheckOut() {
		return btnCheckOut;
	}
	
	public JTextField getSampleNumField() {
		return txtSampleNum;
	}
	
	public JTextArea getCommentField() {
		return txtComment;
	}
}
