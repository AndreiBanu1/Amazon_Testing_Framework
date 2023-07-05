package amzn.pageobjects.loginpage;

import amzn.framework.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By usernameField = By.id("ap_email");
    private By continueButton = By.id("continue");
    private By passwordField = By.id("ap_password");
    private By loginButton = By.id("nav-link-accountList");
    private By errorContainer = By.className("a-alert-container");
    private By signInSubmit = By.id("signInSubmit");
    private By importantMessage = By.xpath("//*[@id=\"auth-warning-message-box\"]/div");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public void clickContinueButton() { driver.findElement(continueButton).click(); }
    public void clickSignInSubmit() { driver.findElement(signInSubmit).click(); }

    public boolean errorContainerIsDisplayed() { driver.findElement(errorContainer).isDisplayed();
        return true;
    }
    public boolean importantMessageIsDisplayed() { driver.findElement(importantMessage).isDisplayed();
        return true;
    }
}