package com.nbcuni.test.publisher.tests.Video.ScheduleMPXVideos;


import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.SearchFor;
import junit.framework.Assert;

import java.util.Arrays;
import java.util.List;


public class MPXVideosSchedulingVerificationInitialOverrides extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login Publisher 7 using Drupal 1 credentials.,Login succeeds.
     * Step 2 - In a new browser, access the URL, http://mpx.theplatform.com, and login to MPX using the following credentials:  Username: AdminPub7QA Password: Pa55word ,The user is taken to the main mpx page, where the "All Media" view is shown.
     * Step 3 - On the top of the page, populate the field, "Account" with "DB TV". ,The user populates the field successfully.
     * Step 4 - Click on the "Upload" button. ,The "Upload Media" overlay appears.
     * Step 5 - Click on the "Choose Files" button, and then double click on any video file located in the hard drive. ,The user is returned to the "Upload Media" overlay where the video is selected.
     ,6,Click on the "Upload" button. ,The user is returned to the "All Media" overlay, where the previously chosen video appears in the table, and is selected by default.
     ,7,Populate the following fields in the "Pub7 Media Panel - Default Fields" section located on the right.  Available date: <BLANK> Author: Pub7 Content Provider Airdate: <Today's date> Title: Test Video Canonical URL (a.k.a Related Link): TestLink Categories: Series, Show Content rating (US/Film Advisory Board): PD (Parental discretion) Content rating (US/MPAA): PG-13   AT, SL, V Content rating (US/V-CHIP): TV-Y7   D, FV, L, S, V Copyright: NBCUNI QA Credits: QA Analysts, QA Tester Description: This is a test for mpx overrides Expiration date: <BLANK> Geo-restriction: Prevent playback in certain countries Countries: India Keywords: mpx, metadata, field, override ,The fields are populated correctly.
     ,8,Click on the "Save" button. ,The mpx asset is saved successfully.
     ,9,Click on the "Files" tab. ,The user is taken to the "Files for 'Test Video'" view.
     ,10,Click on the "Public URLs" tab. ,The user is taken to the "Public URLs for 'Test Video'" view.
     ,12,Note down the value populated in the "URL" field.,The value populated in the "URL" field is noted down successfully.
     ,13,Click on the "All Media" tab. ,The user is returned to the "All Media" overlay.
     ,14,Note down the value populated in the following fields:  ID Reference ID ,The values are noted down successfully.
     ,15,Click on the "Publish" button. ,The mpx asset is published successfully, and the user sees a globe symbol in the last column of the "All Media" table corresponding to the mpx asset.
     * Step 16 - Return to the Publisher 7 browser window, and then click on Home --> Run cron. ,The user is taken to the "Status Report" overlay where they get a successful message that the cron run has been successful.
     * Step 17 - Click on Content --> Files --> mpxMedia.,The user is taken to the "Content" overlay, where the "mpxMedia" view is shown.
     * Step 18 - Click on the mpx asset that was published in Step 14.,The user is taken to the mpx asset's detail page.
     * Step 19 - Verify that the following metadata is present and contains the correct values.  MPX Released File Public IDs: <The last part of the "URL" value noted down in Step 12> MPX Media Default Released File Public ID: <The last part of the "URL" value noted down in Step 12> MPX Media Categories:  Series Show MPX Media Title: Test Video MPX Media ID: <The "ID" value noted down in Step 14> MPX Media GUID:  <The "Reference ID" value noted down in Step 14> MPX Media Description: This is a test for mpx overrides MPX Media Author:  Pub7 Content Provider MPX Media Air Date:  <Today's date> MPX Media Keywords:  mpx, metadata, field, override MPX Media Copyright:  NBC UNI QA MPX Media Related Link:  TestLink MPX Media Film Advisory Board Rating:  pd MPX Media MPAA Rating:  pg-13 MPX Media MPAA Sub-Ratings:  at sl v MPX Media V-Chip Rating:  tv-y7 MPX Media V-Chip Sub-Ratings:  d fv l s v MPX Media Exclude Selected Countries:  Prevent Playback in Selected Countries MPX Media Selected Countries: IN ,The metadata fields are populated correctly. The values have successfully made it from mpx to Publisher 7.
     * Step 20 - Verify that the following two fields do not appear on the mpx video detail page.  MPX Media Available Date MPX Media Expiration Date,The fields do not appear on the mpx detail page as expected.
     ,21,Click on the "Edit" tab.,The user is taken to the "Edit mpx video" overlay.
     ,22,Verify that the following fields appear on the page with no values populated in them, and are read only.  MPX Media Available Date MPX Media Expiration Date,The fields appear on the mpx edit view as expected, and do not have any values populated in them.
     ,23,Put a check on the following checkbox fields.  Override MPX Media Available Date Value Override MPX Media Expiration Date Value,The following fields become editable once the checkboxes are checked.  MPX MEDIA AVAILABLE DATE MPX MEDIA EXPIRATION DATE
     ,24,Populate the following fields with the corresponding values, and click on the "Save" button.  MPX MEDIA AVAILABLE DATE:  <Today's Date + 7 days> <12:00pm> MPX MEDIA EXPIRATION DATE: <Today's Date + 3 years> <12:00pm> ,The user is taken to the mpx asset's detail page where the changes are retained successfully.
     ,25,Verify that the following fields appear on the page containing the values:  MPX Media Available Date: <Today's date + 7 days> <12:00pm> MPX Media Expiration Date: <Today's Date + 3 years> <12:00pm> ,The fields appear on the mpx detail page as expected containing the overriden values.
     ,26,Click on Content --> Files --> mpxMedia.,The user is taken to the "Content" overlay, where the "mpxMedia" view is shown.
     ,27,Scroll down to the mpx asset that was just updated, and verify that the values underneath the following columns are updated as below:  SUNRISE: <Today's date + 7 days> <12:00pm> SUNSET: <Today's Date + 3 years> <12:00pm> ,The values underneath the columns, "SUNRISE" and "SUNSET" are updated with the values overriden in Step 24 successfully.
     ,28,Return to the thePlatform browser window, click on the mpx asset that was uploaded earlier, and populate the following fields with the corresponding values:  Available date: <Today's date + 3 days + 4 months> <12:00 AM> Expiration date: <Today's date + 3 days + 6 months - 1 year> <12:00 AM> ,The values are populated correctly.
     ,29,Click on the "Save" button.,The mpx metadata is updated as expected.
     ,30,Click on the button next to the "Delete" button, and click on "Publish Update".,The mpx asset is published again with the updates as expected.
     ,31,Return to the Publisher 7 browser window, and then click on Home --> Run cron.,The cron job is run successfully.
     ,32,Click on Content --> Files --> mpxMedia.,The user is taken to the "Content" overlay, where the "mpxMedia" view is shown.
     ,33,Scroll down to the mpx asset that was just updated, and verify that the values underneath the following columns still remain populated with the override values:  SUNRISE: <Today's date + 7 days> <12:00pm> SUNSET: <Today's Date + 3 years> <12:00pm> ,The values underneath the columns, "SUNRISE" and "SUNSET" are updated with the values overriden in Step 24 successfully.
     ,34,Click on this mpx media's "TITLE" value.,The user is taken to the mpx media detail page.
     ,35,Verify that the following mpx fields are still populated with the overriden values from Step 24 (and not from the values updated in thePlatform from Step 28).  MPX Media Available Date: <Today's date + 7 days> <12:00pm> MPX Media Expiration Date: <Today's Date + 3 years> <12:00pm> ,The values on the mpx detail page still retain the overridden values from Step 24 as expected.
     ,36,Click on the "Edit" tab.,The user is taken to the "Edit mpx video" overlay.
     ,37,Uncheck on the following checkbox fields.  Override MPX Media Available Date Value Override MPX Media Expiration Date Value ,The following fields become read-only, and are automatically populated with the mpx values from Step 28.  MPX MEDIA AVAILABLE DATE MPX MEDIA EXPIRATION DATE
     ,38,Click on the "Save" button.,The user is taken to the mpx asset's detail page where the changes showing the original mpx metadata values are retained successfully.
     ,39,Click on "Log out". ,The user logs out of Publisher 7.
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    //@Test(groups = {"full", "smoke"})
    public void MPXVideosSchedulingVerificationInitialOverrides() throws Exception{


    	//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 2 on requires prior MPX configuration
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        Settings settings = new Settings(webDriver);
        if (settings.IsMPXConfigured() == true) {
        	
        	//Step 2 through 15 will use an existing mpx published video due to flash based restriction
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            overlay.SwitchToActiveFrame();
            MPXMedia mpxMedia = new MPXMedia(webDriver);
            mpxMedia.ExpandMPXMedia();

            if (configuredAccounts.get(0).equals("DB TV")) {
                mpxMedia.SelectMPXPlayerForAccount1("Auditude Demo player");
            }
            else if (configuredAccounts.get(0).equals("")) {
                mpxMedia.SelectMPXPlayerForAccount1("");
            }
            else if (configuredAccounts.get(0).equals("")) {
                mpxMedia.SelectMPXPlayerForAccount1("");
            }
            mpxMedia.ClickSyncMPXMediaNowLnk();
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.VerifyMessageStatus("Processed video import/update manually for all accounts.");
            Cron cron = new Cron(webDriver);
            cron.ClickRunCronToCompleteImportLnk();
            overlay.SwitchToFrame("Cron");
            cron.ClickRunCronBtn();
            contentParent.VerifyMessageStatus("Processed video import/update via cron for all accounts.");
            contentParent.VerifyMessageStatus("Cron run successfully.");
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            overlay.SwitchToFrame("Content");
            SearchFor searchFor = new SearchFor(webDriver);
            searchFor.EnterTitle("Automation-NoAirDate");
            searchFor.ClickApplyBtn();
            searchFor.VerifySearchResultsPresent(Arrays.asList("Automation-NoAirDate"));

            searchFor.ClickSearchTitleLnk("Automation-NoAirDate");
            overlay.switchToDefaultContent();
            contentParent.VerifyPageContentPresent(Arrays.asList("Automation-NoAirDate"));

            //Step 20
            contentParent.VerifyPageContentNotPresent(Arrays.asList("MPX Media Available Date:", "MPX Media Expiration Date:"));

        }
        else {
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        	
        }

        Assert.fail("Test under construction");
    }
}
