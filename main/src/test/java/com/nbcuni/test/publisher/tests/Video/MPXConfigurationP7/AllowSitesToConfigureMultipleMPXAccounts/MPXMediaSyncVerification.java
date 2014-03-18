package com.nbcuni.test.publisher.tests.Video.MPXConfigurationP7.AllowSitesToConfigureMultipleMPXAccounts;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXDataClient;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class MPXMediaSyncVerification extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login Publisher 7 using Drupal 1 credentials.,Login succeeds.
     * Step 2 - Click on "Configuration" --> "Media" --> "Media: thePlatform mpx settings".,The user is taken to the "Media" thePlatform mpx settings" page. Three MPX sub-accounts are configured from the previous test case titled, "1_Multiple MPX Accounts".
     * Step 3 - In a new browser, access the URL, http://mpx.theplatform.com, and login to MPX using the following credentials:   Username: AdminPub7QA Password: Pa55word,The user is taken to the main mpx page.
     * Step 3b - Step missing from QC - but I'm guessing this step is to return to pub7 and nav to Content>>Files>>mpxMedia<br>
     * Step 4 - Click on the "SYNC MPXMEDIA" link.  Note: If the Sync Multimedia section is already expanded ,The section where "SYNC MPXMEDIA" link is located expands. It shows 3 "Import new mpxMedia for account <Account Name>" drop down fields, and a button called "Sync mpxMedia Now".  Note: If the Sync Multimedia section is already expanded (as after a first-time-ever MPX configuration, this step Passes. 
     * Step 5a - If not selected, select the first player option for sync<br> 
     * Step 5b - Click Sync mpxMedia Now. ,For each MPX account/subaccount player selected in the previous step, a success message is displayed that shows how many new mpxMedia have been queued.   
     * Step 6 - Run cron, and then click Content > Files > mpxMedia ,The user is taken to the "mpxMedia" view in the "Content" overlay. The MPX assets that have been published with respect to different players are present in the mpxMedia table. 
     * Step 7 - Logout of MPX and Publisher7.,The user logs out of MPX and Publisher 7. (NA)
     * Step 8 - Close all the browsers. ,The browsers are closed. (NA)
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void MPXMediaSyncVerification_Test() throws Exception{
    	
    	//NOTE - Automated steps were re-ordered for ease of automation purposes
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToActiveFrame();
        Settings settings = new Settings(webDriver, applib);
        if (settings.IsMPXConfigured() == true) {
            
        	//Step 2 (continued)
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();

            //Step 3
        	MPXDataClient mpxDataClient = new MPXDataClient(webDriver);
            mpxDataClient.SignInToMPXDataClient("player", applib.getMPXUsername(), applib.getMPXPassword());
            mpxDataClient.ChooseMPXAccount(configuredAccounts.get(0));
            List<String> allActivePlayerTitlesForAccount1 = mpxDataClient.GetAllActivePlayers();
            
            //Step 3 (continued)
            List<String> allActivePlayerTitlesForAccount2 = null;
            if (configuredAccounts.size() >= 2) {
            
            	mpxDataClient.SignInToMPXDataClient("player", applib.getMPXUsername(), applib.getMPXPassword());
                mpxDataClient.ChooseMPXAccount(configuredAccounts.get(1));
                allActivePlayerTitlesForAccount2 = mpxDataClient.GetAllActivePlayers();
            }
            
            //Step 3 (continued)
            List<String> allActivePlayerTitlesForAccount3 = null;
            if (configuredAccounts.size() >= 3) {
            	
            	mpxDataClient.SignInToMPXDataClient("player", applib.getMPXUsername(), applib.getMPXPassword());
                mpxDataClient.ChooseMPXAccount(configuredAccounts.get(2));
                allActivePlayerTitlesForAccount3 = mpxDataClient.GetAllActivePlayers();
            }

            //Step 3 (continued)
            List<String> allActivePlayerTitlesForAccount4 = null;
            if (configuredAccounts.size() >= 4) {

            	mpxDataClient.SignInToMPXDataClient("player", applib.getMPXUsername(), applib.getMPXPassword());
                mpxDataClient.ChooseMPXAccount(configuredAccounts.get(3));
                allActivePlayerTitlesForAccount4 = mpxDataClient.GetAllActivePlayers();
            }
        	
        	//Step 3b
            applib.openApplication();
            taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            overlay.SwitchToActiveFrame();
            
            //Step 4
            MPXMedia mpxMedia = new MPXMedia(webDriver);
            mpxMedia.ExpandMPXMedia();
            
            //Step 5a
            if (configuredAccounts.get(0).equals("DB TV") || configuredAccounts.get(0).equals("NBCU TVE Dev - NBC")) {
                mpxMedia.SelectMPXPlayerForAccount1("Auditude Demo player");
            }
            else {
                mpxMedia.SelectMPXPlayerForAccount1(allActivePlayerTitlesForAccount1.get(0));
            }
            if (configuredAccounts.size() >= 2) {
                if (configuredAccounts.get(1).equals("DB TV") || configuredAccounts.get(1).equals("NBCU TVE Dev - NBC")) {
                    mpxMedia.SelectMPXPlayerForAccount2("Auditude Demo player");
                }
                else {
                    mpxMedia.SelectMPXPlayerForAccount2(allActivePlayerTitlesForAccount2.get(0));
                }
            }
            if (configuredAccounts.size() >= 3) {
                mpxMedia.SelectMPXPlayerForAccount3(allActivePlayerTitlesForAccount3.get(0));
            }
            if (configuredAccounts.size() >= 4) {
                mpxMedia.SelectMPXPlayerForAccount4(allActivePlayerTitlesForAccount4.get(0));
            }

            //Step 5b
            mpxMedia.ClickSyncMPXMediaNowLnk();
            ContentParent contentParent = new ContentParent(webDriver, applib);
            contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
            
            //Step 6
            Cron cron = new Cron(webDriver);
            cron.ClickRunCronToCompleteImportLnk();
            overlay.SwitchToActiveFrame();
            cron.ClickRunCronBtn();
            contentParent.VerifyMessageStatus("Cron run successfully.");
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            overlay.SwitchToActiveFrame();
            SearchFor searchFor = new SearchFor(webDriver, applib);
            
            if (configuredAccounts.contains("DB TV") || configuredAccounts.contains("NBCU TVE Dev - NBC")) {
                searchFor.EnterTitle("Automation1");
                searchFor.ClickApplyBtn();
                searchFor.VerifySearchResultsPresent(Arrays.asList("Automation1"));

            }
            //TODO - add logic that checks for a media title in the event account1 or 2 is not configured (highly unlikely)

        }
        else {
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        }
    }
}
