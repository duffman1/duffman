package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Logo Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 19, 2014
 *********************************************/

public class Logo {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public Logo(final Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Logo_Img = By.cssSelector("div[id='edit-field-logo-und-0'] img");
    
    private By Edit_Btn = By.id("edit-field-cover-logo-und-0-edit");
    
    private By Select_Btn = By.id("edit-field-logo-und-0-select");
    
    
    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Assert that img source of the Logo contains '" + imageSrc + "'.");
    	WebElement ele = waitFor.ElementVisible(Logo_Img);
    	Assert.assertTrue(ele.getAttribute("src").contains(imageSrc));
    	
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

