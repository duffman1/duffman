package com.nbcuni.test.publisher.pageobjects.Cron;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Cron Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 2, 2014
 *********************************************/

public class Cron {

	private ErrorChecking errorChecking;
	private EmberNav navigation;
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public Cron(WebDriver webWebWebDriver) {
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webWebWebDriver, timeout);
    	interact = new Interact(webWebWebDriver, timeout);
        errorChecking = new ErrorChecking(webWebWebDriver);
        navigation = new EmberNav(webWebWebDriver);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By RunCronToCompleteImport_Lnk = By.xpath("//li[contains(text(), 'to complete the import')]/a[text()='Run cron']");
    
    private By RunCron_Btn = By.id("edit-run");
    
    
    //PAGE OBJECT METHODS
    public void ClickRunCronToCompleteImportLnk() throws Exception {
    	
    	Reporter.log("Click the 'Run Cron to complete import' link.");
    	interact.Click(waitFor.ElementVisible(RunCronToCompleteImport_Lnk));
    	
    }
    
    public void ClickRunCronBtn() throws Exception {
    	
    	Reporter.log("Click the 'Run Cron' button.");
    	interact.Click(waitFor.ElementVisible(RunCron_Btn));
    	
    }
    
    public void RunCron() throws Exception {
    	
    	navigation.Configuration("Cron");
    	this.ClickRunCronBtn();
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
}

