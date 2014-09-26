package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com UserLogin Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class UserLogin {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public UserLogin(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[@id='edit-name']")
    private WebElement Email_Address_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-pass']")
    private WebElement Password_Txb;
    
    @FindBy(how = How.CSS, using = "input[value='Log in']")
    private WebElement LogIn_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterEmailAddress(String emailAddress) throws Exception {
    	
    	Reporter.log("Enter '" + emailAddress + "' in the 'Email Address' text box.");
    	Email_Address_Txb.sendKeys(emailAddress);
    	
    }
    
    public void EnterPassword(String password) throws Exception {
    	
    	Reporter.log("Enter '" + password + "' in the 'Password' text box.");
    	Password_Txb.sendKeys(password);
    	
    }
    
    public void ClickLoginBtn() throws Exception {
    	
    	Reporter.log("Click the 'Login' button.");
    	try {
    		LogIn_Btn.click();
    	}
    	catch (TimeoutException e) {
    		webDriver.navigate().refresh();
    	}
    	
    }
    
    public void Login(String emailAddress, String password) throws Exception {
    	
    	this.EnterEmailAddress(emailAddress);
    	this.EnterPassword(password);
    	this.ClickLoginBtn();
    	Thread.sleep(1000);
    }
    
    
  
}

