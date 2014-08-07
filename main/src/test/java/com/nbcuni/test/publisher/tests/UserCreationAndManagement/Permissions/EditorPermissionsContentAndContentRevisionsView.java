package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Permissions;

import java.util.Arrays;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.Content;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;
import com.nbcuni.test.publisher.pageobjects.People.Permissions;
import com.nbcuni.test.publisher.pageobjects.People.Roles;

public class EditorPermissionsContentAndContentRevisionsView extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1393
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17766094466
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "certify"})
    public void EditorPermissionsContentAndContentRevisionsView_TC1393() throws Exception {
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2 and 3
        AddUser addUser = new AddUser(webDriver, applib);
        String userName = addUser.AddDefaultUser(Arrays.asList("editor"), false);
        
        //Step 4
        taxonomy.NavigateSite("People>>Permissions>>Roles");
        overlay.SwitchToActiveFrame();
        
        //Step 5
        Roles roles = new Roles(webDriver);
        roles.ClickEditorEditPermissionsLnk(); 
        overlay.SwitchToActiveFrame();
        
        //Step 6
        Permissions permissions = new Permissions(webDriver, applib);
        permissions.EnablePermissions(null, Arrays.asList("create post content", 
        		"edit own post content", "delete own post content", "create files"));
        permissions.DisablePermissions(null, Arrays.asList("edit any post content", "delete any post content"));
        permissions.ClickSaveConfigurationsBtn();
        contentParent.VerifyMessageStatus("The changes have been saved.");
        
        //Step 7
        overlay.ClickCloseOverlayLnk();
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
        
        //Step 8
        userLogin.Login(userName, "pa55word");
        
        //Step 9 and 10 (truncated)
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle = createDefaultContent.Post("Draft");
        
        //Step 11
        taxonomy.NavigateSite("Content");
        overlay.SwitchToActiveFrame();
        
        //Step 12 and 13 (truncated)
        Content content = new Content(webDriver, applib);
        content.VerifyContentItemEditDelete(postTitle);
        
        //Step 14
        overlay.ClickCloseOverlayLnk();
        logout.ClickLogoutBtn();
        
        //Step 15
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 16 and 17 (truncated)
        String postTitle2 = createDefaultContent.Post("Draft");
        
        //Step 18
        logout.ClickLogoutBtn();
        
        //Step 19
        userLogin.Login(userName, "pa55word");
        
        //Step 20
        taxonomy.NavigateSite("Content");
        overlay.SwitchToActiveFrame();
        
        //Step 21
        content.VerifyContentItemEditDeleteNotPresent(postTitle2);
        
        //Step 22
        overlay.ClickCloseOverlayLnk();
        taxonomy.NavigateSite("Content>>Content Revisions");
        overlay.SwitchToActiveFrame();
        
        //Step 23
        Revisions revisions = new Revisions(webDriver, applib);
        revisions.VerifyContentItemEditDeleteNotPresent(postTitle2);
        
        //Step 24
        revisions.VerifyContentItemEditDelete(postTitle);
        
        //Step 25 and 26 (truncated)
        revisions.ClickEditMenuBtn(postTitle);
        overlay.SwitchToActiveFrame();
        
        //Step 27
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent(true);
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been updated.");
        
        //Step 28
        taxonomy.NavigateSite("Content");
        overlay.SwitchToActiveFrame();
        
        //Step 29
        content.ClickEditExtendMenuBtn(postTitle);
        content.ClickDeleteMenuBtn(postTitle);
        overlay.SwitchToActiveFrame();
        Delete delete = new Delete(webDriver);
        delete.ClickDeleteBtn();
        overlay.switchToDefaultContent(true);
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been deleted.");
        
        //Step 30
        taxonomy.NavigateSite("Content");
        overlay.SwitchToActiveFrame();
        
        //Step 31
        content.VerifyContentItemNotPresent(postTitle);
        
        //Step 32 on
        //TODO - these steps are redundant by content type and not a priority. Add repetition as time allows.
        
        
    }
}
