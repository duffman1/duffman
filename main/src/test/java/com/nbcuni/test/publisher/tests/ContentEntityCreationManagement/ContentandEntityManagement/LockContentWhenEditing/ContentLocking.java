package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.LockContentWhenEditing;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.ContentLock;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.People.AddUser;
import com.nbcuni.test.publisher.pageobjects.People.Permissions;
import com.nbcuni.test.publisher.pageobjects.People.Roles;
import org.testng.annotations.Test;
import java.util.Arrays;

public class ContentLocking extends ParentTest {
	
	/*************************************************************************************
	 * TEST CASE
	 * Step 1 - Log into a new-installation Publisher test instance as Drupal User 1<br>
	 * Step 2 - Enable content lock setting<br>
	 * Step 3 - Enable Pub Post setting <br>
	 * Step 4 - Add a new Editor<br>
	 * Step 5 - Logout and login with newly created user<br>
	 * Step 6 - Create a Draft post<br>
	 * Step 7 - Go to Content and click on edit link<br>
	 * Step 8 - Verify content lock message<br>
	 *************************************************************************************/
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void ContentLocking_Test() throws Exception {
         
		//Step 1
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
	  
		//Step 2
	    taxonomy.NavigateSite("Configuration>>Content authoring>>Content lock");
	    overlay.SwitchToActiveFrame();
	    ContentLock contentLock = new ContentLock(webDriver, applib);
	    contentLock.CheckShowLockUnlockMessagesCbx();
	    contentLock.ClickSaveConfiguration();
	    contentParent.VerifyMessageStatus("The configuration options have been saved.");
	    overlay.ClickCloseOverlayLnk();
	    
	    //Step 3
	    Modules modules = new Modules(webDriver, applib);
	    modules.VerifyModuleEnabled("Pub Post");
	        
	    //Step 4
	    taxonomy.NavigateSite("People>>Add user");
	    overlay.SwitchToActiveFrame();
	    AddUser addUser = new AddUser(webDriver);
	    String userName = random.GetCharacterString(15) + "@" + random.GetCharacterString(15) + ".com";
	    addUser.EnterUsername(userName);
	    addUser.EnterEmailAddress(userName);
	    String passWord = "pa55word";
	    addUser.EnterPassword(passWord);
	    addUser.EnterConfirmPassword(passWord);
	    addUser.ClickEditorRoleCbx();
	    String firstName = random.GetCharacterString(15);
	    addUser.EnterFirstName(firstName);
	    String lastName = random.GetCharacterString(15);
	    addUser.EnterLastName(lastName);
	    addUser.ClickCreateNewAccountBtn();        
	    contentParent.VerifyMessageStatus("A welcome message with further instructions has been e-mailed to the new user " + userName + "."); 
	    overlay.ClickCloseOverlayLnk();
	    taxonomy.NavigateSite("People>>Permissions>>Roles");  
	    overlay.SwitchToActiveFrame();
	    Roles roles = new Roles(webDriver);
	    roles.ClickEditorEditPermissionsLnk();
	    overlay.SwitchToActiveFrame();
	    Permissions permissions = new Permissions(webDriver, applib);
	    permissions.EnablePermissions(null, Arrays.asList("create post content", 
        		"edit own post content", "create files"));
	    permissions.ClickSaveConfigurationsBtn();
	    contentParent.VerifyMessageStatus("The changes have been saved.");
	    overlay.ClickCloseOverlayLnk();
	    
	    //Step 5
	    Logout logout = new Logout(webDriver);
	    logout.ClickLogoutBtn();
	    userLogin.Login(userName, passWord);
	        
	    //Step 6
	    CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
	    String postTitle = createDefaultContent.Post("Draft");
	        
	    //Step 7
	    taxonomy.NavigateSite("Content");
	    overlay.SwitchToActiveFrame();
	    Content content = new Content(webDriver, applib);
	    content.ClickEditMenuBtn(postTitle);
	    overlay.SwitchToActiveFrame();
	    
	    //Step 8
	    contentParent.VerifyMessageStatus("This document is now locked against simultaneous editing. It will unlock when you navigate elsewhere.");
	    contentParent.VerifyMessageStatus("Your lock will be considered stale and up for grabs in 30 min.");
	        
        //TODO - add functionality that opens up a simultaneous browser session and validates content is actually locked.
	    //as of now this is not possible since webdriver runs one browser instance with active auth credentials in session at any given time...
    }
}
