package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.MyWorkbench;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.MyWorkbench.MyWork;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

import java.util.Arrays;

public class EditContentMyWorkbench extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1394
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17766277487
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void EditContentMyWorkbench_TC1394() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	//Step 2
        	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webWebWebDriver);
        	String postTitle = createDefaultContent.Post("Draft");
        	
        	//Step 3
        	navigation.WorkBench();
        	
        	//Step 4
        	MyWork myWork = new MyWork(webWebWebDriver);
        	myWork.VerifyContentItemData(Arrays.asList(postTitle, "Draft", "Post", "sec ago"));
        	
        	//Step 5
        	myWork.ClickEditLnk(postTitle);
        	
        	//Step 6
        	PublishingOptions publishingOptions = new PublishingOptions(webWebWebDriver);
        	publishingOptions.ClickPublishingOptionsLnk();
        	publishingOptions.SelectModerationState("Review");
        	contentParent.ClickSaveBtn();
        	
        	//Step 7
        	contentParent.VerifyMessageStatus("Post " + postTitle + " has been updated.");
        	myWork.VerifyContentItemData(Arrays.asList(postTitle, "Review", "Post", "sec ago"));
        	
    }
}
