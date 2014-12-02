package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Revert Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 13, 2014
 *********************************************/

public class Revert {

	private Config config;
	private WaitFor waitFor;
	
    //PAGE OBJECT CONSTRUCTOR
    public Revert(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
        config = new Config();
        waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Revert_Btn = By.id("edit-submit");
    
    
    //PAGE OBJECT METHODS
    public void ClickRevertBtn() throws Exception {
    	
    	Reporter.log("Click the 'Revert' button.");
    	waitFor.ElementVisible(Revert_Btn).click();
    	
    }
    
}

