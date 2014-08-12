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
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Content Parent Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class ErrorChecking {

    private Driver webDriver;
    private AppLib applib;
    
    //PAGE OBJECT CONSTRUCTOR
    public ErrorChecking(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        this.applib = applib;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//div[@class='messages error']")
    private WebElement Error_Ctr;
    
    @FindBy(how = How.XPATH, using = "//div[@class='messages error']/ul")
    private WebElement MoreThanOneError_Ctr;
    
    private WebElement DisabledPlayerError_Ctr(String playerTitle) {
    	return webDriver.findElement(By.xpath("//div[@class='messages error']/ul/li//em[contains(text(), '" + playerTitle + "')]/../.."));
    }
    
    private List<WebElement> Error_Itms() {
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
    	
    		//ALLOWED ERRORS
    		List<String> allowedErrors = new ArrayList<String>();
    		allowedErrors.add("There are security updates available for one or more of your modules or themes");
    		allowedErrors.add("An MPXplayer that's in use");
    		allowedErrors.add("To change its status in MPX, log into mpx.theplatform");
    		allowedErrors.add("To change its status in Publisher, click here");
    		allowedErrors.add("There is a security update available for your version of Drupal");
    		allowedErrors.add("A side file with this name already exists.");
    		allowedErrors.add("This revision cannot be deleted because it is currently the primary revision for this queue");
    		allowedErrors.add("Your sitemap is up to date and does not need to be rebuilt");
    		
    		//random error on cron run
    		allowedErrors.add("Warning: Attempt to assign property of non-object in EntityAPIController->save()");
    		allowedErrors.add("Notice: Trying to get property of non-object in EntityAPIController->save()");
    		
    		//DE5638 focal point error on image save
    		allowedErrors.add("Notice: getimagesize() [function.getimagesize]: Read error! in image_gd_get_info()");
    		
    		//DE5638 error when creating new custom mpx type
    		allowedErrors.add("Notice: Undefined index: #build_id in form_set_cache() (line 565 of");
    		
    		//DE6585 exif error when uploading new image
    		allowedErrors.add("Notice: Undefined offset: 0 in simple_exif_form_alter()");
    		allowedErrors.add("Warning: array_keys() expects parameter 1 to be array, null given in simple_exif_form_alter()");
    		allowedErrors.add("Warning: Invalid argument supplied for foreach() in simple_exif_form_alter()");
    		allowedErrors.add("Warning: in_array() expects parameter 2 to be array, null given in simple_exif_form_alter()");
    		
    		//US7050 and DE6393
    		allowedErrors.add("Failed to update URI to \"mpx:");
    		allowedErrors.add("Notice: Trying to get property of non-object in MediaThePlatformMpxStreamWrapper->interpolateUrl");
    		
    		//module error
    		allowedErrors.add("There was a problem checking available updates");
    		
    		//DE8455 dyanmic queue error
    		allowedErrors.add("Notice: Undefined offset: 4 in _menu_translate()");
    		
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
    								 				|| errorText.contains(allowedErrors.get(7))
    								 					|| errorText.contains(allowedErrors.get(8))
    								 					|| errorText.contains(allowedErrors.get(9))
    								 					|| errorText.contains(allowedErrors.get(10))
    								 					|| errorText.contains(allowedErrors.get(11))
    								 					|| errorText.contains(allowedErrors.get(12))
    								 					|| errorText.contains(allowedErrors.get(13))
    								 					|| errorText.contains(allowedErrors.get(14))
    								 					|| errorText.contains(allowedErrors.get(15))
    								 					|| errorText.contains(allowedErrors.get(16))
    								 					|| errorText.contains(allowedErrors.get(17))
    								 					|| errorText.contains(allowedErrors.get(18))
    								 					|| errorText.contains(allowedErrors.get(19))) {
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
    				for (String errorText : Errors) {
    					if (errorText.contains(allowedErrors.get(0)) 
    							|| errorText.contains(allowedErrors.get(1))
    									|| errorText.contains(allowedErrors.get(2))
    										|| errorText.contains(allowedErrors.get(3))
    										 	|| errorText.contains(allowedErrors.get(4))
    										 		|| errorText.contains(allowedErrors.get(5))
    										 			|| errorText.contains(allowedErrors.get(6))
    										 				|| errorText.contains(allowedErrors.get(7))
    										 					|| errorText.contains(allowedErrors.get(8))
    										 					|| errorText.contains(allowedErrors.get(9))
    										 					|| errorText.contains(allowedErrors.get(10))
    										 					|| errorText.contains(allowedErrors.get(11))
    										 					|| errorText.contains(allowedErrors.get(12))
    	    								 					|| errorText.contains(allowedErrors.get(13))
    	    								 					|| errorText.contains(allowedErrors.get(14))
    	    								 					|| errorText.contains(allowedErrors.get(15))
    	    								 					|| errorText.contains(allowedErrors.get(16))
    	    								 					|| errorText.contains(allowedErrors.get(17))
    	    								 					|| errorText.contains(allowedErrors.get(18))
    	    								 					|| errorText.contains(allowedErrors.get(19))) {
    						//ignore error
    					}
    					else {
    						//legitimate error and fail test
        					Assert.fail("Error text of '" + errorText + "' is present in application.");
    					}
    				}
    			}
    			
    		}
    		
    		webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	}
    }
}

