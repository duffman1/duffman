package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ProgramGuides.ROVITVSchedule;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.ProgramGuide;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;

public class ProgramGuideVerification extends ParentTest{
	/*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to the test instance as Drupal User 1 (usually admin in Publisher sites)./<br>
     * Step 2 - Populate the field, "Filter list" with the value, "program guide", put a check on the "ENABLED"
     * 			checkbox next to the "Program Guide" and "Program Guide Example" modules, and click on the "Save configuration" button.<br>
     * Step 3 - Populate the field, "Filter list" with the value, "views ui", put a check on the "ENABLED" checkbox 
     * 			next to the "Views UI" module, and click on the "Save configuration" button.
     * Step 4 - Click on Structure --> Blocks.
     * Step 5 - Scroll down to the "Program Guide" instance, and select the "Content" for the "REGION" value, 
     * 			and click on the "Save blocks" button.<br>
     * Step 6 - Click on "Configuration" --> "Web services" --> "Program Guide".
     * Step 7 - Populate the field, "Data URL" with the value,
     * 			"http://feed.entertainment.tv.theplatform.com/f/dCK2IC/stage_usa_listing?range=1-*&form=json".<br>
     * Step 8 -  Save and verify success message.
     * Step 9 - Verify that the section, "Program Guide" is displayed in the site top page.<br>
     * Step 10 - Verify that a table appears underneath it with the columns, "Date", "Show", "Information".
     * Step 11 - NA = Verify that only 1 row appears in the table, "There is no data".
     * Step 12 - Click on "Home" --> "Run Cron".<br>
     * Step 13 - Scroll down to the "Program Guide Data URL" field, and verify that it is populated with 
     * 			"http://feed.entertainment.tv.theplatform.com/f/dCK2IC/stage_usa_listing?range=1-*&form=json".<br>
     * Step 14 - Click on the "Home" icon.<br>
     * Step 15 - Verify that the section, "Program Guide" appears.
     * Step 16 - Verify that a table appears underneath it with the columns, "Date", "Show", "Information".<br>
     * Step 17 - Verify that the row, "No data found" does not appear anymore.<br>    
     * Step 18 - Verify that TV Listings information appears underneath the "Date", "Show" and "Information" column starting from the current date.
     * Step 19 - Scroll down, and verify that the TV Listings information appears for the next day also.
	 * Step 20 - Disable Program Guide block
     * @throws Throwable No Return values are needed
     *************************************************************************************/
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void ProgramGuideVerification_Test() throws Exception{
		
		//Step 1
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
			    
		//Step 2 and 3	   
		Modules modules = new Modules(webDriver, applib);
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
		ContentParent contentParent = new ContentParent(webDriver, applib);
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
