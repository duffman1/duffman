package com.nbcuni.test.publisher.pageobjects.People;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Apply Permission Set Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 25, 2014
 *********************************************/

public class ApplyPermissionSet {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public ApplyPermissionSet(CustomWebDriver webDriver, AppLib applib) {
        ApplyPermissionSet.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private static WebElement Role_Ddl(String role) {
    	return webDriver.findElement(By.xpath("//label[text()='" + role + " ']/../select"));
    }
    
    @FindBy(how = How.XPATH, using = "//a[text()='Permissions']")
    private static WebElement Permissions_Btn;
    
    @FindBy(how = How.ID, using = "edit-apply")
    private static WebElement ApplyPermissionSets_Btn;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private static WebElement DoIt_Btn;
    
    
    //PAGE OBJECT METHODS
    public void SelectRolePermissionSet(String role, String option) throws Exception {
    	
    	Reporter.log("Select '" + option + "' from the '" + role + "' drop down list.");
    	new Select(Role_Ddl(role)).selectByVisibleText(option);
    }
    
    public void ClickPermissionsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Permissions' button.");
    	Permissions_Btn.click();
    }

    public void ClickApplyPermissionSetsBtn() throws Exception {
    	
    	Reporter.log("Click the 'Apply Permission Set(s)' button.");
    	ApplyPermissionSets_Btn.click();
    }
    
    public void ClickDoItBtn() throws Exception {
    	
    	Reporter.log("Click the 'Do it!' button.");
    	DoIt_Btn.click();
    }
    
}

