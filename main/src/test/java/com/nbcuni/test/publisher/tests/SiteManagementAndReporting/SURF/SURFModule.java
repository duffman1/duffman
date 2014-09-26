package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SURF;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Surf;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class SURFModule extends ParentTest {

	/*************************************************************************************
	 * TEST CASE - TC3564
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/19953324533
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	 public void SURFModule_TC3564() throws Exception {
		 
		Reporter.log("STEP 1 - N/A");
		
		Reporter.log("STEP 2");
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
	       
		Reporter.log("STEP 3 - 9");
		taxonomy.NavigateSite("Modules");
		overlay.SwitchToActiveFrame();
		Modules modules = new Modules(webDriver, applib);
		modules.EnterFilterName("Surf");
		modules.EnableModule("Surf");
		modules.EnterFilterName("Pub SURF Example");
		modules.EnableModule("Pub SURF Example");
		overlay.ClickCloseOverlayLnk();
		
		Reporter.log("STEP 10");
		taxonomy.NavigateSite("Structure>>Blocks");
		overlay.SwitchToActiveFrame();
		
		Reporter.log("STEP 11");
		Blocks blocks = new Blocks(webDriver);
		blocks.SelectRegion("Surf Example Actions", "Sidebar first");
		blocks.SelectRegion("Surf Example Content", "Sidebar first");
		blocks.ClickSaveBlocksBtn();
		contentParent.VerifyMessageStatus("The block settings have been updated.");
		overlay.ClickCloseOverlayLnk();
		
		Reporter.log("STEP 12 - N/A");
		
		Reporter.log("STEP 13 - N/A");
		
		Reporter.log("STEP 14");
		Surf surf = new Surf(webDriver, applib);
		surf.ClickSignInBtn();
		
		Reporter.log("STEP 15");
		surf.SwitchToSurfFrm();
		surf.EnterEmailUsername("brandon.clark@nbcuni.com");
		surf.EnterPassword("tufNewcyd4#");
		surf.ClickSurfFrmSignInBtn();
		overlay.switchToDefaultContent(true);
		surf.VerifyUsernamePresent("baclark77");
		
		Reporter.log("STEP 16 - N/A");
		
		Reporter.log("STEP 17");
		surf.ClickSignOutBtn();
		
		Reporter.log("STEP 18 - N/A");
		
	}
	 
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"}, dependsOnMethods = {"SURFModule_TC3564"}, alwaysRun=true)
		public void Cleanup() throws Exception {
			UserLogin userLogin = applib.openApplication();
			userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
			taxonomy.NavigateSite("Modules");
	    	overlay.SwitchToActiveFrame();
	    	Modules modules = new Modules(webDriver, applib);
	    	modules.EnterFilterName("Pub SURF Example");
	    	modules.DisableModule("Pub SURF Example");
	    	overlay.ClickOverlayTab("Uninstall");
	        overlay.SwitchToActiveFrame();
	        if (modules.IsModuleInstalled("Pub SURF Example")) {
	        	modules.UninstallModule("Pub SURF Example");
	        	overlay.SwitchToActiveFrame();
	        }
		}
}
