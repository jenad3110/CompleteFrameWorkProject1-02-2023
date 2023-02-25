package com.tutorialsninja.qa.pages;

import com.tutorialsninja.qa.base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonAPI {

    @FindBy(name = "search")
    private WebElement searchBar;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void sendTextInSearchBar(String text) {

        searchBar.sendKeys(text);

    }

    public void drawBorderAroundSearchBar() {
        drawBorder(searchBar);

    }


}
