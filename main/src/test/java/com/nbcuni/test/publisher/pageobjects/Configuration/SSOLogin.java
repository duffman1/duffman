package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com SSO Login Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: July 3, 2014
*********************************************/
public class SSOLogin {
		
	//PAGE OBJECT CONSTRUCTOR
	public SSOLogin(Driver webDriver) {
		PageFactory.initElements(webDriver, this);
		
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="username")
	private WebElement SSOID_Txb;
	
	@FindBy(how = How.ID, using ="password")
	private WebElement Password_Txb;
	
	@FindBy(how = How.XPATH, using ="//button[text()='Sign In']")
	private WebElement SignIn_Btn;
	
	
	//PAGE OBJECT METHODS
	public void EnterSSOID(String ssoID) throws Exception {
		
		Reporter.log("Enter '" + ssoID + "' in the 'SSO ID' text box.");
		SSOID_Txb.sendKeys(ssoID);
	}
	
	public void EnterPassword(String password) throws Exception {
		
		Reporter.log("Enter '" + password + "' in the 'Password' text box.");
		Password_Txb.sendKeys(password);
	}
	
	public void ClickSignInBtn() throws Exception {
		
		Reporter.log("Click the 'Sign In' button.");
		SignIn_Btn.click();
	}
}