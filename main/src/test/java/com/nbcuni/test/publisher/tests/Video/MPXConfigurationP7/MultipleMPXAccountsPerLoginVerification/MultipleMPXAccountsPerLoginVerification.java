package com.nbcuni.test.publisher.tests.Video.MPXConfigurationP7.MultipleMPXAccountsPerLoginVerification;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.AccessDenied;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXDataClient;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.People.Permissions;
import com.nbcuni.test.publisher.pageobjects.People.Roles;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.CastCrew;
import com.nbcuni.test.publisher.pageobjects.content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.content.RevisionState;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.errorchecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.queues.DeleteQueue;
import com.nbcuni.test.publisher.pageobjects.queues.Queues;
import com.nbcuni.test.publisher.pageobjects.queues.QueuesRevisionList;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class MultipleMPXAccountsPerLoginVerification extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1a - Login Publisher 7 using Drupal 1 credentials<br>
     * Step 1b - Login to mpx data client and get all accounts<br>
     * Step 1c - Enable module "Pub MPX"<br>
     * Step 2a - Click on "Configuration" --> "Media" --> "Media: thePlatform mpx settings"<br>
     * Step 2b - If MPX already configured, verify accounts present and pass test<br>
     * Step 3 - Click on the link, "MPX LOGIN"<br>
     * Step 4 - Populate the following fields with their corresponding values, and click on the "Update" button.  Username for Account1: mpx/AdminPub7QA Password for Account1: Pa55word  Newkirk 20130820: There's no Update button in a brand-new, never-configured-before installation, just Add Account and Connect to MPX. First time entry requires clicking Connect to MPX rather than Update. Clicking Add Account instead gives false-positive success message; navigating away and back reveals no config was actually entered<br>
     * Step 5 - In a new browser, access the URL, http://mpx.theplatform.com, and login to MPX using the following credentials:  Username: AdminPub7QA Password: Pa55word NOTE - for automation purposes we are using the mpx data client<br>
     * Step 6a - Expand the "Account" drop down field, and note down all the account names. Return to the Publisher7 browser window, navigate to the drop down field, "Select Import Account for Account 1", expand this field, and verify that the values noted from the mpx page are present in this drop down field from Publisher7<br>
     * Step 6b - NOTE - if import account ddl = "DB TV" and is disabled by default, verify other accounts are present in pub 7 options and pass the test. Per Mirza (12.30.13).
     * Step 7 - Populate the field, "Select Import Account for Account 1" with the value, "DB TV", and click "Set Import Account"<br>
     * Step 8 - Click on the "Save Configuration" button.,The user gets a message, "The configuration options have been saved"<br>
     * Step 9 - Click on the link, "MPX LOGIN"<br>
     * Step 10 - Click on the button, "Add Account".,A new set of login fields titled, "Username for New Account", and "Password for New Account" appear underneath the first set of login fields.
