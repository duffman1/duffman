package com.nbcuni.test.publisher.pageobjects.ErrorChecking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

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
        PageFactory.initElements(webDriver, this);
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
    public void VerifyErrorMessagePresent(String errorMessage) throws Exception {
    	
    	if (!Error_Ctr.getText().contains(errorMessage)) {
    		Assert.fail("Error message container does not contain error message '" + errorMessage + "'.");
    	}
    }

    public void VerifyAllRequiredFields(List<String> allFieldTitles) throws Exception {
    	
    	for (String field : allFieldTitles) {
    		Assert.assertTrue(Error_Ctr.getText().contains(field + " field is required."));
    	}
    }
    
    public void VerifyMPXPlayerDisabled(String playerTitle) throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	Reporter.log("Verify that the disabled player text is present for player titled '" + playerTitle + "'.");
    	String disabledPlayerTxt = DisabledPlayerError_Ctr(playerTitle).getText();
    	if (!disabledPlayerTxt.contains("An MPXplayer that's in use (" + playerTitle + ") has been disabled in MPX.")) {
    		Assert.fail("Disabled player text not present for player titled '" + playerTitle + "'.");
    	}
    	Assert.assertTrue(disabledPlayerTxt.contains("To change its status in MPX, log into mpx.theplatform"));
    	
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    public void VerifyMPXPlayerDisabledAndUnpublished(String playerTitle) throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	Reporter.log("Verify that the disabled and unpublished player text is present for player titled '" + playerTitle + "'.");
    	String disabledPlayerTxt = DisabledPlayerError_Ctr(playerTitle).getText();
    	if (!disabledPlayerTxt.contains("An MPXplayer that's in use (" + playerTitle + ") has been disabled and unpublished.")) {
    		Assert.fail("Disabled and unpublished player text not present for player titled '" + playerTitle + "'.");
    	}
    	Assert.assertTrue(disabledPlayerTxt.contains("To change its status in Publisher, click here"));
    	Assert.assertTrue(disabledPlayerTxt.contains("To change its status in MPX, log into mpx.theplatform"));
    	
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
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
    		allowedErrors.add("Warning: Attempt to assign property of non-object in EntityAPIController->save()");
    		allowedErrors.add("Notice: Trying to get property of non-object in EntityAPIController->save()");
    		allowedErrors.add("Notice: Undefined offset: 2 in drupal_http_request() (line 1006 of /mnt/www/html/nbcupublisher7qa/docroot/includes/common.inc)");
    		
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
    								 	|| errorText.contains(allowedErrors.get(4))
    								 		|| errorText.contains(allowedErrors.get(5))
    								 			|| errorText.contains(allowedErrors.get(6))
    								 				|| errorText.contains(allowedErrors.get(7))) {
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
    										 	|| error.contains(allowedErrors.get(4))
    										 		|| error.contains(allowedErrors.get(5))
    										 			|| error.contains(allowedErrors.get(6))
    										 				|| error.contains(allowedErrors.get(7))) {
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

