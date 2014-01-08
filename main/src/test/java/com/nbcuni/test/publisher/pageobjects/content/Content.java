package com.nbcuni.test.publisher.pageobjects.content;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


/*********************************************
 * publisher.nbcuni.com Content Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Content {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    private static String Operation_Dd = ".//*[@id='edit-operation']";
        
    public Content(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void VerifyContentItemEditDelete(String contentItemTitle) throws Exception {
    	
    	this.ClickEditExtendMenuBtn(contentItemTitle);
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Edit']"))));
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Delete']"))));
    	
    }
    
    public void VerifyContentItemEditDeleteNotPresent(String contentItemTitle) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//a[text()='" + contentItemTitle + "']")));

        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean elPresent = false;

        try {
            webDriver.findElement(By.xpath("//a[text()='\" + contentItemTitle + \"']/../..//a[text()='operations']"));
            elPresent = true;
        }
        catch (Exception e) {
            elPresent = false;
        }

        Assert.assertFalse(elPresent);

        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    }
    
    public void ClickEditExtendMenuBtn(String contentItemTitle) throws Exception {
    	
    	WebElement expandBtn = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='operations']")));
    	expandBtn.click();
    }
    
    public void ClickDeleteMenuBtn(String contentItemTitle) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//a[text()='" + contentItemTitle + "']/../..//a[text()='Delete']")));
    	el.click();
    }
    
    public void VerifyContentItemNotPresent(String contentItemTitle) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.not(ExpectedConditions.
    			presenceOfElementLocated((By.xpath("//a[text()='" + contentItemTitle + "']")))));
    	
    }
    public void SelectCheckBoxOfTheContent(String contentItemTitle) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//a[text()='" + contentItemTitle + "']/../..//input[@type='checkbox']")));
    	el.click();
    }
    public void SelectModerationState(String OperationName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Operation_Dd))));
    	webDriver.selectFromDropdown(Operation_Dd, OperationName);
    }
    public void ClickTheContentFromContentpage(String contentItemTitle) throws Exception {
    	
    	WebElement contentName = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//a[text()='" + contentItemTitle + "']")));
    	contentName.click();
    }
    
}

