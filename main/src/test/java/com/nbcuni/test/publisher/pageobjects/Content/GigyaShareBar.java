package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.openqa.selenium.By;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Taxonomy Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 10, 2014
 *********************************************/
public class GigyaShareBar {

	private Driver webDriver;
	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
	//PAGE OBJECT CONSTRUCTORS
    public GigyaShareBar(Driver webDriver) {
    	this.webDriver = webDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Tumblr_Btn = By.xpath("//div[contains(@class, 'gig')][text()='Tumblr']");
    
    private By Email_Btn = By.xpath("//div[contains(@class, 'gig')][text()='Email']");
    
    private By GooglePlus_Btn = By.xpath("//div[contains(@class, 'gig')][text()='Google']");
    
    private By Foursquare_Btn = By.xpath("//div[contains(@class, 'gig')][text()='Foursquare']");
    
    private By Print_Btn = By.xpath("//div[contains(@class, 'gig')][text()='Print']");
    
    private By Twitter_Frm = By.xpath("//div[contains(@class, 'gig')]//iframe[contains(@id, 'twitter')]");
    
    private By Tweet_Btn = By.xpath("//span[@class='label'][text()='Tweet']");
    
    private By Like_Btn = By.xpath("//span[@class='pluginButtonLabel'][text()='Like']");
    
    private By Facebook_Frm = By.xpath("//div[contains(@class, 'gig')]//iframe[contains(@title, 'Facebook')]");
    
  
    //PAGE OBJECT METHODS
    public void VerifyTumblrBtnPresent() throws Exception{
    
    	Reporter.log("Verify the Tumblr gigya share link is present.");
    	waitFor.ElementVisible(Tumblr_Btn);
    	
    }
    
    public void VerifyEmailBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Email gigya share link is present.");
    	waitFor.ElementVisible(Email_Btn);
    	
    }

    public void VerifyGooglePlusBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Google Plus gigya share link is present.");
    	waitFor.ElementVisible(GooglePlus_Btn);
    	
    }
    
    public void VerifyFoursquareBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Foursquare gigya share link is present.");
    	waitFor.ElementVisible(Foursquare_Btn);
    	
    }
    
    public void VerifyPrintBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Print gigya share link is present.");
    	waitFor.ElementVisible(Print_Btn);
    	
    }
    
    public void VerifyTwitterBtnPresent() throws Exception {
        
    	Reporter.log("Verify the Twitter gigya share link is present.");
    	waitFor.ElementVisible(Twitter_Frm);
    	
    }
    
    public void ClickTweetButton() throws Exception {
        
    	Reporter.log("Click the 'Tweet' button.");
    	webDriver.switchTo().frame(waitFor.ElementPresent(Twitter_Frm));
    	interact.Click(waitFor.ElementVisible(Tweet_Btn));
    	
    }
    
    public void ClickLikeButton() throws Exception {
        
    	Reporter.log("Click the 'Like' button.");
    	webDriver.switchTo().frame(waitFor.ElementPresent(Facebook_Frm));
    	interact.Click(waitFor.ElementVisible(Like_Btn));
    	
    }
    
    public void VerifyFacebookBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Facebook gigya share link is present.");
    	waitFor.ElementPresent(Facebook_Frm);
    	
    }

}
