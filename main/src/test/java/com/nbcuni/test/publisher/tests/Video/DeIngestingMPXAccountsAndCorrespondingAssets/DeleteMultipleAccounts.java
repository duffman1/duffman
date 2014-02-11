package com.nbcuni.test.publisher.tests.Video.DeIngestingMPXAccountsAndCorrespondingAssets;

import java.util.Arrays;
import java.util.List;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.errorchecking.ErrorChecking;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteMultipleAccounts extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into Pub 7<br>
     * Step 3 - Open pub 7 and navigate to Confuguration>>Media>>Media: thePlatform mpx settings<br>
     * Step 4 - Verify that mpx is configured. Fail test if not.<br>
     * Step 5 - Click "MPX Login" link to expand mpx login options<br>
     * Step 6 - For each "Delete Account xxx", get the element click the delete button<br>
     * Step 7 - For each "You are about to..." confirmation, click the "Delete" button<br>
     * Step 8 - For each account to be deleted, Wait for step 4 of 4 to complete for "Deleting MPX Account XXX" dialog<br>
     * Step 9 - For each account to be deleted, Wait for page refresh to recycle<br>
     * Step 10 - Close overlay, navigate "Content>>Files>>mpxMedia, verify no MPX assets present in list<br>
     * Step 11 - Re-import all previous accounts
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "mpx"})
    public void DeleteMultipleAccounts_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	PageFactory.initElements(webDriver, userLogin);
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2
        applib.openApplication();
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        
        //Step 3
        Settings settings = new Settings(webDriver, applib);
        if (settings.IsMPXConfigured() == true) { 
        	
        	//Step 4
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
        	settings.ExpandMPXLogin();
        	
        	//Step 5 through 8
        	ContentParent contentParent = new ContentParent(webDriver, applib);
        	PageFactory.initElements(webDriver, contentParent);
        	ErrorChecking errorChecking = new ErrorChecking(webDriver, applib);
        	PageFactory.initElements(webDriver, errorChecking);
        	List<WebElement> AllDeleteAccountButtons = settings.GetAllDeleteAccountButtons();
        	Assert.assertTrue(configuredAccounts.size() == AllDeleteAccountButtons.size());
        	while (settings.GetAllDeleteAccountButtons().size() > 0) {
        		
        		settings.GetAllDeleteAccountButtons().get(0).click();
        		contentParent.VerifyPageContentPresent(Arrays.asList("You are about to delete account", "and all of its assets. This action cannot be undone."));
        		settings.ClickDeleteBtn();
        		overlay.switchToDefaultContent();
        		overlay.SwitchToFrame("Deleting MPX Account");
        		overlay.switchToDefaultContent();
        		overlay.WaitForFrameNotPresent("Deleting MPX Account");
        		overlay.SwitchToActiveFrame();
        		contentParent.VerifyMessageStatus("Beginning process of deleting account");
        		errorChecking.VerifyNoMessageErrorsPresent();
        		settings.ExpandMPXLogin();
        		
        	}
        	
        	//Step 9
        	overlay.ClickCloseOverlayLnk();
        	overlay.switchToDefaultContent();
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	overlay.SwitchToActiveFrame();
        	SearchFor searchFor = new SearchFor(webDriver, applib);
        	PageFactory.initElements(webDriver, searchFor);
        	searchFor.EnterTitle("Automation");
        	searchFor.ClickApplyBtn();
        	overlay.switchToDefaultContent();
        	Assert.assertTrue(searchFor.GetSearchResultSize() == 0);
        	
        	//Step 10
        	taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
            settings.ClickAddAccountBtn();
        	settings.EnterUsername0(applib.getMPXUsername());
        	settings.EnterPassword0(applib.getMPXPassword());
        	settings.ClickConnectToMPXBtn();
        	contentParent.VerifyMessageStatus("Login successful");
        	settings.SelectImportAccount1("DB TV");
        	settings.ClickSetImportAccountBtn();
        	contentParent.VerifyMessageStatus("Setting import account \"DB%20TV\" for account");
        	settings.ClickSaveConfigurationsBtn();
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	
        	//Step 11
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	MPXMedia mpxMedia = new MPXMedia(webDriver);
        	PageFactory.initElements(webDriver, mpxMedia);
            mpxMedia.ExpandMPXMedia();
            mpxMedia.SelectMPXPlayerForAccount1("Auditude Demo player");
            mpxMedia.ClickSyncMPXMediaNowLnk();
            contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
        	taxonomy.NavigateSite("Home>>Run cron");
        	contentParent.VerifyMessageStatus("Cron ran successfully.");
    	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	    searchFor.EnterTitle("Automation");
    	    searchFor.ClickApplyBtn();
    	    overlay.switchToDefaultContent();
    	    int I = 0;
    	    while (!searchFor.GetFirstMPXMediaSearchResult().contains("Automation")) {
    	    	I++; Thread.sleep(5000); //significant pause necessary as media ingestion can take a while from mpx
    	    	searchFor.ClickApplyBtn();
    	    	if (I >= 5) { break; }
    	    }
    	    Assert.assertTrue(searchFor.GetFirstMPXMediaSearchResult().contains("Automation"));
    	    
        }
        else { 
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        	
        }
        
    }
}
