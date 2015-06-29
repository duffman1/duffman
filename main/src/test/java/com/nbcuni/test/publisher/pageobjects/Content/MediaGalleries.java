package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/**
 * ******************************************
 * publisher.nbcuni.com Media Galleries Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: March 27, 2014
 *          *******************************************
 */

public class MediaGalleries {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    //PAGE OBJECT IDENTIFIERS
    private By AddMediaGallery_Lnk = By.xpath("//a[text()='Add Media Gallery']");

    //PAGE OBJECT CONSTRUCTOR
    public MediaGalleries(WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    private By Edit_Lnk(String contentItemTitle) {
        return By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Edit']");
    }

    private By ExtendEditMenu_Lnk(String contentItemTitle) {
        return By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='operations']");
    }

    private By EditMenuDelete_Lnk(String contentItemTitle) {
        return By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Delete']");
    }


    //PAGE OBJECT METHODS
    public void ClickAddMediaGalleryLnk() throws Exception {

        Reporter.log("Click the 'Add Media Gallery' link.");
        interact.Click(waitFor.ElementVisible(AddMediaGallery_Lnk));

    }

    public void ClickEditMenuBtn(String contentItemTitle) throws Exception {

        Reporter.log("Click the 'Edit' link for content item titled '" + contentItemTitle + ".");
        interact.Click(waitFor.ElementVisible(Edit_Lnk(contentItemTitle)));

    }

    public void ClickEditExtendMenuBtn(String contentItemTitle) throws Exception {

        Reporter.log("Click the extend edit menu link.");
        interact.Click(waitFor.ElementVisible(ExtendEditMenu_Lnk(contentItemTitle)));

    }

    public void ClickDeleteMenuBtn(String contentItemTitle) throws Exception {

        Reporter.log("Click the 'Delete' menu link.");
        interact.Click(waitFor.ElementVisible(EditMenuDelete_Lnk(contentItemTitle)));

    }
}

