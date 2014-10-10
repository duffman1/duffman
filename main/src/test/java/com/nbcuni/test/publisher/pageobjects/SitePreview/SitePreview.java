package com.nbcuni.test.publisher.pageobjects.SitePreview;

import org.testng.Assert;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com PreviewSite Library. Copyright
 * 
 * @author Khan, Faizan
 * @version 1.0 Date: February 14, 2014
 *********************************************/
public class SitePreview {

	 private Driver webDriver;
	 private WebDriverWait wait;
	 
	 //PAGE OBJECT CONSTRUCTOR    
	 public SitePreview(Driver webDriver) {
		 this.webDriver = webDriver;
		 PageFactory.initElements(webDriver, this);
	     wait = new WebDriverWait(webDriver, 10);  
	 }
	 
	 //PAGE OBJECT IDENTIFIERS
	 @FindBy(how = How.ID, using ="preview-site")
	 private WebElement PreviewSite_Lnk;
	    
	 @FindBy(how = How.CSS, using ="input[value='Enable Preview']")
	 private WebElement EnablePreview_Btn;
	    
	 @FindBy(how = How.ID, using ="edit-active-condition-container-active-condition-selector")
	 private WebElement SelectCondition_Ddl;
	    
	 @FindBy(how = How.ID, using ="edit-active-condition-container-date-condition-widget-preview-date-datepicker-popup-0")
	 private WebElement Date_Txb;
	      
	 @FindBy(how = How.ID, using ="edit-active-condition-container-date-condition-widget-preview-date-timeEntry-popup-1")
	 private WebElement Time_Txb;
	   
	 @FindBy(how = How.ID, using ="edit-cancel")
	 private WebElement DisablePreview_Btn;	
	    
	 @FindBy(how = How.CSS, using ="input[value='Update Preview']")
	 private WebElement UpdatePreview_Btn;
	    
	
	 //PAGE OBJECT METHODS
	 public void ClickEnablePreviewBtn() throws Exception { 
		 
		 Reporter.log("Click the 'Enable Preview' button.");
		 Thread.sleep(1000);
		 try {
			 	EnablePreview_Btn.click();
	    	}
	    	catch (WebDriverException e) {
	    		webDriver.executeScript("arguments[0].click();", EnablePreview_Btn);
	    	}
		 	
	 }
	    
	 public void ClickPreviewSiteLnk() throws Exception { 
	    	
		 Reporter.log("Click on the 'Preview Site' link.");
		 Thread.sleep(1000);
		 try {
			 PreviewSite_Lnk.click();
		 }
		 catch (WebDriverException e) {
			 webDriver.executeScript("arguments[0].click();", PreviewSite_Lnk);
		 }
	 }
	    
	 public void VerifyEnablePreviewBtnVisible() throws Exception { 
	    	
		 Reporter.log("Verify the 'Enable Preview' button is visible.");
		 wait.until(ExpectedConditions.visibilityOf(EnablePreview_Btn));
		 //Assert.assertTrue(EnablePreview_Btn.isDisplayed());
	 }
	    
	 public void SelectACondition() throws Exception { 
	    	
		 Reporter.log("Select 'Site as of ...' from the 'SELECT A CONDITION' drop down list.");	
		 wait.until(ExpectedConditions.visibilityOf(SelectCondition_Ddl));
		 new Select(SelectCondition_Ddl).selectByVisibleText("Site as of ...");
	     
	 }
	       
	 public void VerifySelectAConditionValue(final String value) throws Exception { 	    	
	    	
		 Reporter.log("Verify the selected option of the 'SELECT A CONDITION' drop down list is '" + value + "'.");	  
		 Thread.sleep(1000);
		 wait.until(ExpectedConditions.visibilityOf(SelectCondition_Ddl));
		 Assert.assertEquals(new Select(SelectCondition_Ddl).getFirstSelectedOption().getText(),value);
	 }
	   
	 public void EnterDate(String date) throws Exception {
	    	
		 Reporter.log("Enter '" + date + "' in the 'DATE' text box.");
		 wait.until(ExpectedConditions.visibilityOf(Date_Txb)).clear();
		 Date_Txb.sendKeys(date);
		 
	 }
	    
	 public void VerifyDateValue(String date) throws Exception {
	    	
		 Reporter.log("Verify the value of the 'DATE' text box is '" + date + "'.");	    	
		 Assert.assertEquals(Date_Txb.getAttribute("value"),date);
		 
	 }
	     
	 public void EnterTime(String time) throws Exception {
	    		
		 Reporter.log("Enter '" + time + "' in the 'TIME' text box.");		
		 Time_Txb.clear();	
		 Time_Txb.sendKeys(time);
		 
	 }
	       
	 public void VerifyTimeValue(String time) throws Exception {
	    	
		 Reporter.log("Verify the value of the 'TIME' text box is '" + time + "'.");	    	
		 Assert.assertTrue(Time_Txb.getAttribute("value").contains(time));
		 
	 }
	      
	 public void VerifyDisablePreviewBtnVisible() throws Exception {
	    	
		 Reporter.log("Verify the 'Disable Preview' is visible.");
		 wait.until(ExpectedConditions.visibilityOf(DisablePreview_Btn));
		 
	 }
	 
	 public void VerifyDisablePreviewBtnNotVisible() throws Exception {
	    	
		 Reporter.log("Verify the 'Disable Preview' is NOT visible.");
		 wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(DisablePreview_Btn)));
		 
	 }
	    
	 public void ClickDisablePreviewBtn() throws Exception {
	    	
		 Reporter.log("Click the 'Disable Preview' button.");	    
		 Thread.sleep(1000);
		 DisablePreview_Btn.click();
	 }
	    
	 public void VerifyUpdatePreviewBtnVisible() throws Exception {	    	
	    	
		 Reporter.log("Verify the 'Update Preview' button is present.");
		 UpdatePreview_Btn.isDisplayed();
	 }
	       
	 public void ClickUpdatePreviewBtn() throws Exception {
	    	
		 Reporter.log("Click the 'Update Preview' button.");	
		 Thread.sleep(1000);
		 UpdatePreview_Btn.click();
	 }
}
