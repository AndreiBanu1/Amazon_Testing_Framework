package amzn.regressiontests.login;

import amzn.framework.login.LoginTest;
import org.testng.annotations.Test;

public class LoginRegressionTest extends LoginTest {
    @Test
    public void testInvalidUsernameScenario() {
        testInvalidUsername();
    }

    @Test
    public void testInvalidPasswordScenario() {
        testInvalidPassword();
    }

    @Test
    public void testValidLoginScenario() {
        testValidLogin();
    }
}