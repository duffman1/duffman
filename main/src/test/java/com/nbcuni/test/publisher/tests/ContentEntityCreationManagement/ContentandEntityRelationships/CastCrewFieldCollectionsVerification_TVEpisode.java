package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityRelationships;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CastCrew;
import com.nbcuni.test.publisher.pageobjects.Content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.CoverPhoto;
import com.nbcuni.test.publisher.pageobjects.Content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;

public class CastCrewFieldCollectionsVerification_TVEpisode extends ParentTest {
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
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        ContentParent contentParent = new ContentParent(webDriver, applib);
        
        //Step 2
        CharactersInformation charactersInformation = new CharactersInformation(webDriver);
        SelectFile selectFile = new SelectFile(webDriver, applib);
        
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
	        overlay.switchToDefaultContent();
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
	        overlay.switchToDefaultContent();
	        contentParent.VerifyMessageStatus("Person " + Persons.get(PCount) + " has been created.");
        }
        
        //Step 4
        taxonomy.NavigateSite("Content>>Add content>>TV Episode");
        overlay.SwitchToActiveFrame();
        
        //Step 5
        String TVEpisodeName = random.GetCharacterString(15);        
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.EnterTitle(TVEpisodeName);
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
        basicInformation.ClickBasicInformationTab();
      
        //Step 7
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        WorkBench workBench = new WorkBench(webDriver, applib);
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToActiveFrame();
        Revisions revisions = new Revisions(webDriver, applib);
        revisions.ClickEditMenuBtn(TVEpisodeName);
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
        String TVEpisodeName2 = random.GetCharacterString(15);        
        basicInformation.EnterTitle(TVEpisodeName2);
        contentParent.ClickSaveBtn();
      
        //Step 8
        overlay.switchToDefaultContent();        
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToActiveFrame();
        revisions.ClickEditMenuBtn(TVEpisodeName2);
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
        String TVEpisodeName3 = random.GetCharacterString(15);        
        basicInformation.EnterTitle(TVEpisodeName3);
        contentParent.ClickSaveBtn();
      
        //Step 9
        overlay.switchToDefaultContent();        
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToActiveFrame();
        revisions.ClickEditExtendMenuBtn(TVEpisodeName2);
        revisions.ClickEditMenuDeleteBtn(TVEpisodeName2);
        overlay.SwitchToActiveFrame();
        revisions.ClickDeleteConfirmBtn();
        overlay.SwitchToActiveFrame();
        revisions.ClickEditMenuBtn(TVEpisodeName3);
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
        
    }
}
