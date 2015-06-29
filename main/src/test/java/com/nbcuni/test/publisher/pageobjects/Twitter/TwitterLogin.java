package com.nbcuni.test.publisher.pageobjects.Twitter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.Arrays;

/*********************************************
 * publisher.nbcuni.com Twitter Login Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: July 1, 2014
 *********************************************/

public class TwitterLogin {

	private WebDriver webWebWebDriver;
	private ContentParent contentParent;
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public TwitterLogin(WebDriver webWebWebDriver, AppLib applib) {
    	this.webWebWebDriver = webWebWebDriver;
        contentParent = new ContentParent(webWebWebDriver);
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AdminUsernameOrEmail_Txb = By.id("username_or_email");
    
    private By AllUsernameOrEmail_Txbs = By.xpath("//input[@name='session[username_or_email]']");
    
    private By AdminPassword_Txb = By.id("password");
    
    private By AllPassword_Txbs = By.xpath("//input[@name='session[password]']");
    
    private By AuthorizeApp_Btn = By.id("allow");
    
    private By SignInAndTweet_Btn = By.xpath("//input[@value='Log in and Tweet']");
    
    private By Tweet_Btn = By.xpath("//input[@value='Tweet']");
    
    private By AllSignIn_Btns = By.xpath("//button[text()='Log in']");
    
    
    //PAGE OBJECT METHODS
    public void EnterAdminUsernameOrEmail(String userName) throws Exception {
    	
    	Reporter.log("Enter '" + userName + "' in the 'Userame or email' text box.");
    	interact.Type(waitFor.ElementVisible(AdminUsernameOrEmail_Txb), userName);
    	
    }
    
    public void EnterAdminPassword(String password) throws Exception {
    	
    	Reporter.log("Enter '" + password + "' in the 'Password' text box.");
    	interact.Type(waitFor.ElementVisible(AdminPassword_Txb), password);
    	
    }
    
    public void EnterUsernameOrEmail(String userName) throws Exception {
    	
    	Reporter.log("Enter '" + userName + "' in the 'Userame or email' text box.");
    	for (WebElement el : waitFor.ElementsPresent(AllUsernameOrEmail_Txbs)) {
    		if (el.isDisplayed()) {
    			interact.Type(el, userName);
    			break;
    		}
    	}
    }
    
    public void EnterPassword(String password) throws Exception {
    	
    	Reporter.log("Enter '" + password + "' in the 'Password' text box.");
    	for (WebElement el : waitFor.ElementsPresent(AllPassword_Txbs)) {
    		if (el.isDisplayed()) {
    			interact.Type(el, password);
    			break;
    		}
    	}
    }

    public void ClickAuthorizeAppBtn() throws Exception {
    	
    	Reporter.log("Click the 'Authorize App' button.");
    	interact.Click(waitFor.ElementVisible(AuthorizeApp_Btn));
    	
    }
    
    public void ClickSignInAndTweetBtn() throws Exception {
    	
    	Reporter.log("Click the 'Sign in and Tweet' button.");
    	interact.Click(waitFor.ElementVisible(SignInAndTweet_Btn));
    	
    }
    
    public void ClickTweetBtn() throws Exception {
    	
    	Reporter.log("Click the 'Tweet' button.");
    	interact.Click(waitFor.ElementVisible(Tweet_Btn));
    	
    }
    
    public void ClickSignInBtn() throws Exception {
    	
    	Reporter.log("Click the 'Sign In' button.");
    	for (WebElement el : waitFor.ElementsPresent(AllSignIn_Btns)) {
    		if (el.isDisplayed()) {
    			interact.Click(el);
    			break;
    		}
    	}
    }
    
    public void VerifyTwitterPostPresent(String content) throws Exception {
    	
    	for (int I = 0 ; ; I++) {
        	if (I >= 10) {
        		Assert.fail("Twitter post has not posted to twitter.");
        	}
        	try {
        		contentParent.VerifyPageContentPresent(Arrays.asList("Publisher Seven", content));
        		break;
        	}
        	catch (AssertionError e) {
        		Thread.sleep(1000);
        		webWebWebDriver.navigate().refresh();
        	}
        	
        }
    }
    
}

