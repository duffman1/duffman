package com.nbcuni.test.publisher.pageobjects.People;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Roles Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Roles {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Roles_Btn = "//li/a[text()='Roles']";
    private static String RoleRows_Ctr = "//table[@id='user-roles']//a[@class='tabledrag-handle']/..";
    private static String Editor_EditPermissions_Lnk = "//td[text()='editor']/..//a[text()='edit permissions']";
    
    public Roles(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void ClickRolesBtn() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Roles_Btn)));
    	el.click();
    }
    
    public void VerifyRoleRows() throws Exception {
    	
    	List<WebElement> allColumns = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(RoleRows_Ctr)));
    	
    	Assert.assertTrue(allColumns.get(0).getText().contains("anonymous user"));
    	Assert.assertTrue(allColumns.get(1).getText().contains("authenticated user"));
    	Assert.assertTrue(allColumns.get(2).getText().contains("administrator"));
    	Assert.assertTrue(allColumns.get(3).getText().contains("editor"));
    	Assert.assertTrue(allColumns.get(4).getText().contains("senior editor"));
    }
    
    public void ClickEditorEditPermissionsLnk() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Editor_EditPermissions_Lnk)));
    	el.click();
    }
    
    
    
  
}

