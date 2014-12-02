package com.nbcuni.test.publisher.pageobjects.People;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

/*********************************************
 * publisher.nbcuni.com Permissions Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Permissions {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    
    //PAGE OBJECT CONSTRUCTOR
    public Permissions(Driver webDriver, AppLib applib) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AllRoleColumns_Ctr = By.xpath("(//th[text()='Permission'])[2]/../th[@class='checkbox']");
    
    private By SavePermissions_Btn = By.cssSelector("input[value='Save permissions']");
    
    private By SingleRolePermission_Cbx(String value) {
    	return By.cssSelector("input[value='" + value + "']");
    }
    
    private By MultiRolePermission_Cbx(String roleName, String value) {
    	return By.xpath("//label[contains(text(),'" + roleName + "')]/../input[@value='" + value + "']");
    }
    
    
    //PAGE OBJECT METHODS
    public void VerifyRoleColumns() throws Exception {
    	
    	List<WebElement> allColumns = waitFor.ElementsPresent(AllRoleColumns_Ctr);
    	
    	Reporter.log("Verify all roles are present in the 'Role' list.");
    	Assert.assertEquals(allColumns.get(0).getText(), "ANONYMOUS USER");
    	Assert.assertEquals(allColumns.get(1).getText(), "AUTHENTICATED USER");
    	Assert.assertEquals(allColumns.get(2).getText(), "ADMINISTRATOR");
    	Assert.assertEquals(allColumns.get(3).getText(), "EDITOR");
    	Assert.assertEquals(allColumns.get(4).getText(), "SENIOR EDITOR");
    	
    }
    
    public void EnablePermissions(String roleName, List<String> permissionValues) throws Exception {
    	
    	//TODO - refactor and clean this up
    	for (String value : permissionValues) {
    		if (roleName == null) {
    			WebElement ele = waitFor.ElementVisible(SingleRolePermission_Cbx(value));
    			if (ele.isSelected() == false) {
        			Reporter.log("Check the '" + value + "' checkbox.");
        			interact.ScrollToTop();
        			interact.Click(ele);
        			
        		}
    		}
    		else {
    			WebElement ele = waitFor.ElementVisible(MultiRolePermission_Cbx(roleName, value));
    			if (ele.isSelected() == false) {
        			Reporter.log("Check the '" + value + "' checkbox for the '" + roleName + "' role.");
        			interact.ScrollToTop();
        			interact.Click(ele);
        			
        		}
    		}
    	}
    	
    }
    
    public void DisablePermissions(String roleName, List<String> permissionValues) throws Exception {
    	
    	for (String value : permissionValues) {
    		if (roleName == null) {
    			WebElement ele = waitFor.ElementVisible(SingleRolePermission_Cbx(value));
    			if (ele.isSelected() == true) {
        			Reporter.log("Uncheck the '" + value + "' checkbox.");
        			interact.Click(ele);
        		}
    		}
    		else {
    			WebElement ele = waitFor.ElementVisible(MultiRolePermission_Cbx(roleName, value));
    			if (ele.isSelected() == true) {
        			Reporter.log("Uncheck the '" + value + "' checkbox for the '" + roleName + "' role.");
        			interact.Click(ele);
        		}
    		}
    	}
    	
    }
    
    public void VerifyPermissionsSelected(String roleName, List<String> permissionValues) throws Exception {
    	
    	for (String value : permissionValues) {
    		if (roleName == null) {
    			Reporter.log("Verify the '" + value + "' checkbox is checked.");
        		Assert.assertTrue(waitFor.ElementVisible(SingleRolePermission_Cbx(value)).isSelected());
    		}
    		else {
    			Reporter.log("Verify the '" + value + "' checkbox for the '" + roleName + "' is checked.");
        		Assert.assertTrue(waitFor.ElementVisible(MultiRolePermission_Cbx(roleName, value)).isSelected());
    		}
    	}
    	
    }
    
    public void VerifyPermissionsNotSelected(String roleName, List<String> permissionValues) throws Exception {
    	
    	for (String value : permissionValues) {
    		if (roleName == null) {
    			Reporter.log("Verify the '" + value + "' checkbox is not checked.");
        		Assert.assertFalse(waitFor.ElementVisible(SingleRolePermission_Cbx(value)).isSelected());
    		}
    		else {
    			Reporter.log("Verify the '" + value + "' checkbox for the '" + roleName + "' is not checked.");
        		Assert.assertFalse(waitFor.ElementVisible(MultiRolePermission_Cbx(roleName, value)).isSelected());
    		}
    	}
    	
    }
    
    public void ClickSaveConfigurationsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save Permissions' button.");
    	interact.Click(waitFor.ElementVisible(SavePermissions_Btn));
    	
    }
    
}

