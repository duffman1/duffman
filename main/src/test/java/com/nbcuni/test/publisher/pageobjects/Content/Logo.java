package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * ******************************************
 * publisher.nbcuni.com Logo Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: March 19, 2014
 *          *******************************************
 */

public class Logo {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    //PAGE OBJECT IDENTIFIERS
    private By Logo_Img = By.cssSelector("div[id='edit-field-logo-und-0'] img");
    private By Edit_Btn = By.id("edit-field-cover-logo-und-0-edit");
    private By Select_Btn = By.id("edit-field-logo-und-0-browse-button");

    //PAGE OBJECT CONSTRUCTOR
    public Logo(final WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {

        Reporter.log("Assert that img source of the Logo contains '" + imageSrc + "'.");
        WebElement ele = waitFor.ElementContainsAttribute(Logo_Img, "src", imageSrc);

        Reporter.log("Assert the the img is loaded and visible.");
        waitFor.ImageVisible(ele);

    }

    public void ClickEditBtn() throws Exception {

        Reporter.log("Click the Logo 'Edit' button.");
        interact.Click(waitFor.ElementVisible(Edit_Btn));

    }

    public void ClickSelectBtn() throws Exception {

        Reporter.log("Click the Logo 'Select' button.");
        interact.Click(waitFor.ElementVisible(Select_Btn));

    }


}

