package com.tutorialsninja.qa.screenshot;

import base.CommonAPI;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeScreenShot extends CommonAPI {


     WebDriver driver;




    public  void ScreenShot(ITestResult result) {


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


            } else
                if (result.getStatus() == ITestResult.SUCCESS) {
                    file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    try {
                        FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "\\screenshots\\screenshotsPassedTest\\  " + df.format(date) + " " + name + " .jpeg"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else
                    if (result.getStatus() == ITestResult.SKIP) {
                        file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                        try {
                            FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "\\screenshots\\screenshotsSkippedTest\\ " + name + " " + df.format(date) + ".jpeg"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }


    }

    public  String ScreenshotPath(ITestResult result){

        File file;
        String name = result.getName();
        DateFormat df = new SimpleDateFormat("MMddyyyyHHmma");
        Date date = new Date();
        df.format(date);
        String Path= System.getProperty("user.dir") + "\\screenshots\\screenshotsFailedTest\\ " + name + " " + df.format(date) + ".jpeg";
        if (result.getStatus() == ITestResult.FAILURE) {
            file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file, new File(Path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        return Path;
    }
}
