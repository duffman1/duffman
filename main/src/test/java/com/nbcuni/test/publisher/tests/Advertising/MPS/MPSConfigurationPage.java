package com.nbcuni.test.publisher.tests.Advertising.MPS;

import java.util.Arrays;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.MPSConfiguration;

public class MPSConfigurationPage extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC3306
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/19393095610
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void MPSConfigurationPage_TC3306() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	Reporter.log("SETUP");
        	Modules modules = new Modules(webDriver, applib);
            modules.VerifyModuleEnabled("MPS");
            
            Reporter.log("STEP 2 - 3");
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            
            Reporter.log("STEP 4");
            MPSConfiguration mpsConfiguration = new MPSConfiguration(webDriver);
            mpsConfiguration.EnterMPSHost("mps.nbcuni.com");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("sandbox");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            overlay.ClickCloseOverlayLnk();
            contentParent.VerifySourceInPage(Arrays.asList("var mpscall = {\"site\":\"sandbox\",\"title\":\"Welcome to Site-Install\",\"path\":\"\\/\",\"qs\":\"\",\"cat\":\"node\",\"content_id\":\"\",\"is_content\":0,\"type\":\"\",\"cag\":\"\",\"envelope\":\"\",\"pubdate\":\"\"}",
            		"var mpsopts = {\"host\":\"mps.nbcuni.com\"}", "var mps = mps ||"));
            
            Reporter.log("STEP 5");
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.ClickIntegrationMethod("DOM Injection (Only select this method if JQuery is enabled).");
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            overlay.ClickCloseOverlayLnk();
            contentParent.VerifySourceInPage(Arrays.asList("mpsload.src='//'+mpsopts.host+'/fetch/ext/load-'+mpscall.site+'.js?nowrite=jq'; mpsload.id=\"mps-load\"", "mpsload.async=false; head.insertBefore(mpsload,head.firstChild)"));
            
            Reporter.log("STEP 6");
            applib.openSitePage("/?x=y");
            contentParent.VerifySourceInPage(Arrays.asList("Site-Install\",\"path\":\"\\/\",\"qs\":\"eD15\""));
            
            Reporter.log("STEP 7");
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.UnCheckSendQueryStringsCbx();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            overlay.ClickCloseOverlayLnk();
            contentParent.VerifySourceNotInPage("Site-Install\",\"path\":\"\\/\",\"qs\":\"eD15\"");
            
            Reporter.log("STEP 8");
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.EnterMPSHost("mps.nbcuni.com");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("sandbox");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.EnterName("key1", "1");
            mpsConfiguration.EnterValue("value1", "1");
            mpsConfiguration.ClickAddAnotherOptBtn();
            mpsConfiguration.EnterName("key2", "2");
            mpsConfiguration.EnterValue("{\"key\":\"value\", \"key2\": \"value2\"}", "2");
            mpsConfiguration.CheckJSONCbx("2");
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            overlay.ClickCloseOverlayLnk();
            contentParent.VerifySourceInPage(Arrays.asList("var mpsopts = {\"host\":\"mps.nbcuni.com\",\"key1\":\"value1\",\"key2\":{\"key\":\"value\",\"key2\":\"value2\"}}"));
            
            Reporter.log("STEP 9");
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.EnterName("key3", "3");
            mpsConfiguration.EnterValue("jshdhdhdhjdhshd", "3");
            mpsConfiguration.CheckJSONCbx("3");
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageError("If the json checkbox is checked, this must be a valid json object.");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 10");
            contentParent.VerifySourceInPage(Arrays.asList("//mps.nbcuni.com/request/page/jsonp?callback=mpsCallback"));
            
            Reporter.log("STEP 11 - N/A");
            
            Reporter.log("CLEANUP");
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.EnterName("", "1");
            mpsConfiguration.EnterName("", "2");
            mpsConfiguration.EnterName("", "3");
            mpsConfiguration.EnterValue("", "1");
            mpsConfiguration.EnterValue("", "2");
            mpsConfiguration.EnterValue("", "3");
            mpsConfiguration.CheckJSONCbx("2");
            mpsConfiguration.ClickSaveConfigurationBtn();
            
            
    }
}
