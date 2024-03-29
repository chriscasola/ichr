package ichr.controller;

import static org.junit.Assert.*;

import java.sql.SQLException;

import ichr.ICHRException;
import ichr.database.DataStore;
import ichr.view.login.LoginView;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for the login controller
 * 
 * @author Chris Casola
 * @version Apr 8, 2013
 * 
 */
public class TestLoginController {
	
	private static LoginView view;
	
	private static LoginController controller;
	
	@BeforeClass
	public static void setup() throws SQLException {
		DataStore.getDB().openTestConnection();
		DataStore.getDB().createTables();
		addTestData();
		view = new LoginView("Test");
		controller = new LoginController(view);
	}
	
	@AfterClass
	public static void tearDown() {
		DataStore.getDB().executeSQLFile("./sql/drop.sql");
		DataStore.getDB().closeConnection();
	}

	@Test
	public void checkCorrectPassword() throws ICHRException {
		controller.checkPassword("testuser", "testpassword");
	}
	
	@Test(expected=ICHRException.class)
	public void checkIncorrectPassword() throws ICHRException {
		controller.checkPassword("testuser", "bla");
	}
	
	@Test(expected=ICHRException.class)
	public void checkPasswordWithInvalidUsername() throws ICHRException {
		controller.checkPassword("bla", "bla");
	}
	
	@Test
	public void loginFormWorksWithCorrectPassword() throws ICHRException {
		view.getLoginPanel().getUserNameField().setText("testuser");
		view.getLoginPanel().getPasswordField().setText("testpassword");
		controller.checkCredentials();
	}
	
	@Test(expected=ICHRException.class)
	public void loginFormWorksWithIncorrectPassword() throws ICHRException {
		view.getLoginPanel().getUserNameField().setText("testuser");
		view.getLoginPanel().getPasswordField().setText("testpswd");
		controller.checkCredentials();
	}
	
	@Test
	public void loginFormHandlesBlankFields() {
		try {
			controller.checkCredentials();
			fail("Check credentials should throw an exception when username/password are blank.");
		}
		catch (ICHRException e) {
			assertEquals("Login Failed: Incorrect password", e.getMessage());
		}
	}

	private static void addTestData() {
		DataStore.getDB().executeSQLFile("./sql/test_data.sql");
	}
}
