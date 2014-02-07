package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.MediaGallery;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;

public class CreateMediaGallery extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE Create Media Gallery
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Click on "Content" >> "Add Content" >> "Media Gallery"<br>
     * Step 3 - Populate the valid value in the following mandatory fields: "Title"<br>
     * Step 4 - Click on "Select" button under "Cover Media", click on "Browse" button and choose the image from the local machine. Click on "Next" button twice then click on "Save" button.<br>
     * Step 5 [UNABLE TO AUTOMATE THIS STEP] - Click on "Select media" button under "Media" items. Drag and drop 3 or more images from the local machine and click on "Next" button<br>
     * Step 6 - Click on "Save" button<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full" })
    public void CreateMediaGallery_Test() throws Exception{
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	PageFactory.initElements(webDriver, userLogin);
            userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 2
            taxonomy.NavigateSite("Content>>Add content>>Media Gallery");
            
            //Step 3
            BasicInformation basicInformation = new BasicInformation(webDriver);
            overlay.SwitchToFrame("Create Media Gallery");
            String title = random.GetCharacterString(15);
            basicInformation.EnterTitle(title);
            
            //Step 4
            basicInformation.ClickCoverSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            PageFactory.initElements(webDriver, selectFile);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToFrame("Create Media Gallery");
            basicInformation.VerifyCoverImagePresent("HanSolo");
            
            //Step 5
            /* TODO - find a way to get multiple file uploads working
            mediaGallery.ClickMediaItemsSelectMediaBtn();
            selectFile.SwitchToSelectFileFrm();
            
            selectFile.AddMultipleFiles(Arrays.asList("/Users/brandonclark/Desktop/HanSolo.jpg", 
            		"/Users/brandonclark/Desktop/HanSolo.jpg", 
            			"/Users/brandonclark/Desktop/HanSolo.jpg"));
            
            */
            
            //Step 6
            ContentParent contentParent = new ContentParent(webDriver, applib);
            PageFactory.initElements(webDriver, contentParent);
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
            
    }
}
