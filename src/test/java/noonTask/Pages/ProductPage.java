package noonTask.Pages;

import noonTask.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver){
        super(driver);
    }
    By BrandTitle= By.xpath("//div[@data-qa='pdp-brand-N70015761V']");
    By SellerName=By.xpath("//span[@class='allOffers']");
    public void OpenProductPage(String searchData){
        HomePage homePage=new HomePage(driver);
        homePage.OpenUrl().Search(searchData);



    }
    public String getBrandTitle(){
       return driver.findElement(BrandTitle).getText();
    }
    public String getSellerName(){
        return driver.findElement(SellerName).getText();
    }
}