,11,Populate the following fields with their corresponding values, and click "Update".  Username for New Account: mpx/AdminPub7QA Password for New Account: Pa55word  Note: Yes, these are the same credentials as those used at Step 5. This is not an error. ,The user logs in successfully. A new field appears in the "IMPORT ACCOUNTS" section, "Select Import Account for Account 2".
,12,Repeat Step 6 for "Select Import Account for Account 2" in Publisher7.,The "Account" values are present as expected in the "Select Import Account for Account 2" field in Publisher7.
,13,Populate the field, "Select Import Account for Account 2" with the value, "NBCU TVE Dev - NBC", and click on the "Set Import Account" button.,The user gets a message, "Import account set for account <Account # value>".   The fields , "Select Import Account for Account 1" and "Select Import Account for Account 2" are greyed out, and cannot be changed.  Note: A considerable delay may occur after Set Import Account is clicked, as a feed is being ingested. 
,14,Click on the "Save Configuration" button.,The user gets a message, "The configuration options have been saved".
,15,Click on the link, "MPX LOGIN".,The section where "MPX LOGIN" link is located expands, and has login fields along with "Add Account" & "Update" buttons.
,16,Click on the button, "Add Account".,A new set of login fields titled, "Username for New Account", and "Password for New Account" appear underneath the first set of login fields.
,17,Populate the following fields with their corresponding values, and click Update.  Username for New Account: mpx/AdminPub7QA Password for New Account: Pa55word ,The user logs in successfully. A new field appears in the "IMPORT ACCOUNTS" section, "Select Import Account for Account 3".
,18,Repeat Step 6 for "Select Import Account for Account 3" in Publisher7.,The "Account" values are present as expected in the "Select Import Account for Account 3" field in Publisher7.
,19,Populate the field, "Select Import Account for Account 3" with the value, "NBCU TVE Stage - Golf Channel", and click on the "Set Import Account" button.,The user gets a message, "Import account set for account <Account # value>" The use gets a message "All mpxPlayers imported."  The fields , "Select Import Account for Account 1" through "Select Import Account for Account 3" are greyed out, and cannot be changed. 
,20,Click on the "Save Configuration" button.,The user gets a message, "The configuration options have been saved".
,21,Click on the "Home" menu item on the top.,The user is taken to the Publisher7 home page.
,22,Click on "Configuration" --> "Media" --> "Media: thePlatform mpx settings".,The user is taken to the "Media" thePlatform mpx settings" page.
,23,Click on the link, "MPX LOGIN".,The configurations created for 3 MPX accounts are seen has having been retained for the "AdminPub7QA" user.  Note: The Password box for all three accounts will be blank. This is by design. 
,24,Click on the button, "Add Account".,A new set of login fields titled, "Username for New Account", and "Password for New Account" appear underneath the first set of login fields.
,25,Populate the following fields with their corresponding values, and then click Update.  Username for New Account: mpx/AdminPub7QA Password for New Account: Pa55word ,The user gets an error message, "There are no more sub-accounts for this account. The settings have not been saved because of the errors".
,26,Click on the "Home" menu item on the top.,The user is taken to the Publisher7 home page.
,27,Click on "Configuration" --> "Media" --> "Media: thePlatform mpx settings".,The user is taken to the "Media" thePlatform mpx settings" page.
,28,Click on the link, "MPX LOGIN". ,The configurations created for 3 MPX sub-accounts are seen has having been retained for the "AdminPub7QA" user. 

     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke"})
    public void MultipleMPXAccountsPerLoginVerification() throws Exception{
    	
    	//Step 1a
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 1b
        MPXDataClient mpxDataClient = new MPXDataClient(webDriver);
        mpxDataClient.SignInToMPXDataClient("media", "mpx/AdminPub7QA", "Pa55word");
        List<String> accountNames = mpxDataClient.GetAllMPXAccounts();
        
        //Step 1c
        applib.openApplication();
        Modules modules = new Modules(webDriver);
        modules.VerifyModuleEnabled("Pub MPX");
        
        //Step 2a
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        
        //Step 2b
        Settings settings = new Settings(webDriver);
        if (settings.IsMPXConfigured() == true) { //MPX previously configured - pass test
        	
        	settings.VerifyImportAccountOptions(accountNames);
        }
        else { //Configure MPX for multiple accounts
        	
        	//Step 3
        	settings.ClickAddAccountBtn();
        	settings.EnterUsername0("mpx/AdminPub7QA");
        	settings.EnterPassword0("Pa55word");
        	settings.ClickConnectToMPXBtn();
        	ContentParent contentParent = new ContentParent(webDriver);
        	contentParent.VerifyMessageStatus("Retrieved 5 import accounts for account 1.");
        	
        	settings.VerifyImportAccountOptions(accountNames);
            
        	settings.ClickMPXLoginLnk();
        	settings.ClickAddAccountBtn();
        	settings.EnterUsername0("mpx/AdminPub7QA");
        	settings.EnterPassword0("Pa55word");
        	settings.ClickUpdateBtn();
        	
        	settings.ClickMPXLoginLnk();
        	settings.ClickAddAccountBtn();
        	settings.EnterUsername0("mpx/AdminPub7QA");
        	settings.EnterPassword0("Pa55word");
        	settings.ClickUpdateBtn();
        	
            settings.SelectImportAccount1("DB TV");
        	settings.SelectImportAccount2("NBCU TVE Dev - NBC");
        	settings.SelectImportAccount3("NBCU TVE Stage - Golf Channel");
        	settings.ClickSetImportAccountBtn();
        	
        	contentParent.VerifyMessageStatus("Setting import account \"DB%20TV\" for account 1.");
        	contentParent.VerifyMessageStatus("Retrieving import account information for \"DB%20TV\".");
        	contentParent.VerifyMessageStatus("Setting import account \"NBCU%20TVE%20Dev%20-%20NBC\" for account 2.");
        	contentParent.VerifyMessageStatus("Retrieving import account information for \"NBCU%20TVE%20Dev%20-%20NBC\".");
        	contentParent.VerifyMessageStatus("Setting import account \"NBCU%20TVE%20Stage%20-%20Golf%20Channel\" for account 3.");
        	contentParent.VerifyMessageStatus("Retrieving import account information for \"NBCU%20TVE%20Stage%20-%20Golf%20Channel\".");
        	
        	settings.VerifyImportAccountsDisabled();
        	
        	settings.ClickSaveConfigurationsBtn();
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	
        	
        }
        
        
        
        	Assert.fail("Test under construction");
        
    }
}
