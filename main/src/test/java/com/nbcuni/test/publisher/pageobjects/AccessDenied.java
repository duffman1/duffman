package com.nbcuni.test.publisher.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Modules Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class AccessDenied {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    
    public AccessDenied(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void VerifyAccessDeniedTxt() throws Exception {
    	
    	ul.verifyObjectContainsText("//body", "You are not authorized to access this page.");
    }
    
    
  
}

