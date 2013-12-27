package com.nbcuni.test.publisher.pageobjects.content;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com PersonsInformation Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class PersonsInformation {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String FirstName_Txb = "//input[@id='edit-field-person-first-name-und-0-value']";
    private static String Biography_Frm = "//iframe[@id='edit-body-und-0-value_ifr']";
    private static String Biography_Txa = "//body[@id='tinymce']";
    private static String CoverPhotoSelect_Btn = "//a[@id='edit-field-person-cover-photo-und-0-select']";
    
    public PersonsInformation(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
public void EnterFirstName(String firstName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(FirstName_Txb)));
    	webDriver.type(FirstName_Txb, firstName);
    }
    
    public String EnterBiography() throws Exception{

        //switch to the body frame
    	WebElement frm = webDriver.findElement(By.xpath(Biography_Frm));
        webDriver.switchTo().frame(frm);

        //Enter a randomized body
        Random random = new Random();
        String body = random.GetCharacterString(20) + " " +
                        random.GetCharacterString(20) + " " +
                            random.GetCharacterString(20) + " " +
                                random.GetCharacterString(20);
        webDriver.click(Biography_Txa);
        webDriver.type(Biography_Txa, body);
        
        return body;
    }
    
    public void ClickCoverPhotoSelectBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(CoverPhotoSelect_Btn)));
    	webDriver.click(CoverPhotoSelect_Btn);
    }
    
    
    
}

