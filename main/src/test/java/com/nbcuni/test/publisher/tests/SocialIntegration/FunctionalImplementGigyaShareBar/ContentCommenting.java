package com.nbcuni.test.publisher.tests.SocialIntegration.FunctionalImplementGigyaShareBar;

import java.net.URL;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.GigyaShareBar;
import com.nbcuni.test.publisher.pageobjects.Twitter.TwitterLogin;

public class ContentCommenting extends ParentTest{
	
	/*************************************************************************************
     * TEST CASE - TC3977
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20523176723
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void ContentCommenting_TC3977() throws Exception{
    	
    	Reporter.log("STEP 1");
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	Reporter.log("STEP 2 - N/A");
        
    	Reporter.log("STEP 3");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle = createDefaultContent.Post("Draft");
        GigyaShareBar gigyaShareBar = new GigyaShareBar(webDriver, applib);
        String parentWindow = webDriver.getWindowHandle();
	    gigyaShareBar.ClickTweetButton();
        for (String window : webDriver.getWindowHandles()){
           if (!window.equals(parentWindow)){
             webDriver.switchTo().window(window);
             break;
           }
        }
        TwitterLogin twitterLogin = new TwitterLogin(webDriver, applib);
        twitterLogin.EnterUsernameOrEmail("publisherseven");
        twitterLogin.EnterPassword("Publ!$her");
        twitterLogin.ClickSignInAndTweetBtn();
        webDriver.switchTo().window(parentWindow);
        webDriver.navigate().to(new URL("https://twitter.com/"));
        overlay.switchToDefaultContent();
        twitterLogin.VerifyTwitterPostPresent(postTitle);
        
        Reporter.log("STEP 4");
        /* 7-15-14 commenting out step 4 until Sruthi can investigate the facebook "an error has occured" on like.
        applib.openApplication();
        taxonomy.NavigateSite("Content>>Add content>>Post");
        overlay.SwitchToActiveFrame();
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String postTitle2 = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle2);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState("Published");
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("Post " + postTitle2 + " has been created.");
        gigyaShareBar.ClickLikeButton();
        for (String window : webDriver.getWindowHandles()){
           if (!window.equals(parentWindow)){
             webDriver.switchTo().window(window);
             break;
           }
        }
        DrupalForFacebook drupalForFacebook = new DrupalForFacebook(webDriver, applib);
        drupalForFacebook.LoginToFacebook("publisher_rifassi_user@tfbnw.net", "pa55word");
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
        */
    }
}