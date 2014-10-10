package com.nbcuni.test.publisher.pageobjects.Facebook;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Share Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Share {

    private Driver webDriver;
    private Config config;
    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public Share(Driver webDriver) {
        this.webDriver = webDriver;
        config = new Config();
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Facebook']")
    private WebElement Facebook_Lnk;
    
    @FindBy(how = How.ID, using = "edit-facebook-enabled")
    private WebElement PostToFacebookWall_Cbx;
    
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Twitter']")
    private WebElement Twitter_Lnk;
    
    @FindBy(how = How.ID, using = "edit-twitter-enabled")
    private WebElement PostToTwitter_Cbx;
    
    @FindBy(how = How.ID, using = "edit-facebook-stream-post")
    private WebElement ProvideBriefMessage_Txa;
    
    @FindBy(how = How.ID, using = "edit-twitter-status-update")
    private WebElement Tweet_Txa;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Share']")
    private WebElement Share_Btn;
    
    
    //PAGE OBJECT METHODS
    public void ClickFacebookLnk() throws Exception {
    	
    	Reporter.log("Click the 'Facebook' link.");
    	wait.until(ExpectedConditions.visibilityOf(Facebook_Lnk)).click();
    }

    public void ClickPostToFacebookWallCbx() throws Exception {
    	
    	Reporter.log("Check the 'Post to facebook wall' check box.");
    	wait.until(ExpectedConditions.visibilityOf(PostToFacebookWall_Cbx)).click();
    }
    
    public void ClickTwitterLnk() throws Exception {
    	
    	Reporter.log("Click the 'Twitter' link.");
    	wait.until(ExpectedConditions.visibilityOf(Twitter_Lnk)).click();
    }
    
    public void ClickPostToTwitterCbx() throws Exception {
	
    	Reporter.log("Check the 'Post to Twitter' check box.");
    	wait.until(ExpectedConditions.visibilityOf(PostToTwitter_Cbx)).click();
    }
    
    public void EnterBriefMessage(String messageTxt) throws Exception {
    	
    	Reporter.log("Enter '" + messageTxt + "' in the 'Provide brief message' text area.");
    	wait.until(ExpectedConditions.visibilityOf(ProvideBriefMessage_Txa)).sendKeys(messageTxt);
    }
    
    public void EnterTweet(String messageTxt) throws Exception {
    	
    	Reporter.log("Enter '" + messageTxt + "' in the 'Tweet' text area.");
    	wait.until(ExpectedConditions.visibilityOf(Tweet_Txa)).sendKeys(messageTxt);
    }
    
    public void ClickShareBtn() throws Exception {
    	
    	Reporter.log("Click the 'Share' button.");
    	Share_Btn.click();
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

            boolean elementPresent = true;
            for (int time = 0; ; time++){
                 if (time >= 60) {
                     Assert.fail("Element is still present after timeout");}
                  try{
                  Share_Btn.isDisplayed();
                  elementPresent = true;
                  }
                  catch (Exception e){ elementPresent = false;  }
                  if (elementPresent == false){ break; }
                  Thread.sleep(1000);
                  }

            webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);

    }
    
}

