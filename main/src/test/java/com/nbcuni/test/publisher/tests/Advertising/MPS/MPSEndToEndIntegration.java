package com.nbcuni.test.publisher.tests.Advertising.MPS;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.MPSConfiguration;

public class MPSEndToEndIntegration extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC3996
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20554120099
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify", "mps"})
    public void MPSEndToEndIntegration_TC3996() throws Exception {
        	
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        	Modules modules = new Modules(webDriver);
        	taxonomy.NavigateSite("Modules");
        	overlay.SwitchToActiveFrame();
        	modules.EnterFilterName("Pixelman");
        	modules.DisableModule("Pixelman");
        	modules.EnterFilterName("MPS");
        	modules.EnableModule("MPS");
        	overlay.ClickCloseOverlayLnk();
        	
        	Reporter.log("STEP 2");
        	taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            
            Reporter.log("STEP 3");
            MPSConfiguration mpsConfiguration = new MPSConfiguration(webDriver);
            mpsConfiguration.EnterMPSHost("mps.io");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("pub7-development");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.CleanAllMPSOptions();
            mpsConfiguration.ClickSaveConfigurationBtn();
            mpsConfiguration.ClickSyncAdBlocksBtn();
            contentParent.WaitForThrobberNotPresent();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 4 - N/A");
            
            Reporter.log("STEP 5");
            taxonomy.NavigateSite("Structure>>Blocks");
            overlay.SwitchToActiveFrame();
            
            Reporter.log("STEP 6 and 7");
            Blocks blocks = new Blocks(webDriver);
            blocks.SelectRegion("topmulti (MPS)", "Footer");
            blocks.ClickSaveBlocksBtn();
            contentParent.VerifyMessageStatus("The block settings have been updated.");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 8");
            taxonomy.NavigateSite("Home");
            Thread.sleep(5000); //TODO - figure out a good dynamic wait for this...
        	//mpsConfiguration.VerifyTopMultiAdPresent();
        	overlay.switchToDefaultContent(true);
        	
        	Reporter.log("CLEANUP");
        	taxonomy.NavigateSite("Structure>>Blocks");
        	overlay.SwitchToActiveFrame();
        	blocks.SelectRegion("topmulti (MPS)", "- None -");
        	blocks.ClickSaveBlocksBtn();
        	
        	
    }
}
