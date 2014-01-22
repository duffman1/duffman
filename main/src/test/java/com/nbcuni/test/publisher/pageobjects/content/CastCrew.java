package com.nbcuni.test.publisher.pageobjects.content;


import java.util.List;

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
    private static String AddAnotherItem_Btn=".//input[@value= 'Add another item']";
    
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
    public void SelectRole(String roleName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(Role_Ddl))));
    	webDriver.selectFromDropdown(Role_Ddl, roleName);
    }
    public void SelectRole(String roleName,Integer RoleboxNum) throws Exception {
    	
    	List<WebElement> Roleboxs = webDriver.findElements(By.xpath(Role_Ddl));
    	try{
	    	//webDriver.selectFromDropdown(Role_Ddl, roleName);
	    	Select select = new Select(Roleboxs.get(RoleboxNum));
	    	select.selectByVisibleText(roleName);
	    	Roleboxs.get(RoleboxNum).sendKeys(Keys.TAB);
    	}catch(Exception e){System.out.println("failled");}
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
    public void EnterPersonName(String personName, Integer PersonNum) throws Exception {
    	
    	List<WebElement> txbs =webDriver.findElements(By.xpath(Person_Txb));
    	
    	txbs.get(PersonNum).sendKeys(personName);
    	Thread.sleep(2000);
    	txbs.get(PersonNum).sendKeys(Keys.ARROW_DOWN);
    	txbs.get(PersonNum).sendKeys(Keys.ENTER);  
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
    public void EnterCharacterName(String characterName, Integer characterBoxNum) throws Exception {
    	
    	List<WebElement> txbs = webDriver.findElements(By.xpath(Character_Txb));
    	txbs.get(characterBoxNum).sendKeys(characterName);
    	Thread.sleep(2000);
    	txbs.get(characterBoxNum).sendKeys(Keys.ARROW_DOWN);
    	txbs.get(characterBoxNum).sendKeys(Keys.ENTER);
    	txbs.get(characterBoxNum).sendKeys(Keys.TAB);
    	txbs.get(characterBoxNum).sendKeys(Keys.TAB);
    	
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
    
    public void VerifyPersonNameValue(String personName,Integer PersonNum) throws Exception {
    	
    	List<WebElement> PersonTxtBox = webDriver.findElements(By.xpath(Person_Txb));
    	Assert.assertTrue(PersonTxtBox.get(PersonNum).getAttribute("value").contains(personName));
    }
    
    public void VerifyCharacterNameValue(String characterName, Integer characterBoxNum) throws Exception {
    	
    	List<WebElement> characterBox=webDriver.findElements(By.xpath(Character_Txb));
    	Assert.assertTrue(characterBox.get(characterBoxNum).getAttribute("value").contains(characterName));
    }
    
    public void VerifyRoleValue(String roleName,Integer RoleboxNum) throws Exception {
    	
    	List<WebElement>  Rolebox=webDriver.findElements(By.xpath(Role_Ddl));
    	Select el = new Select(Rolebox.get(RoleboxNum));
    	Assert.assertEquals(el.getFirstSelectedOption().getText(), roleName);
    }
    
  
}

