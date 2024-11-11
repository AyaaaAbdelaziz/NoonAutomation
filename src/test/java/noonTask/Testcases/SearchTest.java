package noonTask.Testcases;

import noonTask.Base.BaseTest;
import noonTask.Pages.HomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;

public class SearchTest extends BaseTest {
    String searchData = "appl";
    String filterData = "Apple";
    String SellerData = "noon";

    @Test
    public void SearchWithValidData() {
        HomePage homePage = new HomePage(driver);
        String searchResult =
                homePage
                        .OpenUrl()
                        .Search(searchData + Keys.ENTER)
                        .GetSearchHeader();
        Assert.assertEquals(searchResult, searchData);

    }

    @Test
    public void CanFilterByBrand() {
        HomePage homePage = new HomePage(driver);
        homePage
                .OpenUrl()
                .Search(searchData + Keys.ENTER)
                .BrandFilter(filterData);
        String searchResult = homePage.GetSearchHeader();
        Assert.assertEquals(searchResult, filterData);

    }

    @Test
    public void AutoSuggestionIsValid() {
        HomePage homePage = new HomePage(driver);
        Boolean searchAutoSuggestResult =
                homePage
                        .OpenUrl()
                        .Search(searchData)
                        .AutoTypingSuggest(searchData);
        Assert.assertTrue(searchAutoSuggestResult, "AutoSuggestion is incorrect");


    }

    @Test
    public void CanFiltersByBrandAndSeller() {
        HomePage homePage = new HomePage(driver);
        Map<String, String> productDetails =
                homePage
                        .OpenUrl()
                        .Search(searchData + Keys.ENTER)
                        .BrandFilter(filterData)
                        .SellerFilter("noon")
                        .OpenProductPage()
                        .productDetails();
        Assert.assertEquals(productDetails.get("brandTitle"), filterData);
        Assert.assertEquals(productDetails.get("sellerTitle"), SellerData);


    }

}
