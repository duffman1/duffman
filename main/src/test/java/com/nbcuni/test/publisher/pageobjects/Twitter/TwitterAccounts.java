package com.nbcuni.test.publisher.pageobjects.Twitter;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Twitter Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 19, 2014
 *********************************************/

public class TwitterAccounts {

	private static CustomWebDriver webDriver;
	private static AppLib applib;
	
    //PAGE OBJECT CONSTRUCTOR
    public TwitterAccounts(CustomWebDriver webDriver, AppLib applib) {
    	TwitterAccounts.webDriver = webDriver;
    	TwitterAccounts.applib = applib;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-submit")
    private static WebElement GoToTwitterAddAuthenticatedAccount_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[text()='@PublisherSeven']")
    private static WebElement PublisherSevenAccount_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void ClickGoToTwitterAddAuthenticatedAccountBtn() throws Exception {
    	
    	Reporter.log("Click the 'Go to Twitter to add an authenticated account' button.");
    	GoToTwitterAddAuthenticatedAccount_Btn.click();
    }
    
    public boolean TwitterAccountExists() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	boolean existingAccountPresent = false;
    	try {
    		PublisherSevenAccount_Lnk.isDisplayed();
    		existingAccountPresent = true;
    	}
    	catch (Exception e) {
    		existingAccountPresent = false;
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    	return existingAccountPresent;
    }
    
   
  
}

