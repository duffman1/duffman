package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.TVEpisode;


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

import com.nbcuni.test.publisher.common.AppLib;
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


public class CreateTVEpisode extends ParentTest{
	

    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to pub7<br>
     * Step 1a - Add a new person
     * Step 2 - Navigate to create TV Episode page<br> 
     * Step 3 - Verify that below fields are the mandatory ones and have star symbol marked on them:-  Basic Information tab:- 1) Title 2) Episode 3) Synopsis  Publishhing Options tab:- 1) Moderation state<br>
     * Step 4 - Enter value in Title, Episode and Synopsis<br>
     * Step 5 - Upload image in Cover Media<br> 
     * Step 6 - Click on Publishing options tab and select Draft in Moderation state<br>
     * Step 7 - Click on the "Cast/Crew" vertical tab<br>
     * Step 8 - Select any person and role value<br> 
     * Step 9 - Click on save<br> 
     * Step 10 - Repeat above steps with Moderation state value as Review and Published<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void CreateTVEpisode() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            List<String> allStates = Arrays.asList("Draft", "Review", "Published");
            for (String state : allStates) {
            
            	Reporter.log("Publish state currently set to " + state);
            
            	//Step 1A
            	Taxonomy taxonomy = new Taxonomy(webDriver);
            	taxonomy.ClickTier1ContentTier2AddContentTier3PersonLnk();
            	Overlay overlay = new Overlay(webDriver);
            	overlay.SwitchToCreatePersonFrm();
            	PersonsInformation personsInformation = new PersonsInformation(webDriver);
            	Random random = new Random();
            	String personFirstName = random.GetCharacterString(15);
            	personsInformation.EnterFirstName(personFirstName);
            	personsInformation.EnterBiography();
            	overlay.switchToDefaultContent();
            	overlay.SwitchToCreatePersonFrm();
            	personsInformation.ClickCoverPhotoSelectBtn();
            	SelectFile selectFile = new SelectFile(webDriver, applib);
            	selectFile.SelectDefaultCoverImg();
            	overlay.SwitchToCreatePersonFrm();
            	ContentParent contentParent = new ContentParent(webDriver);
            	contentParent.ClickSaveBtn();
            	overlay.switchToDefaultContent();
            
            	//Step 2
            	taxonomy.ClickTier1ContentTier2AddContentTier3TVEpisodeLnk();
            	overlay.SwitchToCreateTVEpisodeFrm();
            
            	//Step 3
            	contentParent.VerifyRequiredFields(Arrays.asList("Title", "Episode", "Synopsis"));
            	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
            	publishingOptions.ClickPublishingOptionsLnk();
            	contentParent.VerifyRequiredFields(Arrays.asList("Moderation State"));
            
            	//Step 4
            	BasicInformation basicInformation = new BasicInformation(webDriver);
            	basicInformation.ClickBasicInformationTab();
            	String tvEpisodeTitle = random.GetCharacterString(15);
            	basicInformation.EnterTitle(tvEpisodeTitle);
            	basicInformation.EnterEpisodeNumber("1");
            	basicInformation.EnterSynopsis();
                overlay.switchToDefaultContent();
                overlay.SwitchToCreateTVEpisodeFrm();
            	
            	//Step 5
            	basicInformation.ClickCoverSelectBtn();
            	selectFile.SelectDefaultCoverImg();
            	overlay.SwitchToCreateTVEpisodeFrm();
        	
            	//Step 6
            	publishingOptions.ClickPublishingOptionsLnk();
            	publishingOptions.SelectModerationState(state);
        	
            	//Step 7
            	CastCrew castCrew = new CastCrew(webDriver);
            	castCrew.ClickCastCrewLnk();
        	
            	//Step 8
            	castCrew.EnterPersonName(personFirstName);
            	castCrew.SelectRole("Contributor");
        	
            	//Step 9
            	contentParent.ClickSaveBtn();
            	contentParent.VerifyMessageStatus("TV Episode " + tvEpisodeTitle + " has been created.");
            	RevisionState revisionState = new RevisionState(webDriver);
            	revisionState.VerifyRevisionState(state);
        	
            }
    
    }
}
