package com.nbcuni.test.publisher.tests.Video.SyncFromMPXButtonToVideoFileEntitiesEditForm;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;

import org.testng.annotations.Test;

public class SyncFromMPXButtonToVideoFileEntitiesEditForm extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to P7 as Admin (user 1) In a separate browser window login to Mpx (http://mpx.theplatform.com/) as Adminpub7QA ,User is logged in successfully 
     * Step 1a - Create a new mpx asset
     * Step 2 - Navigate to Configuration --> Media --> MPX The Platform, Verify MPX configuration is complete  and correct. ,MPX is configured correctly 
     * Step 3 - Run Cron ,Cron is run successfully 
     * Step 4 - Navigate to Content --> Files --> MPX Media select a video that has "Published" status.  Click on "Edit" and view the MPX Metadata.   Take note of the name and then go to the browser instance that contains MPX and search for the video.  In P7, verify metadata for this asset matches what is displayed in MPX. ,Metadata matches 
     * Step 5 - In P7 navigate to Publishing options and verify "Published" check mark is "checked" and not 'editable.'    Verify the text of the message "( this setting can only be changed by publishing/unpublishing the video in MPX and then clicking the Update MPX Data button or waiting for cron to run )" is displayed. ,Check box is checked and not editable 
     * Step 6 - In MPX make an update to a Core Metadata Field such as Title (Title in MPX is = MPX Media Title in P7) & 'Publish' updates from MPX.    Return to P7 on the Edit page for the asset under test and click on "Sync from MPX" ,Sync is run and Core fields are updated correctly 
     * Step 7 - Repeat step 6 for the remaining Core fields in MPX, verify that each Field is updated accordingly and accurately. *NOTE* We do not need to test all core fields for this update at this time, this testing can be added when this TC is automated.  For now, only _KEY_ fields must be tested. ,Sync is run and Core fields are updated correctly 
     * Step 8 - In MPX make an update to a Custom Field such as Title  'Publish' updates from MPX.    Return to P7 on the Edit page for the asset under test and click on "Sync from MPX" *NOTE* We do not need to test all types core fields for this update at this time, this testing can be added when this TC is automated.  For now, only _KEY_ fields must be tested. ,Sync is run and Custom fields are updated correctly 
     * Step 9 - Repeat step 8 for the remaining Core field types (date, boolean, etc.) in MPX, verify that each Field is updated accordingly and accurately. *NOTE* We do not need to test all custom field types for this update at this time, this testing can be added when this TC is automated.  For now, only _KEY_ fields must be tested. ,Sync is run and Custom fields are updated correctly 
     * Step 10 - In MPX change the available date, expiration date and air date for the asset under test asset so that they are all in the past (sunset scenario)  Pubish updates  In P7 click on "Sync from MPX  button" for this asset and refresh the page.  Verify dates are updated.    Navigate to Content --> Files --> MPX Media and locate the asset under test, verify this asset now shows as unpublished. ,Sync is run and video now shows as unpublished. 
     * Step 11 - In P7 navigate to Publishing options and verify "Published" check mark is _NOT_ "checked" and _NOT_ not 'editable.'    Verify the text of the message "( this setting can only be changed by publishing/unpublishing the video in MPX and then clicking the Update MPX Data button or waiting for cron to run )" is displayed. ,Check box is _NOT_ checked and _NOT_  editable 
     * Step 12 - In MPX change the available date, expiration date and air date for the asset under test asset so that they are all in the future  Publish updates  In P7 click on "Sync from MPX  button" for this asset and refresh the page.  Verify dates are updated.  Navigate to Content --> Files --> MPX Media and locate the asset under test, verify this asset now shows as "Published" ,Sync is run and video shows as published. 
     * Step 13 - In MPX,  select the asset under test and "Delete" the asset.    In P7, click on "Sync from MPX  button" for this asset and refresh the page.   ,Sync is run 
     * Step 14 - Navigate to Content --> files --> Mpx Media and locate the asset under test. ,Asset now shows as unpublished  
     * Step 15 - In MPX upload a new asset and populate Available date and Expiration date in the future.  Publish the asset.  In P7, Run Cron and note the asset has been ingested  In MPX, change the Available and expiration date in the future, publish updates  In P7, navigate to the edit page for the asset under test and click on "Sync from MPX"  Return to Content --> Files --> MPX Media and note the file is listed as unpublished  In MPX, change the Available date to a past date and expiration date to a future date.  Publish updates  In P7,  navigate to the edit page for the asset under test and click on "Sync from MPX"  Return to Content --> Files --> MPX Media and note the file is listed as published,The 'Sync from MPX' will update publish/unpublish status correctly as dates are updated and shifted.    *NOTE* This step was added to ensure adequate documentation of the state change testing was captured. 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx"})
    public void SyncFromMPXButtonToVideoFileEntitiesEditForm_Test() throws Exception{
    	
    	/*TODO - Functionality removed from iteration 39 but will be added back in a future iteration. When complete, uncomment this test
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
    	//Step 1a
    	MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
    	mpxLogin.OpenMPXThePlatform();
    	mpxLogin.Login("mpx/AdminPub7QA", "Pa55word");
    	MPXAssets mpxAssets = new MPXAssets(webDriver, applib);
    	MPXAddMedia mpxAddMedia = new MPXAddMedia(webDriver, applib);
    	mpxAddMedia.ClickUploadBtn();
    	mpxAddMedia.ClickChooseFilesBtn();
    	mpxAddMedia.ClickMoviesUploadBtn();
    	mpxAddMedia.ClickTestMovieBtn();
    	mpxAddMedia.ClickOpenBtn();
    	mpxAddMedia.ClickUploadFromDialogBtn();
    	String mediaTitle = "Automation" + random.GetCharacterString(10);
    	mpxAddMedia.GiveFocusToMediaItem();
    	mpxAddMedia.EnterAuthor("Pub7 Content Provider");
    	mpxAddMedia.EnterTitle(mediaTitle);
    	mpxAddMedia.EnterCategories("Series, Show");
    	mpxAddMedia.ClickSaveBtn();
    	MPXPublishMedia mpxPublishMedia = new MPXPublishMedia(webDriver, applib);
    	mpxPublishMedia.ClickPublishBtn();
    	mpxPublishMedia.ClickPublishToAllCbx();
    	mpxPublishMedia.ClickPublishFromDialogBtn();
    	
    	//Step 2
    	applib.openApplication();
    	taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        Settings settings = new Settings(webDriver);
        if (settings.IsMPXConfigured() == true) {
            
        	//Step 3
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
    	    overlay.switchToDefaultContent(true);
    	    taxonomy.NavigateSite("Home>>Run cron");
    	    overlay.SwitchToActiveFrame();
    	    ContentParent contentParent = new ContentParent(webDriver);
    	    contentParent.VerifyMessageStatus("Cron ran successfully.");
    	    
    	    //Step 4 (note that we are using the media asset created in step 1a above)
    	    overlay.switchToDefaultContent(true);
    	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	    overlay.SwitchToActiveFrame();
    	    SearchFor searchFor = new SearchFor(webDriver);
    	    searchFor.EnterTitle(mediaTitle);
    	    searchFor.ClickApplyBtn();
    	    searchFor.ClickSearchTitleLnk(mediaTitle);
    	    Workflow workflow = new Workflow(webDriver);
    	    workflow.ClickWorkflowTab("Edit");
    	    overlay.SwitchToActiveFrame();
    	    EditMPXVideo editMPXVideo = new EditMPXVideo(webDriver);
    	    editMPXVideo.VerifyMPXMediaCategory1("Series");
    	    editMPXVideo.VerifyMPXMediaCategory2("Show");
    	    editMPXVideo.VerifyMPXMediaTitle(mediaTitle);
    	    editMPXVideo.VerifyMPXMediaAuthor("Pub7 Content Provider");
    	    
    	    //Step 5
    	    PublishingOptions publishingOptions = new PublishingOptions(webDriver);
    	    publishingOptions.ClickPublishingOptionsLnk();
    	    publishingOptions.VerifyPublishedCbxChecked();
    	    
    	    //Step 6 and 7
    	    mpxLogin.OpenMPXThePlatform();
    	    String newMediaTitle = "Automation" + random.GetCharacterString(10);
    	    mpxAddMedia.GiveFocusToMediaItem();
    	    mpxAddMedia.EnterTitle(newMediaTitle);
    	    mpxAddMedia.EnterCategories("Series, Topic");
    	    Calendar cal = Calendar.getInstance();
        	cal.add(Calendar.DATE, 1);
        	Date date = cal.getTime();
        	SimpleDateFormat sdfDate = new SimpleDateFormat("M/dd/yyyy");
            String sDate = sdfDate.format(date);
    	    mpxAddMedia.EnterExpirationDate(sDate + " 12:00 AM");
    	    mpxAddMedia.ClickSaveBtn();
    	    mpxPublishMedia.ClickAdditionalOptionsArrow();
    	    mpxPublishMedia.ClickPublishUpdateLnk();
    	    mpxPublishMedia.ClickPublishToAllCbx();
    	    mpxPublishMedia.ClickUpdateBtn();
    	    applib.openApplication();
    	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	    overlay.SwitchToActiveFrame();
    	    searchFor.EnterTitle(mediaTitle);
    	    searchFor.ClickApplyBtn();
    	    searchFor.ClickSearchTitleLnk(mediaTitle);
    	    workflow.ClickWorkflowTab("Edit");
    	    overlay.SwitchToActiveFrame();
    	    editMPXVideo.ClickSyncFromMPXBtn();
    	    overlay.SwitchToActiveFrame();
    	    editMPXVideo.VerifyMPXMediaTitle(newMediaTitle);
    	    editMPXVideo.VerifyMPXMediaCategory1("Series");
    	    editMPXVideo.VerifyMPXMediaCategory2("Topic");
    	    SimpleDateFormat sdfDate2 = new SimpleDateFormat("MM/dd/yyyy");
            String sDate2 = sdfDate2.format(date);
    	    editMPXVideo.VerifyMPXExpirationDate(sDate2);
    	    editMPXVideo.VerifyMPXExpirationTime("05:00am");
    	    
    	    //Step 8 (unable to automate as custom fields aren't displayed on pub7. Following up with Pete)
    	    
    	    //Step 9 and 10 
    	    mpxLogin.OpenMPXThePlatform();
    	    mpxAddMedia.GiveFocusToMediaItem();
    	    Calendar cal2 = Calendar.getInstance();
        	cal.add(Calendar.DATE, -1);
        	Date date3 = cal2.getTime();
        	String sDate3 = sdfDate.format(date3);
        	mpxAddMedia.EnterAvailableDate(sDate3 + " 12:00 AM");
        	mpxAddMedia.EnterAirDate(sDate3 + " 12:00 AM");
    	    mpxAddMedia.SelectContentRatingUSMPAA_PG13();
    	    mpxAddMedia.EnterContentRatingUSMPAASubRating("AT");
    	    mpxAddMedia.EnterExpirationDate(sDate3 + " 12:00 AM");
    	    mpxAddMedia.ClickSaveBtn();
        	mpxPublishMedia.ClickAdditionalOptionsArrow();
    	    mpxPublishMedia.ClickPublishUpdateLnk();
    	    mpxPublishMedia.ClickPublishToAllCbx();
    	    mpxPublishMedia.ClickUpdateBtn();
    	    applib.openApplication();
    	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	    overlay.SwitchToActiveFrame();
    	    searchFor.EnterTitle(newMediaTitle);
    	    searchFor.ClickApplyBtn();
    	    searchFor.ClickSearchTitleLnk(newMediaTitle);
    	    workflow.ClickWorkflowTab("Edit");
    	    overlay.SwitchToActiveFrame();
    	    editMPXVideo.ClickSyncFromMPXBtn();
    	    overlay.SwitchToActiveFrame();
    	    editMPXVideo.ClickSyncFromMPXBtn();
    	    overlay.SwitchToActiveFrame();
    	    editMPXVideo.VerifyMPXMPAARating("pg-13");
    	    editMPXVideo.VerifyMPXMPAASubRating1("at");
    	    SimpleDateFormat sdfDate3 = new SimpleDateFormat("MM/dd/yyyy");
            String sDate4 = sdfDate3.format(date3);
            editMPXVideo.VerifyMPXAirDate(sDate4);
    	    editMPXVideo.VerifyMPXAirTime("05:00am");
    	    editMPXVideo.VerifyMPXAvailableDate(sDate4);
    	    editMPXVideo.VerifyMPXAvailableTime("05:00am");
    	    editMPXVideo.VerifyMPXExpirationDate(sDate4);
    	    editMPXVideo.VerifyMPXExpirationTime("05:00am");
    	    
    	    //Step 11
    	    publishingOptions.ClickPublishingOptionsLnk();
    	    publishingOptions.VerifyPublishedCbxNotCheckedAndNotEditable();
    	    contentParent.VerifyPageContentPresent(Arrays.asList("this setting can only be changed by publishing/unpublishing the video in MPX and then clicking the Update MPX Data button or waiting for cron to run"));
    	    
    	    //Step 12
    	    mpxLogin.OpenMPXThePlatform();
    	    mpxAddMedia.GiveFocusToMediaItem();
    	    mpxAddMedia.EnterAvailableDate(sDate + " 12:00 AM");
        	mpxAddMedia.EnterAirDate(sDate + " 12:00 AM");
        	mpxAddMedia.EnterExpirationDate(sDate + " 12:00 AM");
        	mpxAddMedia.ClickSaveBtn();
    	    mpxPublishMedia.ClickAdditionalOptionsArrow();
    	    mpxPublishMedia.ClickPublishUpdateLnk();
    	    mpxPublishMedia.ClickPublishToAllCbx();
    	    mpxPublishMedia.ClickUpdateBtn();
    	    applib.openApplication();
    	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	    overlay.SwitchToActiveFrame();
    	    searchFor.EnterTitle(newMediaTitle);
    	    searchFor.ClickApplyBtn();
    	    contentParent.VerifyPageContentPresent(Arrays.asList("Expired"));
    	    searchFor.ClickSearchTitleLnk(newMediaTitle);
    	    workflow.ClickWorkflowTab("Edit");
    	    overlay.SwitchToActiveFrame();
    	    editMPXVideo.ClickSyncFromMPXBtn();
    	    overlay.SwitchToActiveFrame();
    	    editMPXVideo.VerifyMPXAirDate(sDate2);
    	    editMPXVideo.VerifyMPXAirTime("05:00am");
    	    editMPXVideo.VerifyMPXAvailableDate(sDate2);
    	    editMPXVideo.VerifyMPXAvailableTime("05:00am");
    	    editMPXVideo.VerifyMPXExpirationDate(sDate2);
    	    editMPXVideo.VerifyMPXExpirationTime("05:00am");
        	
    	    //Step 13 through 15
    	    //TODO - test steps are the deletion of the asset which are handled in other tests. add these as time allows
        }
        else {
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        }
        */
        
    }
}
