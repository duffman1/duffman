package com.nbcuni.test.publisher.pageobjects.Structure.ManageFields;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

/*********************************************
 * publisher.nbcuni.com Edit Field Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: April 13, 2014
 *********************************************/

public class Edit {

    private WebDriver webWebWebDriver;
    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public Edit(WebDriver webWebWebDriver, AppLib applib) {
        this.webWebWebDriver = webWebWebDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By EnabledBrowserPlugins_Cbx(String plugin) {
    	return By.xpath("//label[contains(text(), '" + plugin + "')]/../input");
    }
    
    private By AllowedRemoteMediaTypes_Cbx(String media) {
    	return By.xpath("//label[contains(text(), '" + media + "')]/../input");
    }
    
    private By AllowedURISchemes_Cbx(String uriScheme) {
    	return By.xpath("//label[contains(text(), '" + uriScheme + "')]/../input");
    }
    
    private By FileDirectory_Txb = By.id("edit-instance-settings-file-directory");
    
    private By SaveSettings_Btn = By.id("edit-submit");
    
    
    //PAGE OBJECT METHODS
    public void CheckEnabledBrowserPluginCbx(String plugin) throws Exception {
    	
    	WebElement ele = waitFor.ElementVisible(EnabledBrowserPlugins_Cbx(plugin));
    	if (!ele.isSelected()) {
    		Reporter.log("Chech the '" + plugin + "' check box under 'Enabled browser plugins'.");
    		interact.Click(ele);
    	}
    	
    }
    
    public void CheckAllowedRemoteMediaTypesCbx(String media) throws Exception {
    	
    	WebElement ele = waitFor.ElementVisible(AllowedRemoteMediaTypes_Cbx(media));
    	if (!ele.isSelected()) {
    		Reporter.log("Chech the '" + media + "' check box under 'Allowed remote media types'.");
    		interact.Click(ele);
    	}
    	
    }
    
    public void CheckAllowedURISchemesCbx(String uriScheme) throws Exception {
    	
    	webWebWebDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	try {
    		WebElement ele = webWebWebDriver.findElement(AllowedURISchemes_Cbx(uriScheme));
    		if (!ele.isSelected()) {
        		Reporter.log("Chech the '" + uriScheme + "' check box under 'Allowed URI schemes'.");
        		interact.Click(ele);
        	}
    	}
    	catch (NoSuchElementException e) {}
    	webWebWebDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    
    }
    
    public void VerifyFileDirectoryValue(String path) throws Exception {
    	
    	Reporter.log("Verify the 'File directory' value is '" + path + "'.");
    	Assert.assertEquals(path, waitFor.ElementVisible(FileDirectory_Txb).getAttribute("value"));
    	
    }
    
    public void ClickSaveSettingsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save settings' button.");
    	interact.Click(waitFor.ElementVisible(SaveSettings_Btn));
    	
    }
    
}

