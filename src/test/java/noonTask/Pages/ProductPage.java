package noonTask.Pages;

import noonTask.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    By BrandTitle = By.xpath("//div[contains(@data-qa,'pdp-brand')]");
    By SellerName = By.xpath("//div[@class='sc-13c73e1d-0 gfEahn']");

    public String getBrandTitle() {
        return driver.findElement(BrandTitle).getText();
    }

    public String getSellerName() {

        return driver.findElement(SellerName).getText();
    }

    public Map<String, String> productDetails() {
        Map<String, String> details = new HashMap<>();
        details.put("brandTitle", getBrandTitle());
        details.put("sellerTitle", getSellerName());
        return details;
    }
}



