package com.nbcuni.test.publisher.tests.Video.CanonicalURLSupport;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ibm.icu.util.Calendar;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.FileTypes.FileTypes;
import com.nbcuni.test.publisher.pageobjects.FileTypes.MPXFileType;
import com.nbcuni.test.publisher.pageobjects.FileTypes.ManageFields;
import com.nbcuni.test.publisher.pageobjects.MPX.EditMPXVideo;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXPublishMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.AddFile;
import com.nbcuni.test.publisher.pageobjects.content.Content;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.SearchFor;

import junit.framework.Assert;

import org.openqa.selenium.support.PageFactory;
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
    @Test(groups = {"full", "mpx"})
    public void MPXCanonicalURLImportableURLAlias_Test() throws Exception{

    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	PageFactory.initElements(webDriver, userLogin);
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //MPX Configuration required
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        Settings settings = new Settings(webDriver, applib);
        if (settings.IsMPXConfigured() == true) {

        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();

        	//Step 2
        	if (configuredAccounts.get(0).equals("DB TV")) {
        		overlay.ClickCloseOverlayLnk();
        		overlay.switchToDefaultContent();
        		taxonomy.NavigateSite("Structure>>File types");
        		overlay.SwitchToActiveFrame();
        	
        		//Step 3
        		FileTypes fileTypes = new FileTypes(webDriver);
        		PageFactory.initElements(webDriver, fileTypes);
        		fileTypes.ClickManageFieldsLnk(configuredAccounts.get(0));
        		
        		//Step 4
        		overlay.SwitchToActiveFrame();
        		ContentParent contentParent = new ContentParent(webDriver, applib);
        		PageFactory.initElements(webDriver, contentParent);
        		ManageFields manageFields = new ManageFields(webDriver, applib);
        		PageFactory.initElements(webDriver, manageFields);
        		if (manageFields.FieldLabelExists("MPX Media Related Link") == false) {
        			
        			manageFields.EnterAddNewField("MPX Media Related Link");
        			
        			//Step 5
        			manageFields.SelectFieldType("Link");
        			manageFields.ClickSaveBtn(); 
        			contentParent.VerifyMessageStatus("Your settings have been saved.");
        		}
        		
        		//Step 6
    			overlay.ClickCloseOverlayLnk();
        		overlay.switchToDefaultContent();
        		taxonomy.NavigateSite("Structure>>File types");
        		overlay.SwitchToActiveFrame();
        		fileTypes.ClickEditFileTypeLnk("DB TV");
        		overlay.SwitchToActiveFrame();
        		
        		//Step 7
        		MPXFileType mpxFileType = new MPXFileType(webDriver);
        		PageFactory.initElements(webDriver, mpxFileType);
        		mpxFileType.SelectURLAliasField("MPX Media Related Link");
        		mpxFileType.ClickSaveBtn();
        		contentParent.VerifyMessageStatus("has been updated.");
        		
        		//Step 8 NOTE- step 8 creates a new video with a canonical url rather than using an existing video
        		MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
            	mpxLogin.OpenMPXThePlatform();
            	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
            	if (configuredAccounts.contains("DB TV")) {
                	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(webDriver, applib);
                	mpxSelectAccount.SelectAccount("DB TV");
            	}
            	MPXAddMedia mpxAddMedia = new MPXAddMedia(webDriver, applib);
            	AddFile addFile = new AddFile(webDriver, applib);
            	mpxAddMedia.ClickUploadBtn();
            	mpxAddMedia.ClickChooseFilesBtn();
            	if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
            		mpxAddMedia.ClickMoviesUploadBtn();
            		mpxAddMedia.ClickTestMovieBtn();
            		mpxAddMedia.ClickOpenBtn();
            	}
            	else {
            		addFile.EnterPathToFile_Win(applib.getPathToMedia());
                	addFile.ClickGoBtn_Win();
                	addFile.EnterFileName_Win("DefAutMed.m4v");
                	addFile.ClickOpenBtn();
            	}
                mpxAddMedia.ClickUploadFromDialogBtn();
                String mediaTitle = "Automation" + random.GetCharacterString(10);
                mpxAddMedia.GiveFocusToMediaItem();
                mpxAddMedia.EnterTitle(mediaTitle);
                String canonicalURL = "canonicalurl" + random.GetCharacterString(10);
                mpxAddMedia.EnterCanonicalURL(canonicalURL);
                mpxAddMedia.ClickSaveBtn();
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
        		
                //Step 9
                applib.openApplication();
                taxonomy.NavigateSite("Home>>Run cron");
        	    overlay.SwitchToActiveFrame();
        	    contentParent.VerifyMessageStatus("Cron ran successfully.");
        	    overlay.ClickCloseOverlayLnk();
        	    overlay.switchToDefaultContent();
        	    taxonomy.NavigateSite("Home>>Flush all caches");
        	    contentParent.VerifyMessageStatus("Every cache cleared.");
        	    
        	    //Step 10
        	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	    overlay.SwitchToActiveFrame();
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
