package com.nbcuni.test.publisher.tests.Video.DeIngestingMPXAccountsAndCorrespondingAssets;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

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
        	Settings settings = new Settings(webWebWebDriver);
        	settings.ConfigureMPXIfNeeded();
        	settings.ConfigureMPXIngestionType();
            MPXMedia mpxMedia = new MPXMedia(webWebWebDriver);
            Cron cron = new Cron(webWebWebDriver);
            
            //Step 4
            navigation.Configuration("Media: thePlatform mpx settings");
            List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
        	settings.ExpandAccountList();
        	
        	//Step 5 through 8
        	ErrorChecking errorChecking = new ErrorChecking(webWebWebDriver);
        	List<WebElement> AllDeleteAccountButtons = settings.GetAllDeleteAccountButtons();
        	Assert.assertTrue(configuredAccounts.size() == AllDeleteAccountButtons.size());
        	while (settings.GetAllDeleteAccountButtons().size() > 0) {
        		
        		settings.ExpandAccountList();
        		settings.GetAllDeleteAccountButtons().get(0).click();
        		contentParent.VerifyPageContentPresent(Arrays.asList("You are about to delete account", "and all of its assets. This action cannot be undone."));
        		settings.ClickDeleteBtn();
        		contentParent.WaitForProgressBarNotPresent();
        		errorChecking.VerifyNoMessageErrorsPresent();
        		
        	}
        	
        	//Step 9
        	navigation.Content("Files", "mpxMedia");
        	SearchFor searchFor = new SearchFor(webWebWebDriver);
        	searchFor.EnterTitle("Automation");
        	searchFor.ClickApplyBtn();
        	searchFor.VerifyNoSearchResultsPresent();
        	
        	//Step 10
        	navigation.Configuration("Media: thePlatform mpx settings");
        	settings.EnterUsername(config.getConfigValueString("MPXUsername"));
        	settings.EnterPassword(config.getConfigValueString("MPXPassword"));
        	settings.ClickConnectToMPXBtn();
        	contentParent.VerifyMessageStatus("Login successful");
        	settings.SelectImportAccount1("DB TV");
        	settings.ClickSetImportAccountBtn();
        	contentParent.VerifyMessageStatus("Setting import account \"DB%20TV\" for account");
        	if (config.getConfigValueString("DrushIngestion").equals("true")) {
        		settings.UnCheckSyncMPXMediaOnCronBtn();
        	}
        	else {
        		settings.CheckSyncMPXMediaOnCronBtn();
        	}
        	settings.ClickSaveConfigurationsBtn();
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	
        	//Step 11
        	navigation.Content("Files", "mpxMedia");
        	mpxMedia.ExpandMPXMedia();
            mpxMedia.SelectMPXPlayerForAccount1("AutomationPlayer1");
            mpxMedia.ClickSyncMPXMediaNowLnk();
            contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
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
