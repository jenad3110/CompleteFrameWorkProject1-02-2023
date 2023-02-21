package com.tutorialsninja.qa.base;

import com.tutorialsninja.qa.config.ConfigProp;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {
	
	WebDriver driver;
	Properties prop = ConfigProp.loadProperties();
	String browserName = prop.getProperty("browser");
	String url = prop.getProperty("url");

	String implicitlyWait = prop.getProperty("implicitlyWait");

	String pageLoadTime = prop.getProperty("pageLoadTime");


	public WebDriver initializeBrowser() {

		if(browserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();

		}else if(browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		}else if(browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		}else if(browserName.equalsIgnoreCase("safari")) {

			driver = new SafariDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(url);

		return driver;

	}

	@AfterMethod
	public void closure() {

		driver.close();

	}
	
	
	
}
