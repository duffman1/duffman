package com.nbcuni.test.publisher.pageobjects.Structure;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

/*********************************************
 * publisher.nbcuni.com Add a New Permission Set Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 24, 2014
 *********************************************/

public class AddNewPermissionSet {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public AddNewPermissionSet(WebDriver webWebWebDriver, AppLib applib) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By PermissionSetName_Txb = By.id("edit-name");
    
    private By Save_Btn = By.id("edit-submit");
    
    private By Edit_Tab = By.xpath("//a[text()='Edit']");
    
    private By Export_Tab = By.xpath("//a[text()='Export']");
    
    private By Export_Txa = By.id("edit-code");
    
    private By Permission_Cbx(String value) {
    	return By.cssSelector("input[value='" + value + "']");
    }
    
    private By AllPermission_Cbxs = By.cssSelector("table[id='permissions'] input[type='checkbox']");
   
    
    //PAGE OBJECT METHODS
    public void EnterPermissionSetName(String setName) throws Exception {
    	
    	Reporter.log("Enter '" + setName + "' in the 'Permission Set Name' text box.");
    	interact.Type(waitFor.ElementVisible(PermissionSetName_Txb), setName);
    	
    }
    
    public void EnablePermissions(List<String> permissionValues) throws Exception {
    	
    	for (String value : permissionValues) {
    		WebElement ele = waitFor.ElementVisible(Permission_Cbx(value));
    		if (!ele.isSelected()) {
    			Reporter.log("Check the '" + value + "' checkbox.");
    			interact.ScrollToTop();
    			interact.Click(ele);
    		}
    	}
    	
    }
    
    public void DisablePermissions(List<String> permissionValues) throws Exception {
    	
    	for (String value : permissionValues) {
    		WebElement ele = waitFor.ElementVisible(Permission_Cbx(value));
    		if (ele.isSelected()) {
    			Reporter.log("Uncheck the '" + value + "' checkbox.");
    			interact.ScrollToTop();
    			interact.Click(ele);
    		}
    	}
    	
    }
    
    public void VerifyAllPermissionCbxsNotChecked() throws Exception {
    	
    	Reporter.log("Verify all permission checkboxes are not checked.");
    	for (WebElement cbx : waitFor.ElementsPresent(AllPermission_Cbxs)) {
    		if (cbx.isSelected()) {
    			Assert.fail("Check box with value '" + cbx.getAttribute("value") + "' is checked.");
    		}
    	}
    	
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	interact.Click(waitFor.ElementVisible(Save_Btn));
    	
    }
    
    public void ClickExportTab() throws Exception {
    	
    	Reporter.log("Click the 'Export' tab.");
    	interact.Click(waitFor.ElementVisible(Export_Tab));
    	
    }
    
    public void ClickEditTab() throws Exception {
    	
    	Reporter.log("Click the 'Edit' tab.");
    	interact.Click(waitFor.ElementVisible(Edit_Tab));
    	
    }
    
    public void VerifyExportCodeValue(String value) throws Exception {
    	
    	Reporter.log("Verify 'Export' text area value is '" + value + "'.");
    	Assert.assertEquals(waitFor.ElementVisible(Export_Txa).getAttribute("value"), value);
    	
    }
    
}

