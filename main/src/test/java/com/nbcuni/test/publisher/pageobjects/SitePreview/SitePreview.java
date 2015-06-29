package com.nbcuni.test.publisher.pageobjects.SitePreview;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com PreviewSite Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: November 14, 2014
 *********************************************/
public class SitePreview {

	 private Config config;
	 private Integer timeout;
	 private WaitFor waitFor;
	 private Interact interact;
	 
	 //PAGE OBJECT CONSTRUCTOR    
	 public SitePreview(WebDriver webWebWebDriver) {
		 config = new Config();
		 timeout = config.getConfigValueInt("WaitForWaitTime");
		 waitFor = new WaitFor(webWebWebDriver, timeout);
		 interact = new Interact(webWebWebDriver, timeout);
	 }
	 
	 //PAGE OBJECT IDENTIFIERS
	 private By InteractivePreview_Btn = By.xpath("//button[@class='iib-icon iib-icon-iib iib-trigger']");
	    
	 private By EnablePreview_Lnk = By.cssSelector("input[value='Enable Preview']");
	    
	 private By SelectCondition_Ddl = By.xpath("//select[contains(@id, 'edit-active-condition-container-active-condition-selector')]");
	    
	 private By Date_Txb = By.id("edit-active-condition-container-date-condition-widget-preview-date-datepicker-popup-0");
	      
	 private By Time_Txb = By.id("edit-active-condition-container-date-condition-widget-preview-date-timeEntry-popup-1");
	   
	 private By DisablePreview_Lnk = By.id("edit-cancel");
	    
	 private By UpdatePreview_Lnk = By.cssSelector("input[value='Update Preview']");
	    
	
	 //PAGE OBJECT METHODS
	 public void ClickInteractivePreviewBtn() throws Exception { 
		 
		 Reporter.log("Click the 'Interactive Preview' button.");
		 interact.Click(waitFor.ElementVisible(InteractivePreview_Btn));
		 Thread.sleep(1000);
		 
	 }
	    
	 public void VerifyEnablePreviewLnkVisible() throws Exception {
		 
		 Reporter.log("Verify the 'Enable Preview' link is present.");
		 waitFor.ElementVisible(EnablePreview_Lnk);
		 
	 }
	 
	 public void VerifyEnablePreviewLnkNotPresent() throws Exception {
		 
		 Reporter.log("Verify the 'Enable Preview' link is not present.");
		 waitFor.ElementNotPresent(EnablePreview_Lnk);
		 
	 }
	 
	 public void ClickEnablePreviewLnk() throws Exception { 
	    	
		 Reporter.log("Click on the 'Enable Preview' link.");
		 interact.Click(waitFor.ElementVisible(EnablePreview_Lnk));
		 Thread.sleep(1000);
		 
	 }
	    
	 public void SelectACondition() throws Exception { 
	    	
		 Reporter.log("Select 'Site as of ...' from the 'SELECT A CONDITION' drop down list.");	
		 interact.Select(waitFor.ElementVisible(SelectCondition_Ddl), "Site as of ...");
		 Thread.sleep(1000);
		 
	 }
	       
	 public void VerifySelectAConditionValue(final String value) throws Exception { 	    	
	    	
		 Reporter.log("Verify the selected option of the 'SELECT A CONDITION' drop down list is '" + value + "'.");	 
		 Select ele = new Select(waitFor.ElementVisible(SelectCondition_Ddl));
		 Assert.assertEquals(ele.getFirstSelectedOption().getText(),value);
		 
	 }
	   
	 public void EnterDate(String date) throws Exception {
	    	
		 Reporter.log("Enter '" + date + "' in the 'DATE' text box.");
		 interact.Type(waitFor.ElementVisible(Date_Txb), date);
		 Thread.sleep(1000);
		 
	 }
	 
	 public void VerifyDateTxbVisible() throws Exception { 
	    	
		 Reporter.log("Verify the 'Date' Textbox is visible.");	
		 waitFor.ElementVisible(Date_Txb);
		 
	 }
	    
	 public void VerifyDateValue(String date) throws Exception {
	    	
		 Reporter.log("Verify the value of the 'DATE' text box is '" + date + "'.");	    	
		 Assert.assertEquals(waitFor.ElementVisible(Date_Txb).getAttribute("value"),date);
		 
	 }
	     
	 public void EnterTime(String time) throws Exception {
	    		
		 Reporter.log("Enter '" + time + "' in the 'TIME' text box.");
		 interact.Type(waitFor.ElementVisible(Time_Txb), time);
		 Thread.sleep(1000);
		 
	 }
	       
	 public void VerifyTimeValue(String time) throws Exception {
	    	
		 Reporter.log("Verify the value of the 'TIME' text box is '" + time + "'.");	    	
		 Assert.assertTrue(waitFor.ElementVisible(Time_Txb).getAttribute("value").contains(time));
		 
	 }
	      
	 public void VerifyDisablePreviewLnkVisible() throws Exception {
	    	
		 Reporter.log("Verify the 'Disable Preview' link is visible.");
		 waitFor.ElementVisible(DisablePreview_Lnk);
		 
	 }
	 
	 public void VerifyDisablePreviewLnkNotVisible() throws Exception {
	    	
		 Reporter.log("Verify the 'Disable Preview' link is NOT visible.");
		 waitFor.ElementNotVisible(DisablePreview_Lnk);
		 
	 }
	    
	 public void ClickDisablePreviewLnk() throws Exception {
	    	
		 Reporter.log("Click the 'Disable Preview' link.");	    
		 interact.Click(waitFor.ElementVisible(DisablePreview_Lnk));
		 Thread.sleep(1000);
		 
	 }
	    
	 public void VerifyUpdatePreviewLnkVisible() throws Exception {	    	
	    	
		 Reporter.log("Verify the 'Update Preview' link is present.");
		 waitFor.ElementVisible(UpdatePreview_Lnk);
		 
	 }
	       
	 public void ClickUpdatePreviewLnk() throws Exception {
	    	
		 Reporter.log("Click the 'Update Preview' link.");	
		 interact.Click(waitFor.ElementVisible(UpdatePreview_Lnk));
		 Thread.sleep(1000);
		 
	 }
}
