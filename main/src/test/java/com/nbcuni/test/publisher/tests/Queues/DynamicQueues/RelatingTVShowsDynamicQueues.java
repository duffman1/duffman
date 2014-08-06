package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueues;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class RelatingTVShowsDynamicQueues extends ParentTest{
	
	/*************************************************************************************
     * TEST CASE - TC4389
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20942343684
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke" })
    public void RelatingTVShowsDynamicQueues_TC4389() throws Exception{
    	
    	webDriver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
    	
        Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        Reporter.log("SETUP");
        Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Dynamic Queue");
        
        Reporter.log("STEP 2");
        taxonomy.NavigateSite("Structure>>Dynamic Queue types>>Add dynamic queue type");
        overlay.SwitchToActiveFrame();
        String dynamicQueueTypeName = random.GetCharacterString(15);
        AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webDriver);
        addDynamicQueueType.EnterName(dynamicQueueTypeName);
        addDynamicQueueType.SelectEntityType();
        addDynamicQueueType.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 3");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String tvShowName1 = createDefaultContent.TVShow("Published");
        //String tvShowName2 = createDefaultContent.TVShow("Published");
        
        Reporter.log("STEP 4");
        taxonomy.NavigateSite("Structure>>Content types>>Movie>>Manage fields");
    	overlay.SwitchToActiveFrame();
    	ContentTypes contentTypes = new ContentTypes(webDriver, applib);
    	if (contentTypes.IsFieldPresent("Relationships").equals(false)) {
    		contentTypes.EnterAddExistingField("Relationships");
        	contentTypes.SelectExistingField("Pub TV Relationship: field_tv_shows (TV Shows)");
        	contentParent.ClickSaveBtn();
        	overlay.SwitchToActiveFrame();
        	com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Relationships relationships = new com.nbcuni.test.publisher.pageobjects.Structure.ManageFields.Relationships(webDriver);
        	relationships.SelectTVRelationshipWidgetDepth("Show");
        	relationships.ClickSaveSettingsBtn();
        	contentParent.VerifyMessageStatus("Saved Relationships configuration.");
        	overlay.ClickCloseOverlayLnk();
    	}
    	else {
    		overlay.ClickCloseOverlayLnk();
    	}
    	taxonomy.NavigateSite("Content>>Add content>>Movie");
    	overlay.SwitchToActiveFrame();
    	BasicInformation basicInformation = new BasicInformation(webDriver);
    	String movieTitle = random.GetCharacterString(15);
    	basicInformation.EnterTitle(movieTitle);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver, applib);
        selectFile.SelectDefaultCoverImg();
        overlay.SwitchToActiveFrame();
        com.nbcuni.test.publisher.pageobjects.Content.Relationships relationshipsContent = new com.nbcuni.test.publisher.pageobjects.Content.Relationships(webDriver, applib);
        relationshipsContent.SelectShow(tvShowName1);
        contentParent.WaitForThrobberNotPresent();
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState("Published");
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
    	
    	Reporter.log("STEP 5"); //TODO
    	
    	Reporter.log("STEP 6");
    	taxonomy.NavigateSite("Content>>Dynamic Queues>>Add " + dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 7");
        String dynamicQueueTitle = random.GetCharacterString(15);
        AddDynamicQueue addDynamicQueue = new AddDynamicQueue(webDriver);
        addDynamicQueue.EnterTitle(dynamicQueueTitle);
        addDynamicQueue.CheckTargetBundle_Cbx("Movie");
        addDynamicQueue.SelectTVShow(tvShowName1);
        addDynamicQueue.ClickSortByNewestRdb();
        addDynamicQueue.ClickSaveDynamicQueueBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTitle));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 8");
        taxonomy.NavigateSite("Content>>Dynamic Queues");
        overlay.SwitchToActiveFrame();
        DynamicQueues dynamicQueues = new DynamicQueues(webDriver, applib);
        String dynamicQueueNodeID = dynamicQueues.GetDynamicQueueNodeNumber(dynamicQueueTitle);
        overlay.ClickCloseOverlayLnk();
        String parentWindow = webDriver.getWindowHandle();
        applib.openNewWindow();
        applib.switchToNewWindow(parentWindow);
        applib.openSitePage("/dynamic-queue/" + dynamicQueueNodeID);
        contentParent.VerifyPageContentPresent(Arrays.asList(movieTitle, "Relationships", tvShowName1));
        
        //ADDITIONAL STEPS TODO
    }
    
}
