package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SURF;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Surf;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class SURFModule extends ParentTest {

	Boolean testSuccessful = false;
	
	/*************************************************************************************
	 * TEST CASE - TC3564
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/19953324533
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	 public void SURFModule_TC3564() throws Exception {
		 
		Reporter.log("STEP 1 - N/A");
		
		Reporter.log("STEP 2");
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
	       
		Reporter.log("STEP 3 - 9");
		navigation.Modules();
		Modules modules = new Modules(webDriver);
		modules.EnableModule("Pub SURF Example");
		modules.EnableModule("Surf");
		
		Reporter.log("STEP 10");
		navigation.Structure("Blocks");
		
		Reporter.log("STEP 11");
		Blocks blocks = new Blocks(webDriver);
		blocks.SelectRegion("Surf Example Actions", "Sidebar first");
		blocks.SelectRegion("Surf Example Content", "Sidebar first");
		blocks.ClickSaveBlocksBtn();
		contentParent.VerifyMessageStatus("The block settings have been updated.");
		
		Reporter.log("STEP 12 - N/A");
		
		Reporter.log("STEP 13 - N/A");
		
		Reporter.log("STEP 14");
		navigation.Home();
		Surf surf = new Surf(webDriver);
		surf.ClickSignInBtn();
		
		Reporter.log("STEP 15");
		surf.SwitchToSurfFrm();
		surf.EnterEmailUsername("brandon.clark@nbcuni.com");
		surf.EnterPassword("tufNewcyd4#");
		surf.ClickSurfFrmSignInBtn();
		webDriver.switchTo().defaultContent();
		surf.VerifyUsernamePresent("baclark77");
		
		Reporter.log("STEP 16 - N/A");
		
		Reporter.log("STEP 17");
		surf.ClickSignOutBtn();
		
		Reporter.log("STEP 18 - N/A");
		
		Reporter.log("CLEANUP");
		navigation.Modules();
    	modules.DisableModule("Pub SURF Example");
    	navigation.ClickPrimaryTabNavLnk("Uninstall");
    	if (modules.IsModuleInstalled("Pub SURF Example")) {
        	modules.UninstallModule("Pub SURF Example");
        }
    	
		testSuccessful = true;
		
	}
	 
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"}, dependsOnMethods = {"SURFModule_TC3564"}, alwaysRun=true)
		public void Cleanup() throws Exception {
		 	if (testSuccessful == false) {
		 		UserLogin userLogin = applib.openApplication();
				userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
				navigation.Modules();
		    	Modules modules = new Modules(webDriver);
		    	modules.DisableModule("Pub SURF Example");
		    	navigation.ClickPrimaryTabNavLnk("Uninstall");
		    	if (modules.IsModuleInstalled("Pub SURF Example")) {
		        	modules.UninstallModule("Pub SURF Example");
		        }
		 	}
			
		}
}
