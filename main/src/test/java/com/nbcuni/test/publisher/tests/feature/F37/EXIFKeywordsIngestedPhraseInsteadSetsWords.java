package com.nbcuni.test.publisher.tests.feature.F37;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.SimpleEXIFIPTCMappings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;

import org.testng.annotations.Test;

public class EXIFKeywordsIngestedPhraseInsteadSetsWords extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC17
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/16715541439
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "F37"})
    public void EXIFKeywordsIngestedPhraseInsteadSetsWords_TC17() throws Exception{
         
        	//Step 1
    		Boolean publicFileOptionPresent = true;
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
            //Step 1a
        	navigation.Configuration("Simple EXIF/IPTC Mappings");
            SimpleEXIFIPTCMappings simpleEXIFIPTCMappings = new SimpleEXIFIPTCMappings(webDriver, applib);
            simpleEXIFIPTCMappings.SelectAltText("Title");
            simpleEXIFIPTCMappings.SelectTitleText("Title");
            simpleEXIFIPTCMappings.SelectCaption("Caption");
            simpleEXIFIPTCMappings.SelectCopyright("Copyright");
            simpleEXIFIPTCMappings.SelectCredit("Credit");
            simpleEXIFIPTCMappings.SelectKeywords("Keywords");
            simpleEXIFIPTCMappings.SelectMediaCategories("none");
            simpleEXIFIPTCMappings.SelectMediaTags("none");
            simpleEXIFIPTCMappings.SelectSource("Source");
            simpleEXIFIPTCMappings.ClickSaveBtn();
            
            //Step 2
            navigation.Content("Files");
            Content content = new Content(webDriver);
            content.ClickAddFileLnk();
            AddFile addFile = new AddFile(webDriver);
            addFile.ClickAddFilesLnk();
            if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
            	addFile.ClickPicturesUploadBtn();
            	addFile.ClickTestPictureExifDataBtn();
            	addFile.ClickOpenBtn();
            }
            else {
            	addFile.EnterPathToFile_Win(config.getConfigValueFilePath("PathToMediaContent"));
            	addFile.ClickGoBtn_Win();
            	addFile.EnterFileName_Win("NUP_155306_0046.JPG");
            	addFile.ClickOpenBtn();
            }
            addFile.ClickStartUploadLnk();
            addFile.WaitForSuccessfulUpload();
            addFile.ClickNextBtn();
            
            //Step 3
            EditImage editImage = new EditImage(webDriver);
            editImage.VerifyTitleTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifySourceValue("1", "Episodic");
            editImage.VerifyCreditValue("1", "Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("1", "2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("1", "NUP_155306, Revolution, Episode 118, Season 1");
            editImage.ClickSaveBtn("1");
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.VerifyMessageStatus("Image");
            contentParent.VerifyMessageStatus("has been updated.");
            
            //Step 4
            navigation.AddContent("Media Gallery");
            BasicInformation basicInformation = new BasicInformation(webDriver);
            String title = random.GetCharacterString(15);
            basicInformation.EnterTitle(title);
            basicInformation.ClickMediaItemsSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SwitchToSelectFileFrm();
            addFile.ClickAddFilesLnk();
            if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
            	addFile.ClickPicturesUploadBtn();
            	addFile.ClickTestPictureExifDataBtn();
            	addFile.ClickOpenBtn();
            }
            else {
            	addFile.EnterPathToFile_Win(config.getConfigValueFilePath("PathToMediaContent"));
            	addFile.ClickGoBtn_Win();
            	addFile.EnterFileName_Win("NUP_155306_0046.JPG");
            	addFile.ClickOpenBtn();
            }
            addFile.ClickStartUploadLnk();
            addFile.WaitForSuccessfulUpload();
            addFile.ClickNextBtn();
            webDriver.switchTo().defaultContent();
            
            //Step 5
            MediaItems mediaItems = new MediaItems(webDriver);
            mediaItems.VerifyFileImagePresent("NUP_155306_0046", "1");
            mediaItems.ClickEditBtn("1");
            
            //Step 6
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyTitleTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifySourceValue("1", "Episodic");
            editImage.VerifyCreditValue("1", "Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("1", "2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("1", "NUP_155306, Revolution, Episode 118, Season 1");
            editImage.ClickCloseWindowImg();
            
            //Step 7
            basicInformation.ClickCoverSelectBtn();
            selectFile.SwitchToSelectFileFrm();
        	selectFile.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "NUP_155306_0046.JPG");
        	selectFile.ClickUploadBtn();
        	selectFile.WaitForFileUploaded("NUP_155306_0046.JPG");
        	selectFile.ClickNextBtn();
        	publicFileOptionPresent = selectFile.ClickPublicLocalFilesRdb();
        	if (publicFileOptionPresent == true) {
        		selectFile.ClickNextBtn();
        	}
        	
        	//Step 8
        	editImage.VerifyTitleTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifySourceValue("1", "Episodic");
            editImage.VerifyCreditValue("1", "Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("1", "2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("1", "NUP_155306, Revolution, Episode 118, Season 1");
        	selectFile.VerifyFileImagePresent("NUP_155306_0046");
        	selectFile.ClickSaveBtn();
        	selectFile.WaitForSelectFileFrameClose();
        	webDriver.switchTo().defaultContent();
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
            
            //Step 9
            //TODO - automate this extra step as time allows
            
            //Step 10 - NA
            
    }
}
