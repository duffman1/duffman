package com.nbcuni.test.publisher.tests.Advertising.GooglePublisherTagsGPT;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.DFPAddTags;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class GPTTagsVerification extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 1A - Addendum: Click on "Modules", enter filter for "Double click for publishers" and ensure enabled. If necessary, enable it.
     * Step 2 - Click on "Structure" >> "DFP Add Tags" >> "Add"<br>
     * Step 3 - Populate  the following fields with their corresponding values<br>
     * Ad Slot Name = Test GPT
     * Size(s) = 300x250
     * Ad Unit Patter = Test_AdUnit_Pattern
     * Step 4 - Click on the "Save" button<br>
     * Step 5 - Click on "Structure" >> "Blocks"<br>
     * Step 6 - Scroll down until a field with created prefix of the value given in step 3 for "ad slot name" is found. Under the "Region" column, select the value "right sidebar" (note - may vary by theme) and click the "Save blocks" button<br>
     * Step 7 - Scroll down to the "Right sidebar" section and verify that the value from step 3 exists, and the region is "Right sidebar"<br>
     * Step 8 - Click on the home icon, access the page source and verify the relevant script sources are present.
     * Step 9 - Log out of publisher 7
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void GPTTagsVerification_Test() throws Exception {
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	//Setup
            taxonomy.NavigateSite("Modules");
            overlay.SwitchToActiveFrame();
            Modules modules = new Modules(webDriver, applib);
            modules.EnterFilterName("DART");
            modules.DisableModule("DART");
            modules.EnterFilterName("Doubleclick for Publishers");
            modules.EnableModule("Doubleclick for Publishers");
            overlay.ClickCloseOverlayLnk();
            taxonomy.NavigateSite("Structure>>DFP Ad Tags>>Global DFP Settings");
            overlay.SwitchToActiveFrame();
            DFPAddTags dfpAddTags = new DFPAddTags(webDriver, applib);
            dfpAddTags.EnterNetworkId("nbcu");
            dfpAddTags.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            overlay.ClickCloseOverlayLnk();
            
            //Step 2
            taxonomy.NavigateSite("Structure>>DFP Ad Tags>>Add");
            overlay.SwitchToActiveFrame();
            
            //Step 3
            String adSlotName = random.GetCharacterString(15);
            dfpAddTags.EnterAdSlotName(adSlotName);
            dfpAddTags.EnterAdSizes("300x250");
            dfpAddTags.EnterAdUnitPattern("Test_AdUnit_Pattern");
            
            //Step 4
            dfpAddTags.ClickSaveBtn();
            contentParent.VerifyMessageStatus(adSlotName + " has been created.");
            overlay.ClickCloseOverlayLnk();
            
            //Step 5
            taxonomy.NavigateSite("Structure>>Blocks");
            overlay.SwitchToActiveFrame();
            
            //Step 6
            Blocks blocks = new Blocks(webDriver);
            blocks.SelectRegion("DFP tag: " + adSlotName, "Sidebar first");
            blocks.ClickSaveBlocksBtn();
            contentParent.VerifyMessageStatus("The block settings have been updated.");
            
            //Step 7
            blocks.VerifySelectedRegion(adSlotName, "Sidebar first");
            overlay.ClickCloseOverlayLnk();
            
            //Step 8
            taxonomy.NavigateSite("Home");
            blocks.VerifyScriptSourceInPage("http://www.googletagservices.com/tag/js/gpt.js");
            blocks.VerifyHomePageBlockPresent(adSlotName);
            
            //Step 9 - NA
            
            //Cleanup - remove added block
            taxonomy.NavigateSite("Structure>>Blocks");
            overlay.SwitchToActiveFrame();
            blocks.SelectRegion("DFP tag: " + adSlotName, "- None -");
            blocks.ClickSaveBlocksBtn();
    }
}
