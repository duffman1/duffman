package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Permissions;


import junit.framework.Assert;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;
import com.nbcuni.test.publisher.pageobjects.People.Permissions;
import com.nbcuni.test.publisher.pageobjects.People.Roles;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.Content;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.content.Delete;
import com.nbcuni.test.publisher.pageobjects.content.Revisions;


public class CreateConfigureAndValidatePermissionsEditor extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login Publisher 7 using Drupal 1 credentials<br>
     * Step 2 - Click on People --> Add user<br>
     * Step 3 - Populate the following fields with their corresponding values, and click on the "Create new account" button.  Username: editor Email Address: nbcuniqa1@gmail.com Password: pa55word Confirm Password: pa55word Status: Active Roles: <Put a check on the "editor" checkbox> First Name: TestFirst Last Name: TestLast<br>
     * Step 4 - Click on People --> Permissions --> Roles<br>
     * Step 5 - For the "editor" value, click on the "edit permissions" link in the "OPERATIONS" column<br>
     * Step 6 - Put a check on the following permissions, and click on the "Save Permissions" button.  Post: Create new content Post: Edit own content Post: Delete own content<br>
     * Step 7 - Click on the "Log out" link<br>
     * Step 8 - Login Publisher 7 using the editor user credentials, created from Steps 2-6<br>
     * Step 9 - Click on Content --> Add new content --> Post<br>
     * Step 10 - Populate all required fields , and click on the "Save" button<br>
	 * Step 11 - Click on "Content"<br>
     * Step 12 - For the "Post" content that was just created, verify that a dropdown appears in the "OPERATIONS" column showing the "Edit" value<br>
     * Step 13 - Expand the dropdown in the "OPERATIONS" column for the "Post" content that was just created, and verify that both "Edit" and "Delete" values appear<br>
     * Step 14 - Click on the "Log out" link<br>
     * Step 15 - Login Publisher 7 using Drupal 1 credentials<br>
     * Step 16 - Click on Content --> Add new content --> Post<br>
     * Step 17 - Populate all required fields , and click on the "Save" button<br>
     * Step 18 - Click on the "Log out" link.,The user is taken to the Publisher 7 main page where they are asked for a username and password.
     * Step 19 - Login Publisher 7 using the editor user credentials, created from Steps 2-6<br>
     * Step 20 - Click on "Content"<br>
     * Step 21 - For the "Post" content that was created using Drupal 1 credentials from Step 16-17, verify that no dropdown field appears in the "OPERATIONS" column<br>
     * Step 22 - Click on Content --> Content Revisions<br>
     * Step 23 - For the "Post" content that was created using Drupal 1 credentials from Step 16-17, verify that no dropdown field appears in the "OPERATIONS" column<br>
     * Step 24 - For the "Post" content that was created from Steps 9-10, verify that a dropdown appears in the "OPERATIONS" column showing the "Edit" value<br>
     * Step 25 - Expand the dropdown in the "OPERATIONS" column for the "Post" content that was just created, and verify that both "Edit" and "Delete" values appear<br>
     * Step 26 - Click on the "Edit" value<br>
     * Step 27 - Change the value of the "Body" field, and click on "Save"<br>
     * Step 28 - Click on "Content"<br>
     * Step 29 - For the "Post" content that was created from Steps 9-10, expand the dropdown in the "OPERATIONS" column, and click on "Delete"<br>
     * Step 30 - Click on "Content"<br>
     * Step 31 - Verify that the "Post" created from Steps 9-10 does not appear<br>
     * Step 32 - Click on the "Log out" link<br>
     * Step 33 - Repeat Steps 1 -32 but replace Step 6 and all other steps that use the "Post" content type with the following content types, and verify that the behavior is the same.  Character Profile Media Gallery Movie Person TV Episode TV Season TV Show<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke"})
    public void CreateConfigureAndValidatePermissionsEditor() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 1a (enable post module if needed)
        Modules modules = new Modules(webDriver);
        modules.VerifyModuleEnabled("Pub Post");
        
        //Step 2
        taxonomy.NavigateSite("People>>Add user");
        overlay.SwitchToFrame("People");
        
        //Step 3
        AddUser addUser = new AddUser(webDriver);
        String userName = random.GetCharacterString(15) + "@" + random.GetCharacterString(15) + ".com";
        addUser.EnterUsername(userName);
        addUser.EnterEmailAddress(userName);
        String passWord = "pa55word"; //TODO - randomize username and password generation
        addUser.EnterPassword(passWord);
        addUser.EnterConfirmPassword(passWord);
        addUser.ClickEditorRoleCbx();
        String firstName = random.GetCharacterString(15);
        addUser.EnterFirstName(firstName);
        String lastName = random.GetCharacterString(15);
        addUser.EnterLastName(lastName);
        addUser.ClickCreateNewAccountBtn();
        ContentParent contentParent = new ContentParent(webDriver);
        contentParent.VerifyMessageStatus("A welcome message with further instructions has been e-mailed to the new user " + userName + ".");
       
        //Step 4
        overlay.switchToDefaultContent();
        taxonomy.NavigateSite("People>>Permissions>>Roles");
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame("People");
        
        //Step 5
        Roles roles = new Roles(webDriver);
        roles.ClickEditorEditPermissionsLnk(); 
        overlay.switchToDefaultContent();
        //TODO - fix this in overlays for frames that have duplicate titles
        webDriver.switchTo().frame(webDriver.findElement(By.xpath("(//iframe[contains(@title, 'People')])[2]")));
        
        //Step 6
        Permissions permissions = new Permissions(webDriver);
        permissions.CheckPostCreateNewContentCbx();
        permissions.CheckPostEditOwnContentCbx();
        permissions.CheckPostDeleteOwnContentCbx();
        permissions.CheckAddAndUploadNewFilesCbx();
        permissions.ClickSaveConfigurationsBtn();
        contentParent.VerifyMessageStatus("The changes have been saved.");
        
        //Step 7
        overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent();
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
        
        //Step 8
        userLogin.Login(userName, passWord);
        
        //Step 9 and 10 (truncated)
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle = createDefaultContent.Post("Draft");
        
        //Step 11
        taxonomy.NavigateSite("Content");
        overlay.SwitchToFrame("Content");
        
        //Step 12 and 13 (truncated)
        Content content = new Content(webDriver);
        content.VerifyContentItemEditDelete(postTitle);
        
        //Step 14
        overlay.ClickCloseOverlayLnk();
        logout.ClickLogoutBtn();
        
        //Step 15
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 16 and 17 (truncated)
        String postTitle2 = createDefaultContent.Post("Draft");
        
        //Step 18
        logout.ClickLogoutBtn();
        
        //Step 19
        userLogin.Login(userName, passWord);
        
        //Step 20
        taxonomy.NavigateSite("Content");
        overlay.SwitchToFrame("Content");
        
        //Step 21
        content.VerifyContentItemEditDeleteNotPresent(postTitle2);
        
        //Step 22
        overlay.switchToDefaultContent();
        taxonomy.NavigateSite("Content>>Content Revisions");
        overlay.SwitchToFrame("Content Revisions");
        
        //Step 23
        Revisions revisions = new Revisions(webDriver);
        revisions.VerifyContentItemEditDeleteNotPresent(postTitle2);
        
        //Step 24
        revisions.VerifyContentItemEditDelete(postTitle);
        
        //Step 25 and 26 (truncated)
        revisions.ClickEditExtendMenuBtn(postTitle);
        revisions.ClickEditMenuBtn(postTitle);
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame(postTitle);
        
        //Step 27
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.EnterSynopsis();
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been updated.");
        
        //Step 28
        taxonomy.NavigateSite("Content");
        overlay.SwitchToFrame("Content");
        
        //Step 29
        content.ClickEditExtendMenuBtn(postTitle);
        content.ClickDeleteMenuBtn(postTitle);
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame(postTitle);
        Delete delete = new Delete(webDriver);
        delete.ClickDeleteBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been deleted.");
        
        //Step 30
        taxonomy.NavigateSite("Content");
        overlay.SwitchToFrame("Content");
        
        //Step 31
        content.VerifyContentItemNotPresent(postTitle);
        
        //Step 32
        overlay.ClickCloseOverlayLnk();
        logout.ClickLogoutBtn();
        
        //Step 33
        //TODO - this step is redundant by content type and not a priority. Add repetition as time allows.
        
        
        Assert.fail("Test under construction");
    }
}
