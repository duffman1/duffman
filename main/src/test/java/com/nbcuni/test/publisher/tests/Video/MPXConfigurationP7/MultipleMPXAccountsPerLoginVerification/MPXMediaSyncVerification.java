package com.nbcuni.test.publisher.tests.Video.MPXConfigurationP7.MultipleMPXAccountsPerLoginVerification;


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


public class MPXMediaSyncVerification extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login Publisher 7 using Drupal 1 credentials.,Login succeeds.
     * Step 2 - Click on "Configuration" --> "Media" --> "Media: thePlatform mpx settings".,The user is taken to the "Media" thePlatform mpx settings" page. Three MPX sub-accounts are configured from the previous test case titled, "1_Multiple MPX Accounts".
     * Step 3 - In a new browser, access the URL, http://mpx.theplatform.com, and login to MPX using the following credentials:   Username: AdminPub7QA Password: Pa55word,The user is taken to the main mpx page.
     * Step 3b - Step missing from QC - but I'm guessing this step is to return to pub7 and nav to Content>>Files>>mpxMedia<br>
     * Step 4 - Click on the "SYNC MPXMEDIA" link.  Note: If the Sync Multimedia section is already expanded ,The section where "SYNC MPXMEDIA" link is located expands. It shows 3 "Import new mpxMedia for account <Account Name>" drop down fields, and a button called "Sync mpxMedia Now".  Note: If the Sync Multimedia section is already expanded (as after a first-time-ever MPX configuration, this step Passes. 
     * Step 5 - Click Sync mpxMedia Now. ,For each MPX account/subaccount player selected in the previous step, a success message is displayed that shows how many new mpxMedia have been queued.   
     * Step 6 - Run cron, and then click Content > Files > mpxMedia ,The user is taken to the "mpxMedia" view in the "Content" overlay. The MPX assets that have been published with respect to different players are present in the mpxMedia table. 
     * Step 7 - Logout of MPX and Publisher7.,The user logs out of MPX and Publisher 7. (NA)
     * Step 8 - Close all the browsers. ,The browsers are closed. (NA)
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke"})
    public void MPXMediaSyncVerification() throws Exception{
    	
    	//TODO - re order test steps above to follow test steps below. Automated steps were re-ordered for ease of automation purposes
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 2
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        Settings settings = new Settings(webDriver);
        if (settings.IsMPXConfigured() == true) {
            
        	//Step 2 (continued)
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
        	
        	//Step 3 
        	MPXDataClient mpxDataClient = new MPXDataClient(webDriver);
            mpxDataClient.SignInToMPXDataClient("media", "mpx/AdminPub7QA", "Pa55word");
            mpxDataClient.ChooseMPXAccount(configuredAccounts.get(0));
            List<String> allMediaTitlesForAccount1 = mpxDataClient.GetAllMPXObjectFields("Media", "title");
            
            //Step 3 (continued)
            List<String> allMediaTitlesForAccount2 = null;
            if (configuredAccounts.size() >= 2) {
            
            	mpxDataClient.SignInToMPXDataClient("media", "mpx/AdminPub7QA", "Pa55word");
            	mpxDataClient.ChooseMPXAccount(configuredAccounts.get(1));
            	allMediaTitlesForAccount2 = mpxDataClient.GetAllMPXObjectFields("Media", "title");
            }
            
            //Step 3 (continued)
            List<String> allMediaTitlesForAccount3 = null;
            if (configuredAccounts.size() >= 3) {
            	
            	mpxDataClient.SignInToMPXDataClient("media", "mpx/AdminPub7QA", "Pa55word");
            	mpxDataClient.ChooseMPXAccount(configuredAccounts.get(2));
            	allMediaTitlesForAccount3 = mpxDataClient.GetAllMPXObjectFields("Media", "title");
            }
        	
        	//Step 3b
            applib.openApplication();
            taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            overlay.SwitchToFrame("Content");
            
            //Step 4
            MPXMedia mpxMedia = new MPXMedia(webDriver);
            mpxMedia.ExpandMPXMedia();
            
            //Step 5
            mpxMedia.ClickSyncMPXMediaNowLnk();
            ContentParent contentParent = new ContentParent(webDriver);
            //contentParent.VerifyMessageStatus("players returned for account");
            contentParent.VerifyMessageStatus("All mpxMedia is up to date for account \"" + configuredAccounts.get(0) + "\"");
            if (configuredAccounts.size() >= 2) {
            	contentParent.VerifyMessageStatus("All mpxMedia is up to date for account \"" + configuredAccounts.get(1) + "\""); 
            }
            if (configuredAccounts.size() >= 3) {
            	contentParent.VerifyMessageStatus("All mpxMedia is up to date for account \"" + configuredAccounts.get(2) + "\"");   
            }
            
            //Step 6
            Cron cron = new Cron(webDriver);
            cron.ClickRunCronToCompleteImportLnk();
            overlay.SwitchToFrame("Cron");
            cron.ClickRunCronBtn();
            contentParent.VerifyMessageStatus("Processed video import/update via cron for all accounts.");
            contentParent.VerifyMessageStatus("Cron run successfully.");
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            overlay.SwitchToFrame("Content");
            SearchFor searchFor = new SearchFor(webDriver);
            searchFor.EnterTitle(allMediaTitlesForAccount1.get(0));
            searchFor.ClickApplyBtn();
            searchFor.VerifySearchResultsPresent(Arrays.asList(allMediaTitlesForAccount1.get(0)));
            
        }
        else {
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        }
    }
}
