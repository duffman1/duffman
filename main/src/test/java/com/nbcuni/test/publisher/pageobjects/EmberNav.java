package com.nbcuni.test.publisher.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
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
    private Interact interact;
    private Integer timeout;
    
    //PAGE OBJECT CONSTRUCTOR
    public EmberNav(Driver webDriver) {
    	this.webDriver = webDriver;
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webDriver, timeout);
    	interact = new Interact(webDriver, timeout);
    	errorChecking = new ErrorChecking(webDriver);
    	
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By ActiveUserMenu_Lnk = By.id("navbar-item--4");
    
    private By MenuSubNav_Lnk(String lnkTxt) {
    	return By.xpath("//a[contains(@class,'navbar-menu-item')][text()='" + lnkTxt + "']");
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
    	Thread.sleep(500);
    	waitFor.ElementVisible(Home_Lnk).click();
    	
    }
    
    private void ShowMenu() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	for (int I = 0; I <= timeout; I++) {
    		
    		if (I == timeout) {
    			Assert.fail("Main menu not visible after " + timeout + " seconds.");
    		}
    		
    		if (!waitFor.ElementPresent(Content_Lnk).isDisplayed()) {
    			Reporter.log("Click the 'Menu' link to show navigation menu items.");
    			interact.Click(waitFor.ElementVisible(Menu_Lnk));
    		}
    		else {
    			break;
    		}
    		
    		Thread.sleep(1000);
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
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
    	WebElement ele = waitFor.ElementVisible(MainMenuNav_Lnk(menuLnkTxt));
    	Thread.sleep(500);
    	interact.Click(ele);
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void ClickAddContentLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add content' link.");
    	WebElement ele = waitFor.ElementVisible(AddContent_Lnk);
    	Thread.sleep(500);
    	interact.Click(ele);
    	
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void ClickAdminLnkByTxtLnk(String lnkTxt) throws Exception {
    	
    	Reporter.log("Click the '" + lnkTxt + "' link.");
    	WebElement ele = waitFor.ElementVisible(AdminLnkByTxt_Lnk(lnkTxt));
    	Thread.sleep(500);
    	interact.Click(ele);
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void ClickPrimaryTabNavLnk(String lnkTxt) throws Exception {
    	
    	Reporter.log("Click the primary nav '" + lnkTxt + "'.");
    	WebElement ele = waitFor.ElementVisible(TabBar_Lnk(lnkTxt));
    	Thread.sleep(500);
    	interact.Click(ele);
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void ClickSecondaryTabNavLnk(String lnkTxt) throws Exception {
    	
    	Reporter.log("Click the secondary nav '" + lnkTxt + "'.");
    	WebElement ele = waitFor.ElementVisible(SecondaryTabBar_Lnk(lnkTxt));
    	Thread.sleep(500);
    	interact.Click(ele);
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void ClickActiveUserLnk() throws Exception {
    	
    	WebElement ele = waitFor.ElementPresent(ActiveUserMenu_Lnk);
    	Reporter.log("Click the activer user menu link for logged in user '" + ele.getText() + "'.");
    	interact.Click(ele);
    	
    }
    
    public void ClickMenuSubNavLnk(String lnkTxt) throws Exception {
    	
    	Reporter.log("Click the menu sub nav '" + lnkTxt + "' link.");
    	WebElement ele = waitFor.ElementVisible(MenuSubNav_Lnk(lnkTxt));
    	Thread.sleep(500);
    	interact.Click(ele);
    	
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
