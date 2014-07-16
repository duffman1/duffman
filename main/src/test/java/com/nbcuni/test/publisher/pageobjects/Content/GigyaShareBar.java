package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.NoSuchElementException;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Taxonomy Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 10, 2014
 *********************************************/
public class GigyaShareBar {

	private Driver webDriver;
	private WebDriverWait wait;
	
	//PAGE OBJECT CONSTRUCTORS
    public GigyaShareBar(Driver webDriver, AppLib applib) {
    	this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = (WebDriverWait) new WebDriverWait(webDriver, 30).ignoring(NoSuchElementException.class);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')][text()='Tumblr']")
    private WebElement Tumblr_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')][text()='Email']")
    private WebElement Email_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')][text()='Google+']")
    private WebElement GooglePlus_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')][text()='Foursquare']")
    private WebElement Foursquare_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')][text()='Print']")
    private WebElement Print_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')]//iframe[contains(@id, 'twitter')]")
    private WebElement Twitter_Frm;
    
    @FindBy(how = How.XPATH, using = "//span[@class='label'][text()='Tweet']")
    private WebElement Tweet_Btn;
    
    @FindBy(how = How.XPATH, using = "//span[@class='pluginButtonLabel'][text()='Like']")
    private WebElement Like_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gig')]//iframe[contains(@title, 'Facebook')]")
    private WebElement Facebook_Frm;
    
  
    //PAGE OBJECT METHODS
    public void VerifyTumblrBtnPresent() throws Exception{
    
    	Reporter.log("Verify the Tumblr gigya share link is present.");
    	wait.until(ExpectedConditions.visibilityOf(Tumblr_Btn));
    	
    }
    
    public void VerifyEmailBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Email gigya share link is present.");
    	wait.until(ExpectedConditions.visibilityOf(Email_Btn));
    	
    }

    public void VerifyGooglePlusBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Google Plus gigya share link is present.");
    	wait.until(ExpectedConditions.visibilityOf(GooglePlus_Btn));
    	
    }
    
    public void VerifyFoursquareBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Foursquare gigya share link is present.");
    	wait.until(ExpectedConditions.visibilityOf(Foursquare_Btn));
    	
    }
    
    public void VerifyPrintBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Print gigya share link is present.");
    	wait.until(ExpectedConditions.visibilityOf(Print_Btn));
    	
    }
    
    public void VerifyTwitterBtnPresent() throws Exception {
        
    	Reporter.log("Verify the Twitter gigya share link is present.");
    	wait.until(ExpectedConditions.visibilityOf(Twitter_Frm));
    	
    }
    
    public void ClickTweetButton() throws Exception {
        
    	Reporter.log("Click the 'Tweet' button.");
    	wait.until(ExpectedConditions.visibilityOf(Twitter_Frm));
    	webDriver.switchTo().frame(Twitter_Frm);
    	Tweet_Btn.click();
    	
    }
    
    public void ClickLikeButton() throws Exception {
        
    	Reporter.log("Click the 'Like' button.");
    	wait.until(ExpectedConditions.visibilityOf(Facebook_Frm));
    	webDriver.switchTo().frame(Facebook_Frm);
    	Like_Btn.click();
    	
    }
    
    public void VerifyFacebookBtnPresent() throws Exception{
        
    	Reporter.log("Verify the Facebook gigya share link is present.");
    	wait.until(ExpectedConditions.visibilityOf(Facebook_Frm));
    	
    }
    
    

}
