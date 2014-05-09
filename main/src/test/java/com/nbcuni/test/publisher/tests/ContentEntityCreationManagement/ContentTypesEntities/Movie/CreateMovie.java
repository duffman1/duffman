package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.Movie;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class CreateMovie extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1043
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441339620
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke" })
    public void CreateMovie_TC1043() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 2
            taxonomy.NavigateSite("Content>>Add content>>Character Profile");
            
            //Step 3 
            CharactersInformation charactersInformation = new CharactersInformation(webDriver);
            overlay.SwitchToActiveFrame();
            String characterName = "Character" + random.GetCharacterString(15);
            charactersInformation.EnterCharacterFirstName(characterName);
            CoverPhoto coverPhoto = new CoverPhoto(webDriver);
            coverPhoto.ClickSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToActiveFrame();
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Character Profile " + characterName + " has been created.");
            
            //Step 4
            taxonomy.NavigateSite("Content>>Add content>>Movie");
            overlay.SwitchToActiveFrame();
            
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
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Content>>Add content>>Person");
            overlay.SwitchToActiveFrame();
            
            //Step 10
            PersonsInformation personsInformation = new PersonsInformation(webDriver);
            String personFirstName = "Person" + random.GetCharacterString(15);
            personsInformation.EnterFirstName(personFirstName);
            personsInformation.EnterBiography();
            overlay.SwitchToActiveFrame();
            personsInformation.ClickCoverPhotoSelectBtn();
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToActiveFrame();
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Person " + personFirstName + " has been created.");
            
            //Step 11
            taxonomy.NavigateSite("Content>>Add content>>Movie");
            
            //Step 12
            overlay.SwitchToActiveFrame();
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
            overlay.SwitchToActiveFrame();
            basicInformation.ClickCoverSelectBtn();
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToActiveFrame();
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
            
            //Step 15
            WorkBench workBench = new WorkBench(webDriver, applib);
            workBench.ClickWorkBenchTab("Edit Draft");
            overlay.SwitchToActiveFrame();
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
            overlay.switchToDefaultContent();
            workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList("Published"));
            
    }
}
