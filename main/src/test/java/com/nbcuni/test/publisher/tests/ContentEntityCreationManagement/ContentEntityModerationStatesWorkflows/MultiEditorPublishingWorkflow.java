package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentEntityModerationStatesWorkflows;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;

public class MultiEditorPublishingWorkflow extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE
     * Setup - Create a new editor user and a new Senior editor user
     * Step 1 - Log in to the test instance as a user with the role of Editor and only Editor. ,Login succeeds. 
     * Step 2 - Successfully create and save a content node of any type. ,a  Content is created successfully. b  The View tab is displayed for the new node. 
     * Step 3 - Click Edit Draft. ,The node is opened for editing. 
     * Step 4 - Click Publishing options, and then verify that the expected behaviors are met. ,a  The Publishing options tab is displayed.  b  An Assign to drop down list control is available to the right of (or, if overlays are disabled, below) the Moderation state control.  c  The value of Moderation State is Draft, and the value of Assign to reflects the username of the current user.  Note: The parenthetical number after the username value in Assign to is the numeric Drupal ID of the user account named. 
     * Step 5 - Add this step after Step 7:  Click the Dashboard tab. ,a  The Dashboard tab is displayed.  b  The My Work and All Recent Content lists do not include an Assigned to column.   
     * Step 6 - In the main menu, click My Workbench, and then click My work.  ,a  The My Work list is displayed.  b  An entry for the node saved in the previous step is displayed.  c  In the entry for the node saved in the previous step, the current Moderation State is Draft.  d  An Assigned to column is present between the Last Updated and Actions columns, and for the entry for the node saved in the previous step, the value of Assigned to is the username of its author.  
     * Step 7 - Display the Content list, and then click the title of the node created at Step 2. ,The View tab of the selected node is displayed. 
     * Step 8 - Click Edit Draft. ,The Edit overlay is displayed for the current node. 
     * Step 9 - In the Publishing options tab:  a  Ensure that the value of Moderation state is set to Draft;  b  Set the value of Assign to reflect the username of a Senior Editor user identified at Step 1.  c  Click Save. ,The changes are successfully saved.  The View tab is displayed for the current node. 
     * Step 10 - In the main menu, click My Workbench, and then examine the Dashboard tab for the presence of an entry for the node reassigned in the previous step.,An entry for the node reassigned in the previous step is not present in the Dashboard tab of the My Workbench page.
     * Step 11 - Click My Work, and examine the list for the presence of an entry for the node saved and reassigned at Step 9.,An entry for the node saved and reassigned at Step 9 is present in the list.
     * Step 12 - Click the header of the Assigned to column, and note whether or not doing so re-sorts the list. ,Clicking the header of the Assigned to column re-sorts the list. 
     * Step 13 - Log out of publisher, and then log in as the Senior Editor user to which the node was reassigned at Step 9. ,Logout and login succeed. 
     * Step 14 - Click My Workbench, and then examine the Dashboard tab for the presence of an entry for the node reassigned at Step 9. ,An entry for the node reassigned at Step 9 is present in the Dashboard list.
     * Step 15 - Log out of Publisher 7. ,Logout succeeds.
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void MultiEditorPublishingWorkflow_Test() throws Exception {
        
    	/* COMMENTING TEST OUT PER DISCUSSION WITH SRUTHI.
    	 * TEST CASE IS BLOCKED BY OUTSTANDING DEFECT DE3710
    	 
    	//Setup - create editor user
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
    	taxonomy.NavigateSite("People>>Add user");
        overlay.SwitchToActiveFrame();
        AddUser addUser = new AddUser(webDriver);
        String editorUserName = random.GetCharacterString(15) + "@" + random.GetCharacterString(15) + ".com";
        addUser.EnterUsername(editorUserName);
        addUser.EnterEmailAddress(editorUserName);
        String passWord = "pa55word";
        addUser.EnterPassword(passWord);
        addUser.EnterConfirmPassword(passWord);
        addUser.ClickEditorRoleCbx();
        String firstName = random.GetCharacterString(15);
        addUser.EnterFirstName(firstName);
        String lastName = random.GetCharacterString(15);
        addUser.EnterLastName(lastName);
        addUser.ClickCreateNewAccountBtn();
        ContentParent contentParent = new ContentParent(webDriver, applib);
        contentParent.VerifyMessageStatus("A welcome message with further instructions has been e-mailed to the new user " + editorUserName + ".");
        overlay.ClickCloseOverlayLnk();
        
        //Setup - create senior editor user
        taxonomy.NavigateSite("People>>Add user");
        overlay.SwitchToActiveFrame();
        String seniorEditorUserName = random.GetCharacterString(15) + "@" + random.GetCharacterString(15) + ".com";
        addUser.EnterUsername(seniorEditorUserName);
        addUser.EnterEmailAddress(seniorEditorUserName);
        addUser.EnterPassword(passWord);
        addUser.EnterConfirmPassword(passWord);
        addUser.ClickSeniorEditorRoleCbx();
        addUser.EnterFirstName(firstName);
        addUser.EnterLastName(lastName);
        addUser.ClickCreateNewAccountBtn();
        contentParent.VerifyMessageStatus("A welcome message with further instructions has been e-mailed to the new user " + seniorEditorUserName + ".");
        overlay.ClickCloseOverlayLnk();
        
        //Setup - ensure editor and senior editor have correct permissions
        taxonomy.NavigateSite("People>>Permissions>>Roles");
        overlay.SwitchToActiveFrame();
        Roles roles = new Roles(webDriver);
        roles.ClickEditorEditPermissionsLnk(); 
        overlay.SwitchToActiveFrame();
        Permissions permissions = new Permissions(webDriver);
        permissions.EnablePermissions(Arrays.asList("create post content", 
        		"edit any post content", "delete any post content", "create files", "access workbench"));
        permissions.ClickSaveConfigurationsBtn();
        contentParent.VerifyMessageStatus("The changes have been saved.");
        overlay.ClickCloseOverlayLnk();
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
        
        //Step 1
        userLogin.Login(editorUserName, passWord);
            
        //Step 2
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle = createDefaultContent.Post("Draft");
        
        //Step 3
        WorkBench workBench = new WorkBench(webDriver, applib);
        workBench.ClickWorkBenchTab("Edit Draft");
        overlay.SwitchToActiveFrame();
            
        //Step 4
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.VerifyModerationStateValue("Draft");
        publishingOptions.VerifyAssignToValue(editorUserName);
        overlay.ClickCloseOverlayLnk();
        
        //Step 5
        //TODO
        
        //Step 6
        taxonomy.NavigateSite("My Workbench");
        overlay.SwitchToActiveFrame();
        MyWork myWork = new MyWork(webDriver);
        myWork.ClickMyWorkBtn();
        overlay.SwitchToActiveFrame();
        SearchFor searchFor = new SearchFor(webDriver, applib);
        searchFor.EnterTitle(postTitle);
        searchFor.ClickApplyBtn();
        Thread.sleep(1000); //TODO - dynamic wait
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitle, "Draft", "Post", editorUserName));
        
        //Step 7
        myWork.ClickEditLnk(postTitle);
        overlay.SwitchToActiveFrame();
        
        //Step 8 - N/A 
        
        //Step 9
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.EnterAssignTo(seniorEditorUserName);
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been updated.");
        
        //Step 10
        taxonomy.NavigateSite("My Workbench");
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentNotPresent(Arrays.asList(postTitle));
        
        //Step 11
        myWork.ClickMyWorkBtn();
        overlay.SwitchToActiveFrame();
        searchFor.EnterTitle(postTitle);
        searchFor.ClickApplyBtn();
        Thread.sleep(1000); //TODO - dynamic wait
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitle, seniorEditorUserName));
        
        //Step 12 TODO automate as time allows
        
        //Step 13
        logout.ClickLogoutBtn();
        userLogin.Login(seniorEditorUserName, passWord);
        
        //Step 14
        taxonomy.NavigateSite("My Workbench");
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitle));
        
        //Step 15 - N/A
        */
    }
}
