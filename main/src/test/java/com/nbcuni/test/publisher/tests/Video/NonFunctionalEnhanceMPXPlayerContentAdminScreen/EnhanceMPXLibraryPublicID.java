package com.nbcuni.test.publisher.tests.Video.NonFunctionalEnhanceMPXPlayerContentAdminScreen;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

import org.testng.annotations.Test;

import java.util.List;

import org.testng.Assert;

public class EnhanceMPXLibraryPublicID extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC2476
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/18505216115
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx"})
    public void EnhanceMPXLibraryPublicID_TC2476() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Setup
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
        	
        //Step 3 - NA as I will use an existing known mpx player ID for DB TV account
        String playerName = "AutomationPlayer1";
        String playerID = "VeXC0F2L9wg2";
        	
        if (configuredAccounts.contains("DB TV")) {
        	
        	//Setup
        	String initialFirstResult = searchFor.GetFirstMPXPlayerSearchResult();
        	int initialResultSize = searchFor.GetMPXSearchResultSize();
        		
        	//Step 4
        	searchFor.EnterTitle(playerName);
        	searchFor.ClickApplyBtn();
        	overlay.switchToDefaultContent(true);
        	searchFor.VerifyMPXResultPublicID(playerName, playerID);
        		
        	//Step 5
        	searchFor.ClickResetBtn();
        	searchFor.ClickSearchHeaderColumnLnk("Public ID");
        	Assert.assertTrue(searchFor.GetMPXSearchResultSize().equals(initialResultSize));
        		
        	//Step 6 and 7
        	searchFor.EnterPublicID(playerID);
        	searchFor.ClickApplyBtn();
        	Assert.assertTrue(searchFor.GetFirstMPXPlayerSearchResult().contains(playerName));
        		
        	//Step 8
        	searchFor.ClickResetBtn();
        	Assert.assertTrue(searchFor.GetFirstMPXPlayerSearchResult().equals(initialFirstResult));
        	Assert.assertTrue(searchFor.GetMPXSearchResultSize().equals(initialResultSize));
        		
        	}
        	else {
        		Assert.fail("DBTV account not configured for this test");
        	}
        	
    }
}
