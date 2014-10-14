package com.nbcuni.test.publisher.pageobjects.Reports;

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
		
	//PAGE OBJECT CONSTRUCTOR
	public EntityTrackerReports(Driver webDriver) {
		PageFactory.initElements(webDriver, this);
		
	}

	//PAGE OBJECT IDENTIFIERS
	@FindBy(how = How.ID, using ="edit-from-datepicker-popup-0")
	private WebElement From_Txb;
	
	@FindBy(how = How.ID, using ="edit-to-datepicker-popup-0")
	private WebElement To_Txb;
	
	@FindBy(how = How.ID, using ="edit-submit")
	private WebElement Apply_Btn;
	
	@FindBy(how = How.XPATH, using ="(//div[contains(@class, 'krumo-element krumo-expand')]//em)[1][contains(text(), 'Array')]")
	private WebElement ParentArrayElement_Lnk;
	
	@FindBy(how = How.XPATH, using ="(//a[@class='krumo-name'])[2]")
	private WebElement ChildArrayElement_Lnk;

	@FindBy(how = How.XPATH, using ="(//a[@class='krumo-name'])[2]/../..//a[text()='info']")
	private WebElement ChildArrayElementInfo_Lnk;
	
	@FindBy(how = How.XPATH, using ="((//a[@class='krumo-name'])[2]/../..//a[text()='info']/../..//a[contains(text(), 'title')]/..//strong)[2]")
	private WebElement ChildArrayElementInfoTitle_Lnk;
	
	
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
	
	public void ClickParentArrayElementLnk() throws Exception {

		Reporter.log("Click the '...Array, # elements' link.");
		ParentArrayElement_Lnk.click();
	}
	
	public void ClickChildArrayElementLnk() throws Exception {

		Reporter.log("Click the child content node link.");
		ChildArrayElement_Lnk.click();
	}
	
	public Integer GetChildNodeId() throws Exception {
		
		Reporter.log("Get the child node content id number.");
		return Integer.parseInt(ChildArrayElement_Lnk.getText());
	}
	
	public void ClickChildArrayInfoElementLnk() throws Exception {

		Reporter.log("Click the child content node 'info' link.");
		ChildArrayElementInfo_Lnk.click();
	}
	
	public String GetChildTitle() throws Exception {
		
		Reporter.log("Get the child node content id title.");
		return ChildArrayElementInfoTitle_Lnk.getText();
	}
	
}