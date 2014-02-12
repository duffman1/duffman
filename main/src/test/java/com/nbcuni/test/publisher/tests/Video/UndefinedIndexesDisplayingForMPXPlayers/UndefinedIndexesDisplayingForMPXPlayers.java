package com.nbcuni.test.publisher.tests.Video.UndefinedIndexesDisplayingForMPXPlayers;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.MPX.EditMPXVideo;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXDataClient;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXPlayers;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddPlayer;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAssets;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.ExecutePHPCode;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

import junit.framework.Assert;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class UndefinedIndexesDisplayingForMPXPlayers extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to MPX with Valid Credentials ,Login Successful 
     * Step 2 - Add a Player in MPX Steps:  In the Library->Click Players In AllPlayers list view-> Click Add Players In the Players > All Players > Basic Settings, type the name of your player in the Title text box. ,Players get added sucessfully 
     * Step 3 - Log in to P7 -> Run Cron ,Cron Run successfully 
     * Step 4 - Go to Content -> Files-> mpxMedia-> Select any video ,Video title page appears 
     * Step 5 - Click on "Edit " tab ,Edit page of video appears 
     * Step 6 - At the bottom of the page under "PubMPX Video Player" field -> Set the Player created in step no.2 above-> Save ,Newly created Player is associated with the video instead of default player. 
     * Step 7 - Go to Home-> Development->Execute PHP code-> copy the below code which list the player ids which are associated with the video instead of default players             $player_query = db_select('mpx_player', 'p'); $player_query->fields('p', array('player_id')); $players = $player_query->execute()->fetchCol();  $query = db_select("mpx_video", 'v'); $query->fields('v', array('player_id')); $query->condition('player_id', 0, '>'); $query->condition('v.player_id', $players, 'IN'); $query->distinct(); $query->isNotNull('v.player_id'); $result = $query->execute(); foreach ($result as $record) { echo "player id: ".$record->player_id.PHP_EOL; } Click Execute    Note the player id.,List of Player ids appears. 
     * Step 8 - Go to Content -> Files-> mpxPlayer-> Click on the Player just created in step2 above ,Title page of player appears 
     * Step 9 - Click on "Edit " tab -> On vertical tab option -> under Publishing option-> uncheck the Publish checkbox and unpublish the players-> Save ,Player is unpublished 
     * Step 10 - Navigate to content-> Files-> MPX media->Below message appears -> Click on the "here" link -> Verify that no broken link appears.                                                                "An MPXplayer that's in use (Player3) has been unpublished. To change its status in Publisher, click here                            " ,Edit page of the player appears and no broken link is seen as expected 
     * Step 11 - Go to Home-> Development->Execute PHP code-> Copy the code to delete the player                                                           $res = db_delete('mpx_player')     ->condition('player_id', <ID GOES HERE>)     ->execute(); echo ($res ? 'Player deleted.' : "Player NOT deleted.  (Maybe it was deleted previously?)"); echo PHP_EOL;  Note: Set the Player id noted in step no 7 in this code "<ID GOES HERE> and Execute ,"Player gets deleted" message appears 
     * Step 12 - To confirm the last 4 players deleted                                           Go to -> Home-> Development-> Execute PHP code                     $player_query = db_select('mpx_player', 'p'); $player_query->fields('p', array('player_id')); $players = $player_query->execute()->fetchCol();  $query = db_select("mpx_video", 'v'); $query->fields('v', array('player_id')); $query->condition('v.player_id', $players, 'NOT IN'); $query->distinct(); $query->isNotNull('v.player_id'); $result = $query->execute(); foreach ($result as $record) { echo "deleted player id: ".$record->player_id.PHP_EOL; } ,List of Last 4 players deleted appears 
     * Step 13 - Go to Content -> File-> MPX player tab and  mpx media tab.  Verify that no player errors are seen  ,No error messages are seen as expected 
     * Step 14 - Go to-> Configure -> Media-> Media: thePlatform MPX settings.  Verify that no error messages are seen ,No error messages are seen as expected 
     * Step 15 - Logout from P7  ,Logout successful (not needed for automation)
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "mpx"})
    public void UndefinedIndexesDisplayingForMPXPlayers_Test() throws Exception{
    	
    	//Defect was removed from iteration 39 for work at a later date. commenting out test until resolved at the request of the business
    	/*
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	PageFactory.initElements(webDriver, userLogin);
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
    	MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
    	mpxLogin.OpenMPXThePlatform();
    	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
    	MPXAssets mpxAssets = new MPXAssets(webDriver, applib);
    	mpxAssets.WaitForAllAssetsToLoad();
    	
        //Step 2
        MPXAddPlayer mpxAddPlayer = new MPXAddPlayer(webDriver, applib);
        mpxAddPlayer.ClickPlayersLnk();
        mpxAddPlayer.ClickAllPlayersLnk();
        mpxAssets.WaitForAllAssetsToLoad();
        mpxAddPlayer.ClickAddBtn();
        String playerTitle = "AutomationPlayer" + random.GetCharacterString(10);
        mpxAddPlayer.EnterPlayerTitle(playerTitle);
        mpxAddPlayer.ClickSaveBtn();
        
        //Step 3
        applib.openApplication();
    	
    	//Step 3 (continued)
    	taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        Settings settings = new Settings(webDriver);
        if (settings.IsMPXConfigured() == true) {
            
        	//Step 3 (continued)
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
    	    overlay.switchToDefaultContent();
    	    
        	//Step 3a
    	    taxonomy.NavigateSite("Modules");
        	overlay.SwitchToFrame("Modules");
        	Modules modules = new Modules(webDriver);
        	modules.EnterFilterName("Devel");
            modules.DisableModule("Devel");
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
    	    
        	//Step 3 (continued)
        	taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
            overlay.SwitchToFrame("Content");
            MPXPlayers MPXPlayers = new MPXPlayers(webDriver);
            MPXPlayers.ClickSyncMPXPlayersLnk();
            MPXPlayers.ClickSyncMPXPlayersNowLnk();
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.VerifyMessageStatus("players returned for account");
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Home>>Run cron");
        	
        	//Step 4
        	overlay.switchToDefaultContent();
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	overlay.SwitchToActiveFrame();
        	SearchFor searchFor = new SearchFor(webDriver);
        	String firstSearchResult = searchFor.GetFirstSearchResult();
        	searchFor.ClickSearchTitleLnk(firstSearchResult);
        	
        	//Step 5
        	Workflow workflow = new Workflow(webDriver);
        	workflow.ClickWorkflowTab("Edit");
        	overlay.SwitchToActiveFrame();
        	
        	//Step 6
        	EditMPXVideo editMPXVideo = new EditMPXVideo(webDriver);
        	editMPXVideo.SelectPubMPXVideoPlayer(playerTitle);
        	contentParent.ClickSaveBtn();
        	
        	//Step 7
        	overlay.switchToDefaultContent();
        	modules.VerifyModuleEnabled("Devel");
        	taxonomy.NavigateSite("Home>>Development>>Execute PHP Code");
        	overlay.SwitchToActiveFrame();
        	ExecutePHPCode executePHPCode = new ExecutePHPCode(webDriver);
        	executePHPCode.EnterPHPCode("$player_query = db_select('mpx_player', 'p'); $player_query->fields('p', array('player_id'));" +  
        			"$players = $player_query->execute()->fetchCol();  $query = db_select(\"mpx_video\", 'v'); " + 
        				"$query->fields('v', array('player_id')); $query->condition('player_id', 0, '>'); " + 
        					"$query->condition('v.player_id', $players, 'IN'); $query->distinct(); $query->isNotNull('v.player_id');" +
        						"$result = $query->execute(); foreach ($result as $record) { echo \"player id: \".$record->player_id.PHP_EOL; }");
        	executePHPCode.ClickExecuteBtn();
        	String playerID = executePHPCode.GetPlayerId();
        	
        	//Step 8
        	overlay.switchToDefaultContent();
        	taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
        	overlay.SwitchToActiveFrame();
        	searchFor.ClickSearchTitleLnk(playerTitle);
        	overlay.switchToDefaultContent();
        	
        	//Step 9
        	workflow.ClickWorkflowTab("Edit");
        	overlay.SwitchToActiveFrame();
        	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        	publishingOptions.ClickPublishingOptionsLnk();
        	publishingOptions.UncheckPublishedCbx();
        	contentParent.ClickSaveBtn();
        	overlay.switchToDefaultContent();
        	contentParent.VerifyMessageStatus("MPX Player");
        	contentParent.VerifyMessageStatus("has been updated.");
        	
        	//Step 10
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	overlay.SwitchToActiveFrame();
        	contentParent.VerifyMessageError("An MPXplayer that's in use (" + playerTitle + ") has been unpublished.");
        	MPXMedia mpxMedia = new MPXMedia(webDriver);
        	mpxMedia.ClickMPXPlayerUnpublishedHereLnk(playerTitle);
        	overlay.switchToDefaultContent();
        	overlay.SwitchToFrame("Edit mpx_player");
        	contentParent.VerifyNoMessageErrorsPresent();
        	
        	//Step 11
        	overlay.switchToDefaultContent();
        	taxonomy.NavigateSite("Home>>Development>>Execute PHP Code");
        	overlay.SwitchToActiveFrame();
        	executePHPCode.EnterPHPCode("$res = db_delete('mpx_player')->condition('player_id', " + playerID + ")->execute();echo ($res ? 'Player deleted.' : \"Player NOT deleted.  (Maybe it was deleted previously?)\");echo PHP_EOL;");
        	executePHPCode.ClickExecuteBtn();
        	contentParent.VerifyMessageStatus("Player deleted.");
        	
        	//Step 12
        	executePHPCode.EnterPHPCode("$player_query = db_select('mpx_player', 'p'); $player_query->fields('p', array('player_id')); $players = $player_query->execute()->fetchCol();  $query = db_select(\"mpx_video\", 'v'); $query->fields('v', array('player_id')); $query->condition('v.player_id', $players, 'NOT IN'); $query->distinct(); $query->isNotNull('v.player_id'); $result = $query->execute(); foreach ($result as $record) { echo \"deleted player id: \".$record->player_id.PHP_EOL; }");
            executePHPCode.ClickExecuteBtn();
            overlay.SwitchToActiveFrame();
            contentParent.VerifyMessageStatus("deleted player id: " + playerID);
            
            //Step 13
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            overlay.SwitchToActiveFrame();
            contentParent.VerifyNoMessageErrorsPresent();
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
            overlay.SwitchToActiveFrame();
            contentParent.VerifyNoMessageErrorsPresent();
            
            //Step 14
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
            overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
            contentParent.VerifyNoMessageErrorsPresent();
        }
        else {
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        }
        */
    }
}
