package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.EnhanceContentLibraryTable;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.SearchFor;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;

public class EnhanceContentLibraryTable extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Pre-requisites:  - MPX Login and Import Accounts are set - Cron run succeeds , 
     * Step 2 - Log into QA instance as Admin,Login Succeeds 
     * Step 3 - Go to Content > Files  ,List of files loads 
     * Step 4 - Verify the existence of the below columns in the order mentioned below, moving left to right  - Title - Type - Size - Upload By - Upload Date - Operations ,The columns appear in the listed order 
     * Step 5 - Look for the default order in which the list is sorted ,The default sort order should be by Most Recent Date. 
     * Step 6 - Look for an MPX video in the list with a thumbnail.   NOTE: If no video with thumbnail exists, update any video in MPX and run cron.  ,The video's cover image should be listed as thumbnail. If no video thumbnail exists, it should be blank  NOTE: Verify that a video player, "We are sorry, no video available" is not seen 
     * Step 7 - Click on the column 'Title'  ,The list gets sorted by Title 
     * Step 8 - Click on the column 'Type' ,The list gets sorted by Type 
     * Step 9 - Click on the column Size ,The list gets sorted by size 
     * Step 10 - Click on Uploaded By  NOTE: If only admin or anonymous user exists, do the following: - create another user with role as Editor/Senior Editor - Upload Files as an Editor/Senior Editor  ,The list gets sorted by Uploaded By 
     * Step 11 - Click on Upload Date ,The list gets sorted by Upload Date 
     * Step 12 - Click on Upload Date again, the list is sorted by default results as seen in step 5
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full"})
    public void EnhanceContentLibraryTable_Test() throws Exception{
    	
    	//NOTE- Automated steps were re-ordered for ease of automation purposes
    	
    	//Step 1 and 2
    	UserLogin userLogin = applib.openApplication();
    	PageFactory.initElements(webDriver, userLogin);
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        Settings settings = new Settings(webDriver, applib);
        if (settings.IsMPXConfigured() == true) { 
        
        	//Step 3
        	overlay.switchToDefaultContent();
        	taxonomy.NavigateSite("Content>>Files");
        	overlay.SwitchToActiveFrame();
        	
        	//Step 4
        	SearchFor searchFor = new SearchFor(webDriver, applib);
        	PageFactory.initElements(webDriver, searchFor);
        	searchFor.VerifySearchHeaderColumnOrder();
        	
        	//Step 5 //TODO this step needs additional logic as time allows
        	
        	//Step 6
        	ContentParent contentParent = new ContentParent(webDriver, applib);
        	contentParent.VerifyPageContentNotPresent(Arrays.asList("We are sorry, no video available"));
        	
        	//Step 7
        	String defaultFirstSearchResult = searchFor.GetFirstSearchResult();
        	Integer resultSetSize = searchFor.GetSearchResultSize();
        	Assert.assertTrue(resultSetSize >= 1);
        	searchFor.ClickSearchHeaderColumnLnk("Title");
        	overlay.SwitchToActiveFrame();
        	Assert.assertTrue(searchFor.GetSearchResultSize() == resultSetSize);
        	
        	//Step 8
        	searchFor.ClickSearchHeaderColumnLnk("Type");
        	overlay.SwitchToActiveFrame();
        	Assert.assertTrue(searchFor.GetSearchResultSize() == resultSetSize);
        	
        	//Step 9
        	searchFor.ClickSearchHeaderColumnLnk("Size");
        	overlay.SwitchToActiveFrame();
        	Assert.assertTrue(searchFor.GetSearchResultSize() == resultSetSize);
        	
        	//Step 10
        	searchFor.ClickSearchHeaderColumnLnk("Uploaded By");
        	overlay.SwitchToActiveFrame();
        	Assert.assertTrue(searchFor.GetSearchResultSize() == resultSetSize);
        	
        	//Step 11
        	searchFor.ClickSearchHeaderColumnLnk("Upload date");
        	overlay.SwitchToActiveFrame();
        	Assert.assertTrue(searchFor.GetSearchResultSize() == resultSetSize);
        	
        	//Step 12
        	searchFor.ClickSearchHeaderColumnLnk("Upload date");
        	overlay.SwitchToActiveFrame();
        	Assert.assertTrue(searchFor.GetFirstSearchResult().equals(defaultFirstSearchResult));
        	Assert.assertTrue(searchFor.GetSearchResultSize() == resultSetSize);
        }
        else { 
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        }
       
    }
}
