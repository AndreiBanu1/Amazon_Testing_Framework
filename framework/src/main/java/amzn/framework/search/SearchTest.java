package amzn.framework.search;

import amzn.framework.core.BaseTest;
import amzn.pageobjects.searchmodule.SearchModule;
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
    String sortMethod = "Price: Low to High";
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
        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));

        if (searchResultsElement.isDisplayed()) {
            System.out.println("Test BasicSearch Passed: Search results are displayed");
        } else {
            System.out.println("Test BasicSearch Failed: Search results are not displayed");
        }
    }

    @Test
    public void advancedSearch() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get("https://www.amazon.com");
        searchModule.selectCategory("Electronics");
        searchModule.enterSearchString(searchString);
        searchModule.clickSearchButton();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));

        if (searchResultsElement.isDisplayed()) {
            System.out.println("Test advancedSearch Passed: Search results are displayed based on specific category selected");
        } else {
            System.out.println("Test advancedSearch Failed: Search results are not displayed based on specific category selected");
        }
    }

    @Test
    public void emptySearch() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get("https://www.amazon.com/");

        searchModule.clickSearchButton();

        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.amazon.com/";

        Assert.assertEquals(currentURL, expectedURL, "Test Failed: Current URL is not the same as the initial URL");
    }

    @Test
    public void searchSuggestions() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get("https://www.amazon.com/");

        searchModule.enterSearchString(searchString);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchSuggestionDropdown = wait.until(ExpectedConditions.elementToBeClickable(searchModule.selectSearchSuggestionLocator()));

        String suggestion = "iphone 14 pro max";
        searchModule.selectSearchSuggestion(suggestion);
        searchModule.clickSearchButton();

        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));

        if (searchResultsElement.isDisplayed()) {
            System.out.println("Test searchSuggestions Passed: Search results are displayed based on selected suggestion");
        } else {
            System.out.println("Test searchSuggestions Failed: Search results are not displayed based on selected suggestion");
        }
    }

    @Test
    public void searchResultSorting() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get("https://www.amazon.com");

        searchModule.enterSearchString(searchString);
        searchModule.clickSearchButton();
        searchModule.selectSortingMethod(sortMethod);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));

        WebElement sortingMethodSelected = driver.findElement(searchModule.getSortButtonLocator());
        String selectedSortMethod = sortingMethodSelected.getText();

        if (selectedSortMethod.contains(sortMethod)) {
            System.out.println("Test Case searchResultSorting Passed: Search results are sorted by the expected method");
        } else {
            System.out.println("Test Case searchResultSorting Failed: Search results are not sorted by the expected method");
        }
    }

    @Test
    public void pagination() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get("https://www.amazon.com");
        searchModule.enterSearchString(searchString);
        searchModule.clickSearchButton();

        //Wait for the result page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));

        WebElement resultHeader = driver.findElement(searchModule.getSearchResultHeaderLocator());
        WebElement moreResultsHeader = driver.findElement(searchModule.getMoreResultHeaderLocator());

        // Get the current URL before clicking on the second page
        String beforeClickURL = driver.getCurrentUrl();

        boolean allElementsExist = true;

        // Check if resultHeader exists and is displayed
        if (!resultHeader.isDisplayed()) {
            allElementsExist = false;
        }

        // Check if moreResultsHeader exists and is displayed
        if (!moreResultsHeader.isDisplayed()) {
            allElementsExist = false;
        }

        // Click on the second page
        WebElement pageTwoLink = driver.findElement(searchModule.paginationLinkLocator("2"));
        pageTwoLink.click();

        // Wait for the page to load
        wait.until(ExpectedConditions.urlContains("page=2"));

        // Get the current URL after clicking on the second page
        String afterClickURL = driver.getCurrentUrl();

        if (beforeClickURL.equals(afterClickURL)) {
            allElementsExist = false;
        }

        if (allElementsExist) {
            System.out.println("Test Passed: All elements exist and are displayed and the pagination works.");
        } else {
            System.out.println("Test Failed: One or more elements do not exist or are not displayed or the pagination does not work.");
        }

    }
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
        wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));
        long responseTime = System.currentTimeMillis() - startTime;

        if (responseTime < 5000) {
            System.out.println("Test SearchPerformance Passed: " + responseTime + " milliseconds." + " Search response time is within acceptable limits.");
        } else {
            System.out.println("Test SearchPerformance Failed: " + responseTime + " milliseconds." + " Search response time is too slow.");
        }
    }
}
