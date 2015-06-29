package com.nbcuni.test.publisher.pageobjects.Twitter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

/*********************************************
 * publisher.nbcuni.com Twitter Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 19, 2014
 *********************************************/

public class TwitterAccounts {

	private WebDriver webWebWebDriver;
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public TwitterAccounts(WebDriver webWebWebDriver) {
    	this.webWebWebDriver = webWebWebDriver;
    	config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By GoToTwitterAddAuthenticatedAccount_Btn = By.id("edit-submit");
    
    private By PublisherSevenAccount_Lnk = By.xpath("//a[text()='@PublisherSeven']");
    
    
    //PAGE OBJECT METHODS
    public void ClickGoToTwitterAddAuthenticatedAccountBtn() throws Exception {
    	
    	Reporter.log("Click the 'Go to Twitter to add an authenticated account' button.");
    	interact.Click(waitFor.ElementVisible(GoToTwitterAddAuthenticatedAccount_Btn));
    	
    }
    
    public boolean TwitterAccountExists() throws Exception {
    	
    	webWebWebDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	
    	boolean existingAccountPresent = false;
    	try {
    		webWebWebDriver.findElement(PublisherSevenAccount_Lnk);
    		existingAccountPresent = true;
    	}
    	catch (Exception e) { }
    	
    	webWebWebDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
    	return existingAccountPresent;
    }
    
   
  
}

