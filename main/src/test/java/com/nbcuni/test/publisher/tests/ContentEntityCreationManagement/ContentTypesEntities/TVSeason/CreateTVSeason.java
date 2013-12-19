package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.TVSeason;


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
import com.nbcuni.test.publisher.Logout;
import com.nbcuni.test.publisher.Modules;
import com.nbcuni.test.publisher.Overlay;
import com.nbcuni.test.publisher.UserLogin;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.content.BasicInformation;
import com.nbcuni.test.publisher.content.CastCrew;
import com.nbcuni.test.publisher.content.CharactersInformation;
import com.nbcuni.test.publisher.content.ContentParent;
import com.nbcuni.test.publisher.content.ContentTypes;
import com.nbcuni.test.publisher.content.PersonsInformation;
import com.nbcuni.test.publisher.content.PublishingOptions;
import com.nbcuni.test.publisher.content.RevisionState;
import com.nbcuni.test.publisher.content.SelectFile;
import com.nbcuni.test.publisher.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class CreateTVSeason extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to Pub7<br>
     * Step 2 - Navigate to create Tv Season page<br> 
     * Step 3 - Verify that below fields are the mandatory ones and have star symbol marked on them:-  Basic Information tab:- 1) Title 2) Season 3) Synopsis  Publishhing Options tab:- 1) Moderation state<br>
     * Step 4 - Enter value in Title, Season and Synopsis<br>
     * Step 5 - Upload image in Cover media<br> 
     * Step 6 - Click on publishing options tab and select draft in Moderation state<br> 
     * Step 7 - Click on save<br>
     * Step 8 - Repeat above steps with Moderation state value as Review and Published<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void CreateTVSeason() throws Exception{
    	
        //Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        List<String> allStates = Arrays.asList("Draft", "Review", "Published");
        for (String state : allStates) {
        
        	Reporter.log("Publish state currently set to " + state);
        		
        //Step 2
        Taxonomy taxonomy = new Taxonomy(webDriver);
        taxonomy.ClickTier1ContentTier2AddContentTier3TVSeasonLnk();
        Overlay overlay = new Overlay(webDriver);
        overlay.SwitchToCreateTVSeasonFrm();
        
        //Step 3
        ContentParent contentParent = new ContentParent(webDriver);
        contentParent.VerifyRequiredFields(Arrays.asList("Title", "Season", "Synopsis"));
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        contentParent.VerifyRequiredFields(Arrays.asList("Moderation State"));
        
        //Step 4
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.ClickBasicInformationTab();
        Random random = new Random();
        String tvSeasonTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(tvSeasonTitle);
        basicInformation.EnterSeasonNumber("1");
        basicInformation.EnterSynopsis();
        overlay.SwitchToCreateTVSeasonFrm();
        
        //Step 5
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver, applib);
    	selectFile.SelectDefaultCoverImg();
    	overlay.SwitchToCreateTVSeasonFrm();
    	
    	//Step 6
    	publishingOptions.ClickPublishingOptionsLnk();
    	publishingOptions.SelectModerationState(state);
    	
    	//Step 7
    	contentParent.ClickSaveBtn();
    	contentParent.VerifyMessageStatus("TV Season " + tvSeasonTitle + " has been created.");
    	RevisionState revisionState = new RevisionState(webDriver);
    	revisionState.VerifyRevisionState(state);
    	
        }
        
    }
}
