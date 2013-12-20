package com.nbcuni.test.publisher.pageobjects.content;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Revision State Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *********************************************/

public class RevisionState {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String RevisionState_Ctr = "//div[@class='workbench-info-block'][contains(text(), 'Revision state')]";
    
    
    public RevisionState(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void VerifyRevisionState(String state) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(RevisionState_Ctr))));
    	Assert.assertTrue(webDriver.findElement(By.xpath(RevisionState_Ctr)).getText().contains(state));
    }
    
    
}

