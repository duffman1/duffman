package com.nbcuni.test.publisher.pageobjects.Structure.ManageFields;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Edit Field Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: April 13, 2014
 *********************************************/

public class Edit {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public Edit(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private WebElement EnabledBrowserPlugins_Cbx(String plugin) {
    	return webDriver.findElement(By.xpath("//label[contains(text(), '" + plugin + "')]/../input"));
    }
    
    private WebElement AllowedRemoteMediaTypes_Cbx(String media) {
    	return webDriver.findElement(By.xpath("//label[contains(text(), '" + media + "')]/../input"));
    }
    
    private WebElement AllowedURISchemes_Cbx(String uriScheme) {
    	return webDriver.findElement(By.xpath("//label[contains(text(), '" + uriScheme + "')]/../input"));
    }
    
    @FindBy(how = How.ID, using = "edit-instance-settings-file-directory")
    private WebElement FileDirectory_Txb;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveSettings_Btn;
    
    
    //PAGE OBJECT METHODS
    public void CheckEnabledBrowserPluginCbx(String plugin) throws Exception {
    	
    	if (EnabledBrowserPlugins_Cbx(plugin).isSelected() == false) {
    		Reporter.log("Chech the '" + plugin + "' check box under 'Enabled browser plugins'.");
    		EnabledBrowserPlugins_Cbx(plugin).click();
    	}
    }
    
    public void CheckAllowedRemoteMediaTypesCbx(String media) throws Exception {
    	
    	if (AllowedRemoteMediaTypes_Cbx(media).isSelected() == false) {
    		Reporter.log("Chech the '" + media + "' check box under 'Allowed remote media types'.");
    		AllowedRemoteMediaTypes_Cbx(media).click();
    	}
    }
    
    public void CheckAllowedURISchemesCbx(String uriScheme) throws Exception {
    	
    	if (AllowedURISchemes_Cbx(uriScheme).isSelected() == false) {
    		Reporter.log("Chech the '" + uriScheme + "' check box under 'Allowed URI schemes'.");
    		AllowedURISchemes_Cbx(uriScheme).click();
    	}
    }
    
    public void EnterFileDirectory(String path) throws Exception {
    	
    	Reporter.log("Enter '" + path + "' in the 'File directory' text box.");
    	FileDirectory_Txb.clear();
    	FileDirectory_Txb.sendKeys(path);
    }
    
    public void ClickSaveSettingsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save settings' button.");
    	SaveSettings_Btn.click();
    	
    }
    
}

