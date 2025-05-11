package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;


public class LogoutTests extends TestBase{
	HomePage homeObject;
	    LoginPage loginObject;

	    @Test
	    public void logoutUserSuccessfully() {
	        homeObject = new HomePage(driver);
	        loginObject = new LoginPage(driver);

	        // Step 3: Verify home page is visible
	        Assert.assertTrue(homeObject.homeBtn.isDisplayed(), "Home page not visible!");

	        // Step 4: Click 'Signup / Login'
	        homeObject.openLoginPage();

	        // Step 5: Verify login page
	        Assert.assertTrue(loginObject.loginAccountMessage.isDisplayed(), "Login message not visible!");

	        // Step 6-7: Login with correct data
	        loginObject.userCanLogin("samarabdu@example.com", "correct123");

	        // Step 8: Verify 'Logged in as...'
	        Assert.assertTrue(loginObject.loggedLink.isDisplayed(), "Logged in message not visible!");

	        // Step 9: Logout
	        loginObject.userCanLogout();

	        // Step 10: Verify return to login page
	        Assert.assertTrue(loginObject.loginAccountMessage.isDisplayed(), "User not returned to login page!");
	    }
	}

