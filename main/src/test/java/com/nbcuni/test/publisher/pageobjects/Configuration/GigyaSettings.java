package com.nbcuni.test.publisher.pageobjects.Configuration;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Gigya Settings Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 10, 2014
 *********************************************/

public class GigyaSettings {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    
    //PAGE OBJECT CONSTRUCTOR    
    public GigyaSettings(CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
    }
    
    //PAGE OBJECT IDENTIFIERS    
    @FindBy(how = How.ID, using ="edit-gigya-share-buttons")
    private static WebElement Providers_Txb;
    
    @FindBy(how = How.XPATH, using ="//fieldset[@id='edit-gigya-advanced-sharing']//a")
    private static WebElement AdvancedShareBarSettings_Lnk;
    
    @FindBy(how = How.ID, using ="edit-gigya-share-advanced")
    private static WebElement AdvancedShowShareBarUISettings_Txa;
    
    @FindBy(how = How.ID, using ="edit-submit")
    private static WebElement SaveConfiguration_Btn;
    
    
  
    //PAGE OBJECT METHODS
    public void EnterProviders(String providerData) throws Exception { 
    	
    	Reporter.log("Enter '" + providerData + "' in the Privider text box."); 
    	Providers_Txb.clear();
    	Providers_Txb.sendKeys(providerData);
    }
    
    public void ClickGigyaAdvancedShareBarSettingsLnk() throws Exception {
    	
    	Reporter.log("Click the 'ADVANCED SHARE BAR SETTINGS' link to expand advanced menu options.");
    	AdvancedShareBarSettings_Lnk.click();
    }
    
    public void EnterAdvancedShowShareBarUISettings(String settings) throws Exception {
    	
    	Reporter.log("Enter '" + settings + "' in the 'Advanced showShareBarUI settings' text area.");
    	Thread.sleep(1000);
    	AdvancedShowShareBarUISettings_Txa.clear();
    	AdvancedShowShareBarUISettings_Txa.sendKeys(settings);
    }
    
    public void ClickSaveConfiguration_Btn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
    
}