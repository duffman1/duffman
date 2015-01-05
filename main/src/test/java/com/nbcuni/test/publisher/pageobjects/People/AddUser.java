package com.nbcuni.test.publisher.pageobjects.People;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Add User Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 26, 2014
 *********************************************/

public class AddUser {

	private EmberNav navigation;
	private ContentParent contentParent;
	private Random random;
	private Config config;
	private Integer timeout;
	private People people;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public AddUser(Driver webDriver) {
    	navigation = new EmberNav(webDriver);
        contentParent = new ContentParent(webDriver);
        random = new Random();
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        people = new People(webDriver);
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Username_Txb = By.xpath("//input[@id='edit-name']");
    
    private By EmailAddress_Txb = By.xpath("//input[@id='edit-mail']");
    
    private By ChangeYourPassword_Lnk = By.xpath("//a[text()='Change your password']");
    
    private By CurrentPassword_Txb = By.id("edit-current-pass");
    
    private By Password_Txb = By.xpath("//input[@id='edit-pass-pass1']");
    
    private By ConfirmPassword_Txb = By.xpath("//input[@id='edit-pass-pass2']");
    
    private By Role_Cbx(String role) {
    	return By.xpath("//label[text()='" + role + " ']/../input");
    }
    
    private By NotifyUserNewAccount_Cbx = By.id("edit-notify");
    
    private By FirstName_Txb = By.xpath("//input[@id='edit-field-first-name-und-0-value']");
    
    private By LastName_Txb = By.xpath("//input[@id='edit-field-last-name-und-0-value']");
    
    private By CreateNewAccount_Btn = By.xpath("//input[@value='Create new account']");
    
    private By PasswordStrength_Ctr = By.cssSelector("div[class='password-suggestions description']");
    
    private By PasswordMatch_Ctr = By.cssSelector("div[class='password-confirm']");
    
    
    //PAGE OBJECT METHODS
    public void EnterUsername(String userName) throws Exception {
    	
    	Reporter.log("Enter '" + userName + "' in the 'Username' text box.");
    	interact.Type(waitFor.ElementVisible(Username_Txb), userName);
    	
    }
    
    public void EnterEmailAddress(String emailAddress) throws Exception {
    	
    	Reporter.log("Enter '" + emailAddress + "' in the 'E-mail address' text box.");
    	interact.Type(waitFor.ElementVisible(EmailAddress_Txb), emailAddress);
    	
    }
    
    public void EnterCurrentPassword(String passWord) throws Exception {
    	
    	Reporter.log("Enter '" + passWord + "' in the 'Current password' text box.");
    	WebElement ele = waitFor.ElementVisible(CurrentPassword_Txb);
    	Thread.sleep(500);
    	interact.Type(ele, passWord);
    	
    }

    public void EnterPassword(String passWord) throws Exception {
    	
    	Reporter.log("Enter '" + passWord + "' in the 'Password' text box.");
    	interact.Type(waitFor.ElementVisible(Password_Txb), passWord);
    	
    }
    
    public void EnterConfirmPassword(String passWord) throws Exception {
    	
    	Reporter.log("Enter '" + passWord + "' in the 'Confirm password' text box.");
    	interact.Type(waitFor.ElementVisible(ConfirmPassword_Txb), passWord);
    	
    }
    
    public void CheckRoleCbx(List<String> roles) throws Exception {
    	
    	for (String role : roles) {
    		WebElement ele = waitFor.ElementVisible(Role_Cbx(role));
    		if (ele.isSelected() == false) {
    			Reporter.log("Check the '" + role + "' check box.");
    			interact.Click(ele);
    		}
    	}
    	
    }
    
    public void CheckNotifyUserNewAccountCbx() throws Exception {
    	
    	WebElement ele = waitFor.ElementVisible(NotifyUserNewAccount_Cbx);
    	if (!ele.isSelected()) {
    		Reporter.log("Check the 'Notify user of a new account' check box.");
    		interact.Click(ele);
    	}
    	
    }
    
    public void EnterFirstName(String firstName) throws Exception {
    	
    	Reporter.log("Enter '" + firstName + "' in the 'First Name' text box.");
    	interact.Type(waitFor.ElementVisible(FirstName_Txb), firstName);
    	
    }
    
    public void EnterLastName(String lastName) throws Exception {
    	
    	Reporter.log("Enter '" + lastName + "' in the 'Last Name' text box.");
    	interact.Type(waitFor.ElementVisible(LastName_Txb), lastName);
    	
    }
    
    public void ClickCreateNewAccountBtn() throws Exception {
    	
    	Reporter.log("Click the 'Create new account' button.");
    	interact.Click(waitFor.ElementVisible(CreateNewAccount_Btn));
    	
    }
    
    public void VerifyPasswordStrengthCtrNotPresent() throws Exception {
    	
    	Reporter.log("Verify the password strength container is not present.");
    	waitFor.ElementNotVisible(PasswordStrength_Ctr);
    	
    }
    
    public void VerifyPasswordStrengthTxtPresent(List<String> strengthMessages) throws Exception {
    	
    	for (String message : strengthMessages) {
    		Reporter.log("Verify that password strength message '" + message + "' is present.");
    		waitFor.ElementContainsText(PasswordStrength_Ctr, message);
    	}
    	
    }
    
    public void VerifyPasswordStrengthTxtNotPresent(List<String> strengthMessages) throws Exception {
    	
    	for (String message : strengthMessages) {
    		Reporter.log("Verify that password strength message '" + message + "' is not present.");
    		waitFor.ElementNotContainsText(PasswordStrength_Ctr, message);
    	}
    	
    }
    
    public void VerifyPasswordsMatch(Boolean match) throws Exception {
    	
    	if (match == true) {
    		Reporter.log("Verify password match text equals 'yes'.");
    		waitFor.ElementContainsText(PasswordMatch_Ctr, "yes");
    	}
    	else {
    		Reporter.log("Verify password match text equals 'no'.");
    		waitFor.ElementContainsText(PasswordMatch_Ctr, "no");
    	}
    }
    
    public void VerifyUsernameNotPresent() throws Exception {
    	
    	Reporter.log("Verify the 'Username' text box is not present.");
    	waitFor.ElementNotPresent(Username_Txb);
    	
    }
    
    public void VerifyPasswordNotPresent() throws Exception {
    	
    	Reporter.log("Verify the 'Password' and 'Password Confirm' text boxes are not present.");
    	waitFor.ElementNotPresent(Password_Txb);
    	
    }

    public void VerifyUsernameValueAndIsDisabled(String userName) throws Exception {
    	
    	Reporter.log("Verify the 'Username' value is '" + userName + "' and the text box is disabled.");
    	WebElement ele = waitFor.ElementVisible(Username_Txb);
    	Assert.assertEquals(userName, ele.getAttribute("value"));
    	Assert.assertFalse(ele.isEnabled());
    	
    }
    
    public void VerifyEmailAddressValueAndIsDisabled(String email) throws Exception {
    	
    	Reporter.log("Verify the 'E-mail address' value is '" + email + "' and the text box is disabled.");
    	WebElement ele = waitFor.ElementVisible(EmailAddress_Txb);
    	Assert.assertEquals(email, ele.getAttribute("value"));
    	Assert.assertFalse(ele.isEnabled());
    	
    }
    
    public void VerifyPasswordEnabled() throws Exception {
    	
    	Reporter.log("Verify the 'Password' text box is enabled.");
    	waitFor.ElementEnabled(Password_Txb);
    	
    }
    
    public void VerifyEmailAddressValueAndIsEnabled(String email) throws Exception {
    	
    	Reporter.log("Verify the 'E-mail address' value is '" + email + "' and the text box is disabled.");
    	WebElement ele = waitFor.ElementVisible(EmailAddress_Txb);
    	Assert.assertEquals(email, ele.getAttribute("value"));
    	Assert.assertFalse(ele.isEnabled());
    	
    }
    
    public void VerifyRoleCbxPresentAndEnabled(List<String> allRoles) throws Exception {
    	
    	for (String role : allRoles) {
    		WebElement ele = waitFor.ElementVisible(Role_Cbx(role));
    		Reporter.log("Verify the role '" + role + "' check box is present and enabled.");
    		Assert.assertTrue(ele.isDisplayed());
    		Assert.assertTrue(ele.isEnabled());
    	}
    	
    }
    
    public void VerifyRoleCbxNotPresent(List<String> allRoles) throws Exception {
    	
    	for (String role : allRoles) {
    		waitFor.ElementNotPresent(Role_Cbx(role));
    	}
    	
    }
    
    public void ClickChangeYourPasswordLnk() throws Exception {
    	
    	Reporter.log("Click the 'Change your password' link.");
    	interact.Click(waitFor.ElementVisible(ChangeYourPassword_Lnk));
    	
    }
    
    public String AddDefaultUser(List<String> roles, Boolean isSSO) throws Exception {
    	
    	navigation.People();
    	people.ClickAddUserLnk();
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
        
        return userName;
    }
    
  
}

