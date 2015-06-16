package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.AddApplyEmbeddedMetadataButton;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.SimpleEXIFIPTCMappings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import org.testng.annotations.Test;

public class AddApplyEmbeddedMetadataButtonAllImageForms extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17266417771 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "broken"})
    public void AddApplyEmbeddedMetadataButtonAllImageForms_Test() throws Exception{
         
        	//Step 1
    		Boolean publicFileOptionPresent = true;
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	//Step 2
        	navigation.Configuration("Simple EXIF/IPTC Mappings");
            SimpleEXIFIPTCMappings simpleEXIFIPTCMappings = new SimpleEXIFIPTCMappings(webDriver, applib);
            simpleEXIFIPTCMappings.SelectAltText("Title");
            simpleEXIFIPTCMappings.SelectTitleText("Title");
            simpleEXIFIPTCMappings.SelectCaption("Caption");
            simpleEXIFIPTCMappings.SelectCopyright("Copyright");
            simpleEXIFIPTCMappings.SelectCredit("Credit");
            simpleEXIFIPTCMappings.SelectKeywords("Keywords");
            simpleEXIFIPTCMappings.SelectMediaCategories("none");
            simpleEXIFIPTCMappings.SelectMediaTags("Title");
            simpleEXIFIPTCMappings.SelectSource("Source");
            simpleEXIFIPTCMappings.ClickSaveBtn();
            
            //Step 3
            navigation.AddContent("Post");
            CoverMedia coverMedia = new CoverMedia(webDriver);
            coverMedia.ClickSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SwitchToSelectFileFrm();
        	selectFile.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "NUP_155306_0046.JPG");
        	selectFile.ClickUploadBtn();
        	selectFile.WaitForFileUploaded("NUP_155306_0046.JPG");
        	selectFile.ClickNextBtn();
        	publicFileOptionPresent = selectFile.ClickPublicLocalFilesRdb();
        	if (publicFileOptionPresent == true) {
        		selectFile.ClickNextBtn();
        	}
        	selectFile.VerifyFileImagePresent("NUP_155306_0046");
            
            //Step 4
            EditImage editImage = new EditImage(webDriver);
            editImage.VerifyApplyEmbeddedMetadataBtnPresent();
            
            //Step 5
            editImage.VerifyTitleTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifySourceValue("1", "Episodic");
            editImage.VerifyCreditValue("1", "Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("1", "2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("1", "NUP_155306, Revolution, Episode 118, Season 1");
            
            //Step 6
            editImage.EnterTitleText("1", "");
            editImage.EnterSource("1", "");
            editImage.ClickApplyEmbeddedMetadataBtn();
            editImage.VerifyTitleTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifySourceValue("1", "Episodic");
            
            //Step 7
            editImage.EnterTitleText("1", "ModifiedTitle.JPG");
            editImage.EnterSource("1", "ModifiedSource");
            editImage.ClickSaveBtn("1");
            
            //Step 8
            webDriver.switchTo().defaultContent();
            coverMedia.ClickEditBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyTitleTextValue("1", "ModifiedTitle.JPG");
            editImage.VerifySourceValue("1", "ModifiedSource");
            
            //Step 9
            editImage.EnterTitleText("1", "");
            editImage.EnterSource("1", "");
            editImage.ClickApplyEmbeddedMetadataBtn();
            editImage.VerifyTitleTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifySourceValue("1", "Episodic");
            
            //Step 10
            editImage.EnterTitleText("1", "");
            editImage.EnterSource("1", "");
            editImage.ClickSaveFromEditFrmBtn();
            editImage.WaitForEditImageFrameClose();
            
            //Step 11
            coverMedia.ClickEditBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyTitleTextValue("1", "NUP_155306_0046.JPG");
            editImage.VerifySourceValue("1", "Episodic");
            
            //Steps 12 through 25 TODO automate as time allows
            
            
    }
}
