package com.nbcuni.test.publisher.pageobjects.errorchecking;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Content Parent Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class ErrorChecking {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Error_Ctr = "//div[@class='messages error']";
    
    
    public ErrorChecking(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void VerifyAllRequiredFields(List<String> allFieldTitles) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath(Error_Ctr)));
    	
    	for (String field : allFieldTitles) {
    		
    		ul.verifyObjectContainsText(Error_Ctr, field + " field is required.");
    	}
    }
   
    
    
}

