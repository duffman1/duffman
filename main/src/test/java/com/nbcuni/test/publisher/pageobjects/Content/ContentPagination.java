package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Driver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;


/*********************************************
 * publisher.nbcuni.com Content Pagination Library. Copyright 
 *
 * @author Vineela Juturu
 * @version 1.0 Date: September 12, 2014
 *********************************************/

public class ContentPagination {

	private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public ContentPagination(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS      
    @FindBy(how = How.XPATH, using = "//div[@class='item-list']")
    private WebElement Pager_Ctr;
    
    private List<WebElement> Page_Elements() {
    	return webDriver.findElements(By.xpath("//div[@class='item-list']/ul/li"));
    }
    
    @FindBy(how = How.XPATH, using = "//div[@class='item-list']//a[text()='next ›']")
    private WebElement NextPage_lnk;
    
    //PAGE OBJECT METHODS
    public void VerifyPageCtrElementPresent() throws Exception {
    	
    	Reporter.log("Verify Page Container Element is present.");
    	
    	if(!Pager_Ctr.isDisplayed()){
    	Assert.fail("Page Container Element is not present on the page");
    	}
    	
    }
    
    public int getExpectedNumberOfPages(int itemsPerPage, int totalLimit) throws Exception{
    	
    	Reporter.log("Returns Expected NumberOfPages.");
    	
    	int expectedPages = 0;
    	if(itemsPerPage != 0){
   
    		if (totalLimit%itemsPerPage == 0) {
    			expectedPages = totalLimit/itemsPerPage;
    		} else {
    			expectedPages = totalLimit/itemsPerPage + 1;
    		}
    	}
    	return expectedPages;
    	
    }
    
    public void VerifyCorrectNumberOfPagesDisplayed(int itemsPerPage, int totalLimit) throws Exception{
    
    	Reporter.log("Verify Correct NumberOfPages Displayed.");
    	
    	int expectedPages = getExpectedNumberOfPages (itemsPerPage, totalLimit);
    	
    	List<WebElement> totalPages = Page_Elements();
    		
    	//On Initial view there will be 2 additional pages 'next >','last>>' 
    	if ((totalPages.size()-2) != (expectedPages)){
    			Assert.fail("Actual Pages Displayed '"+ (totalPages.size()-2) +"' is not equals to expected pages '"+expectedPages+"'.");    			
    	}    	 
    
    }
    
    public void ClickNextPageLink(){
    	
    	Reporter.log("Click the 'NextPage' Link");
    	
    	NextPage_lnk.click();
    	
    }
    
    public boolean isNextPageExists(){
    	
    	Reporter.log("Returns true if nextPage link exists.");
    	
    	boolean nextPage = false;
    	try{
    	if(NextPage_lnk.isDisplayed())
    		nextPage = true;
    	}catch(NoSuchElementException e){
    		nextPage = false;
    	}	
    	return nextPage;
    }
    
}




