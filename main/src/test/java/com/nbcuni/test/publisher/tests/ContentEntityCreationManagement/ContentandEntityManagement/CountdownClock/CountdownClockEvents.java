package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.CountdownClock;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.EventCountdownConfig;
import com.nbcuni.test.publisher.pageobjects.Content.Content;
import com.nbcuni.test.publisher.pageobjects.Content.EventCountdown;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;

import org.testng.Reporter;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CountdownClockEvents extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC4887
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/22256545043
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive"})
    public void CountdownClockEvents_TC4887() throws Exception {
         
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("STEP 2");
        	Modules modules = new Modules(webDriver);
        	modules.VerifyModuleEnabled("Event Countdown");
        	
        	Reporter.log("STEP 3");
        	taxonomy.NavigateSite("Configuration>>Media>>Event Countdown");
        	overlay.SwitchToActiveFrame();
        	EventCountdownConfig eventCountdownConfig = new EventCountdownConfig(webDriver);
        	if (eventCountdownConfig.AreSampleNodesConfigured()) {
        		Reporter.log("Sample nodes have already been created.");
        	}
        	else {
        		eventCountdownConfig.ClickCreateBtn();
            	eventCountdownConfig.WaitForSampleNodeCreation();
        	}
        	overlay.ClickCloseOverlayLnk();
        	
        	Reporter.log("STEP 4");
        	
        	Reporter.log("STEP 5 AND 6");
        	EventCountdown eventCountdown = new EventCountdown(webDriver);
        	for (String eventTitle : Arrays.asList("Birthday Event")) {
        		
        		taxonomy.NavigateSite("Content");
        		overlay.SwitchToActiveFrame();
            	
        		SearchFor searchFor = new SearchFor(webDriver);
            	searchFor.EnterTitle(eventTitle);
            	searchFor.ClickApplyBtn();
            	overlay.switchToDefaultContent(true);
                Content content = new Content(webDriver);
                content.ClickEditMenuBtn(eventTitle);
            	
            	Calendar cal1MinuteFuture = Calendar.getInstance();
                cal1MinuteFuture.add(Calendar.MINUTE, 2);
            	Date date1MinuteFuture = cal1MinuteFuture.getTime();
            	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("MM/dd/yyyy");
            	pub7DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            	SimpleDateFormat pub7TimeFormat = new SimpleDateFormat("hh:mm:ssa");
            	pub7TimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            	eventCountdown.EnterTimerDateTime(pub7DateFormat.format(date1MinuteFuture), pub7TimeFormat.format(date1MinuteFuture));
            	eventCountdown.EnterActiveCallToAction("http://www.amazon.com/s/ref=nb_sb_ss_c_0_8/190-0002947-5703141?url=search-alias%3Dtoys-and-games&field-keywords=birthday%20party%20supplies&sprefix=birthday%2Caps%2C168");
            	eventCountdown.ClickActiveImageSelectBtn();
            	SelectFile selectFile = new SelectFile(webDriver);
            	selectFile.SwitchToSelectFileFrm();
            	selectFile.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "HanSolo1.jpg");
            	selectFile.ClickUploadBtn();
            	selectFile.WaitForFileUploaded("HanSolo1.jpg");
            	selectFile.ClickNextBtn();
            	selectFile.ClickPublicLocalFilesRdb();
            	selectFile.ClickNextBtn();
            	selectFile.VerifyFileImagePresent("HanSolo");
            	Thread.sleep(1000);
            	selectFile.ClickSaveBtn();
            	overlay.switchToDefaultContent(true);
            	eventCountdown.EnterActiveBackgroundColor("#FF0000");
            	eventCountdown.EnterExpiryCallToAction("http://www.nbc.com");
            	eventCountdown.ClickExpiryImageSelectBtn();
            	selectFile.SwitchToSelectFileFrm();
            	selectFile.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "nbclogosmall.jpg");
            	selectFile.ClickUploadBtn();
            	selectFile.WaitForFileUploaded("nbclogosmall.jpg");
            	selectFile.ClickNextBtn();
            	selectFile.ClickPublicLocalFilesRdb();
            	selectFile.ClickNextBtn();
            	selectFile.VerifyFileImagePresent("nbclogosmall");
            	Thread.sleep(1000);
            	selectFile.ClickSaveBtn();
            	overlay.switchToDefaultContent(true);
            	eventCountdown.EnterExpiryBackgroundColor("#00FFFF");
            	contentParent.ClickSaveBtn();
        	}
        	
        	Reporter.log("STEP 7");
        	taxonomy.NavigateSite("Structure>>Blocks");
        	Blocks blocks = new Blocks(webDriver);
        	blocks.SelectRegion("Birthday Block", "Content");
        	blocks.SelectRegion("Holiday Party Block", "Content");
        	blocks.ClickSaveBlocksBtn();
        	contentParent.VerifyMessageStatus("The block settings have been updated.");
        	
        	Reporter.log("STEP 8");
        	taxonomy.NavigateSite("Home");
        	
        	Reporter.log("STEP 9");
        	eventCountdown.VerifyCountDownTimerRunning("Birthday Event");
        	eventCountdown.VerifyActiveImagePresent("Birthday Event", "HanSolo", "1");
        	eventCountdown.VerifyActiveBackgroundColor("Birthday Event", "#FF0000");
        	eventCountdown.VerifyActiveActionLnk("Birthday Event", "http://www.amazon.com/s/ref=nb_sb_ss_c_0_8/190-0002947-5703141?url=search-alias%3Dtoys-and-games&field-keywords=birthday%20party%20supplies&sprefix=birthday%2Caps%2C168");
        	
        	Reporter.log("STEP 10");
        	eventCountdown.WaitForCountDownExpiration("Birthday Event");
        	eventCountdown.VerifyCountDownTimerNotRunning("Birthday Event");
        	eventCountdown.VerifyExpiryImagePresent("Birthday Event", "nbclogosmall", "1");
        	eventCountdown.VerifyExpiryBackgroundColor("Birthday Event", "#00FFFF");
        	eventCountdown.VerifyExpiryActionLnk("Birthday Event", "http://www.nbc.com");
        	
        	Reporter.log("STEP 11 AND 12"); //TODO
        	
        	Reporter.log("STEP 13");
        	taxonomy.NavigateSite("Content>>Add content>>Event Countdown");
        	overlay.SwitchToActiveFrame();
        	String eventCountdownTitle = random.GetCharacterString(15);
        	eventCountdown.EnterTitle(eventCountdownTitle);
        	Calendar cal1MinuteFuture = Calendar.getInstance();
            cal1MinuteFuture.add(Calendar.MINUTE, 2);
        	Date date1MinuteFuture = cal1MinuteFuture.getTime();
        	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("MM/dd/yyyy");
        	pub7DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	SimpleDateFormat pub7TimeFormat = new SimpleDateFormat("hh:mm:ssa");
        	pub7TimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	eventCountdown.EnterTimerDateTime(pub7DateFormat.format(date1MinuteFuture), pub7TimeFormat.format(date1MinuteFuture));
        	eventCountdown.EnterActiveCallToAction("http://www.amazon.com/s/ref=nb_sb_ss_c_0_8/190-0002947-5703141?url=search-alias%3Dtoys-and-games&field-keywords=birthday%20party%20supplies&sprefix=birthday%2Caps%2C168");
        	eventCountdown.ClickActiveImageSelectBtn();
        	SelectFile selectFile = new SelectFile(webDriver);
        	selectFile.SwitchToSelectFileFrm();
        	selectFile.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "HanSolo1.jpg");
        	selectFile.ClickUploadBtn();
        	selectFile.WaitForFileUploaded("HanSolo1.jpg");
        	selectFile.ClickNextBtn();
        	selectFile.ClickPublicLocalFilesRdb();
        	selectFile.ClickNextBtn();
        	selectFile.VerifyFileImagePresent("HanSolo");
        	selectFile.ClickSaveBtn();
        	overlay.SwitchToActiveFrame();
        	eventCountdown.EnterActiveBackgroundColor("#FF0000");
        	eventCountdown.EnterExpiryCallToAction("http://www.nbc.com");
        	eventCountdown.ClickExpiryImageSelectBtn();
        	selectFile.SwitchToSelectFileFrm();
        	selectFile.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "nbclogosmall.jpg");
        	selectFile.ClickUploadBtn();
        	selectFile.WaitForFileUploaded("nbclogosmall.jpg");
        	selectFile.ClickNextBtn();
        	selectFile.ClickPublicLocalFilesRdb();
        	selectFile.ClickNextBtn();
        	selectFile.VerifyFileImagePresent("nbclogosmall");
        	selectFile.ClickSaveBtn();
        	overlay.SwitchToActiveFrame();
        	eventCountdown.EnterExpiryBackgroundColor("#00FFFF");
        	contentParent.ClickSaveBtn();
        	overlay.switchToDefaultContent(false);
        	contentParent.VerifyMessageStatus("Event Countdown " + eventCountdownTitle + " has been created.");
        	
        	//TODO - some extra steps as time allows for the separate content types and publishing/unpublishing
    }
    
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive"}, dependsOnMethods = {"CountdownClockEvents_TC4887"}, alwaysRun=true)
	public void Cleanup() throws Exception {
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
		taxonomy.NavigateSite("Modules");
    	overlay.SwitchToActiveFrame();
    	Modules modules = new Modules(webDriver);
    	modules.EnterFilterName("Event Countdown");
    	modules.DisableModule("Event Countdown");
    	overlay.ClickOverlayTab("Uninstall");
        overlay.SwitchToActiveFrame();
        if (modules.IsModuleInstalled("Event Countdown")) {
        	modules.UninstallModule("Event Countdown");
        	overlay.SwitchToActiveFrame();
        }
	}
}
