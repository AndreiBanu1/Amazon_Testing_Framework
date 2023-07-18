package amzn.pageobjects.searchmodule;

import amzn.framework.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
public class SearchModule extends BasePage{
    private By searchFieldLocator = By.id("twotabsearchtextbox");

    public By getSearchFieldLocator() {
        return searchFieldLocator;
    }
    private By searchButtonLocator = By.id("nav-search-submit-button");
    private By searchFiltersLocator = By.id("searchDropdownBox");
    private By searchResultsLocator = By.xpath("//*[@id=\"search\"]/div[1]");
    private By searchSuggestionsLocator = By.className("s-suggestion-ellipsis-direction");
    private By sortButtonLocator = By.id("s-result-sort-select");
    private By searchResultHeaderLocator = By.xpath("//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div");
    private By moreResultHeaderLocator = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[6]/div/span/div/div/span");
    private By firstResultInSearchListLocator = By.className("a-link-normal");
    private By searchResultsItemsLocator = By.xpath("//span[@class='a-size-medium a-color-base a-text-normal' and contains(text(),'Iphone')]");
    private int resultsItemsCount;
    private int resultsImageCount;
    private By brandFilterLocator = By.xpath("//li[@id='p_89/Apple' and @aria-label='Apple']//input[@type='checkbox']");
    private By lowPriceInputLocator = By.id("low-price");
    private By highPriceInputLocator = By.id("high-price");
    private By goButtonLocator = By.id("a-autoid-1");
    private By imageResultsLocator = By.className("s-image");

    public SearchModule(WebDriver driver) {
        super(driver);
    }
    public void enterSearchString(String searchString) {driver.findElement(searchFieldLocator).sendKeys(searchString);}
    public void selectCategory(String category) {
        Select searchCategoryDropdown = new Select(driver.findElement(searchFiltersLocator));
        searchCategoryDropdown.selectByVisibleText(category);
    }
    public void clickSearchButton() {
        driver.findElement(searchButtonLocator).click();
    }
    public By getSearchResultsLocator() {
        return searchResultsLocator;
    }
    public By selectSearchSuggestionLocator() {
        return searchSuggestionsLocator;
    }
    public void selectSearchSuggestion(String suggestion) {
        String cssSelector = "[aria-label='" + suggestion + "']";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchSuggestion = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
        searchSuggestion.click();
    }
    public void selectSortingMethod(String sortMethod) {
        Select sortByDropdown = new Select(driver.findElement(sortButtonLocator));
        sortByDropdown.selectByVisibleText(sortMethod);
    }
    public By getSortButtonLocator() {
        return sortButtonLocator;
    }
    public By getSearchResultHeaderLocator() {
        return searchResultHeaderLocator;
    }
    public By getMoreResultHeaderLocator() {
        return moreResultHeaderLocator;
    }
    public By paginationLinkLocator(String pageNumber) {
        return By.xpath("//a[contains(@href, 'page=" + pageNumber + "') and @class='s-pagination-item s-pagination-button']");
    }
    public void selectSecondPage(){
        WebElement pageTwoLink = driver.findElement(paginationLinkLocator("2"));
        pageTwoLink.click();
    }
    public void clickFirstResult(){
        driver.findElement(firstResultInSearchListLocator).click();
    }
    public By getFirstResultInSearchListLocator() {
        return firstResultInSearchListLocator;
    }
    public void checkResultsValidation() {
        List<WebElement> searchResultElements = driver.findElements(searchResultsItemsLocator);
        resultsItemsCount = searchResultElements.size();
    }
    public int getResultsItemsCount() {
        return resultsItemsCount;
    }
    public void selectBrandFilter() {
        WebElement checkboxElement = driver.findElement(brandFilterLocator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", checkboxElement);
    }
    public void setPriceFilter(int lowPrice, int highPrice){
        WebElement lowPriceFilter = driver.findElement(lowPriceInputLocator);
        WebElement highPriceFilter = driver.findElement(highPriceInputLocator);
        WebElement goButton = driver.findElement(goButtonLocator);

        lowPriceFilter.sendKeys(String.valueOf(lowPrice));
        highPriceFilter.sendKeys(String.valueOf(highPrice));
        goButton.click();
    }
    public void countImageResults() {
        List<WebElement> imageElements = driver.findElements(imageResultsLocator);

        for (WebElement imageElement : imageElements) {
            String altText = imageElement.getAttribute("alt");
            if (altText != null && altText.toLowerCase().contains("iphone")) {
                resultsImageCount++;
            }
        }
    }
    public int getImageResultsCount() {
        return resultsImageCount;
    }
}
