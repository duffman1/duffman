package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Media Galleries Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 27, 2014
 *********************************************/

public class MediaGalleries {

	private Driver webDriver;
	private WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public MediaGalleries(Driver webDriver) {
    	this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='Add Media Gallery']")
    private WebElement AddMediaGallery_Lnk;
    
    private WebElement Edit_Lnk(String contentItemTitle) {
    	return webDriver.findElement(By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Edit']"));
    }
    
    private WebElement ExtendEditMenu_Lnk(String contentItemTitle) {
    	return webDriver.findElement(By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='operations']"));
    }
    
    private WebElement EditMenuDelete_Lnk(String contentItemTitle) {
    	return webDriver.findElement(By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Delete']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddMediaGalleryLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add Media Gallery' link.");
    	AddMediaGallery_Lnk.click();
    }
    
    public void ClickEditMenuBtn(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Click the 'Edit' link for content item titled '" + contentItemTitle + ".");
    	Edit_Lnk(contentItemTitle).click();
    }

    public void ClickEditExtendMenuBtn(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Click the extend edit menu link.");
    	wait.until(ExpectedConditions.visibilityOf(ExtendEditMenu_Lnk(contentItemTitle))).click();
    }
    
    public void ClickDeleteMenuBtn(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Click the 'Delete' menu link.");
    	wait.until(ExpectedConditions.visibilityOf(EditMenuDelete_Lnk(contentItemTitle))).click();
    }
}

