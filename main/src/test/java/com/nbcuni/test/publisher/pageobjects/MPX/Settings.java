package com.nbcuni.test.publisher.pageobjects.MPX;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/*********************************************
 * publisher.nbcuni.com Settings Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 29, 2013
 *********************************************/

public class Settings {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    
    private static String MPXLogin_Lnk = "//fieldset[@id='edit-accounts']//a";
    private static String Username0_Txb = "//input[@id='edit-accounts-new-0-theplatform-username']";
    private static String Password0_Txb = "//input[@id='edit-accounts-new-0-theplatform-password']";
    private static String Username1_Txb = "//input[@id='edit-accounts-existing-1-theplatform-username']";
    private static String Password1_Txb = "//input[@id='edit-accounts-existing-1-theplatform-password']";
    private static String Update_Btn = "//input[@value='Update']";
    private static String SelectImportAccount1_Ddl = "(//select[contains(@id, 'edit-import-accounts-settings')])[1]";
    private static String SelectImportAccount2_Ddl = "(//select[contains(@id, 'edit-import-accounts-settings')])[2]";
    private static String SelectImportAccount3_Ddl = "(//select[contains(@id, 'edit-import-accounts-settings')])[3]";
    private static String SelectImportAccount4_Ddl = "(//select[contains(@id, 'edit-import-accounts-settings')])[4]";
    private static String SelectImportAccount5_Ddl = "(//select[contains(@id, 'edit-import-accounts-settings')])[5]";
    private static String SetImportAccount_Btn = "//input[@id='edit-import-accounts-actions-submit']";
    private static String SaveConfigurations_Btn = "//input[@id='edit-submit']";
    private static String ConnectToMPX_Btn = "//input[@value='Connect to MPX']";
    private static String AddAccount_Btn = "//input[@value='Add Account']";
    private static String DisplayMPXDebugMessageLevel_Ddl = "//select[@id='edit-media-theplatform-mpx-output-message-watchdog-severity']";
    private static String DeleteAccount_Btns = "//input[contains(@value, 'Delete Account')]";
    private static String Delete_Btn = "//input[@value='Delete']";
    
    public Settings(CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        
    }
    
