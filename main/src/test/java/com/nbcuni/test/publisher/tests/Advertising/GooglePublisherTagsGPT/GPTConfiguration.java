package com.nbcuni.test.publisher.tests.Advertising.GooglePublisherTagsGPT;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.DFPAddTags;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class GPTConfiguration extends ParentTest{


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
    public void GPTConfiguration() throws Exception {
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            //Step 2
            Taxonomy taxonomy = new Taxonomy(webDriver);
            taxonomy.ClickTier1ModulesLnk();
            
            //Step 3
            Overlay overlay = new Overlay(webDriver);
            overlay.SwitchToModulesFrm();
            Modules modules = new Modules(webDriver);
            modules.EnterFilterName("DART");
            modules.DisableModule("DART");
            
            //Step 4
            modules.EnterFilterName("Doubleclick for Publishers");
            modules.EnableModule("Doubleclick for Publishers");
            
            //Step 5
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            taxonomy.ClickTier1StructureTier2DFPAddTagsTier3GlobalDFPSettingsLnk();
            
            //Step 6
            DFPAddTags dfpAddTags = new DFPAddTags(webDriver);
            overlay.SwitchToDFPAddTagsFrm();
            dfpAddTags.EnterNetworkId("nbcu");
            dfpAddTags.ClickSaveConfigurationBtn();
            dfpAddTags.VerifyConfigurationSaved();
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            
            //Step 7
            Logout logout = new Logout(webDriver);
            logout.ClickLogoutBtn();
            
        
    }
}
