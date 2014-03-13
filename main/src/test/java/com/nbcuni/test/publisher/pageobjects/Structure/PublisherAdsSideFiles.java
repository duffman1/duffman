package com.nbcuni.test.publisher.pageobjects.Structure;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
* publisher.nbcuni.com Publisher Ads Side Files Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/

public class PublisherAdsSideFiles {

	private static CustomWebDriver webDriver;
	private static AppLib applib;
	private static WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public PublisherAdsSideFiles(CustomWebDriver webDriver, AppLib applib) {
    	PublisherAdsSideFiles.webDriver = webDriver;
    	PublisherAdsSideFiles.applib = applib;
    	PageFactory.initElements(webDriver, this);
    	wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.LINK_TEXT, using = "Add")
    private static WebElement Add_Lnk;
    
    private static WebElement AdSideFile_Lnk(String linkPath) {
    	return webDriver.findElement(By.xpath("//a[text()='" + linkPath + "']"));
    }
    
    private static WebElement AdSideFileEdit_Lnk(String adName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + adName + "']/..//a[text()='Edit']"));
    }
    
    private static WebElement AdSideFileName_Txt(String adName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + adName + "']"));
    }
    
    private static WebElement AdSideFileStorage_Txt(String adName, String storageType) {
    	return webDriver.findElement(By.xpath("//td[text()='" + adName + "']/../td[contains(@class, 'storage')][text()='" + storageType + "']"));
    }
    
    private static WebElement AdSideFileExpandEdit_Lnk(String adName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + adName + "']/..//a[text()='open']"));
    }
    
    private static WebElement AdSideFileClone_Lnk(String adName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + adName + "']/..//a[text()='Clone']"));
    }
    
    private static WebElement AdSideFileDelete_Lnk(String adName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + adName + "']/..//a[text()='Delete']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddLnk() throws Exception {
    
    	Reporter.log("Click the 'Add' link.");
    	Add_Lnk.click();
    }
    
    public void ClickAdSideFileLnk(String path) throws Exception {
        
    	Reporter.log("Click the Ad Side File link with path '" + path + "'.");
    	AdSideFile_Lnk(path).click();
    }
    
    public void ClickAdSideFileEditLnk(String adName) throws Exception {
        
    	Reporter.log("Click the Ad Side File 'Edit' link for adName + '" + adName + "'.");
    	AdSideFileEdit_Lnk(adName).click();
    }
    
    public void VerifyAdSideFileStorageType(String adName, String storageType) throws Exception {
        
    	Reporter.log("Verify storage type for Ad Side File '" + adName + "' is set to '" + storageType + "'.");
    	AdSideFileStorage_Txt(adName, storageType).isDisplayed();
    }
    
    public void ClickAdSideFileExpandEditLnk(String adName) throws Exception {
        
    	Reporter.log("Click the Ad Side File expand link for adName + '" + adName + "'.");
    	AdSideFileExpandEdit_Lnk(adName).click();
    }
    
    public void ClickAdSideFileCloneLnk(String adName) throws Exception {
        
    	Reporter.log("Click the Ad Side File 'Clone' link for adName + '" + adName + "'.");
    	wait.until(ExpectedConditions.visibilityOf(AdSideFileClone_Lnk(adName))).click();
    }
    
    public void ClickAdSideFileDeleteLnk(String adName) throws Exception {
        
    	Reporter.log("Click the Ad Side File 'Delete' link for adName + '" + adName + "'.");
    	wait.until(ExpectedConditions.visibilityOf(AdSideFileDelete_Lnk(adName))).click();
    }
    
    public void VerifyAdSideFileNotPresent(String adName) throws Exception {
        
    	Reporter.log("Verify Ad Side File '" + adName + "' is NOT present.");
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	boolean elPresent = true;
    	try {
    		
    		AdSideFileName_Txt(adName).isDisplayed();
    		elPresent = true;
    	}
    	catch (Exception e) {
    	
    		elPresent = false;
    	}
    	if (elPresent == true) {
    		Assert.fail("Ad side file '" + adName + "' is present when it should not be");
    	}
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
}
