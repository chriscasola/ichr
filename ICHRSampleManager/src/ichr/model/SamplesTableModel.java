/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * @author Chris Casola
 * @version Apr 8, 2013
 *
 */
@SuppressWarnings("serial")
public class SamplesTableModel extends AbstractTableModel {
	
	private String[] columnNames;
	
	private List<Object[]> data;
	
	public SamplesTableModel() {
		columnNames = new String[0];
		data = new ArrayList<Object[]>();
	}
	
	public void updateTable(ResultSet rs) {
		try {
			// update column names
			columnNames = new String[rs.getMetaData().getColumnCount()];
			for (int i = 0; i < columnNames.length; i++) {
				columnNames[i] = rs.getMetaData().getColumnLabel(i+1);
			}
			
			// update data
			while (rs.next()) {
				Object[] newSample = new Object[columnNames.length];
				for (int i = 0; i < newSample.length; i++) {
					newSample[i] = rs.getObject(i+1);
				}
				data.add(newSample);
			}
			
		} catch (SQLException e) {
			System.err.println("Error occurred refreshing samples table.");
			e.printStackTrace();
		}
		this.fireTableStructureChanged();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return data.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int col) {
		return data.get(row)[col];
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}
