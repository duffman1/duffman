package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com MPS Configuration Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: June 2, 2014
 *********************************************/

public class MPSConfiguration {

	private Driver webDriver;
	
    //PAGE OBJECT CONSTRUCTOR    
    public MPSConfiguration(Driver webDriver) {
    	this.webDriver = webDriver;
    	PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS    
    @FindBy(how = How.ID, using ="edit-mps-host")
    private WebElement MPSHost_Txb;
    
    private WebElement IntegrationMethod_Rdb(String integrationMethod) {
    	return webDriver.findElement(By.xpath("//label[text()='" + integrationMethod + " ']/..//input"));
    }
    
    @FindBy(how = How.ID, using ="edit-mps-site-override")
    private WebElement SiteInstanceOverride_Txb;
    
    @FindBy(how = How.ID, using ="edit-mps-query")
    private WebElement SendQueryStrings_Cbx;
    
    private WebElement Name_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'name')])[" + index + "]"));
    }
    
    private WebElement Value_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'value')])[" + index + "]"));
    }
    
    private WebElement JSON_Cbx(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'json')])[" + index + "]"));
    }
    
    @FindBy(how = How.ID, using ="edit-add-another-opt")
    private WebElement AddAnotherOpt_Btn;
    
    @FindBy(how = How.ID, using ="edit-submit")
    private WebElement SaveConfiguration_Btn;
    
    
    //PAGE OBJECT METHODS
    public Boolean IsMPSEnabled() throws Exception { 
    	
    	Boolean mpsEnabled;
    	if (webDriver.getPageSource().contains("mpscall")) {
    		mpsEnabled = true;
    	}
    	else {
    		mpsEnabled = false;
    	}
    	
    	return mpsEnabled;
    }

    public void VerifyNoMPSCallsMade() throws Exception {
    	
    	Reporter.log("Verify that no MPS calls were made on the page.");
    	Assert.assertFalse(this.IsMPSEnabled());
    }
    
    public void EnterMPSHost(String host) throws Exception { 
    	
    	Reporter.log("Enter '" + host + "' in the 'MPS Host' text box."); 
    	MPSHost_Txb.clear();
    	MPSHost_Txb.sendKeys(host);
    }
    
    public void ClickIntegrationMethod(String label) throws Exception { 
    	
    	Reporter.log("Click the '" + label + "' radio button."); 
    	IntegrationMethod_Rdb(label).click();
    }
    
    public void EnterSiteInstanceOverride(String override) throws Exception { 
    	
    	Reporter.log("Enter '" + override + "' in the 'Site Instance Override' text box.");
    	SiteInstanceOverride_Txb.clear();
    	SiteInstanceOverride_Txb.sendKeys(override);
    }
    
    public void CheckSendQueryStringsCbx() throws Exception { 
    	
    	if (SendQueryStrings_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Send Query Strings' check box.");
    		SendQueryStrings_Cbx.click();
    	}
    }
    
    public void UnCheckSendQueryStringsCbx() throws Exception { 
    	
    	if (SendQueryStrings_Cbx.isSelected() == true) {
    		Reporter.log("Un-check the 'Send Query Strings' check box.");
    		SendQueryStrings_Cbx.click();
    	}
    }
    
    public void EnterName(String nameTxt, String index) throws Exception { 
    	
    	Reporter.log("Enter '" + nameTxt + "' in the 'Name' text box with index '" + index + "'.");
    	Name_Txb(index).clear();
    	Name_Txb(index).sendKeys(nameTxt);
    }
    
    public void EnterValue(String valueTxt, String index) throws Exception { 
    	
    	Reporter.log("Enter '" + valueTxt + "' in the 'Value' text box with index '" + index + "'.");
    	Value_Txb(index).clear();
    	Value_Txb(index).sendKeys(valueTxt);
    }
    
    public void CheckJSONCbx(String index) throws Exception { 
    	
    	Reporter.log("Check the 'JSON' check box with index '" + index + "'.");
    	JSON_Cbx(index).click();
    	
    }
    
    public void ClickAddAnotherOptBtn() throws Exception { 
    	
    	Reporter.log("Click the 'Add another opt' button.");
    	AddAnotherOpt_Btn.click();
    }

    public void ClickSaveConfigurationBtn() throws Exception { 
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
    
}