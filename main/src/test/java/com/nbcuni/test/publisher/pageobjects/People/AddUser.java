package com.nbcuni.test.publisher.pageobjects.People;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Add User Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class AddUser {

    //PAGE OBJECT CONSTRUCTOR
    public AddUser(CustomWebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[@id='edit-name']")
    private static WebElement Username_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-mail']")
    private static WebElement EmailAddress_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-pass-pass1']")
    private static WebElement Password_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-pass-pass2']")
    private static WebElement ConfirmPassword_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-roles-4']")
    private static WebElement Role_Editor_Cbx;
    
    @FindBy(how = How.ID, using = "edit-roles-5")
    private static WebElement Role_SeniorEditor_Cbx;
    
    @FindBy(how = How.ID, using = "edit-notify")
    private static WebElement NotifyUserNewAccount_Cbx;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-field-first-name-und-0-value']")
    private static WebElement FirstName_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-field-last-name-und-0-value']")
    private static WebElement LastName_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Create new account']")
    private static WebElement CreateNewAccount_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterUsername(String userName) throws Exception {
    	
    	Reporter.log("Enter '" + userName + "' in the 'Username' text box.");
    	Username_Txb.sendKeys(userName);
    	
    }
    
    public void EnterEmailAddress(String emailAddress) throws Exception {
    	
    	Reporter.log("Enter '" + emailAddress + "' in the 'E-mail address' text box.");
    	EmailAddress_Txb.sendKeys(emailAddress);
    	
    }
    
    public void EnterPassword(String passWord) throws Exception {
    	
    	Reporter.log("Enter '" + passWord + "' in the 'Password' text box.");
    	Password_Txb.sendKeys(passWord);
    	
    }
    
    public void EnterConfirmPassword(String passWord) throws Exception {
    	
    	Reporter.log("Enter '" + passWord + "' in the 'Confirm password' text box.");
    	ConfirmPassword_Txb.sendKeys(passWord);
    }
    
    public void ClickEditorRoleCbx() throws Exception {
    	
    	Reporter.log("Check the 'editor' checkbox.");
    	Role_Editor_Cbx.click();
    }
    
    public void ClickSeniorEditorRoleCbx() throws Exception {
    	
    	Reporter.log("Check the 'senior editor' checkbox.");
    	Role_SeniorEditor_Cbx.click();
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
    
    
    
    
    
    
    
   
  
}

