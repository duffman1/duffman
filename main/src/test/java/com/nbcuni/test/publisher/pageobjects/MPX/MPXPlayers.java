package com.nbcuni.test.publisher.pageobjects.MPX;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com MPX Players Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 31, 2013
 *********************************************/

public class MPXPlayers {

	private static CustomWebDriver webDriver;
	
    //PAGE OBJECT CONSTRUCTOR
    public MPXPlayers(CustomWebDriver webDriver) {
    	MPXPlayers.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='mpxPlayers']")
    private static WebElement MPXPlayers_Lnk;
    
    @FindBy(how = How.XPATH, using = "//a[@class='fieldset-title']")
    private static WebElement SyncMPXPlayers_Lnk;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Sync mpxPlayers Now']")
    private static WebElement SyncMPXPlayersNow_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void ClickMPXPlayersLnk() throws Exception {
    	
    	Reporter.log("Click the 'MPX Players' link.");
    	MPXPlayers_Lnk.click();
    }
    
    public void ClickSyncMPXPlayersLnk() throws Exception {
    	
    	Reporter.log("Click the 'Sync MPX Players' link.");
    	SyncMPXPlayers_Lnk.click();
    }
    
    public void ClickSyncMPXPlayersNowLnk() throws Exception {
    	
    	Reporter.log("Click the 'Sync MPX Players Now' link.");
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(SyncMPXPlayersNow_Lnk)).click();
    }
    
}

