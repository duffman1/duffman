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
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx"})
    public void DeleteMultipleAccounts_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToActiveFrame();
        
        //Step 3
        Settings settings = new Settings(webDriver, applib);
        if (settings.IsMPXConfigured() == true) { 
        	
        	//Step 4
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
        	settings.ExpandMPXLogin();
        	
        	//Step 5 through 8
        	ContentParent contentParent = new ContentParent(webDriver, applib);
        	ErrorChecking errorChecking = new ErrorChecking(webDriver, applib);
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
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	overlay.SwitchToActiveFrame();
        	SearchFor searchFor = new SearchFor(webDriver, applib);
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
        	mpxMedia.ExpandMPXMedia();
            mpxMedia.SelectMPXPlayerForAccount1("Auditude Demo player");
            mpxMedia.ClickSyncMPXMediaNowLnk();
            contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
        	Cron cron = new Cron(webDriver, applib);
        	cron.RunCron(false);
    	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	    searchFor.EnterTitle("Automation");
    	    searchFor.ClickApplyBtn();
    	    overlay.switchToDefaultContent();
    	    int I = 0;
    	    while (!searchFor.GetFirstMPXMediaSearchResult().contains("Automation")) {
    	    	I++; Thread.sleep(5000); //pause necessary as media ingestion can take a while from mpx
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
        else { 
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        	
        }
        
    }
}
