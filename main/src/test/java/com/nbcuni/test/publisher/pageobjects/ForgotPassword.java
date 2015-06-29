package com.nbcuni.test.publisher.pageobjects;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com ForgotPassword Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 6, 2014
 *********************************************/

public class ForgotPassword {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public ForgotPassword(WebDriver webWebWebDriver) {
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webWebWebDriver, timeout);
    	interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By RequestNewPassword_Lnk = By.xpath("//a[text()='Request new password']");
    
    private By Email_Txb = By.id("edit-name");
    
    private By ResetPassword_Btn = By.id("edit-submit");
    
    
    //PAGE OBJECT METHODS
    public void ClickRequestPasswordLnk() throws Exception {
    	
    	Reporter.log("Click the 'Request new password' link.");
    	interact.Click(waitFor.ElementVisible(RequestNewPassword_Lnk));
    	
    }
    
    public void EnterEmail(String emailAddress) throws Exception {
    	
    	Reporter.log("Enter '" + emailAddress + "' in the 'E-mail' text box.");
    	interact.Type(waitFor.ElementVisible(Email_Txb), emailAddress);
    	
    }
    
    public void ClickResetPasswordBtn() throws Exception {
    	
    	Reporter.log("Click the 'Reset password' button.");
    	interact.Click(waitFor.ElementVisible(ResetPassword_Btn));
    	
    }
    
}

