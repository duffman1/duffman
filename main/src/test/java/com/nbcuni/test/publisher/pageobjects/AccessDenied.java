package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Access Denied Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class AccessDenied {

	private WaitFor waitFor;
	
    //PAGE OBJECT CONSTRUCTOR
    public AccessDenied(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
        waitFor = new WaitFor(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.TAG_NAME, using = "body")
    private WebElement Body_Txt;
    
    //PAGE OBJECT METHODS
    public void VerifyAccessDeniedTxt() throws Exception {
    	
    	Reporter.log("Verify access denied text is present.");
    	waitFor.ElementContainsText(Body_Txt, "You are not authorized to access this page.");
    	
    }
    
}

