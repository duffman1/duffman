package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.People.Permissions;
import com.nbcuni.test.publisher.pageobjects.Structure.*;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.*;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.ManageDisplay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CreateConfigureDynamicQueue extends GlobalBaseTest{
	
	String viewModeLabel = "";
	Boolean testSuccessful = false;
	
	/*************************************************************************************
     * TEST CASE - TC4197
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20794225692
     * @author Brandon Clark
     * @author Vineela Juturu
     * @version 1.0 Date: October 13, 2014
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "broken"})
    public void CreateConfigureDynamicQueue_TC4197() throws Exception{
    	
    	webDriver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
    	
        Reporter.log("STEP 1");
        UserLogin userLogin = appLib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        Reporter.log("SETUP");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        String charProfBiography = random.GetCharacterString(15);
        String characterProfileTitle = createDefaultContent.CharacterProfile("Published", null, null, charProfBiography);
        String postBody = random.GetCharacterString(15);
        String postTitle = createDefaultContent.Post("Published", postBody);
        String unpublishedPostBody = random.GetCharacterString(15);
        String unpublishedPostTitle = createDefaultContent.Post("Draft", unpublishedPostBody);
        System.out.println("Character profile title = " + characterProfileTitle);
        System.out.println("Post title = " + postTitle);
        System.out.println("Unpublished Post title = " + unpublishedPostTitle);
        String nameNumber_ErrorMessage = "Name cannot be a number. It is recommended that this name begin with a capital letter and contain only letters, numbers, and spaces.";
      
        Reporter.log("STEP 2");
        navigation.Modules();
        Modules modules = new Modules(webDriver);
        for (String module : Arrays.asList("Dynamic Queue", "Dynamic Queue Workbench")) {
        	modules.EnableModule(module);
        }
        
        Reporter.log("STEP 3");
        navigation.Structure("Dynamic Queue types");
        DynamicQueueTypes dynamicQueueTypes = new DynamicQueueTypes(webDriver);
        dynamicQueueTypes.ClickAddDynamicQueueTypeLnk();
        
        Reporter.log("STEP 4");
        AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webDriver);
        String dynamicQueueTypeNameNumber = "25";
        addDynamicQueueType.EnterName(dynamicQueueTypeNameNumber);
        addDynamicQueueType.ClickSaveBtn();
        ErrorChecking errorchecking = new ErrorChecking(webDriver);
        errorchecking.VerifyErrorMessagePresent(nameNumber_ErrorMessage);
        
        Reporter.log("STEP 5");
        addDynamicQueueType.ClickMachineNameEditLnk();
        String dynamicQueueTypeName = random.GetCharacterString(15);        
        addDynamicQueueType.EnterName(dynamicQueueTypeName);
        addDynamicQueueType.EnterMachineName(dynamicQueueTypeName);
        addDynamicQueueType.SelectCacheLifetime("1 min");		
        addDynamicQueueType.SelectEntityType();
        addDynamicQueueType.ClickSaveBtn();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
        
        Reporter.log("STEP 6&7");
        navigation.Content("Dynamic Queues");
        DynamicQueues dynamicQueues = new DynamicQueues(webDriver);
        dynamicQueues.ClickAddDynamicQueueLnk(dynamicQueueTypeName);
        String dynamicQueueTitle = random.GetCharacterString(15);
        AddDynamicQueue addDynamicQueue = new AddDynamicQueue(webDriver);
        addDynamicQueue.EnterTitle(dynamicQueueTitle);       
        addDynamicQueue.CheckTargetBundle_Cbx("Character Profile");
        addDynamicQueue.CheckTargetBundle_Cbx("Post");
        addDynamicQueue.ClickSortByNewestRdb();
        addDynamicQueue.SelectModerationState("Publish");
        addDynamicQueue.ClickSaveDynamicQueueBtn();
        
        Reporter.log("STEP 8 ");
        navigation.Content("Dynamic Queues");
        dynamicQueues.VerifyDynamicQueueStatus(dynamicQueueTitle, "Published");
        
        Reporter.log("STEP 9 - MOVED TO SETUP");
        
        Reporter.log("STEP 10 ");
        String dynamicQueueNodeID = dynamicQueues.GetDynamicQueueNodeNumber(dynamicQueueTitle);
        String parentWindow = webDriver.getWindowHandle();
        appLib.openNewWindow();
        appLib.switchToNewWindow(parentWindow);
        appLib.openSitePage("/dynamic-queue/" + dynamicQueueNodeID);
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostTitle, postTitle, characterProfileTitle));
        
        Reporter.log("STEP 11"); //NA - takes an incredibly long time to load
        
        Reporter.log("STEP 12"); //NA
        
        Reporter.log("STEP 13"); //TODO
        
        Reporter.log("STEP 14");
        appLib.switchToParentWindow(parentWindow);
        navigation.Structure("Dynamic Queue types");
        dynamicQueueTypes.ClickEditLnk(dynamicQueueTypeName);
        
        Reporter.log("STEP 15");
        addDynamicQueueType.EnableContentTypes(Arrays.asList("Movie", "Person"));
        addDynamicQueueType.ClickSaveBtn();
        
        Reporter.log("STEP 16");
        navigation.Content("Dynamic Queues");
        dynamicQueues.ClickAddDynamicQueueLnk(dynamicQueueTypeName);
        addDynamicQueue.VerifyTargetBundlesPresent(Arrays.asList("Movie", "Person"));
        
        Reporter.log("STEP 17");
        navigation.Structure("Dynamic Queue types");
        dynamicQueueTypes.ClickManageDisplayLnk(dynamicQueueTypeName);
        contentParent.VerifyPageContentPresent(Arrays.asList("Full content", "Teaser", "Revision comparison",
                "Tokens"));
        
    	Reporter.log("STEP 18");
    	ManageDisplay manageDisplay = new ManageDisplay(webDriver);
        manageDisplay.SelectEntityListFormat("Full content");
        contentParent.WaitForThrobberNotPresent();
        manageDisplay.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        appLib.switchToNewWindow(parentWindow);
        appLib.refreshPage();
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostTitle, postTitle, characterProfileTitle));
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostBody, postBody, charProfBiography));
        contentParent.VerifyPageContentPresent(Arrays.asList("Character: First Name", "Cover Media"));
        
        Reporter.log("STEP 19");
        appLib.switchToParentWindow(parentWindow);
        navigation.Structure("Dynamic Queue types");
        dynamicQueueTypes.ClickManageDisplayLnk(dynamicQueueTypeName);
        manageDisplay.SelectEntityListFormat("Teaser");
        contentParent.WaitForThrobberNotPresent();
        manageDisplay.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        appLib.switchToNewWindow(parentWindow);
        appLib.refreshPage();
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostTitle, postTitle, characterProfileTitle));
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostBody, postBody, charProfBiography));
        contentParent.VerifyPageContentNotPresent(Arrays.asList("Character: First Name", "Cover Media"));
        
        Reporter.log("STEP 20"); //TODO
        
        Reporter.log("STEP 21");
        appLib.switchToParentWindow(parentWindow);
        navigation.Structure("Display suite");
        DisplaySuite displaySuite = new DisplaySuite(webDriver);
        displaySuite.ClickViewModesLnk();
        ViewModes viewModes = new ViewModes(webDriver);
        viewModes.ClickAddViewModeLnk();
        viewModeLabel = random.GetCharacterString(15);
        AddViewMode addViewMode = new AddViewMode(webDriver);
        addViewMode.EnterLabel(viewModeLabel);
        addViewMode.CheckEntityCbx("Node");
        addViewMode.ClickSaveBtn();
        contentParent.VerifyMessageStatus("The view mode " + viewModeLabel + " has been saved");
        
        Reporter.log("STEP 22,23,24");
        navigation.Structure("Content types");
        ContentTypes contentTypes = new ContentTypes(webDriver);
        contentTypes.ClickManageDisplayLnk("Post");
        com.nbcuni.test.publisher.pageobjects.Structure.ManageDisplay manageDisplays = new com.nbcuni.test.publisher.pageobjects.Structure.ManageDisplay(webDriver);
        manageDisplays.ClickViewMode(viewModeLabel);
        manageDisplays.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        manageDisplays.ClickViewModeTab(viewModeLabel);
        for (String fieldLabel : Arrays.asList("Body", "Categories", "Tags", "Contributor", "Media Gallery", 
        		"Cover Media", "Short Description")) {
        	manageDisplays.SelectFormat(fieldLabel, "<Hidden>");
        	contentParent.WaitForThrobberNotPresent();
        }
        manageDisplays.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        
        navigation.Structure("Content types");
        contentTypes.ClickManageDisplayLnk("Character Profile");
	    manageDisplays.ClickViewMode(viewModeLabel);
	    manageDisplays.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        manageDisplays.ClickViewModeTab(viewModeLabel);
        for (String fieldLabel : Arrays.asList("Biography", "Character: First Name", "Character: Middle Name",
        		"Character: Last Name", "Cover Photo", "Alias", "Birth Date", "Character: Prefix", "Character: Suffix",
        		"External Links", "Short Biography")) {
        	manageDisplays.SelectFormat(fieldLabel, "<Hidden>");
        	contentParent.WaitForThrobberNotPresent();
        }
        manageDisplays.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        
        Reporter.log("STEP 25");
        navigation.Structure("Dynamic Queue types");
        dynamicQueueTypes.ClickManageDisplayLnk(dynamicQueueTypeName);
        manageDisplay.SelectEntityListFormat(viewModeLabel);
        contentParent.WaitForThrobberNotPresent();
        manageDisplay.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        
        Reporter.log("STEP 26");
        appLib.switchToNewWindow(parentWindow);
        Thread.sleep(60000); //cache expiration
        appLib.refreshPage();
        contentParent.VerifyPageContentPresent(Arrays.asList(unpublishedPostTitle, postTitle, characterProfileTitle));
        contentParent.VerifyPageContentNotPresent(Arrays.asList(unpublishedPostBody, postBody, charProfBiography));
        
        Reporter.log("STEP 27"); 
        appLib.switchToParentWindow(parentWindow);
        navigation.People("Permissions");
        Permissions permissions = new Permissions(webDriver, appLib);
        permissions.EnablePermissions("anonymous user", Arrays.asList("access dynamic queues"));
        permissions.EnablePermissions("authenticated user", Arrays.asList("access dynamic queues"));
        permissions.ClickSaveConfigurationsBtn();
        contentParent.VerifyMessageStatus("The changes have been saved.");
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
       
        Reporter.log("STEP 28");
        appLib.switchToNewWindow(parentWindow);
        appLib.refreshPage();
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitle, characterProfileTitle));
        contentParent.VerifyPageContentNotPresent(Arrays.asList(unpublishedPostTitle, unpublishedPostBody));
        
        //cleanup
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        navigation.Structure("Display suite");
		displaySuite.ClickViewModesLnk();
        viewModes.ClickDeleteLnk(viewModeLabel);
        Delete delete = new Delete(webDriver);
        delete.ClickDeleteBtn();
        contentParent.VerifyMessageStatus("The view mode " + viewModeLabel + " has been deleted.");
        
        testSuccessful = true;
        
   } 
    
   @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "broken"}, dependsOnMethods = {"CreateConfigureDynamicQueue_TC4197"}, alwaysRun=true)
   public void Cleanup() throws Exception {
		if (testSuccessful == false && viewModeLabel != "") {
			
			UserLogin userLogin = appLib.openApplication();
	        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
	        navigation.Structure("Display suite");
	        DisplaySuite displaySuite = new DisplaySuite(webDriver);
	        displaySuite.ClickViewModesLnk();
	        ViewModes viewModes = new ViewModes(webDriver);
	        viewModes.ClickDeleteLnk(viewModeLabel);
	        Delete delete = new Delete(webDriver);
	        delete.ClickDeleteBtn();
	        contentParent.VerifyMessageStatus("The view mode " + viewModeLabel + " has been deleted.");
		}
	}
}
