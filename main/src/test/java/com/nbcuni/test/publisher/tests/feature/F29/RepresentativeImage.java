package com.nbcuni.test.publisher.tests.feature.F29;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class RepresentativeImage extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC3217
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/19212549516
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "mpx", "F29"})
    public void RepresentativeImage_TC3217() throws Exception{
         
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("STEP 2");
        	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        	String postTitle = createDefaultContent.Post("Published");
        	
        	Reporter.log("STEP 3");
        	navigation.Content();
        	SearchFor searchFor = new SearchFor(webDriver);
        	searchFor.EnterTitle(postTitle);
        	searchFor.ClickApplyBtn();
        	searchFor.VerifySearchThumbnailImgPresent(postTitle, "HanSolo");
        	
        	Reporter.log("STEP 4");
        	navigation.Content("Files");
        	Thumbnails thumbnails = new Thumbnails(webDriver);
        	thumbnails.VerifyThumbnailImagePresent("HanSolo", "1");
            
            Reporter.log("STEP 5");
            navigation.AddContent("Post");
        	BasicInformation basicInformation = new BasicInformation(webDriver);
            String postTitle2 = random.GetCharacterString(15);
            basicInformation.EnterTitle(postTitle2);
            basicInformation.EnterSynopsis();
            basicInformation.ClickCoverSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SwitchToSelectFileFrm();
            selectFile.ClickPub7MPXVideoBtn();
    		selectFile.EnterTitle("AutomationWThumb");
    		selectFile.ClickApplyBtn();
    		contentParent.WaitForThrobberNotPresent();
    		selectFile.ClickPub7MPXVideoBtn();
    		selectFile.ClickMPXMediaThumbnailImage("nbclogosmall", "1");
    		selectFile.ClickSubmitBtn();
            webDriver.switchTo().defaultContent();
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Post " + postTitle2 + " has been created.");
            
            Reporter.log("STEP 6");
            navigation.Content();
            searchFor.EnterTitle(postTitle2);
        	searchFor.ClickApplyBtn();
        	searchFor.VerifySearchThumbnailImgPresent(postTitle2, "nbclogosmall");
        	
        	//TODO - step 7 to repeat 1 through 6 for all content items as time allows
    }
}