package com.nbcuni.test.publisher.pageobjects.Cron;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;

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
    public Cron(Driver webDriver) {
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webDriver, timeout);
    	interact = new Interact(webDriver, timeout);
        errorChecking = new ErrorChecking(webDriver);
        navigation = new EmberNav(webDriver);
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

