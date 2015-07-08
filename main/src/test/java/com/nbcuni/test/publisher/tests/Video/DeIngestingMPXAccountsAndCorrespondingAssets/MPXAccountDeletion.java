package com.nbcuni.test.publisher.tests.Video.DeIngestingMPXAccountsAndCorrespondingAssets;

import java.util.Arrays;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MPXAccountDeletion extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC1884
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/18169765713
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"onrequest"})
    public void MPXAccountDeletion_TC1884() throws Exception{
    	
    	if (config.getConfigValueString("DrushIngestion").equals("false")) {
    		
    		//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
            //Step 2 and 3
        	Settings settings = new Settings(webDriver);
        	settings.ConfigureMPXIfNeeded();
        	settings.ConfigureMPXIngestionType();
            MPXMedia mpxMedia = new MPXMedia(webDriver);
            Cron cron = new Cron(webDriver);
            
            //Step 4
            navigation.Configuration("Media: thePlatform mpx");
            
            //Step 5 through 8
        	ErrorChecking errorChecking = new ErrorChecking(webDriver);
        	while (settings.GetAllDeleteAccountLnks().size() > 0) {
        		
        		settings.GetAllDeleteAccountLnks().get(0).click();
        		contentParent.VerifyPageContentPresent(Arrays.asList("Are you sure you want to delete the mpx account"));
        		settings.ClickConfirmBtn();
        		contentParent.WaitForProgressBarNotPresent();
        		errorChecking.VerifyNoMessageErrorsPresent();
        		
        	}
        	
        	//Step 9
        	navigation.Content("Files", "mpxMedia");
        	SearchFor searchFor = new SearchFor(webDriver);
        	searchFor.EnterTitle("Automation");
        	searchFor.ClickApplyBtn();
        	searchFor.VerifyNoSearchResultsPresent();
        	
        	//Step 10
        	navigation.Configuration("Media: thePlatform mpx");
        	settings.ClickAddMPXAccountLnk();
        	settings.EnterUsername(config.getConfigValueString("MPXUsername"));
        	settings.EnterPassword(config.getConfigValueString("MPXPassword"));
        	settings.ClickContinueAndEditBtn();
        	contentParent.VerifyMessageStatus("Created mpx account");
        	settings.SelectImportAccount("DB TV");
        	settings.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("has been saved.");
        	navigation.ClickPrimaryTabNavLnk("Settings");
        	if (config.getConfigValueString("DrushIngestion").equals("true")) {
        		settings.UnCheckSyncMPXMediaOnCronBtn();
        	}
        	else {
        		settings.CheckSyncMPXMediaOnCronBtn();
        	}
        	settings.ClickSaveConfigurationsBtn();
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	
        	//Step 11
        	navigation.ClickPrimaryTabNavLnk("Accounts");
        	settings.ClickPlayersNotImportedLnk();
        	mpxMedia.ClickSyncMPXPlayersNowLnk();
        	settings.ClickNotConfiguredLnk();
            settings.SelectDefaultPlayer("AutomationPlayer1");
            settings.ClickSaveBtn();
            contentParent.VerifyMessageStatus("has been saved");
            cron.RunCron();
            navigation.Content("Files", "mpxMedia");
    	    searchFor.EnterTitle("Automation");
    	    searchFor.ClickApplyBtn();
    	    int I = 0;
    	    while (!searchFor.GetFirstMPXMediaSearchResult().contains("Automation")) {
    	    	I++; 
    	    	searchFor.ClickApplyBtn();
    	    	if (I >= 5) { break; }
    	    }
    	    Assert.assertTrue(searchFor.GetFirstMPXMediaSearchResult().contains("Automation"));
    	    
    	    //Cleanup
    	    settings.DeleteAllOldMPXFileTypes();
    	}
    }
}
