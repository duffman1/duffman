package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SSO;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Configuration.SSOLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.SimpleSAML;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;
import com.nbcuni.test.publisher.pageobjects.People.People;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;

public class SSOManageUsers extends GlobalBaseTest {

	Boolean testSuccessful = false;
	
	/*************************************************************************************
	 * TEST CASE - TC3853
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/20332795162
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive", "broken"})
	 public void SSOManageUsers_TC3853() throws Exception {
		 
		UserLogin userLogin = new UserLogin(webDriver);
		Modules modules = new Modules(webDriver);
		SimpleSAML simpleSAML = new SimpleSAML(webDriver);
		Logout logout = new Logout(webDriver);
		String parentWindow = null;
		
		Reporter.log("STEP 1");
		appLib.openSitePage("/user");
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
		       
		Reporter.log("STEP 2");
		modules.VerifyModuleEnabled("Pub SSO");
		navigation.Configuration("SimpleSAMLphp Auth Settings");
		simpleSAML.VerifyDefaultSettings();
		simpleSAML.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "stage.crt");
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		contentParent.VerifyPageContentPresent(Arrays.asList("A certificate exists for this instance of SimpleSAMLphp."));
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		simpleSAML.CheckActivateAuthCbx();
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		logout.ClickLogoutBtn();
		appLib.openSitePage("/saml_login");
		Thread.sleep(1000);
		SSOLogin ssoLogin = new SSOLogin(webDriver);
		ssoLogin.EnterSSOID(config.getConfigValueString("SSOUsername"));
		ssoLogin.EnterPassword(config.getConfigValueString("SSOPassword"));
		ssoLogin.ClickSignInBtn();
		appLib.refreshPage();
		contentParent.VerifyPageContentPresent(Arrays.asList(config.getConfigValueString("SSOEmail")));
		contentParent.VerifyPageContentNotPresent(Arrays.asList("Modules"));
			
		Reporter.log("STEP 3");
		WorkBench workBench = new WorkBench(webDriver);
		workBench.ClickWorkBenchTab("Edit");
		AddUser addUser = new AddUser(webDriver);
		addUser.VerifyUsernameValueAndIsDisabled(config.getConfigValueString("SSOEmail"));
		addUser.VerifyEmailAddressValueAndIsDisabled(config.getConfigValueString("SSOEmail"));
		    
		Reporter.log("STEP 4");
		parentWindow = webDriver.getWindowHandle();
		addUser.ClickChangeYourPasswordLnk();
	    appLib.switchToNewWindow(parentWindow);
		ssoLogin.VerifySSOPasswordReset();
		    
		Reporter.log("STEP 5");
		webDriver.close();
		webDriver.switchTo().window(parentWindow);
		logout.ClickLogoutBtn();
		appLib.openSitePage("/user");
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
		    
		Reporter.log("STEP 6");
		String editorUserName = addUser.AddDefaultUser(Arrays.asList("editor"), true);
	        
	    Reporter.log("STEP 7");
		navigation.People();
		People people = new People(webDriver);
		people.SeachForUsername(editorUserName);
		people.ClickEditLnk(editorUserName);
		addUser.VerifyUsernameValueAndIsDisabled(editorUserName);
		    
		Reporter.log("STEPS 8-11");
		appLib.openSitePage("/admin/config/people/simplesamlphp_auth");
		simpleSAML.UnCheckActivateAuthCbx();
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		contentParent.VerifyMessageStatus("SimpleSAMLphp authentication is NOT yet activated.");
		navigation.Modules();
		modules.DisableModule("Pub SSO");
		Thread.sleep(1000);
		modules.DisableModule("simpleSAMLphp authentication");
		 
		testSuccessful = true;
		
	}
	 
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive", "broken"}, dependsOnMethods = {"SSOManageUsers_TC3853"}, alwaysRun=true)
		public void Cleanup() throws Exception {
			if (testSuccessful == false) {
				
				UserLogin userLogin = new UserLogin(webDriver);
				SimpleSAML simpleSAML = new SimpleSAML(webDriver);
				Modules modules = new Modules(webDriver);
				
				appLib.openSitePage("/user");
				userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
				appLib.openSitePage("/admin/config/people/simplesamlphp_auth");
				simpleSAML.UnCheckActivateAuthCbx();
				simpleSAML.ClickSaveConfigurationBtn();
				contentParent.VerifyMessageStatus("The configuration options have been saved.");
				contentParent.VerifyMessageStatus("SimpleSAMLphp authentication is NOT yet activated.");
				navigation.Modules();
				modules.DisableModule("Pub SSO");
				Thread.sleep(1000);
				modules.DisableModule("simpleSAMLphp authentication");
				
			}
		}
}
