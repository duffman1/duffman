package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityRelationships;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CastCrewFieldCollectionsVerificationMovie extends ParentTest{
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
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CastCrewFieldCollectionsVerificationMovie_Test() throws Exception{
    	
    	//Step 1
    	List<String> Characters = Arrays.asList("Character" + random.GetCharacterString(10),
    			"Character" + random.GetCharacterString(10),
    				"Character" + random.GetCharacterString(10));
    	List<String> Persons = Arrays.asList("Person" + random.GetCharacterString(10),
    			"Person" + random.GetCharacterString(10),
    				"Person" + random.GetCharacterString(10));
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 2
        CharactersInformation charactersInformation = new CharactersInformation(webDriver);
        SelectFile selectFile = new SelectFile(webDriver);
        for(int CCount=0;CCount<3;CCount++) {
        	navigation.AddContent("Character Profile");
	        charactersInformation.EnterCharacterFirstName(Characters.get(CCount));
	        CoverPhoto coverPhoto = new CoverPhoto(webDriver);
	        coverPhoto.ClickSelectBtn();
	        selectFile.SelectDefaultCoverImg();
	        coverPhoto.VerifyFileImagePresent("HanSolo");
	        contentParent.ClickSaveBtn();
	        contentParent.VerifyMessageStatus("Character Profile " + Characters.get(CCount) + " has been created.");
	     
        }
        
        //Step 3
        PersonsInformation personsInformation = new PersonsInformation(webDriver);
        for(int PCount=0;PCount<3;PCount++) {
        	navigation.AddContent("Person");
	        personsInformation.EnterFirstName(Persons.get(PCount));
	        personsInformation.EnterBiography();
	        personsInformation.ClickCoverPhotoSelectBtn();
	        selectFile.SelectDefaultCoverImg();
	        contentParent.ClickSaveBtn();
	        contentParent.VerifyMessageStatus("Person " + Persons.get(PCount) + " has been created.");
        }
        
       
      	//Step 4
        navigation.AddContent("Movie");
        
        //Step 5
        String movieTitle = "Movie" + random.GetCharacterString(15);
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.EnterTitle(movieTitle);
        basicInformation.EnterSynopsis();
        basicInformation.ClickCoverSelectBtn();
        selectFile.SelectDefaultCoverImg();
        
        //Step 6
        CastCrew castCrew = new CastCrew(webDriver);
        castCrew.ClickCastCrewLnk();
        castCrew.EnterPersonName(Persons.get(0), "1");
        castCrew.SelectRole("Character", "1");
        castCrew.VerifyCharacterTxbDisplayed();
        castCrew.EnterCharacterName(Characters.get(0), "1");
        
        //Step 7
        Thread.sleep(3000); //TODO - figure out why this pause is necessary and add dynamic wait
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
        WorkBench workBench = new WorkBench(webDriver);
        workBench.ClickWorkBenchTab("Revisions");
        Revisions revisions = new Revisions(webDriver);
        revisions.ClickEditMenuBtn(movieTitle);
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();  
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
        castCrew.ClickCastCrewLnk();
        castCrew.VerifyPersonNameValue(Persons.get(0), "1");
        castCrew.VerifyCharacterNameValue(Characters.get(0), "1");
        castCrew.VerifyRoleValue("Character", "1");
        castCrew.ClickAddAnotherItemBtn();
        castCrew.EnterPersonName(Persons.get(1), "2");
        castCrew.SelectRole("Character", "2");
        castCrew.VerifyCharacterTxbDisplayed();
        castCrew.EnterCharacterName(Characters.get(1), "2");
        basicInformation.ClickBasicInformationTab();
        String movieTitle2 = "Movie" + random.GetCharacterString(15);        
        basicInformation.EnterTitle(movieTitle2);
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Movie " + movieTitle2 + " has been updated.");
      
        //Step 8
        workBench.ClickWorkBenchTab("Revisions");
        revisions.ClickEditMenuBtn(movieTitle2);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
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
        basicInformation.ClickBasicInformationTab();
        String movieTitle3 = random.GetCharacterString(15);        
        basicInformation.EnterTitle(movieTitle3);
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Movie " + movieTitle3 + " has been updated.");
      
        //Step 9
        workBench.ClickWorkBenchTab("Revisions");
        revisions.ClickEditExtendMenuBtn(movieTitle2);
        revisions.ClickEditMenuDeleteBtn(movieTitle2);
        revisions.ClickDeleteConfirmBtn();
        
        //Step 10
        revisions.ClickEditMenuBtn(movieTitle3);
        contentParent.VerifyPageContentNotPresent(Arrays.asList("Notice: Trying to get property of non-object in field_collection_field_get_entity() (line 1608 of /mnt/www/html/nbcuqa4dev/docroot/profiles/all/modules/contrib/field_collection/field_collection.module)"));
        castCrew.ClickCastCrewLnk();
        castCrew.VerifyPersonNameValue(Persons.get(0), "1");
		castCrew.VerifyCharacterNameValue(Characters.get(0), "1");
		castCrew.VerifyRoleValue("Character", "1");
		castCrew.VerifyPersonNameValue(Persons.get(1), "2");
		castCrew.VerifyRoleValue("Character", "2");
		castCrew.VerifyCharacterNameValue(Characters.get(1), "2");     
		castCrew.VerifyPersonNameValue(Persons.get(2), "3");        
		castCrew.VerifyRoleValue("Character", "3");
		castCrew.VerifyCharacterNameValue(Characters.get(2), "3");  
		
	    //Step 11 - NA	
        
    }
}
