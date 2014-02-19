package com.nbcuni.test.publisher.pageobjects.People;

import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
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

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public Permissions(CustomWebDriver webDriver) {
        Permissions.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private static List<WebElement> RoleColumns_Ctr() {
    	return webDriver.findElements(By.xpath("(//th[text()='Permission'])[2]/../th[@class='checkbox']"));
    }
    
    @FindBy(how = How.CSS, using = "input[id*='create-post-content']")
    private static WebElement Post_CreateNewContent_Cbx;
    
    @FindBy(how = How.ID, using = "edit-4-edit-own-post-content")
    private static WebElement Post_EditOwnContent_Cbx;
    
    @FindBy(how = How.ID, using = "edit-4-delete-own-post-content")
    private static WebElement Post_DeleteOwnContent_Cbx;
    
    @FindBy(how = How.ID, using = "edit-4-create-files")
    private static WebElement AddAndUploadNewFiles_Cbx;
    
    @FindBy(how = How.CSS, using = "input[value='Save permissions']")
    private static WebElement SavePermissions_Btn;
    
    
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

    public void CheckAddAndUploadNewFilesCbx() throws Exception {

        if (AddAndUploadNewFiles_Cbx.isSelected() == false) {
        	Reporter.log("Scroll up and check the 'Add and Upload New Files' check box.");
        	webDriver.executeScript("window.scrollBy(0,-50000);");
        	AddAndUploadNewFiles_Cbx.click();
        }
    }

    public void CheckPostCreateNewContentCbx() throws Exception {
    	
    	if (Post_CreateNewContent_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Post Create New Content' check box.");
    		Post_CreateNewContent_Cbx.click();
    	}
    }
    
    public void CheckPostEditOwnContentCbx() throws Exception {
    	
    	if (Post_EditOwnContent_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Post Edit Own Content' check box.");
    		Post_EditOwnContent_Cbx.click();
    	}
    }
    
    public void CheckPostDeleteOwnContentCbx() throws Exception {
    	
    	if (Post_DeleteOwnContent_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Delete Own Content' check box.");
    		Post_DeleteOwnContent_Cbx.click();
    	}
    }
    
    public void ClickSaveConfigurationsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save Permissions' button.");
    	SavePermissions_Btn.click();
    }
    
}

