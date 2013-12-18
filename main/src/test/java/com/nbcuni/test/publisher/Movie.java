package com.nbcuni.test.publisher;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Character Profile Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Movie {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String CreateMovie_Frm = "//iframe[@title='Create Movie dialog']";
    private static String Save_Btn = "//input[@id='edit-submit']";
    private static String Message_Ctr = "//div[@class='messages status']";
    private static String CastCrew_Tab = "//a/strong[text()='Cast/Crew']";
    private static String Role_Ddl = "//select[@id='edit-field-movie-credit-und-0-field-movie-credit-role-und']";
    private static String Character_Txb = "//input[@id='edit-field-movie-credit-und-0-field-movie-credit-character-und-0-target-id']";
    
    public Movie(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void SwitchToCreateMovieFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(CreateMovie_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
    
    public void ClickCastCrewTab() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(CastCrew_Tab)));
    	webDriver.click(CastCrew_Tab);
    }
    
    public void SelectRole(String roleName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementSelectionStateToBe(By.xpath(Role_Ddl), true));
    	webDriver.selectFromDropdown(Role_Ddl, roleName);
    }
    
    public void VerifyCharacterTxbNotDisplayed() throws Exception {
    	
    	//TODO - make this a utility method in lib
    	Assert.assertTrue(webDriver.findElement(By.xpath(Character_Txb)).isDisplayed() == false);
    }
    
    public void VerifyCharacterTxbDisplayed() throws Exception {
    	
    	/*
    	//TODO - make this a utility method in lib
    	Assert.assertTrue(webDriver.findElement(By.xpath(Character_Txb)).isDisplayed() == true);
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.)
    	*/
    }
    
    
    
    
}

