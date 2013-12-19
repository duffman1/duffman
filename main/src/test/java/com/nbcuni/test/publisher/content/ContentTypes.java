package com.nbcuni.test.publisher.content;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com ContentTypes Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class ContentTypes {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Name_Txb = "//input[@id='edit-name']";
    private static String Save_Btn = "//input[@id='edit-submit']";
    private static String MessageStatus_Ctr = "//div[@class='messages status']";
    private static String ContentTypeTbl_Ctr = "//div[@class='content']//tbody";
    private static String AddNewField_Txb = "//input[@id='edit-fields-add-new-field-label']";
    private static String FieldType_Ddl = "//select[@id='edit-fields-add-new-field-type']";
    
    
    
    public ContentTypes(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void EnterName(String name) throws Exception {
    	
    	webDriver.type(Name_Txb, name);
    	
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	webDriver.click(Save_Btn);
    	
    }
    
    public void VerifyContentTypeSaved(String name) throws Exception {
    	
    	ul.verifyObjectContainsText(MessageStatus_Ctr, "The content type " + name + " has been added.");
    	ul.verifyObjectContainsText(ContentTypeTbl_Ctr, name);
    	
    }
    
    public void SwitchToNewContentTypeFrm(String name) throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath("//iframe[contains(@title, '" + name + "')]"));
    	webDriver.switchTo().frame(frm);
    	
    }
    
    public void SwitchToCreateContentFrm(String name) throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath("//iframe[contains(@title, 'Create " + name + " dialog')]"));
    	webDriver.switchTo().frame(frm);
    	
    }
    
    public void AddNewField(String fieldName) throws Exception {
    	
    	webDriver.type(AddNewField_Txb, fieldName);
    	
    }
    
    public void SelectFieldType(String fieldType) throws Exception {
    	
    	webDriver.selectFromDropdown(FieldType_Ddl, fieldType);
    	
    }
    
    public void VerifyNewFieldSaved(String fieldName) throws Exception {
    	
    	ul.verifyObjectContainsText(MessageStatus_Ctr, "Updated field " + fieldName + " field settings.");
    	
    }
    
    public void VerifyConfigurationSaved(String fieldName) throws Exception {
    	
    	ul.verifyObjectContainsText(MessageStatus_Ctr, "Saved " + fieldName + " configuration.");
    	
    }
    
    public void VerifyFieldSaveBtnPresent(String fieldName) throws Exception {
    	
    	ul.verifyObjectExists("//a[@id='edit-field-" + fieldName + "-und-field-" + fieldName + "-und-0-select']");
    	
    }
    
    
    
    
  
}

