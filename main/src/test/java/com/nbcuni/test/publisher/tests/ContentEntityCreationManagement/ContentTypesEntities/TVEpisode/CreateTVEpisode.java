package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.TVEpisode;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CreateTVEpisode extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1045
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441340536
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke"})
    public void CreateTVEpisode_TC1045() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	PageFactory.initElements(webDriver, userLogin);
            userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            List<String> allStates = Arrays.asList("Draft", "Review", "Published");
            for (String state : allStates) {
            
            	//Step 1A
            	taxonomy.NavigateSite("Content>>Add content>>Person");
            	overlay.SwitchToFrame("Create Person");
            	PersonsInformation personsInformation = new PersonsInformation(webDriver);
            	String personFirstName = random.GetCharacterString(15);
            	personsInformation.EnterFirstName(personFirstName);
            	personsInformation.EnterBiography();
            	overlay.switchToDefaultContent();
            	overlay.SwitchToFrame("Create Person");
            	personsInformation.ClickCoverPhotoSelectBtn();
            	SelectFile selectFile = new SelectFile(webDriver, applib);
            	PageFactory.initElements(webDriver, selectFile);
            	selectFile.SelectDefaultCoverImg();
            	overlay.SwitchToFrame("Create Person");
            	ContentParent contentParent = new ContentParent(webDriver, applib);
            	PageFactory.initElements(webDriver, contentParent);
            	contentParent.ClickSaveBtn();
            	overlay.switchToDefaultContent();
            	contentParent.VerifyMessageStatus("Person " + personFirstName + " has been created.");
            	
            	//Step 2
            	taxonomy.NavigateSite("Content>>Add content>>TV Episode");
            	overlay.SwitchToFrame("TV Episode");
            	
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
                overlay.SwitchToFrame("Create TV Episode");
                
            	//Step 5
            	basicInformation.ClickCoverSelectBtn();
            	selectFile.SelectDefaultCoverImg();
            	overlay.SwitchToFrame("Create TV Episode");
        	
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
            	WorkBench workBench = new WorkBench(webDriver, applib);
            	PageFactory.initElements(webDriver, workBench);
            	workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList(state));
            	
            }
    
    }
}
