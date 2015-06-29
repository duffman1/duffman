package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Permissions;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;
import com.nbcuni.test.publisher.pageobjects.People.Permissions;
import com.nbcuni.test.publisher.pageobjects.People.Roles;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

import java.util.Arrays;

public class EditorPermissionsContentAndContentRevisionsView extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1393
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17766094466
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify"})
    public void EditorPermissionsContentAndContentRevisionsView_TC1393() throws Exception {
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 2 and 3
        AddUser addUser = new AddUser(webWebWebDriver);
        String userName = addUser.AddDefaultUser(Arrays.asList("editor"), false);
        
        //Step 4
        navigation.People("Permissions", "Roles");
        
        //Step 5
        Roles roles = new Roles(webWebWebDriver);
        roles.ClickEditorEditPermissionsLnk(); 
        
        //Step 6
        Permissions permissions = new Permissions(webWebWebDriver, applib);
        permissions.EnablePermissions(null, Arrays.asList("create post content", 
        		"edit own post content", "delete own post content", "create files", "create character_profile content"));
        permissions.DisablePermissions(null, Arrays.asList("edit any post content", "delete any post content"));
        permissions.ClickSaveConfigurationsBtn();
        contentParent.VerifyMessageStatus("The changes have been saved.");
        
        //Step 7
        Logout logout = new Logout(webWebWebDriver);
        logout.ClickLogoutBtn();
        
        //Step 8
        userLogin.Login(userName, "pa55word");
        
        //Step 9 and 10 (truncated)
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webWebWebDriver);
        String postTitle = createDefaultContent.Post("Draft");
        
        //Step 11
        navigation.Content();
        
        //Step 12 and 13 (truncated)
        Content content = new Content(webWebWebDriver);
        content.VerifyContentItemEditDelete(postTitle);
        
        //Step 14
        logout.ClickLogoutBtn();
        
        //Step 15
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 16 and 17 (truncated)
        String postTitle2 = createDefaultContent.Post("Draft");
        
        //Step 18
        logout.ClickLogoutBtn();
        
        //Step 19
        userLogin.Login(userName, "pa55word");
        
        //Step 20
        navigation.Content();
        
        //Step 21
        content.VerifyContentItemEditDeleteNotPresent(postTitle2);
        
        //Step 22
        navigation.Content("Content Revisions");
        
        //Step 23
        Revisions revisions = new Revisions(webWebWebDriver);
        revisions.VerifyContentItemEditDeleteNotPresent(postTitle2);
        
        //Step 24
        revisions.VerifyContentItemEditDelete(postTitle);
        
        //Step 25 and 26 (truncated)
        revisions.ClickEditMenuBtn(postTitle);
        
        //Step 27
        BasicInformation basicInformation = new BasicInformation(webWebWebDriver);
        basicInformation.EnterSynopsis();
        contentParent.ClickSaveBtn();
        webWebWebDriver.switchTo().defaultContent();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been updated.");
        
        //Step 28
        navigation.Content();
        
        //Step 29
        content.ClickEditExtendMenuBtn(postTitle);
        content.ClickDeleteMenuBtn(postTitle);
        Delete delete = new Delete(webWebWebDriver);
        delete.ClickDeleteBtn();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been deleted.");
        
        //Step 30
        navigation.Content();
        
        //Step 31
        content.VerifyContentItemNotPresent(postTitle);
        
        //Step 32 on
        //TODO - these steps are redundant by content type and not a priority. Add repetition as time allows.
        
        
    }
}
