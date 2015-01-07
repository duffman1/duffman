package com.nbcuni.test.publisher.pageobjects.Structure;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
* publisher.nbcuni.com Views Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: January 4, 2015
*********************************************/

public class Views {

	@SuppressWarnings("unused")
	private Driver webDriver;
	private Config config;
	private Integer timeout;
	private Interact interact;
	private WaitFor waitFor;
	
    //PAGE OBJECT CONSTRUCTOR
    public Views(Driver webDriver) {
    	this.webDriver = webDriver;
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webDriver, timeout);
    	interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AddNewView_Lnk = By.linkText("Add new view");
    
    private By ExtendEditMenu_Lnk(String viewTitle) {
    	return By.xpath("//div[text()='" + viewTitle + "']/../..//a[text()='open']");
    }
    
    private By EditMenuDelete_Lnk(String viewTitle) {
    	return By.xpath("//div[text()='" + viewTitle + "']/../..//a[text()='Delete']");
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddNewViewLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add new view' link.");
    	interact.Click(waitFor.ElementVisible(AddNewView_Lnk));
    	
    }
    
    public void ClickEditExtendMenuBtn(String viewTitle) throws Exception {
    	
    	Reporter.log("Click the extend edit menu link.");
    	interact.Click(waitFor.ElementVisible(ExtendEditMenu_Lnk(viewTitle)));
    	
    }
    
    public void ClickDeleteMenuBtn(String viewTitle) throws Exception {
    	
    	Reporter.log("Click the 'Delete' menu link.");
    	interact.Click(waitFor.ElementVisible(EditMenuDelete_Lnk(viewTitle)));
    	
    }
    
}
