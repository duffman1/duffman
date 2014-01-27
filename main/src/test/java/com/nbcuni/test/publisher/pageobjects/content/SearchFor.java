package com.nbcuni.test.publisher.pageobjects.content;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*********************************************
 * publisher.nbcuni.com Searc Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 2, 2014
 *********************************************/

public class SearchFor {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Title_Txb = "//input[@id='edit-title']";
    private static String Apply_Btn = "//input[@id='edit-submit-content-files']";
    private static String AllSearchResults_Lnks = "//tbody//td[3]//a";
    private static String AllMPXSearchResults_Lnks = "//tbody//td[2]//a";
    private static String Reset_Btn = "//input[@id='edit-reset']";
    private static String MPXPlayerAccount_Ddl = "//select[@id='edit-player-account']";
    private static String MPXPlayerStatus_Ddl = "//select[@id='edit-status']";
    
    public SearchFor(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void EnterTitle(String title) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Title_Txb)))).sendKeys(title);
    }
    
    public void ClickApplyBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Apply_Btn)))).click();
    }
    
    public void ClickResetBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Reset_Btn)))).click();
    }
    
    public void VerifySearchResultsPresent(List<String> resultSet) throws Exception {
    	
    	Thread.sleep(2000); //TODO - replace this sleep with a better wait option
    	List<WebElement> allResults = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOfAllElements(webDriver.findElements(By.xpath("//table/tbody/tr/td[contains(@class, 'title')]"))));
    	
    	List<String> allResultTitles = new ArrayList<String>();
    	for (WebElement el : allResults) {
    		
    		allResultTitles.add(el.getText());
    	}
    	
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

    	Thread.sleep(1000); //TODO - slight pause required here due to stale element exception. page factory leverage will likely resolve this
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                visibilityOf(webDriver.findElement(By.xpath("//a[text()='" + title + "']")))).click();
    }
    
    public void VerifySearchHeaderColumnOrder() throws Exception {
    	
    	List<WebElement> allColumns = webDriver.findElements(By.xpath("//thead//th/a"));
    	
    	Assert.assertTrue(allColumns.get(0).getText().equals("TITLE"));
    	Assert.assertTrue(allColumns.get(1).getText().equals("TYPE"));
    	Assert.assertTrue(allColumns.get(2).getText().equals("SIZE"));
    	Assert.assertTrue(allColumns.get(3).getText().equals("UPLOADED BY"));
    	Assert.assertTrue(allColumns.get(4).getText().equals("UPLOAD DATE"));
    }
    
    public void VerifyMPXSearchHeaderColumnOrder() throws Exception {
    	
    	List<WebElement> allColumns = webDriver.findElements(By.xpath("//thead//th"));
    	
    	Assert.assertTrue(allColumns.get(0).getText().equals("ID"));
    	Assert.assertTrue(allColumns.get(1).getText().equals("TITLE"));
    	Assert.assertTrue(allColumns.get(2).getText().equals("DESCRIPTION"));
    	Assert.assertTrue(allColumns.get(3).getText().equals("SOURCE"));
    	Assert.assertTrue(allColumns.get(4).getText().equals("MPX STATUS"));
    	Assert.assertTrue(allColumns.get(5).getText().equals("FIRST IMPORTED"));
    	Assert.assertTrue(allColumns.get(6).getText().equals("LAST UPDATED"));
    }
    
    public String GetFirstSearchResult() throws Exception {

    	Thread.sleep(1000); //TODO - slight pause required here due to stale element exception. page factory leverage will likely resolve this
        String firstResult = webDriver.findElement(By.xpath("(//tbody//td[3]//a)[1]")).getText();
        
    	return firstResult;
    }
    
    public String GetFirstMPXSearchResult() throws Exception {

    	Thread.sleep(1000); //TODO - slight pause required here due to stale element exception. page factory leverage will likely resolve this
        String firstResult = webDriver.findElement(By.xpath("(//tbody//td[2]//a)[1]")).getText();
        
    	return firstResult;
    }
    
    public String GetFirstPublishedSearchResult() throws Exception {

    	Thread.sleep(1000); //TODO - slight pause required here due to stale element exception. page factory leverage will likely resolve this
        String firstPublishedResult = webDriver.findElement(By.xpath("(//div[text()='Published']/../..//td[@class='views-field views-field-title']/a)[1]")).getText();
        
    	return firstPublishedResult;
    }
    
    public Integer GetSearchResultSize() throws Exception {

    	Thread.sleep(1000); //TODO - slight pause required here due to stale element exception. page factory leverage will likely resolve this
        List<WebElement> resultSet = webDriver.findElements(By.xpath(AllSearchResults_Lnks));
        
        Integer resultSetSize = resultSet.size();
    	
    	return resultSetSize;
    }
    
    public Integer GetMPXSearchResultSize() throws Exception {

    	Thread.sleep(1000); //TODO - slight pause required here due to stale element exception. page factory leverage will likely resolve this
        List<WebElement> resultSet = webDriver.findElements(By.xpath(AllMPXSearchResults_Lnks));
        
        Integer resultSetSize = resultSet.size();
    	
    	return resultSetSize;
    }
    
    public void ClickSearchHeaderColumnLnk(String columnTxt) throws Exception {
    	
    	webDriver.findElement(By.xpath("//thead//th/a[text()='" + columnTxt + "']")).click();
    }
    
    public void VerifyMPXPlayerAccountOptions(List<String> allAccounts) throws Exception {
    	
    	Select mpxPlayerAccountDdl = new Select(webDriver.findElement(By.xpath(MPXPlayerAccount_Ddl)));
    	
    	List<WebElement> allMPXDdlAccountOptions = mpxPlayerAccountDdl.getOptions();
    	
    	Assert.assertTrue(allAccounts.size() + 1 == allMPXDdlAccountOptions.size());
    	
    	List<String> allMPXDdlAccountOptionsTxt = new ArrayList<String>();
    	for (WebElement el : allMPXDdlAccountOptions) {
    		
    		allMPXDdlAccountOptionsTxt.add(el.getText());
    	}
    	
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
    	
    	Select mpxPlayerAccountDdl = new Select(webDriver.findElement(By.xpath(MPXPlayerAccount_Ddl)));
    	mpxPlayerAccountDdl.selectByVisibleText(accountOption);
    }
    
    public void VerifyMPXResultSetSource(String account) throws Exception {

    	Thread.sleep(1000); //TODO - slight pause required here due to stale element exception. page factory leverage will likely resolve this
        List<WebElement> resultSet = webDriver.findElements(By.xpath("//tbody//td[4]"));
        
        for (WebElement el : resultSet) {
    		
    		Assert.assertTrue(el.getText().equals(account));
    	}
    }
    
    public void SelectMPXPlayerStatus(String status) throws Exception {
    	
    	Select mpxPlayerStatusDdl = new Select(webDriver.findElement(By.xpath(MPXPlayerStatus_Ddl)));
    	mpxPlayerStatusDdl.selectByVisibleText(status);
    }
    
    public void VerifyMPXResultSetMPXStatus(String status) throws Exception {

    	Thread.sleep(1000); //TODO - slight pause required here due to stale element exception. page factory leverage will likely resolve this
        List<WebElement> resultSet = webDriver.findElements(By.xpath("//tbody//td[5]"));
        
        for (WebElement el : resultSet) {
    		
    		Assert.assertTrue(el.getText().equals(status));
    	}
    }
    
    
}

