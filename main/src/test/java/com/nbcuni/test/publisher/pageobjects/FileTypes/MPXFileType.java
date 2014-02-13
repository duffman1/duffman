package com.nbcuni.test.publisher.pageobjects.FileTypes;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com MPX Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 7, 2014
 *********************************************/

public class MPXFileType {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public MPXFileType(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//select[contains(@id, 'url-alias-field')]")
    private static WebElement URLAliasField_Ddl;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'enable-default-field-overrides')]")
    private static WebElement EnableMPXValueOverrides_Cbx;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private static WebElement Save_Btn;
    
    
    //PAGE OBJECT METHODS
    public void SelectURLAliasField(String fieldName) throws Exception {
    	
    	Reporter.log("Select '" + fieldName + "' from the 'URL Alias Field' drop down list.");
    	new Select(URLAliasField_Ddl).selectByVisibleText(fieldName);
    }
    
    public boolean EnableMPXValueOverrides() throws Exception {
    	
    	boolean isEnabled = false;
    	
    	if (EnableMPXValueOverrides_Cbx.isSelected() == true) {
    		
    		isEnabled = true;
    	}
    	else {
    		
    		Reporter.log("Click the 'Enable MPX Value Overrides' check box.");
    		EnableMPXValueOverrides_Cbx.click();
    	}
    	
    	return isEnabled;
    }
    
    public void ClickSaveBtn () throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    
  
}

