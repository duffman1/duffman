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
            navigation.Modules();
            Modules modules = new Modules(webDriver);
            modules.DisableModule("DART");
            modules.EnableModule("Doubleclick for Publishers");
            navigation.Structure("DFP Ad Tags");
            DFPAddTags dfpAddTags = new DFPAddTags(webDriver, applib);
            dfpAddTags.ClickGlobalDFPSettingsLnk();
            dfpAddTags.EnterNetworkId("nbcu");
            dfpAddTags.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            
            //Step 2
            navigation.Structure("DFP Ad Tags");
            dfpAddTags.ClickAddLnk();
            
            //Step 3
            String adSlotName = random.GetCharacterString(15);
            dfpAddTags.EnterAdSlotName(adSlotName);
            dfpAddTags.EnterAdSizes("300x250");
            dfpAddTags.EnterAdUnitPattern("Test_AdUnit_Pattern");
            
            //Step 4
            dfpAddTags.ClickSaveBtn();
            contentParent.VerifyMessageStatus(adSlotName + " has been created.");
            
            //Step 5
            navigation.Structure("Blocks");
            
            //Step 6
            Blocks blocks = new Blocks(webDriver);
            blocks.SelectRegion("DFP tag: " + adSlotName, "Sidebar first");
            blocks.ClickSaveBlocksBtn();
            contentParent.VerifyMessageStatus("The block settings have been updated.");
            
            //Step 7
            blocks.VerifySelectedRegion(adSlotName, "Sidebar first");
           
            //Step 8
            navigation.Home();
            blocks.VerifyScriptSourceInPage("http://www.googletagservices.com/tag/js/gpt.js");
            blocks.VerifyHomePageBlockPresent(adSlotName);
            
            //Step 9 - NA
            
            //Cleanup - remove added block
            navigation.Structure("Blocks");
            blocks.SelectRegion("DFP tag: " + adSlotName, "- None -");
            blocks.ClickSaveBlocksBtn();
    }
}
