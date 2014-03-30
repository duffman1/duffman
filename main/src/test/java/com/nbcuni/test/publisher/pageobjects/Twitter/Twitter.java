package com.nbcuni.test.publisher.pageobjects.Twitter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Twitter Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 19, 2014
 *********************************************/

public class Twitter {

    //PAGE OBJECT CONSTRUCTOR
    public Twitter(Driver webDriver, AppLib applib) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-twitter-consumer-key")
    private WebElement OAuthConsumerKey_Txb;
    
    @FindBy(how = How.ID, using = "edit-twitter-consumer-secret")
    private WebElement OAuthConsumerSecret_Txb;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveConfiguration_Btn;
    
    //PAGE OBJECT METHODS
    public void EnterOAuthConsumerKey(String key) throws Exception {
    	
    	Reporter.log("Enter the key in the 'OAuth Consumer Key' text box.");
    	OAuthConsumerKey_Txb.clear();
    	OAuthConsumerKey_Txb.sendKeys(key);
    }
    
    public void EnterOAuthConsumerSecret(String secret) throws Exception {
    	
    	Reporter.log("Enter the secret in the 'OAuth Consumer Secret' text box.");
    	OAuthConsumerSecret_Txb.clear();
    	OAuthConsumerSecret_Txb.sendKeys(secret);
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
   
  
}

