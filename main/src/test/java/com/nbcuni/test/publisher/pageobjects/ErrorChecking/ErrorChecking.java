package com.nbcuni.test.publisher.pageobjects.ErrorChecking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Content Parent Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class ErrorChecking {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    
    //PAGE OBJECT CONSTRUCTOR
    public ErrorChecking(CustomWebDriver webDriver, AppLib applib) {
        ErrorChecking.webDriver = webDriver;
        ErrorChecking.applib = applib;
        
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//div[@class='messages error']")
    private static WebElement Error_Ctr;
    
    @FindBy(how = How.XPATH, using = "//div[@class='messages error']/ul")
    private static WebElement MoreThanOneError_Ctr;
    
    private static WebElement DisabledPlayerError_Ctr(String playerTitle) {
    	return webDriver.findElement(By.xpath("//div[@class='messages error']/ul/li//em[contains(text(), '" + playerTitle + "')]/../.."));
    }
    
    private static List<WebElement> Error_Itms() {
    	return webDriver.findElements(By.xpath("//div[@class='messages error']/ul/li"));
    }
    
    
    //PAGE OBJECT METHODS
    public void VerifyAllRequiredFields(List<String> allFieldTitles) throws Exception {
    	
    	for (String field : allFieldTitles) {
    		Assert.assertTrue(Error_Ctr.getText().contains(field + " field is required."));
    	}
    }
    
    public void VerifyMPXPlayerDisabled(String playerTitle) throws Exception {
    	
    	Assert.assertTrue(DisabledPlayerError_Ctr(playerTitle).getText().contains("An MPXplayer that's in use (" + playerTitle + ") has been disabled in MPX."));
    	Assert.assertTrue(DisabledPlayerError_Ctr(playerTitle).getText().contains("To change its status in MPX, log into mpx.theplatform"));
    }
    
    public void VerifyMPXPlayerDisabledAndUnpublished(String playerTitle) throws Exception {
    	
    	Assert.assertTrue(DisabledPlayerError_Ctr(playerTitle).getText().contains("An MPXplayer that's in use (" + playerTitle + ") has been disabled and unpublished."));
    	Assert.assertTrue(DisabledPlayerError_Ctr(playerTitle).getText().contains("To change its status in Publisher, click here"));
    	Assert.assertTrue(DisabledPlayerError_Ctr(playerTitle).getText().contains("To change its status in MPX, log into mpx.theplatform"));
    }
    
    public void VerifyNoMessageErrorsPresent() throws Exception{
    	
    	if (applib.IsErrorCheckingEnabled() == true) {
    		webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    		//allowed errors
    		List<String> allowedErrors = new ArrayList<String>();
    		allowedErrors.add("There are security updates available for one or more of your modules or themes");
    		allowedErrors.add("An MPXplayer that's in use");
    		allowedErrors.add("There is a security update available for your version of Drupal");
    		allowedErrors.add("Request to retrieve mpx feed data returned a(n) \"com.theplatform.authentication.api.exception.AuthenticationException\" exception.");
    		allowedErrors.add("Expiring mpx token FAILED.");
    		
    		//FIRST - check if error container is present
    		boolean errorContainerPresent = false;
    		try {
    			Error_Ctr.isDisplayed();
    			errorContainerPresent = true;
    		}
    		catch (Exception e) {
    			errorContainerPresent = false;
    		}
    		
    		if (errorContainerPresent == true) {
    			
    			//SECOND - check if there is one error or more than 1
    			boolean moreThan1Error = false;
    			
    			try {
    				MoreThanOneError_Ctr.isDisplayed();
    				moreThan1Error = true;
    			}
    			catch (Exception e) {
    				moreThan1Error = false;
    			}
    			
    			//THIRD - check errors in case of 1 error
    			if (moreThan1Error == false) {
    				
    				//get the text of the error
    				String errorText = Error_Ctr.getText();
    				errorText.replace("Error message", "");
    				//check the error text isn't in list of allowed errors
    				if (errorText.contains(allowedErrors.get(0)) 
    						|| errorText.contains(allowedErrors.get(1))
    							|| errorText.contains(allowedErrors.get(2))
    								|| errorText.contains(allowedErrors.get(3))
    								 	|| errorText.contains(allowedErrors.get(4))) {
    					//ignore error
    				}
    				else {
    					//legit error and fail test
    					Assert.fail("Error text of '" + errorText + "' is present in application.");
    				}
    			}
    			else { //FOURTH - check errors in case of MORE than 1 error
    				
    				//get the error text of every error present
    				List<WebElement> errorItems = Error_Itms();
    				List<String> Errors = new ArrayList<String>();
    				for (WebElement el : errorItems) {
    					Errors.add(el.getText());
    				}
    				
    				//check the error text of each error
    				for (String error : Errors) {
    					if (error.contains(allowedErrors.get(0)) 
    							|| error.contains(allowedErrors.get(1))
    									|| error.contains(allowedErrors.get(2))
    										|| error.contains(allowedErrors.get(3))
    										 	|| error.contains(allowedErrors.get(4))) {
    						//ignore error
    					}
    					else {
    						//legit error and fail test
        					Assert.fail("Error text of '" + error + "' is present in application.");
    					}
    				}
    			}
    			
    		}
    		
    		webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	}
    }
}

