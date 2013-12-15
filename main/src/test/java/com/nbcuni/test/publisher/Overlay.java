package com.nbcuni.test.publisher;


import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Overlay Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Overlay {

    private static CustomWebDriver webDriver;
    
    
    private static String Page_Title = "Site-Install";
    private static String CloseOverlay_Lnk = "//a[@id='overlay-close']";
    
    
    

    public Overlay(final CustomWebDriver custWebDr) {
    	webDriver = custWebDr;
    }
    
    
    public void ClickCloseOverlayLnk() throws Exception {
    	
    	webDriver.click(CloseOverlay_Lnk);
    	
    }
    
   
}

