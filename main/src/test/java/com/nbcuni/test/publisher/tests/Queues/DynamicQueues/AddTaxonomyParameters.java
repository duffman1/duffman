package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.AdditionalInformation;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueueTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueues;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
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
        Modules modules = new Modules(webWebWebDriver);
        modules.VerifyModuleEnabled("Dynamic Queue");
        String movieWTax = "movieWTax" + random.GetCharacterString(15);
        String movieWOTax = "movieWOTax" + random.GetCharacterString(15);
        for (String title : Arrays.asList(movieWTax, movieWOTax)) {
        	navigation.AddContent("Movie");
        	BasicInformation basicInformation = new BasicInformation(webWebWebDriver);
            basicInformation.ClickBasicInformationTab();
            basicInformation.EnterTitle(title);
            basicInformation.EnterSynopsis();
            basicInformation.ClickCoverSelectBtn();
            SelectFile selectFile = new SelectFile(webWebWebDriver);
            selectFile.SelectDefaultCoverImg();
            if (title.contains("WTax")) {
            	AdditionalInformation additionalInformation = new AdditionalInformation(webWebWebDriver);
                additionalInformation.ClickAdditionalInformationLnk();
                additionalInformation.SelectMovieType("Syndicated");
                additionalInformation.SelectRating("G");
                additionalInformation.SelectPrimaryGenre("Action");
            }
            PublishingOptions publishingOptions = new PublishingOptions(webWebWebDriver);
            publishingOptions.ClickPublishingOptionsLnk();
            publishingOptions.SelectModerationState("Published");
            contentParent.ClickSaveBtn();
        }
        
        Reporter.log("STEP 2");
        navigation.Structure("Dynamic Queue types");
        DynamicQueueTypes dynamicQueueTypes = new DynamicQueueTypes(webWebWebDriver);
        dynamicQueueTypes.ClickAddDynamicQueueTypeLnk();
        
        Reporter.log("STEP 3");
        String dynamicQueueTypeName = random.GetCharacterString(15);
        AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webWebWebDriver);
        addDynamicQueueType.EnterName(dynamicQueueTypeName);
        addDynamicQueueType.SelectEntityType();
        addDynamicQueueType.SelectCacheLifetime("1 min");
        addDynamicQueueType.EnableContentTypes(Arrays.asList("Movie", "Person"));
        addDynamicQueueType.ClickTaxonomyFilterLnk();
        addDynamicQueueType.EnableTaxonomyFilters(Arrays.asList("field_movie_primary_genre", 
        		"field_movie_secondary_genre", "field_additional_genres", "field_primary_genre", 
        		"field_movie_rating", "field_movie_type"));
        addDynamicQueueType.ClickSaveBtn();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
        
        Reporter.log("STEP 4");
        navigation.Content("Dynamic Queues");
        DynamicQueues dynamicQueues = new DynamicQueues(webWebWebDriver);
        dynamicQueues.ClickAddDynamicQueueLnk(dynamicQueueTypeName);
        
        Reporter.log("STEP 5");
        String dynamicQueueTitle = random.GetCharacterString(15);
        AddDynamicQueue addDynamicQueue = new AddDynamicQueue(webWebWebDriver);
        addDynamicQueue.EnterTitle(dynamicQueueTitle);
        
        Reporter.log("STEP 6");
        addDynamicQueue.CheckTargetBundle_Cbx("Movie");
        addDynamicQueue.CheckTargetBundle_Cbx("Person");
        
        Reporter.log("STEP 7");
        addDynamicQueue.SelectFieldMovieTypeTaxonomy("Syndicated");
        addDynamicQueue.SelectFieldMoviePrimaryGenreTaxonomy("Action");
        addDynamicQueue.SelectFieldMovieRatingTaxonomy("G");
        
        Reporter.log("STEP 8 - TODO"); //TODO
        
        Reporter.log("STEP 9");
        addDynamicQueue.ClickSortByNewestRdb();
        addDynamicQueue.ClickSaveDynamicQueueBtn();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTitle));
        
        Reporter.log("STEPS 10 AND 11 - MOVED TO TEST SETUP");
        
        Reporter.log("STEP 12 TODO"); //TODO
        
        Reporter.log("STEP 13");
        navigation.Content("Dynamic Queues");
        String dynamicQueueNodeID = dynamicQueues.GetDynamicQueueNodeNumber(dynamicQueueTitle);
        String parentWindow = webWebWebDriver.getWindowHandle();
        applib.openNewWindow();
        applib.switchToNewWindow(parentWindow);
        applib.openSitePage("/dynamic-queue/" + dynamicQueueNodeID);
        contentParent.VerifyPageContentPresent(Arrays.asList(movieWTax));
        contentParent.VerifyPageContentNotPresent(Arrays.asList(movieWOTax));
        
        //TODO - some additional steps as time allows
        
        
    }
    
}
