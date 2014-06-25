package com.nbcuni.test.publisher.pageobjects.MPX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com MPXDataClient Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 29, 2013
 *********************************************/

public class MPXDataClient {

    private Driver webDriver;
    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public MPXDataClient(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 30);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "user")
    private WebElement User_Txb;
    
    @FindBy(how = How.ID, using = "password")
    private WebElement Password_Txb;
    
    @FindBy(how = How.ID, using = "submitAuth-button")
    private WebElement SignIn_Btn;
    
    @FindBy(how = How.ID, using = "accountPickerButton-button")
    private WebElement AccountPicker_Btn;
    
    private List<WebElement> Account_Ctr(){
    	return webDriver.findElements(By.xpath("//ul[@class='first-of-type hastitle']//a"));
    }
    
    @FindBy(how = How.ID, using = "object")
    private WebElement Object_Ddl;
    
    @FindBy(how = How.ID, using = "getSparseFields")
    private WebElement Fields_Txb;
    
    @FindBy(how = How.ID, using = "rangeStart")
    private WebElement StartRange_Txb;
    
    @FindBy(how = How.ID, using = "rangeEnd")
    private WebElement EndRange_Txb;
    
    @FindBy(how = How.ID, using = "response")
    private WebElement Response_Pre;
    
    @FindBy(how = How.ID, using = "submit-button")
    private WebElement Submit_Btn;
    
    
    //PAGE OBJECT METHODS
    public void OpenMPXDataClient(String clientType) throws Exception {
    	
    	Reporter.log("Open the mpx data client at http://data." + clientType + ".theplatform.com/" + clientType + "/client.");
    	webDriver.navigate().to("http://data." + clientType + ".theplatform.com/" + clientType + "/client");
    	wait.until(ExpectedConditions.titleIs("Data Service Web Client Login"));
    }
    
    public void EnterUser(String userName) throws Exception {
    	
    	Reporter.log("Enter the Username in the 'Username' text box.");
    	User_Txb.sendKeys(userName);
    }
    
    public void EnterPassword(String passWord) throws Exception {
    	
    	Reporter.log("Enter the Password in the 'Password' text box.");
    	Password_Txb.sendKeys(passWord);
    }
    
    public void ClickSignInBtn() throws Exception {
    	
    	Reporter.log("Click the 'Sign In' button.");
    	SignIn_Btn.click();
    }
    
    public void SignInToMPXDataClient(String clientType, String userName, String passWord) throws Exception {
    	
    	this.OpenMPXDataClient(clientType);
    	this.EnterUser(userName);
    	this.EnterPassword(passWord);
    	this.ClickSignInBtn();
    	wait.until(ExpectedConditions.titleContains("Data Service Web Client"));
    }
    
    public List<String> GetAllMPXAccounts() throws Exception {
    	
    	Reporter.log("Click the Account Picker button to display all mpx accounts.");
    	wait.until(ExpectedConditions.visibilityOf(AccountPicker_Btn)).click();
    	
    	Reporter.log("Store all the active mpx accounts to a list.");
    	List<WebElement> allAccountLnks = Account_Ctr();
    	
    	List<String> accountNames = new ArrayList<String>();
    	for (WebElement el : allAccountLnks) {
    		
    		accountNames.add(el.getText());
    	}
    	
    	return accountNames;
    }
    
    public void ChooseMPXAccount(String accountName) throws Exception {
    	
    	Reporter.log("Click the Account Picker button.");
    	AccountPicker_Btn.click();
    	
    	List<WebElement> allAccountLnks = Account_Ctr();
    	
    	for (WebElement el : allAccountLnks) {
    		
    		if (el.getText().equals(accountName)) {
    			Reporter.log("Click the '" + accountName + "' account check box.");
    			el.click();
    		}
    		
    	}
    	
    }
    
    public List<String> GetAllMPXObjectFields(String mpxObject, String field, String startRange, String endRange) throws Exception {
    	
    	Reporter.log("Select the '" + mpxObject + "' from the 'Object' drop down list.");
    	new Select(Object_Ddl).selectByVisibleText(mpxObject);
    	
    	Reporter.log("Enter '" + field + "' in the 'Field' text box.");
    	Fields_Txb.sendKeys(field);
    	
    	Reporter.log("Enter '" + startRange + " - " + endRange + "' in the 'Range' text boxes.");
    	StartRange_Txb.clear();
    	StartRange_Txb.sendKeys(startRange);
    	EndRange_Txb.clear();
    	EndRange_Txb.sendKeys(endRange);
    	
    	Reporter.log("Click the 'Submit' button.");
    	Submit_Btn.click();
    	
    	for (int second = 0; ; second++){
            if (second >= 60) {
                Assert.fail("MPX Data client result set not present after 30 seconds.");}
            try {
                Response_Pre.isDisplayed();
                if (Response_Pre.getText().contains("startIndex")) {break;}
            }
            catch (NoSuchElementException e) {}
            Thread.sleep(1000);
        }
    	
    	Reporter.log("Store the result set to a list.");
    	String result = Response_Pre.getText();
    	String[] resultSet;
    	if (mpxObject == "Player") {
    		
    		result = result.replace("<entry>", "");
    		result = result.replace("</entry>", "");
    		result = result.replace("</" + field + ">",  "");
    		resultSet = result.split("<" + field + ">");
    	}
    	else {
    		
    		result = result.replace("\"", "");
    		result = result.replace("{",  "");
    		result = result.replace("}",  "");
    		result = result.replace(",", "");
    		resultSet = result.split(field + ":");
    		
    	}
    	
    	String[] trimmedResultSet = new String[resultSet.length];
    	for (int i = 0; i < resultSet.length; i++)
    	    trimmedResultSet[i] = resultSet[i].trim();
    	
    	List<String> finalResultSet = Arrays.asList(trimmedResultSet);
    	
    	try {
    		finalResultSet.remove(0); 
    	}
    	catch (UnsupportedOperationException e) {
    		
    		finalResultSet = new ArrayList<String>(finalResultSet);
            finalResultSet.remove(0);
    	}
    	
    	return finalResultSet;
    }
    
    public List<String> GetAllActivePlayers() throws Exception {
    	
    	Reporter.log("Select 'Player' from the 'Object' drop down list.");
    	new Select(Object_Ddl).selectByVisibleText("Player");
    	
    	Reporter.log("Enter 'title, disabled' in the 'Fields' text box.");
    	Fields_Txb.sendKeys("title, disabled");
    	
    	Reporter.log("Click the 'Submit' button.");
    	Submit_Btn.click();
    	
    	for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("MPX Data client result set not present after 30 seconds.");}
            try {
                Response_Pre.isDisplayed();
                if (Response_Pre.getText().contains("startIndex")) {break;}
            }
            catch (NoSuchElementException e) {}
            Thread.sleep(1000);
        }
    	
    	Reporter.log("Store the result set to a list.");
    	String result = Response_Pre.getText();
    	
    	List<String> AllResults = Arrays.asList(result.split("<entry>"));
    	String AllResultString = null;
    	
    	for (String s : AllResults) {
    		if (s.contains("false")) {
    			AllResultString = AllResultString + s;
    		}
    	}
    	
    	String[] resultSet;
    	result = AllResultString;
    	result = result.replace("<plplayer:disabled>false</plplayer:disabled>", "");
    	result = result.replace("</entry>", "");
    	result = result.replace("</title>",  "");
    	result = result.replace("</feed>", "");
    	resultSet = result.split("<title>");
    	
    	String[] trimmedResultSet = new String[resultSet.length];
    	for (int i = 0; i < resultSet.length; i++)
    	    trimmedResultSet[i] = resultSet[i].trim();
    	
    	List<String> finalResultSet = Arrays.asList(trimmedResultSet);
    	
    	try {
    		finalResultSet.remove(0); 
    	}
    	catch (UnsupportedOperationException e) {
    		
    		finalResultSet = new ArrayList<String>(finalResultSet);
            finalResultSet.remove(0);
    	}
    	
    	return finalResultSet;
    }
    
    public List<String> GetAllPlayers() throws Exception {
    	
    	Reporter.log("Select 'Player' from the 'Object' drop down list.");
    	new Select(Object_Ddl).selectByVisibleText("Player");
    	
    	Reporter.log("Enter 'title' in the 'Fields' text box.");
    	Fields_Txb.sendKeys("title");
    	
    	Reporter.log("Click the 'Submit' button.");
    	Submit_Btn.click();
    	
    	for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("MPX Data client result set not present after 30 seconds.");}
            try {
                Response_Pre.isDisplayed();
                if (Response_Pre.getText().contains("startIndex")) {break;}
            }
            catch (NoSuchElementException e) {}
            Thread.sleep(1000);
        }
    	
    	Reporter.log("Store the result set to a list.");
    	String result = Response_Pre.getText();
    	
    	String[] resultSet;
    	result = result.replace("<entry>", "");
    	result = result.replace("</entry>", "");
    	result = result.replace("</title>",  "");
    	resultSet = result.split("<title>");
    	
    	String[] trimmedResultSet = new String[resultSet.length];
    	for (int i = 0; i < resultSet.length; i++)
    	    trimmedResultSet[i] = resultSet[i].trim();
    	
    	List<String> finalResultSet = Arrays.asList(trimmedResultSet);
    	
    	try {
    		finalResultSet.remove(0); 
    	}
    	catch (UnsupportedOperationException e) {
    		
    		finalResultSet = new ArrayList<String>(finalResultSet);
            finalResultSet.remove(0);
    	}
    	
    	return finalResultSet;
    }
    
}

