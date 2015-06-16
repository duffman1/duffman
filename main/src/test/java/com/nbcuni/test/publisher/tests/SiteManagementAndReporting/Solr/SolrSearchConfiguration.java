package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.Solr;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.Solr;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;

public class SolrSearchConfiguration extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC6724
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/28867097666
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void SolrSearchConfiguration_TC6724() throws Exception {
    	
    	Reporter.log("STEP 1");
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
    	Reporter.log("STEP 2");
    	navigation.Modules();
    	Modules modules = new Modules(webDriver);
    	for (String module : Arrays.asList("Apache Solr framework", "Apache Solr search", 
    			"Acquia agent", "Acquia search", "Search")) {
    		modules.EnableModule(module);
    	}
    	
    	Reporter.log("STEP 3");
    	navigation.Configuration("Apache Solr search");
    	Solr solr = new Solr(webDriver);
    	solr.ClickDeleteSolrSearchIndexBtn();
    	
    	Reporter.log("STEP 4");
    	solr.ClickDeleteIndexBtn();
    	contentParent.VerifyMessageStatus("The index has been deleted.");
    	solr.ClickIndexAllQueuedContentBtn();
    	solr.ClickIndexAllRemainingBtn();
    	contentParent.WaitForProgressBarNotPresent();
    	ErrorChecking errorChecking = new ErrorChecking(webDriver);
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    	Reporter.log("STEP 5");
    	navigation.Structure("Blocks");
    	
    	Reporter.log("STEP 6");
    	Blocks blocks = new Blocks(webDriver);
    	blocks.SelectRegion("Search form", "Header");
    	blocks.ClickSaveBlocksBtn();
    	contentParent.VerifyMessageStatus("The block settings have been updated.");
    	
    	Reporter.log("STEP 7");
    	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
    	String postTitle = createDefaultContent.Post("Published");
    	
    	Reporter.log("STEP 8");
    	navigation.Configuration("Apache Solr search");
    	
    	Reporter.log("STEP 9");
    	solr.ClickIndexQueuedContentBtn();
    	contentParent.VerifyMessageStatus("Apachesolr cron successfully executed");
    	
    	Reporter.log("STEP 10");
    	for (int I = 0 ; ; I++) {
        	if (I >= 120) {
        		Assert.fail("Content item has not posted to solr in 2 minutes.");
        	}
        	
        	try {
        		Assert.assertTrue(webDriver.findElement(By.xpath("//body")).getText()
        				.contains("0 items (100% has been sent to the server)"));
        		break;
        	}
        	catch (AssertionError e) { 
        		Thread.sleep(1000);
        		applib.refreshPage();
        	}
        }
    	
    	Reporter.log("STEP 11");
    	navigation.Home();
    	
    	Reporter.log("STEP 12");
    	solr.Search(postTitle.substring(0, 8));
    	
    }
}
