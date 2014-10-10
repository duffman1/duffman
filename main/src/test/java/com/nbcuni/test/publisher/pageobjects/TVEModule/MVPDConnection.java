package com.nbcuni.test.publisher.pageobjects.TVEModule;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com MVPD Connection Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 29, 2014
 *********************************************/

public class MVPDConnection {

    //PAGE OBJECT CONSTRUCTOR
    public MVPDConnection(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-tve-mvpd-service")
    private WebElement MVPDServiceURL_Txb;
    
    @FindBy(how = How.ID, using = "edit-tve-mvpd-brand-id")
    private WebElement RequestorID_Txb;
    
    @FindBy(how = How.ID, using = "edit-tve-mvpd-instance")
    private WebElement Instance_Ddl;
    
    @FindBy(how = How.ID, using = "edit-tve-mvpd-error-message")
    private WebElement GenericErrorMessage_Txa;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveConfiguration_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterMVPDServiceURL(String url) throws Exception {
    	
    	Reporter.log("Enter '" + url + "' in the 'MVPD Service URL' text box.");
    	MVPDServiceURL_Txb.sendKeys(url);
    }
    
    public void EnterRequestorID(String id) throws Exception {
    	
    	Reporter.log("Enter '" + id + "' in the 'Requestor ID' text box.");
    	RequestorID_Txb.sendKeys(id);
    }
    
    public void SelectInstance(String instance) throws Exception {
    	
    	Reporter.log("Select '" + instance + "' from the 'Instance' drop down list.");
    	new Select(Instance_Ddl).selectByVisibleText(instance);
    }
    
    public void EnterGenericErrorMessage(String message) throws Exception {
    	
    	Reporter.log("Enter '" + message + "' in the 'Generic Error Message' text area.");
    	GenericErrorMessage_Txa.clear();
    	GenericErrorMessage_Txa.sendKeys(message);
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
}

