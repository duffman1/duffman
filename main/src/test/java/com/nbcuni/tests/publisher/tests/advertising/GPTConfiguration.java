package com.nbcuni.tests.publisher.tests.advertising;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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


public class GPTConfiguration {
	
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
     * Step 2 - Click on "Modules"<br>
     * Step 3 - In the filter list, type "DART". If the Dart module is enabled, disable it by unchecking the "Enabled" checkbox, and click the "Save configuration" button<br>
     * Step 4 - In the filter list, type "Doublecheck". Put a check in the "enabled" column next to the "Doublecheck for publishers" module, and click on the "Save configuration" button<br>
     * Step 5 - Click on "Structure" >> "DFP Ad Tags" >> "Global DFP Settings"<br>
     * Step 6 - In the "Network ID" field, type "nbcu", and click on the "Save configuration" button<br>
     * Step 7 - Log out Publisher 7
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void Test() {
        try {
            
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            //Step 2
            Taxonomy taxonomy = new Taxonomy(webDriver);
            taxonomy.ClickTier1ModulesLnk();
            
            //Step 3
            Modules modules = new Modules(webDriver);
            modules.SwitchToModulesFrm();
            modules.EnterFilterName("DART");
            modules.DisableModule("DART");
            
            //Step 4
            modules.EnterFilterName("Doubleclick for Publishers");
            modules.EnableModule("Doubleclick for Publishers");
            
            //Step 5
            Overlay overlay = new Overlay(webDriver);
            overlay.ClickCloseOverlayLnk();
            applib.switchToDefaultContent();
            taxonomy.MouseOverTier1StructureLnk();
            taxonomy.MouseOverTier1StructureTier2DFPAddTagsLnk();
            taxonomy.ClickTier1StructureTier2DFPAddTagsTier3GlobalDFPSettingsLnk();
            
            //Step 6
            DFPAddTags dfpAddTags = new DFPAddTags(webDriver);
            dfpAddTags.SwitchToDFPAddTagsFrm();
            dfpAddTags.EnterNetworkId("nbcu");
            dfpAddTags.ClickSaveConfigurationBtn();
            dfpAddTags.VerifyConfigurationSaved();
            overlay.ClickCloseOverlayLnk();
            applib.switchToDefaultContent();
            
            //Step 7
            Logout logout = new Logout(webDriver);
            logout.ClickLogoutBtn();
            
            
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }
}
