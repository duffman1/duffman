package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SSO;

import java.util.Arrays;
import java.util.Set;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

	/*************************************************************************************
	 * TEST CASE - TC3853
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/20332795162
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	 public void SSOManageUsers_TC3853() throws Exception {
		 
		UserLogin userLogin = new UserLogin(webDriver); 
		Modules modules = new Modules(webDriver, applib);
		SimpleSAML simpleSAML = new SimpleSAML(webDriver);
		Logout logout = new Logout(webDriver);
		Set<String> allWindows = null;
		String parentWindow = null;
		
		try {
			Reporter.log("STEP 1");
			applib.openSitePage("/user");
			userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
		       
			Reporter.log("STEP 2");
			modules.VerifyModuleEnabled("Pub SSO");
			taxonomy.NavigateSite("Configuration>>People>>SimpleSAMLphp Auth Settings");
			overlay.SwitchToActiveFrame();
			simpleSAML.VerifyDefaultSettings();
			simpleSAML.EnterFilePath(config.getPathToMedia() + "stage.crt");
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
		    SSOLogin ssoLogin = new SSOLogin(webDriver);
		    ssoLogin.EnterSSOID("206424392");
		    ssoLogin.EnterPassword("tufNewcyd577BC");
		    ssoLogin.ClickSignInBtn();
		    webDriver.navigate().refresh();
		    new WebDriverWait(webDriver, 30).until(ExpectedConditions.titleContains("Site-Install"));
		    contentParent.VerifyPageContentNotPresent(Arrays.asList("Modules"));
		    contentParent.VerifyPageContentPresent(Arrays.asList("Brandon.Clark@nbcuni.com"));
		    	
		    Reporter.log("STEP 3");
		    WorkBench workBench = new WorkBench(webDriver, applib);
		    workBench.ClickWorkBenchTab("Edit");
		    overlay.SwitchToActiveFrame();
		    AddUser addUser = new AddUser(webDriver, applib);
		    addUser.VerifyUsernameValueAndIsDisabled("Brandon.Clark@nbcuni.com");
		    addUser.VerifyEmailAddressValueAndIsDisabled("Brandon.Clark@nbcuni.com");
		    
		    Reporter.log("STEP 4");
		    parentWindow = webDriver.getWindowHandle();
		    addUser.ClickChangeYourPasswordLnk();
	        allWindows = webDriver.getWindowHandles();  
	        for (String window : allWindows){
	           if (!window.equals(parentWindow)){
	             webDriver.switchTo().window(window);
	             break;
	           }
	        }
		    ssoLogin.VerifySSOPasswordReset();
		    
		    Reporter.log("STEP 5");
		    webDriver.close();
		    webDriver.switchTo().window(parentWindow);
		    overlay.SwitchToActiveFrame();
		    overlay.ClickCloseOverlayLnk();
		    logout.ClickLogoutBtn();
		    applib.openSitePage("/user");
		    userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
		    
		    Reporter.log("STEP 6");
		    String editorUserName = addUser.AddDefaultUser(Arrays.asList("editor"), true);
	        
	        Reporter.log("STEP 7");
		    taxonomy.NavigateSite("People");
		    overlay.SwitchToActiveFrame();
		    People people = new People(webDriver, applib);
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
		}
		catch (Exception | AssertionError e) {
			
			if (webDriver.getWindowHandles().size() > 1) {
				webDriver.close();
			    webDriver.switchTo().window(parentWindow);
			}
			
			try {
				logout.ClickLogoutBtn();
			}
			catch (Exception | AssertionError e2) {}
			
			try {
				applib.openSitePage("/user");
				userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
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
			catch (Exception | AssertionError e2) {}
			
	    
			Assert.fail(e.toString());
		}
		
	}
}
