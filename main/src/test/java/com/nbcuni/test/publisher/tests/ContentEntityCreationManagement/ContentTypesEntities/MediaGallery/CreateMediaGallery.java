package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.MediaGallery;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Edit;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CreateMediaGallery extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1042
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441339377
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx" })
    public void CreateMediaGallery_TC1042() throws Exception{
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        	
        	Reporter.log("SETUP");
        	Settings settings = new Settings(webWebWebDriver);
        	settings.ConfigureMPXIfNeeded();
        	navigation.Structure("Content types");
        	ContentTypes contentTypes = new ContentTypes(webWebWebDriver);
        	contentTypes.ClickManageFieldLnk("Media Gallery");
        	contentTypes.ClickEditLnk("Media Items");
        	Edit edit = new Edit(webWebWebDriver, applib);
        	edit.CheckAllowedRemoteMediaTypesCbx("MPX Video for Account \"DB TV\"");
        	edit.ClickSaveSettingsBtn();
        	contentParent.VerifyMessageStatus("Saved Media Items configuration.");
        	
            Reporter.log("STEP 2");
            navigation.AddContent("Media Gallery");
            
            Reporter.log("STEP 3");
            BasicInformation basicInformation = new BasicInformation(webWebWebDriver);
            String title = random.GetCharacterString(15);
            basicInformation.EnterTitle(title);
            basicInformation.EnterSynopsis();
            basicInformation.EnterShortDescription("short description");
            AddFile addFile = new AddFile(webWebWebDriver);
            MediaItems mediaItems = new MediaItems(webWebWebDriver);
            SelectFile selectFile = new SelectFile(webWebWebDriver);
            for(int Count=0;Count<2;Count++) {
            	webWebWebDriver.switchTo().defaultContent();
            	if (Count == 0) {
            		basicInformation.ClickMediaItemsSelectBtn();
            	}
            	else {
            		contentParent.WaitForThrobberNotPresent();
            		mediaItems.ClickAddBtn();
            	}
                selectFile.SwitchToSelectFileFrm();
                addFile.ClickAddFilesLnk();
                if (((RemoteWebDriver)webWebWebDriver).getCapabilities().getPlatform().toString() == "MAC") {
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
                	addFile.EnterPathToFile_Win(config.getConfigValueFilePath("PathToMediaContent"));
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
            }
            webWebWebDriver.switchTo().defaultContent();
            mediaItems.VerifyFileImagePresent("IPTCDefault", "1");
            mediaItems.VerifyFileImagePresent("nbclogosmall", "2");
            
            Reporter.log("STEP 4 and 5");
            mediaItems.ClickEditAllBtn();
            EditImage editImage = new EditImage(webWebWebDriver);
            editImage.WaitForEditImageFrameOpen();
            
            Reporter.log("STEP 6");
            String keywords = random.GetCharacterString(15);
            editImage.EnterKeywordsValue("1", keywords);
            editImage.ClickSaveBtn("2");
            editImage.WaitForEditImageFrameClose();
            
            Reporter.log("STEP 7");
            mediaItems.ClickEditAllBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyKeywordsValue("1", keywords);
            editImage.ClickSaveBtn("2");
            editImage.WaitForEditImageFrameClose();
            
            Reporter.log("STEP 8");
            mediaItems.ClickAddBtn();
            selectFile.SwitchToSelectFileFrm();
            selectFile.ClickPub7MPXVideoBtn();
    		selectFile.EnterTitle("AutomationWThumb");
    		selectFile.ClickApplyBtn();
    		contentParent.WaitForThrobberNotPresent();
    		selectFile.ClickMPXMediaThumbnailImage("nbclogosmall", "1");
    		contentParent.WaitForThrobberNotPresent();
    		Thread.sleep(2000); //TODO - dynamic wait
    		selectFile.ClickSubmitBtn();
    		Thread.sleep(2000); //TODO - dynamic wait
    		webWebWebDriver.switchTo().defaultContent();
            contentParent.WaitForThrobberNotPresent();
            
            Reporter.log("STEP 9");
            mediaItems.ClickEditAllBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyMPXObjectPresent();
            
            Reporter.log("STEP 10");
            editImage.ClickCloseWindowImg();
            //editImage.ClickCancelLnk();
            editImage.WaitForEditImageFrameClose();
            
            Reporter.log("OPTIONAL STEP");
            basicInformation.ClickCoverSelectBtn();
            selectFile.SelectDefaultCoverImg();
            basicInformation.VerifyCoverImagePresent("HanSolo");
            
            Reporter.log("STEP 11");
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
            
            Reporter.log("STEP 12");
            WorkBench workBench = new WorkBench(webWebWebDriver);
            workBench.ClickWorkBenchTab("Edit Draft");
            mediaItems.VerifyFileImagePresent("IPTCDefault", "1");
            mediaItems.VerifyFileImagePresent("nbclogosmall", "2");
            mediaItems.ClickAddBtn();
            selectFile.SwitchToSelectFileFrm();
            addFile.ClickAddFilesLnk();
            addFile.ClickTestPictureDefaultBtn();
            addFile.ClickOpenBtn();
            addFile.ClickStartUploadLnk();
            addFile.WaitForSuccessfulUpload();
            addFile.ClickNextBtn();
            webWebWebDriver.switchTo().defaultContent();
            mediaItems.VerifyFileImagePresent("IPTCDefault", "1");
            mediaItems.VerifyFileImagePresent("nbclogosmall", "2");
            mediaItems.VerifyFileImagePresent("nbclogosmall", "3");
            
            Reporter.log("STEP 13");
            mediaItems.ClickEditAllBtn();
            editImage.WaitForEditImageFrameOpen();
            String newImageTitle = random.GetCharacterString(10) + ".jpg";
            editImage.EnterTitleText("3", newImageTitle);
            editImage.ClickSaveBtn("2");
            editImage.WaitForEditImageFrameClose();
            mediaItems.ClickEditAllBtn();
            editImage.WaitForEditImageFrameOpen();
            editImage.VerifyTitleTextValue("3", newImageTitle);
            editImage.ClickCloseWindowImg();
            editImage.WaitForEditImageFrameClose();
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been updated.");
    }
}
