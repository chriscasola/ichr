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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	private static final String PROD_DB = "ichr";
	private static final String PROD_USER = "ichr";
	private static final String PROD_PWD = "P@ssw0rd";
	
	private static final String TEST_DB = "ichr_test";
	private static final String TEST_USER = "ichr";
	private static final String TEST_PWD = "P@ssw0rd";
	
	private static final String MYSQL_SERVER = "ccasola.dyndns.biz:3306";
	
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
					PROD_DB + "?" + "user=" + PROD_USER + "&password=" + PROD_PWD);
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
	
	/**
	 * Creates the application's tables if they do not already exist
	 */
	public void createTables() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.addBatch("CREATE TABLE IF NOT EXISTS users (" +
					"username VARCHAR(" + USER_NAME_LEN + ")," +
					"password VARCHAR(" + PASSWORD_LEN + ")," +
					"PRIMARY KEY (username)" +
					")");
			stmt.addBatch("INSERT INTO users VALUES ('admin', 'password')");
			stmt.executeBatch();
			connection.commit();
		}
		catch (SQLException e) {
			try {
				System.err.println("Rolling back transaction: " + e.getMessage());
				connection.rollback();
			} catch (SQLException e1) {
				System.err.println("Error occurred rolling back transaction.");
				e1.printStackTrace();
			}
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("Could not close statement!");
					e.printStackTrace();
				}
			}
		}
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
					TEST_DB + "?" + "user=" + TEST_USER + "&password=" + TEST_PWD);
		}
		connection.setAutoCommit(false);
	}
}
