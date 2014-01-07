package com.nbcuni.test.publisher.pageobjects.content;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Revisions State Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Revisions {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    private static String RevComment = ".//*[@id='edit-event-comment']";
    private static String RevUpdate_btn=".//*[@id='edit-submit']";
    private static String RevisionLink=".//a[contains(text(),'Revisions')]";
    
    public Revisions(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void ClickEditExtendMenuBtn(String contentItemTitle) throws Exception {
    	
    	WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTitle + "')]/../..//a[text()='operations']")));
    	element.click();
    }
    
    public void ClickEditMenuBtn(String contentItemTitle) throws Exception {
    	
    	WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTitle + "')]/../..//a[text()='Edit']")));
    	element.click();
    }

    public void ClickShareMenuBtn(String contentItemTitle) throws Exception {
    	
    	WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTitle + "')]/../..//a[text()='Share']")));
    	element.click();
    }
    
    public void VerifyContentItemEditDelete(String contentItemTitle) throws Exception {
    	
    	WebElement expandBtn = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTitle + "')]/../..//a[text()='operations']")));
    	expandBtn.click();
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(
    			"//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTitle + "')]/../..//a[text()='Edit']"))));
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(
    			"//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTitle + "')]/../..//a[text()='Delete']"))));
    	
    }

    public void VerifyContentItemEditDeleteNotPresent(String contentItemTitle) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTitle + "')]")));
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.not(ExpectedConditions.
    			presenceOfElementLocated((By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTitle + "')]/../..//a[text()='operations']")))));
    	
    }
    public void PopulateRevisionComment(String commenttext) throws Exception {
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(RevComment))));
    	webDriver.type(RevComment,commenttext );   	
    	
    }
    public void ClickUpdateStateBtn() throws Exception {    	
    	WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(RevUpdate_btn)));
    	element.click();
    }
    public void ClickRevisionTab() throws Exception{
    	WebElement revision = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(RevisionLink)));
    	revision.click();    	
    }
    
    

}

