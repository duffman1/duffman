package com.nbcuni.test.publisher.pageobjects.Structure;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
* publisher.nbcuni.com Manage Field Input Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/

public class ManageFieldInput {

    //PAGE OBJECT CONSTRUCTOR
    public ManageFieldInput(CustomWebDriver webDriver) {
    	PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-instance-widget-settings-soft-length-minimum")
    private static WebElement SoftLengthMinimum_Txb;
    
    @FindBy(how = How.ID, using = "edit-instance-widget-settings-soft-length-limit")
    private static WebElement SoftLengthLimit_Txb;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private static WebElement SaveSettings_Btn;

    
    //PAGE OBJECT METHODS
    public void EnterSoftLengthMinimum(String characterCount) throws Exception {
    
    	Reporter.log("Enter '" + characterCount + "' in the 'Soft length minimum' text box.");
    	SoftLengthMinimum_Txb.clear();
    	SoftLengthMinimum_Txb.sendKeys(characterCount);
    }
    
    public void EnterSoftLengthLimit(String characterCount) throws Exception {
        
    	Reporter.log("Enter '" + characterCount + "' in the 'Soft length limit' text box.");
    	SoftLengthLimit_Txb.clear();
    	SoftLengthLimit_Txb.sendKeys(characterCount);
    }
    
    public void ClickSaveSettingsBtn() throws Exception {
        
    	Reporter.log("Click the 'Save settings' button.");
    	SaveSettings_Btn.click();
    }
    
}
