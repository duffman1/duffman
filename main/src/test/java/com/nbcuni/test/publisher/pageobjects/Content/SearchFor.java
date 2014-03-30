package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*********************************************
 * publisher.nbcuni.com Search Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 2, 2014
 *********************************************/

public class SearchFor {

    private Driver webDriver;
    private AppLib applib;
    
    //PAGE OBJECT CONSTRUCTOR
    public SearchFor(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        this.applib = applib;
        PageFactory.initElements(webDriver, this);
    }
   
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[@id='edit-title']")
    private WebElement Title_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Apply']")
    private WebElement Apply_Btn;
    
    private List<WebElement> AllSearchResults_Lnks() {
    	List<WebElement> els = webDriver.findElements(By.xpath("//tbody//td[3]//a"));
    	return els;
    }
    
    private List<WebElement> AllMPXSearchResults_Lnks() {
    	List<WebElement> els = webDriver.findElements(By.xpath("//tbody//td[2]//a"));
    	return els;
    }
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-reset']")
    private WebElement Reset_Btn;
    
    @FindBy(how = How.XPATH, using = "//select[@id='edit-player-account']")
    private WebElement MPXPlayerAccount_Ddl;
    
    @FindBy(how = How.XPATH, using = "//select[@id='edit-account']")
    private WebElement MPXMediaSource_Ddl;
    
    @FindBy(how = How.XPATH, using = "//select[@id='edit-status']")
    private WebElement Status_Ddl;
    
    private List<WebElement> AllResultSet_Ttls() {
    	return webDriver.findElements(By.xpath("//table/tbody/tr/td[contains(@class, 'title')]"));
    }
    
    private WebElement SearchTitle_Lnk(String title) {
    	return webDriver.findElement(By.xpath("//a[text()='" + title + "']"));
    }
    
    private WebElement SearchThumbnail_Img(String title) {
    	return webDriver.findElement(By.xpath("//a[text()='" + title + "']/../../td//img"));
    }
    
    private List<WebElement> AllSearchHeader_Clms() {
    	return webDriver.findElements(By.xpath("//thead//th/a"));
    }
    
    private List<WebElement> AllMPXSearchHeader_Clms() {
    	return webDriver.findElements(By.xpath("//thead//th"));
    }
    
    @FindBy(how = How.XPATH, using = "(//tbody//td[3]//a)[1]")
    private WebElement FirstSearchResult_Lnk;
    
    @FindBy(how = How.ID, using = "edit-changed-datepicker-popup-0")
    private WebElement UpdatedOnAfter_Txb;
    
    @FindBy(how = How.XPATH, using = "(//tbody//td[3]//a)[1]")
    private WebElement FirstMPXMediaSearchResult_Lnk;
    
    @FindBy(how = How.XPATH, using = "(//tbody//td[2]//a)[1]")
    private WebElement FirstMPXPlayerSearchResult_Lnk;
    
    @FindBy(how = How.XPATH, using = "(//div[text()='Published']/../..//td[@class='views-field views-field-title']/a)[1]")
    private WebElement FirstPublishedSearchResult_Lnk;
    
    private WebElement ColumnHeader_Lnk(String lnkTxt) {
    	return webDriver.findElement(By.xpath("//thead//th/a[text()='" + lnkTxt + "']"));
    }
    
    List<WebElement> AllMPXResultSetSource_Itms() {
    	return webDriver.findElements(By.xpath("//tbody//td[4]"));
    }
    
    private List<WebElement> AllMPXResultSetStatus_Itms() {
    	return webDriver.findElements(By.xpath("//tbody//td[5]"));
    }
    
    
    //PAGE OBJECT METHODS
    public void EnterTitle(String title) throws Exception {
    	
    	Reporter.log("Enter title " + title + ".");
    	Title_Txb.clear();
    	Title_Txb.sendKeys(title);
    }
    
