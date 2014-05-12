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
     * TEST CASE - TC1042
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441339377
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void CreateMediaGallery_TC1042() throws Exception{
        
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
