package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Cast/Crew Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: April 16, 2014
 *********************************************/

public class CastCrew {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    private Driver webDriver;

    //PAGE OBJECT CONSTRUCTOR
    public CastCrew(Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
        this.webDriver = webDriver;
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By CastCrew_Lnk = By.xpath("//a/strong[text()='Cast/Crew']");
    
    private By Character_Txb = By.xpath("//label[contains(text(), 'Character')]/../input");
    
    private By AddAnotherItem_Btn = By.xpath("//input[@value= 'Add another item']");
    
    private By Role_Ddl(String index) {
    	return By.xpath("(//select[contains(@id, 'role')])[" + index + "]");
    }
    
    private By Person_Txb(String index) {
    	return By.xpath("(//input[contains(@id, 'person')][1])[" + index + "]");
    }
    
    private By Character_Txb(String index) {
    	return By.xpath("(//input[contains(@id, 'character')][1])[" + index + "]");
    }
    
    private By AutoComplete_Opt(String optionTxt) {
    	return By.xpath("//div[@class='reference-autocomplete'][text()='" + optionTxt + "']");
    }
    
    //PAGE OBJECT METHODS
    public void ClickCastCrewLnk() throws Exception {
    	
    	Reporter.log("Click the 'Cast/Crew' link.");
    	interact.ScrollToTop();
//    	interact.Click(waitFor.ElementVisible(CastCrew_Lnk));
        WebElement webElement = webDriver.findElement(CastCrew_Lnk);
    	webDriver.executeScript("arguments[0].click()", webElement);
    }
    
    public void ClickAddAnotherItemBtn() throws Exception {
    	
    	Reporter.log("Click the 'Add Another Item' button.");
    	interact.Click(waitFor.ElementVisible(AddAnotherItem_Btn));
    	
    }
    
    public void SelectRole(String roleName, String index) throws Exception {
    	
    	Reporter.log("Select the '" + roleName + "' from the 'Role' drop down list.");
    	interact.Select(waitFor.ElementVisible(Role_Ddl(index)), roleName);
    	
    }
    
    public void VerifyCharacterTxbNotDisplayed() throws Exception {
    	
    	Reporter.log("Verify that the 'Character' text box is not displayed.");
    	waitFor.ElementNotVisible(Character_Txb);
    	
    }
    
    public void VerifyCharacterTxbDisplayed() throws Exception {
    	
    	Reporter.log("Verify that the 'Character' text box is displayed.");
    	waitFor.ElementVisible(Character_Txb);
    	
    }
    
    public void EnterPersonName(String personName, String index) throws Exception {
    	
    	Reporter.log("Enter '" + personName + "' in the Person text box.");
    	WebElement ele = waitFor.ElementVisible(Person_Txb(index));
    	ele.sendKeys(personName);
    	
    	Reporter.log("Click the '" + personName + "' from the auto complete option list.");
    	waitFor.ElementVisible(AutoComplete_Opt(personName));
    	ele.sendKeys(Keys.DOWN);
    	ele.sendKeys(Keys.ENTER);
    	
    	waitFor.ElementNotPresent(AutoComplete_Opt(personName));
    	
    }
    
    public void EnterCharacterName(String characterName, String index) throws Exception {
    	
    	Reporter.log("Enter '" + characterName + "' in the 'Character' text box.");
    	WebElement ele = waitFor.ElementVisible(Character_Txb(index));
    	ele.sendKeys(characterName);
    	
    	Reporter.log("Click the '" + characterName + "' from the auto complete option list.");
    	interact.Scroll("100");
    	waitFor.ElementVisible(AutoComplete_Opt(characterName));
    	
    	ele.sendKeys(Keys.DOWN);
    	ele.sendKeys(Keys.ENTER);
    	
    	waitFor.ElementNotPresent(AutoComplete_Opt(characterName));
    	
    }
    
    public void VerifyPersonNameValue(String personName, String index) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Person' text box equals '" + personName + "'.");
    	Assert.assertTrue(waitFor.ElementVisible(Person_Txb(index)).getAttribute("value").contains(personName));
    	
    }
    
    public void VerifyCharacterNameValue(String characterName, String index) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Character' text box equals '" + characterName + "'.");
    	Assert.assertTrue(waitFor.ElementVisible(Character_Txb(index)).getAttribute("value").contains(characterName));
    	
    }
    
    public void VerifyRoleValue(String roleName, String index) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Role' drop down list equals '" + roleName + "'.");
    	Select ele = new Select(waitFor.ElementVisible(Role_Ddl(index)));
    	Assert.assertEquals(ele.getFirstSelectedOption().getText(), roleName);
    				
    }
    
    
}

