package com.nbcuni.test.publisher.tests.Advertising.MPS;

import java.util.Arrays;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.MPSConfiguration;

public class MPSConfigurationPage extends ParentTest {
	
	Boolean testSuccessful = false;
	
    /*************************************************************************************
     * TEST CASE - TC3306
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/19393095610
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mps"})
    public void MPSConfigurationPage_TC3306() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("SETUP");
        	navigation.Modules();
        	Modules modules = new Modules(webDriver);
        	modules.DisableModule("Pixelman");
        	modules.EnableModule("MPS");
        	
            Reporter.log("STEP 2 - 3");
            navigation.Configuration("MPS Configuration");
            
            Reporter.log("STEP 4");
            MPSConfiguration mpsConfiguration = new MPSConfiguration(webDriver);
            mpsConfiguration.CleanAllMPSOptions();
            mpsConfiguration.EnterMPSHost("mps.io");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("pub7-development");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            navigation.Home();
            mpsConfiguration.VerifyMPSCallParameters(Arrays.asList("\"site\":\"pub7-development\"", "\"title\":\"Welcome to", "\"path\":\"\\/\"", "\"is_content\":0", "\"type\":\"other\"", "var mpsopts = {\"host\":\"mps.io\"}"));
            
            Reporter.log("STEP 5");
            navigation.Configuration("MPS Configuration");
            mpsConfiguration.ClickIntegrationMethod("DOM Injection (Only select this method if JQuery is enabled).");
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            navigation.Home();
            contentParent.VerifySourceInPage(Arrays.asList("mpsload.src='//'+mpsopts.host+'/fetch/ext/load-'+mpscall.site+'.js?nowrite=jq'; mpsload.id=\"mps-load\"", "mpsload.async=false; head.insertBefore(mpsload,head.firstChild)"));
            
            Reporter.log("STEP 6");
            applib.openSitePage("/?x=y");
            mpsConfiguration.VerifyMPSCallParameters(Arrays.asList("\"title\":\"Welcome to", "\"path\":\"\\/\"", "\"qs\":\"eD15\""));
            
            Reporter.log("STEP 7");
            navigation.Configuration("MPS Configuration");
            mpsConfiguration.UnCheckSendQueryStringsCbx();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            navigation.Home();
            contentParent.VerifySourceNotInPage("Site-Install\",\"path\":\"\\/\",\"qs\":\"eD15\"");
            
            Reporter.log("STEP 8");
            navigation.Configuration("MPS Configuration");
            mpsConfiguration.EnterMPSHost("mps.io");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("pub7-development");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.EnterName("key1", "1");
            mpsConfiguration.EnterValue("value1", "1");
            mpsConfiguration.ClickAddAnotherOptBtn();
            mpsConfiguration.EnterName("key2", "2");
            mpsConfiguration.EnterValue("{\"key\":\"value\", \"key2\": \"value2\"}", "2");
            mpsConfiguration.CheckJSONCbx("2");
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            navigation.Home();
            contentParent.VerifySourceInPage(Arrays.asList("var mpsopts = {\"host\":\"mps.io\",\"key1\":\"value1\",\"key2\":{\"key\":\"value\",\"key2\":\"value2\"}}"));
            
            Reporter.log("STEP 9");
            navigation.Configuration("MPS Configuration");
            mpsConfiguration.EnterName("key3", "3");
            mpsConfiguration.EnterValue("jshdhdhdhjdhshd", "3");
            mpsConfiguration.CheckJSONCbx("3");
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageError("If the json checkbox is checked, this must be a valid json object.");
            
            Reporter.log("STEP 10");
            navigation.Home();
            contentParent.VerifySourceInPage(Arrays.asList("//mps.io/request/page/jsonp?CALLBACK=mpsCallback"));
            
            Reporter.log("STEP 11 - N/A");
            
            Reporter.log("CLEANUP");
            navigation.Configuration("MPS Configuration");
            mpsConfiguration.CleanAllMPSOptions();
            mpsConfiguration.ClickSaveConfigurationBtn();
            
            testSuccessful = true;
            
    }
    
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"}, dependsOnMethods = {"MPSConfigurationPage_TC3306"}, alwaysRun=true)
	public void Cleanup() throws Exception {
		if (testSuccessful == false) {
			
			UserLogin userLogin = applib.openApplication();
			userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
			navigation.Configuration("MPS Configuration");
            MPSConfiguration mpsConfiguration = new MPSConfiguration(webDriver);
            mpsConfiguration.CleanAllMPSOptions();
            mpsConfiguration.ClickSaveConfigurationBtn();
		}
	}
}
