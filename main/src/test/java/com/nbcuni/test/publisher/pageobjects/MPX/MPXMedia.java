package com.nbcuni.test.publisher.pageobjects.MPX;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


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
    private static String SynchMPXMediaForAccount1_Ddl = "(//select[contains(@id, 'edit-video-sync-settings')])[1]";
    private static String SynchMPXMediaForAccount2_Ddl = "(//select[contains(@id, 'edit-video-sync-settings')])[2]";
    private static String SynchMPXMediaForAccount3_Ddl = "(//select[contains(@id, 'edit-video-sync-settings')])[3]";
    private static String SynchMPXMediaForAccount4_Ddl = "(//select[contains(@id, 'edit-video-sync-settings')])[4]";

    
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

    public void ClickSyncMPXMediaNowLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 30).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(SyncMPXMediaNow_Lnk)))).click();;
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
    
    public void SelectMPXPlayerForAccount1(String playerTitle) throws Exception {
    	
    	new Select(new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(SynchMPXMediaForAccount1_Ddl))))).selectByVisibleText(playerTitle);
    }
    
    public void SelectMPXPlayerForAccount2(String playerTitle) throws Exception {
    	
    	new Select(new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(SynchMPXMediaForAccount2_Ddl))))).selectByVisibleText(playerTitle);
    }
    
    public void SelectMPXPlayerForAccount3(String playerTitle) throws Exception {
    	
    	new Select(new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(SynchMPXMediaForAccount3_Ddl))))).selectByVisibleText(playerTitle);
    }

    public void SelectMPXPlayerForAccount4(String playerTitle) throws Exception {

        new Select(new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                visibilityOf(webDriver.findElement(By.xpath(SynchMPXMediaForAccount4_Ddl))))).selectByVisibleText(playerTitle);
    }
    
    public void ClickMPXPlayerUnpublishedHereLnk(String playerTitle) throws Exception {

        webDriver.findElement(By.xpath("//div[@class='messages error']//strong//em[text()='" + playerTitle + "']/../..//a[text()='here']")).click();
    }
}

