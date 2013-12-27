package com.nbcuni.test.publisher.pageobjects.Facebook;


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
 * publisher.nbcuni.com Drupal For Facebook Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class DrupalForFacebook {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Label_Txb = "//input[@id='edit-label']";
    private static String FacebookAppId_Txb = "//input[@id='edit-id']";
    private static String Secret_Txb = "//input[@id='edit-secret']";
    private static String Save_Btn = "//input[@id='edit-submit']";
    private static String SaveConfiguration_Btn = "//input[@value='Save configuration']";
    private static String PostViaPub7_Lnk = "//a[contains(@href, 'https://www.facebook.com/dialog/oauth')]";
    private static String FacebookEmail_Txb = "//input[@id='email']";
    private static String FacebookPassword_Txb = "//input[@id='pass']";
    private static String FacebookLogin_Btn = "//input[@id='u_0_1']";
    private static String ShowCurrentToken_Lnk = "//a[@class='fieldset-title']";
    private static String Token_Ctr = "(//div[@class='fieldset-description'])[2]";
    private static String PasteAccessToken_Txa = "//textarea[@id='edit-fb-stream-token']";
    
    public DrupalForFacebook(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void EnterLabel(String labelTxt) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(Label_Txb))));
    	el.sendKeys(labelTxt);
    }
    
    public void EnterFacebookAppId(String facebookAppId) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(FacebookAppId_Txb))));
    	el.sendKeys(facebookAppId);
    }
    
    public void EnterSecret(String secret) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(Secret_Txb))));
    	el.sendKeys(secret);
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Save_Btn)));
    	el.click();
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(SaveConfiguration_Btn)));
    	el.click();
    }
    
    public void ClickPostViaPub7Lnk() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(PostViaPub7_Lnk)));
    	el.click();
    }
    
    public void LoginToFacebook(String userName, String passWord) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(FacebookEmail_Txb)))).sendKeys(userName);
    	webDriver.type(FacebookPassword_Txb, passWord);
    	webDriver.click(FacebookLogin_Btn);
    }
    
    public void ClickShowCurrentTokenLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(ShowCurrentToken_Lnk)));
    	webDriver.click(ShowCurrentToken_Lnk);
    }
    
    public void EnterToken() throws Exception {
    	
    	String token = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(Token_Ctr)))).getText();
    	webDriver.type(PasteAccessToken_Txa, token);
    	
    }
  
}

