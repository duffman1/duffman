package com.nbcuni.test.publisher.tests.Video.ScheduleMPXVideos;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.FileTypes.MPXFileType;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXDataClient;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXPlayers;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXMedia;
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
import com.nbcuni.test.publisher.pageobjects.content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.errorchecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.queues.DeleteQueue;
import com.nbcuni.test.publisher.pageobjects.queues.Queues;
import com.nbcuni.test.publisher.pageobjects.queues.QueuesRevisionList;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class Configuration extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login Publisher 7 using Drupal 1 credentials.,Login succeeds<br>
	 * Step 2 - Click on "Configuration" --> "Media" --> "Media: thePlatform mpx settings".,The user is taken to the "Media" thePlatform mpx settings" page.
     * Step 3 - Click on the link, "MPX LOGIN".,The section where "MPX LOGIN" link is located expands, and has login fields along with "Add Account" & "Update" buttons.
     * Step 4 - Populate the following fields with their corresponding values, and click on the "Update" button.  Username for Account1: mpx/AdminPub7QA Password for Account1: Pa55word ,The user logs in successfully. A new field appears in the "IMPORT ACCOUNTS" section, "Select Import Account for Account 1".
     * Step 5 - Populate the field, "Select Import Account for Account 1" with the value, "DB TV", and click on the "Set Import Account" button.,The user gets a message, "Import account set for account <Account # value>".    The field, "Select Import Account for Account 1" is greyed out, and cannot be changed.
     * Step 6 - Click on the "Save Configuration" button.,The user gets a message, "The configuration options have been saved".
     * Step 7 - Click on "Content" --> "Files" --> "mpxMedia".,The user is taken to the "mpxMedia" view in the "Content" overlay.
     * Step 8 - Click on the "SYNC MPXMEDIA" link.,The section where "SYNC MPXMEDIA" link is located expands. It shows 3 "Import new mpxMedia for account <Account Name>" drop down fields, and a button called "Sync mpxMedia Now".
     * Step 9 - Populate the field, "Import new mpxMedia for account "DB TV" with mpxPlayer" with the value, "Auditude Demo player", and then click on the "Sync mpxMedia Now" button. ,The user is returned to the "Content" overlay, with the "mpxMedia" view enabled. 
     * Step 10 - Click on Home --> Run cron.,The user is taken to the "Status Report" overlay where they get a successful message that the cron run has been successful.
     * Step 11 - Click on Structure --> Files types --> MPX Video for Account "DB TV". ,The user is taken to the "MPX Video for  DB TV" overlay.
     * Step 12 - Scroll down, and put a check on the "Enable MPX Value Overrides" checkbox field, and click on the "Save" button. ,The user is taken to the "File types" overlay where they get a successful message, "The file type MPX Video for Account DB TV has been updated". 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke"})
    public void Configuration() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 2 through 6 requires prior MPX configuration
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        Settings settings = new Settings(webDriver);
        if (settings.IsMPXConfigured() == true) {
        	
        	//Step
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
        	MPXDataClient mpxDataClient = new MPXDataClient(webDriver);
            mpxDataClient.SignInToMPXDataClient("player", "mpx/AdminPub7QA", "Pa55word");
            mpxDataClient.ChooseMPXAccount(configuredAccounts.get(0));
            List<String> allActivePlayerTitlesForAccount1 = mpxDataClient.GetAllActivePlayers();
            
        	//Step 7
            applib.openApplication();
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	overlay.SwitchToActiveFrame();
        	
        	//Step 8
        	MPXMedia mpxMedia = new MPXMedia(webDriver);
            mpxMedia.ExpandMPXMedia();
            
            //Step 9
            mpxMedia.SelectMPXPlayerForAccount1(allActivePlayerTitlesForAccount1.get(0));
            mpxMedia.ClickSyncMPXMediaNowLnk();
            
            //Step 10
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Home>>Run cron");
            overlay.SwitchToFrame("Status report");
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.VerifyMessageStatus("Cron ran successfully");
            
            //Step 11
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Structure>>File types>>MPX Video for Account \"" + configuredAccounts.get(0));
            overlay.SwitchToActiveFrame();
            
            //Step 12
            MPXFileType mpxFileType = new MPXFileType(webDriver);
            boolean isMPXValueOverrideEnabled = mpxFileType.EnableMPXValueOverrides();
            if (isMPXValueOverrideEnabled == false) {
            	
            	mpxFileType.ClickSaveBtn();
            	overlay.SwitchToActiveFrame();
            	contentParent.VerifyMessageStatus("The file type MPX Video for Account \"" + configuredAccounts.get(0));
            	contentParent.VerifyMessageStatus("has been updated.");
            }
        }
        else {
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        	
        }
        
    }
}
