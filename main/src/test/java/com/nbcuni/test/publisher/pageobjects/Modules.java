package com.nbcuni.test.publisher.pageobjects;


import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
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
    private static AppLib applib;
    
    
    public Modules(CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
    }
    
    private static String FilterList_Txb = "//input[@id='edit-module-filter-name']";
    private static String SaveConfiguration_Btn = "//input[@id='edit-submit']";
    private static String Message_Ctr = "//div[@class='messages status']";
    private static String Continue_Btn = "//input[@value='Continue']";
    private static String Uninstall_Btn = "//input[@value='Uninstall']";
    
    public void EnterFilterName(String filterName) throws Exception {
    	
    	webDriver.type(FilterList_Txb, filterName);
    	
    	//TODO - replace this sleep with a fluent wait
    	Thread.sleep(2000);
    }
    
    public void VerifyConfigurationSaved() throws Exception{
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(Message_Ctr)).getText().contains("The configuration options have been saved."));
    	
    }
    
    public boolean IsModuleEnabled(String moduleName) {
    	
    	boolean isModuleEnabled = false;
    	
    	String moduleLocator = "//label/strong[text()='" + moduleName + "']/../../../td[@class='checkbox']//input";
    	
    	WebElement Cbx = webDriver.findElement(By.xpath(moduleLocator));
    	
    	if (Cbx.isSelected() == true) {
    		
    		isModuleEnabled = true;
    	}
    	
    	return isModuleEnabled;
    }
    
    public void EnableModule(String moduleName) throws Exception {
    	
    	String moduleLocator = "//label/strong[text()='" + moduleName + "']/../../../td[@class='checkbox']//input";
    	
    	WebElement Cbx = webDriver.findElement(By.xpath(moduleLocator));
    	
    	if (Cbx.isSelected() == false) {
    		Cbx.click();
    		webDriver.click(SaveConfiguration_Btn);
    		
    		webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        	
        	boolean additionalModulesRequired = false;
        	
        	Overlay overlay = new Overlay(webDriver);
        	overlay.switchToDefaultContent();
        	
        	try {
        		overlay.SwitchToFrame("Some required modules must be enabled");
        		additionalModulesRequired = true;
        	}
        	catch (Exception e) { }
        	
        	if (additionalModulesRequired == true) {
        		
        		new WebDriverWait(webDriver, 10).until(ExpectedConditions.
        				elementToBeClickable(By.xpath(Continue_Btn))).click();
        		overlay.switchToDefaultContent();
        		
        	}
        	
        	overlay.SwitchToFrame("Modules");
        	webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        	
    		this.VerifyConfigurationSaved();
    	}
    	
    }
    
    public boolean DisableModule(String moduleName) throws Exception {
    	
    	String moduleLocator = "//label/strong[text()='" + moduleName + "']/../../../td[@class='checkbox']//input";
    	
    	WebElement Cbx = webDriver.findElement(By.xpath(moduleLocator));
    	
    	boolean moduleAlreadyDisabled = true;
    	
    	if (Cbx.isSelected() == true) {
    		Cbx.click();
    		webDriver.click(SaveConfiguration_Btn);
    		this.VerifyConfigurationSaved();
    		moduleAlreadyDisabled = false;
    	}
    	
    	return moduleAlreadyDisabled;
    	
    	
    }
    
    public void UninstallModule(String moduleName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			presenceOfElementLocated(By.xpath("//label[text()='" + moduleName + "']/../../..//input"))).click();
    
    	webDriver.findElement(By.xpath(Uninstall_Btn)).click();
    	Overlay overlay = new Overlay(webDriver);
    	overlay.SwitchToActiveFrame();
    	ContentParent contentParent = new ContentParent(webDriver, applib);
    	contentParent.VerifyPageContentPresent(Arrays.asList("The following modules will be completely uninstalled from your site, and all data from these modules will be lost!", 
    			moduleName));
    	
    	webDriver.findElement(By.xpath(Uninstall_Btn)).click();
    	
    }
    
    public void VerifyModuleSourceInPage(String scriptSrc) throws Exception {
    	
    	Assert.assertTrue(webDriver.getPageSource().contains(scriptSrc));
    	    
    }
    
    public void VerifyModuleSourceNotInPage(String scriptSrc) throws Exception {
    	
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

