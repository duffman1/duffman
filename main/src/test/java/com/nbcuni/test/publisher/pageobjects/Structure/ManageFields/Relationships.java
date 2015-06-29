package com.nbcuni.test.publisher.pageobjects.Structure.ManageFields;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Relationships Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 1, 2014
 *********************************************/

public class Relationships {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public Relationships(WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By TVRelationshipWidgetDepth_Ddl = By.id("edit-instance-widget-settings-depth");
    
    private By SaveSettings_Btn = By.id("edit-submit");
    
    
    //PAGE OBJECT METHODS
    public void SelectTVRelationshipWidgetDepth(String depth) throws Exception {
    	
    	Reporter.log("Select '" + depth + "' from the 'TV Relationship Widget Depth' drop down list.");
    	interact.Select(waitFor.ElementVisible(TVRelationshipWidgetDepth_Ddl), depth);
    	
    }
    
    public void ClickSaveSettingsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save settings' button.");
    	interact.Click(waitFor.ElementVisible(SaveSettings_Btn));
    	
    }
    
}

