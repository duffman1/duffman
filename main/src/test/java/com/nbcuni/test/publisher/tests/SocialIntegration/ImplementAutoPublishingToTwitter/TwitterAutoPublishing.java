package com.nbcuni.test.publisher.tests.SocialIntegration.ImplementAutoPublishingToTwitter;

import java.net.URL;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Twitter.NodeTypes;
import com.nbcuni.test.publisher.pageobjects.Facebook.Share;
import com.nbcuni.test.publisher.pageobjects.Twitter.Twitter;
import com.nbcuni.test.publisher.pageobjects.Twitter.TwitterAccounts;
import com.nbcuni.test.publisher.pageobjects.Twitter.TwitterLogin;

public class TwitterAutoPublishing extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into the test instance as Drupal User 1, and then ensure that the module Pub Social is enabled. ,a  Login succeeds. b  The module Pub Social is enabled. 
     * Step 2 - After the module Pub Social has been enabled, ensure that the modules Twitter and Twitter Post are enabled.,
     * Step 3 - In the page at  Configuration.Web services.Twitter.Settings, ensure that  a  The value of Oauth Consumer key is set to 5Ur95TtwzHY2A9hMKg b  The value of Oauth Consumer secret is set to GEHafCRDdJpRoaJ4Zk4tPYDObR3IkeEtnr5otrpIs  and that the configuration has been saved. ,In the page at  Configuration.Web services.Twitter.Settings, ensure that  a  The value of Oauth Consumer key is set to 5Ur95TtwzHY2A9hMKg b  The value of Oauth Consumer secret is set to GEHafCRDdJpRoaJ4Zk4tPYDObR3IkeEtnr5otrpIs 
     * Step 4 - In another tab of the test browser:  a  Log into the Twitter account to be used for testing b  Under Settings.Accounts, ensure that the Tweet Privacy Protect my Tweets checkbox is checked and that this setting has been saved c  Do not log out of Twitter. ,Protect my Tweets is enabled in the Twitter account to be used for testing. 
     * Step 5 - In the page at Configuration.Web services.Twitter, click Go to Twitter to add an authenticated account, and then click Authorize app to authorize the Publisher Seven Twitter application for the account logged into at Step 4.,The Publisher 7 Twitter page is redisplayed with an entry added for the account signed into at Step 4. 
     * Step 6 - Create and save a new Post, and then publish it from the Workflow tab.,A publication success message is displayed in the Workflow tab.
     * Step 7 - Working in the Workflow tab for the content just published, click Share for the revision just published. ,The Share <ContentTitle> to Social Apps overlay is displayed. 
     * Step 8 - Ensure that the Post to Twitter box is checked. ,The Post to Twitter box is checked. 
     * Step 9 - Click in the Tweet box, and then start typing text in the box. ,An incrementing character count is displayed below the Tweet label. 
     * Step 10 - Click the Share button.
     * Step 11 - In the browser tab open to the test Twitter account, view the account homepage to determine if the tweet for the shared Publisher content was posted. ,The tweet for the shared Publisher content is present in the Twitter test account homepage. 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void TwitterAutoPublishing_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        taxonomy.NavigateSite("Modules");
        overlay.SwitchToActiveFrame();
        Modules modules = new Modules(webDriver);
        modules.EnterFilterName("Pub Social");
        modules.EnableModule("Pub Social");
        
        //Step 2
        modules.EnterFilterName("Twitter");
        modules.EnableModule("Twitter");
        modules.EnterFilterName("Twitter Post");
        modules.EnableModule("Twitter Post");
        overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent(true);
        
        //Step 3
        taxonomy.NavigateSite("Configuration>>Web services>>Twitter>>Settings");
        overlay.SwitchToActiveFrame();
        Twitter twitter = new Twitter(webDriver, applib);
        twitter.EnterOAuthConsumerKey("5Ur95TtwzHY2A9hMKg");
        twitter.EnterOAuthConsumerSecret("GEHafCRDdJpRoaJ4Zk4tPYDObR3IkeEtnr5otrpIs");
        twitter.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");
        overlay.ClickCloseOverlayLnk();
        
        //Step 3a
        taxonomy.NavigateSite("Configuration>>Web services>>Twitter>>Post");
        overlay.SwitchToActiveFrame();
        NodeTypes nodeTypes = new NodeTypes(webDriver, applib);
        nodeTypes.EnablePostNode();
        overlay.ClickCloseOverlayLnk();
        
        //Step 4 - NA as we log in to twitter at a later step
        
        //Step 5
        taxonomy.NavigateSite("Configuration>>Web services>>Twitter");
        overlay.SwitchToActiveFrame();
        TwitterAccounts twitterAccounts = new TwitterAccounts(webDriver);
        TwitterLogin twitterLogin = new TwitterLogin(webDriver, applib);
        Boolean accountAlreadyExists = twitterAccounts.TwitterAccountExists();
        if (accountAlreadyExists == false) {
        	twitterAccounts.ClickGoToTwitterAddAuthenticatedAccountBtn();
        	twitterLogin.EnterAdminUsernameOrEmail("publisherseven");
        	twitterLogin.EnterAdminPassword("Publ!$her");
        	twitterLogin.ClickAuthorizeAppBtn();
        	new WebDriverWait(webDriver, 10).until(ExpectedConditions.titleContains("Site-Install"));
        }
        else {
        	overlay.ClickCloseOverlayLnk();
        	webDriver.navigate().to("https://twitter.com/login");
        	twitterLogin.EnterUsernameOrEmail("publisherseven");
        	twitterLogin.EnterPassword("Publ!$her");
        	twitterLogin.ClickSignInBtn();
        	webDriver.navigate().to(config.getConfigValueString("AppURL"));
        }
        
        //Step 6
        taxonomy.NavigateSite("Content>>Add content>>Post");
        if (accountAlreadyExists == true) {
        	overlay.SwitchToActiveFrame();
        }
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String postTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle);
        basicInformation.EnterSynopsis();
        if (accountAlreadyExists == true) {
        	overlay.SwitchToActiveFrame();
        }
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState("Published");
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent(true);
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        WorkBench workBench = new WorkBench(webDriver);
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToFrame("Revisions");
        
        //Step 7
        Revisions revisions = new Revisions(webDriver);
        revisions.ClickEditExtendMenuBtn(postTitle);
        revisions.ClickShareMenuBtn(postTitle);
        
        //Step 8
        Share share = new Share(webDriver);
        share.ClickTwitterLnk();
        share.ClickPostToTwitterCbx();
        
        //Step 9
        String tweet = random.GetCharacterString(25);
        share.EnterTweet(tweet);
        
        //Step 10
        share.ClickShareBtn();
        
        //Step 11
        webDriver.navigate().to(new URL("https://twitter.com/"));
        overlay.switchToDefaultContent(true);
        twitterLogin.VerifyTwitterPostPresent(tweet);
        
    }
}
