package com.nbcuni.test.publisher.pageobjects.queues;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Queues Revision List Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 19, 2013
 *********************************************/

public class QueuesRevisionList {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Confirm_Btn = "";
    private static String Revision_Lnk = "//a[text()='Revisions']";
    private static String StateFlowHistoryEvent_Txt = "//p[@class='state-flow-history-event']";
    private static String Cancel_Lnk = "//a[@id='edit-cancel']";
    
    
    public QueuesRevisionList(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void ClickRevisionsLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(Revision_Lnk)));
    	webDriver.click(Revision_Lnk);
    }
    
    public void VerifyStateFlowHistoryEvent(String messageTxt) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(StateFlowHistoryEvent_Txt)));
    	ul.verifyObjectContainsText(StateFlowHistoryEvent_Txt, messageTxt);
    }
    
    public void ClickCancelLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(Cancel_Lnk)));
    	webDriver.click(Cancel_Lnk);
    }
    
    
  
}

