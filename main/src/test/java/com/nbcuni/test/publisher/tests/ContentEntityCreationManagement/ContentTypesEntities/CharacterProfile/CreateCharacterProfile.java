package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.CharacterProfile;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.CoverPhoto;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Taxonomy.Taxonomy;

public class CreateCharacterProfile extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Click on Content >> add new content >> Character Profile<br>
     * Step 3 - Populate the valid value in the following mandatory fields: Character, First Name OR Character, Last Name<br>
     * Step 4 - Click on "Select" button under Cover Media. Click on "Next" button twice, then click on "Save" button<br>
     * Step 5 - Click on the "Save" button<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full"})
    public void CreateCharacterProfile_Test() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	PageFactory.initElements(webDriver, userLogin);
            userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 2
            taxonomy.NavigateSite("Content>>Add content>>Character Profile");
            
            //Step 3
            CharactersInformation charactersInformation = new CharactersInformation(webDriver);
            PageFactory.initElements(webDriver, charactersInformation);
            overlay.SwitchToActiveFrame();
            String characterName = random.GetCharacterString(15);
            charactersInformation.EnterCharacterFirstName(characterName);
            
            //Step 4
            CoverPhoto coverPhoto = new CoverPhoto(webDriver);
            PageFactory.initElements(webDriver, coverPhoto);
            coverPhoto.ClickSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            PageFactory.initElements(webDriver, selectFile);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToFrame("Create Character Profile");
            coverPhoto.VerifyFileImagePresent("HanSolo");
            
            //Step 5
            ContentParent contentParent = new ContentParent(webDriver, applib);
            PageFactory.initElements(webDriver, contentParent);
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Character Profile " + characterName + " has been created.");
            
       
    }
}
