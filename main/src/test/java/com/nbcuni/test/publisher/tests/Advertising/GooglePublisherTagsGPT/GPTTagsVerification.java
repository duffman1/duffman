package com.nbcuni.test.publisher.tests.Advertising.GooglePublisherTagsGPT;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.DFPAddTags;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;

public class GPTTagsVerification extends ParentTest{
	
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
    @Test(groups = {"full" })
    public void GPTTagsVerification_Test() throws Exception {
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	PageFactory.initElements(webDriver, userLogin);
            userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 1A
            Modules modules = new Modules(webDriver);
            modules.VerifyModuleEnabled("Doubleclick for Publishers");
            
            //Step 2
            taxonomy.NavigateSite("Structure>>DFP Ad Tags>>Add");
            
            //Step 3
            overlay.SwitchToFrame("Add a new DFP ad");
            DFPAddTags dfpAddTags = new DFPAddTags(webDriver);
            Random random = new Random();
            String adSlotName = random.GetCharacterString(15);
            dfpAddTags.EnterAdSlotName(adSlotName);
            dfpAddTags.EnterAdSizes("300x250");
            dfpAddTags.EnterAdUnitPatter("Test_AdUnit_Pattern");
            
            //Step 4
            dfpAddTags.ClickSaveBtn();
            dfpAddTags.VerifyAdTagCreated(adSlotName);
            
            //Step 5
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Structure>>Blocks");
            
            //Step 6
            overlay.SwitchToActiveFrame();
            Blocks blocks = new Blocks(webDriver);
            PageFactory.initElements(webDriver, blocks);
            blocks.SelectRegion("DFP tag: " + adSlotName, "Sidebar first");
            blocks.ClickSaveBlocksBtn();
            ContentParent contentParent = new ContentParent(webDriver);
            PageFactory.initElements(webDriver, contentParent);
            contentParent.VerifyMessageStatus("The block settings have been updated.");
            
            //Step 7
            blocks.VerifySelectedRegion(adSlotName, "Sidebar first");
            
            //Step 8
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Home");
            blocks.VerifyScriptSourceInPage("http://www.googletagservices.com/tag/js/gpt.js");
            blocks.VerifyHomePageBlockPresent(adSlotName);
            
            //Step 9 - NA
            
    }
}
