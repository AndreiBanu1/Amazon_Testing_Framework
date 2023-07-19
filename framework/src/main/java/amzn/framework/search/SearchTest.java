package amzn.framework.search;

import amzn.framework.core.BaseTest;
import amzn.domain.constants.TestConstants;
import amzn.pageobjects.searchmodule.SearchModule;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;


public class SearchTest extends BaseTest {
    private WebDriver driver;
    String sortMethod = "Price: Low to High";
     @BeforeTest
    public void setup() {
         ChromeOptions options = new ChromeOptions();
         options.addArguments("--headless");
         driver = new ChromeDriver(options);
    }
    @Test
    public void basicSearch() {
        SearchModule searchModule = new SearchModule(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(TestConstants.WEBSITE);

        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
        searchModule.clickSearchButton();

        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));
        if (searchResultsElement.isDisplayed()) {
            System.out.println("Test BasicSearch Passed: Search results are displayed.");
        } else {
            System.out.println("Test BasicSearch Failed: Search results are not displayed.");
        }
    }
    @Test
    public void advancedSearch() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get(TestConstants.WEBSITE);
        searchModule.selectCategory("Electronics");
        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
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
        driver.get(TestConstants.WEBSITE);

        searchModule.clickSearchButton();

        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.amazon.com/";

        Assert.assertEquals(currentURL, expectedURL, "Test Failed: Current URL is not the same as the initial URL");
    }
    @Test
    public void searchSuggestions() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get(TestConstants.WEBSITE);

        searchModule.enterSearchString(TestConstants.SEARCH_STRING);

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
        driver.get(TestConstants.WEBSITE);

        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
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
        driver.get(TestConstants.WEBSITE);
        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
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
        searchModule.selectSecondPage();

        // Wait for the page to load
        wait.until(ExpectedConditions.urlContains("page=2"));

        // Get the current URL after clicking on the second page
        String afterClickURL = driver.getCurrentUrl();

        if (beforeClickURL.equals(afterClickURL)) {
            allElementsExist = false;
        }

        if (allElementsExist) {
            System.out.println("Test case pagination Passed: All elements exist and are displayed and the pagination works.");
        } else {
            System.out.println("Test case pagination Failed: One or more elements do not exist or are not displayed or the pagination does not work.");
        }
    }
    @Test
    public void productDetailsFromSearchResult() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get(TestConstants.WEBSITE);
        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
        searchModule.clickSearchButton();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));

        // Get the current URL before clicking on the item page
        String beforeClickURL = driver.getCurrentUrl();

        // Click on first item from the list
        searchModule.clickFirstResult();

        // Wait for the page to load
        wait.until(ExpectedConditions.urlContains("keywords=Iphone+14"));

        // Get the current URL after clicking on the item page
        String afterClickURL = driver.getCurrentUrl();

        if (!beforeClickURL.equals(afterClickURL)) {
            System.out.println("Test productDetailsFromSearchResult Passed: Item page is displayed");
        } else {
            System.out.println("Test productDetailsFromSearchResult Failed: Item page is not displayed");
        }
    }
    @Test
    public void searchResultValidation() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get(TestConstants.WEBSITE);
        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
        searchModule.clickSearchButton();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));

        searchModule.checkResultsValidation();
        // Retrieve the results items count from the SearchModule class
        int resultsItemsCount = searchModule.getResultsItemsCount();

        if (resultsItemsCount > 0) {
            System.out.println("Test searchResultValidation Passed: Valid search results are displayed");
            System.out.println("Number of elements with 'Iphone' in title: " + resultsItemsCount);
        } else {
            System.out.println("Test searchResultValidation Failed: Valid search results are not displayed");
        }
    }
    @Test
    public void searchAcrossMultipleCategories() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get(TestConstants.WEBSITE);
        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
        searchModule.clickSearchButton();

        // Wait for page with results to be loaded
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));

        searchModule.selectBrandFilter();

        // Set low price and high price filter
        searchModule.setPriceFilter(100, 999);

        // Wait for page with filtered results to be loaded
        WebElement searchResultsElementAfterFilter = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));

        if (searchResultsElementAfterFilter.isDisplayed()) {
            System.out.println("Test searchAcrossMultipleCategories Passed: Search results are displayed with filters applied.");
        } else {
            System.out.println("Test searchAcrossMultipleCategories Failed: Search results are not displayed with filters applied.");
        }
    }
    @Test
    public void searchResultImages() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get(TestConstants.WEBSITE);
        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
        searchModule.clickSearchButton();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));

        searchModule.countImageResults();
        int imagesNo = searchModule.getImageResultsCount();

        if (imagesNo > 1) {
            System.out.println("Test searchResultImages Passed: Search results images are displayed accordingly.");
            System.out.println("Number of images: " + imagesNo);
        } else {
            System.out.println("Test BasicSearch Failed: Search results images are not displayed accordingly.");
            System.out.println("Number of images: " + imagesNo);
        }
    }
    @Test
    public void searchPerformance() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get(TestConstants.WEBSITE);

        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
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
    @Test
    public void searchResultLinkValidity() {
        SearchModule searchModule = new SearchModule(driver);
        driver.get(TestConstants.WEBSITE);
        searchModule.enterSearchString(TestConstants.SEARCH_STRING);
        searchModule.clickSearchButton();
        boolean testPassed = false;

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchResultsElement = wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getSearchResultsLocator()));

        // Get the current URL before clicking on the item page
        String beforeClickURL = driver.getCurrentUrl();

        // Click on first item from the list
        searchModule.clickFirstResult();

        // Wait for the page to load
        wait.until(ExpectedConditions.urlContains("keywords=Iphone+14"));

        // Get the current URL after clicking on the item page
        String afterClickURL = driver.getCurrentUrl();

        if (!beforeClickURL.equals(afterClickURL)) {
            testPassed = true;
        }

        driver.navigate().back();

        // Click on the second page
        searchModule.selectSecondPage();
        wait.until(ExpectedConditions.urlContains("page=2"));
        wait.until(ExpectedConditions.presenceOfElementLocated(searchModule.getFirstResultInSearchListLocator()));
        WebElement firstResultLink = driver.findElement(searchModule.getFirstResultInSearchListLocator());
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(firstResultLink).click().perform();
        } catch (ElementClickInterceptedException e) {
            // Handle the ElementClickInterceptedException
            // Wait for the interfering element to become invisible
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("a-size-base")));

            // Retry the click
            firstResultLink.click();
        }

        String afterClickSecondURL = driver.getCurrentUrl();

        if (!beforeClickURL.equals(afterClickURL)) {
            testPassed = true;
        }

        if (testPassed) {
            System.out.println("Test case searchResultLinkValidity Passed: Results have valid links.");
        } else {
            System.out.println("Test case searchResultLinkValidity Failed: One or more results do not have valid links.");
        }
    }
}
