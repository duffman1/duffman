package com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Queues Revision List Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 19, 2013
 *********************************************/

public class QueuesRevisionList {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public QueuesRevisionList(Driver webDriver) {
    	config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By UpdateState_Btn = By.xpath("(//input[@value='Update State'])[1]");
    
    private By Revision_Lnk = By.xpath("//a[text()='Revisions']");
    
    private By Schedule_Tab = By.xpath("//a[text()='Schedule']");
    
    private By StateFlowHistoryEvent_Txt = By.xpath("(//td[@class='views-field views-field-history-list'])[1]");
    
    private By Cancel_Lnk = By.id("edit-cancel");
    
    private By EditExtendMenu_Btn(String index) {
		return By.xpath("(//table[contains(@class, 'views-table')]//td/..//a[text()='operations'])[" + index + "]");
	}
	
	private By EditMenuDelete_Btn(String index) {
		return By.xpath("(//table[contains(@class, 'views-table')]//td/..//a[text()='Delete'])[" + index + "]");
	}
	
	private By EditMenu_Btn(String index) {
		return By.xpath("(//table[contains(@class, 'views-table')]//td/..//a[text()='Edit'])[" + index + "]");
	}
	
	private By AllStateFlow_Rws = By.xpath("//table[contains(@class, 'views-table')]//tr[contains(@class, 'state-flow')]");
	
    
    //PAGE OBJECT METHODS
    public void ClickRevisionsLnk() throws Exception {
    	
    	Reporter.log("Click the 'Revisions' tab.");
    	interact.Click(waitFor.ElementVisible(Revision_Lnk));
    	
    }
    
    public void VerifyStateFlowHistoryEvent(String messageTxt) throws Exception {
    	
    	Reporter.log("Verify the revision event text of '" + messageTxt + "'.");
    	waitFor.ElementContainsText(StateFlowHistoryEvent_Txt, messageTxt);
    	
    }
    
    public void ClickCancelLnk() throws Exception {
    	
    	Reporter.log("Click the 'Cancel' link.");
    	interact.Click(waitFor.ElementVisible(Cancel_Lnk));
    	
    }
    
    public void ClickUpdateStateBtn() throws Exception {
    	
    	Reporter.log("Click the 'Update state' button.");
    	interact.Click(waitFor.ElementVisible(UpdateState_Btn));
    	
    }
    
    public void ClickEditQueueExtendMenuBtn(String revisionIndex) throws Exception {
    	
    	Reporter.log("Click the 'Expand' arrow to extend the edit menu for queue revision index '" + revisionIndex + "'.");
    	interact.Click(waitFor.ElementVisible(EditExtendMenu_Btn(revisionIndex)));
    	
    }
    
    public void ClickDeleteQueueMenuBtn(String revisionIndex) throws Exception {
    	
    	Reporter.log("Click the 'Delete' button for the queue revision index '" + revisionIndex + "'.");
    	interact.Click(waitFor.ElementVisible(EditMenuDelete_Btn(revisionIndex)));
    	
    }
    
    public void ClickEditQueueMenuBtn(String revisionIndex) throws Exception {
    	
    	Reporter.log("Click the 'Edit' button for queue revision index + '" + revisionIndex + "'.");
    	interact.Click(waitFor.ElementVisible(EditMenu_Btn(revisionIndex)));
    	
    }
    
    public void VerifyRevisionCount(int revisionCount) throws Exception {
    	
    	Reporter.log("Verify the count of revisions in the Queues revision list is '" + revisionCount + "'.");
    	Assert.assertEquals(waitFor.ElementsVisible(AllStateFlow_Rws).size(), revisionCount);
    	
    }
    
    public void clickScheduleTab() throws Exception{
    	
    	Reporter.log("Click Schedule Tab");	
    	interact.Click(waitFor.ElementVisible(Schedule_Tab));
    	
    }
    
}

