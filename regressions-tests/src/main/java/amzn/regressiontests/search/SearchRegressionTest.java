package amzn.regressiontests.search;

import amzn.framework.search.SearchTest;
import org.testng.annotations.Test;

public class SearchRegressionTest extends SearchTest{
    @Test
    public void testBasicSearch() {
        basicSearch();
    }
    @Test
    public void testAdvancedSearch(){
        advancedSearch();
    }
    @Test
    public void testEmptySearch(){
        emptySearch();
    }
    @Test
    public void testSearchSuggestions(){
        searchSuggestions();
    }
    @Test
    public void testSearchResultSorting(){
        searchResultSorting();
    }
    @Test
    public void testPagination(){
        pagination();
    }
    @Test
    public void testProductDetailsFromSearchResult() {
        productDetailsFromSearchResult();
    }
    @Test
    public void testSearchResultValidation() {
        searchResultValidation();
    }
    @Test
    public void testSearchAcrossMultipleCategories(){
        searchAcrossMultipleCategories();
    }
    @Test
    public void testSearchResultImages() {
        searchResultImages();
    }
    @Test
    public void testSearchPerformance() {
        searchPerformance();
    }
    @Test
    public void testSearchResultLinkValidity() {
        searchResultLinkValidity();
    }
}
