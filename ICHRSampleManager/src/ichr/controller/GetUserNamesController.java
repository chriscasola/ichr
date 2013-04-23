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
import ichr.database.DataStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris Casola
 * @version Apr 17, 2013
 *
 */
public class GetUserNamesController {

	public static String[] getUserNames() {
		List<String> retVal = new ArrayList<String>();
		PreparedStatement s;
		try {
			s = DataStore.getDB().getPreparedStatement(
					"SELECT username FROM users"
			);
			final ResultSet rs = s.executeQuery();
			
			while (rs.next()) {
				retVal.add(rs.getString("username"));
			}
			
			rs.close();
			DataStore.getDB().closeStatement(s);
		}
		catch (SQLException e) {
			System.err.println("Error occurred retrieving usernames!");
			e.printStackTrace();
		}
		catch (ICHRException e) {
			System.err.println("Error occurred retrieving usernames!");
			e.printStackTrace();
		}
		
		return retVal.toArray(new String[0]);
	}
}
