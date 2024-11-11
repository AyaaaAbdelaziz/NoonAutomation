package noonTask.Pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import noonTask.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {
public  HomePage (WebDriver driver){
    super(driver);
}
By SearchBar = By.id("searchBar");
By FilterList = By.cssSelector("div[data-qa='brand-filter-list']");
By labels=By.tagName("label");
By searchResultHeader=By.xpath("//div[@data-qa='searchHeader']//h1");//div[@data-qa='searchHeader']//h1
By suggest=By.xpath("//button[@class='suggestion-link']");
    protected Wait wait;
    protected Boolean autoSuggestIsCorrect;
    public HomePage OpenUrl(){
        driver.get("https://www.noon.com/egypt-en/");
return this;
    }
public HomePage Search( String searchTxt){

    driver.findElement(SearchBar).sendKeys(searchTxt);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

return this;

}

  public String BrandFilter(String item){
      wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      WebElement filterList = driver.findElement(FilterList);
      List<WebElement> filterLabels=filterList.findElements(labels);
      for(WebElement label:filterLabels){
          WebElement labelText=label.findElement(By.tagName("a"));
          System.out.println(labelText.getText());
          if(labelText.getText().equalsIgnoreCase(item)){
              wait.until(ExpectedConditions.elementToBeClickable(label));
              label.click();
              System.out.println(labelText.getText());
              break;

          }
      }
      String currentUrl = driver.getCurrentUrl();
      wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
      return  GetSearchHeader();
  }
  public String GetSearchHeader(){


      return driver.findElement(searchResultHeader).getText();
  }
  public Boolean AutoTypingSuggest(String searchData) {
      autoSuggestIsCorrect = true;
      //   WebElement autoSuggestList = driver.findElement(suggest);
      List<WebElement> autoSuggestList = driver.findElements(By.cssSelector(".suggestion-link"));
      // wait.until(ExpectedConditions.visibilityOf(autoSuggestList));

     /* if (autoSuggestList == null) {
          System.out.println("Auto suggest list not found!");
          return false;
      }*/
      for (WebElement suggestItem : autoSuggestList) {
          String suggestionText = suggestItem.findElement(By.tagName("b")).getText();


          if (!suggestItem.getText().contains(searchData)) {
              autoSuggestIsCorrect = false;
          }

      }
      return autoSuggestIsCorrect;

      //  return autoSuggestIsCorrect;

  }
}
