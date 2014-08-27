package com.nbcuni.test.publisher.tests.Video.NonFunctionalEnhanceMPXPlayerContentAdminScreen;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;
import java.util.List;
import org.testng.Assert;

public class EnhanceMPXPlayerContentAdminScreen extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to P7 using Admin (user 1) credentials,Login Successful 
     * Step 1a - Verify that mpx is already configured.
     * Step 2 - Navigate to Content -> Files-> Players Overlay. Verify the Columns 1. "ID"  2."Title"  3."Source"   4."Description"  5."MPX Status"  6. "First Imported" date with 12- hr format  7. "Last updated" with 12- hour format sorted with most recent to oldest date  is shown.  01-08-2014 | Mirza | The column order specified in this step should be the following: 1. ID 2. TITLE 3. DESCRIPTION 4. SOURCE 5. MPX STATUS 6. FIRST IMPORTED 7. LAST UPDATED ,Columns present as expected 
     * Step 3 - Verify that the filter list appears  1. Mpx Player Title  2. MPX Player Account  3. MPX Player Status with Apply and Reset Button are shown ,Filter List appears as expected. 
     * Step 4 - Verify the filter functionality of MPX Player Title by populating some test data eg. "test" and click "Apply" button,All Players with text "test" in it are shown in the mpxPlayers overlay .
     * Step 5 - Click on "Reset" button and verify that filters made in step 4 above is undone. ,"Reset" Button works as expected. 
     * Step 6a - Go to Configuration-> Media -> Media:thePlatform mpx settings -> In "IMPORT ACCOUNT" section note the acounts imported.  
     * Step 6b - Navigate to Content-> Files-> Player overlay and verify the MPX Player Account dropdown contain the accounts noted above.  
     * Step 6c - Select any MPX account -> Click Apply and verify the players corresponding to that account appears in the mpxPlayer view.,Players corresponding to that account appears as expected. 
     * Step 7 - Click on "Reset" button and verify that filters made in step 6 above is undone. ,"Reset" Button works as expected 
     * Step 8 - MPX Player Status dropdown shows Publish and Unpublish option. B. Select Publish option and click "Apply" button. Verify that only publish players are displayed.  C. Select Unpublish option and verify only unpublish players are displayed.       Note : Publish Players are all players which are not disabled in mpxthePlatform and unpublish option shows all players which are set as default player for one or more videos in publisher7 and are disabled in mpx thePlatform.,Publish and unpublish option works as expected. 
     * Step 9 - Click on "Reset" button and verify that filters made in step 8 above is undone. ,"Reset" Button works as expected 
     * Step 10 - Logout from P7 ,Logout successful.
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx"})
    public void EnhanceMPXPlayerContentAdminScreen_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 1a
    	Settings settings = new Settings(webDriver, applib);
    	settings.ConfigureMPXIfNeeded();
    	
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToActiveFrame();
        
        //Step 2
        	List<String> configuredAccounts = settings.GetImportAccountSelectedOptions();
        	overlay.ClickCloseOverlayLnk();
        	taxonomy.NavigateSite("Content>>Files>>mpxPlayers");
        	overlay.SwitchToActiveFrame();
        	SearchFor searchFor = new SearchFor(webDriver, applib);
        	searchFor.VerifyMPXSearchHeaderColumnOrder();
        	
        	//Step 3 - NA, redundant as step 2
        	
        	//Step 4
        	if (configuredAccounts.contains("DB TV")) {
        	
        		String initialFirstResult = searchFor.GetFirstMPXPlayerSearchResult();
        		int initialResultSize = searchFor.GetMPXSearchResultSize();
        		searchFor.EnterTitle("Automation");
        		searchFor.ClickApplyBtn();
        		Thread.sleep(1000); //TODO - add dynamic wait for search result validation
        		Assert.assertTrue(searchFor.GetMPXSearchResultSize() < initialResultSize);
        		Assert.assertTrue(searchFor.GetFirstMPXPlayerSearchResult().contains("AutomationPlayer"));
        		
        		//Step 5
        		searchFor.ClickResetBtn();
        		overlay.switchToDefaultContent(true);
        		Thread.sleep(1000); //TODO - add dynamic wait for search result validation
        		Assert.assertTrue(searchFor.GetFirstMPXPlayerSearchResult().equals(initialFirstResult));
        		Assert.assertTrue(searchFor.GetMPXSearchResultSize().equals(initialResultSize));
        		
        		//Step 6a - already executed first line of step 2
        		
        		//Step 6b
        		searchFor.VerifyMPXPlayerAccountOptions(configuredAccounts);
        		
        		//Step 6c
        		searchFor.SelectMPXPlayerAccount(configuredAccounts.get(0));
        		searchFor.ClickApplyBtn();
        		overlay.switchToDefaultContent(true);
        		searchFor.VerifyMPXResultSetSource(configuredAccounts.get(0));
        		
        		//Step 7
        		searchFor.ClickResetBtn();
        		
        		//Step 8
        		searchFor.SelectStatus("Published");
        		searchFor.ClickApplyBtn();
        		searchFor.VerifyMPXResultSetMPXStatus("Published");
        		searchFor.ClickResetBtn();
        		searchFor.SelectStatus("Unpublished");
        		searchFor.ClickApplyBtn();
        		searchFor.VerifyMPXResultSetMPXStatus("Unpublished");
        		
        		//Step 9
        		searchFor.ClickResetBtn();
        		Thread.sleep(1000); //TODO - add dynamic wait for search result validation
        		Assert.assertTrue(searchFor.GetFirstMPXPlayerSearchResult().equals(initialFirstResult));
        		Assert.assertTrue(searchFor.GetMPXSearchResultSize().equals(initialResultSize));
        		
        		//Step 10 - NA for automation purposes
        		
        	}
        	
    }
}
