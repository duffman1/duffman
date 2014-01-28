package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.EXIFKeywordsIngestedAsPhraseInsteadSetsWords;


import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.*;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


public class EXIFKeywordsIngestedPhraseInsteadSetsWords extends ParentTest{
	

    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to P7 using Admin (user 1) credentials ,Login Successful 
     * Step 2 - Go to Content-> Files -> Click Add Files -> On Add file overlay -> Click on Add files -> Select the test images -> Click Next  Note : Test images are attached to the test cases. Please use only those selected images to test as their EXIF data field keyword has commas. And this test is to verify  special character comma  gets ingested in P7 successfully.,"Edit multiple files" overlay appears with all the test images  
     * Step 3 - Verify that in EXIF data under "Keyword" column data values are separated by comma's ( , ) and hence comma's are successfully ingested in the P7 EXIF fields.   ,Special character comma (,) are succesfully ingested in P7 EXIF fields.  
     * Step 4 - Go to Content -> Add Content media Gallery -> under "Create Media Gallery" overlay -> Set all the required fields ->  Click on Media Items "Select" tab -> under "Select a file" overlay -> Click on Add files -> select all test images -> Click Next  ,"Create Media Gallery" overlay appears. 
     * Step 5 - Verify that under MEDIA ITEMS all the test images appears -> Click on Edit link of image. ,"Edit image" overlay appears 
     * Step 6 - Verify that under "Keyword" field data values are separated by comma's (,) and special character comma are succesfully ingested in P7 -> Save ,Special character comma (,) are succesfully ingested in P7 EXIF fields and Title page of Media Gallery appears. 
     * Step 7 - Click on "Edit draft" tab of the media gallery content node ->  On View page Click on Cover Item "Select" tab-> Upload a test image-> Click Next. ,Select a file overlay appears. 
     * Step 8 - Verify that in EXIF data under "Keyword" column data values are separated by comma's ( , ) and hence comma's are successfully ingested in the P7 EXIF fields-> Save ,Special character comma (,) are succesfully ingested in P7 EXIF fields and Title page of Media Gallery appears and media gallery Edit page appears. 
     * Step 9 - Under coveritem click "Edit" link -> verify under "Edit image overlay Keyword field has succussfully saved the data and special character. ,Special character comma (,) are succesfully saved in P7 EXIF fields.  
     * Step 10 - Log out from P7 ,Logout successful 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full" })
    public void EXIFKeywordsIngestedPhraseInsteadSetsWords() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 2
            taxonomy.NavigateSite("Content>>Files");
            overlay.SwitchToActiveFrame();
            Content content = new Content(webDriver);
            content.ClickAddFileLnk();
            overlay.SwitchToActiveFrame();
            AddFile addFile = new AddFile(webDriver, applib);
            PageFactory.initElements(webDriver, addFile);
            addFile.ClickAddFilesLnk();
            addFile.ClickPicturesUploadBtn();
            addFile.ClickTestPictureBtn();
            addFile.ClickOpenBtn();
            addFile.ClickStartUploadLnk();
            addFile.WaitForSuccessfulUpload();
            addFile.ClickNextBtn();
            overlay.SwitchToActiveFrame();
            
            //Step 3
            EditImage editImage = new EditImage(webDriver);
            PageFactory.initElements(webDriver, editImage);
            editImage.VerifyTitleTextValue("NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("f/5.6");
            editImage.VerifySourceValue("Episodic");
            editImage.VerifyCreditValue("Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("NUP_155306, Revolution, Episode 118, Season 1");
            editImage.ClickSaveBtn();
            overlay.SwitchToActiveFrame();
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.VerifyMessageStatus("Image");
            contentParent.VerifyMessageStatus("has been updated.");
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            
            //Step 4
            taxonomy.NavigateSite("Content>>Add content>>Media Gallery");
            overlay.SwitchToActiveFrame();
            BasicInformation basicInformation = new BasicInformation(webDriver);
            PageFactory.initElements(webDriver, basicInformation);
            String title = random.GetCharacterString(15);
            basicInformation.EnterTitle(title);
            basicInformation.ClickMediaItemsSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            PageFactory.initElements(webDriver, selectFile);
            selectFile.SwitchToSelectFileFrm();
            addFile.ClickAddFilesLnk();
            addFile.ClickPicturesUploadBtn();
            addFile.ClickTestPictureBtn();
            addFile.ClickOpenBtn();
            addFile.ClickStartUploadLnk();
            addFile.WaitForSuccessfulUpload();
            addFile.ClickNextBtn();
            overlay.SwitchToActiveFrame();
            
            //Step 5
            MediaItems mediaItems = new MediaItems(webDriver);
            PageFactory.initElements(webDriver, mediaItems);
            mediaItems.VerifyFileImagePresent("NUP_155306_0046");
            mediaItems.ClickEditBtn();
            
            //Step 6
            editImage.VerifyTitleTextValue("NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("f/5.6");
            editImage.VerifySourceValue("Episodic");
            editImage.VerifyCreditValue("Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("NUP_155306, Revolution, Episode 118, Season 1");
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
        	editImage.VerifyTitleTextValue("NUP_155306_0046.JPG");
            editImage.VerifyAltTextValue("f/5.6");
            editImage.VerifySourceValue("Episodic");
            editImage.VerifyCreditValue("Brownie Harris/NBC");
            editImage.VerifyCopyrightValue("2013 NBCUniversal Media, LLC");
            editImage.VerifyKeywordsValue("NUP_155306, Revolution, Episode 118, Season 1");
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
