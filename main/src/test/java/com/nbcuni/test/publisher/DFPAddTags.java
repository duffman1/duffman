package com.nbcuni.test.publisher;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com DFP Add Tags Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class DFPAddTags {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String NetworkId_Txb = "//input[@id='edit-dfp-network-id']";
    private static String SaveConfiguration_Btn = "//input[@id='edit-submit']";
    private static String Save_Btn = "//input[@id='edit-submit']";
    private static String Message_Ctr = "//div[@class='messages status']";
    private static String AdSlotName_Txb = "//input[@id='edit-slot']";
    private static String Sizes_Txb = "//input[@id='edit-size']";
    private static String AdUnitPattern_Txb = "//input[@id='edit-adunit']";
    
    public DFPAddTags(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void EnterNetworkId(String networkIdName) throws Exception {
    	
    	webDriver.type(NetworkId_Txb, networkIdName);
    }
    
    public void ClickSaveConfigurationBtn() {
    	
    	webDriver.click(SaveConfiguration_Btn);
    }
    
    public void ClickSaveBtn() {
    	
    	webDriver.click(Save_Btn);
    }
    
    public void VerifyConfigurationSaved() {
    	
    	ul.verifyObjectContainsText(Message_Ctr, "The configuration options have been saved.");
    }
    
    public void VerifyAdTagCreated(String adTagName) {
    	
    	ul.verifyObjectContainsText(Message_Ctr, adTagName + " has been created.");
    }
    
    public void EnterAdSlotName(String adSlotName){
    	
    	webDriver.type(AdSlotName_Txb, adSlotName);
    }
    
    public void EnterAdSizes(String adSize){
    	
    	webDriver.type(Sizes_Txb, adSize);
    }
    
    public void EnterAdUnitPatter(String adPattern){
    	
    	webDriver.type(AdUnitPattern_Txb, adPattern);
    }
    
    
}

