package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SSO;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Configuration.SSOLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.SimpleSAML;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.People.People;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;

public class SSODefinedDomains extends GlobalBaseTest {

	Boolean testSuccessful = false;
	
	/*************************************************************************************
	 * TEST CASE - TC3474
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/19851154396
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive", "broken"})
	 public void SSODefinedDomains_TC3474() throws Exception {
		 
		UserLogin userLogin = new UserLogin(webDriver);
		Logout logout = new Logout(webDriver);
		SimpleSAML simpleSAML = new SimpleSAML(webDriver);
		Modules modules = new Modules(webDriver);
			
		Reporter.log("STEP 1");
		appLib.openSitePage("/user");
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
	       
		Reporter.log("STEP 2");
		modules.VerifyModuleEnabled("Pub SSO");
			
		Reporter.log("STEP 3");
		navigation.Configuration("SimpleSAMLphp Auth Settings");
			
		Reporter.log("STEP 4");
		simpleSAML.VerifyDefaultSettings();
			
		Reporter.log("STEP 5");
		simpleSAML.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "stage.crt");
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		contentParent.VerifyPageContentPresent(Arrays.asList("A certificate exists for this instance of SimpleSAMLphp."));
			
		Reporter.log("STEP 6");
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
	        
		Reporter.log("STEP 7");
		simpleSAML.CheckActivateAuthCbx();
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		    
		Reporter.log("STEP 8");
		logout.ClickLogoutBtn();
		appLib.openSitePage("/saml_login");
		Thread.sleep(1000);
	        
		Reporter.log("STEP 9");
		SSOLogin ssoLogin = new SSOLogin(webDriver);
		ssoLogin.EnterSSOID(config.getConfigValueString("SSOUsername"));
		ssoLogin.EnterPassword(config.getConfigValueString("SSOPassword"));
		ssoLogin.ClickSignInBtn();
	       
		Reporter.log("STEP 10 AND 11");
		appLib.refreshPage();
		contentParent.VerifyPageContentPresent(Arrays.asList(config.getConfigValueString("SSOUsername")));
		contentParent.VerifyPageContentNotPresent(Arrays.asList("Modules"));
			
		Reporter.log("STEP 12");
		logout.ClickLogoutBtn();
		appLib.openSitePage("/user");
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
		navigation.People();
		People people = new People(webDriver);
		people.SeachForUsername(config.getConfigValueString("SSOEmail"));
		people.ClickUsernameLnk(config.getConfigValueString("SSOEmail"));
	        
		Reporter.log("STEP 13");
		appLib.openSitePage("/admin/config/people/simplesamlphp_auth");
		simpleSAML.UnCheckActivateAuthCbx();
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		contentParent.VerifyMessageStatus("SimpleSAMLphp authentication is NOT yet activated.");
	        
		Reporter.log("STEP 14 - 16 TODO");
	        
		Reporter.log("STEP 17");
		navigation.Modules();
		modules.DisableModule("Pub SSO");
		modules.DisableModule("simpleSAMLphp authentication");
		
		testSuccessful = true;
		
	}
	 
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive", "broken"}, dependsOnMethods = {"SSODefinedDomains_TC3474"}, alwaysRun=true)
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
			modules.DisableModule("simpleSAMLphp authentication");
			
		}
	}
}
