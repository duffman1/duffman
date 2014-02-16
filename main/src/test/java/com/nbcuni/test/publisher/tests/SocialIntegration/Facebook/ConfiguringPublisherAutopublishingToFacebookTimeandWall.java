package com.nbcuni.test.publisher.tests.SocialIntegration.Facebook;

import java.net.URL;
import java.util.Arrays;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Facebook.DrupalForFacebook;
import com.nbcuni.test.publisher.pageobjects.Facebook.NodeTypes;
import com.nbcuni.test.publisher.pageobjects.Facebook.Share;

public class ConfiguringPublisherAutopublishingToFacebookTimeandWall extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to Facebook using the credentials  Facebook Username: Publisher Seven Test User Facebook User ID: 100005347650497 Facebook User Email: publisher_rifassi_user@tfbnw.net <mailto:publisher_rifassi_user@tfbnw.net> Facebook Password: standard password,Facebook login succeeds. 
     * Step 2 - In another tab of the test browser, log into the test site as Drupal User 1 (commonly admin in Publisher test sites).  Note: Testable code for this story was initially delivered to http://qa1prod.publisher/.,Login succeeds. 
     * Step 3 - Ensure that the Pub Social module is enabled.  Note: Enabling Pub Social also enables the necessary Facebook and Twitter modules. ,The Pub Social module is enabled. 
     * Step 3a - Delete any existing facebook apps that were previously configured<br>
     * Step 4 - Navigate to Structure > Facebook Apps > Add App, enter p7 for the label, and specify the credentials  Facebook App Name: Publisher Seven Test App Facebook App ID: 125235334322205 (Facebook App) Secret: a9b07339789b4ea75b3951f81fe27def  and then click Save.  Note 1: There is no Facebook App Name field in form. Note 2: We're leaving Enabled checked. Note 3: We're leaving Set application properties automatically checked. Note 4: You cannot create a second Facebook app with the same label. If the p7 apps has already been created, mark this step as Passed and go to the next step.,a  The Drupal for Facebook form is displayed. b  A success message is displayed.
     * Step 5 - Go to Structure > Facebook Apps > Stream Posts, and then in the Access Token Generator section, click post via the Publisher Seven Test App application.,a  The Drupal for Facebook page is displayed. b  The Facebook application authorization dialog is displayed.  Note: The Facebook application authorization dialog was not displayed. Apparently, the test instance was sufficiently prefconfigured that some events described by the developer in the QA Handoff did not occur. If this is encountered during configuration for sharing testing, mark this and the next steps as Passd and then
     * Step 6 - In the Facebook application authorization dialog, click OK.,a  The Facebook application authorization dialog closes. b  The test site page is redisplayed with a token populate to Access Field.
     * Step 7 - Click Save configuration. ,A success message is displayed.
     * Step 8 - On the Stream posts tab, click Show current token.,The current token value is displayed.  (For this, the Developer handoff wrote: "Below that you will see the token of the page (aka fan page).")
     * Step 9 - Copy & paste this token into the Past Access Token field.  Note: And yet we have already done Save configuration above. More work needed to streamline these steps; do we do Save configuration again? Or just ignore this step?,
     * Step 10 - [This step in doubt.] Go to Configuration > Web Services > Facebook and select a node type to allow sharing,This step moved to test 2737, "Testing Facebook autopublishing," but left here for completeness. Pass this step.
     * Step 11 - Create a new node type of that kind, publish it and go to the revisions tab. You should see a share link on the revisions page (it must be published). ,This step moved to test 2737, "Testing Facebook autopublishing," but left here for completeness. Pass this step.
     * Step 12 - Share the revision to Facebook. You should receive a confirmation link. ,This step moved to test 2737, "Testing Facebook autopublishing," but left here for completeness. Pass this step.
     * Step 13 - Click the Facebook link the success message, log into Facebook if necessary, and inspect the test account Wall for the presence of test post, The test post and its cover image are displayed on the Wall<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full"})
    public void ConfiguringPublisherAutopublishingToFacebookTimeandWall_Test() throws Exception{
    	
    	//Step 1 - NA as test logs into facebook as part of a later step
    	
    	//Step 2
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 3
        taxonomy.NavigateSite("Modules");
        overlay.SwitchToFrame("Modules");
        Modules modules = new Modules(webDriver, applib);
        modules.EnterFilterName("Pub Social");
        modules.EnableModule("Pub Social");
        modules.EnterFilterName("Pub Post");
        modules.EnableModule("Pub Post");
        overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent();
        
        //Step 3a
        taxonomy.NavigateSite("Structure>>Facebook Apps");
        overlay.SwitchToFrame("Drupal for Facebook");
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
            overlay.switchToDefaultContent();
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
        	overlay.SwitchToFrame("Drupal for Facebook");
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
        overlay.SwitchToFrame("Revisions");
        
        //Step 12
        Revisions revisions = new Revisions(webDriver, applib);
        revisions.ClickEditExtendMenuBtn(postTitle);
        revisions.ClickShareMenuBtn(postTitle);
        Share share = new Share(webDriver, applib);
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
                		postTitle, "via Publisher 7 Test App"));
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
