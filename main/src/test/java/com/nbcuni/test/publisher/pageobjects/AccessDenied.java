package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Access Denied Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class AccessDenied {

    //PAGE OBJECT CONSTRUCTOR
    public AccessDenied(CustomWebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.TAG_NAME, using = "body")
    private static WebElement Body_Txt;
    
    //PAGE OBJECT METHODS
    public void VerifyAccessDeniedTxt() throws Exception {
    	
    	Reporter.log("Verify access denied text is present.");
    	if (!Body_Txt.getText().contains("You are not authorized to access this page.")) {
    		Assert.fail("The text 'You are not authorized to access this page.' is not present.");
    	}
    }
    
}

