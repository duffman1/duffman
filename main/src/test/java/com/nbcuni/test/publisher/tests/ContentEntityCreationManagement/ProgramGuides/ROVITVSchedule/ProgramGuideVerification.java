package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ProgramGuides.ROVITVSchedule;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.Program_Guide;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.webdriver.CustomWebDriver;

public class ProgramGuideVerification extends ParentTest{
	/*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to the test instance as Drupal User 1 (usually admin in Publisher sites)./<br>
     * Step 2 - Verify that the section, "Program Guide" is displayed in the site top page.<br>
     * Step 3 - Verify that a table appears underneath it with the columns, "Date", "Show", "Information".
     * Step 4 - Verify that only 1 row appears in the table, "There is no data".
     * Step 5 - Click on "Home" --> "Run Cron".<br>
     * Step 6 - Scroll down to the "Program Guide Data URL" field, and verify that it is populated with 
     * 			"http://feed.entertainment.tv.theplatform.com/f/dCK2IC/stage_usa_listing?range=1-*&form=json".<br>
     * Step 7 - Click on the "Home" icon.<br>
     * Step 8 - Verify that the section, "Program Guide" appears.
     * Step 9 - Verify that a table appears underneath it with the columns, "Date", "Show", "Information".<br>
     * Step 10 - Verify that the row, "No data found" does not appear anymore.<br>    
     * Step 11 - Verify that TV Listings information appears underneath the "Date", "Show" and "Information" column starting from the current date.
     * Step 12 - Scroll down, and verify that the TV Listings information appears for the next day also.

     * @throws Throwable No Return values are needed
     *************************************************************************************/
	@Test(groups = {"full" })
    public void CreatePost() throws Exception{
		//Step 1
		UserLogin userLogin = applib.openApplication();
	    userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
	    //Step 2
	    Program_Guide program_Guide = new Program_Guide(webDriver);
        PageFactory.initElements(webDriver, program_Guide);
        program_Guide.VerifyProgramGuideText();
        //Step 3
        program_Guide.VerifyDateShowInfoColumn();        
        //Step 4
        program_Guide.VerifyNoDataRow();
        //Step 5
        Cron cron = new Cron(webDriver);
       // cron.ClickRunCronHomeLink();
        taxonomy.NavigateSite("Home>>Run cron");
        
      //TODO - Update dynamic wait time
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        
        ContentParent contentparent = new ContentParent(webDriver);
        contentparent.VerifyMessageStatus("Cron ran successfully.");
        //Step 6
        program_Guide.ProgramGuideRunCronStatus();
        overlay.ClickCloseOverlayLnk();
         //Step 7
        taxonomy.NavigateSite("Home");
        //Step 8
        program_Guide.VerifyProgramGuideText();
        //Step 9
        program_Guide.VerifyDateShowInfoColumn();
        
      //TODO - Cron run is not ale to fetch data through feed ...So need to cover following steps:
      //Step 10
        //Verify that the row, "No data found" does not appear anymore.
      //Step 11
        //Verify that TV Listings information appears underneath the "Date", "Show" and "Information" column starting from the current date.
      //Step 12
        //Scroll down, and verify that the TV Listings information appears for the next day also.
        
        
        
    }
    
   

}
