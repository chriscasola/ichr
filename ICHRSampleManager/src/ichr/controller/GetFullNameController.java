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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ichr.ICHRException;
import ichr.ICHRSampleManager;
import ichr.database.DataStore;

/**
 * @author Chris Casola
 * @version Apr 17, 2013
 *
 */
public class GetFullNameController {
	
	private static String firstName = null;
	private static String lastName = null;

	public static String getFirst() {
		if (firstName == null) {
			getName();
		}
		return firstName;
	}
	
	public static String getLast() {
		if (lastName == null) {
			getName();
		}
		return lastName;
	}
	
	public static void getName() {
		PreparedStatement s = null;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"SELECT first, last " +
					"FROM users, contacts " +
					"WHERE users.contact_id = contacts.contact_id " +
					"AND users.username = ?"
			);
			s.setString(1, ICHRSampleManager.getUsername());
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				firstName = rs.getString("first");
				lastName = rs.getString("last");
				firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
				lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
			}
			rs.close();
		}
		catch (ICHRException e) {
			System.err.println("Error occurred retrieving full name.");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.err.println("Error occurred retrieving full name.");
			e.printStackTrace();
		}
		finally {
			DataStore.getDB().closeStatement(s);
		}
	}
}
