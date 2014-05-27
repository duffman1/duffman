package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.RepresentativeImage;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class RepresentativeImage extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC3217
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/19212549516
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void RepresentativeImage_TC3217() throws Exception{
         
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	Reporter.log("STEP 2");
        	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        	String postTitle = createDefaultContent.Post("Published");
        	//TODO - add other content items as time allows
        	
        	Reporter.log("STEP 3");
        	taxonomy.NavigateSite("Content>>Files");
            overlay.SwitchToActiveFrame();
            Content content = new Content(webDriver, applib);
            content.ClickAddFileLnk();
            overlay.SwitchToActiveFrame();
            AddFile addFile = new AddFile(webDriver, applib);
            addFile.ClickAddFilesLnk();
            addFile.AddDefaultPicture();
            addFile.ClickStartUploadLnk();
            addFile.WaitForSuccessfulUpload();
            addFile.ClickNextBtn();
            overlay.SwitchToActiveFrame();
            EditImage editImage = new EditImage(webDriver, applib);
            editImage.ClickSaveBtn("1");
            overlay.SwitchToActiveFrame();
            ContentParent contentParent = new ContentParent(webDriver, applib);
            contentParent.VerifyMessageStatus("Image");
            contentParent.VerifyMessageStatus("has been updated.");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 4");
            taxonomy.NavigateSite("Content");
        	overlay.SwitchToActiveFrame();
        	SearchFor searchFor = new SearchFor(webDriver, applib);
        	searchFor.EnterTitle(postTitle);
        	searchFor.ClickApplyBtn();
        	overlay.switchToDefaultContent();
        	searchFor.VerifySearchThumbnailImgPresent(postTitle, "HanSolo");
        	
        	Reporter.log("STEP 5");
        	taxonomy.NavigateSite("Content>>Files>>Thumbnails");
        	Thumbnails thumbnails = new Thumbnails(webDriver);
        	thumbnails.VerifyThumbnailImagePresent("nbclogosmall", "1");
        	
        	Reporter.log("STEP 6");
        	taxonomy.NavigateSite("Content>>Add content>>Post");
            BasicInformation basicInformation = new BasicInformation(webDriver);
            String postTitle2 = random.GetCharacterString(15);
            basicInformation.EnterTitle(postTitle2);
            basicInformation.EnterSynopsis();
            overlay.switchToDefaultContent();
            basicInformation.ClickCoverSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SwitchToSelectFileFrm();
            selectFile.ClickPub7MPXVideoBtn();
    		selectFile.EnterTitle("Automation");
    		selectFile.ClickApplyBtn();
    		selectFile.WaitForFileSearchComplete();
    		selectFile.ClickMPXMediaThumbnailImage("1");
    		selectFile.ClickSubmitBtn();
            overlay.switchToDefaultContent();
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Post " + postTitle2 + " has been created.");
            
            //TODO - remaining steps are awaiting fix of current defect where video is present instead of video thumbnail
        	
    }
}