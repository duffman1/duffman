package com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

/*********************************************
 * publisher.nbcuni.com Schedule Queues Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: November 15, 2014
 *********************************************/

public class ScheduleQueue {

	private Config config;
	private WaitFor waitFor;
	private Interact interact;
	private Integer timeout;
	
    //PAGE OBJECT CONSTRUCTOR
    public ScheduleQueue(Driver webDriver) {
    	config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Schedule_Tab = By.xpath("//a[text()='Schedule']");
    
    private By AddScheduledRevision_Lnk = By.xpath("//a[text()='Add scheduled revision']");
    
    private By Revision_Ddl = By.id("edit-revision-id");
    
    private By Operation_Ddl = By.id("edit-operation");
    
    private By Date_Txb = By.id("edit-datetime-datepicker-popup-0");
    
    private By Time_Txb = By.id("edit-datetime-timeEntry-popup-1");
    
    private By Schedule_Btn = By.cssSelector("input[value='Schedule']");
    
    private By ScheduledQueue_Ctr = By.xpath("//tbody");
    
    private By Complete_txt = By.xpath("//td[text()='Completed']");
    
    private By RunNow_Lnk(String contentItemTitle, String action) {
    	return By.xpath("//td[contains(text(), '" + contentItemTitle + "')]/../td[text()='" + action + "']/..//a[text()='Run now']");
    }
    
    private By ExpandRunNowMenu_Lnk(String contentItemTitle, String action) {
    	return By.xpath("//td[contains(text(), '" + contentItemTitle + "')]/../td[text()='" + action + "']/..//a[text()='operations']");
    }
    
    private By Cancel_Lnk(String contentItemTitle, String action) {
    	return By.xpath("//td[contains(text(), '" + contentItemTitle + "')]/../td[text()='" + action + "']/..//a[text()='Cancel']");
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickScheduleTab() throws Exception {
    	
    	Reporter.log("Click the 'Schedule' tab.");
    	interact.Click(waitFor.ElementVisible(Schedule_Tab));
    	
    }
    
    public void ClickAddScheduledRevisionLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add Scheduled Revision' link.");
    	interact.Click(waitFor.ElementVisible(AddScheduledRevision_Lnk));
    	
    }
    
    public void SelectRevision(String queueTitle) throws Exception {
    	
    	Reporter.log("Select revision titled '" + queueTitle + "'.");
    	List<WebElement> allOptions = new Select(waitFor.ElementVisible(Revision_Ddl)).getOptions();
    	for (WebElement el : allOptions) {
    		if (el.getText().contains(queueTitle)) {
    			interact.Click(el);
    			break;
    		}
    	}
    }
    
    public void VerifyOperationOptions(List<String> options) throws Exception {
    	
    	Select Operation = new Select(waitFor.ElementVisible(Operation_Ddl));
    	List<WebElement> allOptions = Operation.getOptions();
    	List<String> allOptionsTxt = new ArrayList<String>();
    	for (WebElement option : allOptions) {
    		allOptionsTxt.add(option.getText());
    	}
    	
    	Reporter.log("Verify that the 'Operation' option count equals '" + options.size() + "'.");
    	Assert.assertEquals(allOptions.size(), options.size());
    	
    	for (String option : options) {
    		Assert.assertTrue(allOptionsTxt.contains(option), "Option text '" + option + "' is not present in operations list.");
    	}
    }

    public void SelectOperation(String operationValue) throws Exception {
    	
    	Reporter.log("Select '" + operationValue + "' from the 'Select operation' drop down list.");
    	interact.Select(waitFor.ElementVisible(Operation_Ddl), operationValue);
    	
    }
    
    public void EnterDate(String date) throws Exception {
    	
    	Reporter.log("Enter '" + date + "' in the 'Date' text box.");
    	interact.Type(waitFor.ElementVisible(Date_Txb), date);
    	
    }
    
    public void EnterTime(String time) throws Exception {
    	
    	Reporter.log("Enter '" + time + "' in the 'Time' text box.");
    	interact.Type(waitFor.ElementVisible(Time_Txb), time);
    	
    }
    
    public void ClickScheduleBtn() throws Exception {
    	
    	Reporter.log("Click the 'Schedule' button.");
    	interact.Click(waitFor.ElementVisible(Schedule_Btn));
    	
    }
    
    public void VerifyScheduledQueue(List<String> scheduledTxt) throws Exception {
    	
    	for (String txt : scheduledTxt) {
    		Reporter.log("Verify the scheduled text '" + txt + "' is present in the scheduled queue.");
    		waitFor.ElementContainsText(ScheduledQueue_Ctr, txt);
    	}
    	
    }

    public void VerifyScheduleTableisEmpty() throws Exception {

    	Reporter.log("Verify the scheduled revision table is empty.");
    	waitFor.ElementContainsText(ScheduledQueue_Ctr, "No scheduled revisions available");
    	
    }
    
    public void VerifyAddScheduleVersionLnkPresent() throws Exception {
        
    	Reporter.log("Verify the 'Add Scheduled Revision' link is present.");
    	waitFor.ElementVisible(AddScheduledRevision_Lnk);
    	
    }

    public void ClickRunNowLnk(String contentItemTitle, String action) throws Exception {
        
    	Reporter.log("Click the 'Run now' link for content item '" + contentItemTitle + "' with action '" + action + "'.");
    	interact.Click(waitFor.ElementVisible(RunNow_Lnk(contentItemTitle, action)));
    	
    }
    
    public void ClickRunNowExpandLnk(String contentItemTitle, String action) throws Exception {
        
    	Reporter.log("Click the 'Run now' expand menu link for content item '" + contentItemTitle + "' with action '" + action + "'.");
    	interact.Click(waitFor.ElementVisible(ExpandRunNowMenu_Lnk(contentItemTitle, action)));
    	
    }
    
    public void VerifyRunNowLnkPresent(String contentItemTitle, String action) throws Exception {
    	
    	Reporter.log("Verify the 'Run now' link is present for content item '" + contentItemTitle + "' with action '" + action + "'.");
    	interact.Click(waitFor.ElementVisible(RunNow_Lnk(contentItemTitle, action)));
    	
    }
    
    public void ClickCancelLnk(String contentItemTitle, String action) throws Exception {
        
    	Reporter.log("Click the 'Cancel' link for content item '" + contentItemTitle + "' with action '" + action + "'.");
    	interact.Click(waitFor.ElementVisible(Cancel_Lnk(contentItemTitle, action)));
    	
    }

    public void VerifyCancelLnkPresent(String contentItemTitle, String action) throws Exception {
    	
    	Reporter.log("Verify the 'Cancel' link is present for content item '" + contentItemTitle + "' with action '" + action + "'.");
    	waitFor.ElementVisible(Cancel_Lnk(contentItemTitle, action));
    	
    }
    
    public void VerifyRunStatusComplete() throws Exception {
    	
    	Reporter.log("Verify run status is complete.");
    	waitFor.ElementVisible(Complete_txt);
    	
    }

}

