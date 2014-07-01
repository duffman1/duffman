package com.nbcuni.test.publisher.pageobjects.Twitter;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Twitter Login Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: July 1, 2014
 *********************************************/

public class TwitterLogin {

	private Driver webDriver;
	
    //PAGE OBJECT CONSTRUCTOR
    public TwitterLogin(Driver webDriver, AppLib applib) {
    	this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "username_or_email")
    private WebElement AdminUsernameOrEmail_Txb;
    
    private List<WebElement> UsernameOrEmail_Txbs() {
    	return webDriver.findElements(By.xpath("//input[@name='session[username_or_email]']"));
    }
    
    @FindBy(how = How.ID, using = "password")
    private WebElement AdminPassword_Txb;
    
    private List<WebElement> Password_Txbs() {
    	return webDriver.findElements(By.xpath("//input[@name='session[password]']"));
    }
    
    @FindBy(how = How.ID, using = "allow")
    private WebElement AuthorizeApp_Btn;
    
    private List<WebElement> SignIn_Btns() {
    	return webDriver.findElements(By.xpath("//button[text()='Sign in']"));
    }
    
    
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
    	for (WebElement el : UsernameOrEmail_Txbs()) {
    		if (el.isDisplayed()) {
    			el.sendKeys(userName);
    			break;
    		}
    	}
    }
    
    public void EnterPassword(String password) throws Exception {
    	
    	Reporter.log("Enter '" + password + "' in the 'Password' text box.");
    	for (WebElement el : Password_Txbs()) {
    		if (el.isDisplayed()) {
    			el.sendKeys(password);
    			break;
    		}
    	}
    }

    public void ClickAuthorizeAppBtn() throws Exception {
    	
    	Reporter.log("Click the 'Authorize App' button.");
    	AuthorizeApp_Btn.click();
    }
    
    public void ClickSignInBtn() throws Exception {
    	
    	Reporter.log("Click the 'Sign In' button.");
    	for (WebElement el : SignIn_Btns()) {
    		if (el.isDisplayed()) {
    			el.click();
    			break;
    		}
    	}
    }
   
}

