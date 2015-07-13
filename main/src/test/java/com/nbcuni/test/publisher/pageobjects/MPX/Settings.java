package com.nbcuni.test.publisher.pageobjects.MPX;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
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
    private Cron cron;
    private MPXMedia mpxMedia;
    private Config config;
    private EmberNav navigation;
    private Interact interact;
    //PAGE OBJECT CONSTRUCTOR
    public Settings(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        mpxMedia = new MPXMedia(webDriver);
        cron = new Cron(webDriver);
        config = new Config();
        navigation = new EmberNav(webDriver);
        interact = new Interact(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='Add mpx account']")
    private WebElement AddMPXAccount_Lnk;
    
    @FindBy(how = How.XPATH, using = "//td[text()='No mpx accounts available.']")
    private WebElement NoMPXAccounts_Txt;
    
    @FindBy(how = How.ID, using = "edit-username")
    private WebElement Username_Txb;
    
    @FindBy(how = How.ID, using = "edit-password")
    private WebElement Password_Txb;
    
    @FindBy(how = How.XPATH, using = "//select[@id='edit-import-account']")
    private WebElement SelectImportAccount_Ddl;
    
    @FindBy(how = How.ID, using = "edit-media-theplatform-mpx-cron-videos")
    private WebElement SyncMPXMediaOnCron_Cbx;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveConfigurations_Btn;
    
    @FindBy(how = How.CSS, using = "input[value='Continue and edit']")
    private WebElement ContinueAndEdit_Btn;
    
    @FindBy(how = How.CSS, using = "input[value='Confirm']")
    private WebElement Confirm_Btn;
    
    @FindBy(how = How.CSS, using = "input[value='Save']")
    private WebElement Save_Btn;
    
    @FindBy(how = How.ID, using = "edit-media-theplatform-mpx-output-message-watchdog-severity")
    private WebElement DisplayMPXDebugMessageLevel_Ddl;
    
    private List<WebElement> DeleteAccount_Lnks() {
     return webDriver.findElements(By.linkText("Delete"));
    }
    
    @FindBy(how = How.LINK_TEXT, using = "Delete")
    private WebElement Delete_Lnk;
    
    @FindBy(how = How.LINK_TEXT, using = "Edit")
    private WebElement Edit_Lnk;
    
    @FindBy(how = How.LINK_TEXT, using = "Players not imported")
    private WebElement PlayersNotImported_Lnk;
    
    @FindBy(how = How.LINK_TEXT, using = "Not configured")
    private WebElement NotConfigured_Lnk;
    
    private WebElement DefaultPlayer_Ddl(String player) {
    	return webDriver.findElement(By.xpath("//select[@id='edit-default-player']//option[contains(text(), '" + player + "')]"));
    }
    
    private List<WebElement> AllUsername_Txbs() {
     return webDriver.findElements(By.xpath("//input[contains(@id, 'edit-accounts-existing')][contains(@id, 'username')]"));
    }
    
    
    //PAGE OBJECT METHODS
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
    
    public void ClickPlayersNotImportedLnk() throws Exception {
        
    	Reporter.log("Click the 'Players not imported' link.");
		interact.Click(PlayersNotImported_Lnk);
//    	PlayersNotImported_Lnk.click();
    }
    
    public void ClickNotConfiguredLnk() throws Exception {
        
    	Reporter.log("Click the 'Not configured' link.");
        interact.Click(NotConfigured_Lnk);
//    	NotConfigured_Lnk.click();
    }

    public void ClickAddMPXAccountLnk() throws Exception {
    
    	Reporter.log("Click the 'Add New Account' link.");
    	AddMPXAccount_Lnk.click();
    	
    }
    
    public void ClickConfirmBtn() throws Exception {
        
    	Reporter.log("Click the 'Confirm' button.");
    	Confirm_Btn.click();
    	
    }
    
    public void SelectDefaultPlayer(String player) throws Exception {
        
    	Reporter.log("Select '" + player + "' from the 'Default Player' drop down list.");
    	DefaultPlayer_Ddl(player).click();
    	
    }
    
    public void VerifyImportAccountOptions(List<String> accountNames) throws Exception {
    
     //TODO - clean this up and make it a bit more efficient
     webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    
     Select ddl1 = new Select(SelectImportAccount_Ddl);
    
     Reporter.log("Get the account options from the 'Select Import Account for Account...' drop down lists.");
     List<WebElement> listOptions = ddl1.getOptions();
        
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
    
     	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    }
    
    public boolean IsMPXConfigured() throws Exception {
    
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    
    	Reporter.log("Check if the 'No mpx accounts available' text is present. If it is then MPX has not been previously enabled.");
    	boolean mpxAlreadyConfigured = true;
    
    	try {
    		NoMPXAccounts_Txt.isDisplayed();
    		mpxAlreadyConfigured = false;
    	}
    	catch (NoSuchElementException e) {
    		mpxAlreadyConfigured = true;
    	}
    
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    
    	return mpxAlreadyConfigured;
    }
    
    public void UnCheckSyncMPXMediaOnCronBtn() throws Exception {
    	
    	if (SyncMPXMediaOnCron_Cbx.isSelected() == true) {
    		Reporter.log("Uncheck the 'Sync mpxMedia on Cron' check box.");
    		SyncMPXMediaOnCron_Cbx.click();
    	}
    }
    
    public void CheckSyncMPXMediaOnCronBtn() throws Exception {
    	
    	if (SyncMPXMediaOnCron_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Sync mpxMedia on Cron' check box.");
    		SyncMPXMediaOnCron_Cbx.click();
    	}
    }
    
    public void ClickSaveConfigurationsBtn() throws Exception {
    
    	Reporter.log("Click the 'Save configurations' button.");
    	SaveConfigurations_Btn.click();
    }
    
    public void ClickContinueAndEditBtn() throws Exception {
    
    	Reporter.log("Click the 'Continue and edit' button.");
    	ContinueAndEdit_Btn.click();
        interact.waitPageToLoad();
    }
    
    public void SelectImportAccount(String account) throws Exception {
    
    	Reporter.log("Select '" + account + "' from the 'Import account' drop down list.");
    	new Select(SelectImportAccount_Ddl).selectByVisibleText(account);
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
    
    public List<WebElement> GetAllDeleteAccountLnks() throws Exception {
    
    	Reporter.log("Get each of the 'Delete Account' links");
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	List<WebElement> allElements = DeleteAccount_Lnks();
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    
    	return allElements;
    }
    
    public void ClickDeleteLnk() throws Exception {
    
    	Reporter.log("Click the 'Delete' link.");
    	Delete_Lnk.click();
    }
    
    public void ClickEditLnk() throws Exception {
        
    	Reporter.log("Click the 'Edit' link.");
    	Edit_Lnk.click();
    }
    
    public void ConfigureMPXIfNeeded() throws Exception {
    	
    	navigation.Configuration("Media: thePlatform mpx");
    	
    	if (this.IsMPXConfigured() == false) {
    		this.ClickAddMPXAccountLnk();
    		this.EnterUsername(config.getConfigValueString("MPXUsername"));
        	this.EnterPassword(config.getConfigValueString("MPXPassword"));
        	this.ClickContinueAndEditBtn();
        	this.SelectImportAccount("DB TV");
        	this.ClickSaveBtn();
        	
        	this.ClickPlayersNotImportedLnk();
        	mpxMedia.ClickSyncMPXPlayersNowLnk();
        	this.ClickNotConfiguredLnk();
        	this.SelectDefaultPlayer("AutomationPlayer1");
        	this.ClickSaveBtn();
        	
        	navigation.ClickPrimaryTabNavLnk("Settings");
        	this.CheckSyncMPXMediaOnCronBtn();
        	this.ClickSaveConfigurationsBtn();
        	
        	navigation.Content("Files", "mpxMedia");
        	mpxMedia.ClickSyncMPXMediaNowLnk();
        	
            cron.RunCron();
            
            navigation.Configuration("Media: thePlatform mpx");
        	navigation.ClickPrimaryTabNavLnk("Settings");
        	if (config.getConfigValueString("DrushIngestion").equals("true")) {
        		this.UnCheckSyncMPXMediaOnCronBtn();
        	}
        	else {
        		this.CheckSyncMPXMediaOnCronBtn();
        	}
        	
        	this.ClickSaveConfigurationsBtn();
        	navigation.ClickPrimaryTabNavLnk("Accounts");
            
    	}
    	else {
    		Reporter.log("MPX is already configured.");
    		
    	}
    }
    
    public void ConfigureMPXIngestionType() throws Exception {
    	
    	navigation.Configuration("Media: thePlatform mpx");
    	navigation.ClickPrimaryTabNavLnk("Settings");
    	if (config.getConfigValueString("DrushIngestion").equals("true")) {
        		this.UnCheckSyncMPXMediaOnCronBtn();
        }
        else {
        	this.CheckSyncMPXMediaOnCronBtn();
        }
        this.ClickSaveConfigurationsBtn();
        
        navigation.Content("Files", "mpxMedia");
        mpxMedia.ExpandMPXMedia();
        mpxMedia.SelectMPXPlayerForAccount("AutomationPlayer1");
        mpxMedia.ClickSyncMPXMediaNowLnk();
        
    }
    
    public void DeleteAllOldMPXFileTypes() throws Exception { //TODO - add elements from script to page factory
    	
    	//delete any old mpx account file types (DE3921)
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
        	webDriver.navigate().to(config.getConfigValueString("AppURL") + "/admin/structure/file-types");
        	List<String> eachURL = new ArrayList<String>();
        	String allURLs = null;
        	for (WebElement el : webDriver.findElements(By.xpath("//td[contains(text(), 'MPX Video for Account')][contains(text(), 'DB TV')]/..//a[text()='edit file type']"))) {
        		allURLs = allURLs + el.getAttribute("href");
        		eachURL.add(el.getAttribute("href"));
        	}
        	allURLs = allURLs.replaceAll(config.getConfigValueString("AppURL") + "/admin/structure/file-types/manage/", "").replaceAll("/edit", "");
        	String[] index = allURLs.split("mpx_video_");
        	
        	ArrayList<Integer> allIndexInts = new ArrayList<Integer>();
        	allIndexInts.removeAll(Collections.singleton("empty"));
        	for (String s : index) {
        		try {
        			allIndexInts.add(Integer.parseInt(s));
        		}
        		catch (NumberFormatException e) {}
        	}
        	Integer maxScore = Collections.max(allIndexInts);
        	for (String url : eachURL) {
        		if (!url.contains("mpx_video_" + maxScore.toString())) {
        			webDriver.navigate().to(url);
        			webDriver.findElement(By.id("edit-delete")).click();
        			webDriver.findElement(By.id("edit-submit")).click();
        		}
        	}
        }
        catch (Exception e) {}
        webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
        
    }
  
}
