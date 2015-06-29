package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/**
 * ******************************************
 * publisher.nbcuni.com Revert Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: March 13, 2014
 *          *******************************************
 */

public class Revert {

    private Config config;
    private WaitFor waitFor;
    //PAGE OBJECT IDENTIFIERS
    private By Revert_Btn = By.id("edit-submit");

    //PAGE OBJECT CONSTRUCTOR
    public Revert(WebDriver webWebWebDriver) {
        PageFactory.initElements(webWebWebDriver, this);
        config = new Config();
        waitFor = new WaitFor(webWebWebDriver, config.getConfigValueInt("WaitForWaitTime"));
    }

    //PAGE OBJECT METHODS
    public void ClickRevertBtn() throws Exception {

        Reporter.log("Click the 'Revert' button.");
        waitFor.ElementVisible(Revert_Btn).click();

    }

}

