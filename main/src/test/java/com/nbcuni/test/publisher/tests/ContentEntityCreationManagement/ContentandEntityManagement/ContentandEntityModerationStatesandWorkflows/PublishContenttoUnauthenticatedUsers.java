package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.ContentandEntityModerationStatesandWorkflows;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

public class PublishContenttoUnauthenticatedUsers extends GlobalBaseTest{
	 /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to the test instance as Drupal User 1 (usually admin in Publisher sites)./<br>
     * Step 2 - Creating a Media Gallery (Content > Add Content > Media Gallery) that includes four images as Media Items. <br>
     * Step 3 - Click the Publishing options tab, change the value of Moderation State to Published, type a message 
     *          in the Log message for this state change box, and then click Save.
     * Step 4 - Copy the URL for the just-created content--now showing in the address box of the current test browser--
     *          into the address box of a different test browser in which a logged-in session does not already exist for the test site,
     *          and then attempt to open that URL in the second browser.
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void PublishContenttoUnauthenticatedUsers_Test() throws Exception{
      
    	//Step 1
    	UserLogin userLogin = appLib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 2
    	navigation.AddContent("Media Gallery");
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String title = random.GetCharacterString(15);
        basicInformation.EnterTitle(title);
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver);
        selectFile.SelectDefaultCoverImg();
        basicInformation.VerifyCoverImagePresent("HanSolo");        
       
        //Step 3
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
    	publishingOptions.SelectModerationState("Published");
    	publishingOptions.EnterMessageForStateChange("Log message for state change as Published");
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
      
        //Step 4
        String ContentURL = webDriver.getCurrentUrl();
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
        webDriver.navigate().to(ContentURL);
        contentParent.VerifyPostTitle(title);
    
    }
}
