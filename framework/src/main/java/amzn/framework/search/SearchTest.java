package amzn.framework.search;

import amzn.framework.core.BaseTest;
import amzn.pageobjects.searchmodule.SearchModule;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchTest extends BaseTest {
    private WebDriver driver;
    String searchString = "Iphone 14";
    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void basicSearch() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get("https://www.amazon.com");
        searchModule.enterSearchString(searchString);
        searchModule.clickSearchButton();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResults()));

        if (searchResultsElement.isDisplayed()) {
            System.out.println("Test BasicSearch Passed: Search results are displayed");
        } else {
            System.out.println("Test BasicSearch Failed: Search results are not displayed");
        }
    }

    @Test
    public void advancedSearch() {}

    @Test
    public void emptySearch() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get("https://www.amazon.com");

        searchModule.clickSearchButton();

        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.amazon.com";

        Assert.assertEquals(currentURL, expectedURL, "Test Failed: Current URL is not the same as the initial URL");
    }

    @Test
    public void searchSuggestions() {}

    @Test
    public void searchResultSorting() {}

    @Test
    public void pagination() {}

    @Test
    public void productDetailsFromSearchResult() {}

    @Test
    public void searchResultValidation() {}

    @Test
    public void searchAcrossMultipleCategories() {}

    @Test
    public void searchResultImages() {}

    @Test
    public void searchResultLinkValidity() {}

    @Test
    public void searchPerformance() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get("https://www.amazon.com");

        searchModule.enterSearchString(searchString);
        searchModule.clickSearchButton();
        long startTime = System.currentTimeMillis();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResults()));
        long responseTime = System.currentTimeMillis() - startTime;

        if (responseTime < 5000) {
            System.out.println("Test SearchPerformance Passed: " + responseTime + " miliseconds." + " Search response time is within acceptable limits.");
        } else {
            System.out.println("Test SearchPerformance Failed: " + responseTime + " miliseconds." + " Search response time is too slow.");
        }
    }
}
