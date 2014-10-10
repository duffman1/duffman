package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ProgramGuides.ROVITVSchedule;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.ProgramGuide;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;

public class ProgramGuideVerification extends ParentTest{
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
		Modules modules = new Modules(webDriver);
		taxonomy.NavigateSite("Modules");
		overlay.SwitchToActiveFrame();
		modules.EnterFilterName("Program Guide");
		modules.EnableModule("Program Guide");
		modules.EnterFilterName("Program Guide Example");
		modules.EnableModule("Program Guide Example");
		overlay.ClickCloseOverlayLnk();
			    
		//Step 4
		taxonomy.NavigateSite("Structure>>Blocks");        
		overlay.SwitchToActiveFrame();
		        
		//Step 5
		Blocks blocks = new Blocks(webDriver);
		blocks.SelectRegion("Program Guide", "Content");
		blocks.ClickSaveBlocksBtn();
		overlay.SwitchToActiveFrame();
		ContentParent contentParent = new ContentParent(webDriver);
		contentParent.VerifyMessageStatus("The block settings have been updated.");
		        
		//Step 6
		blocks.VerifySelectedRegion("Program Guide", "Content");
		overlay.ClickCloseOverlayLnk();
		taxonomy.NavigateSite("Configuration>>Web services>>Program Guide");
		      
		//Step 7
		overlay.SwitchToActiveFrame();
		ProgramGuide programGuide = new ProgramGuide(webDriver);
		programGuide.EnterDataURL();
		      
		//Step 8
		programGuide.ClickSaveConfigBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");      
		overlay.ClickCloseOverlayLnk();
		        
		//Step 9
	    programGuide.VerifyProgramGuideText();
        
        //Step 10
        programGuide.VerifyDateShowInfoColumn();   
        
        //Step 11 - N/A
        
        //Step 12
        taxonomy.NavigateSite("Home>>Run cron");
        overlay.SwitchToActiveFrame();
        ErrorChecking errorChecking = new ErrorChecking(webDriver);
        errorChecking.VerifyNoMessageErrorsPresent();
	    
        //Step 13
        programGuide.ProgramGuideRunCronStatus();
        overlay.ClickCloseOverlayLnk();
        
        //Step 14
        taxonomy.NavigateSite("Home");
        
        //Step 15
        programGuide.VerifyProgramGuideText();
        
        //Step 16
        programGuide.VerifyDateShowInfoColumn();
        
        //Step 17 and 18 (truncated)
        programGuide.VerifyProgramGuideContainsShows();
        
        //Step 19
        taxonomy.NavigateSite("Structure>>Blocks");
        overlay.SwitchToActiveFrame();
        blocks.SelectRegion("Program Guide", "- None -");
        blocks.ClickSaveBlocksBtn();
        
    }
    
}
