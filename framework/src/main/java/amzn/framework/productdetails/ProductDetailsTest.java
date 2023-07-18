package amzn.framework.productdetails;

import amzn.domain.constants.TestConstants;
import amzn.framework.core.BaseTest;
import amzn.pageobjects.productdetails.ProductDetails;
import amzn.pageobjects.searchmodule.SearchModule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import amzn.domain.proxies.RotateProxy;

import java.util.List;

public class ProductDetailsTest extends BaseTest {
    private WebDriver driver;
    private RotateProxy rotateProxy;
//    @BeforeTest
//    public void setup() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//
//        // Retrieve the list of proxy data
//        List<String> proxies = RotateProxy.getFreeProxies();
//        rotateProxy = new RotateProxy(proxies);
//        // Get the index of the current proxy to use
//        int currentIndex = rotateProxy.getCurrentIndex();
//        // Set the proxy for rotation
//        options.addArguments("--proxy-server=" + rotateProxy.getCurrentProxy());
//        driver = new ChromeDriver(options);
//    }

//    @BeforeMethod
//    public void beforeEachTest() {
//        // Rotate to the next proxy for each test method
//        String nextProxy = rotateProxy.getNextProxy();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--proxy-server=" + nextProxy);
//        driver = new ChromeDriver(options);
//    }
    @Test
    public void verifyItemTitle() {
        ProductDetails productDetails = new ProductDetails(driver);
        SearchModule searchModule = new SearchModule(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://www.amazon.com");

        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
        searchModule.clickSearchButton();

        WebElement itemTitleElement = driver.findElement(productDetails.getProductDetailsHeaderLocator());
        String itemTitle = itemTitleElement.getText();

        if (itemTitle.length() > 0) {
            System.out.println("Test Case verifyItemTitle Passed: Item title exists. " + itemTitle);
        } else {
            System.out.println("Test Case verifyItemTitle Failed: Item title is empty or not found");
        }
    }
}
