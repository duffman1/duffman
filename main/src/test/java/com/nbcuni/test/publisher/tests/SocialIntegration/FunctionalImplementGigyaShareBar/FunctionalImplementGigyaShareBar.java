package com.nbcuni.test.publisher.tests.SocialIntegration.FunctionalImplementGigyaShareBar;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.GigyaSettings;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.GigyaShareBar;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;

public class FunctionalImplementGigyaShareBar extends ParentTest{
	
	/*************************************************************************************
     * TEST CASE - TC3978
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20523473657
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void FunctionalImplementGigyaShareBar_TC3978() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 2 (creates new post)
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        String postTitle = createDefaultContent.Post("Draft");
        
        //Step Setup
        Modules modules = new Modules(webDriver);
        modules.VerifyModuleEnabled("Pub Gigya");
        
        //Step 3
        navigation.Configuration("Gigya settings");
        
        //Step 4
        WorkBench workBench = new WorkBench(webDriver);
        workBench.ClickWorkBenchTab("Share");
        GigyaSettings gigyaSettings = new GigyaSettings(webDriver);
        gigyaSettings.EnterProviders("Tumblr, email, googleplus-interactive ,foursquare, print, twitter-tweet, facebook-like");
        gigyaSettings.ClickGigyaAdvancedShareBarSettingsLnk();
        gigyaSettings.EnterAdvancedShowShareBarUISettings("wrap|true");
        gigyaSettings.ClickSaveConfiguration_Btn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");
        
        //Step 5
        navigation.Content();
        SearchFor searchFor = new SearchFor(webDriver);
        searchFor.EnterTitle(postTitle);
        searchFor.ClickApplyBtn();
        searchFor.ClickSearchTitleLnk(postTitle);
        GigyaShareBar gigyaShareBar = new GigyaShareBar(webDriver, applib);
        gigyaShareBar.VerifyTumblrBtnPresent();
        gigyaShareBar.VerifyEmailBtnPresent();
        gigyaShareBar.VerifyGooglePlusBtnPresent();
        gigyaShareBar.VerifyFoursquareBtnPresent();
        gigyaShareBar.VerifyPrintBtnPresent();
        gigyaShareBar.VerifyTwitterBtnPresent();
        
        //Step 6 - N/A
        
    }
}
