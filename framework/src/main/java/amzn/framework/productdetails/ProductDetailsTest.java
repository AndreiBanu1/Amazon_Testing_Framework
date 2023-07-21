package amzn.framework.productdetails;

import amzn.domain.constants.TestConstants;
import amzn.domain.useragent.RotateUserAgent;
import amzn.framework.core.BaseTest;
import amzn.pageobjects.productdetails.ProductDetails;
import amzn.pageobjects.searchmodule.SearchModule;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import amzn.domain.proxies.RotateProxy;

public class ProductDetailsTest extends BaseTest {
    private WebDriver driver;
    private RotateProxy rotateProxy;
    @NotNull
    private ProductDetails getProductDetails() {
        ProductDetails productDetails = new ProductDetails(driver);
        SearchModule searchModule = new SearchModule(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(TestConstants.WEBSITE);

        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
        searchModule.clickSearchButton();
        return productDetails;
    }
    @BeforeTest
    public void setup() {
//        // Create an instance of RotateProxy
//        rotateProxy = new RotateProxy();
//
//        // Get the next available proxy IP and port
//        String nextProxy = rotateProxy.getRandomProxy();
//        String[] proxyComponents = nextProxy.split(":");
//        String nextProxyIP = proxyComponents[0];
//        String nextProxyPort = proxyComponents[1];

//        // Printing the current proxy being used
//        System.out.println("Current IP: " + nextProxyIP);
//        System.out.println("Current Port: " + nextProxyPort);
//        System.out.println("Current Proxy: " + nextProxyIP + ":" + nextProxyPort);

        RotateUserAgent rotateUserAgent = new RotateUserAgent();
        // Set the proxy for rotation
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--user-agent=" + rotateUserAgent.getRandomUserAgent());
//        options.addArguments("--proxy-server=" + nextProxyIP + ":" + nextProxyPort);
        // Initialize the ChromeDriver with the proxy settings
        driver = new ChromeDriver(options);
    }
    @Test(description = "Verify the product title.")
    public void verifyProductTitle() {
        ProductDetails productDetails = getProductDetails();

        WebElement productTitleElement = driver.findElement(productDetails.getProductDetailsHeaderLocator());
        String productTitle = productTitleElement.getText();

        if (productTitle.length() > 0) {
            System.out.println("Test Case Verify Product Title Passed: Product title exists. " + productTitle);
        } else {
            System.out.println("Test Case Verify Product Title Failed: Product title is empty or not found");
        }
    }
    @Test(description = "Verify the product description.")
    public void verifyProductDescription() {
        ProductDetails productDetails = getProductDetails();

        WebElement productDescriptionElement = driver.findElement(productDetails.getProductDetailsDescriptionLocator());
        String productDescription = productDescriptionElement.getText();

        if (productDescription.length() > 0) {
            System.out.println("Test Case Verify Product Description Passed: Product description exists. " + productDescription);
        } else {
            System.out.println("Test Case Verify Product Description Failed: Product description is empty or not found");
        }
    }
    @Test(description = "Verify the product image.")
    public void verifyProductImage() {
        ProductDetails productDetails = getProductDetails();

        WebElement productImageElement = driver.findElement(productDetails.getProductDetailsImageLocator());

        if (productImageElement.isDisplayed()) {
            System.out.println("Test Case Verify Product Image Passed: Product Image exists. ");
        } else {
            System.out.println("Test Case Verify Product Image Failed: Product Image is empty or not found");
        }
    }
    @Test(description = "Verify the product price.")
    public void verifyProductPrice() {
        ProductDetails productDetails = getProductDetails();

        WebElement productPriceElement = driver.findElement(productDetails.getProductDetailsPriceLocator());
        String productPriceText = productPriceElement.getText();
        // Remove any non-numeric characters and convert to integer
        int productPrice = Integer.parseInt(productPriceText.replaceAll("[^\\d]", ""));

        if (productPrice > 0) {
            System.out.println("Test Case Verify Product Price Passed: Product price exists. " + productPrice);
        } else {
            System.out.println("Test Case Verify Product Price Failed: Product price is empty or not found");
        }
    }
    @Test(description = "Verify the product availability.")
    public void verifyProductAvailability() {
        ProductDetails productDetails = getProductDetails();

        WebElement productAvailabilityElement = driver.findElement(productDetails.getProductDetailsAvailability());
        String productAvailability = productAvailabilityElement.getText();

        if (productAvailability.length() > 0) {
            System.out.println("Test Case Verify Product Availability Passed: Product availability text exists. " + productAvailability);
        } else {
            System.out.println("Test Case Verify Product Availability Failed: Product availability is empty or not found");
        }
    }
    @Test(description = "Verify the product's customer review.")
    public void verifyProductCustomReviews() {
        ProductDetails productDetails = getProductDetails();

        WebElement productCustomerReviewsElement = driver.findElement(productDetails.getProductDetailsReviewLocator());
        String productCustomerReviewsText = productCustomerReviewsElement.getText();

        WebElement productCustomerReviewsCount = driver.findElement(productDetails.getProductDetailsReviewsCount());
        String productCustomerReviewsCountText = productCustomerReviewsCount.getText();

        // Remove any non-numeric characters and convert to integer
        int productCustomerReviewsCountValue = Integer.parseInt(productCustomerReviewsCountText.replaceAll("[^\\d]", ""));

        if (productCustomerReviewsText.length() > 0 && productCustomerReviewsCountValue > 0) {
            System.out.println("Test Case Verify Product Customer Reviews Passed: Product customer reviews text exists. " + productCustomerReviewsText);
        } else {
            System.out.println("Test Case Verify Product Customer Reviews Failed: Product customer reviews is empty or not found");
        }
    }
    @Test(description = "Verify the product page's related products.")
    public void verifyRelatedProducts() {
        ProductDetails productDetails = getProductDetails();

        WebElement productRelatedProductsElement = driver.findElement(productDetails.getProductDetailsMoreItemsToExploreLocator());

        if (productRelatedProductsElement.isDisplayed()) {
            System.out.println("Test Case Verify Product Related Products Passed: Related products exists. ");
        } else {
            System.out.println("Test Case Verify Product Related Products Failed: Related products not found");
        }
    }
    @Test(description = "Verify adding the product to cart.")
    public void verifyAddingProductToCart() {
        ProductDetails productDetails = getProductDetails();

        // Get the current URL before clicking on the item page
        String beforeClickURL = driver.getCurrentUrl();

        // Click on add to cart button
        productDetails.clickAddToCartButton();

        // Wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("keywords=cart"));

        // Get the current URL after clicking on the item page
        String afterClickURL = driver.getCurrentUrl();

        if (!beforeClickURL.equals(afterClickURL)) {
            System.out.println("Test Case Add Product to Cart Passed: Product was added to cart.");
        } else {
            System.out.println("Test  Case Add Product to Cart Failed: Product was not added to cart.");
        }
    }
}
