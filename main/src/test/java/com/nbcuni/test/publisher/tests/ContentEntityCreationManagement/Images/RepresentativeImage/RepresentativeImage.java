package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.RepresentativeImage;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class RepresentativeImage extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC3217
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/19212549516
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void RepresentativeImage_TC3217() throws Exception{
         
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	Reporter.log("STEP 2");
        	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        	String postTitle = createDefaultContent.Post("Published");
        	
        	Reporter.log("STEP 3");
        	taxonomy.NavigateSite("Content");
        	overlay.SwitchToActiveFrame();
        	SearchFor searchFor = new SearchFor(webDriver, applib);
        	searchFor.EnterTitle(postTitle);
        	searchFor.ClickApplyBtn();
        	overlay.switchToDefaultContent();
        	searchFor.VerifySearchThumbnailImgPresent(postTitle, "HanSolo");
        	
        	Reporter.log("STEP 4");
        	taxonomy.NavigateSite("Home");
        	taxonomy.NavigateSite("Content>>Files");
            overlay.SwitchToActiveFrame();
            Thumbnails thumbnails = new Thumbnails(webDriver);
        	thumbnails.VerifyThumbnailImagePresent("HanSolo", "1");
            overlay.ClickCloseOverlayLnk();
            
            Reporter.log("STEP 5");
        	taxonomy.NavigateSite("Content>>Add content>>Post");
        	overlay.SwitchToActiveFrame();
            BasicInformation basicInformation = new BasicInformation(webDriver);
            String postTitle2 = random.GetCharacterString(15);
            basicInformation.EnterTitle(postTitle2);
            basicInformation.EnterSynopsis();
            overlay.SwitchToActiveFrame();
            basicInformation.ClickCoverSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SwitchToSelectFileFrm();
            selectFile.ClickPub7MPXVideoBtn();
    		selectFile.EnterTitle("AutomationWThumb");
    		selectFile.ClickApplyBtn();
    		selectFile.WaitForFileSearchComplete();
    		selectFile.ClickMPXMediaThumbnailImage("nbclogosmall", "1");
    		selectFile.ClickSubmitBtn();
            overlay.SwitchToActiveFrame();
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Post " + postTitle2 + " has been created.");
            
            Reporter.log("STEP 6");
            taxonomy.NavigateSite("Content");
        	overlay.SwitchToActiveFrame();
        	searchFor.EnterTitle(postTitle2);
        	searchFor.ClickApplyBtn();
        	overlay.switchToDefaultContent();
        	searchFor.VerifySearchThumbnailImgPresent(postTitle2, "nbclogosmall");
        	
        	//TODO - step 7 to repeat 1 through 6 for all content items as time allows
    }
}