package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com UserLogin Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class UserLogin {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public UserLogin(Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By EmailAddress_Txb = By.xpath("//input[@id='edit-name']");
    
    private By Password_Txb = By.xpath("//input[@id='edit-pass']");
    
    private By LogIn_Btn = By.cssSelector("input[value='Log in']");
    
    private By FederatedLogIn_Lnk = By.xpath("//a[text()='Federated Log In']");
    
    
    //PAGE OBJECT METHODS
    public void EnterEmailAddress(String emailAddress) throws Exception {
    	
    	Reporter.log("Enter '" + emailAddress + "' in the 'Email Address' text box.");
    	interact.Type(waitFor.ElementVisible(EmailAddress_Txb), emailAddress);
    	
    }
    
    public void EnterPassword(String password) throws Exception {
    	
    	Reporter.log("Enter '" + password + "' in the 'Password' text box.");
    	interact.Type(waitFor.ElementVisible(Password_Txb), password);
    	
    }
    
    public void ClickLoginBtn() throws Exception {
    	
    	Reporter.log("Click the 'Login' button.");
    	interact.Click(waitFor.ElementVisible(LogIn_Btn));
    	
    }
    
    public void VerifyFederatedLoginLnkPresent() throws Exception {
    	
    	Reporter.log("Verify the 'Federated Login' link is present.");
    	waitFor.ElementVisible(FederatedLogIn_Lnk);
    	
    }
    
    public void VerifyFederatedLoginLnkNotPresent() throws Exception {
    	
    	Reporter.log("Verify the 'Federated Login' link is not present.");
    	waitFor.ElementNotPresent(FederatedLogIn_Lnk);
    	
    }
    
    public void Login(String emailAddress, String password) throws Exception {
    	
    	this.EnterEmailAddress(emailAddress);
    	this.EnterPassword(password);
    	this.ClickLoginBtn();
    	Thread.sleep(1000);
    }
    
    
  
}

