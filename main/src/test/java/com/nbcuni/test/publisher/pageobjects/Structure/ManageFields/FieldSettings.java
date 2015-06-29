package com.nbcuni.test.publisher.pageobjects.Structure.ManageFields;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Field Settings Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: April 13, 2014
 *********************************************/

public class FieldSettings {

    private WaitFor waitFor;
    private Interact interact;
    private Config config;
    
    //PAGE OBJECT CONSTRUCTOR
    public FieldSettings(WebDriver webWebWebDriver) {
        config = new Config();
        Integer timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By TargetType_Ddl = By.id("edit-field-settings-target-type");
    
    private By Mode_Ddl = By.xpath("//label[contains(text(), 'Mode')]/../select");
    
    private By TargetBundle_Cbx(String bundle) {
    	return By.xpath("//label[contains(text(), '" + bundle + "')]/../input");
    }
    
    private By SortBy_Ddl = By.xpath("//label[contains(text(), 'Sort by')]/../select");
    
    private By PublicFiles_Rdb = By.id("edit-field-settings-uri-scheme-public");
    
    private By SaveFieldSettings_Btn = By.id("edit-submit");
    
    
    //PAGE OBJECT METHODS
    public void SelectTargetType(String type) throws Exception {
    	
    	Reporter.log("Select the '" + type + "' option from the 'Target type' drop down list.");
    	interact.Select(waitFor.ElementVisible(TargetType_Ddl), type);
    	
    }
    
    public void SelectMode(String mode) throws Exception {
    	
    	Reporter.log("Select the '" + mode + "' option from the 'Mode' drop down list.");
    	interact.Select(waitFor.ElementVisible(Mode_Ddl), mode);
    	
    }
    
    public void CheckTargetBundleCbx(String label) throws Exception {
    	
    	WebElement cbx = waitFor.ElementVisible(TargetBundle_Cbx(label));
    	
    	if (cbx.isSelected() == false) {
    		Reporter.log("Chech the '" + label + "' 'Target bundles' check box.");
    		interact.Click(cbx);
    	}
    	
    }
    
    public void SelectSortBy(String sortBy) throws Exception {
    	
    	Reporter.log("Select the '" + sortBy + "' option from the 'Sort by' drop down list.");
    	interact.Select(waitFor.ElementVisible(SortBy_Ddl), sortBy);
    	
    }
    
    public void ClickPublicFilesRdb() throws Exception {
    	
    	Reporter.log("Click the 'Public files' radio button.");
    	interact.Click(waitFor.ElementVisible(PublicFiles_Rdb));
    	
    }

    public void ClickSaveFieldSettingsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save field settings' button.");
    	interact.Click(waitFor.ElementVisible(SaveFieldSettings_Btn));
    	
    }
    
}

