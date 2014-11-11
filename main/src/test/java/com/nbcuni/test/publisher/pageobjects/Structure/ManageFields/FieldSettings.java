package com.nbcuni.test.publisher.pageobjects.Structure.ManageFields;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Field Settings Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: April 13, 2014
 *********************************************/

public class FieldSettings {

    private WaitFor waitFor;
    private Config config;
    
    //PAGE OBJECT CONSTRUCTOR
    public FieldSettings(Driver webDriver, AppLib applib) {
        PageFactory.initElements(webDriver, this);
        config = new Config();
        waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-field-settings-target-type")
    private WebElement TargetType_Ddl;
    
    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Mode')]/../select")
    private WebElement Mode_Ddl;
    
    private By TargetBundle_Cbx(String bundle) {
    	return By.xpath("//label[contains(text(), '" + bundle + "')]/../input");
    }
    
    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Sort by')]/../select")
    private WebElement SortBy_Ddl;
    
    @FindBy(how = How.ID, using = "edit-field-settings-uri-scheme-public")
    private WebElement PublicFiles_Rdb;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveFieldSettings_Btn;
    
    
    //PAGE OBJECT METHODS
    public void SelectTargetType(String type) throws Exception {
    	
    	Reporter.log("Select the '" + type + "' option from the 'Target type' drop down list.");
    	new Select(TargetType_Ddl).selectByVisibleText(type);
    	
    }
    
    public void SelectMode(String mode) throws Exception {
    	
    	Reporter.log("Select the '" + mode + "' option from the 'Mode' drop down list.");
    	Thread.sleep(1000);
    	new Select(waitFor.ElementVisible(Mode_Ddl)).selectByVisibleText(mode);
    	
    }
    
    public void CheckTargetBundleCbx(String label) throws Exception {
    	
    	WebElement cbx = waitFor.ElementVisible(TargetBundle_Cbx(label));
    	
    	if (cbx.isSelected() == false) {
    		Reporter.log("Chech the '" + label + "' 'Target bundles' check box.");
    		cbx.click();
    	}
    }
    
    public void SelectSortBy(String sortBy) throws Exception {
    	
    	Reporter.log("Select the '" + sortBy + "' option from the 'Sort by' drop down list.");
    	new Select(SortBy_Ddl).selectByVisibleText(sortBy);
    	
    }
    
    public void ClickPublicFilesRdb() throws Exception {
    	
    	Reporter.log("Click the 'Public files' radio button.");
    	PublicFiles_Rdb.click();
    	
    }

    public void ClickSaveFieldSettingsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save field settings' button.");
    	SaveFieldSettings_Btn.click();
    	
    }
    
}

