package com.nbcuni.test.publisher.tests.Video.Thumbnails;

import java.util.List;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.AddFile;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.FileTypes.FileTypes;
import com.nbcuni.test.publisher.pageobjects.FileTypes.ManageFileDisplay;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXPublishMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ThumbnailsAreNotUpdated extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Prerequisites:  Configure the MPX account at /admin/config/media/theplatform for DBTV  Go to /admin/structure/file-types/manage/mpx_video/file-display. check Pub MPX Image and set the image style  Make sure to set the representative image of the content type being tested as Cover Media,
     * Step 2 - Log into the test instance as admin Note: This user story was initially deployed to http://qa1dev.publisher.nbcuni.com/user,Login Succeeds
     * Step 3 - On another tab, Upload a video in MPX at http://mpx.theplatform.com/ and Publish the video,Video gets published
     * Step 4 - Go to Content > Files > mpxMedia,mpxMedia page is displayed
     * Step 5 - Click Sync mpxMedia now,A confirmation message stating that the sync is complete
     * Step 6 - Run Cron ,All the drupal caches are cleared
     * Step 7 - Go to Content > Files > mpxMedia and look for the video uploaded in step2,The video should be displayed in the list of files.
     * Step 8 - Go back to http://mpx.theplatform.com/ and click the video uploaded in step2,The Preview of the selected video is seen to the right of the page
     * Step 9 - Click Files,Files related to the video are displayed
     * Step 10 - Click Upload and select an image,Uploaded image is displayed in the list of files
     * Step 11 - Click on the image to load the preview,  check This is the default content or thumbnail and save,All the options are saved.
     * Step 12 - Go back to All Media and Save,The video is now updated with the thumbnail image.
     * Step 13 - Go back to the Publisher instance. Run Cron. ,All the drupal caches are cleared
     * Step 14 - Go to Content > Files > mpxMedia and look for the video,A thumbnail should now be seen.
     * Step 15 - Go back to MPX and Choose another thumbnail image (A snapshot of the video or a new image file uploaded) for the same video,A new thumbnail image  has been set for the video.
     * Step 16 - Go back to the Publisher instance. Run Cron. ,All drupal caches are cleared.
     * Step 17 - Go to Content > Files > mpxMedia and look for the video,The updated thumbnail should be seen   Note: Browser cache can sometimes not allow to see the changes. Please verify on another browser or hard refresh to see the image change
     * Step 18 - Go to Content > Add Content > Movie,Movie Page is displayed
     * Step 19 - Click Cover Media. select the video uploaded and Save,The thumbnail of the video should be seen on the content creation page
     * Step 20 - Save the Movie ,Movie is created
     * Step 21 - Go to Content table and look for the Movie created ,A thumbnail image is displayed for the movie.  
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx", "certify"})
    public void ThumbnailsAreNotUpdated_Test() throws Exception {

    	//Step 1 and 2
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //MPX Configuration required
    	navigation.Configuration("Media: thePlatform mpx settings");
    	Settings settings = new Settings(webDriver);
    	
        List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();

        	if (configuredAccounts.contains("DB TV")) {
        		
        		navigation.Structure("File types");
        		
        		FileTypes fileTypes = new FileTypes(webDriver);
        		fileTypes.ClickManageFileDisplayLnk(configuredAccounts.get(0));
        		
        		ManageFileDisplay manageFileDisplay = new ManageFileDisplay(webDriver, applib);
        		manageFileDisplay.CheckPubMPXImageCbx();
        		manageFileDisplay.ClickPubMPXImageLnk();
        		manageFileDisplay.SelectMPXImageStyle("Thumbnail (100x100)");
        		manageFileDisplay.ClickSaveConfigurationBtn();
        		ContentParent contentParent = new ContentParent(webDriver);
        		contentParent.VerifyMessageStatus("Your settings have been saved.");
        		
        		//Step 3
        		MPXLogin mpxLogin = new MPXLogin(webDriver);
            	mpxLogin.OpenMPXThePlatform();
            	mpxLogin.Login(config.getConfigValueString("MPXUsername"), config.getConfigValueString("MPXPassword"));
            	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(webDriver);
                mpxSelectAccount.SelectAccount("DB TV");
            	MPXAddMedia mpxAddMedia = new MPXAddMedia(applib);
            	mpxAddMedia.UploadDefaultVideo();
                String mediaTitle = "AutomationWThumb" + random.GetCharacterString(10);
                mpxAddMedia.GiveFocusToMediaItem();
                mpxAddMedia.EnterTitle(mediaTitle);
                mpxAddMedia.ClickSaveBtn(true);
                mpxAddMedia.ClickFilesLnk();
                mpxAddMedia.ClickUploadBtn();
            	mpxAddMedia.ClickChooseFilesBtn();
            	AddFile addFile = new AddFile(webDriver);
            	if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
            		addFile.ClickPicturesUploadBtn();
                	addFile.ClickNBCLogoLnk();
            	}
            	else {
            		addFile.EnterPathToFile_Win(config.getConfigValueFilePath("PathToMediaContent"));
                	addFile.ClickGoBtn_Win();
                	addFile.EnterFileName_Win("nbclogosmall.jpg");
            	}
            	addFile.ClickOpenBtn();
            	mpxAddMedia.ClickUploadFromDialogBtn();
                mpxAddMedia.ClickAllMediaLnk();
                mpxAddMedia.ClickSaveBtn(false);
                MPXPublishMedia mpxPublishMedia = new MPXPublishMedia();
                mpxPublishMedia.PublishDefaultVideo();
                
                //Step 4 through 12 - N/A as step 3 creates a media item that has an image associated with it.
                
                //Step 13
                applib.openApplication();
                Cron cron = new Cron(webDriver);
                if (config.getConfigValueString("DrushIngestion").equals("false")) {
                	cron.RunCron();
                }
                
        		//Step 14
                navigation.Content("Files", "mpxMedia");
        	    SearchFor searchFor = new SearchFor(webDriver);
        	    searchFor.EnterTitle(mediaTitle);
        	    searchFor.ClickApplyBtn();
        	    if (!searchFor.GetFirstMPXMediaSearchResult().equals(mediaTitle)) {
            	    if (config.getConfigValueString("DrushIngestion").equals("true")) {
            	    	int refreshCount = 0;
            	    	while (!searchFor.GetFirstMPXMediaSearchResult().equals(mediaTitle)) {
                           	
                    		   applib.refreshPage();
                    		   refreshCount++;
                    		   if (refreshCount == 10) {
                    			   Assert.fail("Asset titled '" + mediaTitle + "' not ingested");
                    		   }
                    	   }
            		}
            	    else {
            	    	cron.RunCron();
            	    	navigation.Content("Files", "mpxMedia");
            	    }
                	searchFor.EnterTitle(mediaTitle);
                	searchFor.ClickApplyBtn();
            	}
        	    searchFor.VerifySearchThumbnailImgPresent(mediaTitle, "nbclogosmall");
        		
        		//Step 15 through 21 - TODO as time allows. This initial test is more about initial mpx thumbnail ingestion than thumbnail update.
        		
        	    //Cleanup
        	    navigation.Structure("File types");
        	    fileTypes.ClickManageFileDisplayLnk(configuredAccounts.get(0));
        		manageFileDisplay.UnCheckPubMPXImageCbx();
        		manageFileDisplay.ClickSaveConfigurationBtn();
        		contentParent.VerifyMessageStatus("Your settings have been saved.");
        	}
        	else {
        		Assert.fail("DB TV account must be configured.");
        	}
        
        
    }
}
