package com.nbcuni.test.publisher.pageobjects.TVEModule;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com jQuery Update Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 29, 2014
 *********************************************/

public class jQueryUpdate {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
	
    //PAGE OBJECT CONSTRUCTOR
    public jQueryUpdate(WebDriver webWebWebDriver) {
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webWebWebDriver, timeout);
    	interact = new Interact(webWebWebDriver, timeout);
    }
    
    
    //PAGE OBJECT IDENTIFIERS
    private By DefaultjQueryVersion_Ddl = By.id("edit-jquery-update-jquery-version");
    
    private By SaveConfiguration_Btn = By.id("edit-submit");
    
    
    //PAGE OBJECT METHODS
    public void SelectDefaultjQueryVersion(String version) throws Exception {
    	
    	Reporter.log("Select '" + version + "' from the 'Default jQuery Version' drop down list.");
    	interact.Select(waitFor.ElementVisible(DefaultjQueryVersion_Ddl), version);
    	
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));
    	
    }
    
}

