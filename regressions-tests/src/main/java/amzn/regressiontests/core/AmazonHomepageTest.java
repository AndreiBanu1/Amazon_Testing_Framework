package amzn.regressiontests.core;

import org.testng.annotations.Test;
import amzn.framework.core.BaseTest;
import amzn.pageobjects.homepage.AmazonHomepage;
public class AmazonHomepageTest extends BaseTest {
    @Test
    public void homepageTests() {

        AmazonHomepage amazonHomepage = new AmazonHomepage(getDriver());
    }
}