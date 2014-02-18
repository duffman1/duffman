package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ProgramGuides.ROVITVSchedule;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.ProgramGuide;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;

public class ProgramGuideVerification extends ParentTest{
	/*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to the test instance as Drupal User 1 (usually admin in Publisher sites)./<br>
     * Step 2 - Verify that the section, "Program Guide" is displayed in the site top page.<br>
     * Step 3 - Verify that a table appears underneath it with the columns, "Date", "Show", "Information".
     * Step 4 - NA = Verify that only 1 row appears in the table, "There is no data".
     * Step 5 - Click on "Home" --> "Run Cron".<br>
     * Step 6 - Scroll down to the "Program Guide Data URL" field, and verify that it is populated with 
     * 			"http://feed.entertainment.tv.theplatform.com/f/dCK2IC/stage_usa_listing?range=1-*&form=json".<br>
     * Step 7 - Click on the "Home" icon.<br>
     * Step 8 - Verify that the section, "Program Guide" appears.
     * Step 9 - Verify that a table appears underneath it with the columns, "Date", "Show", "Information".<br>
     * Step 10 - Verify that the row, "No data found" does not appear anymore.<br>    
     * Step 11 - Verify that TV Listings information appears underneath the "Date", "Show" and "Information" column starting from the current date.
     * Step 12 - Scroll down, and verify that the TV Listings information appears for the next day also.
	 * Step 13 - Disable Program Guide block
     * @throws Throwable No Return values are needed
     *************************************************************************************/
	@Test(groups = {"full" })
    public void ProgramGuideVerification_Test() throws Exception{
		
		//Step 1
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
	    
	    //Step 2
	    ProgramGuide programGuide = new ProgramGuide(webDriver);
        programGuide.VerifyProgramGuideText();
        
        //Step 3
        programGuide.VerifyDateShowInfoColumn();   
        
        //Step 4 - N/A
        
        //Step 5
        taxonomy.NavigateSite("Home>>Run cron");
        overlay.SwitchToActiveFrame();
	    ContentParent contentParent = new ContentParent(webDriver, applib);
	    contentParent.VerifyMessageStatus("Cron ran successfully.");
        
        //Step 6
        programGuide.ProgramGuideRunCronStatus();
        overlay.ClickCloseOverlayLnk();
        
        //Step 7
        taxonomy.NavigateSite("Home");
        
        //Step 8
        programGuide.VerifyProgramGuideText();
        
        //Step 9
        programGuide.VerifyDateShowInfoColumn();
        
        //Step 10 through 12 (truncated)
        programGuide.VerifyProgramGuideContainsShows();
        
        //Step 13
        taxonomy.NavigateSite("Structure>>Blocks");
        overlay.SwitchToActiveFrame();
        Blocks blocks = new Blocks(webDriver);
        blocks.SelectRegion("Program Guide", "- None -");
        blocks.ClickSaveBlocksBtn();
        
    }
    
   

}
