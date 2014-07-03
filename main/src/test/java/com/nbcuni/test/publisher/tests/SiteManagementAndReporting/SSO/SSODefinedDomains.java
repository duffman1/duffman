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
import com.nbcuni.test.publisher.pageobjects.People.People;

public class SSODefinedDomains extends ParentTest {

	/*************************************************************************************
	 * TEST CASE - TC3474
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/19851154396
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	 public void SSODefinedDomains_TC3474() throws Exception {
		 
		Reporter.log("STEP 1");
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
       
		Reporter.log("STEP 2");
		Modules modules = new Modules(webDriver, applib);
		modules.VerifyModuleEnabled("Pub SSO");
		
		Reporter.log("STEP 3");
		taxonomy.NavigateSite("Configuration>>People>>SimpleSAMLphp Auth Settings");
		overlay.SwitchToActiveFrame();
		contentParent.VerifyMessageStatus("SimpleSAMLphp authentication is NOT yet activated.");
		
		Reporter.log("STEP 4");
		SimpleSAML simpleSAML = new SimpleSAML(webDriver);
		simpleSAML.VerifyDefaultSettings();
		
		Reporter.log("STEP 5");
		simpleSAML.EnterFilePath(config.getPathToMedia() + "stage.crt");
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
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 8");
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
        applib.openSitePage("/saml_login");
        
        Reporter.log("STEP 9");
        SSOLogin ssoLogin = new SSOLogin(webDriver);
        ssoLogin.EnterSSOID("206424392");
        ssoLogin.EnterPassword("tufNewcyd577BC");
        ssoLogin.ClickSignInBtn();
        
        Reporter.log("STEP 10");
        contentParent.VerifyPageContentNotPresent(Arrays.asList("Modules"));
        
        Reporter.log("STEP 11");
        contentParent.VerifyPageContentPresent(Arrays.asList("Brandon.Clark@nbcuni.com"));
        
        Reporter.log("STEP 12");
        logout.ClickLogoutBtn();
        applib.openSitePage("/user");
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        taxonomy.NavigateSite("People");
        overlay.SwitchToActiveFrame();
        People people = new People(webDriver);
        people.ClickUsernameLnk("Brandon.Clark");
        
        Reporter.log("STEP 13");
        applib.openSitePage("/admin/config/people/simplesamlphp_auth");
        simpleSAML.UnCheckActivateAuthCbx();
        simpleSAML.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");
        contentParent.VerifyMessageStatus("SimpleSAMLphp authentication is NOT yet activated.");
        
        Reporter.log("STEP 14 - 16 TODO");
        
        Reporter.log("STEP 17");
        taxonomy.NavigateSite("Modules");
        modules.EnterFilterName("Pub SSO");
        modules.DisableModule("Pub SSO");
        modules.EnterFilterName("simpleSAMLphp authentication");
        modules.DisableModule("simpleSAMLphp authentication");
        
        Reporter.log("STEP 18");
        taxonomy.NavigateSite("Home>>Flush all caches");
        
        
	}
}
