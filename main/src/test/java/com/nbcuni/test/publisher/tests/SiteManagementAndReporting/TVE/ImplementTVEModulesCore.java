package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.TVE;

import java.util.Arrays;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Blocks;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.FlushCache;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.TVEModule.AdobePass;
import com.nbcuni.test.publisher.pageobjects.TVEModule.MVPDConnection;
import com.nbcuni.test.publisher.pageobjects.TVEModule.OptimumLogin;
import com.nbcuni.test.publisher.pageobjects.TVEModule.TVEAuthExample;
import com.nbcuni.test.publisher.pageobjects.TVEModule.jQueryUpdate;

public class ImplementTVEModulesCore extends ParentTest {

	Boolean testSuccessful = false;
	
	/*************************************************************************************
	 * TEST CASE - TC3261
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/19279373530
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	 public void ImplementTVEModulesCore_TC3261() throws Exception {
		 
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
		Modules modules = new Modules(webDriver);
		modules.VerifyModuleEnabled("jQuery Update");
		jQueryUpdate jqueryUpdate = new jQueryUpdate(webDriver);
		Thread.sleep(2000);
        applib.openSitePage("/admin/config/development/jquery_update");
        jqueryUpdate.SelectDefaultjQueryVersion("1.5");
        jqueryUpdate.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("The configuration options have been saved");
        FlushCache flushCache = new FlushCache(webDriver);
        flushCache.FlushAllCache();
        
		navigation.Modules();
        for (String module : Arrays.asList("TVE Auth Example", "TVE Adobe Pass", "TVE MVPD")) {
        	modules.EnableModule(module);
        }
        
		navigation.Structure("Blocks");
		Blocks blocks = new Blocks(webDriver);
        blocks.SelectRegion("TVE Auth Example", "Sidebar first");
        blocks.ClickSaveBlocksBtn();
        contentParent.VerifyMessageStatus("The block settings have been updated.");
        
        TVEAuthExample tveAuthExample = new TVEAuthExample(webDriver);
        ErrorChecking errorChecking = new ErrorChecking(webDriver);
        
        navigation.Home();
        if (!tveAuthExample.isMVPDConfigured()) {
        	tveAuthExample.ClickMVPDSetupLnk();
            MVPDConnection mvpdConnection = new MVPDConnection(webDriver);
            mvpdConnection.EnterMVPDServiceURL("http://mvpd-admin.nbcuni.com/mvpd/service/syfy/prod/desktop");
            mvpdConnection.EnterGenericErrorMessage("Oops! Something went wrong while fetching providers!");
            mvpdConnection.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            
            navigation.Home();
            contentParent.VerifyPageContentNotPresent(Arrays.asList("The MVPD Connection must be setup."));
        }
        else {
        	System.out.println("MVPD IS ALREADY CONFIGURED.");
        	Reporter.log("MVPD IS ALREADY CONFIGURED.");
        }
        
        if (!tveAuthExample.isAdobePassConfigured()) {
        	tveAuthExample.ClickAdobePassSetupLnk();
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
            
            adobePass.EnterRequestorID("syfy");
            adobePass.EnterResourceID("syfy");
            adobePass.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            
            navigation.Home();
            contentParent.VerifyPageContentNotPresent(Arrays.asList("The Adobe Pass configuration must be setup."));
        }
        else {
        	System.out.println("ADOBE PASS IS ALREADY CONFIGURED.");
        	Reporter.log("ADOBE PASS IS ALREADY CONFIGURED.");
        }
        
        tveAuthExample.ClickConfigureJQueryLnk();
        jqueryUpdate.SelectDefaultjQueryVersion("1.7");
        jqueryUpdate.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");
        
        navigation.Home();
        tveAuthExample.VerifyAuthenticationStatusChecked("true");
        tveAuthExample.VerifyAuthenticated("false");
        tveAuthExample.VerifySelectedMVPD("none");
        
        tveAuthExample.SelectMVPD("Optimum");
        tveAuthExample.ClickLoginBtn();
        OptimumLogin optimumLogin = new OptimumLogin(webDriver);
        optimumLogin.LoginToOptimum("research40", "support40");
        tveAuthExample.VerifyAuthenticationStatusChecked("true");
        tveAuthExample.VerifyAuthenticated("true");
        tveAuthExample.VerifySelectedMVPD("Cablevision");
        errorChecking.VerifyNoMessageErrorsPresent();
        
        Reporter.log("CLEANUP");
        applib.openSitePage("/admin/config/development/jquery_update");
		Thread.sleep(1000);
		jqueryUpdate.SelectDefaultjQueryVersion("1.5");
		jqueryUpdate.ClickSaveConfigurationBtn(); 
		contentParent.VerifyMessageStatus("The configuration options have been saved");
		flushCache.FlushAllCache();
		navigation.Modules();
		for (String module : Arrays.asList("TVE Auth Example")) {
			modules.DisableModule(module);
        	navigation.ClickPrimaryTabNavLnk("Uninstall");
            if (modules.IsModuleInstalled(module)) {
            	modules.UninstallModule(module);
            }
        }
		
        testSuccessful = true;
        
	}
	 
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"}, dependsOnMethods = {"ImplementTVEModulesCore_TC3261"}, alwaysRun=true)
	public void Cleanup() throws Exception {
		
		if (testSuccessful == false) {
			UserLogin userLogin = applib.openApplication();
			userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
			applib.openSitePage("/admin/config/development/jquery_update");
			Thread.sleep(1000);
			jQueryUpdate jqueryUpdate = new jQueryUpdate(webDriver);
			jqueryUpdate.SelectDefaultjQueryVersion("1.5");
			jqueryUpdate.ClickSaveConfigurationBtn(); 
			contentParent.VerifyMessageStatus("The configuration options have been saved");
			FlushCache flushCache = new FlushCache(webDriver);
			flushCache.FlushAllCache();
			Modules modules = new Modules(webDriver);
			navigation.Modules();
			for (String module : Arrays.asList("TVE Auth Example")) {
				modules.DisableModule(module);
	        	navigation.ClickPrimaryTabNavLnk("Uninstall");
	            if (modules.IsModuleInstalled(module)) {
	            	modules.UninstallModule(module);
	            	
	            }
	        }
		}
		
	}
}
