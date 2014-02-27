package com.nbcuni.test.publisher.pageobjects.Twitter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Twitter Login Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 19, 2014
 *********************************************/

public class TwitterLogin {

    //PAGE OBJECT CONSTRUCTOR
    public TwitterLogin(CustomWebDriver webDriver, AppLib applib) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "username_or_email")
    private static WebElement AdminUsernameOrEmail_Txb;
    
    @FindBy(how = How.XPATH, using = "(//input[@name='session[username_or_email]'])[1]")
    private static WebElement UsernameOrEmail_Txb;
    
    @FindBy(how = How.ID, using = "password")
    private static WebElement AdminPassword_Txb;
    
    @FindBy(how = How.XPATH, using = "(//input[@name='session[password]'])[1]")
    private static WebElement Password_Txb;
    
    @FindBy(how = How.ID, using = "allow")
    private static WebElement AuthorizeApp_Btn;
    
    @FindBy(how = How.XPATH, using = "(//button[text()='Sign in'])[1]")
    private static WebElement SignIn_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterAdminUsernameOrEmail(String userName) throws Exception {
    	
    	Reporter.log("Enter '" + userName + "' in the 'Userame or email' text box.");
    	AdminUsernameOrEmail_Txb.sendKeys(userName);
    }
    
    public void EnterAdminPassword(String password) throws Exception {
    	
    	Reporter.log("Enter '" + password + "' in the 'Password' text box.");
    	AdminPassword_Txb.sendKeys(password);
    }
    
    public void EnterUsernameOrEmail(String userName) throws Exception {
    	
    	Reporter.log("Enter '" + userName + "' in the 'Userame or email' text box.");
    	UsernameOrEmail_Txb.sendKeys(userName);
    }
    
    public void EnterPassword(String password) throws Exception {
    	
    	Reporter.log("Enter '" + password + "' in the 'Password' text box.");
    	Password_Txb.sendKeys(password);
    }

    public void ClickAuthorizeAppBtn() throws Exception {
    	
    	Reporter.log("Click the 'Authorize App' button.");
    	AuthorizeApp_Btn.click();
    }
    
    public void ClickSignInBtn() throws Exception {
    	
    	Reporter.log("Click the 'Sign In' button.");
    	SignIn_Btn.click();
    }
   
}

