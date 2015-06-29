package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ProgramGuides.ROVITVSchedule;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Configuration.ProgramGuide;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

public class ProgramGuideVerification extends ParentTest {
	
	private Boolean testSuccessful = false;
	
	/*************************************************************************************
     * TEST CASE - TC1579
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17926339982
     *************************************************************************************/
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void ProgramGuideVerification_TC1579() throws Exception{
		
		//Step 1
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
			    
		//Step 2 and 3	
		navigation.Modules();
		Modules modules = new Modules(webWebWebDriver);
		modules.EnableModule("Program Guide");
		modules.EnableModule("Program Guide Example");
			    
		//Step 4
		navigation.Structure("Blocks");
		       
		//Step 5
		Blocks blocks = new Blocks(webWebWebDriver);
		blocks.SelectRegion("Program Guide", "Footer fourth column");
		blocks.ClickSaveBlocksBtn();
		ContentParent contentParent = new ContentParent(webWebWebDriver);
		contentParent.VerifyMessageStatus("The block settings have been updated.");
		        
		//Step 6
		blocks.VerifySelectedRegion("Program Guide", "Footer fourth column");
		navigation.Configuration("Program Guide");
  
		//Step 7
		ProgramGuide programGuide = new ProgramGuide(webWebWebDriver);
		programGuide.EnterDataURL();
		      
		//Step 8
		programGuide.ClickSaveConfigBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");      
		        
		//Step 9
		navigation.Home();
	    programGuide.VerifyProgramGuideText();
        
        //Step 10
        programGuide.VerifyDateShowInfoColumn();   
        
        //Step 11 - N/A
        
        //Step 12
        Cron cron = new Cron(webWebWebDriver);
        cron.RunCron();
	    
        //Step 13
        navigation.Reports("Status report");
        programGuide.ProgramGuideRunCronStatus();
        
        //Step 14
        navigation.Home();
        
        //Step 15
        programGuide.VerifyProgramGuideText();
        
        //Step 16
        programGuide.VerifyDateShowInfoColumn();
        
        //Step 17 and 18 (truncated)
        programGuide.VerifyProgramGuideContainsShows();
        
        //Step 19
        navigation.Modules();
        modules.DisableModule("Program Guide Example");
        
        testSuccessful = true;
    }
	
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"}, dependsOnMethods = {"ProgramGuideVerification_TC1579"}, alwaysRun=true)
	public void Cleanup() throws Exception {
    	if (testSuccessful == false) {
    		UserLogin userLogin = applib.openApplication();
    		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
    		navigation.Modules();
    		Modules modules = new Modules(webWebWebDriver);
    		modules.DisableModule("Program Guide Example");
    		
    	}
		
	}
    
}
