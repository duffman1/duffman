package com.nbcuni.test.publisher.pageobjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
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
    
    private static String CloseOverlay_Lnk = "//a[@id='overlay-close']";
    private static String ActiveFrame_Frm = "//iframe[@class='overlay-element overlay-active']";
    
    
    public Overlay(final CustomWebDriver custWebDr) {
    	
    	webDriver = custWebDr;
    }
    
    public void switchToDefaultContent() throws Exception {
        
    	webDriver.switchTo().defaultContent();
    }
    
    public void ClickCloseOverlayLnk() throws Exception {
    	
    	webDriver.click(CloseOverlay_Lnk);
    }
    
    public void SwitchToFrame(String frameTitle) {
    	
    	WebElement frm = webDriver.findElement(By.xpath("//iframe[contains(@title, '" + frameTitle + "')]"));
    	webDriver.switchTo().frame(frm);
    	
    }
    
    public void SwitchToFrameByIndex(String frameTitle, Integer frameIndex) {
    	
    	List<WebElement> allFrames = webDriver.findElements(By.xpath("//iframe[contains(@title, '" + frameTitle + "')]"));
    	webDriver.switchTo().frame(allFrames.get(frameIndex));
    	
    }
    
    public void SwitchToActiveFrame() throws Exception {
    	
    	this.switchToDefaultContent();
    	webDriver.switchTo().frame(webDriver.findElement(By.xpath(ActiveFrame_Frm)));
    }
    
    
}

