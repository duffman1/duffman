package com.nbcuni.test.publisher.pageobjects.TVEModule;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Optimum Login Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 29, 2014
 *********************************************/

public class OptimumLogin {

	private WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public OptimumLogin(Driver webDriver) {
    	PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 60);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "IDToken1")
    private WebElement OptimumID_Txb;
    
    @FindBy(how = How.ID, using = "IDToken2")
    private WebElement Password_Txb;
    
    @FindBy(how = How.ID, using = "signin_button")
    private WebElement SignIn_Btn;
    
    
    //PAGE OBJECT METHODS
    public void LoginToOptimum(String userId, String password) throws Exception {
    	
    	Reporter.log("Enter '" + userId + "' in the 'Optimum ID' text box.");
    	wait.until(ExpectedConditions.visibilityOf(OptimumID_Txb)).sendKeys(userId);
    	
    	Reporter.log("Enter '" + password + "' in the 'Password' text box.");
    	Password_Txb.sendKeys(password);
    	
    	Reporter.log("Click the 'SIGN IN' button.");
    	SignIn_Btn.click();
    }
    
}

