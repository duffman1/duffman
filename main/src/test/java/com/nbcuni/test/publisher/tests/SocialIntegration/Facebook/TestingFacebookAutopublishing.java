package com.nbcuni.test.publisher.tests.SocialIntegration.Facebook;


import junit.framework.Assert;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Facebook.DrupalForFacebook;
import com.nbcuni.test.publisher.pageobjects.Facebook.NodeTypes;
import com.nbcuni.test.publisher.pageobjects.Facebook.Share;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.content.Revisions;


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
        taxonomy.NavigateSite("Modules");
        overlay.SwitchToFrame("Modules");
        Modules modules = new Modules(webDriver);
        modules.EnterFilterName("Pub Social");
        modules.EnableModule("Pub Social");
        modules.EnterFilterName("Pub Post");
        modules.EnableModule("Pub Post");
        overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent();
        
        //Step 2
        taxonomy.NavigateSite("Structure>>Facebook Apps>>Add App");
        overlay.SwitchToFrame("Drupal for Facebook");
        DrupalForFacebook drupalForFacebook = new DrupalForFacebook(webDriver);
        String label = random.GetCharacterString(15);
        drupalForFacebook.EnterLabel(label);
        drupalForFacebook.EnterFacebookAppId("125235334322205");
        drupalForFacebook.EnterSecret("a9b07339789b4ea75b3951f81fe27def");
        drupalForFacebook.ClickSaveBtn();
        ContentParent contentParent = new ContentParent(webDriver);
        contentParent.VerifyMessageStatus("Created facebook application Publisher 7 Test App (" + label + ").");
        overlay.switchToDefaultContent();
        
        //Step 3
        taxonomy.NavigateSite("Structure>>Facebook Apps>>Stream Posts");
        overlay.SwitchToFrame("Drupal for Facebook");
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
        taxonomy.NavigateSite("Configuration>>Web services>>Facebook");
        overlay.SwitchToFrame("Facebook");
        NodeTypes nodeTypes = new NodeTypes(webDriver);
        nodeTypes.EnablePostNode();
        overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent();
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle = createDefaultContent.Post("Published");
        contentParent.ClickRevisionsTab();
        overlay.SwitchToFrame("Revisions");
        
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