    public void ExpandMPXLogin() throws Exception {
    	
    	if (new WebDriverWait(webDriver, 30).until(ExpectedConditions.presenceOfElementLocated(
    			By.xpath(AddAccount_Btn))).isDisplayed() == false) {
    		
    		webDriver.findElement(By.xpath(MPXLogin_Lnk)).click();
    		
    		Reporter.log("Wait for 'Update' button to be visible.");
    		new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Update_Btn)));
    	}
    	
    }
    
    public void EnterUsername0(String userName) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Username0_Txb))));
    	el.clear();
    	el.sendKeys(userName);
    } 
    
    public void EnterPassword0(String passWord) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Password0_Txb))));
    	el.sendKeys(passWord);
    }

    public void EnterUsername1(String userName) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Username1_Txb))));
    	el.clear();
    	el.sendKeys(userName);
    }
    
    public void EnterPassword1(String passWord) throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Password1_Txb))));
    	el.sendKeys(passWord);
    }
    
    public void ClickUpdateBtn() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Update_Btn))));
    	el.click();
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
    	
    	Select ddl1 = new Select(webDriver.findElement(By.xpath(SelectImportAccount1_Ddl)));
    	
    	try {
    		
    		ddl2 = new Select(webDriver.findElement(By.xpath(SelectImportAccount2_Ddl)));
    		ddl2Present = true;
    	}
    	catch (Exception e) {}
    	
    	try {
    		
    		ddl3 = new Select(webDriver.findElement(By.xpath(SelectImportAccount3_Ddl)));
    		ddl3Present = true;
    	}
    	catch (Exception e) {}

        try {

            ddl4 = new Select(webDriver.findElement(By.xpath(SelectImportAccount4_Ddl)));
            ddl4Present = true;
        }
        catch (Exception e) {}
        
        try {

            ddl5 = new Select(webDriver.findElement(By.xpath(SelectImportAccount5_Ddl)));
            ddl5Present = true;
        }
        catch (Exception e) {}
    	
    	List<WebElement> listOptions = ddl1.getOptions();
    	if (ddl2Present == true) { listOptions.addAll(ddl2.getOptions()); }
    	if (ddl3Present == true) { listOptions.addAll(ddl3.getOptions()); }
        if (ddl4Present == true) { listOptions.addAll(ddl4.getOptions()); }
        if (ddl5Present == true) { listOptions.addAll(ddl5.getOptions()); }
    	
    	List<String> optionValues = new ArrayList<String>();
    	for (WebElement option : listOptions) {
    		
    		optionValues.add(option.getText());
    	}
    	
    	optionValues.remove("- Select -");
    	
    	for (String option : optionValues) {
    		Assert.assertTrue(accountNames.contains(option));
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    public boolean IsMPXConfigured() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	boolean mpxAlreadyConfigured = true;
    	
    	try {
    		
    		webDriver.findElement(By.xpath(ConnectToMPX_Btn));
    		mpxAlreadyConfigured = false;
    	}
    	catch (NoSuchElementException e) {
    		mpxAlreadyConfigured = true;
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    	return mpxAlreadyConfigured;
    }
    
    public void ClickSetImportAccountBtn() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(SetImportAccount_Btn))));
    	el.click();
    }
    
    public void ClickSaveConfigurationsBtn() throws Exception {
    	
    	WebElement el = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(SaveConfigurations_Btn))));
    	el.click();
    }
    
    public void ClickConnectToMPXBtn() throws Exception {
    	
    	webDriver.findElement(By.xpath(ConnectToMPX_Btn)).click();
    }
    
    public void ClickAddAccountBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(AddAccount_Btn)))).click();
    }
    
    public void SelectImportAccount1(String account) throws Exception {
    	
    	Select ddl = new Select(webDriver.findElement(By.xpath(SelectImportAccount1_Ddl)));
    	ddl.selectByVisibleText(account);
    }
    
    public void SelectImportAccount2(String account) throws Exception {
    	
    	Select ddl = new Select(webDriver.findElement(By.xpath(SelectImportAccount2_Ddl)));
    	ddl.selectByVisibleText(account);
    }
    
    public void SelectImportAccount3(String account) throws Exception {
    	
    	Select ddl = new Select(webDriver.findElement(By.xpath(SelectImportAccount3_Ddl)));
    	ddl.selectByVisibleText(account);
    }
    
    public void SelectImportAccount4(String account) throws Exception {
    	
    	Select ddl = new Select(webDriver.findElement(By.xpath(SelectImportAccount4_Ddl)));
    	ddl.selectByVisibleText(account);
    }
    
    public void VerifyImportAccountsDisabled() throws Exception {
    	
    	Select ddl1 = new Select(webDriver.findElement(By.xpath(SelectImportAccount1_Ddl)));
    	Select ddl2 = new Select(webDriver.findElement(By.xpath(SelectImportAccount2_Ddl)));
    	Select ddl3 = new Select(webDriver.findElement(By.xpath(SelectImportAccount3_Ddl)));
    	
    	Assert.assertTrue(ddl1.getFirstSelectedOption().isEnabled() == false);
    	Assert.assertTrue(ddl1.getFirstSelectedOption().isEnabled() == false);
    	Assert.assertTrue(ddl1.getFirstSelectedOption().isEnabled() == false);
    }
    
    public void VerifyUsernameValues(String userName, int txbCount) throws Exception {
    	
    	List<WebElement> allUsernameTxbs = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOfAllElementsLocatedBy(By.xpath("//input[contains(@id, 'edit-accounts-existing')][contains(@id, 'username')]")));
    	
    	Assert.assertEquals(allUsernameTxbs.size(), txbCount);
    	
    	for (WebElement el : allUsernameTxbs) {
    	
    		Assert.assertEquals(el.getAttribute("value"), userName);
    	}
    }
    
    public List<String> GetImportAccountSelectedOptions() throws Exception {
    	
    	//TODO - clean this up and make it a bit more efficient
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	boolean ddl2Present = false;
    	boolean ddl3Present = false;
        boolean ddl4Present = false;
    	Select ddl2 = null;
    	Select ddl3 = null;
        Select ddl4 = null;
    	
    	Select ddl1 = new Select(new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(SelectImportAccount1_Ddl)))));
    	
    	try {
    		
    		ddl2 = new Select(webDriver.findElement(By.xpath(SelectImportAccount2_Ddl)));
    		ddl2Present = true;
    	}
    	catch (Exception e) {}
    	
    	try {
    		
    		ddl3 = new Select(webDriver.findElement(By.xpath(SelectImportAccount3_Ddl)));
    		ddl3Present = true;
    	}
    	catch (Exception e) {}

        try {

            ddl4 = new Select(webDriver.findElement(By.xpath(SelectImportAccount4_Ddl)));
            ddl4Present = true;
        }
        catch (Exception e) {}
    	
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
    	Select el = new Select(webDriver.findElement(By.xpath(DisplayMPXDebugMessageLevel_Ddl)));
    	if (el.getFirstSelectedOption().getText().equals("Extremely Verbose")) {
    		el.selectByVisibleText("Advanced");
    		webDriver.findElement(By.xpath(SaveConfigurations_Btn)).click();
    	}
    }
    
    public void SelectMPXDebugMessageLevel(String level) {
    	
    	Reporter.log("Select '" + level + "' from the 'MPX Debug Message Level' drop down list.");
    	new Select(webDriver.findElement(By.xpath(DisplayMPXDebugMessageLevel_Ddl))).selectByVisibleText(level);
    	
    }
    
    public List<WebElement> GetAllDeleteAccountButtons() throws Exception {
    	
    	Reporter.log("Get each of the 'Delete Account' buttons");
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	List<WebElement> allElements = webDriver.findElements(By.xpath(DeleteAccount_Btns));
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    	
    	return allElements;
    }
    
    public void ClickDeleteBtn() throws Exception {
    	
    	Reporter.log("Click the 'Delete' button.");
    	webDriver.findElement(By.xpath(Delete_Btn)).click();;
    }
  
}

