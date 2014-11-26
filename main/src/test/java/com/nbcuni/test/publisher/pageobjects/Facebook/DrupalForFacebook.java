package com.nbcuni.test.publisher.pageobjects.Facebook;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Drupal For Facebook Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class DrupalForFacebook {

    private Driver webDriver;
    private Config config;
    
    //PAGE OBJECT CONSTRUCTOR
    public DrupalForFacebook(Driver webDriver) {
        this.webDriver = webDriver;
        config = new Config();
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-label")
    private WebElement Label_Txb;
    
    @FindBy(how = How.ID, using = "edit-id")
    private WebElement FacebookAppId_Txb;
    
    @FindBy(how = How.ID, using = "edit-secret")
    private WebElement Secret_Txb;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Save_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Save configuration']")
    private WebElement SaveConfiguration_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'https://www.facebook.com/dialog/oauth')]")
    private WebElement PostViaPub7_Lnk;
    
    @FindBy(how = How.ID, using = "email")
    private WebElement FacebookEmail_Txb;
    
    @FindBy(how = How.ID, using = "pass")
    private WebElement FacebookPassword_Txb;
    
    @FindBy(how = How.ID, using = "u_0_1")
    private WebElement FacebookLogin_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[@class='fieldset-title']")
    private WebElement ShowCurrentToken_Lnk;
    
    @FindBy(how = How.XPATH, using = "(//div[@class='fieldset-description'])[2]")
    private WebElement Token_Ctr;
    
    @FindBy(how = How.ID, using = "edit-fb-stream-token")
    private WebElement PasteAccessToken_Txa;
    
    @FindBy(how = How.XPATH, using = "//a[text()='edit']")
    private WebElement Edit_Lnk;
    
    
    //PAGE OBJECT METHODS
    public boolean FacebookAppExists() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	
    	boolean existingAppPresent = false;
    	try {
    		Edit_Lnk.isDisplayed();
    		existingAppPresent = true;
    	}
    	catch (Exception e) {
    		existingAppPresent = false;
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
    	return existingAppPresent;
    }
    
    public void ClickEditLnk() throws Exception {
    	
    	Reporter.log("Click the 'Edit' link.");
    	Edit_Lnk.click();
    }
    
    public void EnterLabel(String labelTxt) throws Exception {
    	
    	Reporter.log("Enter '" + labelTxt + "' in the 'Label' text box.");
    	Label_Txb.sendKeys(labelTxt);
    }
    
    public void EnterFacebookAppId(String facebookAppId) throws Exception {
    	
    	Reporter.log("Enter '" + facebookAppId + "' in the 'Facbook app id' text box.");
    	FacebookAppId_Txb.sendKeys(facebookAppId);
    }
    
    public void EnterSecret(String secret) throws Exception {
    	
    	Reporter.log("Enter '" + secret + "' in the 'Secret' text box.");
    	Secret_Txb.sendKeys(secret);
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
    public void ClickPostViaPub7Lnk() throws Exception {
    	
    	Reporter.log("Click the 'Post via pub 7' link.");
    	PostViaPub7_Lnk.click();
    }
    
    public void LoginToFacebook(String userName, String passWord) throws Exception {
    	
    	Reporter.log("Enter '" + userName + "' in the 'Username' text box.");
    	FacebookEmail_Txb.sendKeys(userName);
    	
    	Reporter.log("Enter '" + passWord + "' in the 'Password' text box.");
    	FacebookPassword_Txb.sendKeys(passWord);
    	
    	Reporter.log("Click the 'Facebook login' button.");
    	FacebookLogin_Btn.click();
    }
    
    public void ClickShowCurrentTokenLnk() throws Exception {
    	
    	Reporter.log("Click the 'Show current token' link.");
    	ShowCurrentToken_Lnk.click();
    }
    
    public void EnterToken() throws Exception {
    	
    	Reporter.log("Copy the current token.");
    	String token = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(Token_Ctr)).getText();
    	
    	Reporter.log("Paste the current token into the 'Paste Access token' text area.");
    	PasteAccessToken_Txa.sendKeys(token);
    	
    }
  
}

