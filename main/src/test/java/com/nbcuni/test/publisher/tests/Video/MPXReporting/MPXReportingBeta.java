package com.nbcuni.test.publisher.tests.Video.MPXReporting;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.MPX.MPXStatusBeta;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;

public class MPXReportingBeta extends GlobalBaseTest {
	
    /*************************************************************************************
     * TEST CASE - TC3085
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/18695457554
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx"})
    public void MPXReportingBeta_TC3085() throws Exception {

    	if (config.getConfigValueString("DrushIngestion").equals("false")) {
    		Reporter.log("STEP 1");
        	UserLogin userLogin = appLib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("SETUP");
        	Settings settings = new Settings(webDriver);
        	settings.ConfigureMPXIfNeeded();
        	
        	Reporter.log("STEP 2");
        	Modules modules = new Modules(webDriver);
        	modules.VerifyModuleEnabled("Media: ThePlatform mpx Reports");
        	
        	Reporter.log("STEP 3");
        	navigation.Configuration("Media: thePlatform mpx settings");
        	navigation.ClickPrimaryTabNavLnk("Media: thePlatform MPX Status (BETA)");
        	
        	Reporter.log("STEP 4");
        	contentParent.VerifyPageContentPresent(Arrays.asList("Total Videos Ingested:", "View all videos here.", 
        			"Total Players Ingested:", "View all players here.", "Videos Updated Last Cron", 
        				"Players Updated Last Cron", "Total Videos Queued for Ingestion"));
        	
        	Reporter.log("STEP 5");
        	contentParent.VerifyPageContentPresent(Arrays.asList("Successfully signed into account \"DB TV\" (2312945284)."));
        	MPXStatusBeta mpxStatusBeta = new MPXStatusBeta(webDriver);
        	
        	Reporter.log("STEP 6");
        	Assert.assertTrue(mpxStatusBeta.GetTotalVideosIngestedCount() > 0);
        	Assert.assertTrue(mpxStatusBeta.GetTotalPlayersIngestedCount() > 0);
        	Assert.assertTrue(mpxStatusBeta.GetTotalVideosQueuedIngestionCount() >= 0);
        	Assert.assertTrue(mpxStatusBeta.GetVideosUpdatedLastCronCount() >= 0);
        	Assert.assertTrue(mpxStatusBeta.GetTotalVideosToBeProcessedCount() >= 0);
        	
        	//Steps 7 through 11 - //TODO revisit this test after mpx console upgrade to html
        	//at that point I can modify test to add additional steps with a new video upload and counts from mpx
        	//for additional validation on our end
    	}
    	
    }
}
