package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Cast/Crew Library. Copyright
 * 
 * @author Brandon Clark/Faizan Khan
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class CastCrew {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public CastCrew(CustomWebDriver webDriver) {
        CastCrew.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Cast/Crew']")
    private static WebElement CastCrew_Lnk;
    
    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Character')]/../input")
    private static WebElement Character_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@value= 'Add another item']")
    private static WebElement AddAnotherItem_Btn;
    
    private static WebElement Role_Ddl(String index) {
    	return webDriver.findElement(By.xpath("(//select[contains(@id, 'role')])[" + index + "]"));
    }
    
    private static WebElement Person_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'person')][1])[" + index + "]"));
    }
    
    private static WebElement Character_Txb(String index) {
    	return webDriver.findElement(By.xpath("(//input[contains(@id, 'character')][1])[" + index + "]"));
    }
    
    private static WebElement AutoComplete_Opt(String optionTxt) {
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
    	
    	Reporter.log("Assert that the 'Character' text box is not displayed.");
    	Assert.assertTrue(Character_Txb.isDisplayed() == false);
    }
    
    public void VerifyCharacterTxbDisplayed() throws Exception {
    	
    	Reporter.log("Assert that the 'Character' text box is displayed.");
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(Character_Txb));
    	
    }
    
    public void EnterPersonName(String personName, String index) throws Exception {
    	
    	Reporter.log("Enter '" + personName + "' in the Person text box.");
    	Person_Txb(index).sendKeys(personName);
    	
    	Reporter.log("Click the '" + personName + "' from the auto complete option list.");
    	AutoComplete_Opt(personName).click();
    	
    }
    
    public void EnterCharacterName(String characterName, String index) throws Exception {
    	
    	Reporter.log("Enter '" + characterName + "' in the 'Character' text box.");
    	Character_Txb(index).sendKeys(characterName);
    	
    	Reporter.log("Click the '" + characterName + "' from the auto complete option list.");
    	webDriver.executeScript("window.scrollBy(0,100);");
    	AutoComplete_Opt(characterName).click();
    	
    }
    
    public void VerifyPersonNameValue(String personName, String index) throws Exception {
    	
    	Reporter.log("Assert the value of the 'Person' text box equals '" + personName + "'.");
    	Assert.assertTrue(Person_Txb(index).getAttribute("value").contains(personName));
    }
    
    public void VerifyCharacterNameValue(String characterName, String index) throws Exception {
    	
    	Reporter.log("Assert the value of the 'Character' text box equals '" + characterName + "'.");
    	Assert.assertTrue(Character_Txb(index).getAttribute("value").contains(characterName));
    }
    
    public void VerifyRoleValue(String roleName, String index) throws Exception {
    	
    	Reporter.log("Assert the value of the 'Role' drop down list equals '" + roleName + "'.");
    	Select el = new Select(Role_Ddl(index));
    	Assert.assertEquals(el.getFirstSelectedOption().getText(), roleName);
    	
    						
    }
    
    
}

