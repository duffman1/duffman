package com.nbcuni.test.publisher.pageobjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Overlay Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Overlay {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    
    //PAGE OBJECT CONSTRUCTOR
    public Overlay(CustomWebDriver webDriver, AppLib applib) {
    	Overlay.webDriver = webDriver;
    	Overlay.applib = applib;
    	PageFactory.initElements(webDriver, this);
    }

    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "overlay-close")
    private static WebElement CloseOverlay_Lnk;
    
    @FindBy(how = How.XPATH, using = "//iframe[@class='overlay-element overlay-active']")
    private static WebElement ActiveFrame_Frm;
    
    private static WebElement Dialog_Frm(String frameTitle) {
    	return webDriver.findElement(By.xpath("//iframe[contains(@title, '" + frameTitle + "')]"));
    }
    
    //PAGE OBJECT METHODS
    public void switchToDefaultContent() throws Exception {
    	
    	webDriver.switchTo().defaultContent();
    }
    
    public void ClickCloseOverlayLnk() throws Exception {
    	
    	Reporter.log("Click the 'Close Overlay X'.");
    	CloseOverlay_Lnk.click();
    	this.switchToDefaultContent();
    }
    
    public void SwitchToFrame(String frameTitle) {
    	
    	Reporter.log("Switch to frame titled '" + frameTitle + "'.");
    	webDriver.switchTo().frame(Dialog_Frm(frameTitle));
    }
    
    public void SwitchToActiveFrame() throws Exception {
    	
    	this.switchToDefaultContent();
    	Reporter.log("Switch to the active frame titled '" + ActiveFrame_Frm.getAttribute("title") + "'.");
    	webDriver.switchTo().frame(ActiveFrame_Frm);
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
            Thread.sleep(500);
        }
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    }
    
    
}

