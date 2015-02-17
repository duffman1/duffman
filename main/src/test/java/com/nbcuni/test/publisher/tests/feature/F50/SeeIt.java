package com.nbcuni.test.publisher.tests.feature.F50;

import java.util.Arrays;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.MetaTags;

public class SeeIt extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1060
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441703091
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "F50"})
    public void SeeIt_TC1060() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
    	
    	//Step 2
    	navigation.Modules();
    	Modules modules = new Modules(webDriver);
    	modules.EnableModule("Metatag: Twitter Cards (See It)");
    	
    	//Step 3
    	navigation.AddContent("Post");
    	
        //Step 4
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String postTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle);
        basicInformation.EnterSynopsis();
        
        //Step 5
        MetaTags metaTags = new MetaTags(webDriver);
        metaTags.ClickMetaTagsLnk();
        metaTags.ExpandTwitterCardMenu();
        metaTags.EnterSeeItShowIDEpisodeID("9222635058369246112");
        metaTags.SelectSeeItIdSpace("See It (s)");
        metaTags.EnterSeeItCampaignID("fall");
        metaTags.EnterSeeItAssetID("mainpage");
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        
        //Step 6
        contentParent.VerifySourceInPage(Arrays.asList("twitter:seeit:idspace", "content=\"s\"", 
        		"twitter:seeit:showid", "content=\"9222635058369246112\"", "twitter:seeit:campaignid", 
        			"content=\"fall\"", "twitter:seeit:assetid", "content=\"mainpage\""));
        
    }
}
