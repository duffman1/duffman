package com.nbcuni.test.publisher.tests.Video.DeIngestingMPXAccountsAndCorrespondingAssets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MPXAccountDeletion extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC1884
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/18169765713
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx"})
    public void MPXAccountDeletion_TC1884() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToActiveFrame();
        
        //Step 3
        Settings settings = new Settings(webDriver, applib);
        MPXMedia mpxMedia = new MPXMedia(webDriver);
        Cron cron = new Cron(webDriver, applib);
        if (settings.IsMPXConfigured() == false) { 
        	
        	settings.EnterUsername(applib.getMPXUsername());
        	settings.EnterPassword(applib.getMPXPassword());
        	settings.ClickConnectToMPXBtn();
        	settings.SelectImportAccount1("DB TV");
        	settings.ClickSetImportAccountBtn();
        	settings.ClickSaveConfigurationsBtn();
        	overlay.ClickCloseOverlayLnk();
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	overlay.SwitchToActiveFrame();
        	mpxMedia.ExpandMPXMedia();
            mpxMedia.SelectMPXPlayerForAccount1("Auditude Demo player");
            mpxMedia.ClickSyncMPXMediaNowLnk();
            overlay.ClickCloseOverlayLnk();
            cron.RunCron(true);
        	taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
            overlay.SwitchToActiveFrame();
        }
        
        //Step 4
    	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
    	settings.ExpandAccountList();
    	
    	//Step 5 through 8
    	ContentParent contentParent = new ContentParent(webDriver, applib);
    	ErrorChecking errorChecking = new ErrorChecking(webDriver, applib);
    	List<WebElement> AllDeleteAccountButtons = settings.GetAllDeleteAccountButtons();
    	Assert.assertTrue(configuredAccounts.size() == AllDeleteAccountButtons.size());
    	while (settings.GetAllDeleteAccountButtons().size() > 0) {
    		
    		settings.ExpandAccountList();
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
    		
    	}
    	
    	//Step 9
    	overlay.ClickCloseOverlayLnk();
    	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	overlay.SwitchToActiveFrame();
    	SearchFor searchFor = new SearchFor(webDriver, applib);
    	searchFor.EnterTitle("Automation");
    	searchFor.ClickApplyBtn();
    	overlay.switchToDefaultContent();
    	Assert.assertTrue(searchFor.GetSearchResultSize() == 0);
    	
    	//Step 10
    	taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        settings.EnterUsername(applib.getMPXUsername());
    	settings.EnterPassword(applib.getMPXPassword());
    	settings.ClickConnectToMPXBtn();
    	contentParent.VerifyMessageStatus("Login successful");
    	settings.SelectImportAccount1("DB TV");
    	settings.ClickSetImportAccountBtn();
    	contentParent.VerifyMessageStatus("Setting import account \"DB%20TV\" for account");
    	settings.ClickSaveConfigurationsBtn();
    	contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	
    	//Step 11
    	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	mpxMedia.ExpandMPXMedia();
        mpxMedia.SelectMPXPlayerForAccount1("Auditude Demo player");
        mpxMedia.ClickSyncMPXMediaNowLnk();
        contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
    	cron.RunCron(false);
	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
	    searchFor.EnterTitle("Automation");
	    searchFor.ClickApplyBtn();
	    overlay.switchToDefaultContent();
	    int I = 0;
	    while (!searchFor.GetFirstMPXMediaSearchResult().contains("Automation")) {
	    	I++; 
	    	searchFor.ClickApplyBtn();
	    	if (I >= 5) { break; }
	    }
	    Assert.assertTrue(searchFor.GetFirstMPXMediaSearchResult().contains("Automation"));
	    
	    //Cleanup
	    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
        	List<String> eachURL = new ArrayList<String>();
        	String allURLs = null;
        	for (WebElement el : webDriver.findElements(By.xpath("//a[contains(text(), 'MPX Video for Account')][contains(text(), 'DB TV')]"))) {
        		allURLs = allURLs + el.getAttribute("href");
        		eachURL.add(el.getAttribute("href"));
        	}
        	allURLs = allURLs.replaceAll(applib.getApplicationURL() + "/admin/structure/file-types/manage/", "");
        	String[] index = allURLs.split("mpx_video_");
        	ArrayList<Integer> allIndexInts = new ArrayList<Integer>();
        	allIndexInts.removeAll(Collections.singleton("empty"));
        	for (String s : index) {
        		try {
        			allIndexInts.add(Integer.parseInt(s));
        		}
        		catch (NumberFormatException e) {}
        	}
        	Integer maxScore = Collections.max(allIndexInts);
		    for (String url : eachURL) {
			
        		if (!url.contains("mpx_video_" + maxScore.toString())) {
        			webDriver.navigate().to(url);
        			webDriver.findElement(By.id("edit-delete")).click();
        			webDriver.findElement(By.id("edit-submit")).click();
        		}
        	}
        }
        catch (Exception e) {}
        webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
	    
        
    }
}