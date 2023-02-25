package com.tutorialsninja.qa.utils;

import com.tutorialsninja.qa.base.CommonAPI;
import com.tutorialsninja.qa.config.ConfigProp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Utilities extends CommonAPI {

    WebDriver driver;
    Properties prop = ConfigProp.loadProperties();
    String screenShotForPassedTests = prop.getProperty("takeScreenShotPassedTest", "false");
    String screenShotForSkippedTests = prop.getProperty("takeScreenShotSkippedTest", "false");

    public Utilities(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public static String generateEmailWithTimeStamp() {

        Date date = new Date();
        String timestamp = date.toString().replace(" ", "_").replace(":", "_");
        return "amotoori" + timestamp + "@gmail.com";

    }


    public void ScreenShot(ITestResult result) {
        String name = result.getName();
        DateFormat df = new SimpleDateFormat("MMddyyyyHHmma");
        Date date = new Date();
        df.format(date);
        File file;

        if (result.getStatus() == ITestResult.FAILURE) {
            file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "\\screenshots\\screenshotsFailedTest\\ " + name + " " + df.format(date) + ".jpeg"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (screenShotForPassedTests.equalsIgnoreCase("true"))
            if (result.getStatus() == ITestResult.SUCCESS) {
                file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "\\screenshots\\screenshotsPassedTest\\  " + name + " " + df.format(date) + ".jpeg"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else if (screenShotForSkippedTests.equalsIgnoreCase("true"))
                if (result.getStatus() == ITestResult.SKIP) {
                    file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    try {
                        FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "\\screenshots\\screenshotsSkippedTest\\ " + name + " " + df.format(date) + ".jpeg"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }

    }


    public String ScreenshotPathForExtentReport(ITestResult result) {

        String name = result.getName();
        DateFormat df = new SimpleDateFormat("MMddyyyyHHmma");
        Date date = new Date();
        df.format(date);
        String screenShotPath = System.getProperty("user.dir") + "\\screenshots\\screenshotsFailedTest\\ " + name + " " + df.format(date) + ".jpeg";

        return screenShotPath;
    }

}
