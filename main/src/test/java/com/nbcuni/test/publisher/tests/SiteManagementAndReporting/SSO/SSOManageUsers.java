package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SSO;

import java.util.Arrays;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.SSOLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.SimpleSAML;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;
import com.nbcuni.test.publisher.pageobjects.People.People;

public class SSOManageUsers extends ParentTest {

	Boolean testSuccessful = false;
	
	/*************************************************************************************
	 * TEST CASE - TC3853
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/20332795162
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive"})
	 public void SSOManageUsers_TC3853() throws Exception {
		 
		UserLogin userLogin = new UserLogin(webDriver); 
		Modules modules = new Modules(webDriver);
		SimpleSAML simpleSAML = new SimpleSAML(webDriver);
		Logout logout = new Logout(webDriver);
		String parentWindow = null;
		
		Reporter.log("STEP 1");
		applib.openSitePage("/user");
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
		       
		Reporter.log("STEP 2");
		modules.VerifyModuleEnabled("Pub SSO");
		taxonomy.NavigateSite("Configuration>>People>>SimpleSAMLphp Auth Settings");
		overlay.SwitchToActiveFrame();
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
		overlay.ClickCloseOverlayLnk();
		logout.ClickLogoutBtn();
		applib.openSitePage("/saml_login");
		Thread.sleep(1000);
		SSOLogin ssoLogin = new SSOLogin(webDriver);
		ssoLogin.EnterSSOID(config.getConfigValueString("SSOUsername"));
		ssoLogin.EnterPassword(config.getConfigValueString("SSOPassword"));
		ssoLogin.ClickSignInBtn();
		webDriver.navigate().refresh();
		contentParent.VerifyPageContentPresent(Arrays.asList(config.getConfigValueString("SSOEmail")));
		contentParent.VerifyPageContentNotPresent(Arrays.asList("Modules"));
			
		Reporter.log("STEP 3");
		WorkBench workBench = new WorkBench(webDriver);
		workBench.ClickWorkBenchTab("Edit");
		overlay.SwitchToActiveFrame();
		AddUser addUser = new AddUser(webDriver);
		addUser.VerifyUsernameValueAndIsDisabled(config.getConfigValueString("SSOEmail"));
		addUser.VerifyEmailAddressValueAndIsDisabled(config.getConfigValueString("SSOEmail"));
		    
		Reporter.log("STEP 4");
		parentWindow = webDriver.getWindowHandle();
		addUser.ClickChangeYourPasswordLnk();
	    applib.switchToNewWindow(parentWindow);
		ssoLogin.VerifySSOPasswordReset();
		    
		Reporter.log("STEP 5");
		webDriver.close();
		webDriver.switchTo().window(parentWindow);
		overlay.SwitchToActiveFrame();
		overlay.ClickCloseOverlayLnk();
		logout.ClickLogoutBtn();
		applib.openSitePage("/user");
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
		    
		Reporter.log("STEP 6");
		String editorUserName = addUser.AddDefaultUser(Arrays.asList("editor"), true);
	        
	    Reporter.log("STEP 7");
		taxonomy.NavigateSite("People");
		overlay.SwitchToActiveFrame();
		People people = new People(webDriver);
		people.SeachForUsername(editorUserName);
		people.ClickEditLnk(editorUserName);
		overlay.SwitchToActiveFrame();
		addUser.VerifyUsernameValueAndIsDisabled(editorUserName);
		    
		Reporter.log("STEPS 8-11");
		applib.openSitePage("/admin/config/people/simplesamlphp_auth");
		simpleSAML.UnCheckActivateAuthCbx();
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		contentParent.VerifyMessageStatus("SimpleSAMLphp authentication is NOT yet activated.");
		taxonomy.NavigateSite("Home");
		taxonomy.NavigateSite("Modules");
		overlay.SwitchToActiveFrame();
		modules.EnterFilterName("Pub SSO");
		modules.DisableModule("Pub SSO");
		modules.EnterFilterName("simpleSAMLphp authentication");
		Thread.sleep(1000);
		modules.DisableModule("simpleSAMLphp authentication");
		overlay.ClickCloseOverlayLnk();
		taxonomy.NavigateSite("Home>>Flush all caches");
		    
		testSuccessful = true;
		
	}
	 
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive"}, dependsOnMethods = {"SSOManageUsers_TC3853"}, alwaysRun=true)
		public void Cleanup() throws Exception {
			if (testSuccessful == false) {
				
				UserLogin userLogin = new UserLogin(webDriver);
				SimpleSAML simpleSAML = new SimpleSAML(webDriver);
				Modules modules = new Modules(webDriver);
				
				applib.openSitePage("/user");
				userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
				applib.openSitePage("/admin/config/people/simplesamlphp_auth");
				simpleSAML.UnCheckActivateAuthCbx();
				simpleSAML.ClickSaveConfigurationBtn();
				contentParent.VerifyMessageStatus("The configuration options have been saved.");
				contentParent.VerifyMessageStatus("SimpleSAMLphp authentication is NOT yet activated.");
				taxonomy.NavigateSite("Home");
				taxonomy.NavigateSite("Modules");
				overlay.SwitchToActiveFrame();
				modules.EnterFilterName("Pub SSO");
				modules.DisableModule("Pub SSO");
				modules.EnterFilterName("simpleSAMLphp authentication");
				Thread.sleep(1000);
				modules.DisableModule("simpleSAMLphp authentication");
				overlay.ClickCloseOverlayLnk();
			}
		}
}
