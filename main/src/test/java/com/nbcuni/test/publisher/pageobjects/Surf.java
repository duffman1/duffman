package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;

/*********************************************
 * publisher.nbcuni.com Surf Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: July 7, 2014
 *********************************************/

public class Surf {

    private Driver webDriver;
    private WebDriverWait wait;
    private ContentParent contentParent;
    
    //PAGE OBJECT CONSTRUCTOR
    public Surf(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 30);
        contentParent = new ContentParent(webDriver, applib);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "action-signin")
    private WebElement SignIn_Btn;
    
    @FindBy(how = How.XPATH, using = "//iframe[contains(@id, 'easyXDM')]")
    private WebElement SurfContainer_Frm;
    
    @FindBy(how = How.ID, using = "display-frame")
    private WebElement SurfLogin_Frm;
    
    @FindBy(how = How.ID, using = "input_username")
    private WebElement EmailOrUsername_Txb;
    
    @FindBy(how = How.ID, using = "input_password")
    private WebElement Password_Txb;
    
    @FindBy(how = How.ID, using = "button_submit")
    private WebElement SignInF_Btn;
    
    @FindBy(how = How.ID, using = "action-edit")
    private WebElement EditYourProfile_Btn;
    
    @FindBy(how = How.ID, using = "action-signout")
    private WebElement SignOut_Btn;
    
    @FindBy(how = How.ID, using = "surf-info")
    private WebElement SurfInfo_Ctr;
    
    
    //PAGE OBJECT METHODS
    public void ClickSignInBtn() throws Exception {
    	
    	Reporter.log("Click the 'Sign-in' button.");
    	wait.until(ExpectedConditions.visibilityOf(SignIn_Btn)).click();
    }
    
    public void SwitchToSurfFrm() throws Exception {
    	
    	Reporter.log("Switch to the Surf frame.");
    	webDriver.switchTo().frame(SurfContainer_Frm);
    	webDriver.switchTo().frame(SurfLogin_Frm);
    }
    
    public void EnterEmailUsername(String userName) throws Exception {
    	
    	Reporter.log("Enter '" + userName + "' in the 'Email or Username' text box.");
    	EmailOrUsername_Txb.sendKeys(userName);
    }
    
    public void EnterPassword(String password) throws Exception {
    	
    	Reporter.log("Enter '" + password + "' in the 'Password' text box.");
    	Password_Txb.sendKeys(password);
    }
    
    public void ClickSurfFrmSignInBtn() throws Exception {
    	
    	Reporter.log("Click the 'Sign In' button.");
    	SignInF_Btn.click();
    }
    
    public void VerifyUsernamePresent(String userName) throws Exception {
    	
    	Reporter.log("Verify surf username '" + userName + "' is present.");
    	wait.until(ExpectedConditions.visibilityOf(SurfInfo_Ctr));
    	Assert.assertTrue(SurfInfo_Ctr.getText().contains(userName));
    }
    
    public void ClickSignOutBtn() throws Exception {
    	
    	Reporter.log("Click the 'Sign Out' button.");
    	contentParent.Scroll("-500");
    	wait.until(ExpectedConditions.visibilityOf(SignOut_Btn)).click();
    	
    	Reporter.log("Verify the 'Sign In' button is present.");
    	wait.until(ExpectedConditions.visibilityOf(SignIn_Btn));
    }
    
}

