package noonTask.Base;

import noonTask.Factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void StartDriver(){
driver = new DriverFactory().IntializeDriver();
    }
    @AfterMethod
    public void TearDown(){
//driver.quit();
    }
}
