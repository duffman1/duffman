package com.nbcuni.test.publisher.pageobjects.People;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;


/*********************************************
 * publisher.nbcuni.com Permissions Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Permissions {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String RoleColumns_Ctr = "(//th[text()='Permission'])[2]/../th[@class='checkbox']";
    private static String Post_CreateNewContent_Cbx = "//input[contains(@id, 'create-post-content')]";
    private static String Post_EditOwnContent_Cbx = "//input[@id='edit-4-edit-own-post-content']";
    private static String Post_DeleteOwnContent_Cbx = "//input[@id='edit-4-delete-own-post-content']";
    private static String AddAndUploadNewFiles_Cbx = "//input[@id='edit-4-create-files']";
    private static String SavePermissions_Btn = "//input[@value='Save permissions']";
    
    
    public Permissions(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void VerifyRoleColumns() throws Exception {
    	
    	List<WebElement> allColumns = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(RoleColumns_Ctr)));
    	
    	Assert.assertEquals(allColumns.get(0).getText(), "ANONYMOUS USER");
    	Assert.assertEquals(allColumns.get(1).getText(), "AUTHENTICATED USER");
    	Assert.assertEquals(allColumns.get(2).getText(), "ADMINISTRATOR");
    	Assert.assertEquals(allColumns.get(3).getText(), "EDITOR");
    	Assert.assertEquals(allColumns.get(4).getText(), "SENIOR EDITOR");
    }

    public void CheckAddAndUploadNewFilesCbx() throws Exception {

        WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                visibilityOf(webDriver.findElement(By.xpath(AddAndUploadNewFiles_Cbx))));

        if (el.isSelected() == false) {
        	webDriver.executeScript("window.scrollBy(0,-100);");
            el.click();
        }
    }

    public void CheckPostCreateNewContentCbx() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(Post_CreateNewContent_Cbx))));
    	
    	if (el.isSelected() == false) {
    		el.click();
    	}
    }
    
    public void CheckPostEditOwnContentCbx() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Post_EditOwnContent_Cbx)));
    	
    	if (el.isSelected() == false) {
    		el.click();
    	}
    }
    
    public void CheckPostDeleteOwnContentCbx() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Post_DeleteOwnContent_Cbx)));
    	
    	if (el.isSelected() == false) {
    		el.click();
    	}
    }
    
    public void ClickSaveConfigurationsBtn() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(SavePermissions_Btn)));
    	el.click();
    }
    
    
    
  
}

