package com.nbcuni.test.publisher.pageobjects.content;

import java.util.List;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
/*********************************************
 * publisher.nbcuni.com Taxonomy Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 10, 2014
 *********************************************/
public class GigyaShareBar {

	private static CustomWebDriver webDriver;
    private static AppLib applib;
    
    //PAGE OBJECT CONSTRUCTORS
    public GigyaShareBar(CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')][text()='Tumblr']")
    private static WebElement Tumblr_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')][text()='Email']")
    private static WebElement Email_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')][text()='Google+']")
    private static WebElement GooglePlus_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')][text()='Foursquare']")
    private static WebElement Foursquare_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')][text()='Print']")
    private static WebElement Print_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')]//iframe[contains(@id, 'twitter')]")
    private static WebElement Twitter_Frm;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')]//iframe[contains(@title, 'Facebook')]")
    private static WebElement Facebook_Frm;
    
  
    //PAGE OBJECT METHODS
    public void VerifyTumblrBtnPresent() throws Exception{
    
    	Reporter.log("Verify the Tumblr gigya share link is present.");
    	Tumblr_Btn.isDisplayed();

    }
    
    public void VerifyEmailBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Email gigya share link is present.");
    	Email_Btn.isDisplayed();

    }

    public void VerifyGooglePlusBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Google Plus gigya share link is present.");
    	GooglePlus_Btn.isDisplayed();

    }
    
    public void VerifyFoursquareBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Foursquare gigya share link is present.");
    	Foursquare_Btn.isDisplayed();

    }
    
    public void VerifyPrintBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Print gigya share link is present.");
    	Print_Btn.isDisplayed();

    }
    
    public void VerifyTwitterBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Twitter gigya share link is present.");
    	Twitter_Frm.isDisplayed();

    }
    
    public void VerifyFacebookBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Facebook gigya share link is present.");
    	Facebook_Frm.isDisplayed();

    }
    
    

}
