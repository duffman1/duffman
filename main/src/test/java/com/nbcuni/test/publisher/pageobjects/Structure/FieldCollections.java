package com.nbcuni.test.publisher.pageobjects.Structure;

import com.nbcuni.test.publisher.common.AppLib;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Field Collections Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: April 13, 2014
 *********************************************/

public class FieldCollections {

    private WebDriver webWebWebDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public FieldCollections(WebDriver webWebWebDriver, AppLib applib) {
        this.webWebWebDriver = webWebWebDriver;
        PageFactory.initElements(webWebWebDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private WebElement ManageFields_Lnk(String fieldName) {
    	return webWebWebDriver.findElement(By.xpath("//td[contains(text(), '" + fieldName + "')]/..//a[text()='manage fields']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickManageFieldsLnk(String fieldName) throws Exception {
    	
    	Reporter.log("Click the 'manage fields' link for the field name '" + fieldName + "'.");
    	ManageFields_Lnk(fieldName).click();
    }
    
}

