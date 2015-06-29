package com.nbcuni.test.publisher.pageobjects.Reports;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/*********************************************
* publisher.nbcuni.com Entity Tracker ReportsLibrary. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: October 10, 2014
*********************************************/
public class EntityTrackerReports {
		
	private Config config;
	private WaitFor waitFor;
	private Interact interact;
	
	//PAGE OBJECT CONSTRUCTOR
	public EntityTrackerReports(WebDriver webWebWebDriver) {
		config = new Config();
		Integer timeout = config.getConfigValueInt("WaitForWaitTime");
		waitFor = new WaitFor(webWebWebDriver, timeout);
		interact = new Interact(webWebWebDriver, timeout);
	}

	//PAGE OBJECT IDENTIFIERS
	private By From_Txb = By.id("edit-from-datepicker-popup-0");
	
	private By To_Txb = By.id("edit-to-datepicker-popup-0");
	
	private By Apply_Btn = By.id("edit-submit");
	
	private By ParentArrayElement_Lnk = By.xpath("(//div[contains(@class, 'krumo-element krumo-expand')]//em)[1][contains(text(), 'Array')]");
	
	private By ChildArrayElement_Lnk = By.xpath("(//a[@class='krumo-name'])[2]");
	
	private By ChildArrayElementInfo_Lnk = By.xpath("(//a[@class='krumo-name'])[2]/../..//a[text()='info']");
	
	private By ChildArrayElementInfoTitle_Lnk = By.xpath("((//a[@class='krumo-name'])[2]/../..//a[text()='info']/../..//a[contains(text(), 'title')]/..//strong)[2]");
	
	
	//PAGE OBJECT METHODS
	public void EnterFromDate(String fromDate) throws Exception {
		
		Reporter.log("Enter '" + fromDate + "' in the 'From' date picker.");
		interact.Type(waitFor.ElementVisible(From_Txb), fromDate + Keys.TAB);
		//From_Txb.sendKeys(fromDate + Keys.TAB);
		
	}
	
	public void EnterToDate(String toDate) throws Exception {
		
		Reporter.log("Enter '" + toDate + "' in the 'To' date picker.");
		interact.Type(waitFor.ElementVisible(To_Txb), toDate + Keys.TAB);
		//To_Txb.sendKeys(toDate + Keys.TAB);
		
	}
	
	public void ClickApplyBtn() throws Exception {
	
		Reporter.log("Click the 'Apply' button.");
		interact.Click(waitFor.ElementVisible(Apply_Btn));
		
	}
	
	public void ClickParentArrayElementLnk() throws Exception {
	
		Reporter.log("Click the '...Array, # elements' link.");
		interact.Click(waitFor.ElementVisible(ParentArrayElement_Lnk));
		
	}
	
	public void ClickChildArrayElementLnk() throws Exception {
	
		Reporter.log("Click the child content node link.");
		interact.Click(waitFor.ElementVisible(ChildArrayElement_Lnk));
		
	}
	
	public Integer GetChildNodeId() throws Exception {
		
		Reporter.log("Get the child node content id number.");
		return Integer.parseInt(waitFor.ElementVisible(ChildArrayElement_Lnk).getText());
		
	}
	
	public void ClickChildArrayInfoElementLnk() throws Exception {
	
		Reporter.log("Click the child content node 'info' link.");
		interact.Click(waitFor.ElementVisible(ChildArrayElementInfo_Lnk));
		
	}
	
	public String GetChildTitle() throws Exception {
		
		Reporter.log("Get the child node content id title.");
		return waitFor.ElementVisible(ChildArrayElementInfoTitle_Lnk).getText();
		
	}
}