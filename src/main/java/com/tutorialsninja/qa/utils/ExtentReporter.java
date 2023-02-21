package com.tutorialsninja.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {


    static ExtentReports extentReport;

    public static ExtentReports getExtentReport() {

        String extentReportPath = System.getProperty("user.dir")+"\\reports\\extentreport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportPath);
        reporter.config().setReportName("TutorialsNinja Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extentReport = new ExtentReports();
        extentReport.attachReporter(reporter);
        reporter.config().setTheme(Theme.DARK);

        extentReport.setSystemInfo("Operating System","Windows 11");
        extentReport.setSystemInfo("Tested By","Mohamed fox");

        return extentReport;

    }
}
