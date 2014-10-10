package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.AdditionalInformation;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueues;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.Arrays;

public class AddTaxonomyParameters extends ParentTest{
	
	/*************************************************************************************
     * TEST CASE - TC4420
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/21008432775
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AddTaxonomyParameters_TC4420() throws Exception{
    	
    	Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        Reporter.log("SETUP");
        Modules modules = new Modules(webDriver);
        modules.VerifyModuleEnabled("Dynamic Queue");
        String movieWTax = "movieWTax" + random.GetCharacterString(15);
        String movieWOTax = "movieWOTax" + random.GetCharacterString(15);
        for (String title : Arrays.asList(movieWTax, movieWOTax)) {
        	taxonomy.NavigateSite("Content>>Add content>>Movie");
            overlay.SwitchToActiveFrame();
            BasicInformation basicInformation = new BasicInformation(webDriver);
            basicInformation.ClickBasicInformationTab();
            basicInformation.EnterTitle(title);
            basicInformation.EnterSynopsis();
            overlay.SwitchToActiveFrame();
            basicInformation.ClickCoverSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToActiveFrame();
            if (title.contains("WTax")) {
            	AdditionalInformation additionalInformation = new AdditionalInformation(webDriver);
                additionalInformation.ClickAdditionalInformationLnk();
                additionalInformation.SelectMovieType("Syndicated");
                additionalInformation.SelectRating("G");
                additionalInformation.SelectPrimaryGenre("Action");
            }
            PublishingOptions publishingOptions = new PublishingOptions(webDriver);
            publishingOptions.ClickPublishingOptionsLnk();
            publishingOptions.SelectModerationState("Published");
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent(true);
        }
        
        Reporter.log("STEP 2");
        taxonomy.NavigateSite("Structure>>Dynamic Queue types>>Add dynamic queue type");
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 3");
        String dynamicQueueTypeName = random.GetCharacterString(15);
        AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webDriver);
        addDynamicQueueType.EnterName(dynamicQueueTypeName);
        addDynamicQueueType.SelectEntityType();
        addDynamicQueueType.SelectCacheLifetime("1 min");
        addDynamicQueueType.EnableContentTypes(Arrays.asList("Movie", "Person"));
        addDynamicQueueType.ClickTaxonomyFilterLnk();
        addDynamicQueueType.EnableTaxonomyFilters(Arrays.asList("field_movie_primary_genre", 
        		"field_movie_secondary_genre", "field_additional_genres", "field_primary_genre", 
        		"field_movie_rating", "field_movie_type"));
        addDynamicQueueType.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 4");
        taxonomy.NavigateSite("Content>>Dynamic Queues>>Add " + dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 5");
        String dynamicQueueTitle = random.GetCharacterString(15);
        AddDynamicQueue addDynamicQueue = new AddDynamicQueue(webDriver);
        addDynamicQueue.EnterTitle(dynamicQueueTitle);
        
        Reporter.log("STEP 6");
        addDynamicQueue.CheckTargetBundle_Cbx("Movie");
        addDynamicQueue.CheckTargetBundle_Cbx("Person");
        
        Reporter.log("STEP 7");
        addDynamicQueue.SelectFieldMovieTypeTaxonomy("Syndicated");
        addDynamicQueue.SelectFieldMoviePrimaryGenreTaxonomy("Action");
        //addDynamicQueue.SelectFieldMovieSecondaryGenreTaxonomy("Comedy");
        addDynamicQueue.SelectFieldMovieRatingTaxonomy("G");
        
        Reporter.log("STEP 8 - TODO"); //TODO
        
        Reporter.log("STEP 9");
        addDynamicQueue.ClickSortByNewestRdb();
        addDynamicQueue.ClickSaveDynamicQueueBtn();
        overlay.switchToDefaultContent(true);
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTitle));
        
        Reporter.log("STEPS 10 AND 11 - MOVED TO TEST SETUP");
        
        Reporter.log("STEP 12 TODO"); //TODO
        
        Reporter.log("STEP 13");
        taxonomy.NavigateSite("Content>>Dynamic Queues");
        overlay.SwitchToActiveFrame();
        DynamicQueues dynamicQueues = new DynamicQueues(webDriver);
        String dynamicQueueNodeID = dynamicQueues.GetDynamicQueueNodeNumber(dynamicQueueTitle);
        overlay.ClickCloseOverlayLnk();
        String parentWindow = webDriver.getWindowHandle();
        applib.openNewWindow();
        applib.switchToNewWindow(parentWindow);
        applib.openSitePage("/dynamic-queue/" + dynamicQueueNodeID);
        contentParent.VerifyPageContentPresent(Arrays.asList(movieWTax));
        contentParent.VerifyPageContentNotPresent(Arrays.asList(movieWOTax));
        
        //TODO - some additional steps as time allows
        
        
    }
    
}
