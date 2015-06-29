package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.EXIF;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.SimpleEXIFIPTCMappings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PopulateEXIFBulkUpload extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC3192
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/19126670410
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify"})
    public void PopulateEXIFBulkUpload_TC3192() throws Exception{
         
        	Reporter.log("STEP 1");
        	Boolean publicFileOptionPresent = true;
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
            Reporter.log("SETUP");
            navigation.Configuration("Simple EXIF/IPTC Mappings");
            SimpleEXIFIPTCMappings simpleEXIFIPTCMappings = new SimpleEXIFIPTCMappings(webWebWebDriver, applib);
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
            
            Reporter.log("STEP 2");
            navigation.AddContent("Media Gallery");
            BasicInformation basicInformation = new BasicInformation(webWebWebDriver);
            String title = random.GetCharacterString(15);
            basicInformation.EnterTitle(title);
            
            Reporter.log("STEP 3");
            AddFile addFile = new AddFile(webWebWebDriver);
            MediaItems mediaItems = new MediaItems(webWebWebDriver);
            for(int Count=0;Count<2;Count++) {
            	webWebWebDriver.switchTo().defaultContent();
            	if (Count == 0) {
            		basicInformation.ClickMediaItemsSelectBtn();
            	}
            	else {
            		contentParent.WaitForThrobberNotPresent();
            		mediaItems.ClickAddBtn();
            	}
                SelectFile selectFile = new SelectFile(webWebWebDriver);
                selectFile.SwitchToSelectFileFrm();
                addFile.ClickAddFilesLnk();
                if (((RemoteWebDriver)webWebWebDriver).getCapabilities().getPlatform().toString() == "MAC") {
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
            }
            webWebWebDriver.switchTo().defaultContent();
            mediaItems.VerifyFileImagePresent("NUP_155306_0046", "1");
            mediaItems.VerifyFileImagePresent("NUP_155306_0046", "2");
            
            Reporter.log("STEP 4");
            mediaItems.ClickEditBtn("1");
            EditImage editImage = new EditImage(webWebWebDriver);
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyTitleTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifySourceValue("1", "Episodic");
            editImage.VerifyCreditValue("1", "Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("1", "2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("1", "NUP_155306, Revolution, Episode 118, Season 1");
            editImage.ClickCloseWindowImg();
            
            Reporter.log("STEP 5");
            mediaItems.ClickEditAllBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyTitleTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifySourceValue("1", "Episodic");
            editImage.VerifyCreditValue("1", "Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("1", "2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("1", "NUP_155306, Revolution, Episode 118, Season 1");
            
            Reporter.log("STEP 6");
            String titleTxt1 = random.GetCharacterString(15);
            String altTxt1 = random.GetCharacterString(15);
            String sourceTxt1 = random.GetCharacterString(15);
            String creditTxt1 = random.GetCharacterString(15);
            String copyrightTxt1 = random.GetCharacterString(15);
            String keywordsTxt1 = random.GetCharacterString(15);
            String titleTxt2 = random.GetCharacterString(15);
            String altTxt2 = random.GetCharacterString(15);
            String sourceTxt2 = random.GetCharacterString(15);
            String creditTxt2 = random.GetCharacterString(15);
            String copyrightTxt2 = random.GetCharacterString(15);
            String keywordsTxt2 = random.GetCharacterString(15);
            editImage.EnterTitleText("1", titleTxt1);
            editImage.EnterAltText("1", altTxt1);
            editImage.EnterSource("1", sourceTxt1);
            editImage.EnterCreditValue("1", creditTxt1);
            editImage.EnterCopyright("1", copyrightTxt1);
            editImage.EnterKeywordsValue("1", keywordsTxt1);
            editImage.EnterTitleText("2", titleTxt2);
            editImage.EnterAltText("2", altTxt2);
            editImage.EnterSource("2", sourceTxt2);
            editImage.EnterCreditValue("2", creditTxt2);
            editImage.EnterCopyright("2", copyrightTxt2);
            editImage.EnterKeywordsValue("2", keywordsTxt2);
            editImage.ClickSaveBtn("2");
            editImage.WaitForEditImageFrameClose();
            
            Reporter.log("STEP 7");
            mediaItems.ClickEditAllBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyTitleTextValue("1", titleTxt1);
            editImage.VerifyAltTextValue("1", altTxt1);
            editImage.VerifySourceValue("1", sourceTxt1);
            editImage.VerifyCreditValue("1", creditTxt1);
            editImage.VerifyCopyrightValue("1", copyrightTxt1);
            editImage.VerifyKeywordsValue("1", keywordsTxt1);
            editImage.VerifyTitleTextValue("2", titleTxt2);
            editImage.VerifyAltTextValue("2", altTxt2);
            editImage.VerifySourceValue("2", sourceTxt2);
            editImage.VerifyCreditValue("2", creditTxt2);
            editImage.VerifyCopyrightValue("2", copyrightTxt2);
            editImage.VerifyKeywordsValue("2", keywordsTxt2);
            editImage.ClickCloseWindowImg();
            editImage.WaitForEditImageFrameClose();
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
            
            Reporter.log("STEP 8");
            navigation.AddContent("Media Gallery");
            String title2 = random.GetCharacterString(15);
            basicInformation.EnterTitle(title2);
            basicInformation.ClickCoverSelectBtn();
            
            Reporter.log("STEP 9");
            SelectFile selectFile = new SelectFile(webWebWebDriver);
            selectFile.SwitchToSelectFileFrm();
        	selectFile.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "IPTCDefault.jpg");
        	selectFile.ClickUploadBtn();
        	selectFile.WaitForFileUploaded("IPTCDefault.jpg");
        	selectFile.ClickNextBtn();
        	
        	Reporter.log("STEP 10");
        	publicFileOptionPresent = selectFile.ClickPublicLocalFilesRdb();
        	if (publicFileOptionPresent == true) {
        		selectFile.ClickNextBtn();
        	}
        	selectFile.VerifyFileImagePresent("IPTCDefault");
            editImage.VerifyTitleTextValue("1", "drpin075402");
            editImage.VerifyAltTextValue("1", "drpin075402");
            editImage.VerifySourceValue("1", "David Riecks Photography");
            editImage.VerifyCreditValue("1", "©1985 David Riecks: www.riecks.c");
            editImage.VerifyCopyrightValue("1", "©1985 David Riecks, All Rights Reserved");
            editImage.VerifyKeywordsValue("1", "environment, ecology, ecosystem, environmentalism, scenery, nature, land, mountains, mount, Himalayans, sky, skies, cloud, clouds, concepts, concept, conceptual, summit, peak, weather, snow, snowing, snowfall, outdoors, outdoor, outside");
            selectFile.ClickSaveBtn();
            webWebWebDriver.switchTo().defaultContent();
            
            Reporter.log("STEP 11");
            CoverItem coverItem = new CoverItem(webWebWebDriver);
            coverItem.ClickEditBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyTitleTextValue("1", "drpin075402");
            editImage.VerifyAltTextValue("1", "drpin075402");
            editImage.VerifySourceValue("1", "David Riecks Photography");
            editImage.VerifyCreditValue("1", "©1985 David Riecks: www.riecks.c");
            editImage.VerifyCopyrightValue("1", "©1985 David Riecks, All Rights Reserved");
            editImage.VerifyKeywordsValue("1", "environment, ecology, ecosystem, environmentalism, scenery, nature, land, mountains, mount, Himalayans, sky, skies, cloud, clouds, concepts, concept, conceptual, summit, peak, weather, snow, snowing, snowfall, outdoors, outdoor, outside");
            
            Reporter.log("STEP 12");
            String titleTxtIPTC = random.GetCharacterString(15);
            String altTxtIPTC = random.GetCharacterString(15);
            String sourceTxtIPTC = random.GetCharacterString(15);
            String creditTxtIPTC = random.GetCharacterString(15);
            String copyrightTxtIPTC = random.GetCharacterString(15);
            String keywordsTxtIPTC = random.GetCharacterString(15);
            editImage.EnterTitleText("1", titleTxtIPTC);
            editImage.EnterAltText("1", altTxtIPTC);
            editImage.EnterSource("1", sourceTxtIPTC);
            editImage.EnterCreditValue("1", creditTxtIPTC);
            editImage.EnterCopyright("1", copyrightTxtIPTC);
            editImage.EnterKeywordsValue("1", keywordsTxtIPTC);
            editImage.ClickSaveBtn("2");
            editImage.WaitForEditImageFrameClose();
            coverItem.ClickEditBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyTitleTextValue("1", titleTxtIPTC);
            editImage.VerifyAltTextValue("1", altTxtIPTC);
            editImage.VerifySourceValue("1", sourceTxtIPTC);
            editImage.VerifyCreditValue("1", creditTxtIPTC);
            editImage.VerifyCopyrightValue("1", copyrightTxtIPTC);
            editImage.VerifyKeywordsValue("1", keywordsTxtIPTC);
            
    }
}
