package com.nbcuni.test.publisher.pageobjects.Twitter;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Twitter Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 19, 2014
 *********************************************/

public class Twitter {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public Twitter(Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By OAuthConsumerKey_Txb = By.id("edit-twitter-consumer-key");
    
    private By OAuthConsumerSecret_Txb = By.id("edit-twitter-consumer-secret");
    
    private By SaveConfiguration_Btn = By.id("edit-submit");
    
    		
    //PAGE OBJECT METHODS
    public void EnterOAuthConsumerKey(String key) throws Exception {
    	
    	Reporter.log("Enter the key in the 'OAuth Consumer Key' text box.");
    	interact.Type(waitFor.ElementVisible(OAuthConsumerKey_Txb), key);
    	
    }
    
    public void EnterOAuthConsumerSecret(String secret) throws Exception {
    	
    	Reporter.log("Enter the secret in the 'OAuth Consumer Secret' text box.");
    	interact.Type(waitFor.ElementVisible(OAuthConsumerSecret_Txb), secret);
    	
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));
    	
    }
    
}

