package com.nbcuni.test.publisher.tests.Video.DeIngestingMPXAccountsAndCorrespondingAssets;

import java.util.Arrays;
import java.util.List;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import org.openqa.selenium.WebElement;
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
            taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
            overlay.SwitchToActiveFrame();
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
        	settings.ExpandAccountList();
        	
        	//Step 5 through 8
        	ContentParent contentParent = new ContentParent(webDriver);
        	ErrorChecking errorChecking = new ErrorChecking(webDriver);
        	List<WebElement> AllDeleteAccountButtons = settings.GetAllDeleteAccountButtons();
        	Assert.assertTrue(configuredAccounts.size() == AllDeleteAccountButtons.size());
        	while (settings.GetAllDeleteAccountButtons().size() > 0) {
        		
        		settings.ExpandAccountList();
        		settings.GetAllDeleteAccountButtons().get(0).click();
        		contentParent.VerifyPageContentPresent(Arrays.asList("You are about to delete account", "and all of its assets. This action cannot be undone."));
        		settings.ClickDeleteBtn();
        		overlay.switchToDefaultContent(true);
        		overlay.SwitchToFrame("Deleting MPX Account");
        		overlay.switchToDefaultContent(true);
        		overlay.WaitForFrameNotPresent("Deleting MPX Account");
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Beginning process of deleting account");
        		errorChecking.VerifyNoMessageErrorsPresent();
        		
        	}
        	
        	//Step 9
        	overlay.ClickCloseOverlayLnk();
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	overlay.SwitchToActiveFrame();
        	SearchFor searchFor = new SearchFor(webDriver);
        	searchFor.EnterTitle("Automation");
        	searchFor.ClickApplyBtn();
        	overlay.switchToDefaultContent(true);
        	Assert.assertTrue(searchFor.GetSearchResultSize() == 0);
        	
        	//Step 10
        	taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
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
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	mpxMedia.ExpandMPXMedia();
            mpxMedia.SelectMPXPlayerForAccount1("AutomationPlayer1");
            mpxMedia.ClickSyncMPXMediaNowLnk();
            contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
            cron.RunCron(false);
    	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	    searchFor.EnterTitle("Automation");
    	    searchFor.ClickApplyBtn();
    	    overlay.switchToDefaultContent(true);
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
