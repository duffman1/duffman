package com.nbcuni.test.publisher.pageobjects.errorchecking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
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
    
    private static String Error_Ctr = "//div[@class='messages error']";
    
    
    public ErrorChecking(CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        
    }
    
    public void VerifyAllRequiredFields(List<String> allFieldTitles) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath(Error_Ctr)));
    	
    	for (String field : allFieldTitles) {
    		Assert.assertTrue(el.getText().contains(field + " field is required."));
    	}
    }
    
    public void VerifyMPXPlayerDisabled(String playerTitle) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath(Error_Ctr + "/ul/li//em[contains(text(), '" + playerTitle + "')]/../..")));
    	
    	Assert.assertTrue(el.getText().contains("An MPXplayer that's in use (" + playerTitle + ") has been disabled in MPX."));
    	Assert.assertTrue(el.getText().contains("To change its status in MPX, log into mpx.theplatform"));
    }
    
    public void VerifyMPXPlayerDisabledAndUnpublished(String playerTitle) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath(Error_Ctr + "/ul/li//em[contains(text(), '" + playerTitle + "')]/../..")));
    	
    	Assert.assertTrue(el.getText().contains("An MPXplayer that's in use (" + playerTitle + ") has been disabled and unpublished."));
    	Assert.assertTrue(el.getText().contains("To change its status in Publisher, click here"));
    	Assert.assertTrue(el.getText().contains("To change its status in MPX, log into mpx.theplatform"));
    }
    
    public void VerifyNoMessageErrorsPresent(){
    	
    	if (applib.IsErrorCheckingEnabled() == true) {
    		webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	
    		//allowed errors
    		List<String> allowedErrors = new ArrayList<String>();
    		allowedErrors.add("There are security updates available for");
    		allowedErrors.add("An MPXplayer that's in use");
    		
    		
    		//FIRST - check if error container is present
    		boolean errorContainerPresent = false;
    		try {
    			webDriver.findElement(By.xpath("//div[@class='messages error']"));
    			errorContainerPresent = true;
    		}
    		catch (Exception e) {
    			errorContainerPresent = false;
    		}
    		
    		if (errorContainerPresent == true) {
    			
    			//SECOND - check if there is one error or more than 1
    			boolean moreThan1Error = false;
    			
    			try {
    				webDriver.findElement(By.xpath("//div[@class='messages error']/ul"));
    				moreThan1Error = true;
    			}
    			catch (Exception e) {
    				moreThan1Error = false;
    			}
    			
    			//THIRD - check errors in case of 1 error
    			if (moreThan1Error == false) {
    				
    				//get the text of the error
    				String errorText = webDriver.findElement(By.xpath("//div[@class='messages error']")).getText();
    				errorText.replace("Error message", "");
    				//check the error text isn't in list of allowed errors
    				if (errorText.contains("There are security updates available for") 
    						|| errorText.contains("An MPXplayer that's in use")) {
    					//ignore error
    					
    				}
    				else {
    					//legit error and fail test
    					Assert.fail("Error text of '" + errorText + "' is present in application.");
    				}
    			}
    			else { //FOURTH - check errors in case of MORE than 1 error
    				
    				//get the error text of every error present
    				List<WebElement> errorItems = webDriver.findElements(By.xpath("//div[@class='messages error']/ul/li"));
    				List<String> Errors = new ArrayList<String>();
    				for (WebElement el : errorItems) {
    					Errors.add(el.getText());
    				}
    				
    				//check the error text of each error
    				for (String error : Errors) {
    					if (error.contains("There are security updates available for") 
    							|| error.contains("An MPXplayer that's in use")) {
    						//ignore error
    					}
    					else {
    						//legit error and fail test
        					Assert.fail("Error text of '" + error + "' is present in application.");
    					}
    				}
    			}
    			
    		}
    		
    		
    		/*
    		boolean errorsPresent = false;
    		try {
    	
    			WebElement el = webDriver.findElement(By.xpath(Error_Ctr));
    		
    			String errorText = el.getText();
    			errorText = errorText.replace("Error message", "");
    			errorText = errorText.replace("There are security updates available for one or more of your modules or themes. To ensure the security of your server, you should update immediately! See the available updates page for more information.", "");
    			System.out.println(errorText);
    			if (errorText.length() > 0) {
    				errorsPresent = true;
    			}
    		}
    		catch (Exception e) {
    			errorsPresent = false;
    		}
    	
    		if (errorsPresent == true) {
    			Assert.fail("Error message are present!");
    		}*/
    	
    		webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	}
    }
}

