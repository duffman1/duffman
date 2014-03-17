package com.nbcuni.test.publisher.pageobjects.Queues;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

	private static CustomWebDriver webDriver;
	private static WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public QueuesRevisionList(CustomWebDriver webDriver) {
    	QueuesRevisionList.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
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
    
    private static WebElement EditExtendMenu_Btn(String index) {
		return webDriver.findElement(By.xpath("(//table[contains(@class, 'views-table')]//td/..//a[text()='operations'])[" + index + "]"));
	}
	
	private static WebElement EditMenuDelete_Btn(String index) {
		return webDriver.findElement(By.xpath("(//table[contains(@class, 'views-table')]//td/..//a[text()='Delete'])[" + index + "]"));
	}
	
	private static WebElement EditMenu_Btn(String index) {
		return webDriver.findElement(By.xpath("(//table[contains(@class, 'views-table')]//td/..//a[text()='Edit'])[" + index + "]"));
	}
	
	private static List<WebElement> StateFlow_Rws() {
		return webDriver.findElements(By.xpath("//table[contains(@class, 'views-table')]//tr[contains(@class, 'state-flow')]"));
	}
    
    
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
    
    public void ClickEditQueueExtendMenuBtn(String revisionIndex) throws Exception {
    	
    	Reporter.log("Click the 'Expand' arrow to extend the edit menu for queue revision index '" + revisionIndex + "'.");
    	EditExtendMenu_Btn(revisionIndex).click();
    }
    
    public void ClickDeleteQueueMenuBtn(String revisionIndex) throws Exception {
    	
    	Reporter.log("Click the 'Delete' button for the queue revision index '" + revisionIndex + "'.");
    	wait.until(ExpectedConditions.visibilityOf(EditMenuDelete_Btn(revisionIndex))).click();
    	
    }
    
    public void ClickEditQueueMenuBtn(String revisionIndex) throws Exception {
    	
    	Reporter.log("Click the 'Edit' button for queue revision index + '" + revisionIndex + "'.");
    	EditMenu_Btn(revisionIndex).click();
    }
    
    public void VerifyRevisionCount(int revisionCount) throws Exception {
    	
    	Reporter.log("Verify the count of revisions in the Queues revision list is '" + revisionCount + "'.");
    	Assert.assertEquals(StateFlow_Rws().size(), revisionCount);
    }
    
}

