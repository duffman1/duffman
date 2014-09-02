package com.nbcuni.test.publisher.pageobjects;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Modules Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class Modules {

    private Driver webDriver;
    private AppLib applib;
    private ContentParent contentParent;
    private Overlay overlay;
    private Taxonomy taxonomy;
    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public Modules(Driver webDriver, AppLib applib) {
    	this.webDriver = webDriver;
    	this.applib = applib;
    	contentParent = new ContentParent(webDriver, applib);
    	PageFactory.initElements(webDriver, contentParent);
    	overlay = new Overlay(webDriver, applib);
    	PageFactory.initElements(webDriver, applib);
    	taxonomy = new Taxonomy(webDriver);
    	PageFactory.initElements(webDriver, taxonomy);
    	PageFactory.initElements(webDriver, this);
    	wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[@id='edit-module-filter-name']")
    private WebElement FilterList_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Save configuration']")
    private WebElement SaveConfiguration_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Continue']")
    private WebElement Continue_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Uninstall']")
    private WebElement Uninstall_Btn;
    
    @FindBy(how = How.XPATH, using = "//label/strong[text()='Acquia agent']/../../..")
    private WebElement AcquiaAgentModule_Row;
    
    private WebElement ModuleName_Row(String moduleName) {
    	return webDriver.findElement(By.xpath("//label/strong[text()='" + moduleName + "']/../../.."));
    }
    
    private WebElement ModuleName_Cbx(String moduleName) {
    	return webDriver.findElement(By.xpath("//label/strong[text()='" + moduleName + "']/../../../td[@class='checkbox']//input"));
    }
    
    private WebElement UninstallModuleName_Cbx(String moduleName) {
    	return webDriver.findElement(By.xpath("//label[text()='" + moduleName + "']/../../..//input"));
    }
    
    private WebElement Configure_Lnk(String moduleName) {
    	return webDriver.findElement(By.xpath("//label/strong[text()='" + moduleName + "']/../../../td//a[text()='Configure']"));
    }
    
    private WebElement Category_Lnk(String categoryName) {
    	return webDriver.findElement(By.xpath("//div[@id='module-filter-tabs']//a[contains(text(), '" + categoryName + "')]"));
    }
    
    
    //PAGE OBJECT METHODS
    public void WaitForFilterVisible(final String filterName) throws Exception {
    	Reporter.log("Wait for module filter titled '" + filterName + "' to be visible in module list.");
    	wait.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return (ModuleName_Row(filterName).getAttribute("style").equals("") || ModuleName_Row(filterName).getAttribute("style").equals("display: table-row;"))
    					&& AcquiaAgentModule_Row.getAttribute("style").equals("display: none;");
   		 	}
    	});
    	Thread.sleep(500);
    }
    
    public void EnterFilterName(String filterName) throws Exception {
    	
    	Reporter.log("Enter '" + filterName + "' in the 'Filter Name' text box.");
    	FilterList_Txb.clear();
    	FilterList_Txb.sendKeys(filterName);
    	this.WaitForFilterVisible(filterName);
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
    		
        	boolean additionalModulesRequired = false;
        	
        	overlay.switchToDefaultContent(true);
        	
        	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        	try {
        		overlay.SwitchToFrame("Some required modules must be enabled");
        		additionalModulesRequired = true;
        	}
        	catch (Exception e) { }
        	
        	if (additionalModulesRequired == true) {
        		
        		Reporter.log("Click the 'Continue' button to enable additional modules.");
        		Continue_Btn.click();
        		overlay.switchToDefaultContent(true);
        		
        	}
        	
        	Reporter.log("Switch back to the 'Modules' frame.");
        	overlay.SwitchToFrame("Modules");
        	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
        	
        	if (moduleName != "Overlay") {
        		this.VerifyConfigurationSaved();
        	}
        	
    	}
    	else {
    		Reporter.log("Verify the '" + moduleName + "' check box is checked.");
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
    
    public Boolean IsModuleInstalled(String moduleName) throws Exception {
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	Boolean isModuleInstalled = null;
    	try {
    		UninstallModuleName_Cbx(moduleName).getLocation();
    		isModuleInstalled = true;
    	}
    	catch (NoSuchElementException e) {
    		isModuleInstalled = false;
    	}
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	return isModuleInstalled;
    }
    
    public void UninstallModule(String moduleName) throws Exception {
    	
    	Reporter.log("Click the '" + moduleName + "' uninstall checkbox.");
    	UninstallModuleName_Cbx(moduleName).click();
    	
    	Reporter.log("Click the 'Uninstall' button.");
    	Thread.sleep(1000);
    	Uninstall_Btn.click();
    	overlay.SwitchToActiveFrame();
    	contentParent.VerifyPageContentPresent(Arrays.asList("The following modules will be completely uninstalled from your site, and all data from these modules will be lost!", 
    			moduleName));
    	
    	Reporter.log("Click the 'Uninstall' button to confirm.");
    	Uninstall_Btn.click();
    	
    }
    
    public void VerifyModuleEnabled(String moduleName) throws Exception {
    	
    	taxonomy.NavigateSite("Modules");
    	overlay.SwitchToFrame("Modules");
    	this.EnterFilterName(moduleName);
    	this.EnableModule(moduleName);
    	overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent(true);
        taxonomy.NavigateSite("Home");
    }
    
    public void ClickConfigureLnk(String moduleName) throws Exception {
    	
    	Reporter.log("Click the '" + moduleName + "' module 'Configure' link.");
    	Configure_Lnk(moduleName).click();
    }
    
    public void ClickCategoryLnk(String categoryName) throws Exception {
    	
    	Reporter.log("Click the '" + categoryName + "' module category link.");
    	Category_Lnk(categoryName).click();
    }
    
    
  
  
}

