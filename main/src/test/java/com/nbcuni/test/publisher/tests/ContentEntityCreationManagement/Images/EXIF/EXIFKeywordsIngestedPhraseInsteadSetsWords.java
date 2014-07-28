package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.EXIF;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.SimpleEXIFIPTCMappings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;

import org.testng.annotations.Test;

public class EXIFKeywordsIngestedPhraseInsteadSetsWords extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC17
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/16715541439
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void EXIFKeywordsIngestedPhraseInsteadSetsWords_TC17() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 1a
            taxonomy.NavigateSite("Configuration>>Media>>Simple EXIF/IPTC Mappings");
            overlay.SwitchToActiveFrame();
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
            overlay.switchToDefaultContent(true);
            
            //Step 2
            taxonomy.NavigateSite("Content>>Files");
            overlay.SwitchToActiveFrame();
            Content content = new Content(webDriver, applib);
            content.ClickAddFileLnk();
            overlay.SwitchToActiveFrame();
            AddFile addFile = new AddFile(webDriver, applib);
            addFile.ClickAddFilesLnk();
            if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
            	addFile.ClickPicturesUploadBtn();
            	addFile.ClickTestPictureExifDataBtn();
            	addFile.ClickOpenBtn();
            }
            else {
            	addFile.EnterPathToFile_Win(applib.getPathToMedia());
            	addFile.ClickGoBtn_Win();
            	addFile.EnterFileName_Win("NUP_155306_0046.JPG");
            	addFile.ClickOpenBtn();
            }
            addFile.ClickStartUploadLnk();
            addFile.WaitForSuccessfulUpload();
            addFile.ClickNextBtn();
            overlay.SwitchToActiveFrame();
            
            //Step 3
            EditImage editImage = new EditImage(webDriver, applib);
            editImage.VerifyTitleTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifySourceValue("1", "Episodic");
            editImage.VerifyCreditValue("1", "Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("1", "2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("1", "NUP_155306, Revolution, Episode 118, Season 1");
            editImage.ClickSaveBtn("1");
            overlay.SwitchToActiveFrame();
            ContentParent contentParent = new ContentParent(webDriver, applib);
            contentParent.VerifyMessageStatus("Image");
            contentParent.VerifyMessageStatus("has been updated.");
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent(true);
            
            //Step 4
            taxonomy.NavigateSite("Content>>Add content>>Media Gallery");
            overlay.SwitchToActiveFrame();
            BasicInformation basicInformation = new BasicInformation(webDriver);
            String title = random.GetCharacterString(15);
            basicInformation.EnterTitle(title);
            basicInformation.ClickMediaItemsSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SwitchToSelectFileFrm();
            addFile.ClickAddFilesLnk();
            if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
            	addFile.ClickPicturesUploadBtn();
            	addFile.ClickTestPictureExifDataBtn();
            	addFile.ClickOpenBtn();
            }
            else {
            	addFile.EnterPathToFile_Win(applib.getPathToMedia());
            	addFile.ClickGoBtn_Win();
            	addFile.EnterFileName_Win("NUP_155306_0046.JPG");
            	addFile.ClickOpenBtn();
            }
            addFile.ClickStartUploadLnk();
            addFile.WaitForSuccessfulUpload();
            addFile.ClickNextBtn();
            overlay.SwitchToActiveFrame();
            
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
            overlay.SwitchToActiveFrame();
            
            //Step 7
            basicInformation.ClickCoverSelectBtn();
            selectFile.SwitchToSelectFileFrm();
        	selectFile.EnterFilePath(applib.getPathToMedia() + "NUP_155306_0046.JPG");
        	selectFile.ClickUploadBtn();
        	selectFile.WaitForFileUploaded("NUP_155306_0046.JPG");
        	selectFile.ClickNextBtn();
        	selectFile.ClickPublicLocalFilesRdb();
        	selectFile.ClickNextBtn();
        	
        	//Step 8
        	editImage.VerifyTitleTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifySourceValue("1", "Episodic");
            editImage.VerifyCreditValue("1", "Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("1", "2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("1", "NUP_155306, Revolution, Episode 118, Season 1");
        	selectFile.VerifyFileImagePresent("NUP_155306_0046");
        	selectFile.ClickSaveBtn();
        	overlay.SwitchToActiveFrame();
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
            
            //Step 9
            //TODO - automate this extra step as time allows
            
            //Step 10 - NA
            
    }
}
