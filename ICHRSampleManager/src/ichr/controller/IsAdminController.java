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
public class IsAdminController {

	public static boolean isAdmin() {
		PreparedStatement s = null;
		boolean isAdmin = false;
		
		try {
			s = DataStore.getDB().getPreparedStatement(
					"SELECT role " +
					"FROM users " +
					"WHERE username = ? "
			);
			s.setString(1, ICHRSampleManager.getUsername());
			ResultSet rs = s.executeQuery();
			if (rs.next() && rs.getString("role").equals("admin")) {
				isAdmin = true;
			}
			rs.close();
			
		} catch (ICHRException e) {
			System.err.println("Error occurred checking admin privileges!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Error occurred checking admin privileges!");
			e.printStackTrace();
		}
		finally {
			DataStore.getDB().closeStatement(s);
		}
		
		return isAdmin;
	}
}
