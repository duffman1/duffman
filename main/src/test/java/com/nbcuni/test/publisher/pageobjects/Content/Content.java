package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.util.concurrent.TimeUnit;

/*********************************************
 * publisher.nbcuni.com Content Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Content {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    private static WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public Content(CustomWebDriver webDriver, AppLib applib) {
        Content.webDriver = webDriver;
        Content.applib = applib;
        wait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }
   
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.LINK_TEXT, using = "Add file")
    private static WebElement AddFile_Lnk;
    
    private WebElement ContentItem_Lnk(String contentItemTitle) {
    	return webDriver.findElement(By.xpath("//a[text()='" + contentItemTitle + "']"));
    }
    
    private WebElement Edit_Lnk(String contentItemTitle) {
    	return webDriver.findElement(By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Edit']"));
    }
    
    private WebElement ExtendEditMenu_Lnk(String contentItemTitle) {
    	return webDriver.findElement(By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='operations']"));
    }
    
    private WebElement EditMenuEdit_Lnk(String contentItemTitle) {
    	return webDriver.findElement(By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Edit']"));
    }
    
    private WebElement EditMenuDelete_Lnk(String contentItemTitle) {
    	return webDriver.findElement(By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Delete']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void VerifyContentItemEditDelete(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Click the extend arrow for the 'Edit' menu.");
    	this.ClickEditExtendMenuBtn(contentItemTitle);
    	
    	Reporter.log("Verify the 'Edit' menu link is present from the extended edit menu.");
    	EditMenuEdit_Lnk(contentItemTitle).isDisplayed();
    	
    	Reporter.log("Verify the 'Delete' menu link is present from the extended edit menu.");
    	EditMenuDelete_Lnk(contentItemTitle).isDisplayed();
    	
    }
    
    public void VerifyContentItemEditDeleteNotPresent(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Wait for the content item titled '" + contentItemTitle + "' is present.");
    	ContentItem_Lnk(contentItemTitle).isDisplayed();

        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean elPresent = false;

        Reporter.log("Verify the 'Edit/Delete' menu options are not present for the content item titled '" + contentItemTitle + "'.");
        try {
            ExtendEditMenu_Lnk(contentItemTitle).isDisplayed();
            elPresent = true;
        }
        catch (Exception e) {
            elPresent = false;
        }

        Assert.assertFalse(elPresent);

        webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    }
    
    public void ClickEditMenuBtn(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Click the 'Edit' link for content item titled '" + contentItemTitle + ".");
    	Edit_Lnk(contentItemTitle).click();
    }

    public void ClickEditExtendMenuBtn(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Click the extend edit menu link.");
    	ExtendEditMenu_Lnk(contentItemTitle).click();
    }
    
    public void ClickDeleteMenuBtn(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Click the 'Delete' menu link.");
    	wait.until(ExpectedConditions.visibilityOf(EditMenuDelete_Lnk(contentItemTitle))).click();
    }
    
    public void VerifyContentItemNotPresent(String contentItemTitle) throws Exception {
    	
    	Reporter.log("Verify the content item titled '" + contentItemTitle + "' is not present.");
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	boolean elPresent = true;
    	try {
    		ContentItem_Lnk(contentItemTitle).isDisplayed();
    		elPresent = true;
    	}
    	catch (Exception e) {
    	
    		elPresent = false;
    	}
    	if (elPresent == true) {
    		Assert.fail("Content item is present when it should not be");
    	}
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    }
    
    public void ClickAddFileLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add file' link.");
    	AddFile_Lnk.click();
    }
    
}
