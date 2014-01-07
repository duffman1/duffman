package com.nbcuni.test.publisher.tests.UserCreationAndManagement.UnauthenticatedUsersNotAbleToViewThePublishedContents;

import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.People.Permissions;
import com.nbcuni.test.publisher.pageobjects.People.Roles;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.Content;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.content.RevisionState;
import com.nbcuni.test.publisher.pageobjects.content.Revisions;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;

public class UnauthenticatedUsersNotAbleToViewThePublishedContents extends ParentTest {

    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into a new-installation Publisher test instance as Drupal User 1<br> 
     * Step 2 - Create  a Published post<br> 
     * Step 3 - Copy the URL and Log Out<br>
     * Step 4 - Paste the URL in Browser(as Unauthenticated user)<br>
     * Step 5 - Verify that the user should be able to view the corresponding published content.<br>
     * Step 6 - Log into a new-installation Publisher test instance as Drupal User 1<br> 
     * Step 7 - Move to the Post created in Step-2 <br> 
     * Step 8 - Click on Revision Tab and Populate comment on Revision page and update the post <br>
     * Step 9 - Copy the URL and Log Out<br>
     * Step 10 - Paste the URL in Browser(as Unauthenticated user)<br>
     * Step 11 - Verify that the user should be able to view the corresponding published content.<br>
     * Step 12 - Log into a new-installation Publisher test instance as Drupal User 1<br> 
     * Step 13 - Move to the Post created in Step-2 <br> 
     * Step 14 - Click on Content and Post title link from content container<br>
     * Step 15 - Copy the URL and Log Out<br>
     * Step 16 - Paste the URL in Browser(as Unauthenticated user)<br>
     * Step 17 - Verify that the user should be able to view the corresponding published content.<br>
     * Step 18 - Log into a new-installation Publisher test instance as Drupal User 1<br> 
     * Step 19 - Move to the Post created in Step-2 <br> 
     * Step 20 - Click View Tab<br>
     * Step 21 - Copy the URL and Log Out<br>
     * Step 22 - Paste the URL in Browser(as Unauthenticated user)<br>
     * Step 23 - Verify that the user should be able to view the corresponding published content.<br>
     * 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke"})
    public void UnauthenticatedUsersNotAbleToViewThePublishedContents() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 2        
    	taxonomy.NavigateSite("Content>>Add content>>Post");
    	overlay.SwitchToFrame("Create Post");
    	ContentParent contentParent = new ContentParent(webDriver);
    	contentParent.VerifyRequiredFields(Arrays.asList("Title", "Body"));
    	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
    	publishingOptions.ClickPublishingOptionsLnk();
    	contentParent.VerifyRequiredFields(Arrays.asList("Moderation State"));
    	BasicInformation basicInformation = new BasicInformation(webDriver);
    	basicInformation.ClickBasicInformationTab();
    	String postTitle = random.GetCharacterString(15);
    	basicInformation.EnterTitle(postTitle);
    	basicInformation.EnterSynopsis();
    	overlay.switchToDefaultContent();
    	overlay.SwitchToFrame("Create Post");
    	basicInformation.ClickCoverSelectBtn();
    	SelectFile selectFile = new SelectFile(webDriver, applib);
    	selectFile.SelectDefaultCoverImg();
    	overlay.SwitchToFrame("Create Post");
    	publishingOptions.ClickPublishingOptionsLnk();
    	publishingOptions.SelectModerationState("Published");
    	contentParent.ClickSaveBtn();
    	overlay.switchToDefaultContent();
    	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
    	RevisionState revisionState = new RevisionState(webDriver);
    	revisionState.VerifyRevisionState("Published");
    	
    	//Step 3
        String ContentURL = webDriver.getCurrentUrl();
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
        
        //Step4	
        webDriver.navigate().to(ContentURL);
      
        //Step5
        contentParent.VerifyPostTitle(postTitle);
        
        //Step6
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step7	
        webDriver.navigate().to(ContentURL);
      
        //Step8
        Revisions revisions = new Revisions(webDriver);
        revisions.ClickRevisionTab();
        overlay.SwitchToFrame("Revisions dialog");        
        revisions.EnterLogMessageForStateChange("This Revision Comment");
        revisions.ClickUpdateStateBtn();
      
        //Step9
        ContentURL = webDriver.getCurrentUrl();
        logout.ClickLogoutBtn();
      
        //Step10
        webDriver.navigate().to(ContentURL);
      
        //Step11
        contentParent.VerifyPostTitle(postTitle);
      
        //Step13
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
      
        //Step14
        taxonomy.NavigateSite("Content");
        overlay.SwitchToFrame("Content");
        Content content = new Content(webDriver);
        content.ClickTheContentFromContentpage(postTitle);
      
        //Step15
        ContentURL = webDriver.getCurrentUrl();
        logout.ClickLogoutBtn();
      
        //Step16
        webDriver.navigate().to(ContentURL);
      
        //Step17
        contentParent.VerifyPostTitle(postTitle);
      
        //Step18
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
      
        //Step19
        webDriver.navigate().to(ContentURL);
      
        //Step20
        contentParent.ClickViewTab();
        
        //Step21
        ContentURL = webDriver.getCurrentUrl();
        logout.ClickLogoutBtn();
      
        //Step22
        webDriver.navigate().to(ContentURL);
      
        //Step23
        contentParent.VerifyPostTitle(postTitle);
  
       
    }
}
