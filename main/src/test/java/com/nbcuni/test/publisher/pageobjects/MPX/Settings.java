package com.nbcuni.test.publisher.pageobjects.MPX;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*********************************************
 * publisher.nbcuni.com Settings Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 29, 2013
 *********************************************/

public class Settings {

    private Driver webDriver;
    private AppLib applib;
    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public Settings(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        this.applib = applib;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//fieldset[@id='edit-accounts']//a")
    private WebElement AddAccounts_Lnk;
    
    @FindBy(how = How.XPATH, using = "//fieldset[@id='edit-account-list']//a")
    private WebElement AccountList_Lnk;
    
    @FindBy(how = How.CSS, using = "input[id*='edit-accounts-new-0-theplatform-username']")
    private WebElement Username_Txb;
    
    @FindBy(how = How.CSS, using = "input[id*='edit-accounts-new-0-theplatform-password']")
    private WebElement Password_Txb;
    
    @FindBy(how = How.XPATH, using = "(//select[contains(@id, 'edit-import-accounts-settings')])[1]")
    private WebElement SelectImportAccount1_Ddl;
    
    @FindBy(how = How.XPATH, using = "(//select[contains(@id, 'edit-import-accounts-settings')])[2]")
    private WebElement SelectImportAccount2_Ddl;
    
    @FindBy(how = How.XPATH, using = "(//select[contains(@id, 'edit-import-accounts-settings')])[3]")
    private WebElement SelectImportAccount3_Ddl;
    
    @FindBy(how = How.XPATH, using = "(//select[contains(@id, 'edit-import-accounts-settings')])[4]")
    private WebElement SelectImportAccount4_Ddl;
    
    @FindBy(how = How.XPATH, using = "(//select[contains(@id, 'edit-import-accounts-settings')])[5]")
    private WebElement SelectImportAccount5_Ddl;
    
    @FindBy(how = How.ID, using = "edit-import-accounts-actions-submit")
    private WebElement SetImportAccount_Btn;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveConfigurations_Btn;
    
    @FindBy(how = How.CSS, using = "input[value='Connect to MPX']")
    private WebElement ConnectToMPX_Btn;
    
    @FindBy(how = How.CSS, using = "input[value='Add New Account']")
    private WebElement AddNewAccount_Btn;
    
    @FindBy(how = How.CSS, using = "input[value='Save']")
    private WebElement Save_Btn;
    
    @FindBy(how = How.ID, using = "edit-media-theplatform-mpx-output-message-watchdog-severity")
    private WebElement DisplayMPXDebugMessageLevel_Ddl;
    
    private List<WebElement> DeleteAccount_Btns() {
     return webDriver.findElements(By.cssSelector("input[value*='Delete Account']"));
    }
    
    @FindBy(how = How.CSS, using = "input[value='Delete']")
    private WebElement Delete_Btn;
    
    private List<WebElement> AllUsername_Txbs() {
     return webDriver.findElements(By.xpath("//input[contains(@id, 'edit-accounts-existing')][contains(@id, 'username')]"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ExpandAddAccounts() throws Exception {
    
    	if (AddNewAccount_Btn.isDisplayed() == false) {
    
    		Reporter.log("Click the 'ADD ACCOUNTS' link.");
    		AddAccounts_Lnk.click();
    
    		Reporter.log("Wait for 'Add New Account' button to be visible.");
    		wait.until(ExpectedConditions.visibilityOf(AddNewAccount_Btn));
    	}
    
    }
    
    public void ExpandAccountList() throws Exception {
    
    	if (DeleteAccount_Btns().get(0).isDisplayed() == false) {
    
    		Reporter.log("Click the 'ACCOUNT LIST' link.");
    		AccountList_Lnk.click();
    
    		Reporter.log("Wait for 'Delete Account' button to be visible.");
    		wait.until(ExpectedConditions.visibilityOf(DeleteAccount_Btns().get(0)));
    	}
    
    }
    
    public void EnterUsername(String userName) throws Exception {
    
    	Reporter.log("Enter mpx username in the 'Username for New Account' text box.");
    	Username_Txb.clear();
    	Username_Txb.sendKeys(userName);
    }
    
    public void EnterPassword(String passWord) throws Exception {
    
    	Reporter.log("Enter mpx password in the 'Password for New Account' text box.");
    	Password_Txb.sendKeys(passWord);
    }

    public void ClickSaveBtn() throws Exception {
    
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }

    public void ClickAddNewAccountBtn() throws Exception {
    
    	Reporter.log("Click the 'Add New Account' button.");
    	AddNewAccount_Btn.click();
    }
    
    public void VerifyImportAccountOptions(List<String> accountNames) throws Exception {
    
     //TODO - clean this up and make it a bit more efficient
     webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    
     boolean ddl2Present = false;
     boolean ddl3Present = false;
     boolean ddl4Present = false;
     boolean ddl5Present = false;
     Select ddl2 = null;
     Select ddl3 = null;
     Select ddl4 = null;
     Select ddl5 = null;
    
     Select ddl1 = new Select(SelectImportAccount1_Ddl);
    
     try {
    
    	 ddl2 = new Select(SelectImportAccount2_Ddl);
    	 ddl2Present = true;
     }
     catch (Exception e) {}
    
     try {
    
    	 ddl3 = new Select(SelectImportAccount3_Ddl);
    	 ddl3Present = true;
     }
     catch (Exception e) {}

        try {

            ddl4 = new Select(SelectImportAccount4_Ddl);
            ddl4Present = true;
        }
        catch (Exception e) {}
        
        try {

            ddl5 = new Select(SelectImportAccount5_Ddl);
            ddl5Present = true;
        }
        catch (Exception e) {}
    
        Reporter.log("Get the account options from the 'Select Import Account for Account...' drop down lists.");
        List<WebElement> listOptions = ddl1.getOptions();
        if (ddl2Present == true) { listOptions.addAll(ddl2.getOptions()); }
        if (ddl3Present == true) { listOptions.addAll(ddl3.getOptions()); }
        if (ddl4Present == true) { listOptions.addAll(ddl4.getOptions()); }
        if (ddl5Present == true) { listOptions.addAll(ddl5.getOptions()); }
    
     List<String> optionValues = new ArrayList<String>();
     for (WebElement option : listOptions) {
    
    	 optionValues.add(option.getText());
     }
    
     optionValues.removeAll(Collections.singleton("- Select -"));
    
     for (String option : optionValues) {
    	 if (!accountNames.contains(option)) {
    		 Assert.fail("option '" + option + "' is not present in mpx account selections.");
    	 }
    
     }
    
     	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    public boolean IsMPXConfigured() throws Exception {
    
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    
    	Reporter.log("Check if the 'Connect to MPX' button is present. If not then MPX has not been previously enabled.");
    	boolean mpxAlreadyConfigured = true;
    
    	try {
    
    		ConnectToMPX_Btn.isDisplayed();
    		mpxAlreadyConfigured = false;
    	}
    	catch (NoSuchElementException e) {
    		mpxAlreadyConfigured = true;
    	}
    
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    
    	return mpxAlreadyConfigured;
    }
    
    public void ClickSetImportAccountBtn() throws Exception {
    
    	Reporter.log("click the 'Set Import Account' button.");
    	SetImportAccount_Btn.click();
    }
    
    public void ClickSaveConfigurationsBtn() throws Exception {
    
    	Reporter.log("Click the 'Save configurations' button.");
    	SaveConfigurations_Btn.click();
    }
    
    public void ClickConnectToMPXBtn() throws Exception {
    
    	Reporter.log("Click the 'Connect to MPX' button.");
    	ConnectToMPX_Btn.click();
    }
    
    public void SelectImportAccount1(String account) throws Exception {
    
    	Reporter.log("Select '" + account + "' from the first 'Select Import Account for...' drop down list.");
    	new Select(SelectImportAccount1_Ddl).selectByVisibleText(account);
    }
    
    public void SelectImportAccount2(String account) throws Exception {
    
    	Reporter.log("Select '" + account + "' from the second 'Select Import Account for...' drop down list.");
    	new Select(SelectImportAccount2_Ddl).selectByVisibleText(account);
    }
    
    public void SelectImportAccount3(String account) throws Exception {
    
    	Reporter.log("Select '" + account + "' from the third 'Select Import Account for...' drop down list.");
    	new Select(SelectImportAccount3_Ddl).selectByVisibleText(account);
    }
    
    public void SelectImportAccount4(String account) throws Exception {
    
    	Reporter.log("Select '" + account + "' from the fourth 'Select Import Account for...' drop down list.");
    	new Select(SelectImportAccount4_Ddl).selectByVisibleText(account);
    }
    
    public void VerifyImportAccountsDisabled() throws Exception {
    
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    
    	boolean ddl2Present = false;
    	boolean ddl3Present = false;
    
    	Select ddl1 = new Select(SelectImportAccount1_Ddl);
    	Select ddl2 = null;
    	Select ddl3 = null;
    
    	try {
    		ddl2 = new Select(SelectImportAccount2_Ddl);
    		ddl2Present = true;
    	}
    	catch (NoSuchElementException e) {
    		ddl2Present = false;
    	}
    
    	try {
    		ddl3 = new Select(SelectImportAccount3_Ddl);
    		ddl3Present = true;
    	}
    	catch (NoSuchElementException e) {
    		ddl3Present = false;
    	}
    
    	Reporter.log("Verify the 'Select Import Account for...' drop down lists are disabled.");
    	Assert.assertTrue(ddl1.getFirstSelectedOption().isEnabled() == false);
    	if (ddl2Present == true) {
    		Assert.assertTrue(ddl2.getFirstSelectedOption().isEnabled() == false);
    	}
    	if (ddl3Present == true) {
    		Assert.assertTrue(ddl3.getFirstSelectedOption().isEnabled() == false);
    	}
    
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    public void VerifyUsernameValues(String userName, int txbCount) throws Exception {
    
    	List<WebElement> allUsernameTxbs = AllUsername_Txbs();
    
    	Reporter.log("Verify the count of the Username text boxes matches '" + txbCount + "'.");
    	Assert.assertEquals(allUsernameTxbs.size(), txbCount);
    
    	for (WebElement el : allUsernameTxbs) {
    
    		Reporter.log("Verify the 'Username' text box value equals '" + userName + "'.");
    		Assert.assertEquals(el.getAttribute("value"), userName);
    	}
    }
    
    public List<String> GetImportAccountSelectedOptions() throws Exception {
    
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    
    	boolean ddl2Present = false;
    	boolean ddl3Present = false;
        boolean ddl4Present = false;
        Select ddl2 = null;
        Select ddl3 = null;
        Select ddl4 = null;
    
        Select ddl1 = new Select(SelectImportAccount1_Ddl);
    
        try {
    
        	ddl2 = new Select(SelectImportAccount2_Ddl);
        	ddl2Present = true;
        }
        catch (Exception e) {}
    
        try {
    
        	ddl3 = new Select(SelectImportAccount3_Ddl);
        	ddl3Present = true;
        }
        catch (Exception e) {}

        try {

            ddl4 = new Select(SelectImportAccount4_Ddl);
            ddl4Present = true;
        }
        catch (Exception e) {}
    
        Reporter.log("Get all the selected import account options and store to a list.");
        List<String> selectedOptions = new ArrayList<String>();
        selectedOptions.add(ddl1.getFirstSelectedOption().getText());
        if (ddl2Present == true) { selectedOptions.add(ddl2.getFirstSelectedOption().getText()); }
        if (ddl3Present == true) { selectedOptions.add(ddl3.getFirstSelectedOption().getText()); }
        if (ddl4Present == true) { selectedOptions.add(ddl4.getFirstSelectedOption().getText()); }
    
        webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    
        return selectedOptions;
    }
    
    public void EnsureMPXDebugMessageLevelNotVerbose() {
    
    	Reporter.log("If 'Display MPX Debug Message Level' drop down list is set to 'Extremely Verbose', set it to the appropriate level.");
    	Select el = new Select(DisplayMPXDebugMessageLevel_Ddl);
    	if (el.getFirstSelectedOption().getText().equals("Extremely Verbose")) {
    		el.selectByVisibleText("Advanced");
    		SaveConfigurations_Btn.click();
    	}
    }
    
    public void SelectMPXDebugMessageLevel(String level) {
    
    	Reporter.log("Select '" + level + "' from the 'MPX Debug Message Level' drop down list.");
    	new Select(DisplayMPXDebugMessageLevel_Ddl).selectByVisibleText(level);
    
    }
    
    public List<WebElement> GetAllDeleteAccountButtons() throws Exception {
    
    	Reporter.log("Get each of the 'Delete Account' buttons");
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	List<WebElement> allElements = DeleteAccount_Btns();
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    
    	return allElements;
    }
    
    public void ClickDeleteBtn() throws Exception {
    
    	Reporter.log("Click the 'Delete' button.");
    	Delete_Btn.click();
    }
  
}
