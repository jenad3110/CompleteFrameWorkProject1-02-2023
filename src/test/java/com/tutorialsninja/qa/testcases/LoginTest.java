package com.tutorialsninja.qa.testcases;


import com.tutorialsninja.qa.base.CommonAPI;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.LandingPage;
import com.tutorialsninja.qa.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends CommonAPI {

    Logger log = LogManager.getLogger(LoginTest.class.getName());
    ;

    // WebDriver driver;
/*
    @BeforeMethod
    public void openApplication() throws IOException {

        log = LogManager.getLogger(LoginTest.class.getName());

        driver = initializeBrowser();
        log.debug("Browser got launched");

        log.debug("Navigated to application URL");

    }
*/
    @Test(dataProvider = "getLoginData")
    public void login(String email, String password, String expectedResult) throws IOException, InterruptedException {

        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.myAccountDropdown().click();
        log.debug("Clicked on My Account dropdown");
        /*landingPage.loginOption().click();
        log.debug("Clicked on login option");
*/
        Thread.sleep(3000);

        //LoginPage loginPage = new LoginPage(driver);
        LoginPage loginPage = landingPage.clickOnLoginOption();
        loginPage.emailAddressTextField().sendKeys(email);
        log.debug("Email addressed got entered");
        loginPage.passwordField().sendKeys(password);
        log.debug("Password got entered");
        loginPage.loginButton().click();
        log.debug("Clicked on Login Button");

        AccountPage accountPage = new AccountPage(getDriver());

        String acutualResult = null;

        try {

            if (accountPage.editYourAccountInformation().isDisplayed()) {
                log.debug("User got logged in");
                acutualResult = "Success";
            }

        } catch (Exception e) {
            log.debug("User didn't log in");
            acutualResult = "Failure";

        }

        if (acutualResult.equals(expectedResult)) {

            log.info("Login Test got passed");

        } else {

            log.error("Login Test got failed");
        }


    }
/*
    @AfterMethod
    public void closure() {

        driver.close();
        log.debug("Browser got closed");

    }
    */

    @DataProvider
    public Object[][] getLoginData() {

        Object[][] data = {{"arun.selenium@gmail.com", "Second@123", "Success"}, {"dummy@test.com", "1234", "Failure"}};

        return data;

    }
}
