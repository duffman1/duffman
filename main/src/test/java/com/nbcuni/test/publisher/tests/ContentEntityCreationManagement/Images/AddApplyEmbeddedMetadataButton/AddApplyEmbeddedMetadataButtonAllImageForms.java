package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.AddApplyEmbeddedMetadataButton;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.SimpleEXIFIPTCMappings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import org.testng.annotations.Test;

public class AddApplyEmbeddedMetadataButtonAllImageForms extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17266417771 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AddApplyEmbeddedMetadataButtonAllImageForms_Test() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	//Step 2
            taxonomy.NavigateSite("Configuration>>Media>>Simple EXIF/IPTC Mappings");
            overlay.SwitchToActiveFrame();
            SimpleEXIFIPTCMappings simpleEXIFIPTCMappings = new SimpleEXIFIPTCMappings(webDriver, applib);
            simpleEXIFIPTCMappings.SelectAltText("Aperture");
            simpleEXIFIPTCMappings.SelectTitleText("Title");
            simpleEXIFIPTCMappings.SelectCaption("Caption");
            simpleEXIFIPTCMappings.SelectCopyright("Copyright");
            simpleEXIFIPTCMappings.SelectCredit("Credit");
            simpleEXIFIPTCMappings.SelectKeywords("Keywords");
            simpleEXIFIPTCMappings.SelectMediaCategories("none");
            simpleEXIFIPTCMappings.SelectMediaTags("Title");
            simpleEXIFIPTCMappings.SelectSource("Source");
            simpleEXIFIPTCMappings.ClickSaveBtn();
            overlay.ClickCloseOverlayLnk();
            
            //Step 3
            taxonomy.NavigateSite("Content>>Add content>>Post");
            overlay.SwitchToActiveFrame();
            CoverMedia coverMedia = new CoverMedia(webDriver);
            coverMedia.ClickSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SwitchToSelectFileFrm();
        	selectFile.EnterFilePath(applib.getPathToMedia() + "NUP_155306_0046.JPG");
        	selectFile.ClickUploadBtn();
        	selectFile.WaitForFileUploaded("NUP_155306_0046.JPG");
        	selectFile.ClickNextBtn();
        	selectFile.ClickPublicLocalFilesRdb();
        	selectFile.ClickNextBtn();
        	selectFile.VerifyFileImagePresent("NUP_155306_0046");
            
            //Step 4
            EditImage editImage = new EditImage(webDriver, applib);
            editImage.VerifyApplyEmbeddedMetadataBtnPresent();
            
            //Step 5
            editImage.VerifyTitleTextValue("NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("f/5.6");
            editImage.VerifySourceValue("Episodic");
            editImage.VerifyCreditValue("Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("NUP_155306, Revolution, Episode 118, Season 1");
            
            //Step 6
            editImage.EnterTitleText("");
            editImage.EnterSource("");
            editImage.ClickApplyEmbeddedMetadataBtn();
            editImage.VerifyTitleTextValue("NUP_155306_0046.JPG");
            editImage.VerifySourceValue("Episodic");
            
            //Step 7
            editImage.EnterTitleText("ModifiedTitle.JPG");
            editImage.EnterSource("ModifiedSource");
            editImage.ClickSaveBtn();
            overlay.SwitchToActiveFrame();
            
            //Step 8
            coverMedia.ClickEditBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyTitleTextValue("ModifiedTitle.JPG");
            editImage.VerifySourceValue("ModifiedSource");
            
            //Step 9
            editImage.EnterTitleText("");
            editImage.EnterSource("");
            editImage.ClickApplyEmbeddedMetadataBtn();
            editImage.VerifyTitleTextValue("NUP_155306_0046.JPG");
            editImage.VerifySourceValue("Episodic");
            
            //Step 10
            editImage.EnterTitleText("");
            editImage.EnterSource("");
            editImage.ClickSaveFromEditFrmBtn();
            editImage.WaitForEditImageFrameClose();
            
            //Step 11
            coverMedia.ClickEditBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyTitleTextValue("NUP_155306_0046.JPG");
            editImage.VerifySourceValue("Episodic");
            
            //Steps 12 through 25 TODO automate as time allows
            
    }
}
