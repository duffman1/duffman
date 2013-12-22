package com.nbcuni.test.publisher.pageobjects.content;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Publishing Options Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *********************************************/

public class PublishingOptions {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String PublishingOptions_Lnk = "//a/strong[text()='Publishing options']";
    private static String CreateNewRevision_Cbx = "//input[@id='edit-revision']";
    private static String ModerationState_Ddl = "//select[@id='edit-event']";
    
    public PublishingOptions(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void ClickPublishingOptionsLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			elementToBeClickable(By.xpath(PublishingOptions_Lnk)));
    	
    	webDriver.executeScript("window.scrollBy(0,-50);"); 
    	
    	webDriver.click(PublishingOptions_Lnk);
    }
    
    public void ClickCreateNewRevisionCbx() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			elementToBeClickable(By.xpath(CreateNewRevision_Cbx)));
    	webDriver.click(CreateNewRevision_Cbx);
    }
    
    public void VerifyCreateNewRevisionCbxChecked() throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(CreateNewRevision_Cbx)).isSelected() == true);
    }
    
    public void SelectModerationState(String stateName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(ModerationState_Ddl))));
    	webDriver.selectFromDropdown(ModerationState_Ddl, stateName);
    }
    
    
    
    
    
}

