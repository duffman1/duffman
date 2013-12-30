package com.nbcuni.test.publisher.pageobjects.MPX;


import java.util.ArrayList;
import java.util.List;

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
 * publisher.nbcuni.com Sync MPX Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 29, 2013
 *********************************************/

public class SyncMPXMedia {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String SyncMPXMedia_Lnk = "//fieldset[@id='edit-video-sync']//a";
    
    
    public SyncMPXMedia(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void ClickSyncMPXMediaLnk() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 30).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(SyncMPXMedia_Lnk))));
    	el.click();
    }
    
    public void VerifyImportAccountLabels(List<String> labels) throws Exception {
    	
    	for (String label : labels) {
    		
    		new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    				visibilityOf(webDriver.findElement(By.xpath("//label[contains(@for, 'edit-video-sync-settings')][contains(text(), '" + label + "')]"))));
    	}
    	
    }
    
    
}

