package com.nbcuni.test.publisher.pageobjects.content;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
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
    		catch (Exception e) {
    			Assert.fail("Result '" + result + "' is not present in the search result set");
    		}
    	}
    }

    public void ClickSearchTitleLnk(String title) throws Exception {

        new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                visibilityOf(webDriver.findElement(By.xpath("//a[text()='" + title + "']")))).click();
    }
    
    
}

