package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SSO;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Configuration.FlushCache;
import com.nbcuni.test.publisher.pageobjects.Configuration.SSOLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.SimpleSAML;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;
import com.nbcuni.test.publisher.pageobjects.People.People;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;

public class SSOUseProduction extends ParentTest {

	/*************************************************************************************
	 * TEST CASE - TC6316
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/26686785732
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive"})
	 public void SSOUseProduction_TC6316() throws Exception {
		 
		UserLogin userLogin = applib.openApplication(); 
		Modules modules = new Modules(webWebWebDriver);
		SimpleSAML simpleSAML = new SimpleSAML(webWebWebDriver);
		Logout logout = new Logout(webWebWebDriver);
		FlushCache flushCache = new FlushCache(webWebWebDriver);
		AddUser addUser = new AddUser(webWebWebDriver);
		SSOLogin ssoLogin = new SSOLogin(webWebWebDriver);
		
		Reporter.log("STEP 1");
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
		Reporter.log("STEP 2");
		navigation.People();
    	People people = new People(webWebWebDriver);
    	people.ClickAddUserLnk();
		addUser.EnterUsername("206448849");
		addUser.EnterEmailAddress("publisher_testuser1@testmail.com");
		addUser.EnterPassword("pa55word");
		addUser.EnterConfirmPassword("pa55word");
		addUser.CheckRoleCbx(Arrays.asList("administrator", "editor"));
		addUser.EnterFirstName("Test");
		addUser.EnterLastName("User1");
		addUser.ClickCreateNewAccountBtn();
		
		Reporter.log("STEP 3");
		modules.VerifyModuleEnabled("Pub SSO");
		
		Reporter.log("STEP 4");
		navigation.Configuration("SimpleSAMLphp Auth Settings");
		simpleSAML.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "prod.crt");
		simpleSAML.CheckRolesAuthenticateSSO(Arrays.asList("Administrator", "Editor", "Senior Editor"));
		simpleSAML.UnCheckRolesAuthenticateSSO(Arrays.asList("Authenticated User"));
		
		Reporter.log("STEP 5");
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		contentParent.VerifyPageContentPresent(Arrays.asList("A certificate exists for this instance of SimpleSAMLphp."));
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		
		Reporter.log("STEP 6");
		simpleSAML.CheckActivateAuthCbx();
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		
		Reporter.log("STEP 7");
		flushCache.FlushAllCache();
		
		Reporter.log("STEP 8");
		logout.ClickLogoutBtn();
		applib.openSitePage("/user");
		userLogin.EnterEmailAddress("publisher_testuser1@testmail.com");
		userLogin.EnterPassword("pa55word");
		userLogin.ClickLoginBtn();
		Thread.sleep(1000);
		ssoLogin.EnterSSOID("206448849");
		ssoLogin.EnterPassword("pa55word");
		ssoLogin.ClickSignInBtn();
		applib.refreshPage();
		contentParent.VerifyPageContentPresent(Arrays.asList("206448849"));
		
		Reporter.log("STEP 9");
		logout.ClickLogoutBtn();
		applib.openSitePage("/user");
		userLogin.EnterEmailAddress("publisher_testuser1@testmail.com");
		userLogin.EnterPassword("pa55word");
		userLogin.ClickLoginBtn();
		Thread.sleep(1000);
		ssoLogin.EnterSSOID("206448849");
		ssoLogin.EnterPassword("pa55word");
		ssoLogin.ClickSignInBtn();
		applib.refreshPage();
		contentParent.VerifyPageContentPresent(Arrays.asList("206448849"));
		applib.openSitePage("/admin/config/people/simplesamlphp_auth");
		simpleSAML.UnCheckActivateAuthCbx();
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		contentParent.VerifyMessageStatus("SimpleSAMLphp authentication is NOT yet activated.");
		navigation.Modules();
		modules.DisableModule("Pub SSO");
		modules.DisableModule("simpleSAMLphp authentication");
		 
	}
	 
}
