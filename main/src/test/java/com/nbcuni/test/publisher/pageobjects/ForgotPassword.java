package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com ForgotPassword Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 6, 2014
 *********************************************/

public class ForgotPassword {

    //PAGE OBJECT CONSTRUCTOR
    public ForgotPassword(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='Request new password']")
    private WebElement RequestNewPassword_Lnk;
    
    @FindBy(how = How.ID, using = "edit-name")
    private WebElement Email_Txb;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement ResetPassword_Btn;
    
    
    //PAGE OBJECT METHODS
    public void ClickRequestPasswordLnk() throws Exception {
    	
    	Reporter.log("Click the 'Request new password' link.");
    	RequestNewPassword_Lnk.click();
    }
    
    public void EnterEmail(String emailAddress) throws Exception {
    	
    	Reporter.log("Enter '" + emailAddress + "' in the 'E-mail' text box.");
    	Email_Txb.sendKeys(emailAddress);
    }
    
    public void ClickResetPasswordBtn() throws Exception {
    	
    	Reporter.log("Click the 'Reset password' button.");
    	ResetPassword_Btn.click();
    }
    
}

