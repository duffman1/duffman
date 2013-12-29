package com.nbcuni.test.publisher.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Modules Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class Modules {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String FilterList_Txb = "//input[@id='edit-module-filter-name']";
    private static String SaveConfiguration_Btn = "//input[@id='edit-submit']";
    private static String Message_Ctr = "//div[@class='messages status']";
    
    
    
    public Modules(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void EnterFilterName(String filterName) throws Exception {
    	
    	webDriver.type(FilterList_Txb, filterName);
    	
    	//TODO - replace this sleep with a fluent wait
    	Thread.sleep(2000);
    }
    
    public void VerifyConfigurationSaved() throws Exception{
    	
    	ul.verifyObjectContainsText(Message_Ctr, "The configuration options have been saved.");
    }
    
    public void EnableModule(String moduleName) throws Exception {
    	
    	String moduleLocator = "//label/strong[text()='" + moduleName + "']/../../../td[@class='checkbox']//input";
    	
    	WebElement Cbx = webDriver.findElement(By.xpath(moduleLocator));
    	
    	if (Cbx.isSelected() == false) {
    		Cbx.click();
    		webDriver.click(SaveConfiguration_Btn);
    		this.VerifyConfigurationSaved();
    	}
    	
    	
    }
    
    public void DisableModule(String moduleName) throws Exception {
    	
    	String moduleLocator = "//label/strong[text()='" + moduleName + "']/../../../td[@class='checkbox']//input";
    	
    	WebElement Cbx = webDriver.findElement(By.xpath(moduleLocator));
    	
    	if (Cbx.isSelected() == true) {
    		Cbx.click();
    		webDriver.click(SaveConfiguration_Btn);
    		this.VerifyConfigurationSaved();
    	}
    	
    	
    }
    
    public void VerifyModuleSourceInPage(String scriptSrc) {
    	
    	String pageSource = webDriver.getPageSource();
    	ul.verifyTextContainsExpectedText(pageSource, scriptSrc);
    	
    }
    
    public void VerifyModuleSourceNotInPage(String scriptSrc) {
    	
    	//TODO - add a util method for this to lib
    	Assert.assertFalse(webDriver.getPageSource().contains(scriptSrc), 
    			"Module source is present when it should not be!");
    	
    	
    }
    
    public void VerifyModuleEnabled(String moduleName) throws Exception {
    	
    	Taxonomy taxonomy = new Taxonomy(webDriver);
    	Overlay overlay = new Overlay(webDriver);
    	taxonomy.NavigateSite("Modules");
    	overlay.SwitchToFrame("Modules");
    	this.EnterFilterName(moduleName);
    	this.EnableModule(moduleName);
    	overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent();
    }
    
    
    
  
}

