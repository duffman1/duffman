package com.nbcuni.test.publisher.tests.Video.Configuration;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXDataClient;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class ConfigurationMultipleMPXAccounts extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1073
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17442647774
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void ConfigurationMultipleMPXAccounts_TC1073() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Setup
        MPXDataClient mpxDataClient = new MPXDataClient(webDriver);
        mpxDataClient.SignInToMPXDataClient("media", applib.getMPXUsername(), applib.getMPXPassword());
        List<String> accountNames = mpxDataClient.GetAllMPXAccounts();
        applib.openApplication();
        
        //Step 2
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToActiveFrame();
        
        //Setup
        Settings settings = new Settings(webDriver, applib);
        if (settings.IsMPXConfigured() == true) {
        	
        	settings.VerifyImportAccountOptions(accountNames);
        }
        else { 
        	
        	//Step 3
        	settings.EnterUsername0(applib.getMPXUsername());
        	settings.EnterPassword0(applib.getMPXPassword());
        	
        	//Step 4
        	settings.ClickConnectToMPXBtn();
        	contentParent.VerifyMessageStatus("Login successful");
        	settings.VerifyImportAccountOptions(accountNames);
            
        	//Step 5
            settings.SelectImportAccount1("DB TV");
        	settings.ClickSetImportAccountBtn();
        	contentParent.VerifyMessageStatus("Setting import account \"DB%20TV\" for account");
        	contentParent.VerifyMessageStatus("Retrieving import account information for \"DB%20TV\".");
        	settings.VerifyImportAccountsDisabled();
        	overlay.ClickCloseOverlayLnk();
        	
        	//Step 6 TODO
        	
        	//Step 7
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            overlay.SwitchToActiveFrame();
            MPXMedia mpxMedia = new MPXMedia(webDriver);
            mpxMedia.ExpandMPXMedia();
            mpxMedia.SelectMPXPlayerForAccount1("Auditude Demo player");
            mpxMedia.ClickSyncMPXMediaNowLnk();
            contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
        	
        	//Step 8
            Cron cron = new Cron(webDriver, applib);
            cron.ClickRunCronToCompleteImportLnk();
            overlay.SwitchToActiveFrame();
            cron.ClickRunCronBtn();
            contentParent.VerifyMessageStatus("Cron run successfully.");
            overlay.ClickCloseOverlayLnk();
            
            //Step 9
            taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            overlay.SwitchToActiveFrame();
            SearchFor searchFor = new SearchFor(webDriver, applib);
            searchFor.EnterTitle("Automation1");
            searchFor.ClickApplyBtn();
            searchFor.VerifySearchResultsPresent(Arrays.asList("Automation1"));

            //Step 10
            taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
            settings.ExpandMPXLogin();
        	settings.ClickAddAccountBtn();
        	settings.EnterUsername0(applib.getMPXUsername());
        	settings.EnterPassword0(applib.getMPXPassword());
        	settings.ClickUpdateBtn();
        	settings.SelectImportAccount2("NBCU TVE Stage - Golf Channel");
        	settings.ClickSetImportAccountBtn();
        	contentParent.VerifyMessageStatus("Setting import account \"NBCU%20TVE%20Stage%20-%20Golf%20Channel\" for account");
        	contentParent.VerifyMessageStatus("Retrieving import account information for \"NBCU%20TVE%20Stage%20-%20Golf%20Channel\".");
        	settings.VerifyImportAccountsDisabled();
        	settings.ClickSaveConfigurationsBtn();
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            mpxMedia.ExpandMPXMedia();
            mpxMedia.SelectMPXPlayerForAccount2("Auditude Demo player");
            mpxMedia.ClickSyncMPXMediaNowLnk();
            contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
            cron.ClickRunCronToCompleteImportLnk();
            cron.ClickRunCronBtn();
            contentParent.VerifyMessageStatus("Cron run successfully.");
            taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            searchFor.EnterTitle("The Golf Fix 2/4");
            searchFor.ClickApplyBtn();
            searchFor.VerifySearchResultsPresent(Arrays.asList("The Golf Fix 2/4"));
        	
        }
        
    }
}
