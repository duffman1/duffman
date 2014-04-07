package com.nbcuni.test.publisher.pageobjects.Cron;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;

/*********************************************
 * publisher.nbcuni.com Cron Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 2, 2014
 *********************************************/

public class Cron {

	private Taxonomy taxonomy;
	private Overlay overlay;
	private ErrorChecking errorChecking;
	
    //PAGE OBJECT CONSTRUCTOR
    public Cron(Driver webDriver, AppLib applib) {
        PageFactory.initElements(webDriver, this);
        taxonomy = new Taxonomy(webDriver);
        overlay = new Overlay(webDriver, applib);
        errorChecking = new ErrorChecking(webDriver, applib);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//li[contains(text(), 'to complete the import')]/a[text()='Run cron']")
    private WebElement RunCronToCompleteImport_Lnk;
    
    @FindBy(how = How.ID, using = "edit-run")
    private WebElement RunCron_Btn;
    
    
    //PAGE OBJECT METHODS
    public void ClickRunCronToCompleteImportLnk() throws Exception {
    	
    	Reporter.log("Click the 'Run Cron to complete import' link.");
    	RunCronToCompleteImport_Lnk.click();
    }
    
    public void ClickRunCronBtn() throws Exception {
    	
    	Reporter.log("Click the 'Run Cron' button.");
    	RunCron_Btn.click();
    }
    
    public void RunCron(Boolean inOverlay) throws Exception {
    	
    	taxonomy.NavigateSite("Home>>Run cron");
    	if (inOverlay == true) {
    		overlay.SwitchToActiveFrame();
    	}
    	errorChecking.VerifyNoMessageErrorsPresent();
    	if (inOverlay == true) {
    		overlay.ClickCloseOverlayLnk();
    	}
    }
    
}

