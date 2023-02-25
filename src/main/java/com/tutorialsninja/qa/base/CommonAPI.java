package com.tutorialsninja.qa.base;

import com.aventstack.extentreports.ExtentTest;
import com.tutorialsninja.qa.config.ConfigProp;
import com.tutorialsninja.qa.utils.Utilities;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

public class CommonAPI {

    public WebDriver driver;
    Logger log;
    Properties prop = ConfigProp.loadProperties();
    String browserName = prop.getProperty("browser");
    String url = prop.getProperty("url");

    String implicitlyWait = prop.getProperty("implicitlyWait");

    String pageLoadTime = prop.getProperty("pageLoadTime");


    public CommonAPI(WebDriver driver) {
        this.driver = driver;
    }

    public CommonAPI() {
    }

    public void initializeBrowser() {

        if (browserName.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {

            driver = new EdgeDriver();

        } else if (browserName.equalsIgnoreCase("safari")) {

            driver = new SafariDriver();

        }else {
            driver = new ChromeDriver();
            log.warn("check the Browser parameter in config.properties file");
           // log.fatal("check the Browser parameter in config.properties file");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(implicitlyWait)));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(pageLoadTime)));
        driver.get(url);

    }

    public WebDriver getDriver() {
        return driver;
    }


    @BeforeMethod
    public void setUp() {
        log = LogManager.getLogger(CommonAPI.class.getName());
        initializeBrowser();
        log.info("The browser " + browserName +" selected ");
        driver.get(url);
        log.info("The url "+url +" entered");

    }

    @AfterMethod
    public void tearUp(ITestResult result) {
        new Utilities(driver).ScreenShot(result);
        driver.quit();
        log.info(" \"drive.quit()\" method successfully executed");
    }

    @AfterSuite
    public void openExtentReport() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("C:\\Users\\sron4\\Downloads\\Framework test\\NewFrameWorkCommonProject\\reports\\extentreport.html");

    }

    public void takeScreenShotManually(){


        String Path=System.getProperty("user.dir") +"\\screenshots\\screenShotForSpecificTestCase\\ sc.jpeg";
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(file, new File(Path ));


            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String pathForManualsc(){
        String Path=System.getProperty("user.dir") +"\\screenshots\\screenShotForSpecificTestCase\\ sc.jpeg";
        return Path;
    }


    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void type(WebElement element, String EnterSendKeys) {

        element.sendKeys(EnterSendKeys);

    }

    public void dragAndDrop(WebElement element, WebElement target) {

        Actions actions = new Actions(driver);
        actions.dragAndDrop(element, target).build().perform();
        actions.clickAndHold();


    }


    public void click(WebElement element) {

        element.click();

    }


    public void rightClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.contextClick(element).build().perform();
    }

    public void clickAndHold(WebElement element) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(element).build().perform();
    }

    public void releaseHold() {
        Actions actions = new Actions(driver);
        actions.release().build().perform();

    }

    public void selectFromDropDownList(WebElement element, String InsertVisibleText) {

        Select select = new Select(element);
        select.selectByVisibleText(InsertVisibleText);

    }

    public void drawBorder(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }


    public void doubleClickTest(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element);
    }


    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void hoverOver(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();

    }

    public void clickElementByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


}
