package com.nbcuni.test.publisher.pageobjects.Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Entity Tracker ReportsLibrary. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: October 10, 2014
*********************************************/
public class EntityTrackerReports {
		
	private Driver webDriver;
	
	//PAGE OBJECT CONSTRUCTOR
	public EntityTrackerReports(Driver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
		
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="edit-timestamp-datepicker-popup-0")
	private WebElement From_Txb;
	
	@FindBy(how = How.ID, using ="edit-timestamp-1-datepicker-popup-0")
	private WebElement To_Txb;
	
	@FindBy(how = How.ID, using ="edit-submit-entity-tracker")
	private WebElement Apply_Btn;
	
	private WebElement Title_Lnk(String title) {
		return webDriver.findElement(By.xpath("//a[text()='" + title + "']"));
	}
	
	
	//PAGE OBJECT METHODS
	public void EnterFromDate(String fromDate) throws Exception {

		Reporter.log("Enter '" + fromDate + "' in the 'From' date picker.");
		From_Txb.sendKeys(fromDate + Keys.TAB);
	}

	public void EnterToDate(String toDate) throws Exception {

		Reporter.log("Enter '" + toDate + "' in the 'To' date picker.");
		To_Txb.sendKeys(toDate + Keys.TAB);
	}
	
	public void ClickApplyBtn() throws Exception {

		Reporter.log("Click the 'Apply' button.");
		Apply_Btn.click();
	}
	
	public void ClickContentLnk(String title) throws Exception {
		
		Reporter.log("Click the entity tracker link for content title '" + title + "'.");
		Title_Lnk(title).click();
		
	}
	
}