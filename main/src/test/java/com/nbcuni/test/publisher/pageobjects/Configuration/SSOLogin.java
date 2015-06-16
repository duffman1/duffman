package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
* publisher.nbcuni.com SSO Login Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: July 3, 2014
*********************************************/
public class SSOLogin {
		
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
	//PAGE OBJECT CONSTRUCTOR
	public SSOLogin(Driver webDriver) {
		config = new Config();
		timeout = config.getConfigValueInt("WaitForWaitTime");
		waitFor = new WaitFor(webDriver, timeout);
		interact = new Interact(webDriver, timeout);
	}

	//PAGE OBJECT IDENTIFIERS
	private By SSOID_Txb = By.id("username");
	
	private By Password_Txb = By.id("password");
	
	private By SignIn_Btn = By.xpath("//button[text()='Sign In']");
	
	private By ClickHere_Lnk = By.xpath("//a[text()='Click here']");
	
	
	//PAGE OBJECT METHODS
	public void EnterSSOID(String ssoID) throws Exception {
		
		Reporter.log("Enter '" + ssoID + "' in the 'SSO ID' text box.");
		interact.Type(waitFor.ElementVisible(SSOID_Txb), ssoID);
		
	}
	
	public void EnterPassword(String password) throws Exception {
		
		Reporter.log("Enter '" + password + "' in the 'Password' text box.");
		interact.Type(waitFor.ElementVisible(Password_Txb), password);
		
	}
	
	public void ClickSignInBtn() throws Exception {
		
		Reporter.log("Click the 'Sign In' button.");
		interact.Click(waitFor.ElementVisible(SignIn_Btn));
		
	}
	
	public void ClickClickHereLnk() throws Exception {
		
		Reporter.log("Click the 'Click here' link.");
		interact.Click(waitFor.ElementVisible(ClickHere_Lnk));
		
	}
	
	public void VerifySSOPasswordReset() throws Exception {
		
		Reporter.log("Verify the user is redirected to the SSO Password Reset page.");
		waitFor.TitleContains("SSO Password Reset application");
		waitFor.ElementContainsText(By.xpath("//body"), "Change your password");
		
	}
}