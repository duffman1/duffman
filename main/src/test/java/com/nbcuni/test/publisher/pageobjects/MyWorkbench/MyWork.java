package com.nbcuni.test.publisher.pageobjects.MyWorkbench;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com My Work Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 11, 2014
 *********************************************/

public class MyWork {

	private static CustomWebDriver webDriver;
	
    //PAGE OBJECT CONSTRUCTOR
    public MyWork(CustomWebDriver webDriver) {
    	MyWork.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='My Work']")
    private static WebElement MyWork_Btn;
    
    private static WebElement Edit_Lnk(String title) {
    	return webDriver.findElement(By.xpath("//a[text()='" + title + "']/../..//a[text()='edit']"));
    }
    
    private static WebElement ContentItem_Ctr(String title) {
    	return webDriver.findElement(By.xpath("//td[contains(@class, 'title')]/a[text()='" + title + "']/../.."));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickMyWorkBtn() throws Exception {
    	
    	Reporter.log("Click the 'My Work' button.");
    	MyWork_Btn.click();
    	
    }
    
    public void ClickEditLnk(String title) throws Exception {
    	
    	Reporter.log("Click the 'edit' link for the content item titled '" + title + "'.");
    	Edit_Lnk(title).click();
    	
    }
    
    public void VerifyContentItemData(List<String> contentData) {
    	
    	String contentItemTxt = ContentItem_Ctr(contentData.get(0)).getText();
		
    	for (String data : contentData) {
    		Reporter.log("Verify that text '" + data + "' is present in the content item titled '" + contentData.get(0) + "'.");
    		Assert.assertTrue(contentItemTxt.contains(data));
    	}
    }
    
}

