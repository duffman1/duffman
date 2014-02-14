package com.nbcuni.test.publisher.pageobjects;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;
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
    private static ContentParent contentParent;
    private static Overlay overlay;
    private static Taxonomy taxonomy;
    
    //PAGE OBJECT CONSTRUCTOR
    public Modules(CustomWebDriver webDriver, AppLib applib) {
    	Modules.webDriver = webDriver;
    	Modules.applib = applib;
    	contentParent = new ContentParent(webDriver, applib);
    	PageFactory.initElements(webDriver, contentParent);
    	overlay = new Overlay(webDriver, applib);
    	PageFactory.initElements(webDriver, applib);
    	taxonomy = new Taxonomy(webDriver);
    	PageFactory.initElements(webDriver, taxonomy);
    	PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[@id='edit-module-filter-name']")
    private static WebElement FilterList_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Save configuration']")
    private static WebElement SaveConfiguration_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Continue']")
    private static WebElement Continue_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Uninstall']")
    private static WebElement Uninstall_Btn;
    
    private static WebElement ModuleName_Cbx(String moduleName) {
    	return webDriver.findElement(By.xpath("//label/strong[text()='" + moduleName + "']/../../../td[@class='checkbox']//input"));
    }
    
    private static WebElement UninstallModuleName_Cbx(String moduleName) {
    	return webDriver.findElement(By.xpath("//label[text()='" + moduleName + "']/../../..//input"));
    }
    
    private static WebElement Configure_Lnk(String moduleName) {
    	return webDriver.findElement(By.xpath("//label/strong[text()='" + moduleName + "']/../../../td//a[text()='Configure']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void EnterFilterName(String filterName) throws Exception {
    	
    	Reporter.log("Enter '" + filterName + "' in the 'Filter Name' text box.");
    	FilterList_Txb.clear();
    	FilterList_Txb.sendKeys(filterName);
    	
    	Reporter.log("Wait for the module titled '" + filterName + "' to appear in the list.");
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(ModuleName_Cbx(filterName)));
    	
    }
    
    public void VerifyConfigurationSaved() throws Exception{
    	
    	contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	
    }
    
    public boolean IsModuleEnabled(String moduleName) {
    	
    	boolean isModuleEnabled = false;
    	
    	if (ModuleName_Cbx(moduleName).isSelected() == true) {
    		
    		isModuleEnabled = true;
    	}
    	
    	return isModuleEnabled;
    }
    
    public void EnableModule(String moduleName) throws Exception {
    	
    	if (ModuleName_Cbx(moduleName).isSelected() == false) {
    		
    		Reporter.log("Check the '" + moduleName + "' check box.");
    		ModuleName_Cbx(moduleName).click();
    		
    		Reporter.log("Click the 'Save configuration' button.");
    		try {
    			SaveConfiguration_Btn.click();
    		}
    		catch (WebDriverException e) {
    			webDriver.executeScript("arguments[0].click();", SaveConfiguration_Btn);
    		}
    		
    		webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        	
        	boolean additionalModulesRequired = false;
        	
        	overlay.switchToDefaultContent();
        	
        	try {
        		overlay.SwitchToFrame("Some required modules must be enabled");
        		additionalModulesRequired = true;
        	}
        	catch (Exception e) { }
        	
        	if (additionalModulesRequired == true) {
        		
        		Reporter.log("Click the 'Continue' button to enable additional modules.");
        		Continue_Btn.click();
        		overlay.switchToDefaultContent();
        		
        	}
        	
        	Reporter.log("Switch back to the 'Modules' frame.");
        	overlay.SwitchToFrame("Modules");
        	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
        	
    		this.VerifyConfigurationSaved();
    	}
    	
    }
    
    public boolean DisableModule(String moduleName) throws Exception {
    	
    	boolean moduleAlreadyDisabled = true;
    	
    	if (ModuleName_Cbx(moduleName).isSelected() == true) {
    		Reporter.log("Uncheck the '" + moduleName + "' module checkbox.");
    		ModuleName_Cbx(moduleName).click();
    		
    		Reporter.log("Click the 'Save configuration' button.");
    		try {
    			SaveConfiguration_Btn.click();
    		}
    		catch (WebDriverException e) {
    			webDriver.executeScript("arguments[0].click();", SaveConfiguration_Btn);
    		}
    		this.VerifyConfigurationSaved();
    		moduleAlreadyDisabled = false;
    	}
    	
    	return moduleAlreadyDisabled;
    	
    	
    }
    
    public void UninstallModule(String moduleName) throws Exception {
    	
    	Reporter.log("Click the '" + moduleName + "' uninstall checkbox.");
    	UninstallModuleName_Cbx(moduleName).click();
    	
    	Reporter.log("Click the 'Uninstall' button.");
    	Uninstall_Btn.click();
    	overlay.SwitchToActiveFrame();
    	contentParent.VerifyPageContentPresent(Arrays.asList("The following modules will be completely uninstalled from your site, and all data from these modules will be lost!", 
    			moduleName));
    	
    	Reporter.log("Click the 'Uninstall' button to confirm.");
    	Uninstall_Btn.click();
    	
    }
    
    public void VerifyModuleSourceInPage(String scriptSrc) throws Exception {
    	
    	Assert.assertTrue(webDriver.getPageSource().contains(scriptSrc));
    	    
    }
    
    public void VerifyModuleSourceNotInPage(String scriptSrc) throws Exception {
    	
    	Assert.assertFalse(webDriver.getPageSource().contains(scriptSrc), 
    			"Module source is present when it should not be!");
    	
    }
    
    public void VerifyModuleEnabled(String moduleName) throws Exception {
    	
    	taxonomy.NavigateSite("Modules");
    	overlay.SwitchToFrame("Modules");
    	this.EnterFilterName(moduleName);
    	this.EnableModule(moduleName);
    	overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent();
    }
    
    public void ClickConfigureLnk(String moduleName) throws Exception {
    	
    	Reporter.log("Click the '" + moduleName + "' module 'Configure' link.");
    	Configure_Lnk(moduleName).click();
    }
    
    
  
  
}

