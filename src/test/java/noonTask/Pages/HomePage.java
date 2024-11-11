package noonTask.Pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import noonTask.Base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    By SearchBar = By.id("searchBar");
    By FilterList = By.cssSelector("div[data-qa='brand-filter-list']");
    By labels = By.tagName("label");
    By searchResultHeader = By.xpath("//div[@data-qa='searchHeader']//h1");//div[@data-qa='searchHeader']//h1
    By suggest = By.xpath("//button[@class='suggestion-link']");
    By product = By.xpath("//div[@class='sc-d8caf424-2 fJBKzl']//img");
    By sellerFilter = By.xpath("//div[@data-qa='partner-filter-list']//span[@class='labelText']");
    protected Wait wait;
    protected Boolean autoSuggestIsCorrect;

    public HomePage OpenUrl() {
        driver.get("https://www.noon.com/egypt-en/");
        return this;
    }

    public HomePage Search(String searchTxt) {

        driver.findElement(SearchBar).sendKeys(searchTxt);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return this;

    }

    public HomePage BrandFilter(String item) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement filterList = driver.findElement(FilterList);
        List<WebElement> filterLabels = filterList.findElements(labels);
        for (WebElement label : filterLabels) {
            WebElement labelText = label.findElement(By.tagName("a"));
            System.out.println(labelText.getText());
            if (labelText.getText().equalsIgnoreCase(item)) {
                wait.until(ExpectedConditions.elementToBeClickable(label));
                label.click();
                System.out.println(labelText.getText());
                break;

            }
        }
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        return this;
    }

    public String GetSearchHeader() {


        return driver.findElement(searchResultHeader).getText();
    }

    public Boolean AutoTypingSuggest(String searchData) {
        autoSuggestIsCorrect = true;
        List<WebElement> autoSuggestList = driver.findElements(suggest);

        for (WebElement suggestItem : autoSuggestList) {


            if (!suggestItem.getText().contains(searchData)) {
                autoSuggestIsCorrect = false;
            }

        }
        return autoSuggestIsCorrect;


    }

    public ProductPage OpenProductPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement productElement = driver.findElement(product);

        wait.until(ExpectedConditions.elementToBeClickable(productElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productElement);

        return new ProductPage(driver);

    }

    public HomePage SellerFilter(String item) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> filterLabels = driver.findElements(sellerFilter);
        for (WebElement label : filterLabels) {
            if (label.getText().equalsIgnoreCase(item)) {
                wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(label)));
                label.click();
                System.out.println(label.getText());
                break;

            }
        }
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        return this;
    }
}
