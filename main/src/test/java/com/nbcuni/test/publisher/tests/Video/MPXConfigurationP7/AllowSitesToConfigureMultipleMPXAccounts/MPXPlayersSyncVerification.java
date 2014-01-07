package com.nbcuni.test.publisher.tests.Video.MPXConfigurationP7.AllowSitesToConfigureMultipleMPXAccounts;


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
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.errorchecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.queues.DeleteQueue;
import com.nbcuni.test.publisher.pageobjects.queues.Queues;
import com.nbcuni.test.publisher.pageobjects.queues.QueuesRevisionList;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class MPXPlayersSyncVerification extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1a - Login Publisher 7 using Drupal 1 credentials<br>
     * Step 1b - Enable "Pub MPX" module<br>
     * Step 2a - Click on "Configuration" --> "Media" --> "Media: thePlatform mpx settings"<br>
     * Step 2b - Get the values of the configured account labels<br>
     * Step 3 - In a new browser, access the URL, http://mpx.theplatform.com, and login to MPX using the following credentials:   Username: AdminPub7QA Password: Pa55word<br>
     * Step 4a - Synch media players<br>
     * Step 4b - Return to the Publisher7 browser window, click on "Content" --> "Files" --> "mpxMedia"<br> 
     * Step 5 - Click on the "SYNC MPXMEDIA" link.  Note: In a first-time-ever configuration after one or multiple MPX accounts/subaccounts have been successfully configured per Test 2813, the Sync MPXmedia section is displayed already expanded, and clicking Sync MPXmedia collapses it. If you encouter this condition, ensure that the Sync MPXMedia section is expanded, mark this step as Passed, and go to the next step. ,The section where "SYNC MPXMEDIA" link is located expands. It shows 3 "Import new mpxMedia for account <Account Name>" drop down fields, and a button called "Sync mpxMedia Now".
     * Step 6 - Return to the MPX browser window, in the "Account" dropdown field, select the value with the same name in the first "Import new mpxMedia for account <Account name>" field.,The account is selected, and after a Switching accounts message is displayed, a new set of mpx assets appear in the table. 
     * Step 7 - Click on any MPX asset, click on the "< >" button in the "Preview" section, then note down all values in the first drop down field that appears underneath. ,The user notes down all player values that appears in the drop down field.  Note: If the list requires vertical scrolling, screen-capturing and compositing the list may be the best approach to captuiring all of the player values. 
     * Step 8 - Return to the Publisher7 browser window, navigate to the drop down field, "Import new mpxMedia for account", expand this field, and verify that the values noted from the mpx page ("Preview" section above) are present in this drop down field from Publisher7.  Note: MPX and Publisher sort the list differently such that entry-by-entry verification is tedious. Verifying that the number of player entries in the MPX and Publisher lists is equal is sufficient to Pass this step. ,The player values are present as expected in the "Import new mpxMedia for account" field in Publisher7.
     * Step 9 - Return to the MPX browser window, in the "Account" dropdown field, select the value with the same name in the second "Import new mpxMedia for account <Account name>" field.,The account is selected, and a new set of mpx assets appear in the table.
     * Step 10 - Repeat steps 7-8 for the second "Import new mpxMedia for account <Account name>" field.,The player values are present as expected in the second "Import new mpxMedia for account" field in Publisher7.
     * Step 11 - Return to the MPX browser window, in the "Account" dropdown field, select the value with the same name in the third "Import new mpxMedia for account <Account name>" field.,The account is selected, and a new set of mpx assets appear in the table.
     * Step 12 - Repeat steps 7-8 for the third "Import new mpxMedia for account <Account name>" field.,The player values are present as expected in the third "Import new mpxMedia for account" field in Publisher7.  Note: Verifying that the same number of players is imported suffices for this test. 
	 * Step 13 - Click on "Content" --> "Files" --> "mpxPlayers".,The user is taken to the "mpxPlayers" view in the "Content" overlay.
     * Step 14 - Click on the "SYNC MPXPLAYERS" link.,The section where "SYNC MPXMEDIA" link is located expands. It shows a note, and a button, "Sync mpxPlayers Now".
     * Step 15 - Click on the button, "Sync mpxPlayers Now".,A success message is displayed:  <Number> mpxPlayers returned. <Number> mpxPlayers created. <Number> existing mpxPlayers updated. <Number> existing mpxPlayers inactivated". 
     * Step 16 - From the players noted down in Step 7, verify that all of them appear in the mpxPlayers table with no duplicates. ,All of the players appear as expected with no duplicates.<br>   
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke"})
    public void MPXPlayersSyncVerification() throws Exception{
    	
    	//TODO - re order test steps above to follow test steps below. Automated steps were re-ordered for ease of automation purposes
    	
    	//Step
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        Settings settings = new Settings(webDriver);
        if (settings.IsMPXConfigured() == true) {
            
        	//Step
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
        	
        	//Step
        	MPXDataClient mpxDataClient = new MPXDataClient(webDriver);
            mpxDataClient.SignInToMPXDataClient("player", "mpx/AdminPub7QA", "Pa55word");
            mpxDataClient.ChooseMPXAccount(configuredAccounts.get(0));
            List<String> allActivePlayerTitlesForAccount1 = mpxDataClient.GetAllActivePlayers();
            mpxDataClient.SignInToMPXDataClient("player", "mpx/AdminPub7QA", "Pa55word");
            mpxDataClient.ChooseMPXAccount(configuredAccounts.get(0));
            List<String> allPlayerTitlesForAccount1 = mpxDataClient.GetAllPlayers();
            
            //Step
            List<String> allActivePlayerTitlesForAccount2 = null;
            List<String> allPlayerTitlesForAccount2 = null;
            if (configuredAccounts.size() >= 2) {
            	mpxDataClient.SignInToMPXDataClient("player", "mpx/AdminPub7QA", "Pa55word");
            	mpxDataClient.ChooseMPXAccount(configuredAccounts.get(1));
            	allActivePlayerTitlesForAccount2 = mpxDataClient.GetAllActivePlayers();
            	mpxDataClient.SignInToMPXDataClient("player", "mpx/AdminPub7QA", "Pa55word");
            	mpxDataClient.ChooseMPXAccount(configuredAccounts.get(1));
            	allPlayerTitlesForAccount2 = mpxDataClient.GetAllPlayers();
            }
            
            //Step
            List<String> allActivePlayerTitlesForAccount3 = null;
            List<String> allPlayerTitlesForAccount3 = null;
            if (configuredAccounts.size() >= 3) {
            	mpxDataClient.SignInToMPXDataClient("player", "mpx/AdminPub7QA", "Pa55word");
            	mpxDataClient.ChooseMPXAccount(configuredAccounts.get(2));
            	allActivePlayerTitlesForAccount3 = mpxDataClient.GetAllActivePlayers();
            	mpxDataClient.SignInToMPXDataClient("player", "mpx/AdminPub7QA", "Pa55word");
            	mpxDataClient.ChooseMPXAccount(configuredAccounts.get(2));
            	allPlayerTitlesForAccount3 = mpxDataClient.GetAllPlayers();
            }
        	
        	//Step
            applib.openApplication();
            taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
            overlay.SwitchToFrame("Content");
            MPXPlayers MPXPlayers = new MPXPlayers(webDriver);
            MPXPlayers.ClickSyncMPXPlayersLnk();
            MPXPlayers.ClickSyncMPXPlayersNowLnk();
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.VerifyMessageStatus("players returned for account");
            Integer playerCountForAccount1 = allPlayerTitlesForAccount1.size();
            contentParent.VerifyMessageStatus(playerCountForAccount1.toString() + " players returned for account \"" + configuredAccounts.get(0) + "\"");
            if (configuredAccounts.size() >= 2) {
            	Integer playerCountForAccount2 = allPlayerTitlesForAccount2.size();
            	contentParent.VerifyMessageStatus(playerCountForAccount2.toString() + " players returned for account \"" + configuredAccounts.get(1) + "\""); 
            }
            if (configuredAccounts.size() >= 3) {
            	Integer playerCountForAccount3 = allPlayerTitlesForAccount3.size();
            	contentParent.VerifyMessageStatus(playerCountForAccount3.toString() + " players returned for account \"" + configuredAccounts.get(2) + "\"");   
            }
            
            //Step
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            overlay.SwitchToFrame("Content");
            MPXMedia mpxMedia = new MPXMedia(webDriver);
            mpxMedia.ExpandMPXMedia();
            
            //Step 
            List<String> allLabels = new ArrayList<String>();
            for (String label : configuredAccounts) {
            	
            	allLabels.add("Import new mpxMedia for account \"" + label + "\"");
            }		
            mpxMedia.VerifyImportAccountLabels(allLabels);
            
            //Step
            mpxMedia.VerifyImportAccountPlayerOptions(configuredAccounts.get(0), allActivePlayerTitlesForAccount1);
            if (configuredAccounts.size() >= 2) {
            	mpxMedia.VerifyImportAccountPlayerOptions(configuredAccounts.get(1), allActivePlayerTitlesForAccount2);
            }
            if (configuredAccounts.size() >= 3) {
            	mpxMedia.VerifyImportAccountPlayerOptions(configuredAccounts.get(2), allActivePlayerTitlesForAccount3);
            }
            
            
        }
        else {
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        }
    }
}
