package com.nbcuni.test.publisher.pageobjects.FileTypes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com UserLogin Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class FileTypes {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public FileTypes(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private WebElement ManageFields_Lnk(String fieldName) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + fieldName + "')]/..//a[text()='manage fields']"));
    }
    
    private WebElement EditFileType_Lnk(String fieldName) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + fieldName + "')]/..//a[text()='edit file type']"));
    }
    
    private WebElement ManageFileDisplay_Lnk(String fieldName) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + fieldName + "')]/..//a[text()='manage file display']"));
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
    
    public void ClickManageFileDisplayLnk(String fieldName) throws Exception {
    	
    	Reporter.log("Click the '" + fieldName + " manage file display' link.");
    	ManageFileDisplay_Lnk(fieldName).click();
    }
    
    
}

