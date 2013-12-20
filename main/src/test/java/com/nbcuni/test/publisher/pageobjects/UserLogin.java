package com.nbcuni.test.publisher.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com UserLogin Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class UserLogin {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Page_Title = "Site-Install";
    private static String Email_Address_Txb = "//input[@id='edit-name']";
    private static String Password_Txb = "//input[@id='edit-pass']";
    private static String LogIn_Btn = "//input[@id='edit-submit']";
    

    public UserLogin(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    
    public void EnterEmailAddress(String emailAddress) throws Exception {
    	
    	webDriver.type(Email_Address_Txb, emailAddress);
    	
    }
    
    public void EnterPassword(String password) throws Exception {
    	
    	webDriver.type(Password_Txb, password);
    	
    }
    
    public void ClickLoginBtn() throws Exception {
    	
    	Thread.sleep(1000);
    	webDriver.click(LogIn_Btn);
    	
    }
    
    public void Login(String emailAddress, String password) throws Exception {
    	
    	this.EnterEmailAddress(emailAddress);
    	this.EnterPassword(password);
    	this.ClickLoginBtn();
    }
    
    
  
}

