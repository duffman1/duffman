package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SSO;

import java.util.Arrays;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.SSOLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.SimpleSAML;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;
import com.nbcuni.test.publisher.pageobjects.People.People;

public class SSOAuthenticationModes extends ParentTest {

	/*************************************************************************************
	 * TEST CASE - TC6519
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/27701038265
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive"})
	 public void SSOAuthenticationModes_TC6519() throws Exception {
		 
		UserLogin userLogin = applib.openApplication(); 
		Modules modules = new Modules(webDriver);
		SimpleSAML simpleSAML = new SimpleSAML(webDriver);
		Logout logout = new Logout(webDriver);
		WorkBench workBench = new WorkBench(webDriver);
		AddUser addUser = new AddUser(webDriver);
		SSOLogin ssoLogin = new SSOLogin(webDriver);
		
		Reporter.log("STEP 1");
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
		Reporter.log("STEP 2");
		navigation.People();
    	People people = new People(webDriver);
    	people.ClickAddUserLnk();
		addUser.EnterUsername("206448849");
		addUser.EnterEmailAddress("publisher_testuser1@testmail.com");
		addUser.EnterPassword("pa55word");
		addUser.EnterConfirmPassword("pa55word");
		addUser.CheckRoleCbx(Arrays.asList("senior editor"));
		addUser.EnterFirstName("Test");
		addUser.EnterLastName("User1");
		addUser.ClickCreateNewAccountBtn();
		
		addUser.EnterUsername("206448856");
		addUser.EnterEmailAddress("publisher_testuser2@testmail.com");
		addUser.EnterPassword("pa55word");
		addUser.EnterConfirmPassword("pa55word");
		addUser.CheckRoleCbx(Arrays.asList("editor"));
		addUser.EnterFirstName("Test");
		addUser.EnterLastName("User1");
		addUser.ClickCreateNewAccountBtn();
		
		addUser.EnterUsername("206448858");
		addUser.EnterEmailAddress("publisher_testuser3@testmail.com");
		addUser.EnterPassword("pa55word");
		addUser.EnterConfirmPassword("pa55word");
		addUser.CheckRoleCbx(Arrays.asList("administrator"));
		addUser.EnterFirstName("Test");
		addUser.EnterLastName("User1");
		addUser.ClickCreateNewAccountBtn();
		
		Reporter.log("STEP 3");
		modules.VerifyModuleEnabled("Pub SSO");
		
		Reporter.log("STEP 4");
		navigation.Configuration("SimpleSAMLphp Auth Settings");
		simpleSAML.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "stage.crt");
		
		Reporter.log("STEP 5");
		simpleSAML.CheckRolesAuthenticateSSO(Arrays.asList("Editor", "Senior Editor"));
		simpleSAML.UnCheckRolesAuthenticateSSO(Arrays.asList("Administrator"));
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
		logout.ClickLogoutBtn();
		applib.openSitePage("/user");
		userLogin.VerifyFederatedLoginLnkPresent();
		
		Reporter.log("STEP 8");
		for (String username : Arrays.asList("publisher_testuser1@testmail.com", "publisher_testuser2@testmail.com")) {
			userLogin.EnterEmailAddress(username);
			userLogin.EnterPassword("pa55word");
			userLogin.ClickLoginBtn();
			contentParent.VerifyMessageError("Please login with your SSO. Click here to be redirected to the SSO login page.");
		}
		
		Reporter.log("STEP 9");
		ssoLogin.ClickClickHereLnk();
		Thread.sleep(1000);
		ssoLogin.EnterSSOID("206448849");
		ssoLogin.EnterPassword("pa55word");
		ssoLogin.ClickSignInBtn();
		applib.refreshPage();
		contentParent.VerifyPageContentPresent(Arrays.asList("206448849"));
		
		Reporter.log("STEP 9");
		Thread.sleep(1000);
		workBench.ClickWorkBenchTab("Edit");
		
		Reporter.log("STEP 10");
		String parentWindow = webDriver.getWindowHandle();
		addUser.ClickChangeYourPasswordLnk();
	    applib.switchToNewWindow(parentWindow);
		ssoLogin.VerifySSOPasswordReset();
		
		Reporter.log("STEP 11");
		applib.switchToParentWindow(parentWindow);
		logout.ClickLogoutBtn();
		applib.openSitePage("/user");
		userLogin.EnterEmailAddress("publisher_testuser3@testmail.com");
		userLogin.EnterPassword("pa55word");
		userLogin.ClickLoginBtn();
		workBench.ClickWorkBenchTab("Edit");
		addUser.VerifyPasswordEnabled();
		
		Reporter.log("CLEANUP");
		navigation.Configuration("SimpleSAMLphp Auth Settings");
		simpleSAML.UnCheckRolesAuthenticateSSO(Arrays.asList("Administrator", "Editor", "Senior Editor"));
		simpleSAML.UnCheckActivateAuthCbx();
		simpleSAML.ClickSaveConfigurationBtn();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		contentParent.VerifyMessageStatus("SimpleSAMLphp auth is not yet activated.");
		navigation.Modules();
		modules.DisableModule("Pub SSO");
		modules.DisableModule("simpleSAMLphp authentication");
		navigation.Home();
		logout.ClickLogoutBtn();
		applib.openSitePage("/user");
		userLogin.VerifyFederatedLoginLnkNotPresent();
		userLogin.EnterEmailAddress("publisher_testuser1@testmail.com");
		userLogin.EnterPassword("pa55word");
		userLogin.ClickLoginBtn();
		workBench.ClickWorkBenchTab("Edit");
		logout.ClickLogoutBtn();
		
		
	}
	 
}
