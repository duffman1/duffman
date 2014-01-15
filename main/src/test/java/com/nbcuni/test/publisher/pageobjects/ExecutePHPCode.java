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
 * publisher.nbcuni.com Execute PHP Code Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 14, 2014
 *********************************************/

public class ExecutePHPCode {

    private static CustomWebDriver webDriver;
    
    private static String PHPCodeToExecute_Txa = "//textarea[@id='edit-code']";
    private static String Execute_Btn = "//input[@value='Execute']";
    private static String Message_Ctr = "//div[@class='messages status']";
    
    public ExecutePHPCode(final CustomWebDriver custWebDr) {
    	
    	webDriver = custWebDr;
    }
    
    public void EnterPHPCode(String phpCode) throws Exception {
        
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			presenceOfElementLocated(By.xpath(PHPCodeToExecute_Txa)));
    	el.clear();
    	el.sendKeys(phpCode);
    }
    
    public void ClickExecuteBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			presenceOfElementLocated(By.xpath(Execute_Btn))).click();
    }
    
    public String GetPlayerId() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Message_Ctr)));
    	System.out.println(el.getText());
    	String[] playerIDs = el.getText().replace("Status message", "").trim().split("player id: ");
    	
    	String playerID = playerIDs[1];
    	return playerID;
    }
    
    
    
}

