package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Soft Length Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 12, 2014
 *********************************************/

public class SoftLength {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    
    //PAGE OBJECT CONSTRUCTOR
    public SoftLength(CustomWebDriver webDriver, AppLib applib) {
        SoftLength.webDriver = webDriver;
        SoftLength.applib = applib;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private static WebElement SoftLengthUnderLimit_Ctr(String characterTxt) {
    	return webDriver.findElement(By.xpath("//div[contains(@class,'under-min')][text()='" + characterTxt + "']"));
    }
    
    private static WebElement SoftLengthMeetsLimit_Ctr(String characterTxt) {
    	return webDriver.findElement(By.xpath("//div[@class='soft-length-limit-tooltip description min-style-tooltip'][text()='" + characterTxt + "']"));
    }
    
    private static WebElement SoftLengthExceedsLimit_Ctr(String characterTxt) {
    	return webDriver.findElement(By.xpath("//div[@class='soft-length-limit-tooltip description min-style-tooltip exceeded'][text()='" + characterTxt + "']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void VerifySoftLengthUnderLimit(String characterTxt) throws Exception {
    	
    	Reporter.log("Verify the soft length under limit text of '" + characterTxt + "' is present.");
    	Assert.assertTrue(SoftLengthUnderLimit_Ctr(characterTxt).isDisplayed());
    }
    
    public void VerifySoftLengthMeetsLimit(String characterTxt) throws Exception {
    	
    	Reporter.log("Verify the soft length meets limit text of '" + characterTxt + "' is present.");
    	Assert.assertTrue(SoftLengthMeetsLimit_Ctr(characterTxt).isDisplayed());
    }
    
    public void VerifySoftLengthExceedsLimit(String characterTxt) throws Exception {
    	
    	Reporter.log("Verify the soft length exceeds limit text of '" + characterTxt + "' is present.");
    	Assert.assertTrue(SoftLengthExceedsLimit_Ctr(characterTxt).isDisplayed());
    }
    
	public void VerifySoftLengthUnderLimitNotPresent(String characterTxt) throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean elPresent = false;

        try {
        	Reporter.log("Verify the soft length under limit text of '" + characterTxt + "' is NOT present.");
        	SoftLengthUnderLimit_Ctr(characterTxt).isDisplayed();
            elPresent = true;
        }
        catch (Exception e) {
            elPresent = false;
        }

        Assert.assertFalse(elPresent);

        webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    public void VerifySoftLengthMeetsLimitNotPresent(String characterTxt) throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean elPresent = false;

        try {
        	Reporter.log("Verify the soft length meets limit text of '" + characterTxt + "' is NOT present.");
        	SoftLengthMeetsLimit_Ctr(characterTxt).isDisplayed();
            elPresent = true;
        }
        catch (Exception e) {
            elPresent = false;
        }

        Assert.assertFalse(elPresent);

        webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    public void VerifySoftLengthExceedsLimitNotPresent(String characterTxt) throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean elPresent = false;

        try {
        	Reporter.log("Verify the soft length exceeds limit text of '" + characterTxt + "' is NOT present.");
        	SoftLengthExceedsLimit_Ctr(characterTxt).isDisplayed();
            elPresent = true;
        }
        catch (Exception e) {
            elPresent = false;
        }

        Assert.assertFalse(elPresent);

        webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    
}
