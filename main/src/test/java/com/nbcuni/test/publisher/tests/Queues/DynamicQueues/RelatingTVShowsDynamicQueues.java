package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueueTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueues;

import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;

public class RelatingTVShowsDynamicQueues extends ParentTest{
	
	/*************************************************************************************
     * TEST CASE - TC4389
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20942343684
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify"})
    public void RelatingTVShowsDynamicQueues_TC4389() throws Exception{
    	
    	Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        Reporter.log("SETUP");
        Modules modules = new Modules(webDriver);
        modules.VerifyModuleEnabled("Dynamic Queue");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        String tvShowName1 = createDefaultContent.TVShow("Published");
        
        Reporter.log("STEP 2");
        navigation.Structure("Dynamic Queue types");
        DynamicQueueTypes dynamicQueueTypes = new DynamicQueueTypes(webDriver);
        dynamicQueueTypes.ClickAddDynamicQueueTypeLnk();
        String dynamicQueueTypeName = random.GetCharacterString(15);
        AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webDriver);
        addDynamicQueueType.EnterName(dynamicQueueTypeName);
        addDynamicQueueType.SelectEntityType();
        addDynamicQueueType.SelectCacheLifetime("1 min");
        addDynamicQueueType.ClickSaveBtn();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
        
        Reporter.log("STEP 3 - MOVED TO SETUP");
        
        Reporter.log("STEP 4");
        navigation.Structure("Content types");
        ContentTypes contentTypes = new ContentTypes(webDriver);
        contentTypes.ClickManageFieldLnk("Movie");
        if (contentTypes.IsFieldPresent("Relationships").equals(false)) {
    		contentTypes.EnterAddExistingField("Relationships");
        	contentTypes.SelectExistingField("Pub TV Relationship: field_tv_shows (TV Shows)");
        	contentParent.ClickSaveBtn();
        	com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Relationships relationships = new com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Relationships(webDriver);
        	relationships.SelectTVRelationshipWidgetDepth("Show");
        	relationships.ClickSaveSettingsBtn();
        	contentParent.VerifyMessageStatus("Saved Relationships configuration.");
        	
    	}
    	navigation.AddContent("Movie");
    	BasicInformation basicInformation = new BasicInformation(webDriver);
    	String movieTitle = random.GetCharacterString(15);
    	basicInformation.EnterTitle(movieTitle);
        basicInformation.EnterSynopsis();
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver);
        selectFile.SelectDefaultCoverImg();
        com.nbcuni.test.publisher.pageobjects.Content.Relationships relationshipsContent = new com.nbcuni.test.publisher.pageobjects.Content.Relationships(webDriver);
        relationshipsContent.SelectShow(tvShowName1);
        contentParent.WaitForThrobberNotPresent();
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState("Published");
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
    	
    	Reporter.log("STEP 5"); //TODO
    	
    	Reporter.log("STEP 6");
    	navigation.Content("Dynamic Queues");
    	DynamicQueues dynamicQueues = new DynamicQueues(webDriver);
    	dynamicQueues.ClickAddDynamicQueueLnk(dynamicQueueTypeName);
    	
        Reporter.log("STEP 7");
        String dynamicQueueTitle = random.GetCharacterString(15);
        AddDynamicQueue addDynamicQueue = new AddDynamicQueue(webDriver);
        addDynamicQueue.EnterTitle(dynamicQueueTitle);
        addDynamicQueue.CheckTargetBundle_Cbx("Movie");
        addDynamicQueue.SelectTVShow(tvShowName1);
        addDynamicQueue.ClickSortByNewestRdb();
        addDynamicQueue.ClickSaveDynamicQueueBtn();
        
        Reporter.log("STEP 8");
        navigation.Content("Dynamic Queues");
        String dynamicQueueNodeID = dynamicQueues.GetDynamicQueueNodeNumber(dynamicQueueTitle);
        String parentWindow = webDriver.getWindowHandle();
        applib.openNewWindow();
        applib.switchToNewWindow(parentWindow);
        applib.openSitePage("/dynamic-queue/" + dynamicQueueNodeID);
        contentParent.VerifyPageContentPresent(Arrays.asList(movieTitle, "Relationships", tvShowName1));
        
        //ADDITIONAL STEPS TODO
    }
    
}
