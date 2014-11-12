package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Cover Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 28, 2014
 *********************************************/

public class CoverMedia {

    private Config config;
    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public CoverMedia(final Driver webDriver) {
        PageFactory.initElements(webDriver, this);
        config = new Config();
        waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By CoverMedia_Img = By.cssSelector("div[id='edit-field-cover-media-und-0'] img");
    
    private By Edit_Btn = By.id("edit-field-cover-media-und-0-edit");
    
    private By Select_Btn = By.cssSelector("a[id*= 'cover-media-und-0-select']");
    
    
    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Assert that img source of the Cover Media contains '" + imageSrc + "'.");
    	WebElement img = waitFor.ElementPresent(CoverMedia_Img);
    	Assert.assertTrue(img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	waitFor.ImageVisible(img);
    	
    }
    
    public void ClickEditBtn() throws Exception {
    	
    	Reporter.log("Click the Cover Media 'Edit' button.");
    	waitFor.ElementVisible(Edit_Btn).click();
    	
    }
    
    public void ClickSelectBtn() throws Exception {
    	
    	Reporter.log("Click the Cover Media 'Select' button.");
    	waitFor.ElementVisible(Select_Btn).click();
    	
    }
    
    
}

