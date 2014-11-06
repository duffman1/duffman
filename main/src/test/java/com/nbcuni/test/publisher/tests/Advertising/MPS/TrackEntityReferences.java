package com.nbcuni.test.publisher.tests.Advertising.MPS;

import java.util.Arrays;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.MPSConfiguration;
import com.nbcuni.test.publisher.pageobjects.Content.AdditionalInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;

public class TrackEntityReferences extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC5445
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/23568460386
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mps"})
    public void TrackEntityReferences_TC5445() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("SETUP");
        	Modules modules = new Modules(webDriver);
        	modules.VerifyModuleEnabled("MPS");
        	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        	String tvShowTitle = createDefaultContent.TVShow("Draft");
        	String tvSeasonTitle = createDefaultContent.TVSeason("Draft", tvShowTitle);
        	String tvEpisodeTitle = createDefaultContent.TVEpisode("Draft", tvShowTitle, tvSeasonTitle);
        	WorkBench workBench = new WorkBench(webDriver);
        	workBench.ClickWorkBenchTab("Edit Draft");
        	AdditionalInformation additionalInformation = new AdditionalInformation(webDriver);
        	additionalInformation.ClickAdditionalInformationLnk();
        	additionalInformation.EnterTag("tag1");
        	additionalInformation.EnterTag("tag2");
        	contentParent.ClickSaveBtn();
        	overlay.switchToDefaultContent(true);
        	
        	Reporter.log("STEP 2");
        	navigation.Configuration("MPS Configuration");
        	MPSConfiguration mpsConfiguration = new MPSConfiguration(webDriver);
            mpsConfiguration.EnterMPSHost("mps.io");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("pub7-development");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.CleanAllMPSOptions();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            
            Reporter.log("STEP 3");
            applib.openSitePage("/content/" + tvEpisodeTitle);
            contentParent.VerifySourceInPage(Arrays.asList("\"cag\":{\"tags\":\"tag1|tag2\",\"show\":\"" + tvShowTitle + "\",\"season\":\"" + tvSeasonTitle + "\"}"));
            
            
            
    }
}
