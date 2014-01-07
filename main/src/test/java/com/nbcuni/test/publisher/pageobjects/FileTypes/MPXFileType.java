package com.nbcuni.test.publisher.pageobjects.FileTypes;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    private static AppLib al;
    private final Util ul;
    
    private static String EnableMPXValueOverrides_Cbx = "//input[@id='edit-pub-mpx-mpx-video-1-enable-default-field-overrides']";
    private static String Save_Btn = "//input[@id='edit-submit']";
    
    public MPXFileType(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public boolean EnableMPXValueOverrides() throws Exception {
    	
    	boolean isEnabled = false;
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(EnableMPXValueOverrides_Cbx))));
    	
    	if (el.isSelected() == true) {
    		
    		isEnabled = true;
    	}
    	else {
    		el.click();
    	}
    	
    	return isEnabled;
    }
    
    public void ClickSaveBtn () throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Save_Btn)))).click();
    }
    
    
  
}

