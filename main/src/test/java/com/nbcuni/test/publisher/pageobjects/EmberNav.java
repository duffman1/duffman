package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;

/*********************************************
 * publisher.nbcuni.com Ember Navigation Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: November 15, 2014
 *********************************************/

public class EmberNav {

    private Driver webDriver;
    private WaitFor waitFor;
    private ErrorChecking errorChecking;
    private Config config;
    
    //PAGE OBJECT CONSTRUCTOR
    public EmberNav(Driver webDriver) {
    	this.webDriver = webDriver;
    	config = new Config();
    	waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    	errorChecking = new ErrorChecking(webDriver);
    	
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By ActiveUserMenu_Lnk = By.id("navbar-item--4");
    
    private By MenuSubNav_Lnk(String lnkTxt) {
    	return By.xpath("//a[@class='navbar-menu-item'][text()='" + lnkTxt + "']");
    }
    
    private By Home_Lnk = By.xpath("//a[@class='navbar-icon navbar-icon-home']");
    
    private By Menu_Lnk = By.xpath("//a[@id='navbar-item--2'][text()='Menu']");
    
    private By Content_Lnk = By.xpath("//a[@id='navbar-link-admin-content']");
    
    private By MainMenuNav_Lnk(String lnkTxt) {
    	return By.xpath("//a[contains(@id, 'navbar-link-admin')][text()='" + lnkTxt + "']");
    }
    
    private By AddContent_Lnk = By.xpath("//div[@id='content']//a[text()='Add content']");
    
    private By AdminLnkByTxt_Lnk(String contentTxt) {
    	return By.xpath("//ul[@class='admin-list']//a[text()='" + contentTxt + "']");
    }
    
    private By TabBar_Lnk(String lnkTxt) {
    	return By.xpath("//div[@id='tab-bar']//a[text()='" + lnkTxt + "']");
    }
    
    private By SecondaryTabBar_Lnk(String lnkTxt) {
    	return By.xpath("//ul[@class='tabs secondary']//a[text()='" + lnkTxt + "']");
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickHomeLnk() throws Exception {
    	
    	Reporter.log("Click the 'Home' link.");
    	waitFor.ElementVisible(Home_Lnk).click();
    	
    }
    
    private void ShowMenu() throws Exception {
    	
    	if (!waitFor.ElementPresent(Content_Lnk).isDisplayed()) {
    		Reporter.log("Click the 'Menu' link to show navigation menu items.");
    		webDriver.findElement(Menu_Lnk).click();
    	}
    	
    	waitFor.ElementVisible(Content_Lnk);
    }
    
    private void HideMenu() throws Exception {
    	/*
    	if (waitFor.ElementPresent(Content_Lnk).isDisplayed()) {
    		Reporter.log("Click the 'Menu' link to hide navigation menu items.");
    		webDriver.findElement(Menu_Lnk).click();
    	}
    	
    	waitFor.ElementNotVisible(Content_Lnk);
    	*/
    }
    
    public void ClickMainMenuLnk(String menuLnkTxt) throws Exception {
    	
    	Reporter.log("Click the '" + menuLnkTxt + "' menu link.");
    	waitFor.ElementVisible(MainMenuNav_Lnk(menuLnkTxt));
    	Thread.sleep(500);
    	waitFor.ElementVisible(MainMenuNav_Lnk(menuLnkTxt)).click();
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void ClickAddContentLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add content' link.");
    	waitFor.ElementVisible(AddContent_Lnk).click();
    	
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void ClickAdminLnkByTxtLnk(String lnkTxt) throws Exception {
    	
    	Reporter.log("Click the '" + lnkTxt + "' link.");
    	waitFor.ElementVisible(AdminLnkByTxt_Lnk(lnkTxt)).click();
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void ClickPrimaryTabNavLnk(String lnkTxt) throws Exception {
    	
    	Reporter.log("Click the primary nav '" + lnkTxt + "'.");
    	waitFor.ElementVisible(TabBar_Lnk(lnkTxt)).click();
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void ClickSecondaryTabNavLnk(String lnkTxt) throws Exception {
    	
    	Reporter.log("Click the secondary nav '" + lnkTxt + "'.");
    	waitFor.ElementVisible(SecondaryTabBar_Lnk(lnkTxt)).click();
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void ClickActiveUserLnk() throws Exception {
    	
    	WebElement ele = waitFor.ElementPresent(ActiveUserMenu_Lnk);
    	Reporter.log("Click the activer user menu link for logged in user '" + ele.getText() + "'.");
    	ele.click();
    	
    }
    
    public void ClickMenuSubNavLnk(String lnkTxt) throws Exception {
    	
    	Reporter.log("Click the menu sub nav '" + lnkTxt + "' link.");
    	waitFor.ElementPresent(MenuSubNav_Lnk(lnkTxt)).click();
    	
    }
    
    
    //CONVENIENCE METHODS
    public void Home() throws Exception {
    	
    	this.ClickHomeLnk();
    	
    }
    
    public void Content() throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("Content");
    	this.HideMenu();
    	
    }
    
    public void Content(String lnkTxt) throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("Content");
    	this.HideMenu();
    	this.ClickPrimaryTabNavLnk(lnkTxt);
    	
    }
    
    public void Content(String primaryLnkTxt, String secondaryLnkTxt) throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("Content");
    	this.HideMenu();
    	this.ClickPrimaryTabNavLnk(primaryLnkTxt);
    	this.ClickSecondaryTabNavLnk(secondaryLnkTxt);
    	
    }
    
    public void AddContent(String contentTxt) throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("Content");
    	this.HideMenu();
    	this.ClickAddContentLnk();
    	this.ClickAdminLnkByTxtLnk(contentTxt);
    	
    }
    
    public void Modules() throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("Modules");
    	this.HideMenu();
    	
    }
    
    public void Modules(String lnkTxt) throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("Modules");
    	this.HideMenu();
    	this.ClickPrimaryTabNavLnk(lnkTxt);
    	
    }
    
