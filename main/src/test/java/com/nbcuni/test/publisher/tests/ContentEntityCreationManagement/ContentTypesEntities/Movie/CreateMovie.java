package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.Movie;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CreateMovie extends GlobalBaseTest {
	
    /*************************************************************************************
     * TEST CASE - TC1043
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441339620
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void CreateMovie_TC1043() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = appLib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
            //Step 2
        	navigation.AddContent("Character Profile");
            
            //Step 3 
            CharactersInformation charactersInformation = new CharactersInformation(webDriver);
            String characterName = "Character" + random.GetCharacterString(15);
            charactersInformation.EnterCharacterFirstName(characterName);
            CoverPhoto coverPhoto = new CoverPhoto(webDriver);
            coverPhoto.ClickSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SelectDefaultCoverImg();
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Character Profile " + characterName + " has been created.");
            
            //Step 4
            navigation.AddContent("Movie");
            
            //Step 5
            CastCrew castCrew = new CastCrew(webDriver);
            castCrew.ClickCastCrewLnk();
            
            //Step 6
            castCrew.VerifyCharacterTxbNotDisplayed();
            
            //Step 7
            List<String> allRoles = Arrays.asList("Contributor", "Director", "Executive Producer", "Host",
            		"Judge", "Producer", "Self", "Song Writer", "Writer");
            for (String role : allRoles) {
            	
            	castCrew.SelectRole(role, "1"); Thread.sleep(500);
            	castCrew.VerifyCharacterTxbNotDisplayed();
            	
            }
            
            //Step 8
            castCrew.SelectRole("Character", "1");
            castCrew.VerifyCharacterTxbDisplayed();
            
            //Step 9 
            navigation.AddContent("Person");
            
            //Step 10
            PersonsInformation personsInformation = new PersonsInformation(webDriver);
            String personFirstName = "Person" + random.GetCharacterString(15);
            personsInformation.EnterFirstName(personFirstName);
            personsInformation.EnterBiography();
            personsInformation.ClickCoverPhotoSelectBtn();
            selectFile.SelectDefaultCoverImg();
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Person " + personFirstName + " has been created.");
            
            //Step 11
            navigation.AddContent("Movie");
            
            //Step 12
            castCrew.ClickCastCrewLnk();
            
            //Step 13
            castCrew.EnterPersonName(personFirstName, "1");
            castCrew.SelectRole("Character", "1");
            castCrew.EnterCharacterName(characterName, "1");
            
            //Step 14
            BasicInformation basicInformation = new BasicInformation(webDriver);
            basicInformation.ClickBasicInformationTab();
            String movieTitle = "Movie" + random.GetCharacterString(15);
            basicInformation.EnterTitle(movieTitle);
            basicInformation.EnterSynopsis();
            basicInformation.ClickCoverSelectBtn();
            selectFile.SelectDefaultCoverImg();
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
            
            //Step 15
            WorkBench workBench = new WorkBench(webDriver);
            workBench.ClickWorkBenchTab("Edit Draft");
            castCrew.ClickCastCrewLnk();
            castCrew.VerifyCharacterTxbDisplayed();
            castCrew.VerifyPersonNameValue(personFirstName, "1");
            castCrew.VerifyRoleValue("Character", "1");
            castCrew.VerifyCharacterNameValue(characterName, "1");
            
            //Step 16
            PublishingOptions publishingOptions = new PublishingOptions(webDriver);
            publishingOptions.ClickPublishingOptionsLnk();
            publishingOptions.ClickCreateNewRevisionCbx();
            publishingOptions.SelectModerationState("Published");
            contentParent.ClickSaveBtn();
            
            //Step 17
            workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList("Published"));
            
    }
}
