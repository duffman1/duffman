package com.nbcuni.test.publisher.tests.SocialIntegration.ImplementAutoPublishingToTwitter;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.MetaTags;

public class SeeIt extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1060
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441703091
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void SeeIt_TC1060() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
    	
    	//Step 2
    	Modules modules = new Modules(webDriver, applib);
    	modules.VerifyModuleEnabled("Metatag: Twitter Cards (See It)");
    	
    	//Step 3
    	taxonomy.NavigateSite("Content>>Add content>>Post");
        overlay.SwitchToActiveFrame();
        
        //Step 4
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String postTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        
        //Step 5
        MetaTags metaTags = new MetaTags(webDriver);
        metaTags.ClickMetaTagsLnk();
        metaTags.ExpandTwitterCardMenu();
        metaTags.EnterSeeItShowIDEpisodeID("9222635058369246112");
        metaTags.SelectSeeItIdSpace("See It (s)");
        metaTags.EnterSeeItCampaignID("fall");
        metaTags.EnterSeeItAssetID("mainpage");
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        
        //Step 6
        contentParent.VerifySourceInPage("<meta content=\"s\" name=\"twitter:seeit:idspace\">");
    	contentParent.VerifySourceInPage("<meta content=\"9222635058369246112\" name=\"twitter:seeit:showid\">");
    	contentParent.VerifySourceInPage("<meta content=\"fall\" name=\"twitter:seeit:campaignid\">");
    	contentParent.VerifySourceInPage("<meta content=\"mainpage\" name=\"twitter:seeit:assetid\">");
    	contentParent.VerifySourceInPage("<meta content=\"summary\" name=\"twitter:card\">");
    	
    	
    }
}
