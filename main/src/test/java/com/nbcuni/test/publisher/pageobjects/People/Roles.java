package com.nbcuni.test.publisher.pageobjects.People;


import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

/*********************************************
 * publisher.nbcuni.com Roles Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Roles {

    private WebDriver webWebWebDriver;
    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public Roles(WebDriver webWebWebDriver) {
        this.webWebWebDriver = webWebWebDriver;
        PageFactory.initElements(webWebWebDriver, this);
        waitFor = new WaitFor(webWebWebDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//li/a[text()='Roles']")
    private WebElement Roles_Btn;
    
    private List<WebElement> RoleRows_Ctr() {
    	return webWebWebDriver.findElements(By.xpath("//table[@id='user-roles']//a[@class='tabledrag-handle']/.."));
    }
    
    @FindBy(how = How.XPATH, using = "//td[text()='editor']/..//a[text()='edit permissions']")
    private WebElement Editor_EditPermissions_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void ClickRolesBtn() throws Exception {
    	
    	Reporter.log("Click the 'Roles' button.");
    	Roles_Btn.click();
    }
    
    public void VerifyRoleRows() throws Exception {
    	
    	List<WebElement> allColumns = RoleRows_Ctr();
    	
    	Reporter.log("Verify all the required role permissions are present in the 'Role' list.");
    	waitFor.ElementContainsText(allColumns.get(0), "anonymous user");
    	Assert.assertTrue(allColumns.get(1).getText().contains("authenticated user"));
    	Assert.assertTrue(allColumns.get(2).getText().contains("administrator"));
    	Assert.assertTrue(allColumns.get(3).getText().contains("editor"));
    	Assert.assertTrue(allColumns.get(4).getText().contains("senior editor"));
    }
    
    public void ClickEditorEditPermissionsLnk() throws Exception {
    	
    	Reporter.log("Click the 'Edit Permissions' link for the 'Editor' role.");
    	Editor_EditPermissions_Lnk.click();
    	
    }
    
}

