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
}
