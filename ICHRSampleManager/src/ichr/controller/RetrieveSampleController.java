/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.controller;

import ichr.ICHRException;
import ichr.ICHRSampleManager;
import ichr.database.DataStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Chris Casola
 * @version Apr 17, 2013
 *
 */
public class RetrieveSampleController {

	protected String sampleId;
	protected String censusNum;
	protected String plannedUse;
	protected String volume;
	protected String boxId;
	protected int thawCount;
	protected boolean isEmpty;
	protected int freezerId;
	protected int freezerRow;
	protected int freezerCol;
	protected String freezerName;
	
	protected boolean isValid = false;
	
	public boolean isValidId(String sampleId) {
		PreparedStatement s = null;
		
		try {
			s = DataStore.getDB().getPreparedStatement(
					"SELECT * FROM samples, freezer_shelves, freezers " +
					"WHERE samples.box_id = freezer_shelves.box_id " +
					"AND freezers.freezer_id = freezer_shelves.freezer_id " +
					"AND samples.sample_id = ?"
			);
			s.setString(1, sampleId);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				this.sampleId = rs.getString("sample_id");
				censusNum = rs.getString("census_num");
				plannedUse = rs.getString("planned_use");
				volume = rs.getString("volume");
				boxId = rs.getString("box_id");
				thawCount = rs.getInt("thaw_count");
				isEmpty = rs.getBoolean("is_empty");
				freezerId = rs.getInt("freezer_id");
				freezerRow = rs.getInt("row");
				freezerCol = rs.getInt("col");
				freezerName = rs.getString("freezer_desc");
				isValid = true;
			}
			else {
				isValid = false;
			}
			
			DataStore.getDB().closeStatement(s);
			return isValid;
		}
		catch (ICHRException e) {
			System.err.println("Error validating sample ID");
			isValid = false;
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.err.println("Error validating sample ID");
			isValid = false;
			e.printStackTrace();
		}
		finally {
			DataStore.getDB().closeStatement(s);
		}
		
		return false;
	}
	
	public boolean checkInSample(String comment, boolean isEmpty) {
		if (!isAlreadyCheckedOut()) {
			return false;
		}
		
		PreparedStatement s = null;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"UPDATE sample_uses " +
					"SET time_in = CURRENT_TIMESTAMP, comment_out = ? " +
					"WHERE sample_id = ? " +
					"AND time_in = '0000-00-00 00:00:00'"
			);
			s.setString(1, comment);
			s.setString(2, sampleId);
			if (s.executeUpdate() > 0) {
				DataStore.getDB().getConnection().commit();
				DataStore.getDB().closeStatement(s);
				
				if (isEmpty) {
					s = DataStore.getDB().getPreparedStatement(
							"UPDATE samples SET is_empty = ? " +
							"WHERE sample_id = ?"
					);
					s.setBoolean(1, true);
					s.setString(2, sampleId);
					s.executeUpdate();
					DataStore.getDB().getConnection().commit();
				}
				
				return true;
			}
			
		}
		catch (ICHRException e) {
			System.out.println("Error occurred checking in sample");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error occurred checking in sample");
			e.printStackTrace();
		}
		finally {
			DataStore.getDB().closeStatement(s);
		}
		
		return false;
	}
	
	public boolean checkOutSample(String comment) {
		if (isAlreadyCheckedOut()) {
			return false;
		}
		
		PreparedStatement s = null;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"INSERT INTO sample_uses (sample_id, username, time_out, comment_out) VALUES " +
					"(?, ?, CURRENT_TIMESTAMP, ?)"
			);
			s.setString(1, sampleId);
			s.setString(2, ICHRSampleManager.getUsername());
			s.setString(3, comment);
			if (s.executeUpdate() > 0) {
				s.close();
				s = DataStore.getDB().getPreparedStatement(
						"UPDATE samples SET thaw_count = thaw_count + 1 " +
						"WHERE sample_id = ?"
				);
				s.setString(1, sampleId);
				s.executeUpdate();
				DataStore.getDB().getConnection().commit();
				return true;
			}
		}
		catch (ICHRException e) {
			System.err.println("Error checking out sample");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.err.println("Error checking out sample");
			e.printStackTrace();
		}
		finally {
			DataStore.getDB().closeStatement(s);
		}
		
		return false;
	}
	
	private boolean isAlreadyCheckedOut() {
		boolean checkedOut = false;
		PreparedStatement s = null;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"SELECT sample_id FROM sample_uses " +
					"WHERE sample_id = ? " +
					"AND time_in = '0000-00-00 00:00:00'"
			);
			s.setString(1, sampleId);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				checkedOut = true;
			}
			rs.close();
		}
		catch (ICHRException e) {
			System.err.println("Error checking out sample");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.err.println("Error checking out sample");
			e.printStackTrace();
		}
		finally {
			DataStore.getDB().closeStatement(s);
		}
		
		return checkedOut;
	}
	
	/**
	 * @return the sampleId
	 */
	public String getSampleId() {
		return sampleId;
	}

	/**
	 * @return the censusNum
	 */
	public String getCensusNum() {
		return censusNum;
	}

	/**
	 * @return the plannedUse
	 */
	public String getPlannedUse() {
		return plannedUse;
	}

	/**
	 * @return the volume
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @return the boxId
	 */
	public String getBoxId() {
		return boxId;
	}

	/**
	 * @return the thawCount
	 */
	public int getThawCount() {
		return thawCount;
	}

	/**
	 * @return the isEmpty
	 */
	public boolean isEmpty() {
		return isEmpty;
	}

	/**
	 * @return the freezerId
	 */
	public int getFreezerId() {
		return freezerId;
	}

	/**
	 * @return the freezerRow
	 */
	public int getFreezerRow() {
		return freezerRow;
	}

	/**
	 * @return the freezerCol
	 */
	public int getFreezerCol() {
		return freezerCol;
	}
	
	/**
	 * @return the freezerName
	 */
	public String getFreezerName() {
		return freezerName;
	}
}
