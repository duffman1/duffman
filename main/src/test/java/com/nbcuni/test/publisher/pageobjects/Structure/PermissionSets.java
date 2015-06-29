package com.nbcuni.test.publisher.pageobjects.Structure;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/*********************************************
* publisher.nbcuni.com Permission Sets Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 25, 2014
*********************************************/

public class PermissionSets {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public PermissionSets(WebDriver webWebWebDriver) {
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webWebWebDriver, timeout);
    	interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Add_Lnk = By.linkText("Add");
    
    private By PermissionSetEdit_Lnk(String setName) {
    	return By.xpath("//td[text()='" + setName + "']/..//a[text()='Edit']");
    }
    
    private By PermissionSetName_Txt(String setName) {
    	return By.xpath("//td[text()='" + setName + "']");
    }
    
    private By PermissionSetExpandEdit_Lnk(String setName) {
    	return By.xpath("//td[text()='" + setName + "']/..//a[text()='open']");
    }
    
    private By PermissionSetDelete_Lnk(String setName) {
    	return By.xpath("//td[text()='" + setName + "']/..//a[text()='Delete']");
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddLnk() throws Exception {
    
    	Reporter.log("Click the 'Add' link.");
    	interact.Click(waitFor.ElementVisible(Add_Lnk));
    	
    }
    
    public void ClickPermissionSetEditLnk(String setName) throws Exception {
        
    	Reporter.log("Click the Permission Set 'Edit' link for set name + '" + setName + "'.");
    	interact.Click(waitFor.ElementVisible(PermissionSetEdit_Lnk(setName)));
    	
    }
    
    public void ClickPermissionSetExpandEditLnk(String setName) throws Exception {
        
    	Reporter.log("Click the Permission Set expand link for Set Name + '" + setName + "'.");
    	interact.Click(waitFor.ElementVisible(PermissionSetExpandEdit_Lnk(setName)));
    	
    }
    
    public void ClickPermissionSetDeleteLnk(String setName) throws Exception {
        
    	Reporter.log("Click the Permission Set 'Delete' link for Set Name + '" + setName + "'.");
    	interact.Click(waitFor.ElementVisible(PermissionSetDelete_Lnk(setName)));
    	
    }
    
    public void VerifyPermissionSetNotPresent(String setName) throws Exception {
        
    	Reporter.log("Verify Permission Set '" + setName + "' is NOT present.");
    	waitFor.ElementNotPresent(PermissionSetName_Txt(setName));
    	
    }
    
}
