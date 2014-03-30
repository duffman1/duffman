package com.nbcuni.test.publisher.pageobjects.Structure;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.common.Driver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
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

    private Driver webDriver;
    ContentParent contentParent;
    
    //PAGE OBJECT CONSTRUCTOR
    public AddNewPermissionSet(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        contentParent = new ContentParent(webDriver, applib);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-name")
    private WebElement PermissionSetName_Txb;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Save_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Edit']")
    private WebElement Edit_Tab;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Export']")
    private WebElement Export_Tab;
    
    @FindBy(how = How.ID, using = "edit-code")
    private WebElement Export_Txa;
    
    private WebElement Permission_Cbx(String value) {
    	return webDriver.findElement(By.cssSelector("input[value='" + value + "']"));
    }
    
    private List<WebElement> AllPermission_Cbxs() {
    	return webDriver.findElements(By.cssSelector("table[id='permissions'] input[type='checkbox']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void EnterPermissionSetName(String setName) throws Exception {
    	
    	Reporter.log("Enter '" + setName + "' in the 'Permission Set Name' text box.");
    	PermissionSetName_Txb.sendKeys(setName);
    }
    
    public void EnablePermissions(List<String> permissionValues) throws Exception {
    	
    	for (String value : permissionValues) {
    		if (Permission_Cbx(value).isSelected() == false) {
    			Reporter.log("Check the '" + value + "' checkbox.");
    			try {
    				Permission_Cbx(value).click();
    			}
    			catch (WebDriverException e) {
    				contentParent.Scroll("-500");
    				Permission_Cbx(value).click();
    			}
    		}
    	}
    }
    
    public void DisablePermissions(List<String> permissionValues) throws Exception {
    	
    	for (String value : permissionValues) {
    		if (Permission_Cbx(value).isSelected() == true) {
    			Reporter.log("Uncheck the '" + value + "' checkbox.");
    			Permission_Cbx(value).click();
    		}
    	}
    }
    
    public void VerifyAllPermissionCbxsNotChecked() throws Exception {
    	
    	Reporter.log("Verify all permission checkboxes are not checked.");
    	for (WebElement cbx : AllPermission_Cbxs()) {
    		if (cbx.isSelected() == true) {
    			Assert.fail("Check box with value '" + cbx.getAttribute("value") + "' is checked.");
    		}
    	}
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    public void ClickExportTab() throws Exception {
    	
    	Reporter.log("Click the 'Export' tab.");
    	Export_Tab.click();
    }
    
    public void ClickEditTab() throws Exception {
    	
    	Reporter.log("Click the 'Edit' tab.");
    	Edit_Tab.click();
    }
    
    public void VerifyExportCodeValue(String value) throws Exception {
    	
    	Reporter.log("Verify 'Export' text area value is '" + value + "'.");
    	Assert.assertEquals(Export_Txa.getAttribute("value"), value);
    }
    
}

