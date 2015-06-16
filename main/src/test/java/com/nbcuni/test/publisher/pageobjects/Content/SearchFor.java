package com.nbcuni.test.publisher.pageobjects.Content;

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
 * publisher.nbcuni.com Search Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: November 13, 2014
 *********************************************/

public class SearchFor {

	private Driver webDriver;
    private Config config;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public SearchFor(Driver webDriver) {
    	this.webDriver = webDriver;
        config = new Config();
        Integer timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
   
    
    //PAGE OBJECT IDENTIFIERS
    private By PublicID_Txb = By.id("edit-pid");
    
    private By Title_Txb = By.xpath("//input[@id='edit-title']");
    
    private By Apply_Btn = By.xpath("//input[@value='Apply']");
    
    private By AllSearchResults_Lnks = By.xpath("//tbody//td[3]//a");
    
    private By AllMPXSearchResults_Lnks = By.xpath("//tbody//td[3]//a");
    
    private By Reset_Btn = By.xpath("//input[@id='edit-reset']");
    
    private By MPXPlayerAccount_Ddl = By.xpath("//select[@id='edit-player-account']");
    
    private By MPXMediaSource_Ddl = By.xpath("//select[@id='edit-account']");
    
    private By Status_Ddl = By.xpath("//select[@id='edit-status']");
    
    private By AllResultSet_Ttls = By.xpath("//table/tbody/tr/td[contains(@class, 'title')]");
    
    private By AllMPXResultSet_Ttls = By.xpath("//tbody//td[3]//a");
    	
    private By SearchTitle_Lnk(String title) {
    	return By.xpath("//a[text()='" + title + "']");
    }
    
    private By SearchThumbnail_Img(String title) {
    	return By.xpath("//a[text()='" + title + "']/../../td//img");
    }
    
    private By AllSearchHeader_Clms = By.xpath("//thead//th/a");
    	
    private By AllMPXSearchHeader_Clms = By.xpath("//thead//th");
    
    private By FirstSearchResult_Lnk = By.xpath("(//tbody//td[3]//a)[1]");
    
    private By UpdatedOnAfter_Txb = By.id("edit-changed-datepicker-popup-0");
    
    private By FirstMPXMediaSearchResult_Lnk = By.xpath("(//tbody//td[3]//a)[1]");
    
    private By FirstMPXPlayerSearchResult_Lnk = By.xpath("(//tbody//td[3]//a)[1]");
    
    private By FirstPublishedSearchResult_Lnk = By.xpath("(//div[text()='Published']/../..//td[@class='views-field views-field-title']/a)[1]");
    
    private By ColumnHeader_Lnk(String lnkTxt) {
    	return By.xpath("//thead//th/a[text()='" + lnkTxt + "']");
    }
    
    private By AllMPXResultSetSource_Itms = By.xpath("//tbody//td[5]");
    
    private By AllMPXResultSetStatus_Itms = By.xpath("//tbody//td[6]");
    
    private By PublicID_Txt(String playerTitle) {
    	return By.xpath("//a[text()='" + playerTitle + "']/../../td[2]");
    }
    
    
    //PAGE OBJECT METHODS
    public void EnterTitle(String title) throws Exception {
    	
    	Reporter.log("Enter title " + title + ".");
    	interact.Type(waitFor.ElementVisible(Title_Txb), title);
    	
    }
    
    public void EnterPublicID(String id) throws Exception {
    	
    	Reporter.log("Enter '" + id + "' in the 'Public ID' text box.");
    	interact.Type(waitFor.ElementVisible(PublicID_Txb), id);
    	
    }
    
    public void EnterUpdatedOnAfterDate(String date) throws Exception {
    	
    	Reporter.log("Enter '" + date + "' in the 'Updated On or After' text box.");
    	interact.Type(waitFor.ElementVisible(UpdatedOnAfter_Txb), date);
    	
    }
    
    public void ClickApplyBtn() throws Exception {
    	
    	Reporter.log("Click the 'Apply' button.");
    	interact.Click(waitFor.ElementVisible(Apply_Btn));
    	
    }
    
    public void ClickResetBtn() throws Exception {
    	
    	Reporter.log("Click the 'Reset' button.");
    	interact.Click(waitFor.ElementVisible(Reset_Btn));
    	
    }
    
    public void VerifySearchResultsPresent(List<String> resultSet) throws Exception {
    	
    	List<String> allResultTitles = new ArrayList<String>();
    	for (WebElement el : waitFor.ElementsVisible(AllResultSet_Ttls)) {
    		allResultTitles.add(el.getText());
    	}
    	
    	for (String result : resultSet) {
    		Reporter.log("Verify result '" + result + "' is present in the result set.");
    		Assert.assertTrue(allResultTitles.contains(result), "Result '" + result + "' is not present "
    				+ "in the search result set");
    		
    	}
    }
    
    public void ClickSearchTitleLnk(String title) throws Exception {

    	Reporter.log("Click the first search title link in the search result set.");
    	interact.Click(waitFor.ElementVisible(SearchTitle_Lnk(title)));
    	
    }
    
    public void VerifySearchThumbnailImgPresent(String title, String imgName) throws Exception {

    	Reporter.log("Verify the search thumbnail named '" + imgName + "' is present for search result '" + title + "'.");
    	WebElement ele = waitFor.ElementContainsAttribute(SearchThumbnail_Img(title), "src", imgName);
    	
    	Reporter.log("Verify the the img is loaded and visible.");
    	waitFor.ImageVisible(ele);
    	
    }
    
    public void VerifySearchHeaderColumnOrder() throws Exception {
    	
    	Reporter.log("Verify that the search result set column order matches as expected.");
    	List<WebElement> allColumns = waitFor.ElementsVisible(AllSearchHeader_Clms);
    	Assert.assertTrue(allColumns.get(0).getText().equals("TITLE"));
    	Assert.assertTrue(allColumns.get(1).getText().equals("TYPE"));
    	Assert.assertTrue(allColumns.get(2).getText().equals("SIZE"));
    	Assert.assertTrue(allColumns.get(3).getText().equals("UPLOADED BY"));
    	Assert.assertTrue(allColumns.get(4).getText().equals("UPLOAD DATE"));
    }
    
    public void VerifyMPXSearchHeaderColumnOrder() throws Exception {
    	
    	Reporter.log("Verify that the search result set column order matches as expected.");
    	List<WebElement> allColumns = waitFor.ElementsVisible(AllMPXSearchHeader_Clms);
    	Assert.assertTrue(allColumns.get(0).getText().equals("ID"));
    	Assert.assertTrue(allColumns.get(1).getText().equals("PUBLIC ID"));
    	Assert.assertTrue(allColumns.get(2).getText().equals("TITLE"));
    	Assert.assertTrue(allColumns.get(3).getText().equals("DESCRIPTION"));
    	Assert.assertTrue(allColumns.get(4).getText().equals("SOURCE"));
    	Assert.assertTrue(allColumns.get(5).getText().equals("MPX STATUS"));
    	Assert.assertTrue(allColumns.get(6).getText().equals("FIRST IMPORTED"));
    	Assert.assertTrue(allColumns.get(7).getText().equals("LAST UPDATED"));
    }
    
    public String GetFirstSearchResult() throws Exception {

    	Reporter.log("Get the text of the first search result set item.");
    	return waitFor.ElementVisible(FirstSearchResult_Lnk).getText();
    	
    }
    
    public String GetFirstMPXMediaSearchResult() throws Exception {
    	
    	Reporter.log("Get the text of the first search result set item.");
    	return waitFor.ElementVisible(FirstMPXMediaSearchResult_Lnk).getText();
    	
    }
    
    public String GetFirstMPXMediaSearchResultURL() throws Exception {
    	
    	Reporter.log("Get the url of the first search result set item.");
    	return waitFor.ElementVisible(FirstMPXMediaSearchResult_Lnk).getAttribute("href");
    	
    }
    
    public List<String> GetAllMPXMediaSearchResult() throws Exception {
    	
    	Reporter.log("Get the text of all search result set items.");
    	List<String> allTitles = new ArrayList<String>();
    	for (WebElement el : waitFor.ElementsVisible(AllMPXResultSet_Ttls)) {
    		allTitles.add(el.getText());
    	}
    	
    	return allTitles;
    	
    }
    
    public String GetFirstMPXPlayerSearchResult() throws Exception {
    	
    	Reporter.log("Get the text of the first player search result set item.");
    	return waitFor.ElementVisible(FirstMPXPlayerSearchResult_Lnk).getText();

    }
    
    public String GetFirstPublishedSearchResult() throws Exception {

    	Reporter.log("Get the text of the first published search result set item.");
    	return waitFor.ElementVisible(FirstPublishedSearchResult_Lnk).getText();	
 
    }
    
    public Integer GetSearchResultSize() throws Exception {
    	
    	Reporter.log("Get the number of results in the result set.");
    	return waitFor.ElementsVisible(AllSearchResults_Lnks).size();
    	
    }
    
    public void VerifyNoSearchResultsPresent() throws Exception {
    	
    	Reporter.log("Verify there are no search results present.");
    	waitFor.ElementsNotPresent(AllSearchResults_Lnks);
    	
    }
    
    public Integer GetMPXSearchResultSize() throws Exception {

    	Reporter.log("Get the number of results in the result set.");
    	return waitFor.ElementsVisible(AllMPXSearchResults_Lnks).size();
    	
    }
    
    public void ClickSearchHeaderColumnLnk(String columnTxt) throws Exception {
    	
    	Reporter.log("Click the " + columnTxt + " column header link for column sorting.");
    	interact.Click(waitFor.ElementVisible(ColumnHeader_Lnk(columnTxt)));
    	
    }
    
    public void VerifyMPXPlayerAccountOptions(List<String> allAccounts) throws Exception {
    	
    	Reporter.log("Get all the account options in the 'MPX Player Account' select ddl.");
    	Select mpxPlayerAccountDdl = new Select(waitFor.ElementVisible(MPXPlayerAccount_Ddl));
    	List<WebElement> allMPXDdlAccountOptions = mpxPlayerAccountDdl.getOptions();
    	
    	Reporter.log("Assert that the count of options is expected + 1 for the '-select' option.");
    	Assert.assertTrue(allAccounts.size() + 1 == allMPXDdlAccountOptions.size());
    	
    	Reporter.log("Get the text of each of the ddl options.");
    	List<String> allMPXDdlAccountOptionsTxt = new ArrayList<String>();
    	for (WebElement el : allMPXDdlAccountOptions) {
    		
    		allMPXDdlAccountOptionsTxt.add(el.getText());
    	}
    	
    	Reporter.log("Assert that each expected account option is present in the 'MPX Player Account' select ddl.");
    	for (String account : allAccounts) {
    		
    		Assert.assertTrue(allMPXDdlAccountOptionsTxt.contains(account), "Account '" + account + "' is not present in the MPX player account ddl list");
    		
    	}
    }
    
    public void SelectMPXPlayerAccount(String accountOption) throws Exception {
    	
    	Reporter.log("Select the " + accountOption + " from the 'MPXPlayerAccount' select ddl.");
    	interact.Select(waitFor.ElementVisible(MPXPlayerAccount_Ddl), accountOption);
    	
    }
    
    public void SelectMPXMediaSource(String accountOption) throws Exception {
    	
    	Reporter.log("Select the " + accountOption + " from the 'Source' select ddl.");
    	interact.Select(waitFor.ElementVisible(MPXMediaSource_Ddl), accountOption);
    	
    }
    
    public void VerifyMPXResultSetSource(String account) throws Exception {

    	Reporter.log("Get all the MPX result set 'Source' items.");
    	List<WebElement> resultSet = waitFor.ElementsVisible(AllMPXResultSetSource_Itms);
        
        Reporter.log("Assert that every source item in the result set equals '" + account + "'.");
        for (WebElement el : resultSet) {
    		Assert.assertTrue(el.getText().equals(account));
    	}
        
    }
    
    public void SelectStatus(String status) throws Exception {
    	
    	Reporter.log("Select the '" + status + "' from the 'MPX Status/Published' drop down list.");
    	interact.Select(waitFor.ElementVisible(Status_Ddl), status);
    	
    }
    
    public void VerifyMPXResultSetMPXStatus(String status) throws Exception {

    	Reporter.log("Get all the MPX result set 'Status' items.");
    	List<WebElement> resultSet = webDriver.findElements(AllMPXResultSetStatus_Itms);
    			
        if (status == "Published") {
        	Reporter.log("Assert that result set size for Published items is greater than 0.");
        	Assert.assertTrue(resultSet.size() > 0);
        }
        
        Reporter.log("Assert that every status item in the result set equals '" + status + "'.");
        for (WebElement el : resultSet) {
    		
    		Assert.assertTrue(el.getText().equals(status));
    	}
        
    }
    
    public void VerifyMPXResultPublicID(String playerTitle, String publicID) throws Exception {

    	Reporter.log("Verify that the 'PUBLIC ID' equals '" + publicID + "' for player titled '" + playerTitle + "'.");
    	waitFor.ElementContainsText(PublicID_Txt(playerTitle), publicID);
    	
    }
    
    
}

