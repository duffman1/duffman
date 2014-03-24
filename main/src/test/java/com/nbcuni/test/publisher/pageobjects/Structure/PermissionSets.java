package com.nbcuni.test.publisher.pageobjects.Structure;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
* publisher.nbcuni.com Permission Sets Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 25, 2014
*********************************************/

public class PermissionSets {

	private static CustomWebDriver webDriver;
	private static AppLib applib;
	private static WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public PermissionSets(CustomWebDriver webDriver, AppLib applib) {
    	PermissionSets.webDriver = webDriver;
    	PermissionSets.applib = applib;
    	PageFactory.initElements(webDriver, this);
    	wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.LINK_TEXT, using = "Add")
    private static WebElement Add_Lnk;
    
    private static WebElement PermissionSetEdit_Lnk(String setName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + setName + "']/..//a[text()='Edit']"));
    }
    
    private static WebElement PermissionSetName_Txt(String setName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + setName + "']"));
    }
    
    private static WebElement PermissionSetExpandEdit_Lnk(String setName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + setName + "']/..//a[text()='open']"));
    }
    
    private static WebElement PermissionSetDelete_Lnk(String setName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + setName + "']/..//a[text()='Delete']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddLnk() throws Exception {
    
    	Reporter.log("Click the 'Add' link.");
    	Add_Lnk.click();
    }
    
    public void ClickPermissionSetEditLnk(String setName) throws Exception {
        
    	Reporter.log("Click the Permission Set 'Edit' link for set name + '" + setName + "'.");
    	PermissionSetEdit_Lnk(setName).click();
    }
    
    public void ClickPermissionSetExpandEditLnk(String setName) throws Exception {
        
    	Reporter.log("Click the Permission Set expand link for Set Name + '" + setName + "'.");
    	PermissionSetExpandEdit_Lnk(setName).click();
    }
    
    public void ClickPermissionSetDeleteLnk(String setName) throws Exception {
        
    	Reporter.log("Click the Permission Set 'Delete' link for Set Name + '" + setName + "'.");
    	wait.until(ExpectedConditions.visibilityOf(PermissionSetDelete_Lnk(setName)));
    	Thread.sleep(500); //pause required here
    	PermissionSetDelete_Lnk(setName).click();
    }
    
    public void VerifyPermissionSetNotPresent(String setName) throws Exception {
        
    	Reporter.log("Verify Permission Set '" + setName + "' is NOT present.");
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	boolean elPresent = true;
    	try {
    		
    		PermissionSetName_Txt(setName).isDisplayed();
    		elPresent = true;
    	}
    	catch (Exception e) {
    	
    		elPresent = false;
    	}
    	if (elPresent == true) {
    		Assert.fail("Permission Set '" + setName + "' is present when it should not be");
    	}
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
}
