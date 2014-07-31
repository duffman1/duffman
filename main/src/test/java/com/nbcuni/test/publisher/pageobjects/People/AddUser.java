package com.nbcuni.test.publisher.pageobjects.People;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Add User Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 26, 2014
 *********************************************/

public class AddUser {

	private Driver webDriver;
	private Taxonomy taxonomy;
	private Overlay overlay;
	private ContentParent contentParent;
	private Random random;
	private WebDriverWait wait;
	private Config config;
	
    //PAGE OBJECT CONSTRUCTOR
    public AddUser(Driver webDriver, AppLib applib) {
    	this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        taxonomy = new Taxonomy(webDriver);
        overlay = new Overlay(webDriver, applib);
        contentParent = new ContentParent(webDriver, applib);
        random = new Random();
        wait = new WebDriverWait(webDriver, 10);
        config = new Config();
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[@id='edit-name']")
    private WebElement Username_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-mail']")
    private WebElement EmailAddress_Txb;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Change your password']")
    private WebElement ChangeYourPassword_Lnk;
    
    @FindBy(how = How.ID, using = "edit-current-pass")
    private WebElement CurrentPassword_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-pass-pass1']")
    private WebElement Password_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-pass-pass2']")
    private WebElement ConfirmPassword_Txb;
    
    private WebElement Role_Cbx(String role) {
    	return webDriver.findElement(By.xpath("//label[text()='" + role + " ']/../input"));
    }
    
    @FindBy(how = How.ID, using = "edit-notify")
    private WebElement NotifyUserNewAccount_Cbx;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-field-first-name-und-0-value']")
    private WebElement FirstName_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-field-last-name-und-0-value']")
    private WebElement LastName_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Create new account']")
    private WebElement CreateNewAccount_Btn;
    
    @FindBy(how = How.CSS, using = "div[class='password-suggestions description']")
    private WebElement PasswordStrength_Ctr;
    
    @FindBy(how = How.CSS, using = "div[class='password-confirm']")
    private WebElement PasswordMatch_Ctr;
    
    
    //PAGE OBJECT METHODS
    public void EnterUsername(String userName) throws Exception {
    	
    	Reporter.log("Enter '" + userName + "' in the 'Username' text box.");
    	Username_Txb.sendKeys(userName);
    	
    }
    
    public void EnterEmailAddress(String emailAddress) throws Exception {
    	
    	Reporter.log("Enter '" + emailAddress + "' in the 'E-mail address' text box.");
    	EmailAddress_Txb.sendKeys(emailAddress);
    	
    }
    
    public void EnterCurrentPassword(String passWord) throws Exception {
    	
    	Reporter.log("Enter '" + passWord + "' in the 'Current password' text box.");
    	Thread.sleep(500);
    	CurrentPassword_Txb.sendKeys(passWord);
    	
    }

    public void EnterPassword(String passWord) throws Exception {
    	
    	Reporter.log("Enter '" + passWord + "' in the 'Password' text box.");
    	Password_Txb.clear();
    	Password_Txb.sendKeys(passWord);
    	
    }
    
    public void EnterConfirmPassword(String passWord) throws Exception {
    	
    	Reporter.log("Enter '" + passWord + "' in the 'Confirm password' text box.");
    	ConfirmPassword_Txb.clear();
    	ConfirmPassword_Txb.sendKeys(passWord);
    }
    
    public void CheckRoleCbx(List<String> roles) throws Exception {
    	
    	for (String role : roles) {
    		if (Role_Cbx(role).isSelected() == false) {
    			Reporter.log("Check the '" + role + "' check box.");
    			Role_Cbx(role).click();
    		}
    	}
    }
    
    public void CheckNotifyUserNewAccountCbx() throws Exception {
    	
    	if (NotifyUserNewAccount_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Notify user of a new account' check box.");
    		NotifyUserNewAccount_Cbx.click();
    	}
    }
    
    public void EnterFirstName(String firstName) throws Exception {
    	
    	Reporter.log("Enter '" + firstName + "' in the 'First Name' text box.");
    	FirstName_Txb.sendKeys(firstName);
    	
    }
    
    public void EnterLastName(String lastName) throws Exception {
    	
    	Reporter.log("Enter '" + lastName + "' in the 'Last Name' text box.");
    	LastName_Txb.sendKeys(lastName);
    	
    }
    
    public void ClickCreateNewAccountBtn() throws Exception {
    	
    	Reporter.log("Click the 'Create new account' button.");
    	CreateNewAccount_Btn.click();
    }
    
    public void VerifyPasswordStrengthCtrNotPresent() throws Exception {
    	
    	Reporter.log("Verify the password strength container is not present.");
    	wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(PasswordStrength_Ctr)));
    }
    
    public void VerifyPasswordStrengthTxtPresent(List<String> strengthMessages) throws Exception {
    	
    	String strengthTxt = wait.until(ExpectedConditions.visibilityOf(PasswordStrength_Ctr)).getText();
    	
    	for (String message : strengthMessages) {
    		Reporter.log("Verify that password strength message '" + message + "' is present.");
    		Assert.assertTrue(strengthTxt.contains(message), 
    				"Password strength message '" + message + " is not present.");
    	}
    }
    
    public void VerifyPasswordStrengthTxtNotPresent(List<String> strengthMessages) throws Exception {
    	
    	String strengthTxt = wait.until(ExpectedConditions.visibilityOf(PasswordStrength_Ctr)).getText();
    	
    	for (String message : strengthMessages) {
    		Reporter.log("Verify that password strength message '" + message + "' is not present.");
    		Assert.assertFalse(strengthTxt.contains(message), 
    				"Password strength message '" + message + " is present when it should not be.");
    	}
    }
    
    public void VerifyPasswordsMatch(Boolean match) throws Exception {
    	
    	String passwordMatchTxt = wait.until(ExpectedConditions.visibilityOf(PasswordMatch_Ctr)).getText();
    	
    	if (match == true) {
    		Reporter.log("Verify password match text equals 'yes'.");
    		Assert.assertTrue(passwordMatchTxt.contains("yes"), "Password match text of 'yes' is not present.");
    	}
    	else {
    		Reporter.log("Verify password match text equals 'no'.");
    		Assert.assertTrue(passwordMatchTxt.contains("no"), "Password match text of 'no' is not present.");
    	}
    }
    
    public void VerifyUsernameNotPresent() throws Exception {
    	
    	Reporter.log("Verify the 'Username' text box is not present.");
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	try {
    		Username_Txb.isDisplayed();
    		Assert.fail("Username text box is present when it should not be.");
    	}
    	catch (NoSuchElementException e) { }
    	webDriver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    }
    
    public void VerifyPasswordNotPresent() throws Exception {
    	
    	Reporter.log("Verify the 'Password' and 'Password Confirm' text boxes are not present.");
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	try {
    		Password_Txb.isDisplayed();
    		ConfirmPassword_Txb.isDisplayed();
    		Assert.fail("Password/Password Confirm text boxes are present when they should not be.");
    	}
    	catch (NoSuchElementException e) { }
    	webDriver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    }

    public void VerifyUsernameValueAndIsDisabled(String userName) throws Exception {
    	
    	Reporter.log("Verify the 'Username' value is '" + userName + "' and the text box is disabled.");
    	Assert.assertEquals(userName, Username_Txb.getAttribute("value"));
    	Assert.assertFalse(Username_Txb.isEnabled());
    	
    }
    
    public void VerifyEmailAddressValueAndIsDisabled(String email) throws Exception {
    	
    	Reporter.log("Verify the 'E-mail address' value is '" + email + "' and the text box is disabled.");
    	Assert.assertEquals(email, EmailAddress_Txb.getAttribute("value"));
    	Assert.assertFalse(EmailAddress_Txb.isEnabled());
    	
    }
    
    public void ClickChangeYourPasswordLnk() throws Exception {
    	
    	Reporter.log("Click the 'Change your password' link.");
    	ChangeYourPassword_Lnk.click();
    	
    }
    
    public String AddDefaultUser(List<String> roles, Boolean isSSO) throws Exception {
    	
    	taxonomy.NavigateSite("People>>Add user");
        overlay.SwitchToActiveFrame();
        String userName = random.GetCharacterString(15) + "@" + random.GetCharacterString(15) + ".com";
        if (isSSO.equals(false)) {
        	this.EnterUsername(userName);
        }
        else {
        	this.VerifyUsernameNotPresent();
        }
        this.EnterEmailAddress(userName);
        if (isSSO.equals(false)) {
        	String passWord = "pa55word";
        	this.EnterPassword(passWord);
            this.EnterConfirmPassword(passWord);
        }
        else {
        	this.VerifyPasswordNotPresent();
        }
        this.CheckRoleCbx(roles);
        this.CheckNotifyUserNewAccountCbx();
        String firstName = random.GetCharacterString(15);
        this.EnterFirstName(firstName);
        String lastName = random.GetCharacterString(15);
        this.EnterLastName(lastName);
        this.ClickCreateNewAccountBtn();
        contentParent.VerifyMessageStatus("A welcome message with further instructions has been e-mailed to the new user " + userName + ".");
        overlay.ClickCloseOverlayLnk();
        
        return userName;
    }
    
  
}

