package com.nbcuni.test.publisher.pageobjects.MPX;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com MPXDataClient Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 29, 2013
 *********************************************/

public class MPXDataClient {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String User_Txb = "//input[@id='user']";
    private static String Password_Txb = "//input[@id='password']";
    private static String SignIn_Btn = "//button[@id='submitAuth-button']";
    private static String AccountPicker_Btn = "//button[@id='accountPickerButton-button']";
    private static String Account_Ctr = "//ul[@class='first-of-type hastitle']//a";
    private static String Object_Ddl = "//select[@id='object']";
    private static String Fields_Txb = "//input[@id='getSparseFields']";
    private static String Response_Pre = "//pre[@id='response']";
    private static String Submit_Btn = "//button[@id='submit-button']";
    
    public MPXDataClient(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void OpenMPXDataClient(String clientType) throws Exception {
    	
    	webDriver.navigate().to("http://data." + clientType + ".theplatform.com/" + clientType + "/client");
    	new WebDriverWait(webDriver, 30).until(ExpectedConditions.titleIs("Data Service Web Client Login"));
    }
    
    public void EnterUser(String userName) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(User_Txb))));
    	el.sendKeys(userName);
    }
    
    public void EnterPassword(String passWord) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Password_Txb))));
    	el.sendKeys(passWord);
    }
    
    public void ClickSignInBtn() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(SignIn_Btn))));
    	el.click();
    }
    
    public void SignInToMPXDataClient(String clientType, String userName, String passWord) throws Exception {
    	
    	this.OpenMPXDataClient(clientType);
    	this.EnterUser(userName);
    	this.EnterPassword(passWord);
    	this.ClickSignInBtn();
    	new WebDriverWait(webDriver, 30).until(ExpectedConditions.titleContains("Data Service Web Client"));
    }
    
    public List<String> GetAllMPXAccounts() throws Exception {
    	
    	new WebDriverWait(webDriver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AccountPicker_Btn))).click();
    	
    	List<WebElement> allAccountLnks = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOfAllElementsLocatedBy(By.xpath(Account_Ctr)));
    	
    	List<String> accountNames = new ArrayList<String>();
    	for (WebElement el : allAccountLnks) {
    		
    		accountNames.add(el.getText());
    	}
    	
    	return accountNames;
    }
    
    public void ChooseMPXAccount(String accountName) throws Exception {
    	
    	webDriver.click(AccountPicker_Btn);
    	
    	List<WebElement> allAccountLnks = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOfAllElementsLocatedBy(By.xpath(Account_Ctr)));
    	
    	for (WebElement el : allAccountLnks) {
    		
    		if (el.getText().contains(accountName)) {
    			el.click();
    		}
    		
    	}
    	
    }
    
    public List<String> GetAllMPXObjectFields(String mpxObject, String field) throws Exception {
    	
    	//TODO - refactor to be more efficient
    	
    	webDriver.selectFromDropdown(Object_Ddl, mpxObject);
    	webDriver.type(Fields_Txb, field);
    	webDriver.click(Submit_Btn);
    	
    	new WebDriverWait(webDriver, 45).until(ExpectedConditions.textToBePresentInElement(By.xpath(Response_Pre), "startIndex"));
    	
    	String result = webDriver.findElement(By.xpath(Response_Pre)).getText();
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
    	
    	//TODO - refactor to be more efficient
    	webDriver.selectFromDropdown(Object_Ddl, "Player");
    	webDriver.type(Fields_Txb, "title, disabled");
    	webDriver.click(Submit_Btn);
    	
    	new WebDriverWait(webDriver, 45).until(ExpectedConditions.textToBePresentInElement(By.xpath(Response_Pre), "startIndex"));
    	
    	String result = webDriver.findElement(By.xpath(Response_Pre)).getText();
    	
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
    	
    	//TODO - refactor to be more efficient
    	webDriver.selectFromDropdown(Object_Ddl, "Player");
    	webDriver.type(Fields_Txb, "title");
    	webDriver.click(Submit_Btn);
    	
    	new WebDriverWait(webDriver, 45).until(ExpectedConditions.textToBePresentInElement(By.xpath(Response_Pre), "startIndex"));
    	
    	String result = webDriver.findElement(By.xpath(Response_Pre)).getText();
    	
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

