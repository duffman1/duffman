package com.nbcuni.test.publisher.pageobjects.content;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
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
 * publisher.nbcuni.com Characters Information Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class CharactersInformation {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String CharacterFirstName_Txb = "//input[@id='edit-field-character-first-name-und-0-value']";
    private static String CharacterPrefix_Txb = ".//input[@id='edit-field-character-prefix-und-0-value']";
    
    private static String CharacterMiddleName_Txb = ".//input[@id='edit-field-character-middle-name-und-0-value']";
    private static String CharacterLastName_Txb = ".//input[@id='edit-field-character-last-name-und-0-value']";
 
    
    private static String CharacterSuffix_Txb = ".//input[@id='edit-field-character-suffix-und-0-value']";
    private static String CoverPhotoSelect_Btn = "//a[@id='edit-field-character-cover-photo-und-0-select']";
    private static String Profile_Img = "//div[@class='media-thumbnail']/img";
    
    public CharactersInformation(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void EnterCharacterPrefix(String PrefixSTR) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(CharacterPrefix_Txb)));
    	
    	webDriver.type(CharacterPrefix_Txb, PrefixSTR);
    } 
   
    public void EnterCharacterFirstName(String characterName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(CharacterFirstName_Txb)));
    	
    	webDriver.type(CharacterFirstName_Txb, characterName);
    }
    
    public void EnterCharacterMiddleName(String MiddleNameSTR) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(CharacterMiddleName_Txb)));
    	
    	webDriver.type(CharacterMiddleName_Txb, MiddleNameSTR);
    }
    public void EnterCharacterLastName(String LastNameSTR) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(CharacterLastName_Txb)));
    	
    	webDriver.type(CharacterLastName_Txb, LastNameSTR);
    }
    
    public void EnterCharacterSuffix(String SuffixSTR) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(CharacterSuffix_Txb)));
    	
    	webDriver.type(CharacterSuffix_Txb, SuffixSTR);
    }
    
    public void ClickAddPhotoSelectBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(CoverPhotoSelect_Btn)));
    	
    	webDriver.click(CoverPhotoSelect_Btn);
    }
    
    public void VerifyDefaultImagePresent(String imageSrc) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(Profile_Img)));
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(Profile_Img)).getAttribute("src").contains(imageSrc));
    	
    	webDriver.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth " +
    			"!= \"undefined\" && arguments[0].naturalWidth > 0", Profile_Img);
    }
    

    public void PopulateCharacterInformations(ArrayList InfoArray, Integer PNum) throws Exception{    	 	
    	EnterCharacterSuffix((String)InfoArray.get(0)+ PNum);
    	EnterCharacterFirstName((String)InfoArray.get(1)+ PNum);
    	EnterCharacterMiddleName((String)InfoArray.get(2)+ PNum);
    	EnterCharacterLastName((String)InfoArray.get(3)+ PNum);
    	EnterCharacterSuffix((String)InfoArray.get(4)+ PNum);
    
    }
    
  
}

