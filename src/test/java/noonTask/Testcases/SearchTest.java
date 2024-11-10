package noonTask.Testcases;

import noonTask.Base.BaseTest;
import noonTask.Pages.HomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchTest extends BaseTest {
    @Test
public void SearchWithValidData(){
        HomePage homePage=new HomePage(driver);
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
homePage.OpenUrl().Search("appl").BrandFilter("Apple").GetSearchHeader();


    }

}
