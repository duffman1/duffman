package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.MediaGallery;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.AddFile;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.Content;
import com.nbcuni.test.publisher.pageobjects.Content.EditImage;
import com.nbcuni.test.publisher.pageobjects.Content.MediaItems;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Edit;

public class CreateMediaGallery extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1042
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441339377
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void CreateMediaGallery_TC1042() throws Exception{
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        	
        	Reporter.log("SETUP");
        	taxonomy.NavigateSite("Structure>>Content types>>Media Gallery>>Manage fields>>Media Items");
        	overlay.SwitchToActiveFrame();
        	Edit edit = new Edit(webDriver, applib);
        	edit.CheckAllowedRemoteMediaTypesCbx("MPX Video for Account \"DB TV\"");
        	edit.ClickSaveSettingsBtn();
        	contentParent.VerifyMessageStatus("Saved Media Items configuration.");
        	overlay.ClickCloseOverlayLnk();
        	
            Reporter.log("STEP 2");
            taxonomy.NavigateSite("Content>>Add content>>Media Gallery");
            overlay.SwitchToActiveFrame();
            
            Reporter.log("STEP 3");
            BasicInformation basicInformation = new BasicInformation(webDriver);
            String title = random.GetCharacterString(15);
            basicInformation.EnterTitle(title);
            basicInformation.EnterSynopsis();
            overlay.SwitchToActiveFrame();
            basicInformation.EnterShortDescription("short description");
            AddFile addFile = new AddFile(webDriver, applib);
            MediaItems mediaItems = new MediaItems(webDriver);
            SelectFile selectFile = new SelectFile(webDriver, applib);
            for(int Count=0;Count<2;Count++) {
            	if (Count == 0) {
            		basicInformation.ClickMediaItemsSelectBtn();
            	}
            	else {
            		mediaItems.WaitForImgLoadComplete();
            		mediaItems.ClickAddBtn();
            	}
                selectFile.SwitchToSelectFileFrm();
                addFile.ClickAddFilesLnk();
                if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
                	addFile.ClickPicturesUploadBtn();
                	if (Count == 0) {
                		addFile.ClickTestPictureIPTCBtn();
                	}
                	else {
                		addFile.ClickTestPictureDefaultBtn();
                	}
                	addFile.ClickOpenBtn();
                }
                else {
                	addFile.EnterPathToFile_Win(applib.getPathToMedia());
                	addFile.ClickGoBtn_Win();
                	if (Count == 0) {
                		addFile.EnterFileName_Win("IPTCDefault.jpg");
                	}
                	else {
                		addFile.EnterFileName_Win("nbclogosmall.jpg");
                	}
                	addFile.ClickOpenBtn();
                }
                addFile.ClickStartUploadLnk();
                addFile.WaitForSuccessfulUpload();
                addFile.ClickNextBtn();
                overlay.SwitchToActiveFrame();
            }
            mediaItems.VerifyFileImagePresent("IPTCDefault", "1");
            mediaItems.VerifyFileImagePresent("nbclogosmall", "2");
            
            Reporter.log("STEP 4 and 5");
            mediaItems.ClickEditAllBtn();
            EditImage editImage = new EditImage(webDriver, applib);
            editImage.WaitForEditImageFrameOpen();
            
            Reporter.log("STEP 6");
            String keywords = random.GetCharacterString(15);
            editImage.EnterKeywordsValue("1", keywords);
            editImage.ClickSaveBtn("2");
            editImage.WaitForEditImageFrameClose();
            overlay.SwitchToActiveFrame();
            
            Reporter.log("STEP 7");
            mediaItems.ClickEditAllBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyKeywordsValue("1", keywords);
            editImage.ClickSaveBtn("2");
            editImage.WaitForEditImageFrameClose();
            overlay.SwitchToActiveFrame();
            
            Reporter.log("STEP 8");
            mediaItems.ClickAddBtn();
            selectFile.SwitchToSelectFileFrm();
            selectFile.ClickPub7MPXVideoBtn();
    		selectFile.EnterTitle("AutomationWThumb");
    		selectFile.ClickApplyBtn();
    		selectFile.WaitForFileSearchComplete();
    		selectFile.ClickMPXMediaThumbnailImage("nbclogosmall", "1");
    		selectFile.ClickSubmitBtn();
            overlay.SwitchToActiveFrame();
            mediaItems.WaitForImgLoadComplete();
            
            Reporter.log("STEP 9");
            mediaItems.ClickEditAllBtn();
            editImage.WaitForEditImageFrameOpen();
            try {
            	editImage.VerifyMPXPlayerPresent();
            }
            catch (Exception | AssertionError e) {
            	editImage.VerifyMPXVideoLnkPresent("AutomationWThumb");
            }
            
            Reporter.log("STEP 10");
            editImage.ClickCancelLnk();
            editImage.WaitForEditImageFrameClose();
            overlay.SwitchToActiveFrame();
            
            Reporter.log("OPTIONAL STEP");
            basicInformation.ClickCoverSelectBtn();
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToActiveFrame();
            basicInformation.VerifyCoverImagePresent("HanSolo");
            
            Reporter.log("STEP 11");
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
            
            Reporter.log("STEP 12");
            taxonomy.NavigateSite("Structure>>Content types>>Media Gallery>>Manage fields>>Media Items");
            overlay.SwitchToActiveFrame();
            edit.EnterFileDirectory("test123");
            edit.ClickSaveSettingsBtn();
            contentParent.VerifyMessageStatus("Saved Media Items configuration");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 13");
            taxonomy.NavigateSite("Content");
            overlay.SwitchToActiveFrame();
            Content content = new Content(webDriver, applib);
            content.ClickEditMenuBtn(title);
            overlay.SwitchToActiveFrame();
            mediaItems.ClickAddBtn();
            selectFile.SwitchToSelectFileFrm();
            addFile.ClickAddFilesLnk();
            if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
            	addFile.ClickPicturesUploadBtn();
            	addFile.ClickTestPictureDefaultBtn();
            	addFile.ClickOpenBtn();
            }
            else {
            	addFile.EnterPathToFile_Win(applib.getPathToMedia());
            	addFile.ClickGoBtn_Win();
            	addFile.EnterFileName_Win("nbclogosmall.jpg");
            	addFile.ClickOpenBtn();
            }
            addFile.ClickStartUploadLnk();
            addFile.WaitForSuccessfulUpload();
            addFile.ClickNextBtn();
            overlay.SwitchToActiveFrame();
            mediaItems.WaitForImgLoadComplete();
            mediaItems.VerifyFileImagePresent("IPTCDefault", "2");
            mediaItems.VerifyFileImagePresent("nbclogosmall", "3");
            try {
            	mediaItems.VerifyFileVideoPresent("AutomationWThumb", "1");
            }
            catch (Exception | AssertionError e) {
            	mediaItems.VerifyFileVideoLnkPresent("AutomationWThumb", "1");
            }
            mediaItems.VerifyFileImagePresent("nbclogosmall", "4");
            
            Reporter.log("STEP 14");
            contentParent.ClickSaveBtn();
            overlay.SwitchToActiveFrame();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been updated.");


    }
}
