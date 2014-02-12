package com.nbcuni.test.publisher.tests.UserCreationAndManagement.UnauthenticatedUsersNotAbleToViewThePublishedContents;

import java.util.Arrays;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.Content;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;

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
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full"})
    public void UnauthenticatedUsersNotAbleToViewThePublishedContents_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	PageFactory.initElements(webDriver, userLogin);
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2        
    	taxonomy.NavigateSite("Content>>Add content>>Post");
    	overlay.SwitchToFrame("Create Post");
    	ContentParent contentParent = new ContentParent(webDriver, applib);
    	PageFactory.initElements(webDriver, contentParent);
    	contentParent.VerifyRequiredFields(Arrays.asList("Title", "Body"));
    	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
    	publishingOptions.ClickPublishingOptionsLnk();
    	contentParent.VerifyRequiredFields(Arrays.asList("Moderation State"));
    	BasicInformation basicInformation = new BasicInformation(webDriver);
    	PageFactory.initElements(webDriver, basicInformation);
    	basicInformation.ClickBasicInformationTab();
    	String postTitle = random.GetCharacterString(15);
    	basicInformation.EnterTitle(postTitle);
    	basicInformation.EnterSynopsis();
    	overlay.switchToDefaultContent();
    	overlay.SwitchToFrame("Create Post");
    	basicInformation.ClickCoverSelectBtn();
    	SelectFile selectFile = new SelectFile(webDriver, applib);
    	PageFactory.initElements(webDriver, selectFile);
    	selectFile.SelectDefaultCoverImg();
    	overlay.SwitchToFrame("Create Post");
    	publishingOptions.ClickPublishingOptionsLnk();
    	publishingOptions.SelectModerationState("Published");
    	contentParent.ClickSaveBtn();
    	overlay.switchToDefaultContent();
    	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
    	WorkBench workBench = new WorkBench(webDriver, applib);
    	PageFactory.initElements(webDriver, workBench);
    	workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList("Published"));
    	
    	//Step 3
        String ContentURL = webDriver.getCurrentUrl();
        Logout logout = new Logout(webDriver);
        PageFactory.initElements(webDriver, logout);
        logout.ClickLogoutBtn();
        
        //Step4	
        webDriver.navigate().to(ContentURL);
      
        //Step5
        contentParent.VerifyPostTitle(postTitle);
        
        //Step6
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step7	
        webDriver.navigate().to(ContentURL);
      
        //Step8
        Revisions revisions = new Revisions(webDriver, applib);
        PageFactory.initElements(webDriver, revisions);
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
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
      
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
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
      
        //Step19
        webDriver.navigate().to(ContentURL);
      
        //Step20
        workBench.ClickWorkBenchTab("View");
        
        //Step21
        ContentURL = webDriver.getCurrentUrl();
        logout.ClickLogoutBtn();
      
        //Step22
        webDriver.navigate().to(ContentURL);
      
        //Step23
        contentParent.VerifyPostTitle(postTitle);
  
       
    }
}
