package com.nbcuni.test.publisher.tests.Advertising.MPS;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Configuration.MPSConfiguration;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;

public class AddMPSCallsToErrorPages extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC4950
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/22520431481
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AddMPSCallsToErrorPages_TC4950() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("SETUP");
        	Modules modules = new Modules(webWebWebDriver);
        	modules.VerifyModuleEnabled("MPS");
        	navigation.Configuration("MPS Configuration");
            MPSConfiguration mpsConfiguration = new MPSConfiguration(webWebWebDriver);
            mpsConfiguration.EnterMPSHost("stage-mps.nbcuni.com");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("pub7-development");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.CleanAllMPSOptions();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            
        	Reporter.log("STEP 2");
        	applib.openSitePage("/junkurl");
        	
        	Reporter.log("STEP 3");
        	mpsConfiguration.VerifyMPSCallParameters(Arrays.asList("\"site\":\"pub7-development\"", 
            		"\"path\":\"ERROR/404\"", "\"content_id\":\"ERROR\""));
    }
}
