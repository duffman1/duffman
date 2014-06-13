package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.TVE;

import java.util.Arrays;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.TVEModule.AdobePass;
import com.nbcuni.test.publisher.pageobjects.TVEModule.MVPDConnection;
import com.nbcuni.test.publisher.pageobjects.TVEModule.OptimumLogin;
import com.nbcuni.test.publisher.pageobjects.TVEModule.TVEAuthExample;
import com.nbcuni.test.publisher.pageobjects.TVEModule.jQueryUpdate;

public class ImplementTVEModulesCore extends ParentTest {

	/*************************************************************************************
	 * TEST CASE - TC3261
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/19279373530
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	 public void ImplementTVEModulesCore_TC3261() throws Exception {
		 
		Reporter.log("STEP 1");
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
       
		Reporter.log("SETUP");
		jQueryUpdate jqueryUpdate = new jQueryUpdate(webDriver);
        applib.openSitePage("/#overlay=admin/config/development/jquery_update");
        overlay.SwitchToActiveFrame();
        jqueryUpdate.SelectDefaultjQueryVersion("1.5");
        jqueryUpdate.ClickSaveConfigurationBtn();
        overlay.ClickCloseOverlayLnk();
        
		Reporter.log("STEP 2");
		taxonomy.NavigateSite("Modules");
        overlay.SwitchToActiveFrame();
        Modules modules = new Modules(webDriver, applib);
        modules.EnterFilterName("TVE Adobe Pass");
        modules.EnableModule("TVE Adobe Pass");
        modules.EnterFilterName("TVE Auth Example");
        modules.EnableModule("TVE Auth Example");
        modules.EnterFilterName("TVE MVPD");
        modules.EnableModule("TVE MVPD");
        overlay.ClickCloseOverlayLnk();
		
		Reporter.log("STEP 3");
		taxonomy.NavigateSite("Structure>>Blocks");
		overlay.SwitchToActiveFrame();
		Blocks blocks = new Blocks(webDriver);
        blocks.SelectRegion("TVE Auth Example", "Sidebar first");
        blocks.ClickSaveBlocksBtn();
        contentParent.VerifyMessageStatus("The block settings have been updated.");
        overlay.ClickCloseOverlayLnk();
        
        TVEAuthExample tveAuthExample = new TVEAuthExample(webDriver);
        ErrorChecking errorChecking = new ErrorChecking(webDriver, applib);
        if (tveAuthExample.TVEAuthAlreadyConfigured().equals(false)) {
        	
        	Reporter.log("STEP 4");
            taxonomy.NavigateSite("Home");
            errorChecking.VerifyNoMessageErrorsPresent();
            contentParent.VerifyPageContentPresent(Arrays.asList("The MVPD Connection must be setup.", 
            		"The Adobe Pass configuration must be setup.", 
            		"Configure jQuery to use version 1.7 or higher."));
            
            Reporter.log("STEP 5");
            tveAuthExample.ClickMVPDSetupLnk();
            overlay.SwitchToActiveFrame();
            MVPDConnection mvpdConnection = new MVPDConnection(webDriver);
            mvpdConnection.EnterMVPDServiceURL("http://mvpd-admin.nbcuni.com/mvpd/service");
            mvpdConnection.EnterRequestorID("Syfy");
            mvpdConnection.SelectInstance("stage");
            mvpdConnection.EnterGenericErrorMessage("Oops! Something went wrong while fetching providers!");
            mvpdConnection.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 6");
            taxonomy.NavigateSite("Home");
            contentParent.VerifyPageContentNotPresent(Arrays.asList("The MVPD Connection must be setup."));
            
            Reporter.log("STEP 7");
            tveAuthExample.ClickAdobePassSetupLnk();
            overlay.SwitchToActiveFrame();
            AdobePass adobePass = new AdobePass(webDriver);
            adobePass.EnterAccessEnablerLocation("http://entitlement.auth-staging.adobe.com/entitlement/AccessEnablerDebug.swf");
            adobePass.EnterRequestTimeout("30000");
            adobePass.EnterAdobeFlashVersion("10.1.13");
            adobePass.EnterRequestorID("syfy");
            adobePass.EnterResourceID("syfy");
            adobePass.EnterUserNotAuthorizedError("Your TV subscription does not include this channel. Please contact your TV Provider about upgrading your service to get access.");
            adobePass.EnterInternalAuthorizationError("We had a problem reaching your TV provider. Please try again.");
            adobePass.EnterGenericAuthorizationError("Your TV subscription does not include this channel.");
            adobePass.EnterAdobePassConfigServicePath("http://api.auth-staging.adobe.com/api/v1/config/");
            adobePass.ClickTestConfigurationBtn();
            contentParent.VerifyMessageStatus("All the configurations are valid.");
            
            Reporter.log("STEP 8");
            adobePass.EnterRequestorID("syfy");
            adobePass.EnterResourceID("syfy");
            adobePass.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 9");
            taxonomy.NavigateSite("Home");
            contentParent.VerifyPageContentNotPresent(Arrays.asList("The Adobe Pass configuration must be setup."));
            
        }
        else {
        	Reporter.log("SKIP TO STEP 10 - TVE ALREADY CONFIGURED");
        }
        
        Reporter.log("STEP 10");
        tveAuthExample.ClickConfigureJQueryLnk();
        overlay.SwitchToActiveFrame();
        jqueryUpdate.SelectDefaultjQueryVersion("1.7");
        jqueryUpdate.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 11");
        taxonomy.NavigateSite("Home");
        tveAuthExample.VerifyAuthenticationStatusChecked("true");
        tveAuthExample.VerifyAuthenticated("false");
        tveAuthExample.VerifySelectedMVPD("none");
        
        Reporter.log("STEP 12");
        tveAuthExample.SelectMVPD("Optimum");
        tveAuthExample.ClickLoginBtn();
        OptimumLogin optimumLogin = new OptimumLogin(webDriver);
        optimumLogin.LoginToOptimum("research40", "support40");
        tveAuthExample.VerifyAuthenticationStatusChecked("true");
        tveAuthExample.VerifyAuthenticated("true");
        tveAuthExample.VerifySelectedMVPD("Cablevision");
        errorChecking.VerifyNoMessageErrorsPresent();
        
        Reporter.log("CLEANUP");
        taxonomy.NavigateSite("Structure>>Blocks");
        overlay.SwitchToActiveFrame();
        blocks.SelectRegion("TVE Auth Example", "- None -");
        blocks.ClickSaveBlocksBtn();
        applib.openSitePage("/#overlay=admin/config/development/jquery_update");
        overlay.SwitchToActiveFrame();
        jqueryUpdate.SelectDefaultjQueryVersion("1.5");
        jqueryUpdate.ClickSaveConfigurationBtn(); 
        
	}
}
