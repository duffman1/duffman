package com.nbcuni.test.publisher.tests.Video.CanonicalURLSupport;

import java.util.Arrays;
import java.util.List;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.FileTypes.FileTypes;
import com.nbcuni.test.publisher.pageobjects.FileTypes.MPXFileType;
import com.nbcuni.test.publisher.pageobjects.FileTypes.ManageFields;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXPublishMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MPXCanonicalURLImportableURLAlias extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to the test instance as Drupal User 1 (usually admin in Publisher test sites). ,Login succeeds. 
     * Step 2 - Go to Structure > File Types 
	 * Step 3 - Click "manage fields" link in the MPX Video row.
     * Step 4 - NOTE - if field labeled "MPX Media Related Link" exists, skip steps 4 and 5. Create a new text field. ,
	 * Step 5 - Under MPX Media Data Field Path, enter link -- this will draw data from the Related Link field in MPX. ,
     * Step 6 - Go back to the file types page. ,
	 * Step 7 - Click edit file type in the MPX Video row, and then select the "MPX Media Related Link".
     * Step 8 - Working In MPX, enter a value for any video in the Canonical URL (a.k.a Related Link) field, and click Save.,
     * Step 9 - Run cron, and then clear all caches in Drupal.
     * Step 10 - Go to the Content > FIles > mpxMedia page -- <http://<TestSiteName>/admin/content/file/mpxmedia>. ,The URL path in the browser address bar matches the value you entered in the Related Link field in MPX at Step 7.
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void MPXCanonicalURLImportableURLAlias_Test() throws Exception {

    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //MPX Configuration required
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToActiveFrame();
        Settings settings = new Settings(webDriver, applib);
        if (settings.IsMPXConfigured() == true) {

        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();

        	//Step 2
        	if (configuredAccounts.get(0).equals("DB TV")) {
        		overlay.ClickCloseOverlayLnk();
        		taxonomy.NavigateSite("Structure>>File types");
        		overlay.SwitchToActiveFrame();
        	
        		//Step 3
        		FileTypes fileTypes = new FileTypes(webDriver);
        		fileTypes.ClickManageFieldsLnk(configuredAccounts.get(0));
        		overlay.SwitchToActiveFrame();
        		
        		//Step 4
        		overlay.SwitchToActiveFrame();
        		ContentParent contentParent = new ContentParent(webDriver, applib);
        		ManageFields manageFields = new ManageFields(webDriver, applib);
        		if (manageFields.FieldLabelExists("MPX Media Related Link") == false) {
        			
        			manageFields.EnterAddNewField("MPX Media Related Link");
        			
        			//Step 5
        			manageFields.SelectFieldType("Link");
        			manageFields.ClickSaveBtn(); 
        			manageFields.ClickSaveFieldSettingsBtn();
        			contentParent.VerifyMessageStatus("Updated field MPX Media Related Link field settings.");
        		}
        		
        		//Step 6
    			overlay.ClickCloseOverlayLnk();
    			taxonomy.NavigateSite("Structure>>File types");
        		overlay.SwitchToActiveFrame();
        		
        		//Step 7
        		fileTypes.ClickEditFileTypeLnk(configuredAccounts.get(0));
        		overlay.SwitchToActiveFrame();
        		MPXFileType mpxFileType = new MPXFileType(webDriver);
        		mpxFileType.SelectURLAliasField("MPX Media Related Link");
        		mpxFileType.ClickSaveBtn();
        		contentParent.VerifyMessageStatus("has been updated.");
        		
        		//Step 8 NOTE- step 8 creates a new video with a canonical url rather than using an existing video
        		MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
            	mpxLogin.OpenMPXThePlatform();
            	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
            	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(webDriver, applib);
                mpxSelectAccount.SelectAccount("DB TV");
            	MPXAddMedia mpxAddMedia = new MPXAddMedia(applib);
            	mpxAddMedia.UploadDefaultVideo();
                String mediaTitle = "Automation" + random.GetCharacterString(10);
                mpxAddMedia.GiveFocusToMediaItem();
                mpxAddMedia.EnterTitle(mediaTitle);
                String canonicalURL = "canonicalurl" + random.GetCharacterString(10);
                mpxAddMedia.EnterCanonicalURL(canonicalURL);
                mpxAddMedia.ClickSaveBtn();
                MPXPublishMedia mpxPublishMedia = new MPXPublishMedia(applib);
                mpxPublishMedia.PublishDefaultVideo();
        		
                //Step 9
                applib.openApplication();
                Cron cron = new Cron(webDriver, applib);
                cron.RunCron(true);
                
        	    //Step 10
        	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	    overlay.SwitchToActiveFrame();
        	    SearchFor searchFor = new SearchFor(webDriver, applib);
        	    searchFor.EnterTitle(mediaTitle);
        	    searchFor.ClickApplyBtn();
        	    overlay.switchToDefaultContent();
        	    if (!searchFor.GetFirstMPXMediaSearchResult().equals(mediaTitle)) {
        	    	//re-run cron as sometimes media assets aren't in the first ingested queue
        	    	cron.RunCron(false);
            	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            	    searchFor.EnterTitle(mediaTitle);
            	    searchFor.ClickApplyBtn();
        	    }
        	    searchFor.ClickSearchTitleLnk(mediaTitle);
        	    
        	    //Step 11
        	    contentParent.VerifyPageContentPresent(Arrays.asList("MPX Media Related Link:", canonicalURL));
        	    Assert.assertTrue(webDriver.getCurrentUrl().equals(applib.getApplicationURL() + "/" + canonicalURL));
        		
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
