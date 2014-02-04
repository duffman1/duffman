package com.nbcuni.test.publisher.pageobjects.FileTypes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Reporter;

import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com UserLogin Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class FileTypes {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public FileTypes(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
    }
    
    //PAGE OBJECT IDENTIFIERS
    private static WebElement ManageFields_Lnk(String fieldName) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + fieldName + "')]/..//a[text()='manage fields']"));
    }
    
    private static WebElement EditFileType_Lnk(String fieldName) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + fieldName + "')]/..//a[text()='edit file type']"));
    }
    
    //PAGE OBJECT METHODS
    public void ClickManageFieldsLnk(String fieldName) throws Exception {
    	
    	Reporter.log("Click the '" + fieldName + " manage fields' link.");
    	ManageFields_Lnk(fieldName).click();
    }
    
    public void ClickEditFileTypeLnk(String fieldName) throws Exception {
    	
    	Reporter.log("Click the '" + fieldName + " edit file type' link.");
    	EditFileType_Lnk(fieldName).click();
    }
    
    
}

