package com.nbcuni.test.publisher.pageobjects.TVEModule;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Adobe Pass Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 29, 2014
 *********************************************/

public class AdobePass {

    //PAGE OBJECT CONSTRUCTOR
    public AdobePass(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-tve-adobe-pass-accessenabler-loc")
    private WebElement AccessEnablerLocation_Txb;
    
    @FindBy(how = How.ID, using = "edit-tve-adobe-pass-timeout-length")
    private WebElement RequestTimeout_Txb;
    
    @FindBy(how = How.ID, using = "edit-tve-adobe-pass-flash-ver")
    private WebElement AdobeFlashVersion_Txb;
    
    @FindBy(how = How.ID, using = "edit-tve-adobe-pass-requestor-id")
    private WebElement RequestorID_Txb;
    
    @FindBy(how = How.ID, using = "edit-tve-adobe-pass-resource-id")
    private WebElement ResourceID_Txb;
    
    @FindBy(how = How.ID, using = "edit-tve-adobe-pass-message-auth-err")
    private WebElement UserNotAuthorizedError_Txb;
    
    @FindBy(how = How.ID, using = "edit-tve-adobe-pass-message-internal-err")
    private WebElement InternalAuthorizationError_Txb;
    
    @FindBy(how = How.ID, using = "edit-tve-adobe-pass-message-generic-err")
    private WebElement GenericAuthorizationError_Txb;
    
    @FindBy(how = How.ID, using = "edit-tve-adobe-pass-config-service-path")
    private WebElement AdobePassConfigServicePath_Txb;
    
    @FindBy(how = How.ID, using = "edit-test-connection")
    private WebElement TestConfiguration_Btn;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveConfiguration_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterAccessEnablerLocation(String url) throws Exception {
    	
    	Reporter.log("Enter '" + url + "' in the 'Access Enabler Location' text box.");
    	AccessEnablerLocation_Txb.clear();
    	AccessEnablerLocation_Txb.sendKeys(url);
    }
    
    public void EnterRequestTimeout(String timeout) throws Exception {
    	
    	Reporter.log("Enter '" + timeout + "' in the 'Request Timeout' text box.");
    	RequestTimeout_Txb.clear();
    	RequestTimeout_Txb.sendKeys(timeout);
    }
    
    public void EnterAdobeFlashVersion(String version) throws Exception {
    	
    	Reporter.log("Enter '" + version + "' in the 'Adobe Flash Version' text box.");
    	AdobeFlashVersion_Txb.clear();
    	AdobeFlashVersion_Txb.sendKeys(version);
    }
    
    public void EnterRequestorID(String id) throws Exception {
    	
    	Reporter.log("Enter '" + id + "' in the 'Requestor ID' text box.");
    	RequestorID_Txb.clear();
    	RequestorID_Txb.sendKeys(id);
    }
    
    public void EnterResourceID(String id) throws Exception {
    	
    	Reporter.log("Enter '" + id + "' in the 'Resource ID' text box.");
    	RequestorID_Txb.clear();
    	RequestorID_Txb.sendKeys(id);
    }
    
    public void EnterUserNotAuthorizedError(String error) throws Exception {
    	
    	Reporter.log("Enter '" + error + "' in the 'User not Authorized Error' text box.");
    	UserNotAuthorizedError_Txb.clear();
    	UserNotAuthorizedError_Txb.sendKeys(error);
    }
    
    public void EnterInternalAuthorizationError(String error) throws Exception {
    	
    	Reporter.log("Enter '" + error + "' in the 'Internal Authorization Error' text box.");
    	InternalAuthorizationError_Txb.clear();
    	InternalAuthorizationError_Txb.sendKeys(error);
    }
    
    public void EnterGenericAuthorizationError(String error) throws Exception {
    	
    	Reporter.log("Enter '" + error + "' in the 'Generic Authorization Error' text box.");
    	GenericAuthorizationError_Txb.clear();
    	GenericAuthorizationError_Txb.sendKeys(error);
    }
    
    public void EnterAdobePassConfigServicePath(String url) throws Exception {
    	
    	Reporter.log("Enter '" + url + "' in the 'Adobe Pass Config Service Path' text box.");
    	AdobePassConfigServicePath_Txb.clear();
    	AdobePassConfigServicePath_Txb.sendKeys(url);
    }
    
    public void ClickTestConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Test configuration' button.");
    	TestConfiguration_Btn.click();
    }

    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
}

