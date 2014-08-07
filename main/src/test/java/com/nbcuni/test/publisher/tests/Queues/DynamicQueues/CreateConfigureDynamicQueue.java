package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Structure.AddViewMode;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueueTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueues;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.ManageDisplay;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CreateConfigureDynamicQueue extends ParentTest{
	
	/*************************************************************************************
     * TEST CASE - TC4197
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20794225692
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke" })
    public void CreateConfigureDynamicQueue_TC4197() throws Exception{
    	
    	webDriver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
    	
        Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        Reporter.log("STEP 2");
        Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Dynamic Queue");
        
        Reporter.log("STEP 3");
        taxonomy.NavigateSite("Structure>>Dynamic Queue types>>Add dynamic queue type");
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 4");
        String dynamicQueueTypeName = random.GetCharacterString(15);
        AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webDriver);
        addDynamicQueueType.EnterName(dynamicQueueTypeName);
        		
        addDynamicQueueType.SelectEntityType();
        addDynamicQueueType.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 5");
        taxonomy.NavigateSite("Content>>Dynamic Queues>>Add " + dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 6");
        String dynamicQueueTitle = random.GetCharacterString(15);
        AddDynamicQueue addDynamicQueue = new AddDynamicQueue(webDriver);
        addDynamicQueue.EnterTitle(dynamicQueueTitle);
        addDynamicQueue.CheckTargetBundle_Cbx("Character Profile");
        addDynamicQueue.CheckTargetBundle_Cbx("Post");
        addDynamicQueue.ClickSortByNewestRdb();
        addDynamicQueue.ClickSaveDynamicQueueBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTitle));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 7");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String characterProfileTitle = createDefaultContent.CharacterProfile("Published", null, null);
        String postTitle = createDefaultContent.Post("Published");
        
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
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitle, characterProfileTitle));
        
        Reporter.log("STEP 9"); //NA - takes an incredibly long time to load
        
        Reporter.log("STEP 10"); //NA
        
        Reporter.log("STEP 11"); //TODO
        
        Reporter.log("STEP 12");
        applib.switchToParentWindow(parentWindow);
        taxonomy.NavigateSite("Structure>>Dynamic Queue types");
        overlay.SwitchToActiveFrame();
        DynamicQueueTypes dynamicQueueTypes = new DynamicQueueTypes(webDriver, applib);
        dynamicQueueTypes.ClickEditLnk(dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 13");
        addDynamicQueueType.EnableContentTypes(Arrays.asList("Movie", "Person"));
        addDynamicQueueType.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 14");
        taxonomy.NavigateSite("Content>>Dynamic Queues>>Add " + dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
        addDynamicQueue.VerifyTargetBundlesPresent(Arrays.asList("Movie", "Person"));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 15");
        taxonomy.NavigateSite("Structure>>Dynamic Queue types");
        overlay.SwitchToActiveFrame();
        dynamicQueueTypes.ClickManageDisplayLnk(dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList("Full content", "Teaser", "RSS", 
        		"Revision comparison", "Tokens", "User Ref Preview", "Administrative Refrence Preview", 
        		"Promoted Content"));
        
        if (config.getConfigValue("AppURL").contains("install")) {
        	Reporter.log("STEP 16");
            ManageDisplay manageDisplay = new ManageDisplay(webDriver);
            manageDisplay.SelectEntityListFormat("Full content");
            contentParent.WaitForThrobberNotPresent();
            manageDisplay.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Your settings have been saved.");
            overlay.ClickCloseOverlayLnk();
            applib.switchToNewWindow(parentWindow);
            applib.refreshPage();
            contentParent.VerifyPageContentPresent(Arrays.asList(characterProfileTitle, postTitle, "Character: First Name"));
            
            Reporter.log("STEP 17");
            applib.switchToParentWindow(parentWindow);
            taxonomy.NavigateSite("Structure>>Dynamic Queue types");
            overlay.SwitchToActiveFrame();
            dynamicQueueTypes.ClickManageDisplayLnk(dynamicQueueTypeName);
            overlay.SwitchToActiveFrame();
            manageDisplay.SelectEntityListFormat("Teaser");
            contentParent.WaitForThrobberNotPresent();
            manageDisplay.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Your settings have been saved.");
            overlay.ClickCloseOverlayLnk();
            applib.switchToNewWindow(parentWindow);
            applib.refreshPage();
            contentParent.VerifyPageContentPresent(Arrays.asList(characterProfileTitle, postTitle));
            contentParent.VerifyPageContentPresent(Arrays.asList("Character: First Name", "Cover Media", "Cover Photo"));
            
            Reporter.log("STEP 18"); //TODO
            
            Reporter.log("STEP 19");
            applib.switchToParentWindow(parentWindow);
            taxonomy.NavigateSite("Structure>>Display suite>>View modes>>Add a view mode");
            overlay.SwitchToActiveFrame();
            String viewModeLabel = random.GetCharacterString(15);
            AddViewMode addViewMode = new AddViewMode(webDriver);
            addViewMode.EnterLabel(viewModeLabel);
            addViewMode.CheckEntityCbx("Node");
            addViewMode.ClickSaveBtn();
            contentParent.VerifyMessageStatus("The view mode " + viewModeLabel + " has been saved");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 20");
            taxonomy.NavigateSite("Structure>>Dynamic Queue types");
            overlay.SwitchToActiveFrame();
            dynamicQueueTypes.ClickManageDisplayLnk(dynamicQueueTypeName);
            overlay.SwitchToActiveFrame();
            
            Reporter.log("STEP 21");
            manageDisplay.SelectEntityListFormat(viewModeLabel);
            contentParent.WaitForThrobberNotPresent();
            manageDisplay.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Your settings have been saved.");
            overlay.ClickCloseOverlayLnk();
            applib.switchToNewWindow(parentWindow);
            contentParent.VerifyPageContentPresent(Arrays.asList(characterProfileTitle, postTitle, "Character: First Name"));
            
        }
        
    }
    
}
