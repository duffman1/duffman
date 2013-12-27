package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SocialIntegration.Facebook;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.AccessDenied;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Facebook.DrupalForFacebook;
import com.nbcuni.test.publisher.pageobjects.Facebook.NodeTypes;
import com.nbcuni.test.publisher.pageobjects.Facebook.Share;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.CastCrew;
import com.nbcuni.test.publisher.pageobjects.content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.content.RevisionState;
import com.nbcuni.test.publisher.pageobjects.content.Revisions;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.errorchecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.queues.DeleteQueue;
import com.nbcuni.test.publisher.pageobjects.queues.Queues;
import com.nbcuni.test.publisher.pageobjects.queues.QueuesRevisionList;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class TestingFacebookAutopublishing extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into the test site as Drupal User 1 (usually admin in Publisher sites)<br>
     * Step 1a - Enable "Pub Social" module if necessary<br>
     * Step 2 - Navigate to Structure > Facebook Apps > Add App, enter p7 for the label, and specify the credentials  Facebook App Name: Publisher Seven Test App Facebook App ID: 125235334322205 (Facebook App) Secret: a9b07339789b4ea75b3951f81fe27def  and then click Save.  Note 1: There is no Facebook App Name field in form. Note 2: We're leaving Enabled checked. Note 3: We're leaving Set application properties automatically checked. Note 4: You cannot create a second Facebook app with the same label<br>
     * Step 3 - Go to Structure > Facebook Apps > Stream Posts, and then in the Access Token Generator section, click post via the Publisher Seven Test App application.,a  The Drupal for Facebook page is displayed. b  The Facebook application authorization dialog is displayed.  Note: The Facebook application authorization dialog was not displayed. Apparently, the test instance was sufficiently prefconfigured that some events described by the developer in the QA Handoff did not occur. If this is encountered during configuration for sharing testing, mark this and the next steps as Passd and then
     * Step 4 - In the Facebook application authorization dialog, click OK.,a  The Facebook application authorization dialog closes. b  The test site page is redisplayed with a token populate to Access Field.
     * Step 5 - Click Save configuration<br>
     * Step 6 - On the Stream posts tab, click Show current token<br>
     * Step 7 - Copy & paste this token into the Past Access Token field<br>
     * Step 8 - [This step in doubt.] Go to Configuration > Web Services > Facebook and select a node type to allow sharing,This step moved to test 2737, "Testing Facebook autopublishing," but left here for completeness. Pass this step.
     * Step 9 - Create a new node type of that kind, publish it and go to the revisions tab. You should see a share link on the revisions page (it must be published). ,This step moved to test 2737, "Testing Facebook autopublishing," but left here for completeness. Pass this step.
     * Step 10 - Share the revision to Facebook. You should receive a confirmation link. ,This step moved to test 2737, "Testing Facebook autopublishing," but left here for completeness. Pass this step.
     * 
     * 
     * 
     * Step 2 - Navigate to Configuration > Web Services > Facebook and select a node type to allow sharing<br>
     * Step 3 - Create and publish a new Post node, display its Revisions tab, and inspect the tab for the presence of a Share link.  Note: If Twitter sharing is already configured on the test site, the Share node will be be present whether or not Facebook sharing enabled.,A Share link is present in the Revisions tab.
     * Step 4 - Click Share<br> 
     * Step 5 - Click the Facebook tab, and then click (check) the Post to Facebook wall box<br>
     * Step 6 - In the Provide a brief message box, type message text, and then click Share. {{EXPECTED}} The Revisions tab is redisplayed, with a success message at its top. b  The success message includes a Facebook link to the new post<br> 
     * Step 7 - Click the Facebook link the success message, log into Facebook if necessary, and inspect the test account Wall for the presence of test post. {{EXPECTED}} The test post and its cover image are displayed on the Wall<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full"})
    public void TestingFacebookAutopublishing() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 1a
        Taxonomy taxonomy = new Taxonomy(webDriver);
        taxonomy.ClickTier1ModulesLnk();
        Overlay overlay = new Overlay(webDriver);
        overlay.SwitchToModulesFrm();
        Modules modules = new Modules(webDriver);
        modules.EnterFilterName("Pub Social");
        modules.EnableModule("Pub Social");
        modules.EnterFilterName("Pub Post");
        modules.EnableModule("Pub Post");
        overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent();
        
        //Step 2
        taxonomy.ClickTier1StructureTier2FacebookAppsTier3AddAppLnk();
        overlay.SwitchToDrupalForFacebookFrm();
        DrupalForFacebook drupalForFacebook = new DrupalForFacebook(webDriver);
        Random random = new Random();
        String label = random.GetCharacterString(15);
        drupalForFacebook.EnterLabel(label);
        drupalForFacebook.EnterFacebookAppId("125235334322205");
        drupalForFacebook.EnterSecret("a9b07339789b4ea75b3951f81fe27def");
        drupalForFacebook.ClickSaveBtn();
        ContentParent contentParent = new ContentParent(webDriver);
        contentParent.VerifyMessageStatus("Created facebook application Publisher 7 Test App (" + label + ").");
        overlay.switchToDefaultContent();
        
        //Step 3
        taxonomy.ClickTier1StructureTier2FacebookAppsTier3StreamPostsLnk();
        overlay.SwitchToDrupalForFacebookFrm();
        drupalForFacebook.ClickPostViaPub7Lnk();
        
        //Step 4 and 5 (truncated)
        drupalForFacebook.LoginToFacebook("publisher_rifassi_user@tfbnw.net", "pa55word");
        contentParent.VerifyMessageWarning("Generated a new access token, but not yet saved. Remember to press the save button below!");
        drupalForFacebook.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");
        
        //Step 6
        drupalForFacebook.ClickShowCurrentTokenLnk();
        
        //Step 7
        drupalForFacebook.EnterToken();
        
        //Step 8
        taxonomy.ClickTier1ConfigurationTier2WebservicesTier3FacebookLnk();
        overlay.SwitchToFacebookFrm();
        NodeTypes nodeTypes = new NodeTypes(webDriver);
        nodeTypes.EnablePostNode();
        overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent();
        taxonomy.ClickTier1ContentTier2AddContentTier3PostLnk();
        overlay.SwitchToCreatePostFrm();
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String postTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle);
        basicInformation.EnterSynopsis();
        overlay.switchToDefaultContent();
        overlay.SwitchToCreatePostFrm();
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver, applib);
        selectFile.SelectDefaultCoverImg();
        overlay.SwitchToCreatePostFrm();
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState("Published");
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        contentParent.ClickRevisionsBtn();
        overlay.SwitchToRevisionsFrm();
        
        //Step 4
        Revisions revisions = new Revisions(webDriver);
        revisions.ClickEditExtendMenuBtn(postTitle);
        revisions.ClickShareMenuBtn(postTitle);
        
        //Step 5
        Share share = new Share(webDriver);
        share.ClickPostToFacebookWallCbx();
        
        //Step 6
        String message = random.GetCharacterString(15);
        share.EnterBriefMessage(message);
        share.ClickShareBtn();
        Assert.fail("Test indicates there should be a success message with a link to facebook but no link is present.");
    }
}
