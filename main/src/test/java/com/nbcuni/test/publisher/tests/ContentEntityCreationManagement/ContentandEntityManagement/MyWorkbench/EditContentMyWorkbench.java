package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.MyWorkbench;

import java.util.Arrays;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.MyWorkbench.MyWork;
import org.testng.annotations.Test;

public class EditContentMyWorkbench extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login as admin  Note: Code for this User Story was initially deployed to http://qa1stg.publisher.nbcuni.com/ .,
	 * Step 2 - Create a post content item.
	 * Step 3 - Navigate to 'My Workbench' in the admin menu.
	 * Step 4 - Verify post data content item is present in the my work table.
	 * Step 5 - Click the 'edit' link for the created post item.
	 * Step 6 - Change state to 'review' and save item.
	 * Step 7 - Verify content item saved and moderation state updated in my work table.
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void EditContentMyWorkbench_Test() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	//Step 2
        	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        	String postTitle = createDefaultContent.Post("Draft");
        	
        	//Step 3
        	taxonomy.NavigateSite("My Workbench");
        	overlay.SwitchToActiveFrame();
        	ErrorChecking errorChecking = new ErrorChecking(webDriver, applib);
        	errorChecking.VerifyNoMessageErrorsPresent();
        	
        	//Step 4
        	MyWork myWork = new MyWork(webDriver);
        	myWork.VerifyContentItemData(Arrays.asList(postTitle, "Draft", "Post", "sec ago"));
        	
        	//Step 5
        	myWork.ClickEditLnk(postTitle);
        	overlay.SwitchToActiveFrame();
        	
        	//Step 6
        	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        	publishingOptions.ClickPublishingOptionsLnk();
        	publishingOptions.SelectModerationState("Review");
        	contentParent.ClickSaveBtn();
        	overlay.SwitchToActiveFrame();
        	
        	//Step 7
        	contentParent.VerifyMessageStatus("Post " + postTitle + " has been updated.");
        	myWork.VerifyContentItemData(Arrays.asList(postTitle, "Review", "Post", "sec ago"));
        	
    }
}
