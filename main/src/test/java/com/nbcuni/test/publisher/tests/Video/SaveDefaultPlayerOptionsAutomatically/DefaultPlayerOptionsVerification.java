package com.nbcuni.test.publisher.tests.Video.SaveDefaultPlayerOptionsAutomatically;

import java.util.List;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.FileTypes.FileTypes;
import com.nbcuni.test.publisher.pageobjects.FileTypes.ManageFileDisplay;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DefaultPlayerOptionsVerification extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to Publisher 7 using Drupal 1 credentials. ,The user logs in successfully. 
     * Step 2 - Click on "Configuration" --> "Media" --> "Media: thePlatform mpx settings". ,The user is taken to the "Media: thePlatform mpx settings" overlay. 
     * Step 3 - Click on the "MPX LOGIN" link. ,A new section expands where only one set of credential fields appear.  Username * Password * 
     * Step 4 - Populate the following fields, and click on the "Update" button.  Username *: mpx/AdminPub7QA Password *: Pa55word  Note --> If a user is already logged in, then this step can be skipped. ,The user logs in to MPX successfully. 
     * Step 5 - Scoll down to the "IMPORT ACCOUNT" section, and verify that only one drop down field appears, which is the following:  Select Account: * ,Only one drop down field appears as expected. 
     * Step 6 - Populate the following field with the corresponding value, and click on the "Set Import Account" button.  Select Account *: DB TV  Note --> If an account is already selected in this field, then this step can be skipped. ,The field becomes read only and the account is setup. 
     * Step 7 - Click on "Content" --> "Files" --> "mpxMedia". ,The user is taken to the "Files" overlay, with the "mpxMedia" view enabled. 
     * Step 8 - Click on the "SYNC MPXMEDIA" link. ,A new section appears where only one drop down field appears, "Import new mpxMedia with mpxPlayer: *" along with a button, "Sync mpxMedia Now". 
     * Step 9 - Select the value, "USA_Demo_Player" for the field, "Import new mpxMedia with mpxPlayer: *", and click on the "Sync mpxMedia Now" button.  Note --> If the "USA_Demo_Player" value is already selected, then this step can be skipped. ,The user gets a successful message stating how many mpx assets have been created, updated, deleted,or  unpublished. 
     * Step 10 - Click on "Home" --> "Run Cron". ,The cron job runs successfully, and the "Status Report" overlay appears. 
     * Step 11 - Click on "Content" --> "Files" --> "mpxMedia". ,The user is taken to the "Files" overlay with the "mpxMedia" view enabled. 
     * Step 12 - Verify that the player does not appear in the table containing the mpx assets. ,The video player does not appear as expected. 
     * Step 13 - Click on "Structure" --> "File Types". ,The user is taken to the "File Types" overlay. 
     * Step 14 - For "MPX Video", click on the "manage file display" link. ,The user is taken to the "MPX Video" overlay. 
     * Step 15 - Put a check on the "Pub MPX Video" checkbox, populate the field, "Pub MPX Video Player" with "USA_Demo_Player", and click on the "Save configuration" button. ,The user is returned to the "MPX Video" overlay where the user gets a message stating the settings are saved successfully. 
     * Step 16 - Click on "Content" --> "Files" --> "mpxMedia".,The user is taken to the "Files" overlay with the "mpxMedia" view enabled. 
     * Step 17 - Verify that the player appears on the extreme left column of all mpx assets appearing in the table. ,The player appears as expected. 
     * Step 18 - Click on any mpx asset. ,The user is taken to the mpx detail page. 
     * Step 19 - Verify that the player appears on the mpx detail page. ,The player appears as expected. 
     * Step 20 - Click on the "Play" icon inside the player. ,The video plays as expected in the mpx asset's detail page. 
     * Step 21 - Click on "Log out". ,The user logs out successfully. 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx"})
    public void DefaultPlayerOptionsVerification_Test() throws Exception {

    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //MPX Configuration required
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToActiveFrame();
        Settings settings = new Settings(webDriver, applib);
        if (settings.IsMPXConfigured() == true) {

        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();

        	//Step 2 through 12 not needed (executed in previous mpx tests)
        	if (configuredAccounts.get(0).equals("DB TV")) {
        		
        		//Step 13
        		overlay.ClickCloseOverlayLnk();
        		taxonomy.NavigateSite("Structure>>File types");
        		overlay.SwitchToActiveFrame();
        	
        		//Step 14
        		FileTypes fileTypes = new FileTypes(webDriver);
        		fileTypes.ClickManageFileDisplayLnk(configuredAccounts.get(0));
        		overlay.SwitchToActiveFrame();
        		
        		//Step 15
        		ManageFileDisplay manageFileDisplay = new ManageFileDisplay(webDriver, applib);
        		manageFileDisplay.ClickPubMPXVideoCbx();
        		manageFileDisplay.ClickPubMPXVideoLnk();
        		manageFileDisplay.SelectMPXVideoPlayer("AutomationPlayer2");
        		manageFileDisplay.ClickSaveConfigurationBtn();
        		contentParent.VerifyMessageStatus("Your settings have been saved.");
        		overlay.ClickCloseOverlayLnk();
        		
        		//Step 16
        		taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        		overlay.SwitchToActiveFrame();
        		
        		//Step 17 - N/A
        		
        		//Step 18
        		SearchFor searchFor = new SearchFor(webDriver, applib);
        		searchFor.EnterTitle("AutomationDefault");
        		searchFor.ClickApplyBtn();
        		searchFor.ClickSearchTitleLnk("AutomationDefault");
        		
        		//Step 19
        		WorkBench workBench = new WorkBench(webDriver, applib);
        		workBench.VerifyMPXPlayerPresent();
        		
        		//Test Case TC1812
        		workBench.VerifyMPXPlayerSourceNotPresent("embed");
        		
        		//Step 20 through 21 - N/A
        	}
        	else {
        		Assert.fail("DB TV account must be configured.");
        	}
        }
        else {
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        	
        }

    }
}
