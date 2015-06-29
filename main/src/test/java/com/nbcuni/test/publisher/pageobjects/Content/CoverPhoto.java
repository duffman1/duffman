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
 * publisher.nbcuni.com Cover Photo Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *          *******************************************
 */

public class CoverPhoto {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    //PAGE OBJECT IDENTIFIERS
    private By CoverPhoto_Img = By.xpath("//div[contains(@id, 'field-character-cover-photo')]//img");
    private By Edit_Btn = By.xpath("//div[contains(@id, 'field-character-cover-photo')]//a[text()='Edit']");
    private By Select_Btn = By.xpath("//div[contains(@id, 'field-character-cover-photo')]//a[text()='Browse']");

    //PAGE OBJECT CONSTRUCTOR
    public CoverPhoto(final WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {

        Reporter.log("Assert that img source of the Cover Photo contains '" + imageSrc + "'.");
        WebElement ele = waitFor.ElementContainsAttribute(CoverPhoto_Img, "src", imageSrc);

        Reporter.log("Assert the the img is loaded and visible.");
        waitFor.ImageVisible(ele);

    }

    public void ClickEditBtn() throws Exception {

        Reporter.log("Click the Cover Photo 'Edit' button.");
        interact.Click(waitFor.ElementVisible(Edit_Btn));

    }

    public void ClickSelectBtn() throws Exception {

        Reporter.log("Click the Cover Photo 'Select' button.");
        interact.Click(waitFor.ElementVisible(Select_Btn));

    }


}

