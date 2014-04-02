package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ProgramGuides.ROVITVSchedule;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.ProgramGuide;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;

public class Configuration extends ParentTest{
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
     * @throws Throwable No Return values are needed
     *************************************************************************************/
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void Configuration_Test() throws Exception{
		
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
	    modules.EnterFilterName("Views UI");
	    modules.EnableModule("Views UI");
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
        
	}
	
}
