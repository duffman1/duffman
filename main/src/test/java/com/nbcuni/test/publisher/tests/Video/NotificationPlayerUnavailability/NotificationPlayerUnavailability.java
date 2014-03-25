package com.nbcuni.test.publisher.tests.Video.NotificationPlayerUnavailability;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.MPX.EditMPXVideo;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXPlayers;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddPlayer;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAssets;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSearch;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

public class NotificationPlayerUnavailability extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to the platform (mpx.theplatform.com),User is logged in successfully
     * Step 2 - Click on Players --> all players and select one of the custom players created for testing.,List of players is visible and custom player is accessible
     * Step 2a - Assign a piece of media to the player.
     * Step 3 - In player settings click on "disable this player." and then click save ,Player is disabled and settings are saved successfully. 
	 * Step 4 - Login to publisher as admin (user 1) ,User is logged in successfully 
     * Step 5 - Navigate to Home --> Run Cron,Cron is run successfully 
     * Step 6 - Navigate to content --> Files --> MPX Players,MPX Player content overlay is displayed
     * Step 7 - Locate the player you modified in the platform in step 3, click on player link to view player details Click on Publishing options ensure 'published' check box is checked Save,Publisher is Disabled in MPX and set to published in P7  
     * Step 8 - Navigate to content --> Files --> MPX Player,Message "An MPXplayer that's in use (NAME_OF_PLAYER_IN_STEP_3) has been disabled in MPX."
     * Step 9 - Navigate to Home --> Files --> mpx Media,Message "An MPXplayer that's in use (NAME_OF_PLAYER_IN_STEP_3) has been disabled in MPX." is displayed
     * Step 10 - Navigate to Configuration --> Media -- Media: MPX thePlatform,Message "An MPXplayer that's in use (NAME_OF_PLAYER_IN_STEP_3) has been disabled in MPX." is displayed
     * Step 11 - Log in to the platform (mpx.theplatform.com),User is logged in successfully
     * Step 12 - Click on Players --> all players and select the same custom player from Step 3  Uncheck "Disable this player" Save,Player is no longer disabled and change is saved 
     * Step 13 - Login to publisher as admin (user 1),User is logged in Successfully  
     * Step 14 - Navigate to Home --> Run Cron,Cron is run successfully
     * Step 15 - Navigate to Content --> Files --> MPX Players ,No Message for the player should be displayed
     * Step 16 - Navigate to Content --> Files --> MPX Media ,No Message for the player should be displayed
     * Step 17 - Navigate to Configuration --> Media -- Media: MPX thePlatform,No Message for the player should be displayed
     * Step 18 - Navigate to Home --> Files --> MPX Players Select the Player modified in Step 3 Click "Edit" and Select "Publishing Options." Uncheck "Published" Save,Change is saved Successfully
     * Step 19 - Navigate to Configuration --> Media -- Media: MPX thePlatform,An MPXplayer that's in use (PLAYER_FROM_STEP_3) has been unpublished.
     * Step 20 - Navigate to content --> Files --> MPX Players,An MPXplayer that's in use (PLAYER_FROM_STEP_3) has been unpublished.
     * Step 21 - Navigate to Home --> Files --> mpxMedia page,An MPXplayer that's in use (PLAYER_FROM_STEP_3) has been unpublished
     * Step 22 - Log in to the platform (mpx.theplatform.com) ,User is logged in successfully  
     * Step 23 - Click on Players --> all players and select the same custom player from Step 3  Check "Disable this player" Save,Change is saved Successfully
     * Step 24 - Login to publisher as admin (user 1),User is logged in successfully
     * Step 25 - Navigate to Home --> Run Cron,Cron is run
     * Step 26 - Navigate to Configuration --> Media -- Media: MPX thePlatform,Message "An MPXplayer that's in use (NAME_OF_PLAYER_IN_STEP_3) has been disabled and unpublished." 
     * Step 27 - Navigate to content --> Files --> MPX Players,MPX Player content overlay is displayed Message "An MPXplayer that's in use (NAME_OF_PLAYER_IN_STEP_3) has been disabled and unpublished."
     * Step 28 - Navigate to Home --> Files --> mpxMedia page,Message "An MPXplayer that's in use (NAME_OF_PLAYER_IN_STEP_3) has been disabled and unpublished."
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx"})
    public void NotificationPlayerUnavailability_Test() throws Exception{

    	//NOTE - Test steps re-ordered and truncated for automation optimization
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //MPX Configuration required
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToActiveFrame();
        Settings settings = new Settings(webDriver, applib);
        if (settings.IsMPXConfigured() == true) {

        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();

        	if (configuredAccounts.get(0).equals("DB TV")) {
        		
        		//Step 1
        		MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
            	mpxLogin.OpenMPXThePlatform();
            	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
            	
            	//Step 2 - note that this test creates a new player
            	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(applib);
                mpxSelectAccount.SelectAccount("DB TV");
            	MPXAddPlayer mpxAddPlayer = new MPXAddPlayer(applib);
                mpxAddPlayer.ClickPlayersLnk();
                mpxAddPlayer.ClickAllPlayersLnk();
                mpxAddPlayer.ClickAddBtn();
                String playerTitle = "AutomationPlayer" + random.GetCharacterString(10);
                mpxAddPlayer.EnterPlayerTitle(playerTitle);
                mpxAddPlayer.ClickSaveBtn();
                applib.openApplication();
                taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
                overlay.SwitchToActiveFrame();
                MPXPlayers MPXPlayers = new MPXPlayers(webDriver);
                MPXPlayers.ClickSyncMPXPlayersLnk();
                MPXPlayers.ClickSyncMPXPlayersNowLnk();
                contentParent.VerifyMessageStatus("players returned for account");
        	    SearchFor searchFor = new SearchFor(webDriver, applib);
        	    searchFor.EnterTitle(playerTitle);
                searchFor.ClickApplyBtn();
                overlay.switchToDefaultContent();
                if (!searchFor.GetFirstMPXPlayerSearchResult().equals(playerTitle)) {
        	    	Thread.sleep(10000); //pause and re-run cron as sometimes media assets aren't in the first ingested queue
        	    	taxonomy.NavigateSite("Home>>Run cron");
            	    contentParent.VerifyMessageStatus("Cron ran successfully.");
            	    taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
            	    searchFor.EnterTitle(playerTitle);
                    searchFor.ClickApplyBtn();
                    if (!searchFor.GetFirstMPXPlayerSearchResult().equals(playerTitle)) {
            	    	Assert.fail("MPX Player has not been successfully ingested into pub 7."); 
            	    }
        	    }
                
        	    //Step 2a
        	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	    searchFor.EnterTitle("Automation");
        	    searchFor.SelectMPXStatus("Published");
        	    searchFor.SelectMPXMediaSource("DB TV");
        	    searchFor.ClickApplyBtn();
        	    searchFor.ClickSearchTitleLnk(searchFor.GetFirstMPXMediaSearchResult());
        	    WorkBench workBench = new WorkBench(webDriver, applib);
        	    workBench.ClickWorkBenchTab("Edit");
        	    overlay.SwitchToActiveFrame();
        	    EditMPXVideo editMPXVideo = new EditMPXVideo(webDriver);
        	    editMPXVideo.SelectPubMPXVideoPlayer(playerTitle);
        	    contentParent.ClickSaveBtn();
        	    
                //Step 3
        	    mpxLogin.OpenMPXThePlatform();
        	    MPXAssets mpxAssets = new MPXAssets(applib);
        	    mpxAssets.WaitForAllAssetsToLoad();
            	mpxSelectAccount.SelectAccount("DB TV");
                mpxAddPlayer.ClickPlayersLnk();
                mpxAddPlayer.ClickAllPlayersLnk();
            	MPXSearch mpxSearch = new MPXSearch(applib);
            	mpxSearch.EnterSearchPlayersTxt(playerTitle);
            	mpxSearch.ClickSearchByPlayersTitleLnk();
            	mpxAddPlayer.GiveFocusToPlayerItem();
            	mpxAddPlayer.ClickDisablePlayerCbx();
            	mpxAddPlayer.ClickSaveBtn();
            	
            	//Step 4
            	applib.openApplication();
            	
            	//Step 5
            	taxonomy.NavigateSite("Home>>Run cron");
            	overlay.SwitchToActiveFrame();
            	contentParent.VerifyMessageStatus("Cron ran successfully.");
            	overlay.ClickCloseOverlayLnk();
            	
        	    //Step 6
        	    taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
        	    overlay.SwitchToActiveFrame();
        	    
        	    //Step 7 - note that multiple cron runs are sometimes necessary for disabled player to be present
        	    ErrorChecking errorChecking = new ErrorChecking(webDriver, applib);
        	    try {
        	    		errorChecking.VerifyMPXPlayerDisabled(playerTitle);
        	    }
        	    catch (AssertionError | NoSuchElementException ex) {
        	    	
        	    	Thread.sleep(10000); //pause and re-run cron as this step can be flaky and sometimes require a second cron run.
        	    	overlay.switchToDefaultContent();
        	    	taxonomy.NavigateSite("Home>>Run cron");
        	    	overlay.SwitchToActiveFrame();
        	    	contentParent.VerifyMessageStatus("Cron ran successfully.");
        	    	overlay.ClickCloseOverlayLnk();
        	    	taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
        	    	overlay.SwitchToActiveFrame();
        	    	errorChecking.VerifyMPXPlayerDisabled(playerTitle);
        	   
        	    }
        	    
                //Step 8
                searchFor.EnterTitle(playerTitle);
                searchFor.ClickApplyBtn();
                overlay.switchToDefaultContent();
                searchFor.ClickSearchTitleLnk(playerTitle);
                workBench.ClickWorkBenchTab("Edit");
                overlay.SwitchToActiveFrame();
                PublishingOptions publishingOptions = new PublishingOptions(webDriver);
                publishingOptions.ClickPublishingOptionsLnk();
                publishingOptions.UncheckPublishedCbx();
                contentParent.ClickSaveBtn();
                overlay.switchToDefaultContent();
                contentParent.VerifyMessageStatus("MPX Player " + playerTitle + " has been updated.");
                
                //Step 9
                taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
                overlay.SwitchToActiveFrame();
                errorChecking.VerifyMPXPlayerDisabledAndUnpublished(playerTitle);
                
                //Step 10
                MPXMedia mpxMedia = new MPXMedia(webDriver);
                mpxMedia.ClickMPXMediaLnk();
                overlay.SwitchToActiveFrame();
                errorChecking.VerifyMPXPlayerDisabledAndUnpublished(playerTitle);
                overlay.ClickCloseOverlayLnk();
                
                //Step 11
                taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
                overlay.SwitchToActiveFrame();
                errorChecking.VerifyMPXPlayerDisabledAndUnpublished(playerTitle);
                
                //Step 12
                mpxMedia.ClickMPXPlayerUnpublishedHereLnk(playerTitle);
                overlay.SwitchToActiveFrame();
                publishingOptions.ClickPublishingOptionsLnk();
                publishingOptions.CheckPublishedCbx();
                contentParent.ClickSaveBtn();
                
                //Step 13
                taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
                overlay.SwitchToActiveFrame();
                errorChecking.VerifyMPXPlayerDisabled(playerTitle);
                
                //Step 14
                String parentWindow = webDriver.getWindowHandle();
                mpxMedia.ClickMPXPlayerLogIntoMPXPlatformLnk(playerTitle);
                Set<String> allWindows = webDriver.getWindowHandles();  
                for (String window : allWindows){
                   if (!window.equals(parentWindow)){
                     webDriver.switchTo().window(window);
                     break;
                   }
                }
                
                //Step 15
                mpxAssets.WaitForAllAssetsToLoad();
                mpxSelectAccount.SelectAccount("DB TV");
                mpxAddPlayer.ClickPlayersLnk();
                mpxAddPlayer.ClickAllPlayersLnk();
            	mpxSearch.EnterSearchPlayersTxt(playerTitle);
            	mpxSearch.ClickSearchByPlayersTitleLnk();
            	mpxAddPlayer.GiveFocusToPlayerItem();
            	mpxAddPlayer.ClickDisablePlayerCbx();
            	mpxAddPlayer.ClickSaveBtn();
            	
            	//Step 16
            	webDriver.close();
            	webDriver.switchTo().window(parentWindow);
            	overlay.SwitchToActiveFrame();
            	overlay.ClickCloseOverlayLnk();
            	taxonomy.NavigateSite("Home>>Run cron");
        	    overlay.SwitchToActiveFrame();
        	    contentParent.VerifyMessageStatus("Cron ran successfully.");
        	    overlay.ClickCloseOverlayLnk();
        	    
        	    //Step 17
        	    taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
                overlay.SwitchToActiveFrame();
                try {
        	    	contentParent.VerifyPageContentNotPresent(Arrays.asList("An MPXplayer that's in use (" + playerTitle + ") has been disabled in MPX."));
        	    }
        	    catch (AssertionError e) {
        	    	Thread.sleep(10000); //pause and re-run cron as this step can be flaky and sometimes require a second cron run.
        	    	overlay.switchToDefaultContent();
        	    	taxonomy.NavigateSite("Home>>Run cron");
        	    	overlay.SwitchToActiveFrame();
        	    	contentParent.VerifyMessageStatus("Cron ran successfully.");
        	    	overlay.ClickCloseOverlayLnk();
        	    	taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
        	    	overlay.SwitchToActiveFrame();
        	    	contentParent.VerifyPageContentNotPresent(Arrays.asList("An MPXplayer that's in use (" + playerTitle + ") has been disabled in MPX."));
        	    }
        	}
        	else {
        		Assert.fail("DB TV account must be configured.");
        	}
        }
        else {
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        	
        }

    }
}
