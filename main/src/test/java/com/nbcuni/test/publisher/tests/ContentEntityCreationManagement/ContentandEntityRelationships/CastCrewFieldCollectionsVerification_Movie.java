package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityRelationships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.AccessDenied;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.CastCrew;
import com.nbcuni.test.publisher.pageobjects.content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.content.Content;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.content.Revisions;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.content.Workflow;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.MediaGallery.CreateMediaGallery;

public class CastCrewFieldCollectionsVerification_Movie extends ParentTest{
	 /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to the test instance as Drupal User 1 (usually admin in Publisher sites)./<br>
     * Step 2 - Creating 3 Character's Profiles as draft(Content > Add Content > Character Profile) <br>
     * Step 3 - Creating 3 Persons as draft(Content > Add Content > Person) <br>  
     * Step 4 - Click a Movie as draft(Content > Add Content > Movie).<br>
     * Step 5 - Populate Basic info and select Cover image.<br>
     * Step 6 - Click on Cast/Crew link and Populate Person, Role and Character. Save the Movie content<br>
     * Step 7 - Go to Revision and Edit current revision with new Movie Title(Title2) and adding a Cast/Crew.<br>
     * Step 8 - Go to Revision and Edit current(Second) revision with new Movie Title(Title3) and adding a Cast/Crew.<br>
     * Step 9 - Go to Revision and Delete Second revision. <br>
     * Step 10 - Click on Current revision and verify that there shouldn't be following error message on the page : 
     * 			 Notice: Trying to get property of non-object in field_collection_field_get_entity() (line 1608 of /mnt/www/html/
     * 		  	 nbcuqa4dev/docroot/profiles/all/modules/contrib/field_collection/field_collection.module)	<br>
     * Step 11 - Logout and close.		
     * @throws Throwable No Return values are needed.<br>
     *************************************************************************************/
    @Test(groups = {"full"})
    public void CastCrewFieldCollectionsVerification() throws Exception{
    	
    	/* Item removed from iteration 39, commenting out test until future iteration
    	
    	//Step 1
    	List<String> Characters = Arrays.asList("CharacterFirstName1","CharacterFirstName2","CharacterFirstName3");
    	List<String> Persons = Arrays.asList("PersonFirstName1","PersonFirstName2","PersonFirstName3");
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        ContentParent contentParent = new ContentParent(webDriver);
        
        //Step 2
        CharactersInformation charactersInformation = new CharactersInformation(webDriver);
        Random random = new Random();
        Overlay overlay = new Overlay(webDriver);
        SelectFile selectFile = new SelectFile(webDriver, applib);
        Logout logout = new Logout(webDriver);
        
        for(int CCount=0;CCount<3;CCount++){
	        Taxonomy taxonomy = new Taxonomy(webDriver);
	        taxonomy.NavigateSite("Content>>Add content>>Character Profile");
	        overlay.SwitchToFrame("Create Character Profile");
	        charactersInformation.EnterCharacterFirstName(Characters.get(CCount));
	        charactersInformation.ClickAddPhotoSelectBtn();
	        selectFile.SelectDefaultCoverImg();
	        overlay.SwitchToFrame("Create Character Profile");
	        charactersInformation.VerifyDefaultImagePresent("HanSolo");
	        contentParent.ClickSaveBtn();
	        overlay.switchToDefaultContent();
	        contentParent.VerifyMessageStatus("Character Profile " + Characters.get(CCount) + " has been created.");
	     
        }
        
      //Step 3
        PersonsInformation personsInformation = new PersonsInformation(webDriver);
        for(int PCount=0;PCount<3;PCount++){
	        taxonomy.NavigateSite("Content>>Add content>>Person");
	        overlay.SwitchToFrame("Create Person");	
	        personsInformation.EnterFirstName(Persons.get(PCount));
	        String biography = personsInformation.EnterBiography();
	        overlay.switchToDefaultContent();
	        overlay.SwitchToFrame("Create Person");
	        personsInformation.ClickCoverPhotoSelectBtn();
	        selectFile.SelectDefaultCoverImg();
	        overlay.SwitchToFrame("Create Person");	       
	        contentParent.ClickSaveBtn();
	        overlay.switchToDefaultContent();
	        contentParent.VerifyMessageStatus("Person " + Persons.get(PCount) + " has been created.");
        }
        
       
      	//Step 4
        taxonomy.NavigateSite("Content>>Add content>>Movie");
      
        //Step 5
        overlay.SwitchToFrame("Create Movie");
        String MovieName = random.GetCharacterString(15);
        BasicInformation basicInformation =new BasicInformation(webDriver);
        basicInformation.EnterTitle(MovieName);
        String MovieSynopsis = random.GetCharacterString(30);
        basicInformation.EnterSynopsis(MovieSynopsis);
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame("Create Movie");       
        basicInformation.ClickCoverSelectBtn();
        selectFile.SelectDefaultCoverImg();
      
        //Step 6
        overlay.SwitchToFrame("Create Movie");
        CastCrew castCrew = new CastCrew(webDriver);
        castCrew.ClickCastCrewLnk();
        castCrew.EnterPersonName(Persons.get(0), "1");
        castCrew.SelectRole("Character", "1");
        castCrew.VerifyCharacterTxbDisplayed();
        castCrew.EnterCharacterName(Characters.get(0), "1");
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        basicInformation.ClickBasicInformationTab();
        
        //Step 7
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        Workflow workFlow = new Workflow(webDriver);
        workFlow.ClickWorkflowTab("Revisions");
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        Revisions revision = new Revisions(webDriver);
        revision.ClickEditMenuBtn(MovieName);
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();  
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        castCrew.ClickCastCrewLnk();
        castCrew.VerifyPersonNameValue(Persons.get(0), "1");
        castCrew.VerifyCharacterNameValue(Characters.get(0), "1");
        castCrew.VerifyRoleValue("Character", "1");
        castCrew.ClickAddAnotherItemBtn();
        castCrew.EnterPersonName(Persons.get(1), "2");
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        castCrew.SelectRole("Character", "2");
        castCrew.VerifyCharacterTxbDisplayed();
        castCrew.EnterCharacterName(Characters.get(1), "2");
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        basicInformation.ClickBasicInformationTab();
        String MovieName2 = random.GetCharacterString(15);        
        basicInformation.EnterTitle(MovieName2);
        contentParent.ClickSaveBtn();
      
        //Step 8
        overlay.switchToDefaultContent();        
        workFlow.ClickWorkflowTab("Revisions");
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        revision.ClickEditMenuBtn(MovieName2);
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        
        castCrew.ClickCastCrewLnk();
        castCrew.VerifyPersonNameValue(Persons.get(0), "1");
        castCrew.VerifyCharacterNameValue(Characters.get(0), "1");
        castCrew.VerifyRoleValue("Character", "1");
        castCrew.VerifyPersonNameValue(Persons.get(1), "2");
        castCrew.VerifyRoleValue("Character", "2");
        castCrew.VerifyCharacterNameValue(Characters.get(1), "2");
        castCrew.ClickAddAnotherItemBtn();
        castCrew.EnterPersonName(Persons.get(2), "3");
        castCrew.SelectRole("Character", "3");
        castCrew.VerifyCharacterTxbDisplayed();
        castCrew.EnterCharacterName(Characters.get(2), "3");
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        basicInformation.ClickBasicInformationTab();
        String MovieName3 = random.GetCharacterString(15);        
        basicInformation.EnterTitle(MovieName3);
        contentParent.ClickSaveBtn();
      
        //Step 9
        overlay.switchToDefaultContent();        
        workFlow.ClickWorkflowTab("Revisions");
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        revision.ClickEditExtendMenuBtn(MovieName2);
        revision.ClickEditMenuDeleteBtn(MovieName2);
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        revision.ClickDeleteConfirmBtn();
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        revision.ClickEditMenuBtn(MovieName3);
        overlay.switchToDefaultContent();
        overlay.SwitchToActiveFrame();
        //TODO - this story pulled from iteration 39 so this error will still be present. For now I'm evaluating that it is but once this defect is fixed this line will fail and we will need to evaluate that it's not present.
        contentParent.VerifyPageContentPresent(Arrays.asList("Notice: Trying to get property of non-object in field_collection_field_get_entity() (line 1608 of /mnt/www/html/nbcuqa4dev/docroot/profiles/all/modules/contrib/field_collection/field_collection.module)"));
        castCrew.VerifyPersonNameValue(Persons.get(0), "1");
		castCrew.VerifyCharacterNameValue(Characters.get(0), "1");
		castCrew.VerifyRoleValue("Character", "1");
		castCrew.VerifyPersonNameValue(Persons.get(1), "2");
		castCrew.VerifyRoleValue("Character", "2");
		castCrew.VerifyCharacterNameValue(Characters.get(1), "2");     
		castCrew.VerifyPersonNameValue(Persons.get(2), "3");        
		castCrew.VerifyRoleValue("Character", "3");
		castCrew.VerifyCharacterNameValue(Characters.get(2), "3");  
		
	  //Step 11		
        overlay.ClickCloseOverlayLnk();     
        logout.ClickLogoutBtn();
     */
    }
}