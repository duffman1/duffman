package com.nbcuni.test.publisher.pageobjects.People;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Add User Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class AddUser {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Username_Txb = "//input[@id='edit-name']";
    private static String EmailAddress_Txb = "//input[@id='edit-mail']";
    private static String Password_Txb = "//input[@id='edit-pass-pass1']";
    private static String ConfirmPassword_Txb = "//input[@id='edit-pass-pass2']";
    private static String Role_Editor_Cbx = "//input[@id='edit-roles-4']";
    private static String FirstName_Txb = "//input[@id='edit-field-first-name-und-0-value']";
    private static String LastName_Txb = "//input[@id='edit-field-last-name-und-0-value']";
    private static String CreateNewAccount_Btn = "//input[@value='Create new account']";
    
    public AddUser(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void EnterUsername(String userName) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Username_Txb))));
    	el.sendKeys(userName);
    }
    
    public void EnterEmailAddress(String emailAddress) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(EmailAddress_Txb))));
    	el.sendKeys(emailAddress);
    }
    
    public void EnterPassword(String passWord) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Password_Txb))));
    	el.sendKeys(passWord);
    }
    
    public void EnterConfirmPassword(String passWord) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(ConfirmPassword_Txb))));
    	el.sendKeys(passWord);
    }
    
    public void ClickEditorRoleCbx() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Role_Editor_Cbx)));
    	el.click();
    }
    
    public void EnterFirstName(String firstName) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(FirstName_Txb))));
    	el.sendKeys(firstName);
    }
    
    public void EnterLastName(String lastName) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(LastName_Txb))));
    	el.sendKeys(lastName);
    }
    
    public void ClickCreateNewAccountBtn() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(CreateNewAccount_Btn)));
    	el.click();
    }
    
    
    
    
    
    
    
   
  
}

