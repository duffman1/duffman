package com.nbcuni.test.publisher.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Logout Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Logout {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public Logout(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "(//a[text()='Log out'])[1]")
    private static WebElement LogOut_Btn;
    
    
    //PAGE OBJECT METHODS
    public void ClickLogoutBtn() throws Exception {
    	
    	Reporter.log("Click the 'Logout' button.");
    	try {
    		LogOut_Btn.click();
    	}
    	catch (WebDriverException e) {
    		webDriver.executeScript("arguments[0].click();", LogOut_Btn);
    	}
    	webDriver.navigate().refresh(); //TODO - logout requires a refresh for some reason. Figure out a better way
    	
    }
    
    
    
    
  
}

