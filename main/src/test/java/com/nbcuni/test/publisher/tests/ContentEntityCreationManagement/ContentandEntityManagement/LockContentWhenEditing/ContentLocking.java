package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.LockContentWhenEditing;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Logout;
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
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
	  
		//Step 2
	    taxonomy.NavigateSite("Configuration>>Content authoring>>Content lock");
	    overlay.SwitchToActiveFrame();
	    ContentLock contentLock = new ContentLock(webDriver, applib);
	    contentLock.CheckShowLockUnlockMessagesCbx();
	    contentLock.ClickSaveConfiguration();
	    contentParent.VerifyMessageStatus("The configuration options have been saved.");
	    overlay.ClickCloseOverlayLnk();
	    
	    //Step 3 N/A
	      
	    //Step 4
	    AddUser addUser = new AddUser(webDriver);
	    String userName = addUser.AddDefaultUser(Arrays.asList("editor"), false);
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
	    userLogin.Login(userName, "pa55word");
	        
	    //Step 6
	    CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
	    String postTitle = createDefaultContent.Post("Draft");
	        
	    //Step 7
	    taxonomy.NavigateSite("Content");
	    overlay.SwitchToActiveFrame();
	    Content content = new Content(webDriver);
	    content.ClickEditMenuBtn(postTitle);
	    overlay.SwitchToActiveFrame();
	    
	    //Step 8
	    contentParent.VerifyMessageStatus("This document is now locked against simultaneous editing. It will unlock when you navigate elsewhere.");
	    contentParent.VerifyMessageStatus("Your lock will be considered stale and up for grabs in 30 min.");
	        
        //TODO - add functionality that opens up a simultaneous browser session and validates content is actually locked.
	    //as of now this is not possible since webdriver runs one browser instance with active auth credentials in session at any given time...
    }
}
