package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Media Items Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class MediaItems {

    private Driver webDriver;
    private Config config;
    
    //PAGE OBJECT CONSTRUCTOR
    public MediaItems(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        config = new Config();
    }
    
    //PAGE OBJECT IDENTIFIERS
    private WebElement MediaItem_Img(String imageIndex) {
    	return webDriver.findElement(By.xpath("(//div[@class='media-thumbnail']/img)[" + imageIndex + "]"));
    }
    
    @FindBy(how = How.ID, using = "edit-field-media-items-und-add-more")
    private WebElement Select_Btn;
    
    private WebElement Edit_Btn(String buttonIndex) {
    	return webDriver.findElement(By.xpath("(//a[contains(@id, 'edit-field-media-items')][contains(@id, 'edit')][text()='Edit'])[" + buttonIndex + "]"));
    }
    
    private WebElement Unique_Url(String imageIndex) {
    	return webDriver.findElement(By.xpath("(//div[@class='media-thumbnail']/../../..//div[3]/a)[" + imageIndex + "]"));
    }
    
    private WebElement MediaVideo_Frm(String itemTtl, String videoIndex) {
    	return webDriver.findElement(By.xpath("(//div[@class='media-item'][contains(@title, '" + itemTtl + "')]//iframe[@id='pdk-player'])[" + videoIndex + "]"));
    }
    
    @FindBy(how = How.ID, using = "media-edit-all-button")
    private WebElement EditAll_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-field-media-items-und-add-more')]")
    private WebElement Add_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[@class='throbber']")
    private WebElement Spinner_Img;
    
    
    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc, String imageIndex) throws Exception {
    	
    	Reporter.log("Verify the img source of the Media Item contains '" + imageSrc + "'.");
    	Assert.assertTrue(MediaItem_Img(imageIndex).getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			MediaItem_Img(imageIndex));
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
    public void VerifyFileVideoPresent(String videoTitle, String videoIndex) throws Exception {
    	
    	Reporter.log("Verify the video title with index '" + videoIndex + "' contains '" + videoTitle + "' and is present.");
    	MediaVideo_Frm(videoTitle, videoIndex).isDisplayed();
    }
    
    public void ClickSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Select' button.");
    	Select_Btn.click();
    }

    public void ClickEditBtn(String buttonIndex) throws Exception {
    	
    	Reporter.log("Click the 'Edit' button with index '" + buttonIndex + "'.");
    	Edit_Btn(buttonIndex).click();
    }
    
    public void ClickEditAllBtn() throws Exception {
    	
    	Reporter.log("Click the 'Edit All' button.");
    	EditAll_Btn.click();
    }
    
    public void ClickAddBtn() throws Exception {
    	
    	Reporter.log("Click the 'Add' button.");
    	Add_Btn.click();
    }
    
    public String GetImageUniqueUrl(String imageIndex) throws Exception {
    	
    	Reporter.log("Get the unique url for image number " + imageIndex + ".");
    	return Unique_Url(imageIndex).getAttribute("href");
    }
    
    public void ClickImageUniqueUrl(String imageIndex) throws Exception {
    	
    	Reporter.log("Click the unique url link for image number " + imageIndex + ".");
    	Unique_Url(imageIndex).click();
    }
    
    public void WaitForImgLoadComplete() throws Exception {
    	
    	Reporter.log("Wait for the image load 'spinner' image to not be present, indicating image loading is complete.");
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	boolean searchComplete;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("image load not complete after timeout");
            }
            try {
            	Spinner_Img.isDisplayed();
            	searchComplete = false;
            }
            catch (Exception e) {
            	searchComplete = true;
            }
                
            if (searchComplete == true){ break;}
            Thread.sleep(500);
        }
        webDriver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    
}

