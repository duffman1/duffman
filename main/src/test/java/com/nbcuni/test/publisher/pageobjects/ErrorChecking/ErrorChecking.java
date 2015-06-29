package com.nbcuni.test.publisher.pageobjects.ErrorChecking;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*********************************************
 * publisher.nbcuni.com Content Parent Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class ErrorChecking {

    private WebDriver webDriver;
    private Config config;
    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public ErrorChecking(WebDriver webDriver) {
        this.webDriver = webDriver;
        config = new Config();
        PageFactory.initElements(webDriver, this);
        waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//div[@class='messages error']")
    private WebElement Error_Ctr;
    
    @FindBy(how = How.XPATH, using = "//div[@class='messages error']/ul")
    private WebElement MoreThanOneError_Ctr;
    
    private By DisabledPlayerError_Ctr(String playerTitle) {
    	return By.xpath("//div[@class='messages error']//em[contains(text(), '" + playerTitle + "')]/../..");
    }
    
    private List<WebElement> Error_Itms() {
    	return webDriver.findElements(By.xpath("//div[@class='messages error']/ul/li"));
    }
    
    
    //PAGE OBJECT METHODS
    public void VerifyErrorMessagePresent(String errorMessage) throws Exception {
    	
    	waitFor.ElementContainsText(Error_Ctr, errorMessage);
    	
    }
    
    public void VerifyAllRequiredFields(List<String> allFieldTitles) throws Exception {
    	
    	for (String field : allFieldTitles) {
    		waitFor.ElementContainsText(Error_Ctr, field + " field is required.");
    	}
    }
    
    public void VerifyMPXPlayerDisabled(String playerTitle) throws Exception {
    	
    	waitFor.ElementContainsText(DisabledPlayerError_Ctr(playerTitle), 
    			"An MPXplayer that's in use (" + playerTitle + ") has been disabled in MPX.");
    	waitFor.ElementContainsText(DisabledPlayerError_Ctr(playerTitle), 
    			"To change its status in MPX, log into mpx.theplatform");
    	
    }
    
    public void VerifyMPXPlayerDisabledAndUnpublished(String playerTitle) throws Exception {
    	
    	waitFor.ElementContainsText(DisabledPlayerError_Ctr(playerTitle), "An MPXplayer that's in use (" + playerTitle + ") has been disabled and unpublished.");
    	waitFor.ElementContainsText(DisabledPlayerError_Ctr(playerTitle), "To change its status in Publisher, click here");
    	waitFor.ElementContainsText(DisabledPlayerError_Ctr(playerTitle), "To change its status in MPX, log into mpx.theplatform");
    	
    }
    
    public void VerifyNoMessageErrorsPresent() throws Exception{
    	
    	if (config.IsErrorCheckingEnabled() == true) {
    		webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    		//get the allowed errors from the text file
    		String allowedErrorsFilePath = System.getProperty("user.dir") + "/src/test/resources/AllowedErrors.txt";
    	    allowedErrorsFilePath = allowedErrorsFilePath.replace("/", File.separator);
    	    File allowedErrorsFile = new File(allowedErrorsFilePath);
        	BufferedReader bufferedReader = new BufferedReader(new FileReader(allowedErrorsFile));
        	List<String> allowedErrors = new ArrayList<String>();
        	String line;
        	while ((line = bufferedReader.readLine()) != null) {
        		
        	   allowedErrors.add(line.trim());
        	}
        	bufferedReader.close();
        	
    	    
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
    				
    				boolean ignoreError = false;
    				
    				//get the text of the error
    				String errorText = "";
    				try {
    					errorText = Error_Ctr.getText();
    				}
    				catch (NoSuchElementException e) {
    					ignoreError = true;
    				}
    				errorText.replace("Error message", "");
    				
    				//check the error text isn't in list of allowed errors
    				for (int i=0; i<allowedErrors.size(); i++) {
    					if(errorText.contains(allowedErrors.get(i)) || errorText.equals("") || errorText.isEmpty() || errorText.equals(null)) {
    						//ignore error
    						ignoreError = true;
    					}
    				}
    				
    				if (!ignoreError) {
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
    					boolean ignoreError = false;
    					for (int i=0; i<allowedErrors.size(); i++) {
    						if(errorText.contains(allowedErrors.get(i)) || errorText.equals("") || errorText.isEmpty() || errorText.equals(null)) {
    							//ignore error
    							ignoreError = true;
    						}
    					}
    					
    					if (!ignoreError) {
    						//legitimate error and fail test
    						Assert.fail("Error text of '" + errorText + "' is present in application.");
    					}
    				}
    			}
    		}
    		webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	}
    }
}