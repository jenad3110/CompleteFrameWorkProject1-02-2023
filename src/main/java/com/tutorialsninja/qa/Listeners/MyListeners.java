package com.tutorialsninja.qa.Listeners;

import base.CommonAPI;
import base.TakeScreenShot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyListeners extends CommonAPI implements ITestListener {
    ExtentReports extentReport;
    ExtentTest extentTest;


    @Override
    public void onTestStart(ITestResult iTestResult) {

        //extentTest = extentReport.createTest(iTestResult.getName() + " execution started");
        extentTest = extentReport.createTest(iTestResult.getName());
        extentTest.log(Status.INFO,iTestResult.getName()+" started executing");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.log(Status.PASS, iTestResult.getName() + " Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        extentTest.fail(iTestResult.getThrowable());
        extentTest.log(Status.FAIL, iTestResult.getName() + " Test Failed");
        String destinationScreenshotPath = new TakeScreenShot(getDriver()).ScreenshotPathForExtentReport(iTestResult);
        extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        extentTest.log(Status.INFO, iTestResult.getThrowable());
        extentTest.log(Status.SKIP, iTestResult.getName() + " test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        extentReport = ExtentReporter.getExtentReport();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReport.flush();
/*
        String pathOfExtentReport = System.getProperty("user.dir")+"reports/extentreport.html";
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    */
    }
}
