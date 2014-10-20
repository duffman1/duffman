package com.nbcuni.test.publisher.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;

/*********************************************
 * publisher.nbcuni.com Overlay Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Overlay {

    private Driver webDriver;
    private Config config;
    private ErrorChecking errorChecking;
    private ContentParent contentParent;
    
    //PAGE OBJECT CONSTRUCTOR
    public Overlay(Driver webDriver) {
    	this.webDriver = webDriver;
    	config = new Config();
    	PageFactory.initElements(webDriver, this);
    	errorChecking = new ErrorChecking(webDriver);
    	contentParent = new ContentParent(webDriver);
    }

    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "overlay-close")
    private WebElement CloseOverlay_Lnk;
    
    @FindBy(how = How.XPATH, using = "//iframe[@class='overlay-element overlay-active']")
    private WebElement ActiveFrame_Frm;
    
    @FindBy(how = How.XPATH, using = "//h1[contains(text(), 'Loading')]")
    private WebElement Loading_Ttl;
    
    private WebElement Dialog_Frm(String frameTitle) {
    	return webDriver.findElement(By.xpath("//iframe[contains(@title, '" + frameTitle + "')]"));
    }
    
    private WebElement Overlay_Tab(String tabTxt) {
    	return webDriver.findElement(By.xpath("//ul[@id='overlay-tabs']//a[text()='" + tabTxt + "']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void switchToDefaultContent(boolean checkForErrors) throws Exception {
    	
    	webDriver.switchTo().defaultContent();
    	
    	if (checkForErrors == true) {
    		errorChecking.VerifyNoMessageErrorsPresent();
    	}
    	
    }
    
    public void ClickCloseOverlayLnk() throws Exception {
    	
    	Reporter.log("Click the 'Close Overlay X'.");
    	Thread.sleep(1000);
    	webDriver.executeScript("arguments[0].click();", CloseOverlay_Lnk);
    	this.switchToDefaultContent(true);
    }
    
    public void ClickOverlayTab(String tabTxt) throws Exception {
    	
    	Reporter.log("Click the '" + tabTxt + "' overlay tab.");
    	contentParent.Scroll("-500");
    	try {
    		Overlay_Tab(tabTxt).click();
    	}
    	catch (WebDriverException e) {
    		webDriver.executeScript("arguments[0].click();", Overlay_Tab(tabTxt));
    	}
    }
    
    public void SwitchToFrame(String frameTitle) throws Exception {
    	
    	Reporter.log("Switch to frame titled '" + frameTitle + "'.");
    	Thread.sleep(250); //slight pause to help ensure frame switch occurs to a new frame and not the old.
    	webDriver.switchTo().frame(Dialog_Frm(frameTitle));
    }
    
    public void SwitchToActiveFrame() throws Exception {
    	
    	//wait for frame titled 'loading' to not be present
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	boolean loadingFramePresent = false;

    	for (int second = 0; ; second++){
            if (second >= 120) {
                Assert.fail("Frame titled 'Loading' is still present after timeout");}
            try{
            	Loading_Ttl.isDisplayed();
                loadingFramePresent = true;
            }
            catch (Exception e){
            	loadingFramePresent = false;
            }
            if (loadingFramePresent == false){ break;}
            Thread.sleep(500);
        }
    	
    	//longer timeout for overlay loads
    	webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    	
    	this.switchToDefaultContent(false);
    	Thread.sleep(500); //slight pause to help ensure frame switch occurs to a new frame and not the old.
    	Reporter.log("Switch to the active frame titled '" + ActiveFrame_Frm.getAttribute("title") + "'.");
    	webDriver.switchTo().frame(ActiveFrame_Frm);
    	
    	//default timeout
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
    	//check for errors in overlay
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void WaitForFrameNotPresent(String frameTitle) throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	boolean framePresent = false;

    	Reporter.log("Wait for the frame titled '" + frameTitle + "' to close.");
    	for (int second = 0; ; second++){
            if (second >= 120) {
                Assert.fail("Frame titled '" + frameTitle + "' is still present after timeout");}
            try{
            	Dialog_Frm(frameTitle).isDisplayed();
                framePresent = true;
            }
            catch (Exception e){
            	framePresent = false;
            }
            if (framePresent == false){ break;}
            Thread.sleep(1000);
        }
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
    }
    
    
}

