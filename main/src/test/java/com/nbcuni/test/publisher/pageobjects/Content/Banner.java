package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Banner Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 19, 2014
 *********************************************/

public class Banner {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public Banner(final Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Banner_Img = By.cssSelector("div[id='edit-field-banner-und-0'] img");
    
    private By Edit_Btn = By.id("edit-field-cover-banner-und-0-edit");
    
    private By Select_Btn = By.id("edit-field-banner-und-0-browse-button");
    
    
    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Assert that img source of the Banner contains '" + imageSrc + "'.");
    	WebElement ele = waitFor.ElementContainsAttribute(Banner_Img, "src", imageSrc);
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	waitFor.ImageVisible(ele);
    	
    }
    
    public void ClickEditBtn() throws Exception {
    	
    	Reporter.log("Click the Banner 'Edit' button.");
    	interact.Click(waitFor.ElementVisible(Edit_Btn));
    	
    }
    
    public void ClickSelectBtn() throws Exception {
    	
    	Reporter.log("Click the Banner 'Select' button.");
    	WebElement ele = waitFor.ElementVisible(Select_Btn);
    	Thread.sleep(500);
    	interact.Click(ele);
    	
    }
    
}

