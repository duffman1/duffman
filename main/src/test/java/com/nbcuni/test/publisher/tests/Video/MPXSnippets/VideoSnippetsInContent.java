package com.nbcuni.test.publisher.tests.Video.MPXSnippets;

import java.util.Arrays;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.TextFormat;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.EmbedVideo;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;

public class VideoSnippetsInContent extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC6534
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/28025359436
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx"})
    public void VideoSnippetsInContent_TC6534() throws Exception{
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        	
        	Reporter.log("SETUP");
        	Settings settings = new Settings(webDriver);
        	settings.ConfigureMPXIfNeeded();
        	navigation.Content("Files", "mpxMedia");
        	SearchFor searchFor = new SearchFor(webDriver);
        	searchFor.EnterTitle("AutomationDefault");
        	searchFor.ClickApplyBtn();
        	String mpxVideoURL = searchFor.GetFirstMPXMediaSearchResultURL();
        	
        	Reporter.log("STEP 2");
        	navigation.Modules();
        	Modules modules = new Modules(webDriver);
        	modules.EnableModule("Publisher WYSIWYG");
        	
        	Reporter.log("STEP 3");
        	navigation.Configuration("Text formats");
        	TextFormat textFormat = new TextFormat(webDriver);
        	textFormat.ClickConfigureLnk("Publisher");
        	textFormat.ClickEnabledFilters(Arrays.asList("Limit allowed HTML tags", "Publisher youtube formatter", 
        			"Publisher mpx video formatter", "Convert line breaks into HTML", 
        			"Correct faulty and chopped off HTML"));
        	textFormat.EnterAllowedHTMLTags("<a> <em> <strong> <youtube> <div> <p> <mpx>");
        	textFormat.ClickSaveConfigurationBtn();
        	contentParent.VerifyMessageStatus("The text format Publisher has been updated.");
        	
        	Reporter.log("STEP 4");
        	navigation.AddContent("Movie");
        	BasicInformation basicInformation = new BasicInformation(webDriver);
        	String movieTitle = random.GetCharacterString(15);
        	basicInformation.EnterTitle(movieTitle);
        	basicInformation.EnterSynopsis("test synopsis");
        	basicInformation.ClickEmbedMPXBtn();
        	
        	Reporter.log("STEP 5 AND 6");
        	EmbedVideo embedVideo = new EmbedVideo(webDriver);
        	embedVideo.EnterMPXVideoURL(mpxVideoURL);
        	embedVideo.EnterWidth("640");
        	embedVideo.EnterHeight("360");
        	embedVideo.ClickOkBtn();
        	basicInformation.ClickCoverSelectBtn();
        	SelectFile selectFile = new SelectFile(webDriver);
        	selectFile.SelectDefaultCoverImg();
        	contentParent.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
        	
        	Reporter.log("STEP 7");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "360", "640");
        	
        	Reporter.log("STEP 8");
        	WorkBench workBench = new WorkBench(webDriver);
        	workBench.ClickWorkBenchTab("Edit Draft");
        	basicInformation.ClickEmbedMPXBtn();
        	embedVideo.EnterMPXVideoURL(mpxVideoURL);
        	embedVideo.EnterWidth("100");
        	embedVideo.EnterHeight("100");
        	embedVideo.ClickOkBtn();
        	contentParent.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been updated.");
        	
        	Reporter.log("STEP 9");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "360", "640");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "100", "100");
        	
        	Reporter.log("STEP 10");
        	workBench.ClickWorkBenchTab("Edit Draft");
        	embedVideo.RightClickEditMPXVideoBtn("2");
        	
        	Reporter.log("STEP 11");
        	embedVideo.EnterWidth("200");
        	embedVideo.EnterHeight("200");
        	embedVideo.ClickOkBtn();
        	contentParent.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been updated.");
        	
        	Reporter.log("STEP 12");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "200", "200");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "100", "100");
        	
        	Reporter.log("STEP 13");
        	workBench.ClickWorkBenchTab("Edit Draft");
        	basicInformation.ClickEmbedYoutubeBtn();
        	embedVideo.EnterYouTubeVideoURL("http://www.youtube.com/embed/yp9pTFcD2uk");
        	embedVideo.EnterHeight("360");
        	embedVideo.EnterWidth("640");
        	embedVideo.ClickOkBtn();
        	contentParent.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been updated.");
        	
        	Reporter.log("STEP 14");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "200", "200");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "100", "100");
        	embedVideo.VerifyVideoPresent("//youtube.com/embed/yp9pTFcD2uk", "360", "640");
        	
        	Reporter.log("STEP 15");
        	workBench.ClickWorkBenchTab("Edit Draft");
        	basicInformation.ClickEmbedYoutubeBtn();
        	embedVideo.EnterYouTubeVideoURL("http://www.youtube.com/embed/yp9pTFcD2uk");
        	embedVideo.EnterHeight("200");
        	embedVideo.EnterWidth("200");
        	embedVideo.ClickOkBtn();
        	contentParent.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been updated.");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "200", "200");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "100", "100");
        	embedVideo.VerifyVideoPresent("//youtube.com/embed/yp9pTFcD2uk", "360", "640");
        	embedVideo.VerifyVideoPresent("//youtube.com/embed/yp9pTFcD2uk", "200", "200");
        	
        	Reporter.log("STEP 16");
        	workBench.ClickWorkBenchTab("Edit Draft");
        	embedVideo.RightClickEditYouTubeVideoBtn("2");
        	embedVideo.EnterWidth("100");
        	embedVideo.EnterHeight("100");
        	embedVideo.ClickOkBtn();
        	contentParent.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been updated.");
        	
        	Reporter.log("STEP 17");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "200", "200");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "100", "100");
        	embedVideo.VerifyVideoPresent("//youtube.com/embed/yp9pTFcD2uk", "100", "100");
        	embedVideo.VerifyVideoPresent("//youtube.com/embed/yp9pTFcD2uk", "200", "200");
        	
        	Reporter.log("STEP 18");
        	workBench.ClickWorkBenchTab("Revisions");
        	Revisions revisions = new Revisions(webDriver);
        	revisions.SelectChangeState("Published");
        	revisions.ClickUpdateStateBtn();
        	contentParent.VerifyMessageStatus(movieTitle + " transitioned to the published state.");
        	String currentURL = webDriver.getCurrentUrl();
        	webDriver.manage().deleteAllCookies();
        	applib.openSitePage(currentURL);
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "200", "200");
        	embedVideo.VerifyVideoPresent("player.theplatform.com/p", "100", "100");
        	embedVideo.VerifyVideoPresent("//youtube.com/embed/yp9pTFcD2uk", "100", "100");
        	embedVideo.VerifyVideoPresent("//youtube.com/embed/yp9pTFcD2uk", "200", "200");
        	
        	
    }
}
