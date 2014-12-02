package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Characters Information Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class CharactersInformation {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public CharactersInformation(Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
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
    	interact.Type(waitFor.ElementVisible(CharacterPrefix_Txb), characterPrefix);
    	
    } 
   
    public void EnterCharacterFirstName(String characterFirstName) throws Exception {
    	
    	Reporter.log("Enter '" + characterFirstName + "' in the 'Character First Name' text box.");
    	interact.Type(waitFor.ElementVisible(CharacterFirstName_Txb), characterFirstName);
    	
    }
    
    public void EnterCharacterMiddleName(String characterMiddleName) throws Exception {
    	
    	Reporter.log("Enter '" + characterMiddleName + "' in the 'Character Middle Name' text box.");
    	interact.Type(waitFor.ElementVisible(CharacterMiddleName_Txb), characterMiddleName);
    	
    }
    public void EnterCharacterLastName(String characterLastName) throws Exception {
    	
    	Reporter.log("Enter '" + characterLastName + "' in the 'Character Last Name' text box.");
    	interact.Type(waitFor.ElementVisible(CharacterLastName_Txb), characterLastName);
    	
    }
    
    public void EnterCharacterSuffix(String characterSuffix) throws Exception {
    	
    	Reporter.log("Enter '" + characterSuffix + "' in the 'Character Suffix' text box.");
    	interact.Type(waitFor.ElementVisible(CharacterSuffix_Txb), characterSuffix);
    	
    }
    
}

