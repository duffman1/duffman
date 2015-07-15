package com.nbcuni.test.publisher.pageobjects.Configuration;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Gigya Settings Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 10, 2014
 *********************************************/

public class GigyaSettings {

	private WaitFor waitFor;
	private Interact interact;
	private Config config;
    private Driver webDriver;
	
    //PAGE OBJECT CONSTRUCTOR    
    public GigyaSettings(Driver webDriver) {
        config = new Config();
        Integer timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
        this.webDriver = webDriver;
    }
    
    //PAGE OBJECT IDENTIFIERS    
    private By Providers_Txb = By.id("edit-gigya-share-buttons");
    
    private By AdvancedShareBarSettings_Lnk = By.cssSelector("#edit-gigya-advanced-sharing a");
    
    private By AdvancedShowShareBarUISettings_Txa = By.id("edit-gigya-share-advanced");
    
    private By SaveConfiguration_Btn = By.id("edit-submit");


    @FindBy(xpath = "//fieldset[@id='edit-gigya-advanced-sharing']//a")
    private WebElement advancedGigyaSharing;



    
    //PAGE OBJECT METHODS
    public void EnterProviders(String providerData) throws Exception { 
    	
    	Reporter.log("Enter '" + providerData + "' in the Privider text box.");
    	interact.Type(waitFor.ElementVisible(Providers_Txb), providerData);
    
    }
    
    public void ClickGigyaAdvancedShareBarSettingsLnk() throws Exception {
    	
    	Reporter.log("Click the 'ADVANCED SHARE BAR SETTINGS' link to expand advanced menu options.");
        WebElement gigya = webDriver.findElement(AdvancedShareBarSettings_Lnk);
//    	interact.Click(waitFor.ElementVisible(AdvancedShareBarSettings_Lnk));
    	webDriver.executeScript("arguments[0].click();", gigya);
    }
    
    public void EnterAdvancedShowShareBarUISettings(String settings) throws Exception {
    	
    	Reporter.log("Enter '" + settings + "' in the 'Advanced showShareBarUI settings' text area.");
    	interact.Type(waitFor.ElementVisible(AdvancedShowShareBarUISettings_Txa), settings);
    	
    }
    
    public void ClickSaveConfiguration_Btn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));
    	
    }
    
}