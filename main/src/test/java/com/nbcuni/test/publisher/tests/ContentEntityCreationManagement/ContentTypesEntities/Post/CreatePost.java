package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.Post;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class CreatePost extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to Pub7 
     * Step 1a - Navigate to create post page<br>
     * Step 2 - Verify that below fields are the mandatory ones and have star symbol marked on them:-  Basic Information tab:- 1) Title 2) Body  Publishing Options tab:- 1) Moderation state<br>
     * Step 3 - Enter value in Title and Body<br>
     * Step 4 - Upload image in Cover Media.  
     * Step 5 - Click on Publishing options tab and select Draft in Moderation state. 
     * Step 6 - Click on save<br> 
     * Step 7 - Repeat above steps with Moderation state value as Review and Published<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void CreatePost_Test() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            List<String> allStates = Arrays.asList("Draft", "Review", "Published");
            for (String state : allStates) {
            
            	//Step 1a
            	taxonomy.NavigateSite("Content>>Add content>>Post");
            	overlay.SwitchToActiveFrame();
            
            	//Step 2
            	contentParent.VerifyRequiredFields(Arrays.asList("Title", "Body"));
            	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
            	publishingOptions.ClickPublishingOptionsLnk();
            	contentParent.VerifyRequiredFields(Arrays.asList("Moderation State"));
            
            	//Step 3
            	BasicInformation basicInformation = new BasicInformation(webDriver);
            	basicInformation.ClickBasicInformationTab();
            	String postTitle = random.GetCharacterString(15);
            	basicInformation.EnterTitle(postTitle);
            	basicInformation.EnterSynopsis();
            
            	//Step 4
            	overlay.SwitchToActiveFrame();
            	basicInformation.ClickCoverSelectBtn();
            	SelectFile selectFile = new SelectFile(webDriver, applib);
            	selectFile.SelectDefaultCoverImg();
            	overlay.SwitchToActiveFrame();
            
            	//Step 5
            	publishingOptions.ClickPublishingOptionsLnk();
            	publishingOptions.SelectModerationState(state);
            
            	//Step 6
            	contentParent.ClickSaveBtn();
            	overlay.switchToDefaultContent();
            	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
            	WorkBench workBench = new WorkBench(webDriver, applib);
            	workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList(state));
            	
            }
            
    }
}
