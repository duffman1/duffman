package com.nbcuni.test.publisher.pageobjects.TVEModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com TVE Auth Example Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 29, 2014
 *********************************************/

public class TVEAuthExample {

	private Driver webDriver;
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public TVEAuthExample(Driver webDriver) {
    	this.webDriver = webDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By MVPDSetup_Lnk = By.xpath("//li[contains(text(), 'MVPD')]/a[text()='setup']");
    
    private By AdobeSetup_Lnk = By.xpath("//li[contains(text(), 'Adobe')]/a[text()='setup']");
    
    private By ConfigureJQuery_Lnk = By.xpath("//a[text()='Configure jQuery']");
    
    private By AuthenticatedStatusChecked_Ctr = By.cssSelector("dd[class='authCheckStatus']");
    
    private By Authenticated_Ctr = By.cssSelector("dd[class='authN']");
    
    private By SelectedMVPD_Ctr = By.cssSelector("dd[class='selectedMvpd']");
    
    private By AllMVPDS_Ddl = By.cssSelector("select[class='allMvpds']");
    
    private By Login_Btn = By.cssSelector("input[class='loginBtn']");
    
    private By Logout_Btn = By.cssSelector("button[class='logoutBtn']");
    
    
    //PAGE OBJECT METHODS
    public Boolean TVEAuthAlreadyConfigured() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	Boolean alreadyConfigured;
    	try {
    		webDriver.findElement(MVPDSetup_Lnk);
    		alreadyConfigured = false;
    	}
    	catch (NoSuchElementException e) {
    		alreadyConfigured = true;
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	return alreadyConfigured;
    	
    }
    
    public Boolean isMVPDConfigured() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	Boolean alreadyConfigured;
    	try {
    		webDriver.findElement(MVPDSetup_Lnk);
    		alreadyConfigured = false;
    	}
    	catch (NoSuchElementException e) {
    		alreadyConfigured = true;
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	return alreadyConfigured;
    	
    }
    
    public Boolean isAdobePassConfigured() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	Boolean alreadyConfigured;
    	try {
    		webDriver.findElement(AdobeSetup_Lnk);
    		alreadyConfigured = false;
    	}
    	catch (NoSuchElementException e) {
    		alreadyConfigured = true;
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	return alreadyConfigured;
    	
    }

    public void ClickMVPDSetupLnk() throws Exception {
    	
    	Reporter.log("Click the 'setup' link for MVPD Connection.");
    	interact.Click(waitFor.ElementVisible(MVPDSetup_Lnk));
    	
    }
    
    public void ClickAdobePassSetupLnk() throws Exception {
    	
    	Reporter.log("Click the 'setup' link for Adobe Pass configuration.");
    	interact.Click(waitFor.ElementVisible(AdobeSetup_Lnk));
    	
    }
    
    public void ClickConfigureJQueryLnk() throws Exception {
    	
    	Reporter.log("Click the 'Configure jQuery' link.");
    	WebElement ele = waitFor.ElementVisible(ConfigureJQuery_Lnk);
    	Thread.sleep(1000);
    	interact.Click(ele);
    	
    }
    
    public void VerifyAuthenticationStatusChecked(final String txt) throws Exception {
    	
    	Reporter.log("Verify the 'Authentication status checked:' value is '" + txt + "'.");
    	new WaitFor(webDriver, 120).ElementContainsText(AuthenticatedStatusChecked_Ctr, txt);
    	
    }
    
    public void VerifyAuthenticated(final String txt) throws Exception {
    	
    	Reporter.log("Verify the 'Authenticated' value is '" + txt + "'.");
    	waitFor.ElementContainsText(Authenticated_Ctr, txt);
    	
    }
    
    public void VerifySelectedMVPD(final String txt) throws Exception {
    	
    	Reporter.log("Verify the 'Selected MVPD:' value is '" + txt + "'.");
    	waitFor.ElementContainsText(SelectedMVPD_Ctr, txt);
    	
    }
    
    public void SelectMVPD(String option) throws Exception {
    	
    	Reporter.log("Select '" + option + "' from the 'Al VMPDs:' drop down list.");
    	final WebElement ele = waitFor.ElementVisible(AllMVPDS_Ddl);
    	
    	new WebDriverWait(webDriver, 10).until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return new Select(ele).getOptions().size() > 0;
   		 	}
    	});
    	interact.Select(ele, option);
    	
    }
    
    public void ClickLoginBtn() throws Exception {
    	
    	Reporter.log("Click the 'login' button.");
    	interact.Click(waitFor.ElementVisible(Login_Btn));
    	
    }
    
    public void ClickLogoutBtn() throws Exception {
    	
    	Reporter.log("Click the 'Logout' button.");
    	interact.Click(waitFor.ElementVisible(Logout_Btn));
    	
    }
    
}

