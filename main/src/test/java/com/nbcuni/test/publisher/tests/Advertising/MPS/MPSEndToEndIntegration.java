package com.nbcuni.test.publisher.tests.Advertising.MPS;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.MPSConfiguration;
import com.nbcuni.test.publisher.pageobjects.Structure.MPSBlocks;

public class MPSEndToEndIntegration extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC3996
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20554120099
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify"})
    public void MPSEndToEndIntegration_TC3996() throws Exception {
        	
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        	navigation.Modules();
        	Modules modules = new Modules(webDriver);
        	modules.DisableModule("Pixelman");
        	modules.EnableModule("MPS");
        	
        	Reporter.log("SETUP");
        	navigation.Structure("MPS Blocks");
            MPSBlocks mpsBlocks = new MPSBlocks(webDriver);
            mpsBlocks.ClickAddLnk();
            String blockName = random.GetCharacterString(15);
            mpsBlocks.EnterBlockName(blockName);
            mpsBlocks.ClickSaveBtn();
            contentParent.VerifyMessageStatus(blockName.toLowerCase() + " has been created.");
            
        	Reporter.log("STEP 2");
        	navigation.Configuration("MPS Configuration");
        	
            Reporter.log("STEP 3");
            MPSConfiguration mpsConfiguration = new MPSConfiguration(webDriver);
            mpsConfiguration.EnterMPSHost("stage-mps.nbcuni.com");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("pub7-development");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.CleanAllMPSOptions();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            mpsConfiguration.ClickSyncAdBlocksBtn();
            contentParent.WaitForThrobberNotPresent();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            
            Reporter.log("STEP 4 - N/A");
            
            Reporter.log("STEP 5");
            navigation.Structure("Blocks");
            
            Reporter.log("STEP 6 and 7");
            Blocks blocks = new Blocks(webDriver);
            blocks.SelectRegion(blockName + " (MPS)", "Footer");
            blocks.ClickSaveBlocksBtn();
            contentParent.VerifyMessageStatus("The block settings have been updated.");
            
            Reporter.log("STEP 8");
            navigation.Home();
            Thread.sleep(5000); //TODO - figure out a good dynamic wait for this...
        	//mpsConfiguration.VerifyTopMultiAdPresent();
        	
        	Reporter.log("CLEANUP");
        	navigation.Structure("Blocks");
        	blocks.SelectRegion(blockName + " (MPS)", "- None -");
        	blocks.ClickSaveBlocksBtn();
        	
        	
    }
}
