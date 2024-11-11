package noonTask.Testcases;

import noonTask.Base.BaseTest;
import noonTask.Pages.HomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchTest extends BaseTest {
    String searchData="appl";
    String filterData="Apple";
    @Test
public void SearchWithValidData(){
        HomePage homePage=new HomePage(driver);
        String searchResult=  homePage.OpenUrl().Search(searchData+ Keys.ENTER).GetSearchHeader();
        Assert.assertEquals(searchResult,searchData);

    }
    @Test
    public void CanFilterByBrand(){
        HomePage homePage=new HomePage(driver);
        homePage.OpenUrl().Search(searchData+ Keys.ENTER);
        String searchResult=  homePage.BrandFilter(filterData);
        Assert.assertEquals(searchResult,filterData);

    }
    @Test
    public  void AutoSuggestionIsValid(){
        HomePage homePage=new HomePage(driver);
      Boolean searchAutoSuggestResult=homePage.OpenUrl().Search(searchData).AutoTypingSuggest(searchData);
      Assert.assertTrue(searchAutoSuggestResult,"AutoSuggestion is incorrect");


    }

}
