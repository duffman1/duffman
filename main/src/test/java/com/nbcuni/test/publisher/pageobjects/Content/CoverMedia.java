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
 * publisher.nbcuni.com Cover Media Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: February 28, 2014
 *          *******************************************
 */

public class CoverMedia {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    //PAGE OBJECT IDENTIFIERS
    private By CoverMedia_Img = By.cssSelector("div[id='edit-field-cover-media-und-0'] img");
    private By Edit_Btn = By.id("edit-field-cover-media-und-0-edit");
    private By Select_Btn = By.xpath("//a[contains(@id, 'cover-media')][text()='Browse']");

    //PAGE OBJECT CONSTRUCTOR
    public CoverMedia(final WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {

        Reporter.log("Assert that img source of the Cover Media contains '" + imageSrc + "'.");
        WebElement ele = waitFor.ElementContainsAttribute(CoverMedia_Img, "src", imageSrc);

        Reporter.log("Assert the the img is loaded and visible.");
        waitFor.ImageVisible(ele);

    }

    public void ClickEditBtn() throws Exception {

        Reporter.log("Click the Cover Media 'Edit' button.");
        interact.Click(waitFor.ElementVisible(Edit_Btn));

    }

    public void ClickSelectBtn() throws Exception {

        Reporter.log("Click the Cover Media 'Select' button.");
        WebElement ele = waitFor.ElementVisible(Select_Btn);
        Thread.sleep(500);
        interact.Click(ele);

    }


}

