package com.nbcuni.test.publisher.pageobjects.content;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.AppLib;
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
    private static String CoverPhotoSelect_Btn = "//a[@id='edit-field-character-cover-photo-und-0-select']";
    private static String Profile_Img = "//div[@class='media-thumbnail']/img";
    
    public CharactersInformation(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void EnterCharacterFirstName(String characterName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(CharacterFirstName_Txb)));
    	
    	webDriver.type(CharacterFirstName_Txb, characterName);
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
    
    
    
    
  
}

