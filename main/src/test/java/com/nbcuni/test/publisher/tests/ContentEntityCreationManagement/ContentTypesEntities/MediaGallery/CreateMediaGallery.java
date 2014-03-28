package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.MediaGallery;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.AddFile;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.MediaItems;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;

public class CreateMediaGallery extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE Create Media Gallery
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Click on "Content" >> "Add Content" >> "Media Gallery"<br>
     * Step 3 - Populate the valid value in the following mandatory fields: "Title"<br>
     * Step 4 - Click on "Select" button under "Cover Media", click on "Browse" button and choose the image from the local machine. Click on "Next" button twice then click on "Save" button.<br>
     * Step 5 - Click on "Select media" button under "Media" items. Drag and drop 3 or more images from the local machine and click on "Next" button<br>
     * Step 6 - Click on "Save" button<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void CreateMediaGallery_Test() throws Exception{
        
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
            
            //Step 4
            basicInformation.ClickCoverSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToActiveFrame();
            basicInformation.VerifyCoverImagePresent("HanSolo");
            
            //Step 5
            MediaItems mediaItems = new MediaItems(webDriver);
            mediaItems.ClickSelectBtn();
            selectFile.SwitchToSelectFileFrm();
            AddFile addFile = new AddFile(webDriver, applib);
            addFile.ClickAddFilesLnk();
            addFile.AddDefaultPicture();
            addFile.ClickStartUploadLnk();
            addFile.WaitForSuccessfulUpload();
            addFile.ClickNextBtn();
            overlay.SwitchToActiveFrame();
            mediaItems.VerifyFileImagePresent("nbclogo", "2");
            
            //Step 6
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
            
    }
}
