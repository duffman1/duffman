package com.nbcuni.test.publisher.tests.Video.DeIngestingMPXAccountsAndCorrespondingAssets;


import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXDataClient;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;

import org.testng.annotations.Test;

import java.util.List;

import junit.framework.Assert;


public class DeleteMultipleAccounts extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into Pub 7
     * Step 2 - Log into MPX web data service and get all available accounts
     * Step 3 - Open pub 7 and navigate to Confuguration>>Media>>Media: thePlatform mpx settings
     * Step 4 - Verify that mpx is configured. Fail test if not.
     * Step 5 - Click "MPX Login" link to expand mpx login options
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full"})
    public void DeleteMultipleAccounts() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 2
        MPXDataClient mpxDataClient = new MPXDataClient(webDriver);
        mpxDataClient.SignInToMPXDataClient("media", "mpx/AdminPub7QA", "Pa55word");
        List<String> accountNames = mpxDataClient.GetAllMPXAccounts();
        
        //Step 3
        applib.openApplication();
        taxonomy.NavigateSite("Configuration>>Media>>Media: thePlatform mpx settings");
        overlay.SwitchToFrame("Media: thePlatform mpx settings dialog");
        
        //Step 4
        Settings settings = new Settings(webDriver);
        if (settings.IsMPXConfigured() == true) { 
        	
        	//Step 5
        	settings.ExpandMPXLogin();
        	
        	//Step 6
        	
        }
        else { 
        	
        	Assert.fail("MPX is NOT configured. Test titled 'MultipleMPXAccountsPerLoginVerification' must run before any other MPX tests.");
        	
        }
        
        Assert.fail("Test under construction");
    }
}
