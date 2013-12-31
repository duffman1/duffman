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
 * publisher.nbcuni.com MPX Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 29, 2013
 *********************************************/

public class MPXMedia {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String MPXMedia_Lnk = "//a[text()='mpxMedia']";
    private static String SyncMPXMedia_Lnk = "//fieldset[@id='edit-video-sync']//a";
    private static String SyncMPXMediaNow_Lnk = "//input[@value='Sync mpxMedia Now']";
    
    public MPXMedia(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void ExpandMPXMedia() throws Exception {
    	
    	if (new WebDriverWait(webDriver, 30).until(ExpectedConditions.presenceOfElementLocated(
    			By.xpath(SyncMPXMediaNow_Lnk))).isDisplayed() == false) {
    		
    		webDriver.findElement(By.xpath(SyncMPXMedia_Lnk)).click();
    	}
    	
    }

    public void ClickMPXMediaLnk() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 30).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(MPXMedia_Lnk))));
    	el.click();
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
    
    public void VerifyImportAccountPlayerOptions(String accountName, List<String> playerList) throws Exception {
    	
    	List<WebElement> Options = new Select(new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    				visibilityOf(webDriver.findElement(By.xpath(
    						"//label[contains(@for, 'edit-video-sync-settings')][contains(text(), '" + accountName + "')]/../select"))))).getOptions();
    	
    	List<String> allOptions = new ArrayList<String>();
    	
    	for (WebElement el : Options) {
    		
    		System.out.println("OPTION = " + el.getText());
    		allOptions.add(el.getText());
    	}
    	
    	/*
    	for (String player : playerList) {
    		
    		System.out.println("PLAYER = " + player);
    		Assert.assertTrue(allOptions.contains(player));
    	}*/
    	
    	Assert.assertEquals(allOptions.size() - 1, playerList.size());
    	
    }
}

