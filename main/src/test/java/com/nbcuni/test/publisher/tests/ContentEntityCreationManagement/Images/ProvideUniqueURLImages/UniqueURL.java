package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.ProvideUniqueURLImages;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class UniqueURL extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1056
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441702169 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void UniqueURL_TC1056() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	//Step 2
        	navigation.AddContent("Media Gallery");
        	
        	//Step 3
        	BasicInformation basicInformation = new BasicInformation(webWebWebDriver);
            String title = random.GetCharacterString(15);
            basicInformation.EnterTitle(title);
            basicInformation.ClickMediaItemsSelectBtn();
            
            //Step 4
            SelectFile selectFile = new SelectFile(webWebWebDriver);
            selectFile.SwitchToSelectFileFrm();
            selectFile.ClickViewLibraryBtn();
            selectFile.EnterFileName("HanSolo");
            contentParent.WaitForThrobberNotPresent();
            selectFile.ClickApplyBtn();
            contentParent.WaitForThrobberNotPresent();
            selectFile.VerifyMediaThumbnailImagePresent("HanSolo", "1");
            selectFile.VerifyMediaThumbnailImagePresent("HanSolo", "2");
            selectFile.ClickMediaThumbnailImage("1");
            selectFile.ClickMediaThumbnailImage("2");
            selectFile.ClickSubmitBtn();
            webWebWebDriver.switchTo().defaultContent();
            
            //Step 5
            MediaItems mediaItems = new MediaItems(webWebWebDriver);
            mediaItems.VerifyFileImagePresent("HanSolo", "1");
            mediaItems.VerifyFileImagePresent("HanSolo", "2");
            ContentParent contentParent = new ContentParent(webWebWebDriver);
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
        	WorkBench workBench = new WorkBench(webWebWebDriver);
        	workBench.VerifyFileImageLinkPresent("HanSolo", "1");
        	//workBench.VerifyFileImagePresent("HanSolo", "1");
        	String fileId1 = workBench.GetFileImageId("1");
        	workBench.VerifyFileImageLinkPresent("HanSolo", "1");
        	//workBench.VerifyFileImagePresent("HanSolo", "2");
        	String fileId2 = workBench.GetFileImageId("2");
        	
        	//Step 6
        	workBench.ClickWorkBenchTab("Edit Draft");
        	mediaItems.VerifyFileImagePresent("HanSolo", "1");
        	String uniqueUrl1 = mediaItems.GetImageUniqueUrl("1");
        	Assert.assertTrue(uniqueUrl1.contains(fileId1));
        	mediaItems.VerifyFileImagePresent("HanSolo", "2");
        	String uniqueUrl2 = mediaItems.GetImageUniqueUrl("2");
        	Assert.assertTrue(uniqueUrl2.contains(fileId2));
        	
        	//Step 7
        	contentParent.ClickCancelBtn();
        	applib.openSitePage(uniqueUrl1);
        	Thread.sleep(1000); 
        	workBench.VerifyFileImageLinkPresent("HanSolo", "1");
        	workBench.VerifyFileImageLinkPresent("HanSolo", "2");
        	//workBench.VerifyFileImagePresent("HanSolo", "1");
        	//workBench.VerifyFileImagePresent("HanSolo", "2");
        	contentParent.VerifyPageContentPresent(Arrays.asList("Current Item", "HanSolo", 
        			"Current Item Index", "0", "Current Item ID", 
        				fileId1, "Current Item URL", uniqueUrl1));
        	
        	//Step 8 - N/A
        	
    }
}
