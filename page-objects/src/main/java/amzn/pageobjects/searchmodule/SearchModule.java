package amzn.pageobjects.searchmodule;

import amzn.framework.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchModule extends BasePage{
    private By searchFieldLocator = By.id("twotabsearchtextbox");
    private By searchButtonLocator = By.id("nav-search-submit-button");
    private By searchFiltersLocator = By.id("searchDropdownBox");
    private By searchResultsLocator = By.xpath("//*[@id=\"search\"]/div[1]");
    private By searchSuggestionsLocator = By.className("s-suggestion-ellipsis-direction");
    private By sortButtonLocator = By.id("s-result-sort-select");
    private By searchResultHeaderLocator = By.xpath("//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div");
    private By moreResultHeaderLocator = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[6]/div/span/div/div/span");
    private By pageTwoLocator = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[23]/div/div/span/a[1]");


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
        WebElement pageTwoElement = driver.findElement(pageTwoLocator);
        pageTwoElement.click();
    }
}
