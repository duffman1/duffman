package com.nbcuni.test.publisher.pageobjects.People;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Add User Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 26, 2014
 *********************************************/

public class AddUser {

	private static CustomWebDriver webDriver;
	private static Taxonomy taxonomy;
	private static Overlay overlay;
	private static ContentParent contentParent;
	private static Random random;
	
    //PAGE OBJECT CONSTRUCTOR
    public AddUser(CustomWebDriver webDriver, AppLib applib) {
    	AddUser.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        taxonomy = new Taxonomy(webDriver);
        overlay = new Overlay(webDriver, applib);
        contentParent = new ContentParent(webDriver, applib);
        random = new Random();
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
    
    private static WebElement Role_Cbx(String role) {
    	return webDriver.findElement(By.xpath("//label[text()='" + role + " ']/../input"));
    }
    
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
    
    public String AddDefaultUser(List<String> roles) throws Exception {
    	
    	taxonomy.NavigateSite("People>>Add user");
        overlay.SwitchToActiveFrame();
        String userName = random.GetCharacterString(15) + "@" + random.GetCharacterString(15) + ".com";
        this.EnterUsername(userName);
        this.EnterEmailAddress(userName);
        String passWord = "pa55word";
        this.EnterPassword(passWord);
        this.EnterConfirmPassword(passWord);
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

