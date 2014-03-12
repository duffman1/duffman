package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Characters Information Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class CharactersInformation {

    //PAGE OBJECT CONSTRUCTOR
    public CharactersInformation(CustomWebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
   
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-field-character-first-name-und-0-value")
    private static WebElement CharacterFirstName_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-character-prefix-und-0-value")
    private static WebElement CharacterPrefix_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-character-middle-name-und-0-value")
    private static WebElement CharacterMiddleName_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-character-last-name-und-0-value")
    private static WebElement CharacterLastName_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-character-suffix-und-0-value")
    private static WebElement CharacterSuffix_Txb;
    
    
    //PAGE OBJECT METHODS
    public void EnterCharacterPrefix(String characterPrefix) throws Exception {
    	
    	Reporter.log("Enter '" + characterPrefix + "' in the 'Character Prefix' text box.");
    	CharacterPrefix_Txb.sendKeys(characterPrefix);
    } 
   
    public void EnterCharacterFirstName(String characterFirstName) throws Exception {
    	
    	Reporter.log("Enter '" + characterFirstName + "' in the 'Character First Name' text box.");
    	CharacterFirstName_Txb.clear();
    	CharacterFirstName_Txb.sendKeys(characterFirstName);
    }
    
    public void EnterCharacterMiddleName(String characterMiddleName) throws Exception {
    	
    	Reporter.log("Enter '" + characterMiddleName + "' in the 'Character Middle Name' text box.");
    	CharacterMiddleName_Txb.sendKeys(characterMiddleName);
    }
    public void EnterCharacterLastName(String characterLastName) throws Exception {
    	
    	Reporter.log("Enter '" + characterLastName + "' in the 'Character Last Name' text box.");
    	CharacterLastName_Txb.sendKeys(characterLastName);
    }
    
    public void EnterCharacterSuffix(String characterSuffix) throws Exception {
    	
    	Reporter.log("Enter '" + characterSuffix + "' in the 'Character Suffix' text box.");
    	CharacterSuffix_Txb.sendKeys(characterSuffix);
    }
    
}

