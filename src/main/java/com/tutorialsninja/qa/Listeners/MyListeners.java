package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.reporting.ExtentReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners implements ITestListener {

    ExtentReports extentReport ;
    ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        extentTest = extentReport.createTest(iTestResult.getName() + " execution started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
           extentTest.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        extentTest.log(Status.FAIL, "Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

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
    }
}
