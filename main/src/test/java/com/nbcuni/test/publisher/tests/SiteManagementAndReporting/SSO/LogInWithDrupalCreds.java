package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SSO;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Configuration.FlushCache;
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

public class LogInWithDrupalCreds extends GlobalBaseTest {

	/*************************************************************************************
	 * TEST CASE - TC6012
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/25893188851
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive"})
	 public void LogInWithDrupalCreds_TC6012() throws Exception {
		 
		UserLogin userLogin = appLib.openApplication();
		Modules modules = new Modules(webDriver);
		SimpleSAML simpleSAML = new SimpleSAML(webDriver);
		Logout logout = new Logout(webDriver);
		FlushCache flushCache = new FlushCache(webDriver);
		WorkBench workBench = new WorkBench(webDriver);
		AddUser addUser = new AddUser(webDriver);
		SSOLogin ssoLogin = new SSOLogin(webDriver);
		
		Reporter.log("STEP 1");
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
		Reporter.log("STEP 2");
		String userName1 = addUser.AddDefaultUser(Arrays.asList("authenticated user"), false);
    	navigation.People();
    	People people = new People(webDriver);
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
		simpleSAML.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "stage.crt");
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
		appLib.openSitePage("/user");
		userLogin.EnterEmailAddress("publisher_testuser1@testmail.com");
		userLogin.EnterPassword("pa55word");
		userLogin.ClickLoginBtn();
		Thread.sleep(1000);
		ssoLogin.EnterSSOID("206448849");
		ssoLogin.EnterPassword("pa55word");
		ssoLogin.ClickSignInBtn();
		appLib.refreshPage();
		contentParent.VerifyPageContentPresent(Arrays.asList("206448849"));
		
		Reporter.log("STEP 9");
		Thread.sleep(1000);
		workBench.ClickWorkBenchTab("Edit");
		addUser.VerifyRoleCbxPresentAndEnabled(Arrays.asList("administrator", "editor"));
		
		Reporter.log("STEP 10");
		logout.ClickLogoutBtn();
		userLogin.EnterEmailAddress(userName1);
		userLogin.EnterPassword("pa55word");
		userLogin.ClickLoginBtn();
		
		Reporter.log("STEP 11");
		appLib.openSitePage("/user");
		workBench.ClickWorkBenchTab("Edit");
		
		Reporter.log("STEP 12");
		addUser.VerifyRoleCbxNotPresent(Arrays.asList("administrator", "editor"));
		
		//TODO - a few additional steps as time allows
		Reporter.log("STEP 15");
		logout.ClickLogoutBtn();
		appLib.openSitePage("/user");
		userLogin.EnterEmailAddress("publisher_testuser1@testmail.com");
		userLogin.EnterPassword("pa55word");
		userLogin.ClickLoginBtn();
		Thread.sleep(1000);
		ssoLogin.EnterSSOID("206448849");
		ssoLogin.EnterPassword("pa55word");
		ssoLogin.ClickSignInBtn();
		appLib.refreshPage();
		contentParent.VerifyPageContentPresent(Arrays.asList("206448849"));
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
