package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Cast/Crew Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: April 16, 2014
 *********************************************/

public class CastCrew {

    private Driver webDriver;
    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public CastCrew(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        waitFor = new WaitFor(webDriver, 10);
        
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Cast/Crew']")
    private WebElement CastCrew_Lnk;
    
    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Character')]/../input")
    private WebElement Character_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@value= 'Add another item']")
    private WebElement AddAnotherItem_Btn;
    
    private WebElement Role_Ddl(String index) {
    	return webDriver.findElement(By.xpath("(//select[contains(@id, 'role')])[" + index + "]"));
    }
    
    private WebElement Person_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'person')][1])[" + index + "]"));
    }
    
    private WebElement Character_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'character')][1])[" + index + "]"));
    }
    
    private WebElement AutoComplete_Opt(String optionTxt) {
    	return webDriver.findElement(By.xpath("//div[@class='reference-autocomplete'][text()='" + optionTxt + "']"));
    }
    
    //PAGE OBJECT METHODS
    public void ClickCastCrewLnk() throws Exception {
    	
    	Reporter.log("Click the 'Cast/Crew' link.");
    	webDriver.executeScript("window.scrollBy(0,-500);");
    	CastCrew_Lnk.click();
    }
    
    public void ClickAddAnotherItemBtn() throws Exception {
    	
    	Reporter.log("Click the 'Add Another Item' button.");
    	AddAnotherItem_Btn.click();
    	
    }
    
    public void SelectRole(String roleName, String index) throws Exception {
    	
    	Reporter.log("Select the '" + roleName + "' from the 'Role' drop down list.");
    	new Select(Role_Ddl(index)).selectByVisibleText(roleName);
    }
    
    public void VerifyCharacterTxbNotDisplayed() throws Exception {
    	
    	Reporter.log("Verify that the 'Character' text box is not displayed.");
    	Assert.assertTrue(Character_Txb.isDisplayed() == false);
    }
    
    public void VerifyCharacterTxbDisplayed() throws Exception {
    	
    	Reporter.log("Verify that the 'Character' text box is displayed.");
    	waitFor.ElementVisible(Character_Txb);
    	
    }
    
    public void EnterPersonName(String personName, String index) throws Exception {
    	
    	Reporter.log("Enter '" + personName + "' in the Person text box.");
    	Person_Txb(index).sendKeys(personName);
    	
    	Reporter.log("Click the '" + personName + "' from the auto complete option list.");
    	waitFor.ElementVisible(AutoComplete_Opt(personName));
    	Person_Txb(index).sendKeys(Keys.DOWN);
    	Person_Txb(index).sendKeys(Keys.ENTER);
    	
    	waitFor.ElementNotPresent(AutoComplete_Opt(personName));
    	
    }
    
    public void EnterCharacterName(String characterName, String index) throws Exception {
    	
    	Reporter.log("Enter '" + characterName + "' in the 'Character' text box.");
    	Character_Txb(index).sendKeys(characterName);
    	
    	Reporter.log("Click the '" + characterName + "' from the auto complete option list.");
    	webDriver.executeScript("window.scrollBy(0,100);");
    	waitFor.ElementVisible(AutoComplete_Opt(characterName));
    	
    	Character_Txb(index).sendKeys(Keys.DOWN);
    	Character_Txb(index).sendKeys(Keys.ENTER);
    	waitFor.ElementNotPresent(AutoComplete_Opt(characterName));
    	
    }
    
    public void VerifyPersonNameValue(String personName, String index) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Person' text box equals '" + personName + "'.");
    	Assert.assertTrue(Person_Txb(index).getAttribute("value").contains(personName));
    }
    
    public void VerifyCharacterNameValue(String characterName, String index) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Character' text box equals '" + characterName + "'.");
    	Assert.assertTrue(Character_Txb(index).getAttribute("value").contains(characterName));
    }
    
    public void VerifyRoleValue(String roleName, String index) throws Exception {
    	
    	Reporter.log("Verify the value of the 'Role' drop down list equals '" + roleName + "'.");
    	Select el = new Select(Role_Ddl(index));
    	Assert.assertEquals(el.getFirstSelectedOption().getText(), roleName);
    	
    						
    }
    
    
}

