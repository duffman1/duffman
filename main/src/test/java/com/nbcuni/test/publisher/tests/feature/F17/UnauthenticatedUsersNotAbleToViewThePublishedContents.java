package com.nbcuni.test.publisher.tests.feature.F17;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
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
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "F17"})
    public void UnauthenticatedUsersNotAbleToViewThePublishedContents_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 2  
    	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
    	String postTitle = createDefaultContent.Post("Published");
    	WorkBench workBench = new WorkBench(webDriver);
    	workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList("Published"));
    	
    	//Step 3
        String contentURL = webDriver.getCurrentUrl();
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
        
        //Step4	
        applib.openSitePage(contentURL);
        
        //Step5
        ContentParent contentParent = new ContentParent(webDriver);
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitle));
        
        //Step6
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step7	
        applib.openSitePage(contentURL);
        
        //Step8
        workBench.ClickWorkBenchTab("Revisions");
        Revisions revisions = new Revisions(webDriver);
        revisions.EnterLogMessageForStateChange("This Revision Comment");
        revisions.ClickUpdateStateBtn();
      
        //Step9
        contentURL = webDriver.getCurrentUrl();
        logout.ClickLogoutBtn();
      
        //Step10
        applib.openSitePage(contentURL);
        
        //Step11
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitle));
        
        //Step13
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
      
        //Step14
        navigation.Content();
        SearchFor searchFor = new SearchFor(webDriver);
        searchFor.ClickSearchTitleLnk(postTitle);
        
        //Step15
        contentURL = webDriver.getCurrentUrl();
        logout.ClickLogoutBtn();
      
        //Step16
        applib.openSitePage(contentURL);
        
        //Step17
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitle));
        
        //Step18
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
      
        //Step19
        applib.openSitePage(contentURL);
        
        //Step20
        workBench.ClickWorkBenchTab("View");
        
        //Step21
        contentURL = webDriver.getCurrentUrl();
        logout.ClickLogoutBtn();
      
        //Step22
        applib.openSitePage(contentURL);
      
        //Step23
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitle));
        
       
    }
}
