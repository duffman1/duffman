package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Site Catalyst Variables Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 18, 2014
 *********************************************/

public class SiteCatalystVariables {

    private Driver webDriver;
    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public SiteCatalystVariables(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='SiteCatalyst Variables']")
    private WebElement SiteCatalystVariables_Tab;
    
    private WebElement VariableValue_Txb(String variableName) {
    	return webDriver.findElement(By.xpath("//input[@value='" + variableName + "']/../../..//textarea[contains(@id, 'value')]"));
    }
    
    @FindBy(how = How.ID, using = "edit-add-another-variable")
    private WebElement AddAnotherVariable_Btn;
    
    @FindBy(how = How.XPATH, using = "(//input[contains(@id, 'name')][@value=''])[1]")
    private WebElement BlankVariableName_Txb;
    
    @FindBy(how = How.XPATH, using = "(//input[contains(@id, 'name')][@value='']/../../..//textarea[contains(@id, 'value')])[1]")
    private WebElement BlankVariableValue_Txb;
    
    
    //PAGE OBJECT METHODS
    public void ClickSiteCatalystVariablesTab() throws Exception {
    	
    	Reporter.log("Click the 'SiteCatalyst Variables' tab.");
    	SiteCatalystVariables_Tab.click();
    }
    
    public void EnterVariableValue(String variableName, String variableValue) throws Exception {
    	
    	Reporter.log("Enter '" + variableValue + "' in the 'VALUE' text box for the '" + variableName + " variable'.");
    	VariableValue_Txb(variableName).clear();
    	VariableValue_Txb(variableName).sendKeys(variableValue);
    }
    
    public void ClickAddAnotherVariableBtn() throws Exception {
    	
    	Reporter.log("Click the 'Add another variable' button.");
    	AddAnotherVariable_Btn.click();
    }
    
    public void EnterBlankVariableName(String variableName) throws Exception {
    	
    	Reporter.log("Enter '" + variableName + "' in the first blank 'Name' text box.");
    	wait.until(ExpectedConditions.visibilityOf(BlankVariableName_Txb)).sendKeys(variableName);
    }
    
    public void EnterBlankVariableValue(String variableValue) throws Exception {
    	
    	Reporter.log("Enter '" + variableValue + "' in the first blank 'Value' text box.");
    	BlankVariableValue_Txb.sendKeys(variableValue);
    }
    
}

