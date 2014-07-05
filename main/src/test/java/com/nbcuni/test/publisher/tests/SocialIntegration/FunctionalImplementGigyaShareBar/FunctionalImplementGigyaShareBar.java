package com.nbcuni.test.publisher.tests.SocialIntegration.FunctionalImplementGigyaShareBar;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.GigyaSettings;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.GigyaShareBar;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;

public class FunctionalImplementGigyaShareBar extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE
     * Step 1 - Login to P7 using Admin (user 1) credentials ,Login Successful 
	 * Step 2 - Go to Content -> Add Content-> Add a Content  ,Content is added successfully 
	 * Step 3 - Go to Configuration-> Webservices -> Gigya Settings  ,Gigya settings overlay appears with global settings view 
     * Step 4 - Click on 'SHARE' link                                                                              
     * 		1) Under Providers field fill in all providers name like Tumblr, email, googleplus-interactive,foursquare, twitter-tweet,facebook-like,facebook-send.
     * 		2) Expand  "Advanced share bar settings" link,Verify that 'Advance showShareBarUI settings' is filled with 'wrap|true'.                                                                                
     * Step 5 - Go to content and click on the content title created in step number 2.  Verify that the social provider items  set in configuration above in step 4 are seen in Gigya share bar are adjusting to the size of the content container so that they don't break the container when theirs long list of Providers. ,List of providers are seen to be reponsive as expected. 
     * Step 6 - Log out from P7 ,Logout Successful  
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void FunctionalImplementGigyaShareBar_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2 (creates new post)
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle = createDefaultContent.Post("Draft");
        
        //Step Setup
        Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Pub Gigya");
        
        //Step 3
        taxonomy.NavigateSite("Configuration>>Web services>>Gigya settings");
        overlay.SwitchToActiveFrame();
        
        //Step 4
        WorkBench workBench = new WorkBench(webDriver, applib);
        workBench.ClickWorkBenchTab("Share");
        overlay.SwitchToActiveFrame();
        GigyaSettings gigyaSettings = new GigyaSettings(webDriver, applib);
        gigyaSettings.EnterProviders("Tumblr, email, googleplus-interactive ,foursquare, print, twitter-tweet, facebook-like");
        gigyaSettings.ClickGigyaAdvancedShareBarSettingsLnk();
        gigyaSettings.EnterAdvancedShowShareBarUISettings("wrap|true");
        gigyaSettings.ClickSaveConfiguration_Btn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");
        overlay.ClickCloseOverlayLnk();
        
        //Step 5
        taxonomy.NavigateSite("Content");
        overlay.SwitchToActiveFrame();
        SearchFor searchFor = new SearchFor(webDriver, applib);
        searchFor.EnterTitle(postTitle);
        searchFor.ClickApplyBtn();
        overlay.switchToDefaultContent();
        searchFor.ClickSearchTitleLnk(postTitle);
        GigyaShareBar gigyaShareBar = new GigyaShareBar(webDriver, applib);
        gigyaShareBar.VerifyTumblrBtnPresent();
        gigyaShareBar.VerifyEmailBtnPresent();
        gigyaShareBar.VerifyGooglePlusBtnPresent();
        gigyaShareBar.VerifyFoursquareBtnPresent();
        gigyaShareBar.VerifyPrintBtnPresent();
        gigyaShareBar.VerifyTwitterBtnPresent();
        
        //CLEANUP
        taxonomy.NavigateSite("Modules");
        overlay.SwitchToActiveFrame();
        modules.EnterFilterName("Pub Gigya");
        modules.DisableModule("Pub Gigya");
        
        //Step 6 - N/A
        
    }
}