    public void Structure(String lnkTxt) throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("Structure");
    	this.HideMenu();
    	this.ClickAdminLnkByTxtLnk(lnkTxt);
    	
    }
    
    public void Configuration(String lnkTxt) throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("Configuration");
    	this.HideMenu();
    	this.ClickAdminLnkByTxtLnk(lnkTxt);
    	
    }
    
    public void Configuration(String lnkTxt, String primaryLnkTxt) throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("Configuration");
    	this.HideMenu();
    	this.ClickAdminLnkByTxtLnk(lnkTxt);
    	this.ClickPrimaryTabNavLnk(primaryLnkTxt);
    	
    }
    
    public void Reports(String lnkTxt) throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("Reports");
    	this.HideMenu();
    	this.ClickAdminLnkByTxtLnk(lnkTxt);
    	
    }
    
    public void People() throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("People");
    	this.HideMenu();
    	
    }
    
    public void People(String primaryLnkTxt) throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("People");
    	this.HideMenu();
    	this.ClickPrimaryTabNavLnk(primaryLnkTxt);
    	
    }
    
    public void People(String primaryLnkTxt, String secondaryLnkTxt) throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("People");
    	this.HideMenu();
    	this.ClickPrimaryTabNavLnk(primaryLnkTxt);
    	this.ClickSecondaryTabNavLnk(secondaryLnkTxt);
    	
    }
    
    public void WorkBench() throws Exception {
    	
    	this.ShowMenu();
    	this.ClickMainMenuLnk("My Workbench");
    	this.HideMenu();
    	
    }
    
    public void ActiveUser(String lnkTxt) throws Exception {
    	
    	this.ClickActiveUserLnk();
    	this.ClickMenuSubNavLnk(lnkTxt);
    	
    }
    
  
}

