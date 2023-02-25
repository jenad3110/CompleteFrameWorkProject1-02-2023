package com.tutorialsninja.qa.pages;

import com.tutorialsninja.qa.base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends CommonAPI {

    WebDriver driver;

    public LandingPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath="//a[text()='My Account']")
    WebElement myAccountDropdown;

    @FindBy(linkText="Login")
    WebElement loginOption;

    public WebElement myAccountDropdown() {

        return myAccountDropdown;

    }

    public WebElement loginOption() {

        return loginOption;

    }

    public LoginPage clickOnLoginOption(){

        loginOption.click();

        return new LoginPage(driver);
    }

}