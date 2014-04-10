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
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Publisher Ads Side Files Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/

public class PublisherAdsSideFiles {

	private Driver webDriver;
	private AppLib applib;
	private WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public PublisherAdsSideFiles(Driver webDriver, AppLib applib) {
    	this.webDriver = webDriver;
    	this.applib = applib;
    	PageFactory.initElements(webDriver, this);
    	wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.LINK_TEXT, using = "Add")
    private WebElement Add_Lnk;
    
    private WebElement AdSideFile_Lnk(String linkPath) {
    	return webDriver.findElement(By.xpath("//a[text()='" + linkPath + "']"));
    }
    
    private WebElement AdSideFileEdit_Lnk(String adName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + adName + "']/..//a[text()='Edit']"));
    }
    
    private WebElement AdSideFileName_Txt(String adName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + adName + "']"));
    }
    
    private WebElement AdSideFileStorage_Txt(String adName, String storageType) {
    	return webDriver.findElement(By.xpath("//td[text()='" + adName + "']/../td[contains(@class, 'storage')][text()='" + storageType + "']"));
    }
    
    private WebElement AdSideFileExpandEdit_Lnk(String adName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + adName + "']/..//a[text()='open']"));
    }
    
    private WebElement AdSideFileClone_Lnk(String adName) {
    	return webDriver.findElement(By.xpath("//td[text()='" + adName + "']/..//a[text()='Clone']"));
    }
    
    private WebElement AdSideFileDelete_Lnk(String adName) {
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
    	Thread.sleep(1000); //pause required
    }
    
    public void ClickAdSideFileCloneLnk(String adName) throws Exception {
        
    	Reporter.log("Click the Ad Side File 'Clone' link for adName + '" + adName + "'.");
    	wait.until(ExpectedConditions.visibilityOf(AdSideFileClone_Lnk(adName)));
    	Thread.sleep(500); //pause required here
    	AdSideFileClone_Lnk(adName).click();
    }
    
    public void ClickAdSideFileDeleteLnk(String adName) throws Exception {
        
    	Reporter.log("Click the Ad Side File 'Delete' link for adName + '" + adName + "'.");
    	wait.until(ExpectedConditions.visibilityOf(AdSideFileDelete_Lnk(adName)));
    	Thread.sleep(500); //pause required here
    	AdSideFileDelete_Lnk(adName).click();
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
