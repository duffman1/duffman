package com.nbcuni.test.publisher.pageobjects.Queues;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Queues Revision List Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 19, 2013
 *********************************************/

public class QueuesRevisionList {

    //PAGE OBJECT CONSTRUCTOR
    public QueuesRevisionList(CustomWebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "(//input[@value='Update State'])[1]")
    private static WebElement UpdateState_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Revisions']")
    private static WebElement Revision_Lnk;
    
    @FindBy(how = How.XPATH, using = "(//td[@class='views-field views-field-history-list'])[1]")
    private static WebElement StateFlowHistoryEvent_Txt;
    
    @FindBy(how = How.ID, using = "edit-cancel")
    private static WebElement Cancel_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void ClickRevisionsLnk() throws Exception {
    	
    	Reporter.log("Click the 'Revisions' tab.");
    	Revision_Lnk.click();
    }
    
    public void VerifyStateFlowHistoryEvent(String messageTxt) throws Exception {
    	
    	Reporter.log("Verify the revision event text of '" + messageTxt + "'.");
    	if (!StateFlowHistoryEvent_Txt.getText().contains(messageTxt)) {
    		Assert.fail("Revision history text of '" + messageTxt + "' is not present.");
    	}
    }
    
    public void ClickCancelLnk() throws Exception {
    	
    	Reporter.log("Click the 'Cancel' link.");
    	Cancel_Lnk.click();
    }
    
    public void ClickUpdateStateBtn() throws Exception {
    	
    	Reporter.log("Click the 'Update state' button.");
    	UpdateState_Btn.click();
    }
    
}

