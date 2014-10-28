package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.openqa.selenium.By;
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
 * @version 1.1 Date: October 5, 2014
 *********************************************/

public class ContentPagination {

	private Driver webDriver;
	private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public ContentPagination(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        waitFor = new WaitFor(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS      
    @FindBy(how = How.XPATH, using = "//div[@class='item-list']")
    private WebElement Pager_Ctr;
    
    private List<WebElement> PageNumber_Lnks() {
    	return webDriver.findElements(By.xpath("//div[@class='item-list']/ul/li"));
    }
    
    @FindBy(how = How.XPATH, using = "//div[@class='item-list']//a[contains(text(), 'next')]")
    private WebElement NextPage_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void VerifyPageCtrPresent() throws Exception {
    	
    	Reporter.log("Verify the pagination links are present.");
    	waitFor.ElementVisible(Pager_Ctr);
    	
    }
    
    public void VerifyCorrectNumberOfPagesDisplayed(Integer numberOfPages) throws Exception{
    
    	Reporter.log("Verify the number of pages equals '" + numberOfPages.toString() + ".");
    	Assert.assertTrue(PageNumber_Lnks().size() == numberOfPages);	 
    
    }
    
    public void ClickNextPageLnk(){
    	
    	Reporter.log("Click the 'next >' Link");
    	NextPage_Lnk.click();
    	
    }
    
}
