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
            mpsConfiguration.EnterSiteInstanceOverride("nbc-tonightshow");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            overlay.ClickCloseOverlayLnk();
            contentParent.VerifySourceInPage(Arrays.asList("var mpscall = {\"site\":\"nbc-tonightshow\",", "var mpsopts = {'host':'mps.nbcuni.com'}"));
            
            Reporter.log("STEP 5");
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.ClickIntegrationMethod("DOM Injection");
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
            contentParent.VerifySourceInPage(Arrays.asList("mps-load", "//mps.nbcuni.com/fetch/ext/load-nbc-tonightshow.js?nowrite=jq"));
            
            Reporter.log("STEP 9 - N/A");
            
            Reporter.log("CLEANUP");
            taxonomy.NavigateSite("Configuration>>Web services>>MPS Configuration");
            overlay.SwitchToActiveFrame();
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            
    }
}
