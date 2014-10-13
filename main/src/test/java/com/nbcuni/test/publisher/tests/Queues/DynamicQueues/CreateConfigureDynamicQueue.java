package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.People.Permissions;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Structure.AddViewMode;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueueTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueues;

public class CreateConfigureDynamicQueue extends ParentTest{
	
	/*************************************************************************************
     * TEST CASE - TC4197
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20794225692
     * @author Brandon Clark
     * @author Vineela Juturu
     * @version 1.0 Date: October 13, 2014
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke" })
    public void CreateConfigureDynamicQueue_TC4197() throws Exception{
    	
    	webDriver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
    	
        Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        Reporter.log("SETUP");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String charProfBiography = random.GetCharacterString(15);
        String characterProfileTitle = createDefaultContent.CharacterProfile("Published", null, null, charProfBiography);
        String postBody = random.GetCharacterString(15);
        String postTitle = createDefaultContent.Post("Published", postBody);
        String unpublishedPostBody = random.GetCharacterString(15);
        String unpublishedPostTitle = createDefaultContent.Post("Draft", unpublishedPostBody);
        String nameNumber_ErrorMessage = "Name cannot be a number. It is recommended that this name begin with a capital letter and contain only letters, numbers, and spaces.";
      
        Reporter.log("STEP 2");
        Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Dynamic Queue");
        
        Reporter.log("STEP 3");
        taxonomy.NavigateSite("Structure>>Dynamic Queue types>>Add dynamic queue type");
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 4");
        AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webDriver);
        String dynamicQueueTypeNameNumber = "25";
        addDynamicQueueType.EnterName(dynamicQueueTypeNameNumber);
        addDynamicQueueType.ClickSaveBtn();
        ErrorChecking errorchecking = new ErrorChecking(webDriver, applib);
        errorchecking.VerifyErrorMessagePresent(nameNumber_ErrorMessage);
        
        Reporter.log("STEP 5");
        addDynamicQueueType.ClickMachineNameEditLnk();
        String dynamicQueueTypeName = random.GetCharacterString(15);        
        addDynamicQueueType.EnterName(dynamicQueueTypeName);
        addDynamicQueueType.EnterMachineName(dynamicQueueTypeName);
        addDynamicQueueType.SelectCacheLifetime("1 min");		
        addDynamicQueueType.SelectEntityType();
        addDynamicQueueType.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 6&7");
        taxonomy.NavigateSite("Content>>Dynamic Queues>>Add " + dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();       
        String dynamicQueueTitle = random.GetCharacterString(15);
        AddDynamicQueue addDynamicQueue = new AddDynamicQueue(webDriver);
        addDynamicQueue.EnterTitle(dynamicQueueTitle);       
        addDynamicQueue.CheckTargetBundle_Cbx("Character Profile");
        addDynamicQueue.CheckTargetBundle_Cbx("Post");
        addDynamicQueue.ClickSortByNewestRdb();
        addDynamicQueue.SelectModerationState("Publish");
        addDynamicQueue.ClickSaveDynamicQueueBtn();
        overlay.switchToDefaultContent(true);
        
        Reporter.log("STEP 8 ");
        taxonomy.NavigateSite("Content>>Dynamic Queues");
        overlay.SwitchToActiveFrame();
        DynamicQueues dynamicQueues = new DynamicQueues(webDriver, applib);
        dynamicQueues.VerifyDynamicQueueStatus(dynamicQueueTitle, "Published");
        
        Reporter.log("STEP 9 - MOVED TO SETUP");
        
        Reporter.log("STEP 10 ");
        String dynamicQueueNodeID = dynamicQueues.GetDynamicQueueNodeNumber(dynamicQueueTitle);
        overlay.ClickCloseOverlayLnk();
        String parentWindow = webDriver.getWindowHandle();
        applib.openNewWindow();
        applib.switchToNewWindow(parentWindow);
        applib.openSitePage("/dynamic-queue/" + dynamicQueueNodeID);
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostTitle, postTitle, characterProfileTitle));
        
        Reporter.log("STEP 11"); //NA - takes an incredibly long time to load
        
        Reporter.log("STEP 12"); //NA
        
        Reporter.log("STEP 13"); //TODO
        
        Reporter.log("STEP 14");
        applib.switchToParentWindow(parentWindow);
        taxonomy.NavigateSite("Structure>>Dynamic Queue types");
        overlay.SwitchToActiveFrame();
        DynamicQueueTypes dynamicQueueTypes = new DynamicQueueTypes(webDriver, applib);
        dynamicQueueTypes.ClickEditLnk(dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 15");
        addDynamicQueueType.EnableContentTypes(Arrays.asList("Movie", "Person"));
        addDynamicQueueType.ClickSaveBtn();
        overlay.ClickCloseOverlayLnk();

        Reporter.log("STEP 16");
        taxonomy.NavigateSite("Content>>Dynamic Queues>>Add " + dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
        addDynamicQueue.VerifyTargetBundlesPresent(Arrays.asList("Movie", "Person"));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 17");
        taxonomy.NavigateSite("Structure>>Dynamic Queue types");
        overlay.SwitchToActiveFrame();
        dynamicQueueTypes.ClickManageDisplayLnk(dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList("Full content", "Teaser", "Revision comparison", 
        		"Tokens"));
        
    	Reporter.log("STEP 18");
    	com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.ManageDisplay manageDisplay = new com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.ManageDisplay(webDriver);
        manageDisplay.SelectEntityListFormat("Full content");
        contentParent.WaitForThrobberNotPresent();
        manageDisplay.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        overlay.ClickCloseOverlayLnk();
        applib.switchToNewWindow(parentWindow);
        applib.refreshPage();
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostTitle, postTitle, characterProfileTitle));
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostBody, postBody, charProfBiography));
        contentParent.VerifyPageContentPresent(Arrays.asList("Character: First Name", "Cover Media"));
        
        Reporter.log("STEP 19");
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
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostTitle, postTitle, characterProfileTitle));
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostBody, postBody, charProfBiography));
        contentParent.VerifyPageContentNotPresent(Arrays.asList("Character: First Name", "Cover Media"));
        
        Reporter.log("STEP 20"); //TODO
        
        Reporter.log("STEP 21");
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
        
        Reporter.log("STEP 22,23,24");
        taxonomy.NavigateSite("Structure>>Content types>>Post>>Manage display");
        com.nbcuni.test.publisher.pageobjects.Structure.ManageDisplay md = new com.nbcuni.test.publisher.pageobjects.Structure.ManageDisplay(webDriver);
	    overlay.SwitchToActiveFrame();
	    md.ClickViewModecheckbox(viewModeLabel);
        md.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        md.ClickViewModeTab(viewModeLabel);
        overlay.SwitchToActiveFrame();
        md.draganddroprows_to_hiddensection();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        overlay.ClickCloseOverlayLnk();
        
        taxonomy.NavigateSite("Structure>>Content types>>Character Profile>>Manage display");
	    overlay.SwitchToActiveFrame();
	    md.ClickViewModecheckbox(viewModeLabel);
        md.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        md.ClickViewModeTab(viewModeLabel);
        overlay.SwitchToActiveFrame();
        md.draganddroprows_to_hiddensection();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 25");
        taxonomy.NavigateSite("Structure>>Dynamic Queue types");
        overlay.SwitchToActiveFrame();
        dynamicQueueTypes.ClickManageDisplayLnk(dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();       
        manageDisplay.SelectEntityListFormat(viewModeLabel);
        contentParent.WaitForThrobberNotPresent();
        manageDisplay.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 26");
        applib.switchToNewWindow(parentWindow);
        applib.refreshPage();
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostTitle, postTitle, characterProfileTitle));
        contentParent.VerifyPageContentNotPresent(Arrays.asList(unpublishedPostBody, postBody, charProfBiography));
        
        Reporter.log("STEP 27"); 
        applib.switchToParentWindow(parentWindow);
        taxonomy.NavigateSite("People>>Permissions");
        overlay.SwitchToActiveFrame();
        Permissions permissions = new Permissions(webDriver, applib);
        permissions.EnablePermissions("anonymous user", Arrays.asList("access dynamic queues"));
        permissions.EnablePermissions("authenticated user", Arrays.asList("access dynamic queues"));
        permissions.ClickSaveConfigurationsBtn();
        contentParent.VerifyMessageStatus("The changes have been saved.");
        overlay.ClickCloseOverlayLnk();
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
       
        Reporter.log("STEP 28");
        applib.switchToNewWindow(parentWindow);
        applib.refreshPage();
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitle, characterProfileTitle));
        contentParent.VerifyPageContentNotPresent(Arrays.asList(unpublishedPostTitle, unpublishedPostBody));
        
   } 
}
