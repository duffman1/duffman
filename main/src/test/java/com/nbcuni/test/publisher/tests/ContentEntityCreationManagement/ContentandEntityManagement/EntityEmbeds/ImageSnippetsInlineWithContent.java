package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.EntityEmbeds;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Configuration.TextFormat;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ImageSnippetsInlineWithContent extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC6666
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/28521702752
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void ImageSnippetsInlineWithContent_TC6666() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        	
        	Reporter.log("STEP 2");
        	navigation.Modules();
        	Modules modules = new Modules(webWebWebDriver);
        	modules.EnableModule("Publisher WYSIWYG");
        	modules.EnableModule("Focal Point");
        	
        	Reporter.log("STEP 3");
        	navigation.Configuration("Text formats");
        	TextFormat textFormat = new TextFormat(webWebWebDriver);
        	textFormat.ClickConfigureLnk("Publisher");
        	textFormat.ClickEnabledFilters(Arrays.asList("Limit allowed HTML tags", "Publisher youtube formatter", 
        			"Publisher mpx video formatter", "Convert line breaks into HTML", 
        			"Correct faulty and chopped off HTML"));
        	textFormat.EnterAllowedHTMLTags("<a> <em> <strong> <youtube> <div> <p> <mpx>");
        	textFormat.ClickSaveConfigurationBtn();
        	contentParent.VerifyMessageStatus("The text format Publisher has been updated.");
        	
        	Reporter.log("STEP 4");
        	navigation.AddContent("Movie");
        	BasicInformation basicInformation = new BasicInformation(webWebWebDriver);
        	String movieTitle = random.GetCharacterString(15);
        	basicInformation.EnterTitle(movieTitle);
        	basicInformation.ClickEmbedImageBtn();;
        	
        	Reporter.log("STEP 5");
        	SelectFile selectFile = new SelectFile(webWebWebDriver);
        	selectFile.SwitchToSelectFileFrm();
        	selectFile.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "HanSolo1.jpg");
        	selectFile.ClickUploadBtn();
        	selectFile.WaitForFileUploaded("HanSolo1.jpg");
        	selectFile.ClickNextBtn();
        	Boolean publicFileOptionPresent = selectFile.ClickPublicLocalFilesRdb();
        	if (publicFileOptionPresent == true) {
        		selectFile.ClickNextBtn();
        	}
        	
        	Reporter.log("STEP 6");
        	selectFile.VerifyFocalPointCrossHairLocation("125", "157.5");
        	
        	Reporter.log("STEP 7");
        	selectFile.ClickSaveBtn();
        	selectFile.WaitForSelectFileFrameClose();
        	
        	Reporter.log("STEP 8");
        	EmbedVideo embedVideo = new EmbedVideo(webWebWebDriver);
        	embedVideo.SwitchToSynopsisFrm();
        	embedVideo.VerifyEmbeddedImagePresent("1", "HanSolo", "200", "252");
        	webWebWebDriver.switchTo().defaultContent();
        	
        	Reporter.log("STEP 9 and STEP 10");
        	embedVideo.DoubleClickImagePropertiesBtn("1");
        	ImageProperties imageProperties = new ImageProperties(webWebWebDriver);
        	imageProperties.EnterWidth("300");
        	imageProperties.EnterHeight("378");
        	imageProperties.ClickOKBtn();
        	imageProperties.WaitForImagePropertiesFrameClose();
        	
        	Reporter.log("STEP 11");
        	embedVideo.SwitchToSynopsisFrm();
        	embedVideo.VerifyEmbeddedImagePresent("1", "HanSolo", "300", "378");
        	webWebWebDriver.switchTo().defaultContent();
        	
        	Reporter.log("STEP 12 - 14");
        	basicInformation.ClickCoverSelectBtn();
        	selectFile.SelectDefaultCoverImg();
        	
        	Reporter.log("STEP 15");
        	contentParent.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
        	
        	Reporter.log("STEP 16");
        	embedVideo.VerifyEmbeddedImageAdjustedPresent("1", "HanSolo", "300", "378");
        	
        	Reporter.log("STEP 17");
        	WorkBench workBench = new WorkBench(webWebWebDriver);
        	workBench.ClickWorkBenchTab("Edit Draft");
        	basicInformation.ClickEmbedImageBtn();
        	selectFile.SwitchToSelectFileFrm();
        	selectFile.ClickViewLibraryBtn();
            selectFile.EnterFileName("HanSolo");
            contentParent.WaitForThrobberNotPresent();
            selectFile.ClickApplyBtn();
            contentParent.WaitForThrobberNotPresent();
            selectFile.VerifyMediaThumbnailImagePresent("HanSolo", "1");
            selectFile.ClickMediaThumbnailImage("1");
            selectFile.ClickSubmitBtn();
            selectFile.WaitForSelectFileFrameClose();
            
            Reporter.log("STEP 18");
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been updated.");
            
            Reporter.log("STEP 19");
            embedVideo.VerifyEmbeddedImagePresent("1", "HanSolo", "200", "252");
            embedVideo.VerifyEmbeddedImageAdjustedPresent("2", "HanSolo", "300", "378");
            
            Reporter.log("STEP 20");
            workBench.ClickWorkBenchTab("Edit Draft");
        	basicInformation.ClickEmbedYoutubeBtn();
        	embedVideo.EnterYouTubeVideoURL("http://www.youtube.com/embed/yp9pTFcD2uk");
        	embedVideo.EnterHeight("360");
        	embedVideo.EnterWidth("640");
        	embedVideo.ClickOkBtn();
        	contentParent.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been updated.");
        	
        	Reporter.log("STEP 21");
        	embedVideo.VerifyEmbeddedImagePresent("1", "HanSolo", "200", "252");
            embedVideo.VerifyEmbeddedImageAdjustedPresent("2", "HanSolo", "300", "378");
            embedVideo.VerifyVideoPresent("//youtube.com/embed/yp9pTFcD2uk", "360", "640");
            
            Reporter.log("STEP 22");
            workBench.ClickWorkBenchTab("Edit Draft");
            PublishingOptions publishingOptions = new PublishingOptions(webWebWebDriver);
            publishingOptions.ClickPublishingOptionsLnk();
            publishingOptions.SelectModerationState("Published");
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been updated.");
            String movieURL = webWebWebDriver.getCurrentUrl();
            Logout logout = new Logout(webWebWebDriver);
            logout.ClickLogoutBtn();
            applib.openSitePage(movieURL);
            embedVideo.VerifyEmbeddedImagePresent("1", "HanSolo", "200", "252");
            embedVideo.VerifyEmbeddedImageAdjustedPresent("2", "HanSolo", "300", "378");
            embedVideo.VerifyVideoPresent("//youtube.com/embed/yp9pTFcD2uk", "360", "640");
            
        	
    }
}
