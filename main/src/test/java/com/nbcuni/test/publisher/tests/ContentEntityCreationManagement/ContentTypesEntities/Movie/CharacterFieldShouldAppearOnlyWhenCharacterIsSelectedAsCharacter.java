package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.Movie;


import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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


public class CharacterFieldShouldAppearOnlyWhenCharacterIsSelectedAsCharacter extends ParentTest{
	

    /*************************************************************************************
     * TEST CASE Character field should only appear when Role is selected as character
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Click on "Content" >> "Add new content" >> "Character Profile"
     * Step 3 - Populate all required fields, and click on the "Save" button.
     * Step 4 - Click on "Content" >> "Add new content" >> "Movie"<br>
     * Step 5 - Click on "Cast/Crew" vertical tab<br>
     * Step 6 - Verify that the field "Character" does not appear on the page<br>
     * Step 7 - Select the following values in the "Role" field, one after another, and verify that the "Character" field does not appear:
     * Contributor, Director, Executive Producer, Host, Judge, Producer, Self, Song Writer, Writer
     * Step 8 - Select the value "Character" for field "Role" and verify that the field "Character" appears as expected<br>
     * Step 9 - Click on "Content" >> "Add new content" >> "Person"<br>
     * Step 10 - Populate all required fields and click the "Save" button<br>
     * Step 11 - Click on "Content" >> "Add new content" >> "Movie"
     * Step 12 - Click on "Cast/Crew" vertical tab<br>
     * Step 13 - Populate the field "Person" with the title of the "Person" content type created in step 10. Select "Character" in te "Role" field and populate the "Character" field with the "Character" title created in step 3<br>
     * Step 14 - Click on the "Basic Information" vertical tab, populate all required fields, and click on the "Save" button<br>
     * Step 15 - Click on the "Edit" tab, then click on the "Cast/Crew" tab and verify that the values selected in step 13 are retained<br>
     * Step 16 - Click on the "Publishing options" tab, select the "Moderation State" as "Published", uncheck the "Create new revision" checkbox, and click on the "Save" button<br>
     * Step 17 - Log out of publisher 7<br>
     * Step 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void CharacterFieldShouldAppearOnlyWhenCharacterIsSelectedAsCharacter() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            //Step 2
            taxonomy.NavigateSite("Content>>Add content>>Character Profile");
            
            //Step 3 
            CharactersInformation charactersInformation = new CharactersInformation(webDriver);
            overlay.SwitchToFrame("Create Character Profile");
            String characterName = random.GetCharacterString(15);
            charactersInformation.EnterCharacterFirstName(characterName);
            charactersInformation.ClickAddPhotoSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToFrame("Create Character Profile");
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Character Profile " + characterName + " has been created.");
            
            //Step 4
            taxonomy.NavigateSite("Content>>Add content>>Movie");
            
            //Step 5
            overlay.SwitchToFrame("Create Movie");
            CastCrew castCrew = new CastCrew(webDriver);
            castCrew.ClickCastCrewLnk();
            
            //Step 6
            castCrew.VerifyCharacterTxbNotDisplayed();
            
            //Step 7
            List<String> allRoles = Arrays.asList("Contributor", "Directory", "Executive Producer", "Host",
            		"Judge", "Producer", "Self", "Song Writer", "Writer");
            for (String role : allRoles) {
            	
            	castCrew.SelectRole(role); Thread.sleep(500);
            	castCrew.VerifyCharacterTxbNotDisplayed();
            	
            }
            
            //Step 8
            castCrew.SelectRole("Character");
            castCrew.VerifyCharacterTxbDisplayed();
            
            //Step 9 
            overlay.switchToDefaultContent();
            taxonomy.NavigateSite("Content>>Add content>>Person");
            
            //Step 10
            PersonsInformation personsInformation = new PersonsInformation(webDriver);
            overlay.SwitchToFrame("Create Person");
            String personFirstName = random.GetCharacterString(15);
            personsInformation.EnterFirstName(personFirstName);
            String biography = personsInformation.EnterBiography();
            overlay.switchToDefaultContent();
            overlay.SwitchToFrame("Create Person");
            personsInformation.ClickCoverPhotoSelectBtn();
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToFrame("Create Person");
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Person " + personFirstName + " has been created.");
            
            //Step 11
            taxonomy.NavigateSite("Content>>Add content>>Movie");
            
            //Step 12
            overlay.SwitchToFrame("Create Movie");
            castCrew.ClickCastCrewLnk();
            
            //Step 13
            castCrew.EnterPersonName(personFirstName);
            castCrew.SelectRole("Character");
            castCrew.EnterCharacterName(characterName);
            
            //Step 14
            BasicInformation basicInformation = new BasicInformation(webDriver);
            basicInformation.ClickBasicInformationTab();
            String movieTitle = random.GetCharacterString(15);
            basicInformation.EnterTitle(movieTitle);
            String snyopsis = basicInformation.EnterSynopsis();
            overlay.SwitchToFrame("Create Movie");
            basicInformation.ClickCoverSelectBtn();
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToFrame("Create Movie");
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
            
            //Step 15
            contentParent.ClickEditDraftBtn();
            overlay.SwitchToFrame(movieTitle);
            castCrew.ClickCastCrewLnk();
            castCrew.VerifyCharacterTxbDisplayed();
            castCrew.VerifyPersonNameValue(personFirstName);
            castCrew.VerifyRoleValue("Character");
            castCrew.VerifyCharacterNameValue(characterName);
            
            //Step 16
            PublishingOptions publishingOptions = new PublishingOptions(webDriver);
            publishingOptions.ClickPublishingOptionsLnk();
            publishingOptions.ClickCreateNewRevisionCbx();
            publishingOptions.SelectModerationState("Published");
            contentParent.ClickSaveBtn();
            
            //Step 17
            overlay.switchToDefaultContent();
            RevisionState revisionState = new RevisionState(webDriver);
            revisionState.VerifyRevisionState("Published");
           
    }
}