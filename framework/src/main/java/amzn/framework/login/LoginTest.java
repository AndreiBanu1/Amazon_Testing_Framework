package amzn.framework.login;

import amzn.framework.core.BaseTest;
import amzn.pageobjects.loginpage.LoginPage;
import amzn.domain.login.LoginCredentials;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import amzn.domain.constants.TestConstants;

public class LoginTest extends BaseTest {
    private WebDriver driver;
    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void testInvalidUsername() {
        LoginPage loginPage = new LoginPage(driver);
        driver.get(TestConstants.WEBSITE);

        loginPage.clickLoginButton();
        loginPage.enterUsername("yourUsername");
        loginPage.clickContinueButton();
        try {
            if (loginPage.importantMessageIsDisplayed()) {
                System.out.println("Test Case testInvalidUsername is invalid: Captcha required!!!");
            }
        } catch (NoSuchElementException e) {
        }
        try {
            if (loginPage.errorContainerIsDisplayed()) {
                System.out.println("Test Case testInvalidUsername Passed: Error container displayed for Invalid Username");
            } else {
                System.out.println("Test Case testInvalidUsername Failed:");

            }
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void testInvalidPassword() {
        LoginCredentials loginCredentials = new LoginCredentials();
        LoginPage loginPage = new LoginPage(driver);
        driver.get(TestConstants.WEBSITE);

        loginPage.clickLoginButton();
        loginPage.enterUsername(loginCredentials.getUsername());
        loginPage.clickContinueButton();
        loginPage.enterPassword("yourPassword");
        loginPage.clickSignInSubmit();

        try {
            if (loginPage.importantMessageIsDisplayed()) {
                System.out.println("Test Case testInvalidPassword is invalid: Captcha required!!!");
            }
        } catch (NoSuchElementException e) {
        }
        try {
            if (loginPage.errorContainerIsDisplayed()) {
                System.out.println("Test Case testInvalidPassword Passed: Error container displayed for Invalid Password");
            } else {
                System.out.println("Test Case testInvalidPassword Failed:");
            }
        } catch (NoSuchElementException e) {
        }
    }

    @Test()
    public void testValidLogin() {
        LoginCredentials loginCredentials = new LoginCredentials();
        LoginPage loginPage = new LoginPage(driver);
        driver.get(TestConstants.WEBSITE);

        loginPage.clickLoginButton();
        loginPage.enterUsername(loginCredentials.getUsername());
        loginPage.clickContinueButton();
        loginPage.enterPassword(loginCredentials.getPassword());
        loginPage.clickSignInSubmit();

        try {
            if (loginPage.importantMessageIsDisplayed()) {
                System.out.println("Test Case testValidLogin is invalid: Captcha required!!!");
            } else {
                System.out.println("Test Case testValidLogin Passed: User is logged in");
            }
        } catch (NoSuchElementException e) {
        }
    }
}
