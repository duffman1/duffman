package com.nbcuni.test.publisher.pageobjects.TVEModule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com TVE Auth Example Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 29, 2014
 *********************************************/

public class TVEAuthExample {

	WebDriver webDriver;
	Config config;
	WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public TVEAuthExample(Driver webDriver) {
    	this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        config = new Config();
        wait = (WebDriverWait) new WebDriverWait(webDriver, 120).ignoring(StaleElementReferenceException.class);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//li[contains(text(), 'MVPD')]/a[text()='setup']")
    private WebElement MVPDSetup_Lnk;
    
    @FindBy(how = How.XPATH, using = "//li[contains(text(), 'Adobe')]/a[text()='setup']")
    private WebElement AdobeSetup_Lnk;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Configure jQuery']")
    private WebElement ConfigureJQuery_Lnk;
    
    @FindBy(how = How.CSS, using = "dd[class='authCheckStatus']")
    private WebElement AuthenticatedStatusChecked_Ctr;
    
    @FindBy(how = How.CSS, using = "dd[class='authN']")
    private WebElement Authenticated_Ctr;
    
    @FindBy(how = How.CSS, using = "dd[class='selectedMvpd']")
    private WebElement SelectedMVPD_Ctr;
    
    @FindBy(how = How.CSS, using = "select[class='allMvpds']")
    private WebElement AllMVPDS_Ddl;
    
    @FindBy(how = How.CSS, using = "input[class='loginBtn']")
    private WebElement Login_Btn;
    
    @FindBy(how = How.CSS, using = "button[class='logoutBtn']")
    private WebElement Logout_Btn;
    
    
    //PAGE OBJECT METHODS
    public Boolean TVEAuthAlreadyConfigured() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	Boolean alreadyConfigured;
    	try {
    		MVPDSetup_Lnk.isDisplayed();
    		alreadyConfigured = false;
    	}
    	catch (NoSuchElementException e) {
    		alreadyConfigured = true;
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
    	return alreadyConfigured;
    }

    public void ClickMVPDSetupLnk() throws Exception {
    	
    	Reporter.log("Click the 'setup' link for MVPD Connection.");
    	MVPDSetup_Lnk.click();
    }
    
    public void ClickAdobePassSetupLnk() throws Exception {
    	
    	Reporter.log("Click the 'setup' link for Adobe Pass configuration.");
    	AdobeSetup_Lnk.click();
    }
    
    public void ClickConfigureJQueryLnk() throws Exception {
    	
    	Reporter.log("Click the 'Configure jQuery' link.");
    	ConfigureJQuery_Lnk.click();
    }
    
    public void VerifyAuthenticationStatusChecked(final String txt) throws Exception {
    	
    	Reporter.log("Verify the 'Authentication status checked:' value is '" + txt + "'.");
    	wait.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return AuthenticatedStatusChecked_Ctr.getText().equals(txt);
    			
   		 	}
    	});
    }
    
    public void VerifyAuthenticated(final String txt) throws Exception {
    	
    	Reporter.log("Verify the 'Authenticated' value is '" + txt + "'.");
    	wait.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return Authenticated_Ctr.getText().equals(txt);
   		 	}
    	});
    }
    
    public void VerifySelectedMVPD(final String txt) throws Exception {
    	
    	Reporter.log("Verify the 'Selected MVPD:' value is '" + txt + "'.");
    	wait.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return SelectedMVPD_Ctr.getText().contains(txt);
   		 	}
    	});
    }
    
    public void SelectMVPD(String option) throws Exception {
    	
    	Reporter.log("Select '" + option + "' from the 'Al VMPDs:' drop down list.");
    	wait.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return new Select(AllMVPDS_Ddl).getOptions().size() > 0;
   		 	}
    	});
    	new Select(AllMVPDS_Ddl).selectByVisibleText(option);
    }
    
    public void ClickLoginBtn() throws Exception {
    	
    	Reporter.log("Click the 'login' button.");
    	Login_Btn.click();
    }
    
    public void ClickLogoutBtn() throws Exception {
    	
    	Reporter.log("Click the 'Logout' button.");
    	Logout_Btn.click();
    }
    
}