    public void EnterUpdatedOnAfterDate(String date) throws Exception {
    	
    	Reporter.log("Enter '" + date + "' in the 'Updated On or After' text box.");
    	UpdatedOnAfter_Txb.clear();
    	UpdatedOnAfter_Txb.sendKeys(date);
    }
    
    public void ClickApplyBtn() throws Exception {
    	
    	Reporter.log("Click the 'Apply' button.");
    	Apply_Btn.click();
    }
    
    public void ClickResetBtn() throws Exception {
    	
    	Reporter.log("Click the 'Reset' button.");
    	Reset_Btn.click();
    }
    
    public void VerifySearchResultsPresent(List<String> resultSet) throws Exception {
    	
    	Reporter.log("Get the title text of every result in the search result set.");
    	Thread.sleep(250);
    	List<String> allResultTitles = new ArrayList<String>();
    	for (WebElement el : AllResultSet_Ttls()) {
    		
    		allResultTitles.add(el.getText());
    	}
    	
    	Reporter.log("For each expected result, assert it is present in the result set.");
    	for (String result : resultSet) {
    		
    		try {
    			Assert.assertTrue(allResultTitles.contains(result));
    		}
    		catch (AssertionError e) {
    			Assert.fail("Result '" + result + "' is not present in the search result set");
    		}
    	}
    }

    public void ClickSearchTitleLnk(String title) throws Exception {

    	Reporter.log("Click the first search title link in the search result set.");
    	Thread.sleep(250); 
        SearchTitle_Lnk(title).click();
    }
    
