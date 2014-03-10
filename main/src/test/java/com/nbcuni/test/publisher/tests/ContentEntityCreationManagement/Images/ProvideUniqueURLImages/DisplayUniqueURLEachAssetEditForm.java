package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.ProvideUniqueURLImages;

import java.util.Arrays;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DisplayUniqueURLEachAssetEditForm extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log in to P7 ,Login  successful 
     * Step 2 - Go to Content-> Add content-> Media Gallery ,Create Media Gallery overlay appears 
     * Step 3 - Set the required fields with valid data and click "Select media" in "Media Items" field,"Select a file" overlay appears 
     * Step 4 - Go to View Library tab and select 2- 4 images and Click Submit.  Note:  Note the File Ids of the image selected, from Content-> Files-> Click on the the image selected -> hover  around Edit tab at Title page-> note the file Id  at the left bottom URL. ,Images gets selected in "Create media Gallery " overlay 
     * Step 5 - Save the media gallery created and verify that all the images are seen on the title page.   Note: Note the node id created from the browser window. ,Media gallery saved successfully and Title page appears  with 'View' page and all images are seen as expected 
     * Step 6 - Click on 'Edit Draft' tab and verify that under media items all the images are displayed with there unique URL.  Eg.http://qa3dev.publisher.nbcuni.com/node/8/item/7  . Here the item/7 is the File id of the image. ,Images are seen as expected with their unique URL. 
     * Step 7 - Click on the any URL link -> Modified node view for media gallery appears->Verify the details 1. Current item- Link to image  2. Current Item Index- The image location in that particular node, index number starts from 0  3. Current Item ID - Item id is the file ID  4. Current item URL. ,Details appears as expected. 
     * Step 8 - Logout from P7 ,Logout successful 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void DisplayUniqueURLEachAssetEditForm_Test() throws Exception{
         
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
        	Assert.assertTrue(uniqueUrl1.contains(fileId1.replace("file-", "")));
        	mediaItems.VerifyFileImagePresent("HanSolo", "2");
        	String uniqueUrl2 = mediaItems.GetImageUniqueUrl("2");
        	Assert.assertTrue(uniqueUrl2.contains(fileId2.replace("file-", "")));
        	
        	//Step 7
        	contentParent.ClickCancelBtn();
        	overlay.switchToDefaultContent();
        	webDriver.navigate().to(uniqueUrl1);
        	workBench.VerifyFileImagePresent("HanSolo", "1");
        	workBench.VerifyFileImagePresent("HanSolo", "2");
        	contentParent.VerifyPageContentPresent(Arrays.asList("Current Item", "HanSolo.jpg", 
        			"Current Item Index", "0", "Current Item ID", 
        				fileId1.replace("file-", ""), "Current Item URL", uniqueUrl1));
        	
        	//Step 8 - N/A
        	
    }
}
