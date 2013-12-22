package com.nbcuni.test.publisher.pageobjects.content;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Content Parent Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class ContentParent {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Save_Btn = "//input[@id='edit-submit']";
    private static String Message_Ctr = "//div[@class='messages status']";
    private static String EditDraft_Btn = "//a[text()='Edit Draft']";
    
    public ContentParent(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void ClickSaveBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(Save_Btn)));
    	webDriver.click(Save_Btn);
    }
    
    public void VerifyMessageStatus(String messageStatus){
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.textToBePresentInElement(By.xpath(Message_Ctr)
    					, messageStatus));
    }
    
    public void ClickEditDraftBtn() {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(EditDraft_Btn)));
    	webDriver.click(EditDraft_Btn);
    }
    
    public void VerifyRequiredFields(List<String> allFields) {
    	
    	for (String field : allFields) {
    		webDriver.findElement(By.xpath("//*[contains(text(), '" + field + "')]/span[text()='*']"));
    	}
    }
    
}

