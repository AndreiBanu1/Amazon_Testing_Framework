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
    @BeforeTest
    public void setup() {
        // Create an instance of RotateProxy
        rotateProxy = new RotateProxy();

        // Get the next available proxy IP and port
        String nextProxy = rotateProxy.getRandomProxy();
        String[] proxyComponents = nextProxy.split(":");
        String nextProxyIP = proxyComponents[0];
        String nextProxyPort = proxyComponents[1];

        // Printing the current proxy being used
        System.out.println("Current IP: " + nextProxyIP);
        System.out.println("Current Port: " + nextProxyPort);
        System.out.println("Current Proxy: " + nextProxyIP + ":" + nextProxyPort);

        // Set the proxy for rotation
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.addArguments("--proxy-server=" + nextProxyIP + ":" + nextProxyPort);

        // Initialize the ChromeDriver with the proxy settings
        driver = new ChromeDriver(options);
    }

    @Test
    public void verifyItemTitle() {
        ProductDetails productDetails = new ProductDetails(driver);
        SearchModule searchModule = new SearchModule(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(TestConstants.WEBSITE);

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
