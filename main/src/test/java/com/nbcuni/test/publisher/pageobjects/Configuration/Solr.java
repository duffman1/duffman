package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
* publisher.nbcuni.com Apache Solr Search Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: February 5, 2014
*********************************************/
public class Solr {
		
	private WaitFor waitFor;
	private Config config;
	private Integer timeout;
	private Interact interact;
	
	//PAGE OBJECT CONSTRUCTOR
	public Solr(Driver webDriver) {
		config = new Config();
		timeout = config.getConfigValueInt("WaitForWaitTime");
		waitFor = new WaitFor(webDriver, timeout);
		interact = new Interact(webDriver, timeout);
	}

	
	//PAGE OBJECT IDENTIFIERS
	private By DeleteSearchSolrIndex_Btn = By.xpath("//input[@value='Delete the Search & Solr index']");
	
	private By DeleteIndex_Btn = By.xpath("//input[@value='Delete index']");
	
	private By IndexAllQueuedContent_Btn = By.xpath("//input[@value='Index all queued content']");
	
	private By IndexAllRemaining_Btn = By.xpath("//input[@value='Index all remaining']");
	
	private By IndexQueuedContent_Btn = By.xpath("//input[@value='Index queued content (50)']");
	
	private By Search_Txb = By.id("edit-search-block-form--2");
	
	
	//PAGE OBJECT METHODS
	public void ClickDeleteSolrSearchIndexBtn() throws Exception {

		Reporter.log("Click the 'Delete the Search & Solr index' button.");
		interact.Click(waitFor.ElementVisible(DeleteSearchSolrIndex_Btn));
		
	}
	
	public void ClickDeleteIndexBtn() throws Exception {

		Reporter.log("Click the 'Delete Index' button.");
		interact.Click(waitFor.ElementVisible(DeleteIndex_Btn));
		
	}
	
	public void ClickIndexAllQueuedContentBtn() throws Exception {

		Reporter.log("Click the 'Index all queued content' button.");
		interact.Click(waitFor.ElementVisible(IndexAllQueuedContent_Btn));
		
	}
	
	public void ClickIndexAllRemainingBtn() throws Exception {

		Reporter.log("Click the 'Index all remaining' button.");
		interact.Click(waitFor.ElementVisible(IndexAllRemaining_Btn));
		
	}
	
	public void ClickIndexQueuedContentBtn() throws Exception {

		Reporter.log("Click the 'Index queued content (50)' button.");
		interact.Click(waitFor.ElementVisible(IndexQueuedContent_Btn));
		
	}
	
	public void Search(String title) throws Exception {
		
		Reporter.log("Enter '" + title + "' in the search text box and submit.");
		interact.Type(waitFor.ElementVisible(Search_Txb), title + Keys.ENTER);
		
	}

}