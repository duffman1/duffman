package com.nbcuni.test.publisher.tests.Video.ScheduleMPXVideos;

import com.ibm.icu.util.Calendar;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.MPX.EditMPXVideo;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXPublishMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.content.Workflow;
import com.nbcuni.test.publisher.pageobjects.queues.ScheduleQueue;
import junit.framework.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MPXVideosSchedulingVerificationScheduling extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login Publisher 7 using Drupal 1 credentials.,Login succeeds.
	 * Step 2 - In a new browser, access the URL, http://mpx.theplatform.com, and login to MPX using the following credentials:  Username: AdminPub7QA Password: Pa55word ,The user is taken to the main mpx page, where the "All Media" view is shown. 
     * Step 3 - On the top of the page, populate the field, "Account" with "DB TV". ,The user populates the field successfully.
	 * Step 4 - Click on the mpx asset that was used in the "2_mpx Videos Scheduling Verification_initial_overrides" test case, and populate the following fields with the corresponding values:  Available date: <Today's date  - 20 days> <12:00 AM> Expiration date: <Today's date - 7 days> <12:00 AM>,The values are populated correctly.
     * Step 5 - Click on the "Save" button. ,The mpx metadata is updated as expected.
     * Step 6 - Click on the button next to the "Delete" button, and click on "Publish Update". ,The mpx asset is published again with the updates as expected.
     * Step 7 - Return to the Publisher 7 browser window, and then click on Home --> Run cron. ,The cron job is run successfully.
     * Step 8 - Click on Content --> Files --> mpxMedia. ,The user is taken to the "Content" overlay, where the "mpxMedia" view is shown.
     * Step 9 - Scroll down to the mpx asset from Step 4, and verify that the values underneath the following columns are updated with the correct values:  SUNRISE: <Today's date  - 20 days> <12:00 AM> SUNSET: <Today's date  - 7 days> <12:00 AM> STATUS: Unpublished,The values underneath the columns, "SUNRISE", "SUNSET", and "STATUS" are updated with the new values from thePlatform successfully. 
     * Step 10 - Click on this mpx media's "TITLE" value. ,The user is taken to the mpx media detail page.
     * Step 11 - Verify that the following mpx fields are still populated with the updated values from Step 4 (the values updated in thePlatform).  MPX Media Available Date: <Today's date  - 20 days> <12:00 AM> MPX Media Expiration Date: <Today's date  - 7 days> <12:00 AM>,The values on the mpx detail page are updated with the values from Step 4 as expected.
     * Step 12 - Click on the "Edit" tab. ,The user is taken to the "Edit mpx video" overlay. 
     * Step 13 - Verify that the following mpx fields are still populated with the updated values from Step 4 (the values updated in thePlatform).  MPX Media Available Date: <Today's date  - 20 days> <12:00 AM> MPX Media Expiration Date: <Today's date  - 7 days> <12:00 AM>,The values on the mpx edit page are updated with the values from Step 4 as expected.
     * Step 14 - Click on the "SCHEDULE" tab, and verify that no revisions are scheduled.,No revisions are scheduled as expected.
	 * Step 15 - Return to the thePlatform browser window, click on the mpx asset that was used in the "2_mpx Videos Scheduling Verification_initial_overrides" test case, and populate the following fields with the corresponding values:  Available date: <BLANK> Expiration date: <BLANK>,The values are populated correctly.
     * Step 16 - Click on the "Save" button. ,The mpx metadata is updated as expected.
     * Step 17 - Click on the button next to the "Delete" button, and click on "Publish Update". ,The mpx asset is published again with the updates as expected.
     * Step 18 - Return to the Publisher 7 browser window, and then click on Home --> Run cron. ,The cron job is run successfully.
     * Step 19 - Click on Content --> Files --> mpxMedia. ,The user is taken to the "Content" overlay, where the "mpxMedia" view is shown. 
     * Step 20 - Scroll down to the mpx asset from Step 4, and verify that the values underneath the following columns are updated with the correct values from Step 15:  SUNRISE: <BLANK> SUNSET: <BLANK> STATUS: Published,The values underneath the columns, "SUNRISE", "SUNSET", and "STATUS" are updated with the new values from thePlatform successfully. 
     * Step 21 - Click on this mpx media's "TITLE" value. ,The user is taken to the mpx media detail page.
     * Step 22 - Verify that the following mpx fields are still populated with the updated values from Step 15 (the values updated in thePlatform).  MPX Media Available Date: <BLANK> MPX Media Expiration Date: <BLANK>,The values on the mpx detail page are updated with the values from Step 15 as expected.
     * Step 23 - Click on the "Edit" tab. ,The user is taken to the "Edit mpx video" overlay. 
     * Step 24 - Verify that the following mpx fields are still populated with the updated values from Step 15 (the values updated in thePlatform).  MPX Media Available Date: <BLANK> MPX Media Expiration Date: <BLANK> ,The values on the mpx edit page are updated with the values from Step 15 as expected. 
     * Step 25 - Click on the "SCHEDULE" tab, and verify that no revisions are scheduled.,No revisions are scheduled as expected.
 	 * Step 26 - Return to the thePlatform browser window, click on the mpx asset that was used in the "2_mpx Videos Scheduling Verification_initial_overrides" test case, and populate the following fields with the corresponding values:  Available date: <Today's date> Expiration date: <Today's date + 3 years>,The values are populated correctly.
     * Step 27 - Click on the "Save" button. ,The mpx metadata is updated as expected.
     * Step 28 - Click on the button next to the "Delete" button, and click on "Publish Update". ,The mpx asset is published again with the updates as expected.
     * Step 29 - Return to the Publisher 7 browser window, and then click on Home --> Run cron. ,The cron job is run successfully.
     * Step 30 - Click on Content --> Files --> mpxMedia. ,The user is taken to the "Content" overlay, where the "mpxMedia" view is shown. 
     * Step 31 - Scroll down to the mpx asset from Step 4, and verify that the values underneath the following columns are updated with the correct values from Step 26:  SUNRISE: <Today's date> SUNSET: <Today's date + 3 years> STATUS: Published,The values underneath the columns, "SUNRISE", "SUNSET", and "STATUS" are updated with the new values from thePlatform successfully. 
     * Step 32 - Click on this mpx media's "TITLE" value. ,The user is taken to the mpx media detail page.
     * Step 33 - Verify that the following mpx fields are still populated with the updated values from Step 26 (the values updated in thePlatform).  MPX Media Available Date: <Today's date> MPX Media Expiration Date: <Today's date + 3 years>,The values on the mpx detail page are updated with the values from Step 26 as expected.
     * Step 34 - Click on the "Edit" tab. ,The user is taken to the "Edit mpx video" overlay. 
     * Step 35 - Verify that the following mpx fields are still populated with the updated values from Step 26 (the values updated in thePlatform).  MPX Media Available Date: <Today's date> MPX Media Expiration Date: <Today's date + 3 years>,The values on the mpx edit page are updated with the values from Step 26 as expected.
     * Step 36 - Click on the "SCHEDULE" tab, and verify that the following revision is scheduled  REVISION: Revision <#>: Test Video - <Default Player> ACTION: Unpublish Date: <Today's date + 3 years> OPERATIONS: <Drop down field containing "Run now" and "Cancel">,The revision is scheduled as expected.
     * 37,Click on the "Log out" link.,The user logs out as expected.
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke", "mpx"})
    public void MPXVideosSchedulingVerificationScheduling_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	PageFactory.initElements(webDriver, userLogin);
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Note - test requires mpx configuration
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        Settings settings = new Settings(webDriver);
        if (settings.IsMPXConfigured() == true) {
		
            //Step 2
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
        	MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
        	mpxLogin.OpenMPXThePlatform();
        	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
        	
        	//Step 3
        	if (configuredAccounts.contains("DB TV")) {
        	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(webDriver, applib);
        	mpxSelectAccount.SelectAccount("DB TV");
        	
        	//Step 4 (test creates new mpx asset)
        	MPXAddMedia mpxAddMedia = new MPXAddMedia(webDriver, applib);
        	mpxAddMedia.ClickUploadBtn();
        	mpxAddMedia.ClickChooseFilesBtn();
        	mpxAddMedia.ClickMoviesUploadBtn();
        	mpxAddMedia.ClickTestMovieBtn();
        	mpxAddMedia.ClickOpenBtn();
        	mpxAddMedia.ClickUploadFromDialogBtn();
        	String mediaTitle = "Automation" + random.GetCharacterString(10);
        	mpxAddMedia.GiveFocusToMediaItem();
        	Calendar cal20DaysInPast = Calendar.getInstance();
        	cal20DaysInPast.add(Calendar.DATE, -20);
        	Date date20DaysInPast = cal20DaysInPast.getTime();
        	SimpleDateFormat mpxDateFormat = new SimpleDateFormat("M/dd/yyyy");
            String mpxDate20DaysInPast = mpxDateFormat.format(date20DaysInPast);
    	    mpxAddMedia.EnterAvailableDate(mpxDate20DaysInPast + " 12:00 AM");
        	mpxAddMedia.EnterTitle(mediaTitle);
        	Calendar cal7DaysInPast = Calendar.getInstance();
        	cal7DaysInPast.add(Calendar.DATE, -7);
        	Date date7DaysInPast = cal7DaysInPast.getTime();
        	String mpxDate7DaysInPast = mpxDateFormat.format(date7DaysInPast);
        	mpxAddMedia.EnterExpirationDate(mpxDate7DaysInPast + " 12:00 AM");
        	
        	//Step 5
        	mpxAddMedia.ClickSaveBtn();
        	
        	//Step 6
        	MPXPublishMedia mpxPublishMedia = new MPXPublishMedia(webDriver, applib);
        	mpxPublishMedia.ClickPublishBtn();
        	mpxPublishMedia.ClickPublishToPub7PrimaryCbx();
        	mpxPublishMedia.ClickPublishFromDialogBtn();
        	if (mpxPublishMedia.PublishSuccessful() == false) {
            	mpxPublishMedia.ClickOKBtn();
            	mpxPublishMedia.ClickPublishBtn();
                mpxPublishMedia.ClickPublishToPub7PrimaryCbx();
                mpxPublishMedia.ClickPublishFromDialogBtn();
            }
        	
        	//Step 7
        	applib.openApplication();
        	taxonomy.NavigateSite("Home>>Run cron");
    	    overlay.SwitchToActiveFrame();
    	    ContentParent contentParent = new ContentParent(webDriver);
    	    contentParent.VerifyMessageStatus("Cron ran successfully.");
    	    
    	    //Step 8
    	    overlay.ClickCloseOverlayLnk();
    	    overlay.switchToDefaultContent();
    	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	    overlay.SwitchToActiveFrame();
    	    
    	    //Step 9
    	    SearchFor searchFor = new SearchFor(webDriver, applib);
    	    PageFactory.initElements(webDriver, searchFor);
    	    searchFor.EnterTitle(mediaTitle);
    	    searchFor.ClickApplyBtn();
    	    overlay.switchToDefaultContent();
    	    int I = 0;
    	    while (!searchFor.GetFirstMPXMediaSearchResult().equals(mediaTitle)) {
    	    	I++; Thread.sleep(5000); //significant pause necessary as media ingestion can take a while from mpx
    	    	searchFor.ClickApplyBtn();
    	    	if (I >= 10) { break; }
    	    }
    	    searchFor.VerifySearchResultsPresent(Arrays.asList(mediaTitle));
    	    SimpleDateFormat pub7DateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	    String pub7Date20DaysInPast = pub7DateFormat.format(date20DaysInPast);
    	    String pub7Date7DaysInPast = pub7DateFormat.format(date7DaysInPast);
    	    contentParent.VerifyPageContentPresent(Arrays.asList(pub7Date20DaysInPast + " - 05:00 AM"
    	    		, pub7Date7DaysInPast + " - 05:00 AM", "Expired"));
    	    
    	    //Step 10
    	    searchFor.ClickSearchTitleLnk(mediaTitle);
    	    
    	    //Step 11
    	    SimpleDateFormat pub7WorkflowDateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
    	    String pub7WorkflowDate20DaysInPast = pub7WorkflowDateFormat.format(date20DaysInPast);
    	    String pub7WorkflowDate7DaysInPast = pub7WorkflowDateFormat.format(date7DaysInPast);
    	    contentParent.VerifyPageContentPresent(Arrays.asList(mediaTitle, pub7WorkflowDate20DaysInPast + " - 05:00",
    	    		pub7WorkflowDate7DaysInPast + " - 05:00"));
    	    
    	    //Step 12
    	    Workflow workFlow = new Workflow(webDriver);
    	    workFlow.ClickWorkflowTab("Edit");
    	    overlay.SwitchToActiveFrame();
    	    
    	    //Step 13
    	    EditMPXVideo editMPXVideo = new EditMPXVideo(webDriver);
    	    editMPXVideo.VerifyMPXAvailableDate(pub7Date20DaysInPast);
    	    editMPXVideo.VerifyMPXExpirationDate(pub7Date7DaysInPast);
    	    
    	    //Step 14
    	    ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
    	    scheduleQueue.ClickScheduleTab();
    	    overlay.SwitchToActiveFrame();
    	    scheduleQueue.VerifyScheduleTableisEmpty();
    	    
    	    //Step 15 through 25 - TODO automate as time allows but not an automation priority
    	    
    	    //Step 26
    	    mpxLogin.OpenMPXThePlatform();
    	    mpxAddMedia.GiveFocusToMediaItem();
    	    Calendar calToday = Calendar.getInstance();
        	Date dateToday = calToday.getTime();
        	String mpxDateToday = mpxDateFormat.format(dateToday);
    	    mpxAddMedia.EnterAvailableDate(mpxDateToday + " 12:00 AM");
        	Calendar cal1YearInFuture = Calendar.getInstance();
        	cal1YearInFuture.add(Calendar.DATE, 365);
        	Date date1YearInFuture = cal1YearInFuture.getTime();
        	String mpxDate1YearInFuture = mpxDateFormat.format(date1YearInFuture);
        	mpxAddMedia.EnterExpirationDate(mpxDate1YearInFuture + " 12:00 AM");
    	    
        	//Step 27
        	mpxAddMedia.ClickSaveBtn();
        	
        	//Step 28
        	mpxPublishMedia.ClickAdditionalOptionsArrow();
    	    mpxPublishMedia.ClickPublishUpdateLnk();
    	    mpxPublishMedia.ClickPublishToPub7PrimaryCbx();
    	    mpxPublishMedia.ClickUpdateBtn();
    	    
    	    //Step 29
    	    applib.openApplication();
        	taxonomy.NavigateSite("Home>>Run cron");
    	    overlay.SwitchToActiveFrame();
    	    contentParent.VerifyMessageStatus("Cron ran successfully.");
    	    
    	    //Step 30
    	    overlay.ClickCloseOverlayLnk();
    	    overlay.switchToDefaultContent();
    	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	    overlay.SwitchToActiveFrame();
    	    
    	    //Step 31
    	    searchFor.EnterTitle(mediaTitle);
    	    searchFor.ClickApplyBtn();
    	    overlay.switchToDefaultContent();
    	    searchFor.VerifySearchResultsPresent(Arrays.asList(mediaTitle));
    	    String pub7DateToday = pub7DateFormat.format(dateToday);
    	    String pub7Date1YearInFuture = pub7DateFormat.format(date1YearInFuture);
    	    contentParent.VerifyPageContentPresent(Arrays.asList(pub7DateToday + " - 05:00 AM"
    	    		, pub7Date1YearInFuture + " - 05:00 AM", "Published"));
    	    
    	    //Step 32
    	    searchFor.ClickSearchTitleLnk(mediaTitle);
    	    
    	    //Step 33
    	    String pub7WorkflowDateToday = pub7WorkflowDateFormat.format(dateToday);
    	    String pub7WorkflowDate1YearInFuture = pub7WorkflowDateFormat.format(date1YearInFuture);
    	    contentParent.VerifyPageContentPresent(Arrays.asList(mediaTitle, pub7WorkflowDateToday + " - 05:00",
    	    		pub7WorkflowDate1YearInFuture + " - 05:00"));
    	    
    	    //Step 34
    	    workFlow.ClickWorkflowTab("Edit");
    	    overlay.SwitchToActiveFrame();
    	    
    	    //Step 35
    	    editMPXVideo.VerifyMPXAvailableDate(pub7DateToday);
    	    editMPXVideo.VerifyMPXExpirationDate(pub7Date1YearInFuture);
    	    
    	    //Step 36
    	    scheduleQueue.ClickScheduleTab();
    	    overlay.SwitchToActiveFrame();
    	    scheduleQueue.VerifyRunNowBtn();
    	    contentParent.VerifyPageContentPresent(Arrays.asList(mediaTitle, 
    	    		"Unpublish", pub7Date1YearInFuture + " - 05:00"));
    	    
    	    //Step 37 - NA
    	    
        	}
        	else {
        		Assert.fail("DB TV account is not configured in MPX.");
        	}
        	
        }
        else {
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        	
        }
        
    }
}
