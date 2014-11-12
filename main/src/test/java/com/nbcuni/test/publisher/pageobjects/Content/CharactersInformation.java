package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Characters Information Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class CharactersInformation {

	private Config config;
	private WaitFor waitFor;
	
    //PAGE OBJECT CONSTRUCTOR
    public CharactersInformation(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
        config = new Config();
        waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    }
   
    //PAGE OBJECT IDENTIFIERS
    private By CharacterFirstName_Txb = By.id("edit-field-character-first-name-und-0-value");
    
    private By CharacterPrefix_Txb = By.id("edit-field-character-prefix-und-0-value");
    
    private By CharacterMiddleName_Txb = By.id("edit-field-character-middle-name-und-0-value");
    
    private By CharacterLastName_Txb = By.id("edit-field-character-last-name-und-0-value");
    
    private By CharacterSuffix_Txb = By.id("edit-field-character-suffix-und-0-value");
    
    
    //PAGE OBJECT METHODS
    public void EnterCharacterPrefix(String characterPrefix) throws Exception {
    	
    	Reporter.log("Enter '" + characterPrefix + "' in the 'Character Prefix' text box.");
    	waitFor.ElementVisible(CharacterPrefix_Txb).sendKeys(characterPrefix);
    	
    } 
   
    public void EnterCharacterFirstName(String characterFirstName) throws Exception {
    	
    	Reporter.log("Enter '" + characterFirstName + "' in the 'Character First Name' text box.");
    	WebElement ele = waitFor.ElementVisible(CharacterFirstName_Txb);
    	ele.clear();
    	ele.sendKeys(characterFirstName);
    	
    }
    
    public void EnterCharacterMiddleName(String characterMiddleName) throws Exception {
    	
    	Reporter.log("Enter '" + characterMiddleName + "' in the 'Character Middle Name' text box.");
    	waitFor.ElementVisible(CharacterMiddleName_Txb).sendKeys(characterMiddleName);
    	
    }
    public void EnterCharacterLastName(String characterLastName) throws Exception {
    	
    	Reporter.log("Enter '" + characterLastName + "' in the 'Character Last Name' text box.");
    	waitFor.ElementVisible(CharacterLastName_Txb).sendKeys(characterLastName);
    	
    }
    
    public void EnterCharacterSuffix(String characterSuffix) throws Exception {
    	
    	Reporter.log("Enter '" + characterSuffix + "' in the 'Character Suffix' text box.");
    	waitFor.ElementVisible(CharacterSuffix_Txb).sendKeys(characterSuffix);
    	
    }
    
}

