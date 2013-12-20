package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.Post;


import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.CastCrew;
import com.nbcuni.test.publisher.pageobjects.content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.content.RevisionState;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class CreatePost extends ParentTest{
	

    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to Pub7 
     * Step 1a - Ensure "Pub Post" module is activated<br>
     * Step 1b - Navigate to create post page<br>
     * Step 2 - Verify that below fields are the mandatory ones and have star symbol marked on them:-  Basic Information tab:- 1) Title 2) Body  Publishhing Options tab:- 1) Moderation state<br>
     * Step 3 - Enter value in Title and Body<br>
     * Step 4 - Upload image in Cover Media.  
     * Step 5 - Click on Publishing options tab and select Draft in Moderation state. 
     * Step 6 - Click on save<br> 
     * Step 7 - Repeat above steps with Moderation state value as Review and Published<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void CreatePost() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            //Step 1a
            Taxonomy taxonomy = new Taxonomy(webDriver);
            taxonomy.ClickTier1ModulesLnk();
            Overlay overlay = new Overlay(webDriver);
            overlay.SwitchToModulesFrm();
            Modules modules = new Modules(webDriver);
            modules.EnterFilterName("Pub Post");
            modules.EnableModule("Pub Post");
            overlay.ClickCloseOverlayLnk();
            overlay.switchToDefaultContent();
            
            List<String> allStates = Arrays.asList("Draft", "Review", "Published");
            for (String state : allStates) {
            
            	Reporter.log("Publish state currently set to " + state);
            
            	//Step 1b
            	taxonomy.ClickTier1ContentTier2AddContentTier3PostLnk();
            	overlay.SwitchToCreatePostFrm();
            
            	//Step 2
            	ContentParent contentParent = new ContentParent(webDriver);
            	contentParent.VerifyRequiredFields(Arrays.asList("Title", "Body"));
            	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
            	publishingOptions.ClickPublishingOptionsLnk();
            	contentParent.VerifyRequiredFields(Arrays.asList("Moderation State"));
            
            	//Step 3
            	BasicInformation basicInformation = new BasicInformation(webDriver);
            	basicInformation.ClickBasicInformationTab();
            	Random random = new Random();
            	String postTitle = random.GetCharacterString(15);
            	basicInformation.EnterTitle(postTitle);
            	basicInformation.EnterSynopsis();
            
            	//Step 4
            	overlay.switchToDefaultContent();
            	overlay.SwitchToCreatePostFrm();
            	basicInformation.ClickCoverSelectBtn();
            	SelectFile selectFile = new SelectFile(webDriver, applib);
            	selectFile.SelectDefaultCoverImg();
            	overlay.SwitchToCreatePostFrm();
            
            	//Step 5
            	publishingOptions.ClickPublishingOptionsLnk();
            	publishingOptions.SelectModerationState(state);
            
            	//Step 6
            	contentParent.ClickSaveBtn();
            	overlay.switchToDefaultContent();
            	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
            	RevisionState revisionState = new RevisionState(webDriver);
            	revisionState.VerifyRevisionState(state);
            
            
            }
            
    }
}
