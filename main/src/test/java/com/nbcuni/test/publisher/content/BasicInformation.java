package com.nbcuni.test.publisher.content;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Basic Information Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *********************************************/

public class BasicInformation {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String BasicInformation_Tab = "//a/strong[text()='Basic Information']";
    private static String Title_Txb = "//input[contains(@id, 'edit-title')]";
    private static String Synopsis_Frm = "//iframe[@id='edit-body-und-0-value_ifr']";
    private static String Synopsis_Txa = "//body[@id='tinymce']//p";
    private static String CoverSelect_Btn = "//a[contains(@id, 'cover-media-und-0-select')]";
    
    public BasicInformation(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void ClickBasicInformationTab() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(BasicInformation_Tab)));
    	webDriver.click(BasicInformation_Tab);
    }
    
    public void EnterTitle(String movieTitle) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(Title_Txb))));
    	webDriver.type(Title_Txb, movieTitle);
    }
    
    public String EnterSynopsis() throws Exception{

        //switch to the body frame
    	WebElement frm = webDriver.findElement(By.xpath(Synopsis_Frm));
        webDriver.switchTo().frame(frm);

        //Enter a randomized body
        Random random = new Random();
        String body = random.GetCharacterString(20) + " " +
                        random.GetCharacterString(20) + " " +
                            random.GetCharacterString(20) + " " +
                                random.GetCharacterString(20);
        webDriver.click(Synopsis_Txa);
        Actions action = new Actions(webDriver);
        action.sendKeys(body).build().perform();

        return body;
    }
    
    public void ClickCoverSelectBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(CoverSelect_Btn))));
    	webDriver.click(CoverSelect_Btn);
    }
}

