package com.nbcuni.tests.publisher.tests.advertising;


import junit.framework.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.Blocks;
import com.nbcuni.test.publisher.ContentTypes;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.publisher.DFPAddTags;
import com.nbcuni.test.publisher.Logout;
import com.nbcuni.test.publisher.Modules;
import com.nbcuni.test.publisher.Overlay;
import com.nbcuni.test.publisher.Taxonomy;
import com.nbcuni.test.publisher.UserLogin;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;

import common.Random;


public class GPTTagsVerification {
	
	private CustomWebDriver webDriver;
    private AppLib applib;

    /**
     * Instantiate the TestNG Before Class Method.
     * 
     * @param sEnv - environment
     * @throws Exception - error
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters("Environment")
    public void startSelenium(@Optional("PROD") String sEnv) {
        try {
            webDriver = WebDriverClientExecution.getInstance().getDriver();
            applib = new AppLib(webDriver);
            applib.setEnvironmentInfo(sEnv);
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

    /**
     * Instantiate the TestNG After Class Method.
     * 
     * @throws Exception - error
     */
    @AfterMethod(alwaysRun = true)
    public void stopSelenium() {
        try {
            webDriver.quit();
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

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
    @Test(groups = {"full", "smoke" })
    public void Test() {
        try {
            
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            //Step 1A
            Taxonomy taxonomy = new Taxonomy(webDriver);
            taxonomy.ClickTier1ModulesLnk();
            Modules modules = new Modules(webDriver);
            modules.SwitchToModulesFrm();
            modules.EnterFilterName("Doubleclick for Publishers");
            modules.EnableModule("Doubleclick for Publishers");
            Overlay overlay = new Overlay(webDriver);
            overlay.ClickCloseOverlayLnk();
            applib.switchToDefaultContent();
            
            //Step 2
            taxonomy.MouseOverTier1StructureLnk();
            taxonomy.MouseOverTier1StructureTier2DFPAddTagsLnk();
            taxonomy.ClickTier1StructureTier2DFPAddTagsTier3AddLnk();
            
            //Step 3
            DFPAddTags dfpAddTags = new DFPAddTags(webDriver);
            dfpAddTags.SwitchToAddNewDFPAdFrm();
            Random random = new Random();
            String adSlotName = random.GetCharacterString(15);
            dfpAddTags.EnterAdSlotName(adSlotName);
            dfpAddTags.EnterAdSizes("300x250");
            dfpAddTags.EnterAdUnitPatter("Test_AdUnit_Pattern");
            

            dfpAddTags.ClickSaveBtn();
            dfpAddTags.VerifyAdTagCreated(adSlotName);
            
            //Step 5
            overlay.ClickCloseOverlayLnk();
            applib.switchToDefaultContent();
            taxonomy.MouseOverTier1StructureLnk();
            taxonomy.ClickTier1ContentTier2BlocksLnk();
            
            //Step 6
            Blocks blocks = new Blocks(webDriver);
            blocks.SwitchToBlocksFrm();
            blocks.ClickPubSauceLnk();
            applib.switchToDefaultContent();
            blocks.SwitchToBlocksFrm();
            blocks.SelectRegion(adSlotName, "Right sidebar");
            blocks.ClickSaveBlocksBtn();
            blocks.VerifyBlockSettingsUpdated();
            
            //Step 7
            blocks.VerifySelectedRegion(adSlotName, "Right sidebar");
            
            //Step 8
            overlay.ClickCloseOverlayLnk();
            applib.switchToDefaultContent();
            taxonomy.ClickHomeLnk();
            blocks.VerifyScriptSourceInPage("http://www.googletagservices.com/tag/js/gpt.js");
            Assert.fail("Test case indicates there should be extra scripts present here. Following up with Pete");
            
            //Step 9
            Logout logout = new Logout(webDriver);
            logout.ClickLogoutBtn();
            
        } catch (Exception e) {
            applib.fail(e.toString());
       }

    }
}
