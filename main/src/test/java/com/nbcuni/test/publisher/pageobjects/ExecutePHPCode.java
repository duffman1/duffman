package com.nbcuni.test.publisher.pageobjects;


import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Execute PHP Code Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 14, 2014
 *********************************************/

public class ExecutePHPCode {

	WaitFor waitFor;
	
    //PAGE OBJECT CONSTRUCTOR
    public ExecutePHPCode(WebDriver webWebWebDriver) {
    	PageFactory.initElements(webWebWebDriver, this);
    	waitFor = new WaitFor(webWebWebDriver, 10);
    }

    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-code")
    private WebElement PHPCodeToExecute_Txa;
    
    @FindBy(how = How.CSS, using = "input[value='Execute']")
    private WebElement Execute_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[@class='messages status']")
    private WebElement Message_Ctr;
    
    
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
    
    public void VerifyResponse(String expectedResponse) throws Exception {
    	
    	Reporter.log("Verify the expected response is '" + expectedResponse + "'.");
    	waitFor.ElementContainsText(Message_Ctr, expectedResponse);
    }
    
}

