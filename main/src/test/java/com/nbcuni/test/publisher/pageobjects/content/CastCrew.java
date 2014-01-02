package com.nbcuni.test.publisher.pageobjects.content;


import org.openqa.selenium.By;
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
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class CastCrew {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String CastCrew_Lnk = "//a/strong[text()='Cast/Crew']";
    private static String Role_Ddl = "//label[contains(text(), 'Role')]/../select";
    private static String Character_Txb = "//label[contains(text(), 'Character')]/../input";
    private static String Character_Ato = "//label[contains(text(), 'Character')]/../input[contains(@id, 'auto')]";
    private static String Person_Txb = "//label[contains(text(), 'Person')]/../input";
    private static String Person_Ato = "//label[contains(text(), 'Person')]/../input[contains(@id, 'auto')]";
    
    public CastCrew(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void ClickCastCrewLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(CastCrew_Lnk)));
    	webDriver.executeScript("window.scrollBy(0,-50);");
    	webDriver.click(CastCrew_Lnk);
    }
    
    public void SelectRole(String roleName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(Role_Ddl))));
    	webDriver.selectFromDropdown(Role_Ddl, roleName);
    }
    
    public void VerifyCharacterTxbNotDisplayed() throws Exception {
    	
    	//TODO - make this a utility method in lib
    	Assert.assertTrue(webDriver.findElement(By.xpath(Character_Txb)).isDisplayed() == false);
    }
    
    public void VerifyCharacterTxbDisplayed() throws Exception {
    	
    	
    	//TODO - make this a utility method in lib
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(Character_Txb))));
    	
    }
    
    public void EnterPersonName(String personName) throws Exception {
    	
    	WebElement txb = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf
    			(webDriver.findElement(By.xpath(Person_Txb))));
    	
    	txb.sendKeys(personName);
    	Thread.sleep(2000);
    	txb.sendKeys(Keys.ARROW_DOWN);
    	txb.sendKeys(Keys.ENTER);
    	
    	//TODO - get this wait working properly
    	//new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(Person_Ato)))).click();
    	//Thread.sleep(2000);
    	//WebElement auto = webDriver.findElement(By.xpath(Person_Ato));
    }
    
    public void EnterCharacterName(String characterName) throws Exception {
    	
    	WebElement txb = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf
    			(webDriver.findElement(By.xpath(Character_Txb))));
    	
    	txb.sendKeys(characterName);
    	Thread.sleep(2000);
    	txb.sendKeys(Keys.ARROW_DOWN);
    	txb.sendKeys(Keys.ENTER);
    	
    	//TODO - get this wait working properly
    	//new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(Character_Ato)))).click();
    	//Thread.sleep(2000);
    	//webDriver.findElement(By.xpath(Character_Ato)).click();
    }
    

    public void VerifyPersonNameValue(String personName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf
    			(webDriver.findElement(By.xpath(Person_Txb))));
    	Assert.assertTrue(webDriver.findElement(By.xpath(Person_Txb)).getAttribute("value").contains(personName));
    }
    
    public void VerifyCharacterNameValue(String characterName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf
    			(webDriver.findElement(By.xpath(Character_Txb))));
    	Assert.assertTrue(webDriver.findElement(By.xpath(Character_Txb)).getAttribute("value").contains(characterName));
    }
    
    public void VerifyRoleValue(String roleName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf
    			(webDriver.findElement(By.xpath(Role_Ddl))));
    	Select el = new Select(webDriver.findElement(By.xpath(Role_Ddl)));
    	Assert.assertEquals(el.getFirstSelectedOption().getText(), roleName);
    }
    
  
}
