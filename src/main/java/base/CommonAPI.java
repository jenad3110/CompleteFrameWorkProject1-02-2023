package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class CommonAPI {
    WebDriver driver;


    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://tutorialsninja.com/demo/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


    }

    @AfterMethod
    public void tearUp(ITestResult result) {
        new TakeScreenShot(driver).ScreenShot(result);
        driver.quit();
    }

    @AfterSuite
    public void openExtentReport() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("C:\\Users\\sron4\\Downloads\\Framework test\\NewFrameWorkCommonProject\\reports\\extentreport.html");

    }


}
