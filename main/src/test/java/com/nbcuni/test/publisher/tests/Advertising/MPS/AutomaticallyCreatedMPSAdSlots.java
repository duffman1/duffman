package com.nbcuni.test.publisher.tests.Advertising.MPS;

import java.util.Arrays;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.MPSConfiguration;
import com.nbcuni.test.publisher.pageobjects.Structure.MPSBlocks;

public class AutomaticallyCreatedMPSAdSlots extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC4128
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20696797763
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AutomaticallyCreatedMPSAdSlots_TC4128() throws Exception {
        
        	Reporter.log("STEP 1 AND SETUP");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        	MPSConfiguration mpsConfiguration = new MPSConfiguration(webDriver);
        	Modules modules = new Modules(webDriver, applib);
        	if (mpsConfiguration.IsMPSEnabled() == true) {
        		taxonomy.NavigateSite("Modules");
                overlay.SwitchToActiveFrame();
                modules.EnterFilterName("MPS");
                modules.DisableModule("MPS");
                overlay.ClickCloseOverlayLnk();
                overlay.switchToDefaultContent();
                taxonomy.NavigateSite("Modules>>Uninstall");
                overlay.SwitchToActiveFrame();
                modules.UninstallModule("MPS");
                overlay.SwitchToActiveFrame();
                overlay.ClickCloseOverlayLnk();
        	}
        	taxonomy.NavigateSite("Modules");
            overlay.SwitchToActiveFrame();
            modules.EnterFilterName("Pixelman");
        	modules.DisableModule("Pixelman");
            modules.EnterFilterName("MPS");
            modules.EnableModule("MPS");
            overlay.ClickCloseOverlayLnk();
        	taxonomy.NavigateSite("Structure>>MPS Blocks>>Add");
            overlay.SwitchToActiveFrame();
            MPSBlocks mpsBlocks = new MPSBlocks(webDriver);
            String blockName = random.GetCharacterString(15);
            mpsBlocks.EnterBlockName(blockName);
            mpsBlocks.ClickSaveBtn();
            contentParent.VerifyMessageStatus(blockName.toLowerCase() + " has been created.");
            overlay.ClickCloseOverlayLnk();
        	taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.EnterMPSHost("mps.io");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("pub7-development");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	
        	Reporter.log("STEP 2");
        	mpsConfiguration.ClickSyncAdBlocksBtn();
        	contentParent.VerifyMessageStatus("The configuration options have been saved.");
        	contentParent.VerifyPageContentPresent(Arrays.asList("The following new blocks have been created:", 
        			"topbox", "topmulti", "bottommulti", "topbanner"));
        	
        	Reporter.log("STEP 3");
        	contentParent.VerifyMessageWarning("These blocks don't exist in the list of ad blocks retrieved: " + blockName);
        	overlay.ClickCloseOverlayLnk();
        	
        	Reporter.log("STEP 4");
        	taxonomy.NavigateSite("Structure>>Blocks");
        	overlay.SwitchToActiveFrame();
        	Blocks blocks = new Blocks(webDriver);
        	blocks.SelectRegion("bottommulti (MPS)", "- None -");
        	blocks.SelectRegion("topbanner (MPS)", "- None -");
        	blocks.SelectRegion("topbox (MPS)", "- None -");
        	blocks.SelectRegion("topmulti (MPS)", "- None -");
        	blocks.SelectRegion(blockName + " (MPS)", "- None -");
        	
        	Reporter.log("STEP 5");
        	blocks.SelectRegion(blockName + " (MPS)", "Header");
        	blocks.ClickSaveBlocksBtn();
            contentParent.VerifyMessageStatus("The block settings have been updated.");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 6 - N/A");
            
            Reporter.log("STEP 7");
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.EnterMPSHost("mps.io");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("sandbox");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            
            Reporter.log("STEP 8");
            Reporter.log("Open mps service page at 'http://mps.io/request/describe/sandbox'.");
            webDriver.navigate().to("http://mps.io/request/describe/sandbox");
            contentParent.VerifyPageContentPresent(Arrays.asList("{\"instance\":{\"dart_mode\":\"gpt-asynchronous\",\"multislot\":\"0\"},\"adunits\":{\"Box Ad First\":{\"unit\":\"Box Ad First\",\"slot\":\"topbox\",\"sizes\":[\"300x250\"],\"responsive\":0},\"Box Ad Second\":{\"unit\":\"Box Ad Second\",\"slot\":\"bottombox\",\"sizes\":[\"300x250\"],\"responsive\":0},\"Cool Rich 1\":{\"unit\":\"Cool Rich 1\",\"slot\":\"coolrich\",\"responsive\":0},\"Cool Rich 2\":{\"unit\":\"Cool Rich 2\",\"slot\":\"coolrich\",\"responsive\":0},\"Flex Ad First\":{\"unit\":\"Flex Ad First\",\"slot\":\"topmulti\",\"sizes\":[\"300x250\",\"160x600\",\"300x600\"],\"responsive\":0},\"Flex Ad Second\":{\"unit\":\"Flex Ad Second\",\"slot\":\"bottommulti\",\"sizes\":[\"300x250\",\"160x600\",\"300x600\"],\"responsive\":0},\"Logo A\":{\"unit\":\"Logo A\",\"slot\":\"logo\",\"sizes\":[\"88x31\",\"120x90\"],\"responsive\":0},\"Top Banner\":{\"unit\":\"Top Banner\",\"slot\":\"topbanner\",\"sizes\":[\"728x90\",\"970x66\"],\"responsive\":0},\"Top Banner B\":{\"unit\":\"Top Banner B\",\"slot\":\"topbanner\",\"sizes\":[\"2x2\"],\"responsive\":0}},\"components\":[\"id-0516-4520471\",\"monitoring\",\"test.css\"]}"));
            
            Reporter.log("STEP 9");
            applib.openApplication();
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.ClickSyncAdBlocksBtn();
            contentParent.VerifyPageContentPresent(Arrays.asList("The following new blocks have been created:", 
        			"bottombox", "coolrich", "logo"));
            contentParent.VerifyPageContentNotPresent(Arrays.asList("topbox"));
            contentParent.VerifyMessageWarning("These blocks don't exist in the list of ad blocks retrieved: " + blockName);
            
    }
}
