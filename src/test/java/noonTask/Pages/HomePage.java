package noonTask.Pages;

import noonTask.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {
public  HomePage (WebDriver driver){
    super(driver);
}
By SearchBar = By.id("searchBar");
By FilterList = By.cssSelector("div[data-qa='brand-filter-list']");
By labels=By.tagName("label");
By searchResultHeader=By.xpath("//*[data-qa='searchHeader']//h1");
protected Wait wait;
    public HomePage OpenUrl(){
        driver.get("https://www.noon.com/egypt-en/");
return this;
    }
public HomePage Search( String searchTxt){

    driver.findElement(SearchBar).sendKeys(searchTxt+ Keys.ENTER);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    return this;


}

  public HomePage BrandFilter(String item){
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      WebElement filterList = driver.findElement(FilterList);
      List<WebElement> filterLabels=filterList.findElements(labels);
      for(WebElement label:filterLabels){
          WebElement labelText=label.findElement(By.tagName("a"));
          System.out.println(labelText.getText());
          if(labelText.getText().equalsIgnoreCase(item)){
              label.click();
              System.out.println(labelText.getText());
              break;

          }

      }
      return this;
  }
  public String GetSearchHeader(){
        return driver.findElement(searchResultHeader).getText();
  }
}
