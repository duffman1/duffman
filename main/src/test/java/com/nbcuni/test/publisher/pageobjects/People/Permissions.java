package com.nbcuni.test.publisher.pageobjects.People;

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
 * publisher.nbcuni.com Permissions Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Permissions {

    private Driver webDriver;
    private ContentParent contentParent;
    
    //PAGE OBJECT CONSTRUCTOR
    public Permissions(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        contentParent = new ContentParent(webDriver, applib);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private List<WebElement> RoleColumns_Ctr() {
    	return webDriver.findElements(By.xpath("(//th[text()='Permission'])[2]/../th[@class='checkbox']"));
    }
    
    @FindBy(how = How.CSS, using = "input[id*='create-post-content']")
    private WebElement Post_CreateNewContent_Cbx;
    
    @FindBy(how = How.ID, using = "edit-4-edit-own-post-content")
    private WebElement Post_EditOwnContent_Cbx;
    
    @FindBy(how = How.ID, using = "edit-4-delete-own-post-content")
    private WebElement Post_DeleteOwnContent_Cbx;
    
    @FindBy(how = How.ID, using = "edit-4-create-files")
    private WebElement AddAndUploadNewFiles_Cbx;
    
    @FindBy(how = How.CSS, using = "input[value='Save permissions']")
    private WebElement SavePermissions_Btn;
    
    private WebElement SingleRolePermission_Cbx(String value) {
    	return webDriver.findElement(By.cssSelector("input[value='" + value + "']"));
    }
    
    private WebElement MultiRolePermission_Cbx(String roleName, String value) {
    	return webDriver.findElement(By.xpath("//label[contains(text(),'" + roleName + "')]/../input[@value='" + value + "']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void VerifyRoleColumns() throws Exception {
    	
    	List<WebElement> allColumns = RoleColumns_Ctr();
    	
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
    			if (SingleRolePermission_Cbx(value).isSelected() == false) {
        			Reporter.log("Check the '" + value + "' checkbox.");
        			try {
        				SingleRolePermission_Cbx(value).click();
        			}
        			catch (WebDriverException e) {
        				contentParent.Scroll("-500");
        				SingleRolePermission_Cbx(value).click();
        			}
        		}
    		}
    		else {
    			if (MultiRolePermission_Cbx(roleName, value).isSelected() == false) {
        			Reporter.log("Check the '" + value + "' checkbox for the '" + roleName + "' role.");
        			try {
        				MultiRolePermission_Cbx(roleName, value).click();
        			}
        			catch (WebDriverException e) {
        				contentParent.Scroll("-500");
        				MultiRolePermission_Cbx(roleName, value).click();
        			}
        		}
    		}
    	}
    }
    
    public void DisablePermissions(String roleName, List<String> permissionValues) throws Exception {
    	
    	for (String value : permissionValues) {
    		if (roleName == null) {
    			if (SingleRolePermission_Cbx(value).isSelected() == true) {
        			Reporter.log("Uncheck the '" + value + "' checkbox.");
        			SingleRolePermission_Cbx(value).click();
        		}
    		}
    		else {
    			if (MultiRolePermission_Cbx(roleName, value).isSelected() == true) {
        			Reporter.log("Uncheck the '" + value + "' checkbox for the '" + roleName + "' role.");
        			MultiRolePermission_Cbx(roleName, value).click();
        		}
    		}
    	}
    }
    
    public void VerifyPermissionsSelected(String roleName, List<String> permissionValues) throws Exception {
    	
    	for (String value : permissionValues) {
    		if (roleName == null) {
    			Reporter.log("Verify the '" + value + "' checkbox is checked.");
        		Assert.assertTrue(SingleRolePermission_Cbx(value).isSelected());
    		}
    		else {
    			Reporter.log("Verify the '" + value + "' checkbox for the '" + roleName + "' is checked.");
        		Assert.assertTrue(MultiRolePermission_Cbx(roleName, value).isSelected());
    		}
    	}
    }
    
    public void VerifyPermissionsNotSelected(String roleName, List<String> permissionValues) throws Exception {
    	
    	for (String value : permissionValues) {
    		if (roleName == null) {
    			Reporter.log("Verify the '" + value + "' checkbox is not checked.");
        		Assert.assertFalse(SingleRolePermission_Cbx(value).isSelected());
    		}
    		else {
    			Reporter.log("Verify the '" + value + "' checkbox for the '" + roleName + "' is not checked.");
        		Assert.assertFalse(MultiRolePermission_Cbx(roleName, value).isSelected());
    		}
    	}
    }
    
    public void ClickSaveConfigurationsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save Permissions' button.");
    	SavePermissions_Btn.click();
    }
    
}

