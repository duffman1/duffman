package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/**
 * ******************************************
 * publisher.nbcuni.com Media Items Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *          *******************************************
 */

public class MediaItems {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    private By Select_Btn = By.id("edit-field-media-items-und-add-more");
    private By EditAll_Btn = By.id("media-edit-all-button");
    private By Add_Btn = By.xpath("//a[text()='Add media']");

    //PAGE OBJECT CONSTRUCTOR
    public MediaItems(WebDriver webWebWebDriver) {
        PageFactory.initElements(webWebWebDriver, this);
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    //PAGE OBJECT IDENTIFIERS
    private By MediaItem_Img(String imageIndex) {
        return By.xpath("(//div[contains(@id, 'edit-field-media-items')]//div[@class='media-thumbnail']/img)[" + imageIndex + "]");
    }

    private By Edit_Btn(String buttonIndex) {
        return By.xpath("(//a[contains(@id, 'edit-field-media-items')][contains(@id, 'edit')][text()='Edit'])[" + buttonIndex + "]");
    }

    private By Unique_Url(String imageIndex) {
        return By.xpath("(//div[contains(@id, 'edit-field-media-items')][contains(@class, 'media-widget')]//a)[" + imageIndex + "]");
    }

    private By MediaVideo_Frm(String itemTtl, String videoIndex) {
        return By.xpath("(//div[@class='media-item'][contains(@title, '" + itemTtl + "')]//iframe[@id='pdk-player'])[" + videoIndex + "]");
    }

    private By MediaVideo_Lnk(String videoIndex) {
        return By.xpath("(//div[@class='media-item']//a[contains(@type, 'video/mpx')])[" + videoIndex + "]");
    }

    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc, String imageIndex) throws Exception {

        Reporter.log("Verify the img source of the Media Item contains '" + imageSrc + "'.");
        WebElement ele = waitFor.ElementContainsAttribute(MediaItem_Img(imageIndex), "src", imageSrc);

        Reporter.log("Assert the the img is loaded and visible.");
        waitFor.ImageVisible(ele);

    }

    public void VerifyFileVideoPresent(String videoTitle, String videoIndex) throws Exception {

        Reporter.log("Verify the video title with index '" + videoIndex + "' contains '" + videoTitle + "' and is present.");
        waitFor.ElementVisible(MediaVideo_Frm(videoTitle, videoIndex));

    }

    public void VerifyFileVideoLnkPresent(String videoTitle, String videoIndex) throws Exception {

        Reporter.log("Verify the video title link with index '" + videoIndex + "' contains '" + videoTitle + "' and is present.");
        waitFor.ElementContainsText(MediaVideo_Lnk(videoIndex), videoTitle);

    }

    public void ClickSelectBtn() throws Exception {

        Reporter.log("Click the 'Select' button.");
        interact.Click(waitFor.ElementVisible(Select_Btn));

    }

    public void ClickEditBtn(String buttonIndex) throws Exception {

        Reporter.log("Click the 'Edit' button with index '" + buttonIndex + "'.");
        interact.Click(waitFor.ElementVisible(Edit_Btn(buttonIndex)));

    }

    public void ClickEditAllBtn() throws Exception {

        Reporter.log("Click the 'Edit All' button.");
        interact.Click(waitFor.ElementVisible(EditAll_Btn));

    }

    public void ClickAddBtn() throws Exception {

        Reporter.log("Click the 'Add' button.");
        interact.Click(waitFor.ElementVisible(Add_Btn));

    }

    public String GetImageUniqueUrl(String imageIndex) throws Exception {

        Reporter.log("Get the unique url for image number " + imageIndex + ".");
        return waitFor.ElementVisible(Unique_Url(imageIndex)).getAttribute("href");

    }

    public void ClickImageUniqueUrl(String imageIndex) throws Exception {

        Reporter.log("Click the unique url link for image number " + imageIndex + ".");
        interact.Click(waitFor.ElementVisible(Unique_Url(imageIndex)));

    }


}

