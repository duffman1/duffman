package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.StickyEditActions;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.MetaTags;
import com.nbcuni.test.publisher.pageobjects.Content.StickyEditActions;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

public class StickyButtons extends GlobalBaseTest {
	
	Boolean testSuccessful = false;
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login as admin  Note: Code for this User Story was initially deployed to http://qa1stg.publisher.nbcuni.com/ .,
	 * Step 1a - Verify module "Sticky Edit Actions" is enabled.
     * Step 2 - Go to http://qa1stg.publisher.nbcuni.com/#overlay=node/add/character-profile <http://qa1stg.publisher.nbcuni.com/>   Content > Add content > Character profile ,
     * Step 3 - Verify that when you scroll on all vertical tabs that you can see the Save and Cancel buttons  ,
     * Step 4 - Click the Meta Tags vertical tab and then expand the Open Graph fieldset container, this will get you a very long form to test on. Make sure the save and cancel buttons are showing on scroll (sticking).  ,
     * Step 5 - When a form is too short, the save and cancel buttons should not be stuck to the bottom and be at their normal placement.  (Create Character Profile > Menu items)  And you can check transition from long form to short form by resizing the browser window, even for a very short form/page. ,
	 * Step 6 - Click the 'Save' button, post is saved successfully.
     * Step 7 - You also will need to test this without the overlay, which you can access by removing the text "#overlay=" from the URL path. Example is <http://qa1stg.publisher.nbcuni.com/node/add/character-profile> , 
     * Step 8 - Clean up and disable sticky edit action module.
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive"})
    public void StickyButtons_Test() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = appLib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
            //Step 1a
            Modules modules = new Modules(webDriver);
            modules.VerifyModuleEnabled("Sticky Edit Actions");
            
            //Step 2 - NOTE - using post instead of character profile content type
            navigation.AddContent("Post");
            BasicInformation basicInformation = new BasicInformation(webDriver);
            String postTitle = random.GetCharacterString(15);
        	basicInformation.EnterTitle(postTitle);
        	ContentParent contentParent = new ContentParent(webDriver);
        	interact.Scroll("100");
        	basicInformation.EnterSynopsis();
        	
            //Step 3
            StickyEditActions stickyEditActions = new StickyEditActions(webDriver);
            stickyEditActions.VerifySaveBtnPresent();
            stickyEditActions.VerifyPreviewBtnPresent();
            interact.Scroll("100");
            stickyEditActions.VerifySaveBtnPresent();
            stickyEditActions.VerifyPreviewBtnPresent();
            
            //Step 4
            MetaTags metaTags = new MetaTags(webDriver);
            metaTags.ClickMetaTagsLnk();stickyEditActions.VerifySaveBtnPresent();
            stickyEditActions.VerifyPreviewBtnPresent();
            interact.Scroll("100");
            stickyEditActions.VerifySaveBtnPresent();
            stickyEditActions.VerifyPreviewBtnPresent();
            
            //Step 5
            stickyEditActions.ClickSaveBtnPresent();
            contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        	
        	//Step 7
            appLib.openSitePage("/node/add/post");
        	stickyEditActions.VerifySaveBtnPresent();
            stickyEditActions.VerifyPreviewBtnPresent();
            interact.Scroll("100");
            stickyEditActions.VerifySaveBtnPresent();
            stickyEditActions.VerifyPreviewBtnPresent();
            
            //Step 8
            navigation.Modules();
            modules.DisableModule("Sticky Edit Actions");
        	
        	testSuccessful = true;
        	
    }
    
//    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"sensitive"}, dependsOnMethods = {"StickyButtons_Test"}, alwaysRun=true)
//	public void Cleanup() throws Exception {
//		if (testSuccessful == false) {
//
//			UserLogin userLogin = new UserLogin(webDriver);
//			Modules modules = new Modules(webDriver);
//			appLib.openSitePage("/user");
//			userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
//			navigation.Modules();
//        	modules.DisableModule("Sticky Edit Actions");
//		}
//	}
}
