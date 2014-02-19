package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Execute PHP Code Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 14, 2014
 *********************************************/

public class ExecutePHPCode {

    //PAGE OBJECT CONSTRUCTOR
    public ExecutePHPCode(CustomWebDriver webDriver) {
    	PageFactory.initElements(webDriver, this);
    }

    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-code")
    private static WebElement PHPCodeToExecute_Txa;
    
    @FindBy(how = How.ID, using = "input[value='Execute']")
    private static WebElement Execute_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[@class='messages status']")
    private static WebElement Message_Ctr;
    
    
    //PAGE OBJECT METHODS
    public void EnterPHPCode(String phpCode) throws Exception {
        
    	Reporter.log("Enter '" + phpCode + "' in the 'PHP Code to Execute' text area.");
    	PHPCodeToExecute_Txa.clear();
    	PHPCodeToExecute_Txa.sendKeys(phpCode);
    }
    
    public void ClickExecuteBtn() throws Exception {
    	
    	Reporter.log("Click the 'Execute' button.");
    	Execute_Btn.click();
    }
    
    public String GetPlayerId() throws Exception {
    	
    	Reporter.log("Get the player id from the message container.");
    	String[] playerIDs = Message_Ctr.getText().replace("Status message", "").trim().split("player id: ");
    	
    	String playerID = playerIDs[1];
    	return playerID;
    }
    
}

