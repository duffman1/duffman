package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.TVEpisode;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class CreateTVEpisode extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1045
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441340536
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CreateTVEpisode_TC1045() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
            List<String> allStates = Arrays.asList("Draft", "Review", "Published");
            for (String state : allStates) {
            
            	//Step 1A
            	navigation.AddContent("Person");
            	PersonsInformation personsInformation = new PersonsInformation(webDriver);
            	String personFirstName = random.GetCharacterString(15);
            	personsInformation.EnterFirstName(personFirstName);
            	personsInformation.EnterBiography();
            	personsInformation.ClickCoverPhotoSelectBtn();
            	SelectFile selectFile = new SelectFile(webDriver);
            	selectFile.SelectDefaultCoverImg();
            	contentParent.ClickSaveBtn();
            	contentParent.VerifyMessageStatus("Person " + personFirstName + " has been created.");
            	
            	//Step 2
            	navigation.AddContent("TV Episode");
            	
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
                
            	//Step 5
            	basicInformation.ClickCoverSelectBtn();
            	selectFile.SelectDefaultCoverImg();
            	
            	//Step 6
            	publishingOptions.ClickPublishingOptionsLnk();
            	publishingOptions.SelectModerationState(state);
        	
            	//Step 7
            	CastCrew castCrew = new CastCrew(webDriver);
            	castCrew.ClickCastCrewLnk();
        	
            	//Step 8
            	castCrew.EnterPersonName(personFirstName, "1");
            	castCrew.SelectRole("Contributor", "1");
        	
            	//Step 9
            	Thread.sleep(1000); //TODO - slight pause required here for this test - figure out a proper dynamic wait for this test.
            	contentParent.ClickSaveBtn();
            	contentParent.VerifyMessageStatus("TV Episode " + tvEpisodeTitle + " has been created.");
            	WorkBench workBench = new WorkBench(webDriver);
            	workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList(state));
            	
            }
    
    }
}
