package com.nbcuni.test.publisher.pageobjects.content;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Cast/Crew Library. Copyright
 * 
 * @author Brandon Clark/Faizan Khan
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class CastCrew {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String CastCrew_Lnk = "//a/strong[text()='Cast/Crew']";
    private static String Character_Txb = "//label[contains(text(), 'Character')]/../input";
    private static String AddAnotherItem_Btn="//input[@value= 'Add another item']";
    
    public CastCrew(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void ClickCastCrewLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(CastCrew_Lnk)));
    	webDriver.executeScript("window.scrollBy(0,-50);");
    	webDriver.click(CastCrew_Lnk);
    }
    
    public void ClickAddAnotherItemBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(AddAnotherItem_Btn)));
    	webDriver.click(AddAnotherItem_Btn);
    	
    }
    
    public void SelectRole(String roleName, String index) throws Exception {
    	
    	new Select(new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.
    					xpath("(//select[contains(@id, 'role')])[" + index + "]"))))).
    						selectByVisibleText(roleName);
    }
    
    public void VerifyCharacterTxbNotDisplayed() throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(Character_Txb)).isDisplayed() == false);
    }
    
    public void VerifyCharacterTxbDisplayed() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(Character_Txb))));
    	
    }
    
    public void EnterPersonName(String personName, String index) throws Exception {
    	
    	Thread.sleep(1000); //Stale element exception
    	WebElement txb = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf
   			(webDriver.findElement(By.xpath("(//input[contains(@id, 'person')][1])[" + index + "]")))); 
    	
    	txb.sendKeys(personName);
    	Thread.sleep(2000);
    	txb.sendKeys(Keys.ARROW_DOWN);
    	txb.sendKeys(Keys.ENTER);
    	
    }
    
    public void EnterCharacterName(String characterName, String index) throws Exception {
    	
    	Thread.sleep(1000); //Stale element exception
    	WebElement txb = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf
    			(webDriver.findElement(By.xpath("(//input[contains(@id, 'character')][1])[" + index + "]"))));    	
    	txb.sendKeys(characterName);
    	Thread.sleep(2000);
    	txb.sendKeys(Keys.ARROW_DOWN);
    	txb.sendKeys(Keys.ENTER);
    }
    
    public void VerifyPersonNameValue(String personName, String index) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.
    			xpath("(//input[contains(@id, 'person')][1])[" + index + "]")).
    				getAttribute("value").contains(personName));
    }
    
    public void VerifyCharacterNameValue(String characterName, String index) throws Exception {
    	
    	Assert.assertTrue(webDriver.findElement(By.
    			xpath("(//input[contains(@id, 'character')][1])[" + index + "]")).
    				getAttribute("value").contains(characterName));
    }
    
    public void VerifyRoleValue(String roleName, String index) throws Exception {
    	
    	Select el = new Select(new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.
    					xpath("(//select[contains(@id, 'role')])[" + index + "]")))));
    	Assert.assertEquals(el.getFirstSelectedOption().getText(), roleName);
    	
    						
    }
    
    
}

