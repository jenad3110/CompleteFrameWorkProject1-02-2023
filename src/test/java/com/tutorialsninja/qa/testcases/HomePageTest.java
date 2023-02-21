package com.tutorialsninja.qa.testcases;

import base.CommonAPI;
import com.tutorialsninja.qa.pages.HomePage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class HomePageTest extends CommonAPI {


    @Test
    public void accessHomePage(){

        Assert.assertTrue(false);

    }

    @Test
    public void accessHomePage2(){

        Assert.assertTrue(true);

    }

    @Test
    public void accessHomePage3(){

        throw new SkipException("test deliberately skipped");

    }

    @Test
    public void sendTextInSearchBar(){

        HomePage HP = new HomePage(getDriver());
        HP.sendTextInSearchBar("hello");

    }
}
