package com.nbcuni.test.publisher.pageobjects;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Modules Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class Modules {

    private Driver webDriver;
    private Config config;
    private ContentParent contentParent;
    private EmberNav navigation;
    private WebDriverWait wait;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public Modules(Driver webDriver) {
    	this.webDriver = webDriver;
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	contentParent = new ContentParent(webDriver);
    	navigation = new EmberNav(webDriver);
    	waitFor = new WaitFor(webDriver, timeout);
    	wait = new WebDriverWait(webDriver, timeout);
    	interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    //private By FilterList_Txb = By.xpath("//input[@id='edit-module-filter-name']");
    
    private By SaveConfiguration_Btn = By.xpath("//input[@value='Save configuration']");
    
    private By Continue_Btn = By.xpath("//input[@value='Continue']");
    
    private By Uninstall_Btn = By.xpath("//input[@value='Uninstall']");
    
    private By AcquiaAgentModule_Row = By.xpath("//label/strong[text()='Acquia agent']/../../..");
    
    private By ModuleName_Row(String moduleName) {
    	return By.xpath("//label/strong[text()='" + moduleName + "']/../../..");
    }
    
    private By ModuleName_Cbx(String moduleName) {
    	return By.xpath("//label/strong/span[text()='" + moduleName + "']/../../../../td[@class='checkbox']//input");
    }
    
    @SuppressWarnings("unused")
	private By ModuleName_Tgl(String moduleName) {
    	return By.xpath("//label/strong[text()='" + moduleName + "']/../../../td[@class='checkbox']/div[contains(@class, 'toggle')]");
    }
    
    private By UninstallModuleName_Cbx(String moduleName) {
    	return By.xpath("//label[text()='" + moduleName + "']/../../..//input");
    }
    
    private By Category_Lnk(String categoryName) {
    	return By.xpath("//div[@id='module-filter-tabs']//a[contains(text(), '" + categoryName + "')]");
    }
    
    
    //PAGE OBJECT METHODS
    public void WaitForFilterVisible(final String filterName) throws Exception {
    	
    	Reporter.log("Wait for module filter titled '" + filterName + "' to be visible in module list.");
    	final WebElement moduleNameRow = waitFor.ElementPresent(ModuleName_Row(filterName));
    	final WebElement aquiaAgentModuleRow = waitFor.ElementPresent(AcquiaAgentModule_Row);
    	
    	wait.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return (moduleNameRow.getAttribute("style").equals("") || moduleNameRow.getAttribute("style").equals("display: table-row;"))
    					&& aquiaAgentModuleRow.getAttribute("style").equals("display: none;");
   		 	}
    	});
    	Thread.sleep(500);
    	
    }
    
    public void EnterFilterName(String filterName) throws Exception {
    	
    	/*
    	Reporter.log("Enter '" + filterName + "' in the 'Filter Name' text box.");
    	interact.Type(waitFor.ElementVisible(FilterList_Txb), filterName);
    	this.WaitForFilterVisible(filterName);
    	*/
    }
    
    public void VerifyConfigurationSaved() throws Exception{
    	
    	contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	
    }
    
    public boolean IsModuleEnabled(String moduleName) throws Exception {
    	
    	boolean isModuleEnabled = false;
    	
    	if (waitFor.ElementPresent(ModuleName_Cbx(moduleName)).isSelected() == true) {
    		
    		isModuleEnabled = true;
    	}
    	
    	return isModuleEnabled;
    	
    }
    
    public void EnableModule(String moduleName) throws Exception {
    	
    	if (waitFor.ElementPresent(ModuleName_Cbx(moduleName)).isSelected() == false) {
    		
    		Reporter.log("Check the '" + moduleName + "' check box.");
    		interact.ScrollToTop();
    		interact.Click(waitFor.ElementVisible(ModuleName_Cbx(moduleName)));
    		
    		Reporter.log("Click the 'Save configuration' button.");
    		interact.ScrollToTop();
    		interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));
    		
        	boolean additionalModulesRequired = false;
        	
        	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        	try {
        		webDriver.findElement(Continue_Btn).isDisplayed();
        		additionalModulesRequired = true;
        	}
        	catch (Exception e) { }
        	
        	if (additionalModulesRequired == true) {
        		
        		Reporter.log("Click the 'Continue' button to enable additional modules.");
        		interact.Click(waitFor.ElementVisible(Continue_Btn));
        		
        	}
        	
        	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
        	
        	this.VerifyConfigurationSaved();
        	
    	}
    	else {
    		Reporter.log("Verify the '" + moduleName + "' check box is checked.");
    	}
    	
    }
    
    public boolean DisableModule(String moduleName) throws Exception {
    	
    	boolean moduleAlreadyDisabled = true;
    	
    	if (waitFor.ElementPresent(ModuleName_Cbx(moduleName)).isSelected() == true) {
    		Reporter.log("Uncheck the '" + moduleName + "' module checkbox.");
    		interact.ScrollToTop();
    		interact.Click(waitFor.ElementVisible(ModuleName_Cbx(moduleName)));
    		
    		Reporter.log("Click the 'Save configuration' button.");
    		interact.ScrollToTop();
    		interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));
    		
    		this.VerifyConfigurationSaved();
    		
    		moduleAlreadyDisabled = false;
    	}
    	
    	return moduleAlreadyDisabled;
    	
    }
    
    public Boolean IsModuleInstalled(String moduleName) throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	Boolean isModuleInstalled = null;
    	
    	try {
    		webDriver.findElement(UninstallModuleName_Cbx(moduleName)).getLocation();
    		isModuleInstalled = true;
    	}
    	catch (NoSuchElementException e) {
    		isModuleInstalled = false;
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
    	return isModuleInstalled;
    	
    }
    
    public void UninstallModule(String moduleName) throws Exception {
    	
    	Reporter.log("Click the '" + moduleName + "' uninstall checkbox.");
    	interact.Click(waitFor.ElementVisible(UninstallModuleName_Cbx(moduleName)));
    	
    	Reporter.log("Click the 'Uninstall' button.");
    	interact.Click(waitFor.ElementVisible(Uninstall_Btn));
    	contentParent.VerifyPageContentPresent(Arrays.asList("The following modules will be completely uninstalled from your site, and all data from these modules will be lost!", 
    			moduleName));
    	
    	Reporter.log("Click the 'Uninstall' button to confirm.");
    	interact.Click(waitFor.ElementVisible(Uninstall_Btn));
    	
    }
    
    public void VerifyModuleEnabled(String moduleName) throws Exception {
    	
    	navigation.Modules();
    	this.EnableModule(moduleName);
    	
    }
    
    public void ClickCategoryLnk(String categoryName) throws Exception {
    	
    	Reporter.log("Click the '" + categoryName + "' module category link.");
    	interact.Click(waitFor.ElementVisible(Category_Lnk(categoryName)));
    	
    }
    
}

