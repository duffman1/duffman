package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.ContentandEntityScheduling.FunctionalScheduleToPublishContentByDateTime;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.Content;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.content.RevisionState;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
/*************************************************************************************
 * TEST CASE 
 * Step 1 - Log into a new-installation Publisher test instance as Drupal User 1<br> 
 * Step 2 - Create  a Published post<br> 
 * Step 3 - Copy the URL and Log Out<br>
 * Step 4 - Paste the URL in Browser(as Unauthenticated user)<br>
 * Step 5 - Verify that the user should be able to view the corresponding published content.<br>
 * Step 6 - Log into a new-installation Publisher test instance as Drupal User 1<br> 
 * Step 7 - Move to the Post created in Step-2 <br> 
 * Step 8 - Click on Revision Tab and Populate comment on Revision page and update the post <br>
 * Step 9 - Copy the URL and Log Out<br>
 * Step 10 - Paste the URL in Browser(as Unauthenticated user)<br>
 * Step 11 - Verify that the user should be able to view the corresponding published content.<br>
 * Step 12 - Log into a new-installation Publisher test instance as Drupal User 1<br> 
 * Step 13 - Move to the Post created in Step-2 <br> 
 * Step 14 - Click on Content and Post title link from content container<br>
 * Step 15 - Copy the URL and Log Out<br>
 * Step 16 - Paste the URL in Browser(as Unauthenticated user)<br>
 * Step 17 - Verify that the user should be able to view the corresponding published content.<br>
 * Step 18 - Log into a new-installation Publisher test instance as Drupal User 1<br> 
 * Step 19 - Move to the Post created in Step-2 <br> 
 * Step 20 - Click View Tab<br>
 * Step 21 - Copy the URL and Log Out<br>
 * Step 22 - Paste the URL in Browser(as Unauthenticated user)<br>
 * Step 23 - Verify that the user should be able to view the corresponding published content.<br>
 * 
 * @throws Throwable No Return values are needed
 *************************************************************************************/
/* TODO - faizan still has work outstanding on this test
@Test(groups = {"full", "smoke"})
public class ScheduleToPublishContentByDateTime extends ParentTest {

	public void ScheduletopublishContentbyDateTime() throws Exception{
		
		//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
      
        //Step 2
        taxonomy.VerifyContentMenuExist("Content");
        taxonomy.VerifyContentMenuExist("My Workbench");
      
        //Step 2 
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle2 = createDefaultContent.Post("Published");
        Workflow workflow = new Workflow(webDriver);
        workflow.VerifyWorkflowTab("View");
        workflow.VerifyWorkflowTab("Edit Draft");
        workflow.VerifyWorkflowTab("Revisions");
        workflow.VerifyWorkflowTab("Schedule");
        workflow.ClickWorkflowTab("Revisions");
        
        
      
    	
      //Step3
    	
	}
}*/
