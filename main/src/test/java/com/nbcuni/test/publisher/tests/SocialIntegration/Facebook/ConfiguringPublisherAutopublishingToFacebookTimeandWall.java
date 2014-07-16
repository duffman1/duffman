package com.nbcuni.test.publisher.tests.SocialIntegration.Facebook;

import java.net.URL;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Facebook.DrupalForFacebook;
import com.nbcuni.test.publisher.pageobjects.Facebook.NodeTypes;
import com.nbcuni.test.publisher.pageobjects.Facebook.Share;

public class ConfiguringPublisherAutopublishingToFacebookTimeandWall extends ParentTest{
	
	/*************************************************************************************
     * TEST CASE - TC3976
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20522666960
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void ConfiguringPublisherAutopublishingToFacebookTimeandWall_TC3976() throws Exception{
    	
    	//Step 1 - NA as test logs into facebook as part of a later step
    	
    	//Step 2
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 3
        taxonomy.NavigateSite("Modules");
        overlay.SwitchToActiveFrame();
        Modules modules = new Modules(webDriver, applib);
        modules.EnterFilterName("Pub Social");
        modules.EnableModule("Pub Social");
        overlay.ClickCloseOverlayLnk();
        
        //Step 3a
        taxonomy.NavigateSite("Structure>>Facebook Apps");
        overlay.SwitchToActiveFrame();
        DrupalForFacebook drupalForFacebook = new DrupalForFacebook(webDriver, applib);
        Delete delete = new Delete(webDriver);
        ContentParent contentParent = new ContentParent(webDriver, applib);
        boolean appAlreadyExists = drupalForFacebook.FacebookAppExists();
        if (appAlreadyExists == true) {
        	drupalForFacebook.ClickEditLnk();
        	overlay.SwitchToActiveFrame();
        	delete.ClickDeleteBtn();
        	overlay.switchToDefaultContent();
        	delete.ClickDeleteBtn();
        	delete.ClickDeleteBtn();
        }
        else {
        	overlay.ClickCloseOverlayLnk();
        }
        
        //Step 4
        taxonomy.NavigateSite("Structure>>Facebook Apps>>Add App");
        if (appAlreadyExists == false) {
        	overlay.SwitchToFrame("Drupal for Facebook");
        }
        String label = random.GetCharacterString(15);
        drupalForFacebook.EnterLabel(label);
        drupalForFacebook.EnterFacebookAppId("125235334322205");
        drupalForFacebook.EnterSecret("a9b07339789b4ea75b3951f81fe27def");
        drupalForFacebook.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Created facebook application Publisher 7 Test App (" + label + ").");
        overlay.switchToDefaultContent();
        
        //Step 5
        taxonomy.NavigateSite("Structure>>Facebook Apps>>Stream Posts");
        if (appAlreadyExists == false) {
        	overlay.SwitchToActiveFrame();
        }
        drupalForFacebook.ClickPostViaPub7Lnk();
        
        //Step 6
        drupalForFacebook.LoginToFacebook("publisher_rifassi_user@tfbnw.net", "pa55word");
        contentParent.VerifyMessageWarning("Generated a new access token, but not yet saved. Remember to press the save button below!");
        
        //Step 7
        drupalForFacebook.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");
        
        //Step 8
        drupalForFacebook.ClickShowCurrentTokenLnk();
        
        //Step 9
        drupalForFacebook.EnterToken();
        drupalForFacebook.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");
        
        //Step 10
        taxonomy.NavigateSite("Configuration>>Web services>>Facebook");
        NodeTypes nodeTypes = new NodeTypes(webDriver, applib);
        nodeTypes.EnablePostNode();
        
        //Step 11
        taxonomy.NavigateSite("Content>>Add content>>Post");
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String postTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle);
        basicInformation.EnterSynopsis();
        overlay.switchToDefaultContent();
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState("Published");
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        WorkBench workBench = new WorkBench(webDriver, applib);
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToActiveFrame();
        
        //Step 12
        Revisions revisions = new Revisions(webDriver, applib);
        revisions.ClickEditExtendMenuBtn(postTitle);
        revisions.ClickShareMenuBtn(postTitle);
        Share share = new Share(webDriver, applib);
        share.ClickFacebookLnk();
        share.ClickPostToFacebookWallCbx();
        String message = random.GetCharacterString(15);
        share.EnterBriefMessage(message);
        share.ClickShareBtn();
        
        //Step 14
        webDriver.navigate().to(new URL("https://www.facebook.com/"));
        overlay.switchToDefaultContent();
        for (int I = 0 ; ; I++) {
        	if (I >= 10) {
        		Assert.fail("Facebook app post has not posted to facebook.");
        	}
        	boolean postUpdatePresent = false;
            
        	try {
        		contentParent.VerifyPageContentPresent(Arrays.asList("Publisher Seven Test User", 
                		postTitle, "Publisher 7 Test App"));
        		postUpdatePresent = true;
        	}
        	catch (AssertionError e) {
        		postUpdatePresent = false;
        	}
        	if (postUpdatePresent == true) { break; }
        	else {
        		Thread.sleep(1000);
        		webDriver.navigate().refresh();
        	}
        }
        
    }
}
