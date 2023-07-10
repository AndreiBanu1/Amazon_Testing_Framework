package amzn.pageobjects.searchmodule;

import amzn.framework.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchModule extends BasePage{
    private By searchField = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");
    private By searchFilters = By.id("searchDropdownBox");
    private By searchResults = By.xpath("//*[@id=\"search\"]/div[1]");


    public SearchModule(WebDriver driver) {
        super(driver);
    }

    public void enterSearchString(String searchString) {driver.findElement(searchField).sendKeys(searchString);}
    public void selectCategory(String category) {
        Select searchSuggestionDropdown = new Select(driver.findElement(searchFilters));
        searchSuggestionDropdown.selectByVisibleText(category);
    }
    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }
    public By getSearchResults() {
        return searchResults;
    }

}
