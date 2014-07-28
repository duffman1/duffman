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
     * TEST CASE - TC1048
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441495325
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CreatePost_TC1048() throws Exception {
         
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
            	overlay.switchToDefaultContent(true);
            	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
            	WorkBench workBench = new WorkBench(webDriver, applib);
            	workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList(state));
            	
            }
            
    }
}
