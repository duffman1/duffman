package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.ProvideUniqueURLImages;

import java.util.Arrays;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UniqueURL extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1056
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441702169 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void UniqueURL_TC1056() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	//Step 2
        	taxonomy.NavigateSite("Content>>Add content>>Media Gallery");
        	overlay.SwitchToActiveFrame();
        	
        	//Step 3
        	BasicInformation basicInformation = new BasicInformation(webDriver);
            String title = random.GetCharacterString(15);
            basicInformation.EnterTitle(title);
            basicInformation.ClickMediaItemsSelectBtn();
            
            //Step 4
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SwitchToSelectFileFrm();
            selectFile.ClickViewLibraryBtn();
            selectFile.EnterFileName("HanSolo");
            selectFile.WaitForFileSearchComplete();
            selectFile.ClickApplyBtn();
            selectFile.WaitForFileSearchComplete();
            selectFile.VerifyMediaThumbnailImagePresent("HanSolo", "1");
            selectFile.VerifyMediaThumbnailImagePresent("HanSolo", "2");
            selectFile.ClickMediaThumbnailImage("1");
            selectFile.ClickMediaThumbnailImage("2");
            selectFile.ClickSubmitBtn();
            overlay.SwitchToActiveFrame();
            
            //Step 5
            MediaItems mediaItems = new MediaItems(webDriver);
            mediaItems.VerifyFileImagePresent("HanSolo", "1");
            mediaItems.VerifyFileImagePresent("HanSolo", "2");
            ContentParent contentParent = new ContentParent(webDriver, applib);
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
        	WorkBench workBench = new WorkBench(webDriver, applib);
        	workBench.VerifyFileImagePresent("HanSolo", "1");
        	String fileId1 = workBench.GetFileImageId("1");
        	workBench.VerifyFileImagePresent("HanSolo", "2");
        	String fileId2 = workBench.GetFileImageId("2");
        	
        	//Step 6
        	workBench.ClickWorkBenchTab("Edit Draft");
        	overlay.SwitchToActiveFrame();
        	mediaItems.VerifyFileImagePresent("HanSolo", "1");
        	String uniqueUrl1 = mediaItems.GetImageUniqueUrl("1");
        	Assert.assertTrue(uniqueUrl1.contains(fileId1));
        	mediaItems.VerifyFileImagePresent("HanSolo", "2");
        	String uniqueUrl2 = mediaItems.GetImageUniqueUrl("2");
        	Assert.assertTrue(uniqueUrl2.contains(fileId2));
        	
        	//Step 7
        	contentParent.ClickCancelBtn();
        	overlay.switchToDefaultContent();
        	webDriver.navigate().to(uniqueUrl1);
        	workBench.VerifyFileImagePresent("HanSolo", "1");
        	workBench.VerifyFileImagePresent("HanSolo", "2");
        	contentParent.VerifyPageContentPresent(Arrays.asList("Current Item", "HanSolo", 
        			"Current Item Index", "0", "Current Item ID", 
        				fileId1, "Current Item URL", uniqueUrl1));
        	
        	//Step 8 - N/A
        	
    }
}