    public void VerifySearchThumbnailImgPresent(String title, String imgName) throws Exception {

    	Reporter.log("Verify the search thumbnail named '" + imgName + "' is present for search result '" + title + "'.");
    	Assert.assertTrue(SearchThumbnail_Img(title).getAttribute("src").contains(imgName));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imgName + "' is not fully loaded after timeout");
            }
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			SearchThumbnail_Img(title));
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
    public void VerifySearchHeaderColumnOrder() throws Exception {
    	
    	Reporter.log("Verify that the search result set column order matches as expected.");
    	List<WebElement> allColumns = AllSearchHeader_Clms();
    	Assert.assertTrue(allColumns.get(0).getText().equals("TITLE"));
    	Assert.assertTrue(allColumns.get(1).getText().equals("TYPE"));
    	Assert.assertTrue(allColumns.get(2).getText().equals("SIZE"));
    	Assert.assertTrue(allColumns.get(3).getText().equals("UPLOADED BY"));
    	Assert.assertTrue(allColumns.get(4).getText().equals("UPLOAD DATE"));
    }
    
    public void VerifyMPXSearchHeaderColumnOrder() throws Exception {
    	
    	Reporter.log("Verify that the search result set column order matches as expected.");
    	List<WebElement> allColumns = AllMPXSearchHeader_Clms();
    	Assert.assertTrue(allColumns.get(0).getText().equals("ID"));
    	Assert.assertTrue(allColumns.get(1).getText().equals("TITLE"));
    	Assert.assertTrue(allColumns.get(2).getText().equals("DESCRIPTION"));
    	Assert.assertTrue(allColumns.get(3).getText().equals("SOURCE"));
    	Assert.assertTrue(allColumns.get(4).getText().equals("MPX STATUS"));
    	Assert.assertTrue(allColumns.get(5).getText().equals("FIRST IMPORTED"));
    	Assert.assertTrue(allColumns.get(6).getText().equals("LAST UPDATED"));
    }
    
    public String GetFirstSearchResult() throws Exception {

    	Reporter.log("Get the text of the first search result set item.");
    	Thread.sleep(250); 
        return FirstSearchResult_Lnk.getText();
    }
    
    public String GetFirstMPXMediaSearchResult() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	Reporter.log("Get the text of the first search result set item.");
    	String linkTxt = "";
    	try {
    		linkTxt = FirstMPXMediaSearchResult_Lnk.getText();	
    	}
    	catch (Exception e) {}
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
        return linkTxt;
    }
    
    public String GetFirstMPXPlayerSearchResult() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	Reporter.log("Get the text of the first search result set item.");
    	String linkTxt = "";
    	try {
    		linkTxt = FirstMPXPlayerSearchResult_Lnk.getText();	
    	}
    	catch (Exception e) {}
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
        return linkTxt;
    }
    
    public String GetFirstPublishedSearchResult() throws Exception {

    	Reporter.log("Get the text of the first published search result set item.");
    	Thread.sleep(250);
    	return FirstPublishedSearchResult_Lnk.getText();	
 
    }
    
    public Integer GetSearchResultSize() throws Exception {
    	
    	Reporter.log("Get the number of results in the result set.");
    	webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	int resultSetSize = AllSearchResults_Lnks().size();
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
        return resultSetSize;
    }
    
    public Integer GetMPXSearchResultSize() throws Exception {

    	Reporter.log("Get the number of results in the result set.");
    	Thread.sleep(250); 
        return AllMPXSearchResults_Lnks().size();
    }
    
    public void ClickSearchHeaderColumnLnk(String columnTxt) throws Exception {
    	
    	Reporter.log("Click the " + columnTxt + " column header link for column sorting.");
    	ColumnHeader_Lnk(columnTxt).click();
    }
    
    public void VerifyMPXPlayerAccountOptions(List<String> allAccounts) throws Exception {
    	
    	Reporter.log("Get all the account options in the 'MPX Player Account' select ddl.");
    	Select mpxPlayerAccountDdl = new Select(MPXPlayerAccount_Ddl);
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
    		
    		try {
    			Assert.assertTrue(allMPXDdlAccountOptionsTxt.contains(account));
    		}
    		catch (AssertionError e) {
    			Assert.fail("Account '" + account + "' is not present in the MPX player account ddl list");
    		}
    	}
    }
    
    public void SelectMPXPlayerAccount(String accountOption) throws Exception {
    	
    	Reporter.log("Select the " + accountOption + " from the 'MPXPlayerAccount' select ddl.");
    	Select mpxPlayerAccountDdl = new Select(MPXPlayerAccount_Ddl);
    	mpxPlayerAccountDdl.selectByVisibleText(accountOption);
    }
    
    public void SelectMPXMediaSource(String accountOption) throws Exception {
    	
    	Reporter.log("Select the " + accountOption + " from the 'Source' select ddl.");
    	new Select(MPXMediaSource_Ddl).selectByVisibleText(accountOption);
    }
    
    public void VerifyMPXResultSetSource(String account) throws Exception {

    	Reporter.log("Get all the MPX result set 'Source' items.");
    	Thread.sleep(250);
        List<WebElement> resultSet = AllMPXResultSetSource_Itms();
        
        Reporter.log("Assert that every source item in the result set equals '" + account + "'.");
        for (WebElement el : resultSet) {
    		
    		Assert.assertTrue(el.getText().equals(account));
    	}
    }
    
    public void SelectStatus(String status) throws Exception {
    	
    	Reporter.log("Select the '" + status + "' from the 'MPX Status/Published' drop down list.");
    	new Select(Status_Ddl).selectByVisibleText(status);
    }
    
    public void VerifyMPXResultSetMPXStatus(String status) throws Exception {

    	Reporter.log("Get all the MPX result set 'Status' items.");
    	Thread.sleep(250); 
    	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<WebElement> resultSet = AllMPXResultSetStatus_Itms();
        
        if (status == "Published") {
        	Reporter.log("Assert that result set size for Published items is greater than 0.");
        	Assert.assertTrue(resultSet.size() > 0);
        }
        
        Reporter.log("Assert that every status item in the result set equals '" + status + "'.");
        for (WebElement el : resultSet) {
    		
    		Assert.assertTrue(el.getText().equals(status));
    	}
        webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    
}

