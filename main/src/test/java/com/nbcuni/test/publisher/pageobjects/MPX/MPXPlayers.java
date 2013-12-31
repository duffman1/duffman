package com.nbcuni.test.publisher.pageobjects.MPX;


import java.util.ArrayList;
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
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com MPX Players Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 31, 2013
 *********************************************/

public class MPXPlayers {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String MPXPlayers_Lnk = "//a[text()='mpxPlayers']";
    private static String SyncMPXPlayers_Lnk = "//a[@class='fieldset-title']";
    private static String SyncMPXPlayersNow_Lnk = "//input[@value='Sync mpxPlayers Now']";
    
    public MPXPlayers(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void ClickMPXPlayersLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(MPXPlayers_Lnk)))).click();;
    }
    
    public void ClickSyncMPXPlayersLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(SyncMPXPlayers_Lnk)))).click();;
    }
    
    public void ClickSyncMPXPlayersNowLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(SyncMPXPlayersNow_Lnk)))).click();;
    }
    
}

