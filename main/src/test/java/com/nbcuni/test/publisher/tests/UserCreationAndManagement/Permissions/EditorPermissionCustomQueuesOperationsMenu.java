package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Permissions;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;

public class EditorPermissionCustomQueuesOperationsMenu extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC1379
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17745652850 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify"})
    public void EditorPermissionCustomQueuesOperationsMenu_TC1379() throws Exception {
    	
    	/* COMMENTING OUT THIS TEST - WAITING FOR INFO FROM SRUTHI
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
    	//Step 2
    	taxonomy.NavigateSite("Structure>>Queues>>Add queue type");
    	overlay.SwitchToActiveFrame();
    	
    	//Step 3
    	AddQueueType addQueueType = new AddQueueType(webDriver, applib);
    	String queueTypeName = random.GetCharacterString(15);
    	addQueueType.EnterName(queueTypeName);
    	addQueueType.SelectEntityType("Node");
    	
    	//Step 4
    	addQueueType.EnableContentTypes(Arrays.asList("Post", "Media Gallery"));
    	addQueueType.ClickSaveQueueTypeBtn();
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 5
    	taxonomy.NavigateSite("Content>>Queues>>Add " + queueTypeName);
    	overlay.SwitchToActiveFrame();
    	
    	//Step 6
    	Queues queues = new Queues(webDriver);
    	String queueTitle = random.GetCharacterString(15);
    	queues.EnterTitle(queueTitle);
    	queues.ClickSaveQueueBtn();
    	overlay.SwitchToActiveFrame();
    	queues.VerifyQueuesInList(Arrays.asList(queueTitle));
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 7
    	taxonomy.NavigateSite("Content>>Queues>>Add Promo Queue");
    	overlay.SwitchToActiveFrame();
    	
    	//Step 8
    	String queueTitle2 = random.GetCharacterString(15);
    	queues.EnterTitle(queueTitle2);
    	queues.ClickSaveQueueBtn();
    	overlay.SwitchToActiveFrame();
    	queues.VerifyQueuesInList(Arrays.asList(queueTitle, queueTitle2));
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 9
    	taxonomy.NavigateSite("People>>Permissions>>Roles");
    	overlay.SwitchToActiveFrame();
    	
    	//Step 10
    	Roles roles = new Roles(webDriver);
    	roles.ClickEditorEditPermissionsLnk();
    	overlay.SwitchToActiveFrame();
    	
    	//Step 11
    	Permissions permissions = new Permissions(webDriver, applib);
    	permissions.DisablePermissions(null, Arrays.asList("administer queue types", 
    			"administer queue", "edit any promo_queue queue"));
    	
    	//Step 12
    	permissions.EnablePermissions(null, Arrays.asList("edit any " + queueTypeName.toLowerCase() + " queue",
    			"create " + queueTypeName.toLowerCase() + " queue"));
    	permissions.ClickSaveConfigurationsBtn();
    	contentParent.VerifyMessageStatus("The changes have been saved.");
    	overlay.ClickCloseOverlayLnk();
    	
    	//Setup
    	AddUser addUser = new AddUser(webDriver);
        String userName = addUser.AddDefaultUser(Arrays.asList("editor"));
    	
        //Step 13
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
        userLogin.Login(userName, "pa55word");
        
        //Step 14
        taxonomy.NavigateSite("Content>>Queues");
        overlay.SwitchToActiveFrame();
        
        //Step 15
        queues.VerifyQueueEditDeleteNotPresent(queueTitle2);
        
        //Step 16 and 17
        queues.ClickEditQueueMenuBtn(queueTitle);
        overlay.SwitchToActiveFrame();
        
        //Step 18
        String editedQueueTitle = random.GetCharacterString(15);
        queues.EnterTitle(editedQueueTitle);
        queues.ClickSaveQueueBtn();
        queues.VerifyQueuesInList(Arrays.asList(editedQueueTitle, queueTitle2));
        
        //Step 19
        queues.ClickEditQueueExtendMenuBtn(editedQueueTitle);
        queues.ClickDeleteQueueMenuBtn(editedQueueTitle);
        overlay.SwitchToActiveFrame();
        
        //Step 20
        Delete delete = new Delete(webDriver);
        delete.ClickDeleteBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("Deleted Queue " + editedQueueTitle + ".");
        overlay.ClickCloseOverlayLnk();
        
        //Step 21 - N/A
        
        //Cleanup
        taxonomy.NavigateSite("Structure>>Queues");
        overlay.SwitchToActiveFrame();
        QueueTypes queueTypes = new QueueTypes(webDriver, applib);
        queueTypes.ClickDeleteQueueLnk(queueTypeName);
        overlay.SwitchToActiveFrame();
        delete.ClickDeleteBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("Deleted Queue type " + queueTypeName + ".");
        
    	Assert.fail("Test under construction - waiting on input from sruthi on step 14.");*/
    }
}
