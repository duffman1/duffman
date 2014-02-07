package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ProgramGuides.ROVITVSchedule;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.ProgramGuide;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;

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
	@Test(groups = {"full" })
    public void Configuration_Test() throws Exception{
		
		//Step 1
		UserLogin userLogin = applib.openApplication();
		PageFactory.initElements(webDriver, userLogin);
	    userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
	    
	    //Step 2	   
	    Modules modules = new Modules(webDriver, applib);
	    modules.VerifyModuleEnabled("Program Guide");
	    modules.VerifyModuleEnabled("Program Guide Example");
	    
	    //Step 3
	    modules.VerifyModuleEnabled("Views UI");
	   
	    //Step 4
	    overlay.switchToDefaultContent();
        taxonomy.NavigateSite("Structure>>Blocks");        
        
        //Step 5
        overlay.SwitchToActiveFrame();
        Blocks blocks = new Blocks(webDriver);
        PageFactory.initElements(webDriver, blocks);
        blocks.SelectRegion("Program Guide", "Content");
        blocks.ClickSaveBlocksBtn();
        overlay.SwitchToActiveFrame();
        ContentParent contentParent = new ContentParent(webDriver, applib);
        PageFactory.initElements(webDriver, contentParent);
        contentParent.VerifyMessageStatus("The block settings have been updated.");
        
        //Step 6
        blocks.VerifySelectedRegion("Program Guide", "Content");
        overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent();
        taxonomy.NavigateSite("Configuration>>Web services>>Program Guide");
      
        //Step 7
        overlay.SwitchToActiveFrame();
        ProgramGuide programGuide = new ProgramGuide(webDriver);
        PageFactory.initElements(webDriver, programGuide);
        programGuide.EnterDataURL();
      
        //Step 8
        programGuide.ClickSaveConfigBtn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");       
        
	}
	
}
