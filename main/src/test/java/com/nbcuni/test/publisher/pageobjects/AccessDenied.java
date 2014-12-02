package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Access Denied Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class AccessDenied {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	//PAGE OBJECT CONSTRUCTOR
    public AccessDenied(Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Body_Txt = By.xpath("//body");
    
    //PAGE OBJECT METHODS
    public void VerifyAccessDeniedTxt() throws Exception {
    	
    	Reporter.log("Verify access denied text is present.");
    	waitFor.ElementContainsText(Body_Txt, "You are not authorized to access this page.");
    	
    }
    
}

