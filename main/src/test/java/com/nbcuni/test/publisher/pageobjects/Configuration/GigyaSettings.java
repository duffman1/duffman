package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Gigya Settings Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 10, 2014
 *********************************************/

public class GigyaSettings {

    //PAGE OBJECT CONSTRUCTOR    
    public GigyaSettings(Driver webDriver, AppLib applib) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS    
    @FindBy(how = How.ID, using ="edit-gigya-share-buttons")
    private WebElement Providers_Txb;
    
    @FindBy(how = How.XPATH, using ="//fieldset[@id='edit-gigya-advanced-sharing']//a")
    private WebElement AdvancedShareBarSettings_Lnk;
    
    @FindBy(how = How.ID, using ="edit-gigya-share-advanced")
    private WebElement AdvancedShowShareBarUISettings_Txa;
    
    @FindBy(how = How.ID, using ="edit-submit")
    private WebElement SaveConfiguration_Btn;
    
    
  
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