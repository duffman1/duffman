package com.nbcuni.test.publisher.pageobjects.Cron;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Cron Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 2, 2014
 *********************************************/

public class Cron {

    //PAGE OBJECT CONSTRUCTOR
    public Cron(CustomWebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//li[contains(text(), 'to complete the import')]/a[text()='Run cron']")
    private static WebElement RunCronToCompleteImport_Lnk;
    
    @FindBy(how = How.ID, using = "edit-run")
    private static WebElement RunCron_Btn;
    
    
    //PAGE OBJECT METHODS
    public void ClickRunCronToCompleteImportLnk() throws Exception {
    	
    	Reporter.log("Click the 'Run Cron to complete import' link.");
    	RunCronToCompleteImport_Lnk.click();
    }
    
    public void ClickRunCronBtn() throws Exception {
    	
    	Reporter.log("Click the 'Run Cron' button.");
    	RunCron_Btn.click();
    }
    
}

