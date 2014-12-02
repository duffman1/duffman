package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.openqa.selenium.By;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Content Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Content {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public Content(Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
   
    //PAGE OBJECT IDENTIFIERS
    private By AddFile_Lnk = By.xpath("//a[text()='Add file']");
    
    private By ContentItem_Lnk(String contentItemTitle) {
    	return By.xpath("//a[text()='" + contentItemTitle + "']");
    }
    
    private By Edit_Lnk(String contentItemTitle) {
    	return By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Edit']");
    }
    
    private By ExtendEditMenu_Lnk(String contentItemTitle) {
    	return By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='operations']");
    }
    
    private By EditMenuEdit_Lnk(String contentItemTitle) {
    	return By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Edit']");
    }
    
    private By EditMenuDelete_Lnk(String contentItemTitle) {
    	return By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Delete']");
    }
    
    
    //PAGE OBJECT METHODS
    public void VerifyContentItemEditDelete(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Click the extend arrow for the 'Edit' menu.");
    	this.ClickEditExtendMenuBtn(contentItemTitle);
    	
    	Reporter.log("Verify the 'Edit' menu link is present from the extended edit menu.");
    	waitFor.ElementVisible(EditMenuEdit_Lnk(contentItemTitle));
    	
    	Reporter.log("Verify the 'Delete' menu link is present from the extended edit menu.");
    	waitFor.ElementVisible(EditMenuDelete_Lnk(contentItemTitle));
    	
    }
    
    public void VerifyContentItemEditDeleteNotPresent(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Verify the content item titled '" + contentItemTitle + "' is present.");
    	waitFor.ElementVisible(ContentItem_Lnk(contentItemTitle));
    	
        Reporter.log("Verify the 'Edit/Delete' menu options are not present for the content item titled '" + contentItemTitle + "'.");
        waitFor.ElementNotPresent(ExtendEditMenu_Lnk(contentItemTitle));
        
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
    
    public void VerifyContentItemNotPresent(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Verify the content item titled '" + contentItemTitle + "' is not present.");
    	waitFor.ElementNotPresent(ContentItem_Lnk(contentItemTitle));
    	
    }
    
    public void ClickAddFileLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add file' link.");
    	interact.Click(waitFor.ElementVisible(AddFile_Lnk));
    	
    }
    
}

