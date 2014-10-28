package com.nbcuni.test.publisher.tests.Advertising.GooglePublisherTagsGPT;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.DFPAddTags;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class GPTTagsVerification extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC1157
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17533421348
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void GPTTagsVerification_TC1157() throws Exception {
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	//Setup
            taxonomy.NavigateSite("Modules");
            overlay.SwitchToActiveFrame();
            Modules modules = new Modules(webDriver);
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
