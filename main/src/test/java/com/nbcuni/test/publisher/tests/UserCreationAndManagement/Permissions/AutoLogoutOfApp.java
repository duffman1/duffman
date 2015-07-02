package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Permissions;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Configuration.AutoLogout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;

public class AutoLogoutOfApp extends GlobalBaseTest {
	
	Boolean testSuccessful = false;
	
    /*************************************************************************************
     * TEST CASE - TC5948
     * Steps - https://rally1.rallydev.com/#/14663927728/detail/testcase/25570568057
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AutoLogoutOfApp_TC5948() throws Exception {
    	
    	Reporter.log("STEP 1");
    	UserLogin userLogin = appLib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
    	Reporter.log("STEP 2");
    	Modules modules = new Modules(webDriver);
    	modules.VerifyModuleEnabled("Automated Logout");
    	
    	Reporter.log("STEP 3");
    	navigation.Configuration("Auto Logout");
    	AutoLogout autoLogout = new AutoLogout(webDriver);
    	autoLogout.EnterTimeout("60");
    	autoLogout.EnterMaxTimeout("172800");
    	autoLogout.EnterTimeoutPadding("20");
    	autoLogout.UncheckRoleTimeout();
    	autoLogout.EnterRedirectURLAtLogout("user/login");
    	autoLogout.UncheckDoNotDisplayLogoutDlgCbx();
    	autoLogout.EnterMessageToDisplayInLogout("Your session is about to expire. Do you want to reset it?");
    	autoLogout.EnterMessageToDisplayAfterLogout("You have been logged out due to inactivity.");
    	autoLogout.UncheckEnforceAutoLogoutAdminPages();
    	autoLogout.ClickSaveConfigurationBtn();
    	contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	
    	Reporter.log("STEP 4");
    	navigation.Home();
    	autoLogout.VerifyAutoLogoutConfirmationPresent(70);
    	
    	Reporter.log("STEP 5");
    	autoLogout.ClickYesBtn();
    	
    	Reporter.log("STEP 6");
    	navigation.Configuration("Auto Logout");
    	autoLogout.CheckRoleTimeout();
    	autoLogout.CheckRoleByType("administrator");
    	autoLogout.EnterRoleTimeout("administrator", "60");
    	autoLogout.EnterTimeoutPadding("20");
    	autoLogout.ClickSaveConfigurationBtn();
    	contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	
    	Reporter.log("STEP 7 AND 8");
    	navigation.Home();
    	autoLogout.VerifyAutoLogoutConfirmationPresent(70);
    	Thread.sleep(20000);
    	contentParent.VerifyMessageStatus("You have been logged out due to inactivity.");
    	appLib.openSitePage("/admin");
    	contentParent.VerifyPageContentPresent(Arrays.asList("Access denied"));
    	
    	Reporter.log("CLEANUP");
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        navigation.Modules();
        modules.DisableModule("Automated Logout");
        
        testSuccessful = true;
        
    }
    
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"}, dependsOnMethods = {"AutoLogoutOfApp_TC5948"}, alwaysRun=true)
	public void Cleanup() throws Exception {
		
		if (testSuccessful == false) {
			UserLogin userLogin = appLib.openApplication();
			userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
			navigation.Modules();
			Modules modules = new Modules(webDriver);
	        modules.DisableModule("Automated Logout");
		}
		
	}
}
