package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityRelationships;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CastCrew;
import com.nbcuni.test.publisher.pageobjects.Content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CoverPhoto;
import com.nbcuni.test.publisher.pageobjects.Content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;

public class CastCrewFieldCollectionsVerificationTVEpisode extends ParentTest {
	 /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to the test instance as Drupal User 1 (usually admin in Publisher sites)./<br>
     * Step 2 - Creating 3 Character's Profiles as draft(Content > Add Content > Character Profile) <br>
     * Step 3 - Creating 3 Persons as draft(Content > Add Content > Person) <br>  
     * Step 4 - Click a TV Episode as draft(Content > Add Content > TV Episode).<br>
     * Step 5 - Populate Basic info .<br>
     * Step 6 - Click on Cast/Crew link and Populate Person, Role and Character. Save the TV Episode content<br>
     * Step 7 - Go to Revision and Edit current revision with new TV Episode Title(Title2) and adding a Cast/Crew.<br>
     * Step 8 - Go to Revision and Edit current(Second) revision with new TV Episode Title(Title3) and adding a Cast/Crew.<br>
     * Step 9 - Go to Revision and Delete Second revision. <br>
     * Step 10 - Click on Current revision and verify that there shouldn't be following error message on the page : 
     * 			 Notice: Trying to get property of non-object in field_collection_field_get_entity() (line 1608 of /mnt/www/html/
     * 		  	 nbcuqa5dev/docroot/profiles/all/modules/contrib/field_collection/field_collection.module)	<br>
     * Step 11 - Logout and close.		
     * @throws Throwable No Return values are needed.<br>
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CastCrewFieldCollectionsVerificationTVEpisode_Test() throws Exception{
    	
    	//Step 1
    	List<String> Characters = Arrays.asList("CharacterName" + random.GetCharacterString(10),
    			"CharacterName" + random.GetCharacterString(10),
    				"CharacterName" + random.GetCharacterString(10));
    	List<String> Persons = Arrays.asList("PersonName" + random.GetCharacterString(10),
    			"PersonName" + random.GetCharacterString(10),
    				"PersonName" + random.GetCharacterString(10));
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 2
        CharactersInformation charactersInformation = new CharactersInformation(webDriver);
        SelectFile selectFile = new SelectFile(webDriver);
        for(int CCount=0;CCount<3;CCount++) {
	        taxonomy.NavigateSite("Content>>Add content>>Character Profile");
	        overlay.SwitchToActiveFrame();
	        charactersInformation.EnterCharacterFirstName(Characters.get(CCount));
	        CoverPhoto coverPhoto = new CoverPhoto(webDriver);
	        coverPhoto.ClickSelectBtn();
	        selectFile.SelectDefaultCoverImg();
	        overlay.SwitchToActiveFrame();
	        coverPhoto.VerifyFileImagePresent("HanSolo");
	        contentParent.ClickSaveBtn();
	        overlay.switchToDefaultContent(true);
	        contentParent.VerifyMessageStatus("Character Profile " + Characters.get(CCount) + " has been created.");
        }
        
        //Step 3
        PersonsInformation personsInformation = new PersonsInformation(webDriver);
        for(int PCount=0;PCount<3;PCount++){
	        taxonomy.NavigateSite("Content>>Add content>>Person");
	        overlay.SwitchToActiveFrame();
	        personsInformation.EnterFirstName(Persons.get(PCount));
	        personsInformation.EnterBiography();
	        overlay.SwitchToActiveFrame();
	        personsInformation.ClickCoverPhotoSelectBtn();
	        selectFile.SelectDefaultCoverImg();
	        overlay.SwitchToActiveFrame();
	        contentParent.ClickSaveBtn();
	        overlay.switchToDefaultContent(true);
	        contentParent.VerifyMessageStatus("Person " + Persons.get(PCount) + " has been created.");
        }
        
        //Step 4
        taxonomy.NavigateSite("Content>>Add content>>TV Episode");
        overlay.SwitchToActiveFrame();
        
        //Step 5
        String episodeTitle = random.GetCharacterString(15);        
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.EnterTitle(episodeTitle);
        basicInformation.EnterEpisodeNumber("1");
    	basicInformation.EnterSynopsis();       
        overlay.SwitchToActiveFrame(); 
      
        //Step 6
        CastCrew castCrew = new CastCrew(webDriver);
        castCrew.ClickCastCrewLnk();
        castCrew.EnterPersonName(Persons.get(0), "1");
        castCrew.SelectRole("Character", "1");
        castCrew.VerifyCharacterTxbDisplayed();
        castCrew.EnterCharacterName(Characters.get(0), "1");
        
        //Step 7
        Thread.sleep(1000); //TODO - figure out why this pause is necessary and add dynamic wait
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent(true);
        contentParent.VerifyMessageStatus("TV Episode " + episodeTitle + " has been created.");
        WorkBench workBench = new WorkBench(webDriver);
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToActiveFrame();
        Revisions revisions = new Revisions(webDriver);
        revisions.ClickEditMenuBtn(episodeTitle);
        overlay.SwitchToActiveFrame();
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
        String episodeTitle2 = random.GetCharacterString(15);        
        basicInformation.EnterTitle(episodeTitle2);
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent(true);        
        contentParent.VerifyMessageStatus("TV Episode " + episodeTitle2 + " has been updated.");
      
        //Step 8
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToActiveFrame();
        revisions.ClickEditMenuBtn(episodeTitle2);
        overlay.SwitchToActiveFrame();
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
        String episodeTitle3 = random.GetCharacterString(15);        
        basicInformation.EnterTitle(episodeTitle3);
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent(true);        
        contentParent.VerifyMessageStatus("TV Episode " + episodeTitle3 + " has been updated.");
        
        //Step 9
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToActiveFrame();
        revisions.ClickEditExtendMenuBtn(episodeTitle2);
        revisions.ClickEditMenuDeleteBtn(episodeTitle2);
        overlay.SwitchToActiveFrame();
        revisions.ClickDeleteConfirmBtn();
        overlay.SwitchToActiveFrame();
        
        //Step 10
        revisions.ClickEditMenuBtn(episodeTitle3);
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentNotPresent(Arrays.asList("Notice: Trying to get property of non-object in field_collection_field_get_entity() (line 1608 of /mnt/www/html/nbcuqa5dev/docroot/profiles/all/modules/contrib/field_collection/field_collection.module)"));
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
		
		//Step 11 - N/A
        
    }
}
