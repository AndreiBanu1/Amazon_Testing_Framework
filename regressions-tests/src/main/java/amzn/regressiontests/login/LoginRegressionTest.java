package amzn.regressiontests.login;

import amzn.framework.login.LoginTest;
import amzn.pageobjects.loginpage.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginRegressionTest extends LoginTest {
    private WebDriver driver;
    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        // Perform login actions using the page object methods
        loginPage.enterUsername("yourUsername");
        loginPage.enterPassword("yourPassword");
        loginPage.clickLoginButton();

        // Add assertions or verifications to validate the login functionality
        // Assert.assertTrue(...);
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        // Perform login actions for invalid credentials
        // Add assertions or verifications for expected error messages or behavior
    }
}