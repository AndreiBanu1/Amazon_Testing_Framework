package amzn.pageobjects.productdetails;

import amzn.framework.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetails extends BasePage {
    private By productDetailsImageLocator = By.className("a-dynamic-image");
    private By productDetailsHeaderLocator = By.id("productTitle");
    private By productDetailsReviewLocator = By.id("averageCustomerReviews");
    private By productDetailsAddToCartButtonLocator = By.id("add-to-cart-button");
    private By productDetailsDescriptionLocator = By.id("feature-bullets");
    private By productDetailsPriceLocator = By.className("a-offscreen");
    private By productDetailsAvailabilityLocator = By.id("availability");
    private By productDetailsReviewsCountLocator = By.id("acrCustomerReviewText");
    private By productDetailsMoreItemsToExploreLocator = By.className("a-carousel-row");

    public By getProductDetailsMoreItemsToExploreLocator() {
        return productDetailsMoreItemsToExploreLocator;
    }
    public By getProductDetailsReviewsCount() {
        return productDetailsReviewsCountLocator;
    }
    public By getProductDetailsAvailability() {
        return productDetailsAvailabilityLocator;
    }
    public By getProductDetailsPriceLocator() {
        return productDetailsPriceLocator;
    }
    public By getProductDetailsDescriptionLocator() {
        return productDetailsDescriptionLocator;
    }
    public void clickAddToCartButton() {
        driver.findElement(productDetailsAddToCartButtonLocator).click();
    }
    public By getProductDetailsHeaderLocator() {
        return productDetailsHeaderLocator;
    }
    public By getProductDetailsReviewLocator() {
        return productDetailsReviewLocator;
    }
    public By getProductDetailsImageLocator() {
        return productDetailsImageLocator;
    }
    public ProductDetails(WebDriver driver) {
        super(driver);
    }
}
