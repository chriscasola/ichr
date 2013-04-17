/*******************************************************************************
 * Copyright (c) 2013 -- Steve Berselli, Chris Casola,
 *							Silvia Zamora-Palacios, Dongni Zhang
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package ichr.database;

import ichr.ICHRException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class to provide access to the database
 * 
 * @author Chris Casola
 * @version Apr 7, 2013
 *
 */
public class DataStore {

	private static DataStore instance = null;
	private Connection connection = null;
	
	/*private static final String PROD_DB = "ichr";
	private static final String PROD_USER = "ichr";
	private static final String PROD_PWD = "P@ssw0rd";*/
	
	private static final String PROD_DB = "ichr";
	private static final String PROD_USER = "ichr";
	private static final String PROD_PWD = "mis4720wpi13";
	
	private static final String TEST_DB = "ichr_test";
	private static final String TEST_USER = "ichr";
	private static final String TEST_PWD = "mis4720wpi13";
	
	private static final String MYSQL_SERVER = "ec2-54-234-148-102.compute-1.amazonaws.com:3306";
	
	/** The length of the username field */
	public static final int USER_NAME_LEN = 15;
	
	/** The length of the password field */
	public static final int PASSWORD_LEN = 15;
	
	private DataStore() {
		// Use the mysql jdbc driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could not locate MYSQL connector", e);
		}
	}

	/**
	 * @return the singleton instance of the DataStore
	 */
	public static DataStore getDB() {
		if (instance == null) {
			instance = new DataStore();
		}
		return instance;
	}

	/**
	 * Open a connection to the database if the connection is not
	 * already open.
	 * 
	 * @throws SQLException if a connection could not be opened
	 */
	public void openConnection() throws SQLException {
		System.out.println("Opening database connection.");
		if (connection == null) {
			connection = DriverManager.getConnection("jdbc:mysql://" + MYSQL_SERVER + "/" + 
					PROD_DB + "?" + "user=" + PROD_USER + "&password=" + PROD_PWD + "&zeroDateTimeBehavior=convertToNull");
		}
		connection.setAutoCommit(false);
	}
	
	/**
	 * Close the connection to the database if there is one
	 * open.
	 */
	public void closeConnection() {
		System.out.println("Closing database connection.");
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				System.err.println("Error occurred closing database.");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return the database connection
	 */
	public Connection getConnection() {
			return connection;
	}
	
	public PreparedStatement getPreparedStatement(String query) throws ICHRException {
		final PreparedStatement s;
		try {
			s = connection.prepareStatement(query);
		} catch (SQLException e) {
			throw new ICHRException("Could not create statement.", e);
		}
		return s;
	}
	
	public void closeStatement(Statement s) {
		try {
			s.close();
		} catch (SQLException e) {
			System.err.println("Error closing statmenet!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Recreate the application's tables (REMOVES ALL DATA)
	 */
	public void createTables() {
		executeSQLFile("./sql/drop.sql");
		executeSQLFile("./sql/create.sql");
		executeSQLFile("./sql/insert.sql");
		generateEmptyFreezerGrids();
	}
	
	private void generateEmptyFreezerGrids() {
		PreparedStatement s = null;
		try {
			s = getPreparedStatement(
					"INSERT INTO freezer_shelves VALUES " +
					"(?, ?, ?, NULL);");
			for (int i = 1; i <= 5; i++) { // for each freezer
				for (int j = 1; j <= 9; j++) { // for each column
					for (int k = 1; k <= 9; k++) { // for each row
						s.setInt(1, i);
						s.setInt(2, k);
						s.setInt(3, j);
						s.executeUpdate();
					}
				}
			}
			getConnection().commit();
		}
		catch (ICHRException e) {
			System.err.println("Error initializing freezer shelves!");
			e.printStackTrace();
		} 
		catch (SQLException e) {
			System.err.println("Error initializing freezer shelves!");
			e.printStackTrace();
		}
		finally {
			closeStatement(s);
		}
	}
	
	public void executeSQLFile(String filePath) {
		Statement s = null;
		try {
			List<String> statements = readSQLFile(filePath);
			s = connection.createStatement();
			for (String statement : statements) {
				s.addBatch(statement);
			}
			final int[] results = s.executeBatch();
			for (int i = 0; i < results.length; i++) {
				if (results[i] == Statement.EXECUTE_FAILED) {
					throw new ICHRException("error in sql file");
				}
			}
			connection.commit();
		}
		catch (ICHRException e) {
			System.err.println("Unable to execute SQL file: " + filePath);
		}
		catch (SQLException e) {
			System.err.println("Database error occurred while trying to execute SQL file: " + filePath + "\n\t" + e.getMessage());
		}
		finally {
			if (s != null) {
				closeStatement(s);
			}
		}
	}
	
	private List<String> readSQLFile(String filePath) throws ICHRException {
		BufferedReader sqlFile = null;
		List<String> statements = new ArrayList<String>();
		String currStatement= "";
		
		try {
			sqlFile = new BufferedReader(new FileReader(filePath));
			int c;
			while ((c = sqlFile.read()) != -1) {
				if (c == (int)';') {
					statements.add(currStatement);
					currStatement = "";
				}
				else {
					currStatement += (char)c;
				}
			}
		}
		catch (FileNotFoundException e) {
			throw new ICHRException("Could not open SQL file", e);
		}
		catch (IOException e) {
			throw new ICHRException("Error reading SQL file", e);
		}
		finally {
			if (sqlFile != null) {
				try {sqlFile.close();} catch (IOException e) {throw new ICHRException("Could not close SQL file", e);}
			}
		}
		
		return statements;
	}
	
	/**
	 * Opens a connection to the test database
	 * 
	 * @throws SQLException if the connection could not be opened
	 */
	public void openTestConnection() throws SQLException {
		System.out.println("Opening database connection.");
		if (connection == null) {
			connection = DriverManager.getConnection("jdbc:mysql://" + MYSQL_SERVER + "/" + 
					TEST_DB + "?" + "user=" + TEST_USER + "&password=" + TEST_PWD + "&zeroDateTimeBehavior=convertToNull");
		}
		connection.setAutoCommit(false);
	}
}
