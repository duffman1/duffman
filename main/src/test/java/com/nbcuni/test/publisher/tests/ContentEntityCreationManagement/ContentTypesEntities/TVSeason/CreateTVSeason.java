package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.TVSeason;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CreateTVSeason extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1046
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441493787
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void CreateTVSeason_TC1046() throws Exception{
    	
        //Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        List<String> allStates = Arrays.asList("Draft", "Review", "Published");
        for (String state : allStates) {
        	
        //Step 2
        taxonomy.NavigateSite("Content>>Add content>>TV Season");
        overlay.SwitchToActiveFrame();
        
        //Step 3
        contentParent.VerifyRequiredFields(Arrays.asList("Title", "Season", "Synopsis"));
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        contentParent.VerifyRequiredFields(Arrays.asList("Moderation State"));
        
        //Step 4
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.ClickBasicInformationTab();
        String tvSeasonTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(tvSeasonTitle);
        basicInformation.EnterSeasonNumber("1");
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        
        //Step 5
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver);
        selectFile.SelectDefaultCoverImg();
    	overlay.SwitchToActiveFrame();
    	
    	//Step 6
    	publishingOptions.ClickPublishingOptionsLnk();
    	publishingOptions.SelectModerationState(state);
    	
    	//Step 7
    	contentParent.ClickSaveBtn();
    	contentParent.VerifyMessageStatus("TV Season " + tvSeasonTitle + " has been created.");
    	WorkBench workBench = new WorkBench(webDriver);
    	workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList(state));
    	
        }
        
    }
}
