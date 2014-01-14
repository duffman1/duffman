package com.nbcuni.test.publisher.pageobjects.queues;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


/*********************************************
 * publisher.nbcuni.com Schedule Queues Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 23, 2013
 *********************************************/

public class ScheduleQueue {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Schedule_Tab = "//a[text()='Schedule']";
    private static String AddScheduledRevision_Lnk = "//a[text()='Add scheduled revision']";
    private static String Revision_Ddl = "//select[@id='edit-revision-id']";
    private static String Operation_Ddl = "//select[@id='edit-operation']";
    private static String Date_Txb = "//input[@id='edit-datetime-datepicker-popup-0']";
    private static String Time_Txb = "//input[@id='edit-datetime-timeEntry-popup-1']";
    private static String Schedule_Btn = "//input[@value='Schedule']";
    private static String ScheduledQueue_Ctr = "//div[@class='content']//tbody";
    private static String Schedule_Tbl = "//table[@class='sticky-enabled tableheader-processed sticky-table']/..//td[@class='empty message']";
    private static String RunNow_Btn = "//a[text()='Run now']";
    private static String RunCancel_Btn = "//a[text()='Cancel']";
    private static String Complete_txt = ".//td[text()='Completed']";

    public ScheduleQueue(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void ClickScheduleTab() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(Schedule_Tab)));
    	webDriver.click(Schedule_Tab);
    }
    
    public void ClickAddScheduledRevisionLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(AddScheduledRevision_Lnk)));
    	webDriver.click(AddScheduledRevision_Lnk);
    }
    
    public void SelectRevision(String queueTitle) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Revision_Ddl)));
    	//TODO - add a util method for select by partial text. this should work for the time being
    	List<WebElement> allOptions = new Select(webDriver.findElement(By.xpath(Revision_Ddl))).getOptions();
    	for (WebElement el : allOptions) {
    		if (el.getText().contains(queueTitle)) {
    			el.click();
    			break;
    		}
    	}
    	
    	
    }
    
    public void SelectOperation(String operationValue) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Operation_Ddl)));
    	webDriver.selectFromDropdown(Operation_Ddl, operationValue);
    }
    
    public void EnterDate(String date) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Date_Txb)));
    	webDriver.type(Date_Txb, date);
    }
    
    public void EnterTime(String time) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Time_Txb)));
    	webDriver.type(Time_Txb, time);
    }
    
    public void ClickScheduleBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Schedule_Btn)));
    	webDriver.click(Schedule_Btn);
    }
    
    public void VerifyScheduledQueue(String scheduledTxt) throws Exception {
    	Thread.sleep(1000); //TODO - stale element reference here.
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(ScheduledQueue_Ctr)));
    	ul.verifyObjectContainsText(ScheduledQueue_Ctr, scheduledTxt);
    }

    public void VerifyScheduleTableisEmpty() throws Exception {

        new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                visibilityOf(webDriver.findElement(By.xpath(Schedule_Tbl)))).getText().
                contains("No scheduled revisions available");

    }
    public void VerifyAddScheduleVersionLink() throws Exception {
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                visibilityOf(webDriver.findElement(By.xpath(AddScheduledRevision_Lnk))));
    }

    public void ClickRunNowLnk() throws Exception {
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
                By.xpath(RunNow_Btn))).click();

    }
    
    public void VerifyRunNowBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(RunNow_Btn)));
    }
    public void VerifyCancelBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(RunCancel_Btn)));
    }
    
    public void VerifyCompleteCronRunStatus() throws Exception {
    	webDriver.switchTo().defaultContent();    		
    	webDriver.switchTo().frame(webDriver.findElement(By.xpath(".//iframe[@class='overlay-element overlay-active']")));
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Complete_txt)));  	
    }

}

