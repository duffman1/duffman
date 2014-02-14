package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com UserLogin Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class UserLogin {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public UserLogin(CustomWebDriver webDriver) {
        UserLogin.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[@id='edit-name']")
    private static WebElement Email_Address_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-pass']")
    private static WebElement Password_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-submit']")
    private static WebElement LogIn_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterEmailAddress(String emailAddress) throws Exception {
    	
    	Reporter.log("Enter the user's email address in the 'Email Address' text box.");
    	Email_Address_Txb.sendKeys(emailAddress);
    	
    }
    
    public void EnterPassword(String password) throws Exception {
    	
    	Reporter.log("Enter the user's password in the 'Password' text box.");
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
    }
    
    
  
}

