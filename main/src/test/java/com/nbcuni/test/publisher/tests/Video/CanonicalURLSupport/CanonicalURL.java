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
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CanonicalURL extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1080
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17442651858
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void CanonicalURL_TC1080() throws Exception {

    	Reporter.log("STEP 1");
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        Reporter.log("SETUP");
        Settings settings = new Settings(webDriver, applib);
    	settings.ConfigureMPXIfNeeded();
    	
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToActiveFrame();
        List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();

        Reporter.log("STEP 2");
        if (configuredAccounts.contains("DB TV")) {
        	overlay.ClickCloseOverlayLnk();
        	taxonomy.NavigateSite("Structure>>File types");
        	overlay.SwitchToActiveFrame();
        	
        	Reporter.log("STEP 3");
        	FileTypes fileTypes = new FileTypes(webDriver);
        	fileTypes.ClickManageFieldsLnk(configuredAccounts.get(0));
        	overlay.SwitchToActiveFrame();
        		
        	Reporter.log("STEP 4");
        	overlay.SwitchToActiveFrame();
        	ContentParent contentParent = new ContentParent(webDriver, applib);
        	ManageFields manageFields = new ManageFields(webDriver, applib);
        	if (manageFields.FieldLabelExists("MPX Media Related Link") == false) {
        			
        		manageFields.EnterAddNewField("MPX Media Related Link");
        			
        		Reporter.log("STEP 5");
        		manageFields.SelectFieldType("Link");
        		manageFields.ClickSaveBtn(); 
        		manageFields.ClickSaveFieldSettingsBtn();
        		contentParent.VerifyMessageStatus("Updated field MPX Media Related Link field settings.");
        	}
        		
        	Reporter.log("STEP 6");
    		overlay.ClickCloseOverlayLnk();
    		taxonomy.NavigateSite("Structure>>File types");
        	overlay.SwitchToActiveFrame();
        		
        	Reporter.log("STEP 7");
        	fileTypes.ClickEditFileTypeLnk(configuredAccounts.get(0));
        	overlay.SwitchToActiveFrame();
        	MPXFileType mpxFileType = new MPXFileType(webDriver);
            mpxFileType.SelectURLAliasField("MPX Media Related Link");
        	mpxFileType.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("has been updated.");
        		
        	Reporter.log("STEP 8 NOTE- step 8 creates a new video with a canonical url rather than using an existing video");
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
            mpxAddMedia.ClickSaveBtn(true);
            MPXPublishMedia mpxPublishMedia = new MPXPublishMedia(applib);
            mpxPublishMedia.PublishDefaultVideo();
        		
            Reporter.log("STEP 9");
            applib.openApplication();
            Cron cron = new Cron(webDriver, applib);
            cron.RunCron(true);
                
        	Reporter.log("STEP 10");
        	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	overlay.SwitchToActiveFrame();
        	SearchFor searchFor = new SearchFor(webDriver, applib);
        	searchFor.EnterTitle(mediaTitle);
        	searchFor.ClickApplyBtn();
        	overlay.switchToDefaultContent(true);
        	if (!searchFor.GetFirstMPXMediaSearchResult().equals(mediaTitle)) {
        	    //re-run cron as sometimes media assets aren't in the first ingested queue
        	    cron.RunCron(false);
            	taxonomy.NavigateSite("Content>>Files>>mpxMedia");
            	searchFor.EnterTitle(mediaTitle);
            	searchFor.ClickApplyBtn();
        	}
        	searchFor.ClickSearchTitleLnk(mediaTitle);
        	    
        	Reporter.log("STEP 11");
        	contentParent.VerifyPageContentPresent(Arrays.asList("MPX Media Related Link:", canonicalURL));
        	Assert.assertTrue(webDriver.getCurrentUrl().equals(applib.getApplicationURL() + "/" + canonicalURL));
        		
        }
        else {
        	Assert.fail("DB TV account must be configured.");
        }
        
    }
}
