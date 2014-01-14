package com.nbcuni.test.publisher.tests.Video.Player;


import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.Content;
import com.nbcuni.test.publisher.pageobjects.content.SearchFor;
import junit.framework.Assert;
import org.testng.annotations.Test;


public class SelectVideoPlayerPerVideoFile extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into the test instance as Drupal User 1 (usually admin in Publisher test sites).  Note: Testable code for this User Story was initially deployed to http://qa3dev.publisher.nbcuni.com/user.,Login succeeds.
     * Step 2 - Click on "Content" --> "Files",The user is taken to the "Content" view, where the "FILES" tab is enabled.
     * Step 3 - Verify that there are MPX videos in the table.,MPX videos are present in the table as expected.
     ,4,For an MPX video, click on "Edit" link (located under the "OPERATIONS" column).,The user is taken to the "Edit mpx_video" view.
     ,5,Scroll down to the bottom, and verify that the dropdown field, "Pub MPX Video Player" exists.,The fields exists as expected.
     ,6,Verify that there are other MPX Player values in the "Pub MPX Video Player" dropdown field.,There are other MPX Players as expected.
     ,7,Select the value, " --- Default Player ---- " in the "Pub MPX Video Player" drop down field, and click "Save".,The user is taken back to the "FILES" page.
     ,8,Verify that for the particular MPX video selected in Step 3, the MPX Player appears under the second column.    Note: The user can see a "Play" icon in the MPX Player box.,The player appears as expected for the MPX Video.
     ,9,Click on the "Home" icon located on the top.,The user is taken to the Drupal home page.
     ,10,Click on "Content" --> "Files",The user is taken to the "Content" view, where the "FILES" tab is enabled.
     ,11,For the same MPX video the user made changes to above, verify that the same player appears under the second column.,The player appears as expected for the MPX Video.
     ,12,For the same MPX video, click on "Edit" link (located under the "OPERATIONS" column).,The user is taken to the "Edit mpx_video" view.
     ,13,Scroll down to the dropdown field, "Pub MPX Video Player", and verify that "---Default Player---" is still populated.,The correct value is populated as expected.
     ,14,Select any other value in the "Pub MPX video Player" field, and click "Save".,The user is taken back to the "FILES" page.
     ,15,Verify that for the particular MPX video selected in Step 3, a new MPX Player appears under the second column.    Note: The user can see a "Play" icon in the MPX Player box.,The new player appears as expected for the MPX Video.
     ,16,Click on the "Home" icon located on the top.,The user is taken to the Drupal home page.
     ,17,Click on "Content" --> "Files",The user is taken to the "Content" view, where the "FILES" tab is enabled.
     ,18,For the same MPX video the user made changes to above, verify that the same player appears under the second column.,The player appears as expected for the MPX Video.
     ,19,Close all the browsers.,The browsers are closed.
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full"})
    public void SelectVideoPlayerPerVideoFile () throws Exception{


    	//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 2 on requires prior MPX configuration
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        Settings settings = new Settings(webDriver);
        if (settings.IsMPXConfigured() == true) {

            //Step 2
            taxonomy.NavigateSite("Content>>Files>>mpxMedia");

            //Step 3
            SearchFor searchFor = new SearchFor(webDriver);
            searchFor.EnterTitle("Automation1");
            searchFor.ClickApplyBtn();

            //Step 4
            Content content = new Content(webDriver);
            content.ClickEditExtendMenuBtn("Automation1");

        }
        else {
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        	
        }

        Assert.fail("Test under construction");

    }
}
