package com.nbcuni.test.publisher.tests.Video.PlayerImport;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.MPX.EditMPXVideo;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXPlayers;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddPlayer;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

public class ImportPlayer extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx", "certify"})
    public void NotificationPlayerUnavailability_Test() throws Exception {

    	//NOTE - Test steps re-ordered and truncated for automation optimization
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //MPX Configuration required
    	Settings settings = new Settings(webDriver);
    	settings.ConfigureMPXIfNeeded();
    	
    	navigation.Configuration("Media: thePlatform mpx");
        
        //Step 1
        		MPXLogin mpxLogin = new MPXLogin(webDriver);
            	mpxLogin.OpenMPXThePlatform();
            	mpxLogin.Login(config.getConfigValueString("MPXUsername"), config.getConfigValueString("MPXPassword"));
            	
            	//Step 2 - note that this test creates a new player
            	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(webDriver);
                mpxSelectAccount.SelectAccount("DB TV");
            	MPXAddPlayer mpxAddPlayer = new MPXAddPlayer(webDriver);
                mpxAddPlayer.ClickPlayersLnk();
                mpxAddPlayer.ClickAllPlayersLnk();
                mpxAddPlayer.ClickAddBtn();
                String playerTitle = "AutomationPlayer" + random.GetCharacterString(10);
                mpxAddPlayer.EnterPlayerTitle(playerTitle);
                mpxAddPlayer.ClickSaveBtn();
                applib.openApplication();
                navigation.Content("Files", "mpxPlayers");
                MPXPlayers MPXPlayers = new MPXPlayers(webDriver);
                MPXPlayers.ClickSyncMPXPlayersLnk();
                MPXPlayers.ClickSyncMPXPlayersNowLnk();
                contentParent.VerifyMessageStatus("Processed players manually for all accounts");
                contentParent.VerifyMessageStatus(playerTitle);
        	    
        	    //Step 2a
                navigation.Content("Files", "mpxMedia");
                SearchFor searchFor = new SearchFor(webDriver);
        	    searchFor.EnterTitle("Automation");
        	    searchFor.SelectStatus("Published");
        	    searchFor.SelectMPXMediaSource("DB TV");
        	    searchFor.ClickApplyBtn();
        	    searchFor.ClickSearchTitleLnk(searchFor.GetFirstMPXMediaSearchResult());
        	    WorkBench workBench = new WorkBench(webDriver);
        	    workBench.ClickWorkBenchTab("Edit");
        	    EditMPXVideo editMPXVideo = new EditMPXVideo(webDriver);
        	    editMPXVideo.SelectPubMPXVideoPlayer(playerTitle);
        	    contentParent.ClickSaveBtn();
        	    
        }
}
