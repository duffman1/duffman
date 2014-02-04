package com.nbcuni.test.publisher.pageobjects.content;


import java.util.concurrent.TimeUnit;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/*********************************************
 * publisher.nbcuni.com Revisions State Library. Copyright
 * 
 * @author Brandon Clark/Faizan Khan
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Revisions {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    private static String RevComment = ".//*[@id='edit-event-comment']";
    private static String RevUpdate_btn=".//*[@id='edit-submit']";
    private static String RevisionLink=".//a[contains(text(),'Revisions')]";
    private static String MessageForStateChange_Txa = "(//*[@id='edit-event-comment'])[1]";
    private static String DeleteRev_Btn=".//*[@id='edit-submit']";
    public Revisions(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void ClickEditExtendMenuBtn(String contentItemTitle) throws Exception {
    	
    	WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTitle + "')]/../..//a[text()='operations']")));
    	element.click();
    }
    
    public void ClickEditMenuDeleteBtn(String contentItemTitle) throws Exception {
    	
    	WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTitle + "')]/../..//a[text()='Delete']")));
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
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	boolean elPresent = true;
    	try {
    		webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//a[contains(text(), '" + contentItemTitle + "')]/../..//a[text()='operations']"));
    		elPresent = true;
    	}
    	catch (Exception e) {
    	
    		elPresent = false;
    	}
    	if (elPresent == true) {
    		Assert.fail("Content item Edit/Delete button present when it should not be");
    	}
    	webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

    public void EnterLogMessageForStateChange(String message) throws Exception {
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
            webDriver.findElement(By.xpath(MessageForStateChange_Txa)))).sendKeys(message);

    }
    
    public void ClickDeleteConfirmBtn() throws Exception {
    	
    	WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(DeleteRev_Btn)));
    	element.click();
    }
    
    
    
}

