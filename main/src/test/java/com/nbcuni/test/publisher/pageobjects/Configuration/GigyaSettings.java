package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

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
	
    //PAGE OBJECT CONSTRUCTOR    
    public GigyaSettings(Driver webDriver) {
        config = new Config();
        Integer timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS    
    private By Providers_Txb = By.id("edit-gigya-share-buttons");
    
    private By AdvancedShareBarSettings_Lnk = By.xpath("//fieldset[@id='edit-gigya-advanced-sharing']//a");
    
    private By AdvancedShowShareBarUISettings_Txa = By.id("edit-gigya-share-advanced");
    
    private By SaveConfiguration_Btn = By.id("edit-submit");
    
    
    //PAGE OBJECT METHODS
    public void EnterProviders(String providerData) throws Exception { 
    	
    	Reporter.log("Enter '" + providerData + "' in the Privider text box.");
    	interact.Type(waitFor.ElementVisible(Providers_Txb), providerData);
    
    }
    
    public void ClickGigyaAdvancedShareBarSettingsLnk() throws Exception {
    	
    	Reporter.log("Click the 'ADVANCED SHARE BAR SETTINGS' link to expand advanced menu options.");
    	interact.Click(waitFor.ElementVisible(AdvancedShareBarSettings_Lnk));
    	
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