package com.nbcuni.test.publisher.pageobjects.Queues;


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
 * publisher.nbcuni.com Queues Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Queues {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Title_Txb = "//input[contains(@id, 'edit-title')]";
    private static String SaveQueue_Btn = "//input[@value='Save queue']";
    private static String Edit_Tab = "//a[contains(text(), 'Edit')]";
    
    
    public Queues(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void EnterTitle(String queueTitle) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Title_Txb))));
    	webDriver.type(Title_Txb, queueTitle);
    }
    
    public void ClickSaveQueueBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			elementToBeClickable((By.xpath(Title_Txb))));
    	webDriver.click(SaveQueue_Btn);
    }
    
    public void VerifyQueuesInList(List<String> allQueues) throws Exception {
    	
    	for (String queue : allQueues) {
    		webDriver.findElement(By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queue + "')]/.."));
    	}
    	
    }
    
    public void ClickEditQueueExtendMenuBtn(String queueTitle) throws Exception {
    	
    	WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='operations']")));
    	element.click();
    }
    
    public void ClickDeleteQueueMenuBtn(String queueTitle) throws Exception {
    	
    	WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='Delete']")));
    	element.click();
    }
    
    public void ClickEditQueueMenuBtn(String queueTitle) throws Exception {
    	
    	WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueTitle + "')]/..//a[text()='Edit']")));
    	element.click();
    }
    
    public void EnterQueueItem(String queueItemTitle, String itemTxbInt) throws Exception {
    	
    	WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath("(//input[contains(@id, 'edit-field-qt-promo-queue')][1])[" + itemTxbInt + "]"))));
    	element.sendKeys(queueItemTitle);
    }
    
    public void VerifyQueueState(String queueItemTitle, String state) throws Exception {
    	
    	String loc = "//table[contains(@class, 'views-table')]//td[contains(text(), '" + queueItemTitle + "')]/..//td[3]";
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(loc))));
    	ul.verifyObjectContainsText(loc, state);
    }
    
    public void ClickEditTab() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(Edit_Tab)));
    	webDriver.click(Edit_Tab);
    }
    
    
    
    
    
    
  
}

