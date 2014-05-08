package com.nbcuni.test.publisher.pageobjects.Queues;

import com.nbcuni.test.publisher.common.Driver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

/*********************************************
 * publisher.nbcuni.com Schedule Queues Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 23, 2013
 *********************************************/

public class ScheduleQueue {

	private WebDriver webDriver;
	private WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public ScheduleQueue(Driver webDriver) {
    	this.webDriver = webDriver;
        PageFactory.initElements(webDriver,  this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='Schedule']")
    private WebElement Schedule_Tab;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Add scheduled revision']")
    private WebElement AddScheduledRevision_Lnk;
    
    @FindBy(how = How.ID, using = "edit-revision-id")
    private WebElement Revision_Ddl;
    
    @FindBy(how = How.ID, using = "edit-operation")
    private WebElement Operation_Ddl;
    
    @FindBy(how = How.ID, using = "edit-datetime-datepicker-popup-0")
    private WebElement Date_Txb;
    
    @FindBy(how = How.ID, using = "edit-datetime-timeEntry-popup-1")
    private WebElement Time_Txb;
    
    @FindBy(how = How.CSS, using = "input[value='Schedule']")
    private WebElement Schedule_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[@class='content']//tbody")
    private WebElement ScheduledQueue_Ctr;
    
    @FindBy(how = How.XPATH, using = "//table[@class='sticky-enabled tableheader-processed sticky-table']/..//td[@class='empty message']")
    private WebElement Schedule_Tbl;
    
    @FindBy(how = How.XPATH, using = "//td[text()='Completed']")
    private WebElement Complete_txt;
    
    private WebElement RunNow_Lnk(String contentItemTitle, String action) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + contentItemTitle + "')]/../td[text()='" + action + "']/..//a[text()='Run now']"));
    }
    
    private WebElement ExpandRunNowMenu_Lnk(String contentItemTitle, String action) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + contentItemTitle + "')]/../td[text()='" + action + "']/..//a[text()='operations']"));
    }
    
    private WebElement Cancel_Lnk(String contentItemTitle, String action) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + contentItemTitle + "')]/../td[text()='" + action + "']/..//a[text()='Cancel']"));
    }
    
    //PAGE OBJECT METHODS
    public void ClickScheduleTab() throws Exception {
    	
    	Reporter.log("Click the 'Schedule' tab.");
    	Schedule_Tab.click();
    }
    
    public void ClickAddScheduledRevisionLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add Scheduled Revision' link.");
    	AddScheduledRevision_Lnk.click();
    }
    
    public void SelectRevision(String queueTitle) throws Exception {
    	
    	Reporter.log("Select revision titled '" + queueTitle + "'.");
    	List<WebElement> allOptions = new Select(Revision_Ddl).getOptions();
    	for (WebElement el : allOptions) {
    		if (el.getText().contains(queueTitle)) {
    			el.click();
    			break;
    		}
    	}
    }
    
    public void SelectOperation(String operationValue) throws Exception {
    	
    	Reporter.log("Select '" + operationValue + "' from the 'Select operation' drop down list.");
    	new Select(Operation_Ddl).selectByVisibleText(operationValue);
    }
    
    public void EnterDate(String date) throws Exception {
    	
    	Reporter.log("Enter '" + date + "' in the 'Date' text box.");
    	Date_Txb.sendKeys(date);
    }
    
    public void EnterTime(String time) throws Exception {
    	
    	Reporter.log("Enter '" + time + "' in the 'Time' text box.");
    	Time_Txb.click();
    	Time_Txb.sendKeys(time);
    }
    
    public void ClickScheduleBtn() throws Exception {
    	
    	Reporter.log("Click the 'Schedule' button.");
    	Schedule_Btn.click();
    }
    
    public void VerifyScheduledQueue(List<String> scheduledTxt) throws Exception {
    	
    	Thread.sleep(250);
    	String scheduledCtrTxt = ScheduledQueue_Ctr.getText();
    	
    	for (String txt : scheduledTxt) {
    		Reporter.log("Verify the scheduled text '" + txt + "' is present in the scheduled queue.");
    		Assert.assertTrue(scheduledCtrTxt.contains(txt), "Text '" + txt + "' not present in scheduled queue container.");
    	}
    	
    }

    public void VerifyScheduleTableisEmpty() throws Exception {

    	Reporter.log("Verify the scheduled revision table is empty.");
    	Thread.sleep(250); 
    	if (!ScheduledQueue_Ctr.getText().contains("No scheduled revisions available")) {
    		Assert.fail("Scheduled table is not empty.");
    	}

    }
    public void VerifyAddScheduleVersionLnkPresent() throws Exception {
        
    	Reporter.log("Verify the 'Add Scheduled Revision' link is present.");
    	AddScheduledRevision_Lnk.isDisplayed();
    }

    public void ClickRunNowLnk(String contentItemTitle, String action) throws Exception {
        
    	Reporter.log("Click the 'Run now' link for content item '" + contentItemTitle + "' with action '" + action + "'.");
    	RunNow_Lnk(contentItemTitle, action).click();

    }
    
    public void ClickRunNowExpandLnk(String contentItemTitle, String action) throws Exception {
        
    	Reporter.log("Click the 'Run now' expand menu link for content item '" + contentItemTitle + "' with action '" + action + "'.");
    	ExpandRunNowMenu_Lnk(contentItemTitle, action).click();

    }
    
    public void VerifyRunNowLnkPresent(String contentItemTitle, String action) throws Exception {
    	
    	Reporter.log("Verify the 'Run now' link is present for content item '" + contentItemTitle + "' with action '" + action + "'.");
    	RunNow_Lnk(contentItemTitle, action).isDisplayed();
    	
    }
    
    public void ClickCancelLnk(String contentItemTitle, String action) throws Exception {
        
    	Reporter.log("Click the 'Cancel' link for content item '" + contentItemTitle + "' with action '" + action + "'.");
    	Thread.sleep(500);
    	wait.until(ExpectedConditions.visibilityOf(Cancel_Lnk(contentItemTitle, action))).click();

    }

    public void VerifyCancelLnkPresent(String contentItemTitle, String action) throws Exception {
    	
    	Reporter.log("Verify the 'Cancel' link is present for content item '" + contentItemTitle + "' with action '" + action + "'.");
    	Cancel_Lnk(contentItemTitle, action).isDisplayed();
    }
    
    public void VerifyRunStatusComplete() throws Exception {
    	
    	Reporter.log("Verify run status is complete.");
    	Complete_txt.isDisplayed();	
    }

}

