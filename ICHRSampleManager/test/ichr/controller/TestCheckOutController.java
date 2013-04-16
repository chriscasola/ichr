package ichr.controller;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ichr.ICHRException;
import ichr.ICHRSampleManager;
import ichr.database.DataStore;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestCheckOutController {
	
	//private static CheckOutPanel view;
	
	private static CheckOutController controller;
	
	@BeforeClass
	public static void setup() throws SQLException {
		ICHRSampleManager.setUsername("testuser");
		DataStore.getDB().openTestConnection();
		DataStore.getDB().createTables();
		addTestData();
		//view = new CheckOutPanel();
		//controller = new CheckOutController(new JDialog(), view);
	}
	
	@AfterClass
	public static void tearDown() {
		DataStore.getDB().executeSQLFile("./sql/drop.sql");
		DataStore.getDB().closeConnection();
	}
	
	@Ignore
	@Test(expected=ICHRException.class)
	public void checkOutInvalidSample() throws ICHRException {
		controller.checkOutSample("badsample", "testuser");
		
		PreparedStatement s;
		try {
			// Query the database for the check out entry
			s = DataStore.getDB().getPreparedStatement("SELECT * FROM sample_uses WHERE username=? AND sample_barcode=?");
			s.setString(1, "testuser");
			s.setString(2, "badsample");
			final ResultSet rs = s.executeQuery();
	
			// Check that the check out entry was not created
			if (rs.next()) {
				fail("Sample should not have been checked out!");
			}
		}
		catch (SQLException e) {
			fail("Above code should succeed. Error: " + e.getMessage());
		}
	}

	@Ignore
	@Test
	public void checkOutValidSample() throws ICHRException {
		controller.checkOutSample("sample1", "testuser");
		
		PreparedStatement s;
		try {
			// Query the database for the check out entry
			s = DataStore.getDB().getPreparedStatement("SELECT * FROM sample_uses WHERE username=? AND sample_barcode=?");
			s.setString(1, "testuser");
			s.setString(2, "sample1");
			final ResultSet rs = s.executeQuery();
	
			// Check that the check out entry exists
			if (!rs.next() || !rs.getString("sample_barcode").equals("sample1")) {
				fail("Sample was not checked out!");
			}
		}
		catch (SQLException e) {
			fail("Above code should succeed. Error: " + e.getMessage());
		}
	}

	private static void addTestData() {
		DataStore.getDB().executeSQLFile("./sql/test_data.sql");
	}
}
