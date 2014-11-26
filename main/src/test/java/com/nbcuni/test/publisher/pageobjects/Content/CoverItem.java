package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Cover Item Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 22, 2014
 *********************************************/

public class CoverItem {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public CoverItem(Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By CoverItem_Img = By.xpath("//div[@class='media-item']/img");
    
    private By Select_Btn = By.id("edit-field-cover-item-und-0-select");
    
    private By Edit_Btn = By.id("edit-field-cover-item-und-0-edit");
    
    
    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Verify that img source of the Cover Item contains '" + imageSrc + "'.");
    	WebElement ele = waitFor.ElementContainsAttribute(CoverItem_Img, "src", imageSrc);
    	
    	Reporter.log("Verify the the img is loaded and visible.");
    	waitFor.ImageVisible(ele);
    	
    }
    
    public void ClickSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Select' button.");
    	interact.Click(waitFor.ElementVisible(Select_Btn));
    	
    }

    public void ClickEditBtn() throws Exception {
    	
    	Reporter.log("Click the 'Edit' button.");
    	interact.Click(waitFor.ElementVisible(Edit_Btn));
    	
    }
    
}

